/* 
 *  Copyright 2009 The Last Check, LLC, All Rights Reserved
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0 
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.thelastcheck.commons.buffer;

import java.io.IOException;
import java.io.Writer;

import org.slf4j.LoggerFactory;


/**
 * @author Jerry Bowman
 * @copyright (c) 2009, The Last Check, All Rights Reserved.
 * @version $Date: 2010/11/15 17:42:05 $ $Revision: 1.3 $
 */
public class ByteArrayWriter extends Writer {
    private static final String CVS_ID       = "$Date: 2010/11/15 17:42:05 $ $Revision: 1.3 $";
    static {
        String className = ByteArrayWriter.class.getName();
        LoggerFactory.getLogger("version").info(className + " | " + CVS_ID);
    }

    private static int          INITIAL_SIZE = 100;

    private ByteArray           buffer;
    private int                 position;
    private int                 limit;
    private boolean             canResize;

    /**
     * 
     */
    public ByteArrayWriter() {
        this(INITIAL_SIZE);
    }

    /**
     * 
     */
    public ByteArrayWriter(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Negative buffer size");
        }
        this.buffer = new ByteArray(initialSize);
        this.position = 0;
        this.limit = initialSize;
        this.canResize = true;
    }

    /**
     * 
     */
    public ByteArrayWriter(ByteArray buffer) {
        this.buffer = buffer;
        this.position = 0;
        this.limit = buffer.getLength();
        this.canResize = false;
    }

    /**
     * Closing a <tt>ByteArrayWriter</tt> has no effect. The methods in this
     * class can be called after the stream has been closed without generating
     * an <tt>IOException</tt>.
     * 
     * @see java.io.Writer#close()
     */
    @Override
    public void close() throws IOException {
    }

    /**
     * Flushing a <tt>ByteArrayWriter</tt> has no effect.
     * 
     * @see java.io.Writer#flush()
     */
    @Override
    public void flush() throws IOException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.Writer#write(char[], int, int)
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        ensureCapacity(position + len);
        if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        buffer.write(cbuf, off, len, position);
        position += len;
    }

    public ByteArray getBuffer() {
        if (position == limit) {
            return buffer;
        }
        return buffer.slice(0, position);
    }

    /**
     * Return the total number of bytes written to the ByteArray using the write
     * methods.
     * 
     * @return total bytes written
     */
    public int getTotalBytesWritten() {
        return position;
    }

    /**
     * Write a single character.
     */
    public void write(int c) {
        ensureCapacity(position + 1);
        buffer.write((char) c, position++);
    }

    /**
     * Write a string.
     */
    public void write(String str) {
        ensureCapacity(position + str.length());
        buffer.write(str, position);
        position += str.length();
    }

    /**
     * Write a portion of a string.
     * 
     * @param str
     *            String to be written
     * @param off
     *            Offset from which to start writing characters
     * @param len
     *            Number of characters to write
     */
    public void write(String str, int off, int len) {
        ensureCapacity(position + len);
        buffer.write(str.substring(off, off + len), position);
        position += len;
    }

    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > limit) {
            if (canResize) {
                expandCapacity(minimumCapacity);
            } else {
                throw new BufferCapacityReachedException();
            }
        }
    }

    private void expandCapacity(int minimumCapacity) {
        int newCapacity = (limit + 1) * 2;
        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        } else if (minimumCapacity > newCapacity) {
            newCapacity = minimumCapacity;
        }
        ByteArray newBuffer = new ByteArray(newCapacity, buffer.getEncoding(), buffer.getOrder());
        newBuffer.write(buffer.getArray().value, 0);
        buffer = newBuffer;
        limit = newCapacity;
    }

}
