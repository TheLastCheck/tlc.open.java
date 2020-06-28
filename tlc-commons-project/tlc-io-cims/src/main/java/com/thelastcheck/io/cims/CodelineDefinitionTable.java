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

public class CodelineDefinitionTable extends CodelineDefinition {

    private static final int MAX_FIELDS = 16;
    private static final int BUFFER_LENGTH = 149;
    private static final int SS_DATA_DIS = 0;
    private static final int SS_DATA_LEN = 1;
    private static final int BYTE_1_2_DATA_DIS = SS_DATA_DIS + SS_DATA_LEN;
    private static final int BYTE_1_2_DATA_LEN = 2;
    private static final int HIGH_ORDER_DIGIT_ERRORS_DIS =
            BYTE_1_2_DATA_DIS + BYTE_1_2_DATA_LEN;
    private static final int HIGH_ORDER_DIGIT_ERRORS_LEN = 1;
    private static final int BYTE_4_DIS =
            HIGH_ORDER_DIGIT_ERRORS_DIS + HIGH_ORDER_DIGIT_ERRORS_LEN;
    private static final int BYTE_4_LEN = 1;
    private static final int FIELD_DEFINITION_DIS = BYTE_4_DIS + BYTE_4_LEN;
    // count of MAX_FIELDS, length of each field
    private static final int FIELD_DEFINITION_LEN = FieldDefinition.BUFFER_LENGTH;

    private final FieldDefinition[] fieldDefinitionArray = new FieldDefinition[MAX_FIELDS];

    public CodelineDefinitionTable() {
        super();
    }

    public CodelineDefinitionTable(byte[] newBuffer) {
        setBuffer(newBuffer);
    }

    public void setBuffer(byte[] newBuffer) {
        super.setBuffer(newBuffer);
        buildFieldDefinitionArray();
    }

    private void buildFieldDefinitionArray() {
        int length = FIELD_DEFINITION_LEN;
        int start = FIELD_DEFINITION_DIS;
        for (int i = 0; i < fieldDefinitionArray.length; i++) {
            byte[] fieldDefinition = new byte[FIELD_DEFINITION_LEN];
            buffer.read(start, fieldDefinition, 0, length);
            fieldDefinitionArray[i] = new FieldDefinition(fieldDefinition);
            start += length;
        }
    }

    public int getBufferLength() {
        return BUFFER_LENGTH;
    }

    public boolean getSS1DataFlag() {
        return buffer.testBit(SS_DATA_DIS, (byte) 0x80);
    }

    public void setSS1DataFlag(boolean flag) {
        buffer.setBit(SS_DATA_DIS, (byte) 0x80, flag);
    }

    public boolean getSS2DataFlag() {
        return buffer.testBit(SS_DATA_DIS, (byte) 0x40);
    }

    public void setSS2DataFlag(boolean flag) {
        buffer.setBit(SS_DATA_DIS, (byte) 0x40, flag);
    }

    public boolean getSS3DataFlag() {
        return buffer.testBit(SS_DATA_DIS, (byte) 0x20);
    }

    public void setSS3DataFlag(boolean flag) {
        buffer.setBit(SS_DATA_DIS, (byte) 0x20, flag);
    }

    public boolean getSS4DataFlag() {
        return buffer.testBit(SS_DATA_DIS, (byte) 0x10);
    }

    public void setSS4DataFlag(boolean flag) {
        buffer.setBit(SS_DATA_DIS, (byte) 0x10, flag);
    }

    public boolean getSS5DataFlag() {
        return buffer.testBit(SS_DATA_DIS, (byte) 0x08);
    }

    public void setSS5DataFlag(boolean flag) {
        buffer.setBit(SS_DATA_DIS, (byte) 0x08, flag);
    }

    public boolean getSS6DataFlag() {
        return buffer.testBit(SS_DATA_DIS, (byte) 0x04);
    }

    public void setSS6DataFlag(boolean flag) {
        buffer.setBit(SS_DATA_DIS, (byte) 0x04, flag);
    }

