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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FieldDefinition extends CodelineDefinition {

    public static final int BUFFER_LENGTH = 9;

    /**
     * Default constructor.
     */
    public FieldDefinition() {
        super();
        setBuffer(new byte[getBufferLength()]);
    }

    public FieldDefinition(byte[] buffer) {
        this();
        setBuffer(buffer);
    }

    public int getBufferLength() {
        return BUFFER_LENGTH;
    }

    /**
     * Byte 0 Field-opening symbol (right aligned) (X'00', X'0A')
     */
    public byte getOpeningSymbol() {
        return buffer.readAsByte(0);
    }

    /**
     * Byte 0 Field-opening symbol (right aligned) (X'00', X'0A')
     */
    public void setOpeningSymbol(byte value) {
        buffer.write(value, 0);
    }

    /**
     * Byte 1 Field-closing symbol (right aligned)
     */
    public byte getClosingSymbol() {
        return buffer.readAsByte(1);
    }

    /**
     * Byte 1 Field-closing symbol (right aligned)
     */
    public void setClosingSymbol(byte value) {
        buffer.write(value, 1);
    }

    /**
     * Byte 2 Field alternate closing symbol (right aligned)
     */
    public byte getAlternateClosingSymbol() {
        return buffer.readAsByte(2);
    }

    /**
     * Byte 2 Field alternate closing symbol (right aligned)
     */
    public void setAlternateClosingSymbol(byte value) {
        buffer.write(value, 2);
    }

    /**
     * Byte 3 Eight-bit integer value that gives the offset of the low-order digit of the
     * field from the low-order end (right end) of the process buffer
     */
    public int getLowOrderDigitOffset() {
        return buffer.readAsByte(3);
    }

    /**
     * Byte 3 Eight-bit integer value that gives the offset of the low-order digit of the
     * field from the low-order end (right end) of the process buffer
     */
    public void setLowOrderDigitOffset(int value) {
        buffer.write((byte) value, 3);
    }

    /**
     * Byte 4 Eight-bit integer value that gives the maximum number of digits for this field
     * in the process buffer
     */
    public int getMaxNumberOfDigits() {
        return buffer.readAsByte(4);
    }

    /**
     * Byte 4 Eight-bit integer value that gives the maximum number of digits for this field
     * in the process buffer
     */
    public void setMaxNumberOfDigits(int value) {
        buffer.write((byte) value, 4);
    }

    /**
     * Byte 5 Eight-bit integer value that gives the number of bytes for this field in the
     * process buffer
     */
    public int getNumberOfBytes() {
        return buffer.readAsByte(5);
    }

    /**
     * Byte 5 Eight-bit integer value that gives the number of bytes for this field in the
     * process buffer
     */
    public void setNumberOfBytes(int value) {
        buffer.write((byte) value, 5);
    }

    /**
     * Byte 6 Eight-bit integer value that gives the code line data match threshold limit
     */
    public int getCodeLineDataMatchThresholdLimit() {
        return buffer.readAsByte(6);
    }

    /**
     * Byte 6 Eight-bit integer value that gives the code line data match threshold limit
     */
    public void setCodeLineDataMatchTresholdLimit(int value) {
        buffer.write((byte) value, 6);
    }

    /**
     * Byte 7 bit 0 Fixed-length field
     * matching allowed
     */
    public boolean getFixedLengthField() {
        return buffer.testBit(7, (byte) 0x80);
    }

    /**
     * Byte 7 bit 0 Fixed-length field
     * matching allowed
     */
    public void setFixedLengthField(boolean value) {
        buffer.setBit(7, (byte) 0x80, value);
    }

    /**
     * Byte 7, bit 1 Dash transmission allowed.
     */
    public boolean getDashTransmissionAllowed() {
        return buffer.testBit(7, (byte) 0x40);
    }

    /**
     * Byte 7, bit 1 Dash transmission allowed.
     */
    public void setDashTransmissionAllowed(boolean value) {
        buffer.setBit(7, (byte) 0x40, value);
    }

    /**
     * Byte 7, bit 2 Code line data matching allowed.
     */
    public boolean getCodeLineDataMatchingAllowed() {
        return buffer.testBit(7, (byte) 0x20);
    }

    /**
     * Byte 7, bit 2 Code line data matching allowed.
     */
    public void setCodeLineDataMatchingAllowed(boolean value) {
        buffer.setBit(7, (byte) 0x20, value);
    }

    /**
     * Byte 8 (reserved byte).
     */
    public byte getReserved() {
        return buffer.readAsByte(8);
    }

    /**
     * Byte 8 (reserved byte).
     */
    public void setReserved(byte value) {
        buffer.write(8, value);
    }

    public byte[] toByteArray() {
        return getBuffer();
    }

    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        sb.append("code line data matching allowed", getCodeLineDataMatchingAllowed());
        sb.append("code line data match threshold limit", getCodeLineDataMatchThresholdLimit());
        sb.append("dash transmission allowed", getDashTransmissionAllowed());
        sb.append("fixed length field", getFixedLengthField());
        sb.append("low order digit offset", getLowOrderDigitOffset());
        sb.append("number of digits", getMaxNumberOfDigits());
        sb.append("number of bytes", getNumberOfBytes());
        sb.append("opening symbol", getOpeningSymbol());
        sb.append("closing symbol", getClosingSymbol());
        sb.append("alternate closing symbol", getAlternateClosingSymbol());
        return sb.build();
    }
}
