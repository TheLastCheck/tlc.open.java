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

import com.thelastcheck.commons.base.utils.Preconditions;
import com.thelastcheck.commons.buffer.ByteArray;

public class CimsObjectHeader {

    public static final int RECORD_LENGTH = 208;
    private final ByteArray buffer;

    /**
     * create default cims header, and set all predefined values
     */
    public CimsObjectHeader() {
        buffer = new ByteArray(RECORD_LENGTH);
        setHeaderRecordFormat((byte) 0x02);
    }

    public CimsObjectHeader(byte[] array) {
        Preconditions.checkArgument(array.length == RECORD_LENGTH,
                "Buffer length must be [%s], was [%s]",
                RECORD_LENGTH, array.length);
        buffer = new ByteArray(array);
    }

    public byte[] toByteArray() {
        return buffer.getArray().value;
    }

    /**
     * Internal helper routine to set/clear bits
     *
     * @param b            - boolean value true to set, false to clear
     * @param displacement is the location in the buffer of the byte to set the bits in
     * @param value        is the bits to set in the byte -- only last byte is used.
     */
    private void setOrClearBit(boolean b, int displacement, byte value) {
        if (b) {
            buffer.setBit(displacement, value);
        } else {
            buffer.clearBit(displacement, value);
        }
    }

    private static final int ELRCDHLQ_DISP = 0;

    public String getHighLevelQualifierName() {
        return buffer.readAsString(ELRCDHLQ_DISP, 8);
    }

    public void setHighLevelQualifierName(String s) {
        buffer.write(s, ELRCDHLQ_DISP, 8);
    }

    private static final int ELRCUOBL_DISP = 8;

    public boolean getImageRecordIndicator() {
        return buffer.testBit(ELRCUOBL_DISP, (byte) 0x80);
    }

    public void setImageRecordIndicator(boolean b) {
        setOrClearBit(b, ELRCUOBL_DISP, (byte) 0x80);
    }

    public int getTotalRecordLength() {
        boolean b = getImageRecordIndicator();
        setImageRecordIndicator(false);
        int length = buffer.readAsInt(ELRCUOBL_DISP);
        setImageRecordIndicator(b);
        return length;
    }

    public void setTotalRecordLength(int totalRecordLength) {
        if (totalRecordLength < 0) {
            throw new IllegalArgumentException();
        }
        boolean b = getImageRecordIndicator();
        buffer.write(totalRecordLength, ELRCUOBL_DISP);
        setImageRecordIndicator(b);
    }

    private static final int ELRCUDFT_DISP = 12;

    public byte getHeaderRecordFormat() {
        return buffer.readAsByte(ELRCUDFT_DISP);
    }

    public void setHeaderRecordFormat(byte b) {
        buffer.write(b, ELRCUDFT_DISP);
    }

    private static final int ELRCDNAM_DISP = 16;

    public String getObjectName() {
        return buffer.readPns(ELRCDNAM_DISP, 10);
    }

    public void setObjectName(String s) {
        buffer.writeAsPns(s, ELRCDNAM_DISP, 10);
    }

    private final static int ELRCDUSR_DISP = 26;

    // private final static int ELRCDUSR_LEN = 1;
    // private final static byte ELRCDUSR_NOI = 0x40;
    public byte getCimsUtilityControlValue() {
        return (byte) (buffer.readAsByte(ELRCDUSR_DISP) & 0x30);
    }

    public boolean isOnSuspectList() {
        return getOnSuspectList();
    }

    public boolean getOnSuspectList() {
        return buffer.testBit(ELRCDUSR_DISP, (byte) 0x80);
    }

    public void setOnSuspectList(boolean b) {
        setOrClearBit(b, ELRCDUSR_DISP, (byte) 0x80);
    }

    private static final int ELRCUDSN_DISP = 28;

    public String getElrcudsnFieldAsString() {
        return buffer.readAsString(ELRCUDSN_DISP, 51);
    }

    public void setElrcudsnFieldAsString(String s) {
        buffer.write(s, ELRCUDSN_DISP, 51);
    }

    public byte[] getElrcudsnField() {
        return buffer.read(ELRCUDSN_DISP, 51);
    }

    public void setElrcudsnField(byte[] b) {
        buffer.write(b, 0, 51, ELRCUDSN_DISP);
    }

    public boolean isNoImageSegments() {
        return getNoImageSegments();
    }

    public boolean getNoImageSegments() {
        return buffer.testBit(ELRCDUSR_DISP, (byte) 0x40);
    }

    public void setNoImageSegments(boolean b) {
        setOrClearBit(b, ELRCDUSR_DISP, (byte) 0x40);
    }

    private final static int ELRCDSCN_DISP = 79;

    public byte getSegmentCount() {
        return buffer.readAsByte(ELRCDSCN_DISP);
    }

    public void setSegmentCount(byte b) {
        buffer.write(b, ELRCDSCN_DISP);
    }

    private final static int ELRCDSOF_DISP = 80;

    public int getSegmentOffset(int segmentNumber) {
        return buffer.readAsInt(ELRCDSOF_DISP + segmentNumber * 8);
    }

    public void setSegmentOffset(int segmentNumber, int offset) {
        buffer.write(offset, ELRCDSOF_DISP + segmentNumber * 8);
    }

    public int getSegmentLength(int segmentNumber) {
        return buffer.readAsInt(ELRCDSOF_DISP + segmentNumber * 8 + 4);
    }

    public void setSegmentLength(int segmentNumber, int length) {
        buffer.write(length, ELRCDSOF_DISP + segmentNumber * 8 + 4);
    }

}