    public boolean getErrorCorrectionAllowed() {
        return buffer.testBit(1, (byte) 0x80);
    }

    public void setErrorCorrectionAllowed(boolean flag) {
        buffer.setBit(1, (byte) 0x80, flag);
    }

    public boolean getField1CorrectionFlag() {
        return buffer.testBit(1, (byte) 0x40);
    }

    public void setField1CorrectionFlag(boolean flag) {
        buffer.setBit(1, (byte) 0x40, flag);
    }

    public boolean getField2CorrectionFlag() {
        return buffer.testBit(1, (byte) 0x20);
    }

    public void setField2CorrectionFlag(boolean flag) {
        buffer.setBit(1, (byte) 0x20, flag);
    }

    public boolean getField3CorrectionFlag() {
        return buffer.testBit(1, (byte) 0x10);
    }

    public void setField3CorrectionFlag(boolean flag) {
        buffer.setBit(1, (byte) 0x10, flag);
    }

    public boolean getField4CorrectionFlag() {
        return buffer.testBit(1, (byte) 0x08);
    }

    public void setField4CorrectionFlag(boolean flag) {
        buffer.setBit(1, (byte) 0x08, flag);
    }

    public boolean getField5CorrectionFlag() {
        return buffer.testBit(1, (byte) 0x04);
    }

    public void setField5CorrectionFlag(boolean flag) {
        buffer.setBit(1, (byte) 0x04, flag);
    }

    public boolean getField6CorrectionFlag() {
        return buffer.testBit(1, (byte) 0x02);
    }

    public void setField6CorrectionFlag(boolean flag) {
        buffer.setBit(1, (byte) 0x02, flag);
    }

    public boolean getField7CorrectionFlag() {
        return buffer.testBit(1, (byte) 0x01);
    }

    public void setField7CorrectionFlag(boolean flag) {
        buffer.setBit(1, (byte) 0x01, flag);
    }

    public boolean getField8CorrectionFlag() {
        return buffer.testBit(2, (byte) 0x80);
    }

    public void setField8CorrectionFlag(boolean flag) {
        buffer.setBit(2, (byte) 0x80, flag);
    }

    public boolean getField9CorrectionFlag() {
        return buffer.testBit(2, (byte) 0x40);
    }

    public void setField9CorrectionFlag(boolean flag) {
        buffer.setBit(2, (byte) 0x40, flag);
    }

    public boolean getField10CorrectionFlag() {
        return buffer.testBit(2, (byte) 0x20);
    }

    public void setField10CorrectionFlag(boolean flag) {
        buffer.setBit(2, (byte) 0x20, flag);
    }

    public boolean getField11CorrectionFlag() {
        return buffer.testBit(2, (byte) 0x10);
    }

    public void setField11CorrectionFlag(boolean flag) {
        buffer.setBit(2, (byte) 0x10, flag);
    }

    public boolean getField12CorrectionFlag() {
        return buffer.testBit(2, (byte) 0x08);
    }

    public void setField12CorrectionFlag(boolean flag) {
        buffer.setBit(2, (byte) 0x08, flag);
    }

    public boolean getField13CorrectionFlag() {
        return buffer.testBit(2, (byte) 0x04);
    }

    public void setField13CorrectionFlag(boolean flag) {
        buffer.setBit(2, (byte) 0x04, flag);
    }

    public boolean getField14CorrectionFlag() {
        return buffer.testBit(2, (byte) 0x02);
    }

    public void setField14CorrectionFlag(boolean flag) {
        buffer.setBit(2, (byte) 0x02, flag);
    }

    public boolean getField15CorrectionFlag() {
        return buffer.testBit(2, (byte) 0x01);
    }

    public void setField15CorrectionFlag(boolean flag) {
        buffer.setBit(2, (byte) 0x01, flag);
    }

