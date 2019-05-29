/*******************************************************************************
 * Copyright (c) 2009-2016 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.thelastcheck.io.cims;

import com.thelastcheck.commons.buffer.ByteArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class defines image objects which are instances of CIMS image
 * objects containing multiple segments of images and codeline data.
 * Each <code>ImageObject</code> is made up of 1-16 segments of data.
 * Segments are referenced using a segment number from 0-15. Segment
 * 0 contains the MICR data and other flags associated with the images
 * contained in the other segments. This is represented by the
 * CimsCodedDataHeader. Segments 1-4 are standardized to contain
 * the following:
 * <br/>
 * <br/>
 * <ul>
 * <li>Segment 1 - A front, black-white image</li>
 * <li>Segment 2 - A front, grey-scale image</li>
 * <li>Segment 3 - A rear, black-white image</li>
 * <li>Segment 4 - A rear, grey-scale image</li>
 * </ul>
 * <br/>
 * Any or none of these segments may be present. Other segments are used
 * as determined by a particular application.
 */
public class ImageObject {
    private static Logger log = LoggerFactory.getLogger(ImageObject.class);
    public static final int MAX_SEGMENTS = 15;
    public static final String TYPE_JPEG = "24";
    public static final String TYPE_TIFF = "00";

    public final static int FRONTBW = 1;
    public final static int FRONTGS = 2;
    public final static int REARBW = 3;
    public final static int REARGS = 4;

    // cims header
    CimsObjectHeader cimsObjectHeader;
    // segment 0
    CimsCodedDataHeader cimsCodedDataHeader;
    // segment array for segments 1-15
    byte[][] segmentArray = new byte[MAX_SEGMENTS][];

    /**
     * Default constructor for <code>ImageObject</code>. Will create minimal
     * necessary data for this object to function.
     */
    public ImageObject() {
        this.cimsObjectHeader = new CimsObjectHeader();
        this.cimsCodedDataHeader = new CimsCodedDataHeader();
    }

    /**
     * Default constructor for <code>ImageObject</code>. Will create minimal
     * necessary data for this object to function.
     */
    public ImageObject(CimsObjectHeader header) {
        this.cimsObjectHeader = header;
        this.cimsCodedDataHeader = new CimsCodedDataHeader();
    }

    /**
     * Primary constructor for <code>ImageObject</code>. Will store
     * the buffer reference and calculate the segment offsets and
     * lengths for use in retrieving data for this object.
     * It also stores all image segments so it can recreate original buffer.
     *
     * @param buffer The byte array containing the ImageObject values.
     */
    public ImageObject(ByteArray buffer) {
        byte[] cimsObjectHeaderArray = buffer.read(0, CimsObjectHeader.RECORD_LENGTH);
        cimsObjectHeader = new CimsObjectHeader(cimsObjectHeaderArray);

        //initialize coded data header from input buffer
        int offset0 = cimsObjectHeader.getSegmentOffset(0);
        int length0 = cimsObjectHeader.getSegmentLength(0);
        byte[] cimsCodedDataHeaderArray = new byte[CimsCodedDataHeader.RECORD_LENGTH];
        buffer.read(offset0, cimsCodedDataHeaderArray, 0, length0);
        cimsCodedDataHeader = new CimsCodedDataHeader(cimsCodedDataHeaderArray);

        //initialize segment array, i.e. load all images
        for (int i = 0; i < segmentArray.length; i++) {
            int segmentOffset = cimsObjectHeader.getSegmentOffset(i + 1);
            int segmentLength = cimsObjectHeader.getSegmentLength(i + 1);
            if (segmentLength > 0) {
                segmentArray[i] = buffer.read(segmentOffset, segmentLength);
            }
        }
    }

