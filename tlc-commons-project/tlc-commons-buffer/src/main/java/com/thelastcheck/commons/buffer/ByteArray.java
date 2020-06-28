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

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ByteArray implements Serializable, Cloneable {
    private static final long serialVersionUID = -399296889418381603L;

    private ByteBuffer value;
    private String encoding;

    public static final String ASCII_CHARSET_NAME = "US-ASCII";
    public static final String EBCDIC_CHARSET_NAME = "IBM1047";
    private static final String SPACE = " ";

    private static final Map<String, Byte> encodeMap;
    private static final Pattern psRegexPattern;

    private static final ByteArrayConverter bacAsciiToEbcdic = new AsciiToEbcdicByteArrayConverter();
    private static final ByteArrayConverter bacEbcdicToAscii = new EbcdicToAsciiByteArrayConverter();

    static {
        encodeMap = Collections.synchronizedMap(new HashMap<>());
        byte filler;
        try {
            filler = SPACE.getBytes(ASCII_CHARSET_NAME)[0];
            encodeMap.put(ASCII_CHARSET_NAME, filler);
            filler = SPACE.getBytes(EBCDIC_CHARSET_NAME)[0];
            encodeMap.put(EBCDIC_CHARSET_NAME, filler);
        } catch (java.io.UnsupportedEncodingException ignored) {
        }

        String regex = "(\\A[\\x2D\\x2B]?\\d*\\z)|(\\A\\d*[\\x2D\\x2B]?\\z)";
        psRegexPattern = Pattern.compile(regex);
    }

    /**
     * Create a new ByteArray of the specified size with default encoding of
     * ASCII and byte order of BIG_ENDIAN.
     *
     * @param size is the initial size of these ByteArray
     */
    public ByteArray(int size) {
        this(size, null, null);
    }

    /**
     * Create a new ByteArray of the specified size with specified encoding and
     * byte order of BIG_ENDIAN.
     *
     * @param size     is the initial size of these ByteArray
     * @param encoding is the encoding used for the ByteArray
     */
    public ByteArray(int size, String encoding) {
        this(new byte[size], encoding, null);
    }

    /**
     * Create a new ByteArray of the specified size with default encoding of
     * ASCII and specified byte order.
     *
     * @param size  is the initial size of these ByteArray
     * @param order is the byte order of the primitives contained in the array
     */
    public ByteArray(int size, ByteOrder order) {
        this(new byte[size], null, order);
    }

    /**
     * Create a new ByteArray of the specified size with specified encoding and
     * specified byte order.
     *
     * @param size     is the initial size of these ByteArray
     * @param encoding is the encoding used for the ByteArray
     * @param order    is the byte order of the primitives contained in the array
     */
    public ByteArray(int size, String encoding, ByteOrder order) {
        this(new byte[size], encoding, order);
    }

    /**
     * Create a new ByteArray backed by the specified array with default
     * encoding of ASCII and byte order of BIG_ENDIAN.
     *
     * @param array the byte array the object wraps
     */
    public ByteArray(byte[] array) {
        this(array, null, null);
    }

    /**
     * Create a new ByteArray backed by the specified array with default
     * encoding of ASCII and the specified byte order.
     *
     * @param array the byte array the object wraps
     * @param order is the byte order of the primitives contained in the array
     */
    public ByteArray(byte[] array, ByteOrder order) {
        this(array, null, order);
    }

    /**
     * Create a new ByteArray backed by the specified array with the specified
     * encoding and byte order of BIG_ENDIAN.
     *
     * @param array    the byte array the object wraps
     * @param encoding is the encoding used for the ByteArray
     */
    public ByteArray(byte[] array, String encoding) {
        this(array, encoding, null);
    }

    /**
     * Create a new ByteArray backed by the specified array with the specified
     * encoding and byte order.
     *
     * @param array    the byte array the object wraps
     * @param encoding is the encoding used for the ByteArray
     * @param order    is the byte order of the primitives contained in the array
     */
    public ByteArray(byte[] array, String encoding, ByteOrder order) {
        if (array == null) {
            throw new IllegalNullArgumentException();
        }
        this.value = ByteBuffer.wrap(array);
        if (encoding == null) {
            this.encoding = ASCII_CHARSET_NAME;
        } else {
            this.encoding = encoding;
        }
        if (order == null) {
            this.value.order(ByteOrder.BIG_ENDIAN);
        } else {
            this.value.order(order);
        }
    }

    /**
     * Creates a new ByteArray object that is backed by the same byte[] array
     * value as the original. Calling methods on either the original or this new
     * instance will produce exactly the same results to the same underlying
     * array of bytes.
     * <p>
     * Note this is different from clone() which create a complete new copy of
     * the object including a new underlying byte[] array.
     *
     * @param byteArray is the ByteArray whose backing array is used for this new object
     */
    public ByteArray(ByteArray byteArray) {
        if (byteArray == null) {
            throw new IllegalNullArgumentException();
        }
        // use a new copy of the ByteBuffer to manage the underlying bytes.
        value = byteArray.value.duplicate();
        // propagate order since duplicate does not do this
        value.order(byteArray.value.order());
        // ensure limit is set to capacity
        value.clear();
        encoding = byteArray.encoding;
    }

    /**
     * Create a new ByteArray backed by the specified ByteBuffer with the
     * default encoding of ASCII and byte order of specified ByteBuffer.
     *
     * @param buffer is the ByteBuffer whose backing array is used for this new object
     */
    public ByteArray(ByteBuffer buffer) {
        this(buffer, null);
    }

    /**
     * Create a new ByteArray backed by the specified ByteBuffer with the
     * specified encoding and byte order of specified ByteBuffer.
     *
     * @param buffer   is the ByteBuffer whose backing array is used for this new object
     * @param encoding is the encoding used for the ByteArray
     */
    public ByteArray(ByteBuffer buffer, String encoding) {
        if (buffer == null) {
            throw new IllegalNullArgumentException();
        }
        value = buffer.duplicate();
        // propagate order since duplicate does not do this
        value.order(buffer.order());
        // ensure limit is set to capacity.
        value.clear();

        if (encoding == null) {
            this.encoding = ASCII_CHARSET_NAME;
        } else {
            this.encoding = encoding;
        }
    }

    public Object clone() throws CloneNotSupportedException {
        if (value.isDirect()) {
            throw new CloneNotSupportedException("Can't clone a direct buffer");
        }
        ByteArray object = (ByteArray) super.clone();

        value.clear();
        byte[] bytes = new byte[value.remaining()];
        value.get(bytes);

        object.value = ByteBuffer.wrap(bytes);
        object.value.order(value.order());

        return object;
    }

    /**
     * Creates a new ByteArray backed by the same buffer, but with a different
     * slice or view of the data based on the specified displacement and length
     * into this ByteArray. Changes to this ByteArray's content will be visible
     * in the new array, and vice versa; the two arrays' position, limit, and mark
     * values will be independent.
     *
     * @param displacement is the starting displacement in this buffer to create the splice
     * @param length       is the length of the slice
     * @return A new ByteArray backed by the same data but with a sliced view.
     */
    public ByteArray slice(int displacement, int length) {
        value.position(displacement).limit(displacement + length);
        ByteArray newByteArray = new ByteArray(value.slice(), encoding);
        // Order is not propagated on a ByteBuffer slice, so we need to
        // explicitly set it.
        newByteArray.setOrder(this.getOrder());
        // reset limit to capacity.
        value.clear();
        return newByteArray;
    }

    /**
     * Creates a new ByteArray backed by the same buffer. Changes to this ByteArray's
     * content will be visible in the new array, and vice versa; the two arrays'
     * position, limit, and mark values will be independent.
     *
     * @return A new ByteArray backed by the same data.
     */
    public ByteArray duplicate() {
        return new ByteArray(value, encoding);
    }

    /**
     * Returns a boolean value indicating if all of the bits in the mask are
     * currently set to 1. If any of the bits in the mask are not set in the
     * specified byte, false is returned.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param mask         A bit mask to test
     * @return A boolean indicating if the mask bits are set
     */
    public boolean testBit(int displacement, byte mask) {
        return (value.get(displacement) & mask) == mask;
    }

    /**
     * Sets on all of the bits in the mask.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param mask         A bit mask to set
     * @return this ByteArray
     */
    public ByteArray setBit(int displacement, byte mask) {
        byte b = value.get(displacement);
        b |= mask;
        value.put(displacement, b);
        return this;
    }

    /**
     * Sets on all of the bits in the mask is the boolean value is true. Clears
     * all of the bits otherwise.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param mask         A bit mask to set
     * @param b            A boolean indicate if the mask bits should be set or cleared.
     * @return this ByteArray
     */
    public ByteArray setBit(int displacement, byte mask, boolean b) {
        if (b) {
            setBit(displacement, mask);
        } else {
            clearBit(displacement, mask);
        }
        return this;
    }

    /**
     * Clears all of the bits in the mask.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param mask         A bit mask to set
     * @return this ByteArray
     */
    public ByteArray clearBit(int displacement, byte mask) {
        byte b = value.get(displacement);
        b &= (255 - (mask & 0xff));
        value.put(displacement, b);
        return this;
    }

    /**
     * Returns a new byte array of size <i>length</i> containing a copy of the
     * contents of this byte array at the specified displacement.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return A byte array containing the requested bytes.
     */
    public byte[] read(int displacement, int length) {
        byte[] tempByteArray = new byte[length];
        value.position(displacement);
        value.get(tempByteArray);
        return tempByteArray;
    }

    /**
     * Fills a byte array from the ByteArray starting at the specified
     * displacement. An invocation of this method of the form
     * <tt>ba.read(ba, disp)</tt> behaves in exactly the same was as the
     * invocation
     *
     * <pre>
     * ba.read(ba, 0, ba.length, disp);
     * </pre>
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param bytes        The byte array to be filled
     * @return An int containing the number of bytes actually stored. This could
     * be less than the size of the byte array if the available bytes in
     * the byte array is less than is available from the current
     * displacement.
     * @see ByteArray#read(int, byte[], int, int)
     */
    public int read(int displacement, byte[] bytes) {
        return read(displacement, bytes, 0, bytes.length);
    }

    /**
     * Fills a byte array at the specified offset in the byte array for the
     * length specified from the ByteArray starting at the specified
     * displacement.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param bytes        The byte array to be filled
     * @param offset       The offset in the byte array, first byte is 0.
     * @param length       The length of the bytes to be copied
     * @return An int containing the number of bytes actually stored. This could
     * be less than the requested value if the available bytes in the
     * byte array is less than is available from the current
     * displacement.
     */
    public int read(int displacement, byte[] bytes, int offset, int length) {
        int i;
        value.position(displacement);
        for (i = 0; i < length && value.remaining() > 0; i++) {
            bytes[offset + i] = value.get();
        }
        return i;
    }

    /**
     * Returns a byte derived from the contents of the byte array at the
     * specified displacement.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @return A byte derived from requested displacement.
     */
    public byte readAsByte(int displacement) {
        return value.get(displacement);
    }

    /**
     * Returns a new ByteArray of size <i>length</i> containing a copy of the
     * contents of this byte array at the specified displacement. The new
     * ByteArray will have the same encoding as this one.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return A byte array containing the requested bytes.
     */
    public ByteArray readAsByteArray(int displacement, int length) {
        byte[] tempByteArray = read(displacement, length);
        return new ByteArray(tempByteArray, encoding);
    }

    /**
     * Returns a new char derived from the contents of the byte array at the
     * specified displacement.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @return A char derived from requested byte.
     */
    public char readAsChar(int displacement) {
        char ch;
        if (encoding.equals(EBCDIC_CHARSET_NAME)) {
            ch = (char) bacEbcdicToAscii.convert(value.get(displacement));
        } else if (encoding.equals(ASCII_CHARSET_NAME)) {
            ch = (char) value.get(displacement);
        } else {
            throw new UnsupportedEncodingException();
        }
        return ch;
    }

    /**
     * Returns a new char array of the length specified containing a copy of the
     * contents of the byte array at the specified displacement.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return A char array containing the requested bytes.
     */
    public char[] readAsCharArray(int displacement, int length) {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = readAsChar(displacement + i);
        }
        return chars;
    }

    /**
     * Fills a char array from the ByteArray starting at the specified
     * displacement. An invocation of this method of the form
     * <tt>ba.read(displacement, chars)</tt> behaves in exactly the same was as
     * the invocation
     *
     * <pre>
     * ba.read(displacement, chars, 0, chars.length);
     * </pre>
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param chars        The char array to be filled
     * @return An int containing the number of chars actually stored. This could
     * be less than the size of the char array if the available bytes in
     * the byte array is less than is available from the current
     * displacement.
     * @see ByteArray#read(int, char[], int, int)
     */
    public int read(int displacement, char[] chars) {
        return read(displacement, chars, 0, chars.length);
    }

    /**
     * Fills a char array at the specified offset in the char array for the
     * length specified from the ByteArray starting at the specified
     * displacement.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param chars        The char array to be filled
     * @param offset       The offset in the char array, first byte is 0.
     * @param length       The length of the bytes to be copied
     * @return An int containing the number of chars actually stored. This could
     * be less than the requested value if the available bytes in the
     * byte array is less than is available from the current
     * displacement.
     */
    public int read(int displacement, char[] chars, int offset, int length) {
        value.position(displacement);
        if (value.remaining() == 0) {
            return 0;
        }
        int count = Math.min(length, value.remaining());
        if (encoding.equals(EBCDIC_CHARSET_NAME)) {
            for (int i = 0; i < count; i++) {
                chars[offset + i] = (char) bacEbcdicToAscii.convert(value.get());
            }
        } else if (encoding.equals(ASCII_CHARSET_NAME)) {
            for (int i = 0; i < count; i++) {
                chars[offset + i] = (char) value.get();
            }
        } else {
            throw new UnsupportedEncodingException();
        }
        return count;
    }

    /**
     * Returns a new String containing a copy of the contents of the byte array.
     *
     * @return A String derived from the requested bytes.
     */
    public String readAsString() {
        value.clear();
        return readAsString(0, value.remaining());
    }

    /**
     * Returns a new String containing a copy of the contents of the byte array
     * at the specified displacement to the end of the byte array.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @return A String derived from the requested bytes.
     */
    public String readAsString(int displacement) {
        value.clear();
        value.position(displacement);
        return readAsString(displacement, value.remaining());
    }

    /**
     * Returns a new String containing a copy of the contents of the byte array
     * at the specified displacement for the specified length.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return A String derived from the requested bytes.
     */
    public String readAsString(int displacement, int length) {
        return readAsString(displacement, length, false);
    }

    /**
     * Returns a new String derived from the contents of the byte array at the
     * specified displacement for the specified length.
     * <p>
     * If <i>nts</i> (null terminated string) is <code>true</code>, then a check
     * is made for a null-byte (binary zeroes). If a null byte is found in the
     * buffer area being processed, all data after the null-byte (including the
     * null-byte) will be ignored and only the text data preceding this
     * null-byte will be stored in the newly created String.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @param nts          The flag to indicate to use a null byte as the data terminator
     * @return A String derived from the requested bytes.
     */
    public String readAsString(int displacement, int length, boolean nts) {
        /*
         * default string length to requested length from buffer
         */
        int stringLen = length;
        /*
         * if nts, look for first null byte (terminator for C strings - NTS). j
         * will be count of bytes before null
         */
        if (nts) {
            int i, j;
            for (i = displacement, j = 0; j < length; i++, j++) {
                if (value.get(i) == (byte) 0) {
                    stringLen = j;
                    break;
                }
            }
        }

        char[] chars = new char[stringLen];
        read(displacement, chars);

        return new String(chars);
    }

    /**
     * Returns a new float derived from the contents of the byte array at the
     * specified displacement. IEEE Standard 754 floating point format is
     * assumed for the byte array data. The number of bytes used to retrieve the
     * float value from the byte array is 8.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @return A float derived from the requested bytes.
     */
    public float readAsFloat(int displacement) {
        return value.getFloat(displacement);
    }

    /**
     * Returns a new double derived from the contents of the byte array at the
     * specified displacement. IEEE Standard 754 floating point format is
     * assumed for the byte array data. The number of bytes used to retrieve the
     * double value from the byte array is 8.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @return A double derived from the requested bytes.
     */
    public double readAsDouble(int displacement) {
        return value.getDouble(displacement);
    }

    /**
     * Returns a new short derived from the contents of the byte array at the
     * specified displacement. The data bytes are assumed to be in big-endian
     * format. The number of bytes used to retrieve the short value from the
     * byte array is 2.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @return A short derived from requested bytes.
     */
    public short readAsShort(int displacement) {
        return value.getShort(displacement);
    }

    /**
     * Returns a new int derived from the contents of the byte array at the
     * specified displacement. The data bytes are assumed to be in big-endian
     * format. The number of bytes used to retrieve the int value from the byte
     * array is 4.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @return An int derived from requested bytes.
     */
    public int readAsInt(int displacement) {
        return value.getInt(displacement);
    }

    /**
     * Returns a new long derived from the contents of the byte array at the
     * specified displacement. The data bytes are assumed to be in big-endian
     * format. The number of bytes used to retrieve the long value from the byte
     * array is 8.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @return An long derived from requested bytes.
     */
    public long readAsLong(int displacement) {
        return value.getLong(displacement);
    }

    /**
     * Returns a new String derived from the contents of the byte array at the
     * specified displacement for the specified length. The bytes are read from
     * a packed-no-sign format. This treats each nybble in the array as either a
     * digit from 0-9, or interprets 'A'-'F' as follows: 'A' means space, 'D'
     * means dash, 'E' or 'F' means unknown or digit error. 'B' and 'C' are left
     * unchanged.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return A string containing the field from the buffer converted from a
     * packed no sign field.
     */
    public String readPns(int displacement, int length) {
        return readPns(displacement, length, false);
    }

    /**
     * Returns a new String derived from the contents of the byte array at the
     * specified displacement for the specified length. The bytes are read from
     * a packed-no-sign format. This treats each nybble in the array as either a
     * digit from 0-9, or interprets 'A'-'F' as follows: 'A' means space, 'D'
     * means dash, 'E' or 'F' means unknown or digit error. If the convertAtoF
     * flag is true, then the values are converted to the actual character,
     * i.e., 'A' to space, 'D' to dash, 'E' or 'F' to asterisk, and 'B' and 'C'
     * are left unchanged.
     *
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @param convertAtoF  The flag to indicate the A-F character values should be
     *                     converted.
     * @return A string containing the field from the buffer converted from a
     * packed no sign field.
     */
    public String readPns(int displacement, int length, boolean convertAtoF) {
        StringBuilder sb = new StringBuilder(length * 2);
        byte leftNybble, rightNybble;
        char leftChar, rightChar;
        for (int i = 0; i < length; i++) {
            leftNybble = (byte) ((value.get(displacement + i) & 0xf0) >> 4);
            rightNybble = (byte) (value.get(displacement + i) & 0x0f);
            if (leftNybble < 0x0a) {
                leftChar = (char) (leftNybble + 48);
            } else {
                if (convertAtoF) {
                    leftChar = convertAtoF(leftNybble);
                } else {
                    leftChar = (char) (leftNybble + 65 - 10);
                }
            }
            if (rightNybble < 0x0a) {
                rightChar = (char) (rightNybble + 48);
            } else {
                if (convertAtoF) {
                    rightChar = convertAtoF(rightNybble);
                } else {
                    rightChar = (char) (rightNybble + 65 - 10);
                }
            }
            sb.append(leftChar);
            sb.append(rightChar);
        }

        return sb.toString();
    }

    private char convertAtoF(byte nybble) {
        byte work = (byte) (nybble - 10);
        char convertedChar;
        switch (work) {
            case 0:
                convertedChar = ' ';
                break;
            case 3:
                convertedChar = '-';
                break;
            case 4:
                convertedChar = '*';
                break;
            case 5:
                convertedChar = '*';
                break;
            default:
                convertedChar = (char) (work + 65);
        }
        return convertedChar;
    }

    /**
     * Places the filler space byte in every position in the byte array.
     *
     * @return the current ByteArray
     */
    public ByteArray fill() {
        value.clear();
        return fill(getSpaceFiller(encoding), 0, value.remaining());
    }

    /**
     * Places the byte in every position in the byte array.
     *
     * @param filler The byte to be written to the byte array
     * @return the current ByteArray
     */
    public ByteArray fill(byte filler) {
        value.clear();
        return fill(filler, 0, value.remaining());
    }

    /**
     * Places the byte in every position in the byte array at the displacement
     * specified for the length specified.
     *
     * @param filler       The byte to be written to the byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return the current ByteArray
     */
    public ByteArray fill(byte filler, int displacement, int length) {
        for (int i = displacement; i < (displacement + length); i++) {
            value.put(i, filler);
        }
        return this;
    }

    /**
     * Stores a char array into the ByteArray starting at the specified
     * displacement. An invocation of this method of the form
     * <tt>ba.write(ca, disp)</tt> behaves in exactly the same was as the
     * invocation
     *
     * <pre>
     * ba.write(ca, 0, ca.length, disp);
     * </pre>
     *
     * @param chars        The char array to be stored
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     * @see ByteArray#read(int, char[], int, int)
     */
    public ByteArray write(char[] chars, int displacement) {
        return write(chars, 0, chars.length, displacement);
    }

    /**
     * Places the contents of the from char array into the byte array at the
     * displacement specified for the length specified.
     *
     * @param chars        The character array containing the data to be written to the
     *                     byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return the current ByteArray
     */
    public ByteArray write(char[] chars, int offset, int length, int displacement) {
        if (encoding.equals(EBCDIC_CHARSET_NAME)) {
            byte[] bytes = bacAsciiToEbcdic.convert(chars, offset, length);
            value.position(displacement);
            value.put(bytes);
        } else if (encoding.equals(ASCII_CHARSET_NAME)) {
            value.position(displacement);
            for (int i = 0; i < length; i++) {
                value.put((byte) chars[offset + i]);
            }
        } else {
            throw new UnsupportedEncodingException();
        }
        return this;
    }

    /**
     * Places the contents of the from ByteArray into the byte array at the
     * displacement specified for the length specified.
     *
     * @param byteArray    The ByteArray containing the data to be written to this byte
     *                     array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return the current ByteArray
     */
    public ByteArray write(ByteArray byteArray, int displacement, int length)
            throws ArrayIndexOutOfBoundsException {
        value.position(displacement);
        for (int i = 0; i < length; i++) {
            value.put(byteArray.value.get(i));
        }
        return this;
        // return write(fromByteArray.value, displacement, length);
    }

    /**
     * Places the contents of the fromteh ByteArray into the byte array at the
     * displacement specified for the size of the ByteArray specified.
     *
     * @param byteArray    The ByteArray containing the data to be written to this byte
     *                     array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(ByteArray byteArray, int displacement) {
        byteArray.value.clear();
        int length = byteArray.value.remaining();
        return write(byteArray, displacement, length);
    }

    /**
     * Places the contents of the from byte array into the byte array at the
     * displacement specified for the length of the from byte array.
     *
     * @param bytes        The byte array containing the data to be written to this byte
     *                     array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(byte[] bytes, int displacement) {
        return write(bytes, 0, bytes.length, displacement);
    }

    /**
     * Places the contents of the from byte array into the byte array at the
     * displacement specified for the length specified.
     *
     * @param bytes        The byte array containing the data to be written to this byte
     *                     array
     * @param offset       The offset into the byte array as the start place from which
     *                     to copy the data.
     * @param length       The length of the data to be copied.
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(byte[] bytes, int offset, int length, int displacement)
            throws ArrayIndexOutOfBoundsException {
        value.position(displacement);
        for (int i = 0; i < length; i++) {
            value.put(bytes[offset + i]);
        }
        return this;
    }

    /**
     * Places the contents of the String into the byte array at the displacement
     * specified for the length specified using the encoding scheme for this
     * byte array. Any space remaining in the byte array (String.length &lt;
     * length) will be filled with spaces using the ASCII encoding scheme.
     *
     * @param s            The String value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     * @see ByteArray#write(java.lang.String, int, int, boolean)
     */
    public ByteArray write(String s, int displacement) {
        return write(s, displacement, s.length(), false);
    }

    /**
     * Places the contents of the String into the byte array at the displacement
     * specified for the length specified using the encoding scheme for this
     * array. Any space remaining in the byte array (String.length &lt; length)
     * will be filled with spaces using the ASCII encoding scheme.
     *
     * @param s            The String value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return the current ByteArray
     * @see ByteArray#write(String, int, int, boolean)
     */
    public ByteArray write(String s, int displacement, int length) {
        return write(s, displacement, length, false);
    }

    /**
     * Places the contents of the String into the byte array at the displacement
     * specified for the length specified using the specified encoding scheme.
     * If nts (null terminated string) is true, then any space remaining in the
     * byte array for the length specified (String.length &lt; length) will be
     * filled with nulls (binary zeros). If nts is false, then any extra bytes
     * will be filled with spaces using the specified encoding scheme.
     *
     * @param s            The String value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @param nts          The flag to indicate to use a null byte as the data terminator
     * @return the current ByteArray
     * @see ByteArray#write(String, int, int)
     * @see ByteArray#write(String, int, int, boolean)
     */
    public ByteArray write(String s, int displacement, int length, boolean nts) {
        int dataLen = s.length();
        if (dataLen > length) {
            throw new IllegalArgumentException("Length of String exceeds the available length specified.");
        }
        char[] chars = s.toCharArray();
        write(chars, displacement);
        if (dataLen < length) {
            /*
             * Determine what to use as filler for short strings. If the nts
             * (null terminated string) value is true, then fill with nulls
             * (x'00'). Otherwise fill with a space using the encoding scheme
             * specified. For performance reasons, the space filler is stored in
             * a hash map by encoding for reuse.
             */
            byte filler = 0x00;
            if (!nts) {
                filler = getSpaceFiller(encoding);
            }
            value.position(displacement + dataLen);
            while (dataLen < length) {
                value.put(filler);
                dataLen++;
            }

        }
        return this;
    }

    /**
     * Places the value of the short into the byte array at the displacement
     * specified. The short is stored as a big-endian value in the following 2
     * bytes from the displacement.
     *
     * @param s            The short value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(short s, int displacement) {
        value.putShort(displacement, s);
        return this;
    }

    /**
     * Places the value of the char into the byte array at the displacement
     * specified.
     *
     * @param c            The char value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(char c, int displacement) {
        if (encoding.equals(EBCDIC_CHARSET_NAME)) {
            value.put(displacement, bacAsciiToEbcdic.convert(c));
        } else if (encoding.equals(ASCII_CHARSET_NAME)) {
            value.put(displacement, (byte) c);
        } else {
            throw new UnsupportedEncodingException();
        }
        return this;
    }

    /**
     * Places the value of the byte into the byte array at the displacement
     * specified.
     *
     * @param b            The byte value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(byte b, int displacement) {
        value.put(displacement, b);
        return this;
    }

    /**
     * Places the value of the int into the byte array at the displacement
     * specified. The int is stored as a big-endian value in the following 4
     * bytes from the displacement.
     *
     * @param i            The int value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(int i, int displacement) {
        value.putInt(displacement, i);
        return this;
    }

    /**
     * Places the value of the long into the byte array at the displacement
     * specified. The long is stored as a big-endian value in the following 8
     * bytes from the displacement.
     *
     * @param i            The long value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(long i, int displacement) {
        value.putLong(displacement, i);
        return this;
    }

    /**
     * Places the value of the float into the byte array at the displacement
     * specified. The float is stored as an IEEE Standard 754 format value in
     * the following 4 bytes from the displacement.
     *
     * @param f            The float value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(float f, int displacement) {
        value.putFloat(displacement, f);
        return this;
    }

    /**
     * Places the value of the double into the byte array at the displacement
     * specified. The double is stored as an IEEE Standard 754 format value in
     * the following 8 bytes from the displacement.
     *
     * @param d            The double value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @return the current ByteArray
     */
    public ByteArray write(double d, int displacement) {
        value.putDouble(displacement, d);
        return this;
    }

    /**
     * Validate the input string first (it allows only numbers and + or -
     * character) and then call writeByteArrayAsPns. It also adds a sign at the
     * right (C for +, D for -, F for no sign).
     *
     * @param s            The String value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return the current ByteArray
     */
    public ByteArray writeAsPs(String s, int displacement, int length) {
        s = s.trim();
        // we will also allow that the value has a sign ("-" or "+") at the end,
        // e.g. "1234-"
        Matcher matcher = psRegexPattern.matcher(s);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Value:" + s
                    + " has illegal character values for a packed field.");
        }
        char sign = 'F';
        // look for the sign in the value
        // check if length of string is zero
        if (s.length() > 0) {
            if (s.charAt(s.length() - 1) == '+'
                    || s.charAt(0) == '+') {
                sign = 'C';
                s = s.replaceAll("\\+", "");
            } else if (s.charAt(s.length() - 1) == '-'
                    || s.charAt(0) == '-') {
                sign = 'D';
                s = s.replaceAll("-", "");
            }
        }
        s += sign;
        writeAsPns(s, displacement, length, '0');
        return this;
    }

    /**
     * Places the contents of the String into the byte array at the displacement
     * specified for the length specified as a packed-no-sign field value.
     * Spaces will be converted to 'A', dashed to 'D', and asterisks to 'F'. If
     * the String value will not fill the whole length of the area, it will be
     * right-justified and space ('A') filled on the left.
     *
     * @param s            The String value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return the current ByteArray
     */
    public ByteArray writeAsPns(String s, int displacement, int length) {
        return writeAsPns(s, displacement, length, ' ');
    }

    /**
     * Places the contents of the String into the byte array at the displacement
     * specified for the length specified as a packed-no-sign field value.
     * Spaces will be converted to 'A', dashed to 'D', and asterisks to 'F'. If
     * the String value will not fill the whole length of the area, it will be
     * right-justified and space ('A') filled on the left.
     *
     * @param s            The String value to place in this byte array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @param filler       Character that is used to fill in the input value if it is too
     *                     short
     * @return the current ByteArray
     */
    public ByteArray writeAsPns(String s, int displacement, int length,
                                char filler) {
        String work = s;
        int numNybbles = length * 2;
        if (work.length() > numNybbles) {
            throw new IllegalArgumentException(
                    "Number of digits for PNS field exceeds available buffer. Value:"
                            + s + "\tField Length:" + length);
        }
        if (work.length() < numNybbles) {
            int extraNybbles = numNybbles - work.length();
            StringBuilder sb = new StringBuilder(numNybbles);
            for (int i = 0; i < extraNybbles; i++) {
                sb.append(filler);
            }
            sb.append(work);
            work = sb.toString();
        }
        String normalValue = work.toUpperCase();

        byte leftNybble, rightNybble;
        char leftChar, rightChar;
        int i = 0;
        for (int j = 0; j < length; j++) {
            leftChar = normalValue.charAt(i++);
            rightChar = normalValue.charAt(i++);
            leftNybble = convertCharToNybble(leftChar);
            rightNybble = convertCharToNybble(rightChar);
            value.put(displacement + j, (byte) (leftNybble << 4 | rightNybble));
        }
        return this;
    }

    private static byte convertCharToNybble(char fromChar) {
        char ch = fromChar;
        byte nybble;
        if (ch == ' ') {
            ch = 'A';
        }
        if (ch == '-') {
            ch = 'D';
        }
        if (ch == '*') {
            ch = 'F';
        }
        if ((ch < '0' || ch > '9') && (ch < 'A' || ch > 'F')) {
            throw new IllegalArgumentException(
                    "Value values for PNS field are 0-9, A-F");
        }
        if ((ch < 'A')) {
            nybble = (byte) ((ch - 48));
        } else {
            nybble = (byte) ((ch - 65 + 10));
        }
        return nybble;
    }

    /**
     * Returns a space value for the encoding specified.
     *
     * @param encoding The encoding to used for the space filler value
     * @return A space value in the encoding specified.
     */
    public static byte getSpaceFiller(String encoding) {
        encoding = encoding.toUpperCase();
        byte filler;
        Byte b = encodeMap.get(encoding);
        if (b != null) {
            filler = b;
        } else {
            try {
                filler = SPACE.getBytes(encoding)[0];
            } catch (java.io.UnsupportedEncodingException e1) {
                filler = SPACE.getBytes()[0];
            }
            encodeMap.put(encoding, filler);
        }
        return filler;
    }

    /**
     * Converts String object to byte array. Assumes ASCII as encoding.
     *
     * @param string to be wrapped in a new ByteArray
     * @return the converted byte array
     */
    public static ByteArray valueOf(String string) {
        ByteArray ba = new ByteArray(string.length());
        ba.write(string, 0);
        return ba;
    }

    /**
     * Compares the value of all the corresponding bytes between this byte array
     * and <i>another</i>. If the length of the arrays are the same and each
     * corresponding byte value is the same, then the two arrays are considered
     * equal.
     *
     * @param anObject The ByteArray to compare to this one for an equal match
     * @return <code>true</code> if all corresponding byte values are equal
     */
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof ByteArray) {
            return equalTo(this, (ByteArray) anObject);
        }
        return false;
    }

    /**
     * Compares the value of all the corresponding bytes between byte array
     * <i>a</i> and <i>b</i>. If the length of the arrays are the same and each
     * corresponding byte value is the same, then the two arrays are considered
     * equal.
     *
     * @param a The first ByteArray to compare
     * @param b The second ByteArray to compare
     * @return <code>true</code> if all corresponding byte values are equal
     */
    public static boolean equalTo(ByteArray a, ByteArray b) {
        if (a == null || b == null) {
            throw new IllegalNullArgumentException();
        }
        if (a == b) {
            return true;
        }
        a.value.clear();
        b.value.clear();
        if (a.value.remaining() != b.value.remaining())
            return false;
        int length = a.value.remaining();
        boolean match = true;
        for (int i = 0; i < length; i++) {
            if (a.value.get() != b.value.get()) {
                match = false;
                break;
            }
        }
        a.value.rewind();
        b.value.rewind();
        return match;
    }

    /**
     * Compares the value of all the bytes in this byte array to the specified
     * byte. If all of the values in the array are the same value as the
     * specified byte, then <code>true</code> is returned.
     *
     * @param b The byte value to compare as a match to all bytes in the byte
     *          array
     * @return <code>true</code> if all byte values are equal to <i>value</i>
     */
    public boolean equalSame(byte b) {
        value.clear();
        return equalSame(b, 0, value.remaining());
    }

    /**
     * Compares the value of all the bytes in this byte array to the specified
     * byte starting at <i>displacement</i> for <i>length</i> bytes. If all of
     * the values in this array for the specified range are the same value as
     * the specified byte, then <code>true</code> is returned.
     *
     * @param b            The byte value to compare as a match to all bytes in the byte
     *                     array
     * @param displacement The offset in this byte array, first byte is 0.
     * @param length       The length of the field in the buffer.
     * @return <code>true</code> if all byte values are equal to <i>value</i>
     */
    public boolean equalSame(byte b, int displacement, int length) {
        for (int i = 0, j = displacement; i < length; i++, j++) {
            if (value.get(j) != b)
                return false;
        }
        return true;
    }

    /**
     * @return the length of this ByteArray
     */
    public int getLength() {
        value.clear();
        return value.remaining();
    }

    public static class UnderlyingArray {
        public final byte[] value;
        public final int offset;
        public final int length;

        private UnderlyingArray(byte[] value, int offset, int length) {
            this.value = value;
            this.offset = offset;
            this.length = length;
        }
    }

    /**
     * Tells whether or not this buffer is backed by an accessible byte array.
     *
     * <p>
     * If this method returns <tt>true</tt> then the {@link #getArray()} method
     * may safely be invoked.
     * </p>
     *
     * @return <tt>true</tt> if, and only if, this ByteArray is backed by an
     * array and is not read-only
     */
    public boolean hasArray() {
        return value.hasArray();
    }

    /**
     * This will return the underlying byte array for this object. Modifying
     * this byte array will change the contents of the array in this object.
     * However, if this array is not accessible, an exception is thrown. This
     * array is not accessible from this method if the underlying ByteBuffer
     * does not have an accessible array or is read only.
     *
     * @return a structure indicating the underlying array and it's current
     * offset and length values.
     */
    public UnderlyingArray getArray() {
        byte[] bytes = value.array();
        int offset = value.arrayOffset();
        int length = value.capacity();
        return new UnderlyingArray(bytes, offset, length);
    }

    /**
     * This will return a copy of the contents of the array in this object.
     *
     * @return the array of bytes
     */
    public byte[] getBytes() {
        byte[] bytes = new byte[value.capacity()];
        value.clear();
        value.get(bytes);
        return bytes;
    }

    /**
     * This will return the underlying byte array for this object. Modifying
     * this byte array will change the contents of the array in this object.
     *
     * @return the array of bytes
     * @deprecated as of version 1.7, replaced by {@link #getBytes()}, now
     * replaced by {@link #getArray()}.
     */
    @Deprecated
    public byte[] getByteArray() {
        return getArray().value;
    }

    /**
     * This sets the encoding scheme to be used in setting and getting string
     * data from the byte array. This should match the encoding type of the
     * actual data. Setting this to an incorrect value will result in the string
     * data be improperly interpreted when constructing a String object which is
     * based on Unicode.
     *
     * @param encoding is the encoding used for the ByteArray
     * @return this ByteArray
     */
    public ByteArray setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    /**
     * This returns the encoding scheme to be used in setting and getting string
     * data from the byte array.
     *
     * @return encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * This sets the byte order to be used in setting and getting binary,
     * numeric data (short, int, long, float, double) from the byte array. This
     * should match the byte order of the actual data. Setting this to an
     * incorrect value will result in the numeric data be improperly interpreted
     * when constructing the primitive numeric value.
     *
     * @param order is the byte order of the primitives contained in the array
     * @return this ByteArray
     */
    public ByteArray setOrder(ByteOrder order) {
        value.order(order);
        return this;
    }

    /**
     * This returns the byte order to be used in setting and getting binary
     * numeric data from the byte array.
     *
     * @return order
     */
    public ByteOrder getOrder() {
        return value.order();
    }

    /**
     * This returns a new ByteBuffer with the same backing array used by this
     * object. The returned ByteBuffer is reset so that the position is set to
     * zero, the limit is set to the capacity and any mark is discarded.
     *
     * @return the underlying ByteBuffer
     */
    public ByteBuffer getBuffer() {
        ByteBuffer newByteBuffer = value.duplicate();
        // propagate order since duplicate does not do this
        newByteBuffer.order(value.order());
        // ensure limit is set to capacity
        newByteBuffer.clear();
        return newByteBuffer;
    }

}