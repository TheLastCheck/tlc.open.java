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
import com.thelastcheck.io.x937.records.X937ReturnRecord;
import com.thelastcheck.io.x937.records.base.X937ReturnRecordBase;

public class X937ReturnRecordImpl extends X937ReturnRecordBase {

    private static int maxFieldNumber = 11;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("PayorBankRoutingNumber", 2, 2, 9, FieldType.ROUTING_NUMBER);
        fields[3] = new Field("PayorBankRoutingNumberCheckDigit", 3, 10, 1, FieldType.STRING);
        fields[4] = new Field("OnUsReturnRecord", 4, 11, 20, FieldType.ONUS);
        fields[5] = new Field("ItemAmount", 5, 31, 10, FieldType.LONG);
        fields[6] = new Field("ReturnReason", 6, 41, 1, FieldType.STRING);
        fields[7] = new Field("ReturnRecordAddendumCount", 7, 42, 1, FieldType.INT);
        fields[8] = new Field("ForwardBundleDate", 8, 43, 8, FieldType.DATE);
        fields[9] = new Field("ECEInstitutionItemSequenceNumber", 9, 51, 15, FieldType.STRING);
        fields[10] = new Field("PayorAccountName", 10, 66, 13, FieldType.STRING);
        fields[11] = new Field("UserField", 11, 79, 1, FieldType.STRING);
    }


    /*
     * X937ReturnRecordImpl
     */

    public X937ReturnRecordImpl() {
        super();
    }

    public X937ReturnRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937ReturnRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937ReturnRecordImpl(ByteArray record, int stdLevel) {
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


    public RoutingNumber payorBankRoutingNumber() {
        return getFieldAsRoutingNumber(field(2));
    }

    public X937ReturnRecord payorBankRoutingNumber(RoutingNumber value) {
        setField(value, field(2));
        return this;
    }

    public String payorBankRoutingNumberAsString() {
        return getFieldAsString(field(2));
    }

    public X937ReturnRecord payorBankRoutingNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public String payorBankRoutingNumberCheckDigit() {
        return getFieldAsString(field(3));
    }

    public X937ReturnRecord payorBankRoutingNumberCheckDigit(String value) {
        setField(value, field(3));
        return this;
    }

    public OnUsField onUsReturnRecord() {
        return new OnUsField(getFieldAsString(field(4)));
    }

    public X937ReturnRecord onUsReturnRecord(OnUsField value) {
        setField(value, field(4));
        return this;
    }

    public String onUsReturnRecordAsString() {
        return getFieldAsString(field(4));
    }

    public X937ReturnRecord onUsReturnRecord(String value) {
        setField(value, field(4));
        return this;
    }

    public String itemAmount() {
        return getFieldAsString(field(5));
    }

    public X937ReturnRecord itemAmount(String value) {
        setField(value, field(5));
        return this;
    }

    public long itemAmountAsLong()
        throws InvalidDataException {
        return getFieldAsLong(field(5));
    }

    public X937ReturnRecord itemAmount(long value) {
        setField(value, field(5));
        return this;
    }

    public String returnReason() {
        return getFieldAsString(field(6));
    }

    public X937ReturnRecord returnReason(String value) {
        setField(value, field(6));
        return this;
    }

    public String returnRecordAddendumCount() {
        return getFieldAsString(field(7));
    }

    public X937ReturnRecord returnRecordAddendumCount(String value) {
        setField(value, field(7));
        return this;
    }

    public int returnRecordAddendumCountAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(7));
    }

    public X937ReturnRecord returnRecordAddendumCount(int value) {
        setField(value, field(7));
        return this;
    }

    public Date forwardBundleDate()
        throws InvalidDataException {
        return getFieldAsDate(field(8), x9TimeZone);
    }

    public X937ReturnRecord forwardBundleDate(Date value) {
        setFieldDate(value, field(8), x9TimeZone);        return this;
    }

    public String forwardBundleDateAsString() {
        return getFieldAsString(field(8));
    }

    public X937ReturnRecord forwardBundleDate(String value) {
        setField(value, field(8));
        return this;
    }

    public String ECEInstitutionItemSequenceNumber() {
        return getFieldAsString(field(9));
    }

    public X937ReturnRecord ECEInstitutionItemSequenceNumber(String value) {
        setField(value, field(9));
        return this;
    }

    public String payorAccountName() {
        return getFieldAsString(field(10));
    }

    public X937ReturnRecord payorAccountName(String value) {
        setField(value, field(10));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(11));
    }

    public X937ReturnRecord userField(String value) {
        setField(value, field(11));
        return this;
    }

}

