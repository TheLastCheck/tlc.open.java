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

public class EbcdicToAsciiByteArrayConverter extends ByteArrayConverter {

    private static final String EBCDIC              = ByteArray.EBCDIC_CHARSET_NAME;
    private static final String ASCII               = ByteArray.ASCII_CHARSET_NAME;

    private static byte[]       convertToAsciiTable = null;

    public EbcdicToAsciiByteArrayConverter() {
        super();
    }

    /**
     * @return a byte array containing the conversion for ASCII to EBCDIC
     * @throws UnsupportedEncodingException
     */
    protected byte[] loadConvertTable() throws UnsupportedEncodingException {
        synchronized (EbcdicToAsciiByteArrayConverter.class) {
            loadConvertTableSynch();
        }
        return convertToAsciiTable;
    }

    private void loadConvertTableSynch() throws UnsupportedEncodingException {
        if (convertToAsciiTable != null) {
            return;
        }

        Charset csEbcdic = Charset.forName(EBCDIC);

        byte[] bytesEbcdic = new byte[256];
        for (int i = 0; i < bytesEbcdic.length; i++) {
            bytesEbcdic[i] = (byte) i;
        }

        ByteBuffer bbEbcdic = ByteBuffer.wrap(bytesEbcdic);

        /*
         * This creates an array of bytes that can be indexed using an EBCDIC
         * value to get the corresponding ASCII value. For example:
         * 
         *    byte ebcdic = (byte) '*'; 
         *    byte ascii = convertToAsciiTable[ebcdic & 0x00ff]; 
         */
        CharBuffer cbAscii = csEbcdic.decode(bbEbcdic);
        /*
         * When getting back the string of data from the toString.getBytes on
         * the entire CharBuffer, would sometimes get back a larger string and
         * the table conversion would not be correct. By changing this to deal
         * with the build a character at a time, the proper 256 bytes array is
         * built. Don't know why this was happening, but it worked on a MAC
         * version of the JVM, and was broken on a Linux version. This could be
         * something that was fixed in another version of the JVM. However, this
         * approach should work for all environments.
         */
        char[] caAscii = new char[256];
        cbAscii.get(caAscii);
        convertToAsciiTable = new byte[256];
        for (int i = 0; i < 256; i++) {
            byte b = (byte) caAscii[i];
            // byte values > 7f would appear negative and are not valid ASCII
            // characters
            if (b < 0) {
                b = '?';
            }
            convertToAsciiTable[i] = b;
        }
    }

    @Override
    protected String outputEncoding() {
        return ASCII;
    }
}
