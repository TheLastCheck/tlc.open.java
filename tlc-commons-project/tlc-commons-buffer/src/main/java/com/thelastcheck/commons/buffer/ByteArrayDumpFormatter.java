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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.io.HexDump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jerry Bowman
 * @copyright (c) 2009, The Last Check, All Rights Reserved.
 * @version $Date: 2010/11/15 17:42:05 $ $Revision: 1.3 $
 */

public class ByteArrayDumpFormatter {
    private static final String CVS_ID    = "$Date: 2010/11/15 17:42:05 $ $Revision: 1.3 $";
    static {
        String className = ByteArrayDumpFormatter.class.getName();
        LoggerFactory.getLogger("version").info(className + " | " + CVS_ID);
    }

    private static Logger       logDump   = LoggerFactory.getLogger("DumpByteArray");

    private ByteArray           byteArray = null;
    private OutputStream        out       = null;

    private static final char   NEWLINE   = '\n';

    @SuppressWarnings("unused")
    private ByteArrayDumpFormatter() {
    }

    public ByteArrayDumpFormatter(ByteArray byteArray) {
        this.byteArray = byteArray;
    }

    public ByteArrayDumpFormatter(ByteArray byteArray, OutputStream out) {
        this.byteArray = byteArray;
        this.out = out;
    }

    /**
     * Will print a hexdump of this byte array output to log
     */
    public void dump() {
        dumpByteArray("", this.byteArray, out);
    }

    /**
     * Will print a hexdump of this byte array output to log
     * 
     * @param title
     */
    public void dump(String title) {
        dumpByteArray(title, this.byteArray, out);
    }

    /**
     * Will print a hexdump of this byte array output to defined output stream.
     * If the stream parameter is null, then the output will be to the log.
     * 
     * @param title
     * @param out
     */
    public void dump(String title, OutputStream out) {
        dumpByteArray(title, this.byteArray, out);
    }

    /**
     * Will print a hexdump output of a byte array to log
     * 
     * @param title
     * @param byteArray
     */
    public static void dumpByteArray(String title, ByteArray byteArray) {
        if (logDump.isDebugEnabled()) {
            dumpByteArray(title, byteArray, null);
        }
    }

    /**
     * Will print a hexdump output of a byte array to defined output stream. If
     * the stream parameter is null, then the output will be to the log.
     * 
     * @param title
     * @param byteArray
     * @param out
     */
    public static void dumpByteArray(String title, ByteArray byteArray,
            OutputStream out) {
        if (logDump.isDebugEnabled()) {
            byte[] bytes = null;
            /*
             * If a backing array is available and not a slice, then use it,
             * otherwise get a copy which is the actual array partition in use.
             */
            if (byteArray.hasArray()) {
                ByteArray.UnderlyingArray array = byteArray.getArray();
                if (array.offset == 0 && array.length == array.value.length) {
                    bytes = array.value;
                }
            }
            if (bytes == null) {
                bytes = byteArray.getBytes();
            }
            dumpByteArray(title, bytes, out);
        }
    }

    /**
     * Will print a hexdump output of a byte array to log
     * 
     * @param title
     * @param byteArray
     */
    public static void dumpByteArray(String title, byte[] byteArray) {
        if (logDump.isDebugEnabled()) {
            dumpByteArray(title, byteArray, null);
        }
    }

    /**
     * Will print a hexdump output of a byte array to defined output stream. If
     * the stream parameter is null, then the output will be to the log.
     * 
     * @param title
     * @param byteArray
     * @param out
     */
    private static void dumpByteArray(String title, byte[] byteArray, OutputStream out) {
        int estimatedSizeOfHexdump = byteArray.length * 75;
        ByteArrayOutputStream dumpOut = new ByteArrayOutputStream(
                estimatedSizeOfHexdump);
        try {
            HexDump.dump(byteArray, 0, dumpOut, 0);
            if (out == null) {
                logDump.debug(title + NEWLINE + dumpOut.toString());
            } else {
                out.write(new Date().toString().getBytes());
                out.write((byte) NEWLINE);
                out.write(title.getBytes());
                out.write((byte) NEWLINE);
                out.write(dumpOut.toByteArray());
                out.write((byte) NEWLINE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logDump.error("ArrayIndexOutOfBoundsException", e);
        } catch (IllegalArgumentException e) {
            logDump.error("IllegalArgumentException", e);
        } catch (IOException e) {
            logDump.error("IOException", e);
        }
    }

}
