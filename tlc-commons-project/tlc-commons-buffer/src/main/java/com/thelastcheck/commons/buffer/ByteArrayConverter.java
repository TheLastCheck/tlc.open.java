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

import java.io.UnsupportedEncodingException;

import org.slf4j.LoggerFactory;

/**
 * @author Jerry Bowman
 * @copyright (c) 2009, The Last Check, All Rights Reserved.
 * @version $Date: 2010/11/15 17:42:05 $ $Revision: 1.4 $
 */

public abstract class ByteArrayConverter {

    private byte[]              convertToTable;

    protected ByteArrayConverter() {
        try {
            convertToTable = loadConvertTable();
        } catch (UnsupportedEncodingException e) {
        }
    }

    protected abstract byte[] loadConvertTable() throws UnsupportedEncodingException;

    protected abstract String outputEncoding();

    public byte[] convert(byte[] bytes) {
        return convert(bytes, 0, bytes.length);
    }

    public byte[] convert(byte[] bytes, int offset, int length) {
        if ((offset < 0) || (length < 0) || ((offset + length) > bytes.length)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte[] bytesConverted = new byte[length];

        for (int i = offset, j = 0; j < length; i++, j++) {
            bytesConverted[j] = convert(bytes[i]);
        }
        return bytesConverted;
    }

    public byte convert(byte b) {
        int i = b & 0x00ff;
        return convertToTable[i];
    }

    public byte[] convert(char[] chars) {
        return convert(chars, 0, chars.length);
    }

    public byte[] convert(char[] chars, int offset, int length) {
        if ((offset < 0) || (length < 0) || ((offset + length) > chars.length)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte[] bytesConverted = new byte[length];

        for (int i = offset, j = 0; j < length; i++, j++) {
            bytesConverted[j] = convert(chars[i]);
        }
        return bytesConverted;
    }

    public byte convert(char c) {
        int i = ((byte) c) & 0x00ff;
        return convertToTable[i];
    }

    public ByteArray convert(ByteArray byteArray) {
        return convert(byteArray, 0, byteArray.getLength());
    }

    public ByteArray convert(ByteArray byteArray, int offset, int length) {
        byte[] bytesConverted;
        if (byteArray.hasArray()) {
            ByteArray.UnderlyingArray array = byteArray.getArray();
            bytesConverted = convert(array.value, offset + array.offset,
                    length);
        } else {
            bytesConverted = convert(byteArray.getBytes(), offset,
                    length);
        }
        return new ByteArray(bytesConverted, outputEncoding());
    }
}