    /**
     * This method will return the buffer that contains whole CIMS object.
     *
     * @return A byte array containing whole CIMS object
     */
    public ByteArray toByteArray() {
        ByteArray buffer = new ByteArray(
                CimsObjectHeader.RECORD_LENGTH + CimsCodedDataHeader.RECORD_LENGTH + getLengthOfImageSegments());
        updateCimsObjectHeader();
        int lastSegmentCopiedOffset = 0;
        //copy image segments to output buffer and
        //update segment offsets and lengths for image segments in CIMS object header
        for (int i = 0; i < segmentArray.length; i++) {
            if (segmentArray[i] != null && segmentArray[i].length > 0) {
                //segment offset
                int segmentOffset =
                        CimsObjectHeader.RECORD_LENGTH + CimsCodedDataHeader.RECORD_LENGTH + lastSegmentCopiedOffset;
                //copy segment array to output buffer
                buffer.write(segmentArray[i], 0, segmentArray[i].length, segmentOffset);
                cimsObjectHeader.setSegmentOffset(i + 1, segmentOffset);
                cimsObjectHeader.setSegmentLength(i + 1, segmentArray[i].length);
                lastSegmentCopiedOffset += segmentArray[i].length;
            }
        }
        // copy last so pick up any updates to header during loop.
        buffer.write(cimsObjectHeader.toByteArray(), 0, CimsObjectHeader.RECORD_LENGTH, 0);
        buffer.write(cimsCodedDataHeader.toByteArray(), 0, CimsCodedDataHeader.RECORD_LENGTH, CimsObjectHeader.RECORD_LENGTH);
        return buffer;
    }

    /**
     *
     */
    private void updateCimsObjectHeader() {
        int totalRecordLength =
                CimsObjectHeader.RECORD_LENGTH + CimsCodedDataHeader.RECORD_LENGTH + getLengthOfImageSegments();
        cimsObjectHeader.setSegmentLength(0, CimsCodedDataHeader.RECORD_LENGTH);
        cimsObjectHeader.setSegmentOffset(0, CimsObjectHeader.RECORD_LENGTH);
        cimsObjectHeader.setTotalRecordLength(totalRecordLength);
        cimsObjectHeader.setSegmentCount(getSegmentCount());
    }

    /**
     * This method will return the length of a segment.
     *
     * @param segmentNumber The number of the segment to be written.
     * @return An int containing the length of the segment.
     * @throws IllegalArgumentException This exception is thrown if the
     *                                  <code>segmentNumber</code> is outside the range for the valid number
     *                                  of segments (0-15).
     */
    public int getSegmentLength(int segmentNumber) {
        if (log.isDebugEnabled())
            log.debug("getImageObjectSegmentLen:" + segmentNumber);

        if (segmentNumber < 1 || segmentNumber > MAX_SEGMENTS) {
            throw new IllegalArgumentException("Invalid Segment number");
        }

        if (segmentArray[segmentNumber - 1] == null) {
            return 0;
        }
        return segmentArray[segmentNumber - 1].length;
    }

    /**
     * This method will return the type of the image contained in this
     * segment. If the segment does not exist, null is returned.
     *
     * @param segmentNumber The number of the segment to be written.
     * @return A string value indicating the type of the segment,
     * <code>00->tiff, 24->jpeg, null</code> if segment does not exist.
     * @throws IllegalArgumentException This exception is thrown if the
     *                                  <code>segmentNumber</code> is outside the range for the valid number
     *                                  of segments (0-15).
     */
    public String getSegmentType(int segmentNumber) {
        if (log.isDebugEnabled())
            log.debug("getSegmentType:" + segmentNumber);

        if (segmentNumber < 1 || segmentNumber > MAX_SEGMENTS) {
            throw new IllegalArgumentException();
        }

        if (segmentArray[segmentNumber - 1] == null || segmentArray[segmentNumber - 1].length == 0)
            return null;

        byte[] buffer = segmentArray[segmentNumber - 1];
        if (isTypeTiff(buffer)) {
            return TYPE_TIFF;
        } else {
            return TYPE_JPEG;
        }
    }

