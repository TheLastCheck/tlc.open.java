/*******************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
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

package com.thelastcheck.io.x937.records.std1994;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;
import com.thelastcheck.io.x937.records.base.X937CheckDetailRecordBase;

public class X937CheckDetailRecordImpl extends X937CheckDetailRecordBase {

    private static int maxFieldNumber = 16;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("AuxiliaryOnUs", 2, 2, 15, FieldType.STRING);
        fields[3] = new Field("ExternalProcessingCode", 3, 17, 1, FieldType.STRING);
        fields[4] = new Field("PayorBankRoutingNumber", 4, 18, 9, FieldType.ROUTING_NUMBER);
        fields[5] = new Field("PayorBankRoutingNumberCheckDigit", 5, 26, 1, FieldType.STRING);
        fields[6] = new Field("OnUs", 6, 27, 20, FieldType.ONUS);
        fields[7] = new Field("ItemAmount", 7, 47, 10, FieldType.LONG);
        fields[8] = new Field("ECEInstitutionItemSequenceNumber", 8, 57, 15, FieldType.STRING);
        fields[9] = new Field("DocumentationTypeIndicator", 9, 72, 1, FieldType.STRING);
        fields[10] = new Field("ReturnAcceptanceIndicator", 10, 73, 1, FieldType.STRING);
        fields[11] = new Field("MICRValidIndicator", 11, 74, 1, FieldType.STRING);
        fields[12] = new Field("BOFDIndicator", 12, 75, 1, FieldType.STRING);
        fields[13] = new Field("CheckDetailRecordAddendumCount", 13, 76, 1, FieldType.INT);
        fields[14] = new Field("OnusFormatIndicator", 14, 77, 1, FieldType.STRING);
        fields[15] = new Field("UserField", 15, 78, 1, FieldType.STRING);
        fields[16] = new Field("Reserved", 16, 79, 1, FieldType.STRING);
    }


    /*
     * X937CheckDetailRecordImpl
     */

    public X937CheckDetailRecordImpl() {
        super();
    }

    public X937CheckDetailRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937CheckDetailRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937CheckDetailRecordImpl(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    @Override
    protected void resetDynamicFields() {
    }

    @Override
    public int numberOfFields() {
        return maxFieldNumber;
    }

    @Override
    protected Field field(int fieldNumber) {
        if (fieldNumber < 1 || fieldNumber > maxFieldNumber) {
            throw new IllegalArgumentException(INVALID_FIELD_NUMBER);
        }
        return fields[fieldNumber];
    }


    public String auxiliaryOnUs() {
        return getFieldAsString(field(2));
    }

    public X937CheckDetailRecord auxiliaryOnUs(String value) {
        setField(value, field(2));
        return this;
    }

    public String externalProcessingCode() {
        return getFieldAsString(field(3));
    }

    public X937CheckDetailRecord externalProcessingCode(String value) {
        setField(value, field(3));
        return this;
    }

    public RoutingNumber payorBankRoutingNumber() {
        return getFieldAsRoutingNumber(field(4));
    }

    public X937CheckDetailRecord payorBankRoutingNumber(RoutingNumber value) {
        setField(value, field(4));
        return this;
    }

    public String payorBankRoutingNumberAsString() {
        return getFieldAsString(field(4));
    }

    public X937CheckDetailRecord payorBankRoutingNumber(String value) {
        setField(value, field(4));
        return this;
    }

    public String payorBankRoutingNumberCheckDigit() {
        return getFieldAsString(field(5));
    }

    public X937CheckDetailRecord payorBankRoutingNumberCheckDigit(String value) {
        setField(value, field(5));
        return this;
    }

    public OnUsField onUs() {
        return new OnUsField(getFieldAsString(field(6)));
    }

    public X937CheckDetailRecord onUs(OnUsField value) {
        setField(value, field(6));
        return this;
    }

    public String onUsAsString() {
        return getFieldAsString(field(6));
    }

    public X937CheckDetailRecord onUs(String value) {
        setField(value, field(6));
        return this;
    }

    public String itemAmount() {
        return getFieldAsString(field(7));
    }

    public X937CheckDetailRecord itemAmount(String value) {
        setField(value, field(7));
        return this;
    }

    public long itemAmountAsLong()
        throws InvalidDataException {
        return getFieldAsLong(field(7));
    }

    public X937CheckDetailRecord itemAmount(long value) {
        setField(value, field(7));
        return this;
    }

    public String ECEInstitutionItemSequenceNumber() {
        return getFieldAsString(field(8));
    }

    public X937CheckDetailRecord ECEInstitutionItemSequenceNumber(String value) {
        setField(value, field(8));
        return this;
    }

    public String documentationTypeIndicator() {
        return getFieldAsString(field(9));
    }

    public X937CheckDetailRecord documentationTypeIndicator(String value) {
        setField(value, field(9));
        return this;
    }

    public String returnAcceptanceIndicator() {
        return getFieldAsString(field(10));
    }

    public X937CheckDetailRecord returnAcceptanceIndicator(String value) {
        setField(value, field(10));
        return this;
    }

    public String MICRValidIndicator() {
        return getFieldAsString(field(11));
    }

    public X937CheckDetailRecord MICRValidIndicator(String value) {
        setField(value, field(11));
        return this;
    }

    public String BOFDIndicator() {
        return getFieldAsString(field(12));
    }

    public X937CheckDetailRecord BOFDIndicator(String value) {
        setField(value, field(12));
        return this;
    }

    public String checkDetailRecordAddendumCount() {
        return getFieldAsString(field(13));
    }

    public X937CheckDetailRecord checkDetailRecordAddendumCount(String value) {
        setField(value, field(13));
        return this;
    }

    public int checkDetailRecordAddendumCountAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(13));
    }

    public X937CheckDetailRecord checkDetailRecordAddendumCount(int value) {
        setField(value, field(13));
        return this;
    }

    public String onusFormatIndicator() {
        return getFieldAsString(field(14));
    }

    public X937CheckDetailRecord onusFormatIndicator(String value) {
        setField(value, field(14));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(15));
    }

    public X937CheckDetailRecord userField(String value) {
        setField(value, field(15));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(16));
    }

    public X937CheckDetailRecord reserved(String value) {
        setField(value, field(16));
        return this;
    }

}

