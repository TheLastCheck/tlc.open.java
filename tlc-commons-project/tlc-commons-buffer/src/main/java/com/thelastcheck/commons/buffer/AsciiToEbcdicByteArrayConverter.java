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
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.slf4j.LoggerFactory;


/**
 * @author Jerry Bowman
 * @copyright (c) 2009, The Last Check, All Rights Reserved.
 * @cvs $Date: 2010/11/15 17:42:05 $ $Revision: 1.3 $
 */

public class AsciiToEbcdicByteArrayConverter extends ByteArrayConverter {

    private static final String EBCDIC               = ByteArray.EBCDIC_CHARSET_NAME;

    private static byte[]       convertToEbcdicTable = null;

    public AsciiToEbcdicByteArrayConverter() {
        super();
    }

    /**
     * @return a byte array containing the conversion for ASCII to EBCDIC
     * @throws UnsupportedEncodingException
     */
    protected byte[] loadConvertTable() throws UnsupportedEncodingException {
        synchronized (AsciiToEbcdicByteArrayConverter.class) {
            loadConvertTableSynch();
        }
        return convertToEbcdicTable;
    }

    private void loadConvertTableSynch() {
        if (convertToEbcdicTable != null) {
            return;
        }

        Charset csEbcdic = Charset.forName(EBCDIC);

        char[] charsAscii = new char[256];
        for (int i = 0; i < charsAscii.length; i++) {
            charsAscii[i] = (char) i;
        }

        CharBuffer cbAscii = CharBuffer.wrap(charsAscii);

        /*
         * This create an array of bytes that can be indexed using an ASCII
         * value to get the corresponding EBCDIC value. For example:
         * 
         * byte ascii = (byte) '*'; 
         * byte ebcdic = convertToEbcdicTable[ascii & 0x00ff];
         */
        ByteBuffer bbEbcdic = csEbcdic.encode(cbAscii);
        convertToEbcdicTable = new byte[256];
        bbEbcdic.get(convertToEbcdicTable);
        for (int i = 128; i < convertToEbcdicTable.length; i++) {
            convertToEbcdicTable[i] = 0x6f;
        }
    }

    @Override
    protected String outputEncoding() {
        return EBCDIC;
    }

}