    private boolean isTypeTiff(byte[] buffer) {
        if (buffer.length < 4) return false;
        if ((buffer[0] == 'I') &&
                (buffer[1] == 'I') &&
                (buffer[2] == 42) &&
                (buffer[3] == 0)) {
            return true;
        }
        if ((buffer[0] == 'M') &&
                (buffer[1] == 'M') &&
                (buffer[2] == 0) &&
                (buffer[3] == 42)) {
            return true;
        }
        return false;
    }

    /**
     * This method will return a buffer containing the segment data for
     * the segment number requested. If the segment
     * does not exist, a <code>null</code> value is returned.
     *
     * @param segmentNumber The number of the segment to be returned.
     * @return A byte array containing the segment.
     * @throws IllegalArgumentException This exception is thrown if the
     *                                  <code>segmentNumber</code> is outside the range for the valid number
     *                                  of segments (0-15).
     */
    public byte[] getSegment(int segmentNumber) {
        if (log.isDebugEnabled())
            log.debug("getSegment:" + segmentNumber);

        if (segmentNumber < 0 || segmentNumber > MAX_SEGMENTS) {
            throw new IllegalArgumentException();
        } else {
            if (segmentNumber == 0) {
                return cimsCodedDataHeader.toByteArray();
            } else {
                return segmentArray[segmentNumber - 1];
            }
        }
    }

    /**
     * This method will add a segment data for
     * the segment number requested. If the segment number
     * does not exist, exception is thrown.
     *
     * @param segmentNumber The number of the segment to be added.
     * @param segmentBuffer A byte array containing the segment.
     */
    public void addSegment(int segmentNumber, byte[] segmentBuffer) {
        if (log.isDebugEnabled())
            log.debug("addSegment:" + segmentNumber);
        if (segmentNumber > 0 && segmentNumber <= MAX_SEGMENTS) {
            segmentArray[segmentNumber - 1] = segmentBuffer;
            updateCimsObjectHeader();
        } else {
            throw new IllegalArgumentException("Invalid segment number");
        }
    }

    /**
     * This method will remove a segment data for the segment number requested.
     * If the segment number does not exist, exception is thrown.
     *
     * @param segmentNumber The number of the segment to be added.
     */
    public void removeSegment(int segmentNumber) {
        if (log.isDebugEnabled())
            log.debug("removing Segment/resetting values for segment:" + segmentNumber);
        if (segmentNumber > 0 && segmentNumber <= MAX_SEGMENTS) {
            segmentArray[segmentNumber - 1] = null;
            updateCimsObjectHeader();
        } else {
            throw new IllegalArgumentException("Invalid segment number");
        }
    }

    /**
     * This method calculates the total length of all segments that are not null
     * or their length is greater than 0.
     *
     * @return Total length of all image segments.
     */
    private int getLengthOfImageSegments() {
        int total = 0;
        for (int i = 0; i < segmentArray.length; i++) {
            if (segmentArray[i] != null && segmentArray[i].length > 0) {
                total += segmentArray[i].length;
            }
        }
        return total;
    }

    /**
     * This method returns number of image segments.
     *
     * @return Total number of all image segments.
     */
    public byte getSegmentCount() {
        byte count = 1; // always count segment 0
        for (int i = 0; i < segmentArray.length; i++) {
            if (segmentArray[i] != null && segmentArray[i].length > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return
     */
    public CimsObjectHeader getCimsObjectHeader() {
        updateCimsObjectHeader();
        return cimsObjectHeader;
    }

    /**
     * @return
     */
    public CimsCodedDataHeader getCimsCodedDataHeader() {
        return cimsCodedDataHeader;
    }

    /**
     * @param header
     */
    public void setCimsObjectHeader(CimsObjectHeader header) {
        cimsObjectHeader = header;
    }

    /**
     * @param segment0
     */
    public void setCimsCodedDataHeader(CimsCodedDataHeader segment0) {
        this.cimsCodedDataHeader = segment0;
    }

}
