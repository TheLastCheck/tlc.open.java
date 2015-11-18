/*******************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.thelastcheck.io.base;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidFormatException;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.base.exception.RecordReaderException;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class InputStreamRecordReader implements
        Iterable<Record>, Closeable {

    protected static final String US_ASCII = ByteArray.ASCII_CHARSET_NAME;
    protected static final String EBCDIC = ByteArray.EBCDIC_CHARSET_NAME;

    protected static final String END_OF_STREAM_ERROR =
            "End of stream reached before finished processing expected data.";

    private InputStream is;
    private List<RecordFilter> filterList = new ArrayList<RecordFilter>();
    private Record cachedRecord;
    private int recordCount;
    private long offset;
    private long bytesReadForRecord;

    private boolean skipInvalidRecords;

    /**
     * Used as an internal buffer when reading into ByteArray that does not have
     * an underlying array. This buffer is allocated when needed and is always
     * the largest buffer ever needed during the read process.
     */
    private byte[] maxBuffer;

    public InputStreamRecordReader(InputStream is) {
        this(is, false);
    }

    public InputStreamRecordReader(InputStream is, boolean skipInvalidRecords) {
        super();
        this.is = is;
        this.skipInvalidRecords = skipInvalidRecords;
    }

    public void close() throws IOException {
        if (is != null) {
            is.close();
            is = null;
        }
    }

    public boolean isSkipInvalidRecords() {
        return skipInvalidRecords;
    }

    public void setSkipInvalidRecords(boolean skipInvalidRecords) {
        this.skipInvalidRecords = skipInvalidRecords;
    }

    /**
     * @return
     * @throws IOException
     */
    protected final boolean isStreamAvailable() throws IOException {
        if (is == null || is.available() == 0) {
            return false;
        }
        return true;
    }

    protected final int read(ByteArray record) throws IOException {
        return read(record, 0, record.getLength());
    }

    protected final int read(ByteArray record, int displacement, int length)
            throws IOException {
        int count;
        if (record.hasArray()) {
            ByteArray.UnderlyingArray array = record.getArray();
            count = read(array.value, array.offset + displacement, length);
        } else {
            byte[] buffer = getBuffer(length);
            count = read(buffer, 0, length);
            record.write(buffer, 0, count, displacement);
        }
        return count;
    }

    private byte[] getBuffer(int size) {
        if (maxBuffer == null || maxBuffer.length < size) {
            maxBuffer = new byte[size];
        }
        return maxBuffer;
    }

    private int read(byte[] data, int displacement, int length)
            throws IOException {
        int bytesRead = 0;
        int totalBytesRead = 0;
        int bytesRemaining = length;
        int currentDisplacement = displacement;
        while (bytesRemaining > 0) {
            bytesRead = is.read(data, currentDisplacement, bytesRemaining);
            if (bytesRead == -1) {
                break;
            }
            bytesRemaining -= bytesRead;
            currentDisplacement += bytesRead;
            totalBytesRead += bytesRead;
        }
        bytesReadForRecord += totalBytesRead;
        return totalBytesRead;
    }

    /**
     * When a new record is read, it is passed through all of the filters in the
     * list. Any filter can provide a replacement object for the original item.
     * The new object will be the one passed to subsequent filters. If any
     * filter returns null, then no more filters are called and the record is
     * skipped and NOT returned to the caller.
     *
     * @param record
     * @return
     */
    private Record processFilters(Record record) {
        for (RecordFilter filter : filterList) {
            record = filter.filter(record);
            if (record == null) {
                return null;
            }
        }
        return record;
    }

    /**
     * Adds a filter to the list.
     *
     * @param recordFilter
     */
    public void addFilter(RecordFilter recordFilter) {
        filterList.add(recordFilter);
    }

    /**
     * This method returns the next record in the stream. If there are no more
     * records, return null.
     *
     * @return the next record in the stream or null.
     * @throws IOException
     * @throws EOFException
     * @throws InvalidDataException
     * @throws InvalidStandardLevelException
     * @throws InvalidFormatException
     */
    public Record nextRecord() throws IOException, EOFException {

        if (cachedRecord != null) {
            Record record = cachedRecord;
            cachedRecord = null;
            return record;
        }

        bytesReadForRecord = 0;
        Record record = null;
        do {
            if (!isStreamAvailable()) {
                return null;
            }

            try {
                record = readNextRecord();
                record.recordPosition(++recordCount);
                record.offsetPosition(offset);
                offset += bytesReadForRecord;

                record = processFilters(record);
            } catch (InvalidFormatException e) {
                if (!skipInvalidRecords) throw e;
                // go ahead and update the offset counter and total record count
                ++recordCount;
                offset += bytesReadForRecord;
            }

        } while (record == null);

        return record;
    }

    protected abstract Record readNextRecord() throws IOException, EOFException;

    public Iterator<Record> iterator() {
        return new Iterator<Record>() {

            public boolean hasNext() {
                /*
                 * If we already have a record waiting to be retrieved, then
                 * return true;
                 */
                if (cachedRecord != null) {
                    return true;
                }
                /*
                 * Go see if we have a next record. If not, return false. If
                 * true, store it for use in the next pickup.
                 */
                try {
                    cachedRecord = nextRecord();
                    if (cachedRecord == null) {
                        return false;
                    }
                } catch (Exception e) {
                    throw new RecordReaderException(e);
                }
                return true;
            }

            public Record next() {
                Record record = null;
                try {
                    record = nextRecord();
                } catch (Exception e) {
                    throw new RecordReaderException(e);
                }
                if (record == null) {
                    throw new NoSuchElementException();
                }
                return record;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

}