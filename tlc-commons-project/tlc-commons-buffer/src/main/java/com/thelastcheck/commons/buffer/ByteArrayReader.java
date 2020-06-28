/*
 * Copyright (c) 2009-2020 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.thelastcheck.commons.buffer;

import java.io.IOException;
import java.io.Reader;

public class ByteArrayReader extends Reader {
    private final ByteArray     buffer;
    private final int           limit;
    private int                 position;
    private int                 mark;
    private boolean             isOpen;

    /**
     * Create a new Reader that will return data from a ByteArray. This reader
     * assumes that the ByteArray is a stream of ASCII or EBCDIC characters
     * based on the encoding scheme for the ByteArray. All data is returned from
     * the read routines as Unicode characters.
     */
    public ByteArrayReader(ByteArray buffer) {
        if (buffer == null)
            throw new IllegalArgumentException("buffer cannot be null");
        this.buffer = buffer;
        this.position = 0;
        this.limit = buffer.getLength();
        this.isOpen = true;
    }

    @Override
    public void close() {
        isOpen = false;
    }

    /**
     * Return the total number of bytes read from the ByteArray using the read
     * methods.
     * 
     * @return total bytes read
     */
    public int getTotalBytesRead() {
        return position;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        ensureOpen();
        if (position >= limit) {
            return -1;
        }
        int bytesRead = buffer.read(position, cbuf, off, len);
        position += bytesRead;
        return bytesRead;
    }

    /**
     * Read a single character.
     * 
     * @return The character read, or -1 if the end of the stream has been
     *         reached
     * 
     * @exception IOException
     *                If an I/O error occurs
     */
    public int read() throws IOException {
        ensureOpen();
        if (position >= limit) {
            return -1;
        }
        return buffer.readAsChar(position++);
    }

    /** Check to make sure that the stream has not been closed */
    private void ensureOpen() throws IOException {
        if (!isOpen)
            throw new IOException("Stream closed");
    }

    /**
     * Tell whether this stream is ready to be read.
     * 
     * @return True if the next read() is guaranteed not to block for input
     * 
     * @exception IOException
     *                If the stream is closed
     */
    public boolean ready() throws IOException {
        ensureOpen();
        return true;
    }

    /**
     * Skips the specified number of characters in the stream. Returns the
     * number of characters that were skipped.
     * 
     * <p>
     * The <code>ns</code> parameter may be negative, even though the
     * <code>skip</code> method of the {@link Reader} superclass throws an
     * exception in this case. Negative values of <code>ns</code> cause the
     * stream to skip backwards. Negative return values indicate a skip
     * backwards. It is not possible to skip backwards past the beginning of the
     * string.
     * 
     * <p>
     * If the entire string has been read or skipped, then this method has no
     * effect and always returns 0.
     * 
     * @exception IOException
     *                If an I/O error occurs
     */
    public long skip(long ns) throws IOException {
        ensureOpen();
        if (position >= limit)
            return 0;
        // Bound skip by beginning and end of the source
        long n = Math.min(limit - position, ns);
        n = Math.max(-position, n);
        position += n;
        return n;
    }

    /**
     * Tell whether this stream supports the mark() operation, which it does.
     */
    public boolean markSupported() {
        return true;
    }

    /**
     * Mark the present position in the stream. Subsequent calls to reset() will
     * reposition the stream to this point.
     * 
     * @param readAheadLimit
     *            Limit on the number of characters that may be read while still
     *            preserving the mark. Because the stream's input comes from a
     *            ByteArray, there is no actual limit, so this argument must not
     *            be negative, but is otherwise ignored.
     * 
     * @exception IllegalArgumentException
     *                If readAheadLimit is < 0
     * @exception IOException
     *                If an I/O error occurs
     */
    public void mark(int readAheadLimit) throws IOException {
        if (readAheadLimit < 0) {
            throw new IllegalArgumentException("Read-ahead limit < 0");
        }
        ensureOpen();
        mark = position;
    }

    /**
     * Reset the stream to the most recent mark, or to the beginning of the
     * string if it has never been marked.
     * 
     * @exception IOException
     *                If an I/O error occurs
     */
    public void reset() throws IOException {
        ensureOpen();
        position = mark;
    }

}