    /**
     * 8-bit binary integer that specifies the
     * number of high-order digit errors that can be
     * corrected to zeros in the amount field.
     *
     * @return int number of high order digit errors
     */
    public int getNumberOfHighOrderDigitErrors() {
        return buffer.readAsByte(HIGH_ORDER_DIGIT_ERRORS_DIS);
    }

    /**
     * 8-bit binary integer that specifies the
     * number of high-order digit errors that can be
     * corrected to zeros in the amount field.
     */
    public void setNumberOfHighOrderDigitErrors(int value) {
        buffer.write((byte) (value & 0xff), HIGH_ORDER_DIGIT_ERRORS_DIS);
    }

    /**
     * Byte  4,   bit 0   Is routing number transparency mode active.
     *
     * @return
     */
    public boolean getRoutingNumTransparency() {
        return buffer.testBit(BYTE_4_DIS, (byte) 0x80);
    }

    /**
     * Byte  4,   bit 0   Is routing number transparency mode active.
     *
     * @return
     */
    public void setRoutingNumTransparency(boolean flag) {
        buffer.setBit(BYTE_4_DIS, (byte) 0x80, flag);
    }

    /**
     * bit 1   Code line data match zero-for-able is active.
     *
     * @return
     */
    public boolean getCodeLineDataMatchZeroForAble() {
        return buffer.testBit(BYTE_4_DIS, (byte) 0x40);
    }

    /**
     * bit 1   Code line data match zero-for-able is active.
     *
     * @return
     */
    public void setCodeLineDataMatchZeroForAble(boolean flag) {
        buffer.setBit(BYTE_4_DIS, (byte) 0x40, flag);
    }

    /**
     * bit 2   Code line data match match don't-care-digit is active.
     *
     * @return
     */
    public boolean getCodeLineDataMatchDontCareDigit() {
        return buffer.testBit(BYTE_4_DIS, (byte) 0x20);
    }

    /**
     * bit 2   Code line data match match don't-care-digit is active.
     *
     * @return
     */
    public void setCodeLineDataMatchDontCareDigit(boolean flag) {
        buffer.setBit(BYTE_4_DIS, (byte) 0x20, flag);
    }

    /**
     * bit 3   Sort program gets control of auto-selected documents.
     */
    public boolean getSortProgramGetsControl() {
        return buffer.testBit(BYTE_4_DIS, (byte) 0x10);
    }

    /**
     * bit 3   Sort program gets control of auto-selected documents.
     */
    public void setSortProgramGetsControl(boolean flag) {
        buffer.setBit(BYTE_4_DIS, (byte) 0x10, flag);
    }

    /**
     * Returns array of FieldDefinition objects for this code line data definition table.
     *
     * @return
     */
    public FieldDefinition[] getFieldDefinitionArray() {
        return fieldDefinitionArray;
    }

    /**
     * Gets FieldDefinition object for specified field.
     *
     * @param fieldNumber field number 1-16 for the requested field definition
     * @return FieldDefinition for requested field
     */
    public FieldDefinition getFieldDefinition(int fieldNumber) {
        Preconditions.checkElementIndex(fieldNumber, MAX_FIELDS, "field number");
        return fieldDefinitionArray[fieldNumber];
    }

    /**
     * Gets FieldDefinition object for specified field.
     *
     * @param fieldNumber field number 1-16 for the field definition to be stored
     */
    public void setFieldDefinition(int fieldNumber, FieldDefinition fd) {
        Preconditions.checkElementIndex(fieldNumber, MAX_FIELDS, "field number");
        fieldDefinitionArray[fieldNumber] = fd;
    }

    public byte[] toByteArray() {
        int length = FIELD_DEFINITION_LEN;
        int offset = FIELD_DEFINITION_DIS;
        for (int i = 0; i < fieldDefinitionArray.length; i++) {
            if (fieldDefinitionArray[i] != null) {
                buffer.write(fieldDefinitionArray[i].toByteArray(), 0, length, offset);
            }
            offset += length;
        }
        return buffer.getBytes();
    }
}
