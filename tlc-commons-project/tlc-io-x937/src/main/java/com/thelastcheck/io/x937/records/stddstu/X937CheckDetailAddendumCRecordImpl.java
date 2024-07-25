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

package com.thelastcheck.io.x937.records.stddstu;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumCRecord;
import com.thelastcheck.io.x937.records.base.X937CheckDetailAddendumCRecordBase;

public class X937CheckDetailAddendumCRecordImpl extends X937CheckDetailAddendumCRecordBase {

    private static int maxFieldNumber = 11;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("CheckDetailAddendumCRecordNumber", 2, 2, 2, FieldType.INT);
        fields[3] = new Field("EndorsingBankRoutingNumber", 3, 4, 9, FieldType.ROUTING_NUMBER);
        fields[4] = new Field("EndorsingBankEndorsementDate", 4, 13, 8, FieldType.DATE);
        fields[5] = new Field("EndorsingBankItemSequenceNumber", 5, 21, 15, FieldType.STRING);
        fields[6] = new Field("TruncationIndicator", 6, 36, 1, FieldType.STRING);
        fields[7] = new Field("EndorsingBankConversionIndicator", 7, 37, 1, FieldType.STRING);
        fields[8] = new Field("EndorsingBankCorrectionIndicator", 8, 38, 1, FieldType.STRING);
        fields[9] = new Field("ReturnReason", 9, 39, 1, FieldType.STRING);
        fields[10] = new Field("UserField", 10, 40, 15, FieldType.STRING);
        fields[11] = new Field("Reserved", 11, 55, 25, FieldType.STRING);
    }


    /*
     * X937CheckDetailAddendumCRecordImpl
     */

    public X937CheckDetailAddendumCRecordImpl() {
        super();
    }

    public X937CheckDetailAddendumCRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937CheckDetailAddendumCRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937CheckDetailAddendumCRecordImpl(ByteArray record, int stdLevel) {
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


    public String checkDetailAddendumCRecordNumber() {
        return getFieldAsString(field(2));
    }

    public X937CheckDetailAddendumCRecord checkDetailAddendumCRecordNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public int checkDetailAddendumCRecordNumberAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(2));
    }

    public X937CheckDetailAddendumCRecord checkDetailAddendumCRecordNumber(int value) {
        setField(value, field(2));
        return this;
    }

    public RoutingNumber endorsingBankRoutingNumber() {
        return getFieldAsRoutingNumber(field(3));
    }

    public X937CheckDetailAddendumCRecord endorsingBankRoutingNumber(RoutingNumber value) {
        setField(value, field(3));
        return this;
    }

    public String endorsingBankRoutingNumberAsString() {
        return getFieldAsString(field(3));
    }

    public X937CheckDetailAddendumCRecord endorsingBankRoutingNumber(String value) {
        setField(value, field(3));
        return this;
    }

    public Date endorsingBankEndorsementDate()
        throws InvalidDataException {
        return getFieldAsDate(field(4), x9TimeZone);
    }

    public X937CheckDetailAddendumCRecord endorsingBankEndorsementDate(Date value) {
        setFieldDate(value, field(4), x9TimeZone);
        return this;
    }

    public String endorsingBankEndorsementDateAsString() {
        return getFieldAsString(field(4));
    }

    public X937CheckDetailAddendumCRecord endorsingBankEndorsementDate(String value) {
        setField(value, field(4));
        return this;
    }

    public String endorsingBankItemSequenceNumber() {
        return getFieldAsString(field(5));
    }

    public X937CheckDetailAddendumCRecord endorsingBankItemSequenceNumber(String value) {
        setField(value, field(5));
        return this;
    }

    public String truncationIndicator() {
        return getFieldAsString(field(6));
    }

    public X937CheckDetailAddendumCRecord truncationIndicator(String value) {
        setField(value, field(6));
        return this;
    }

    public String endorsingBankConversionIndicator() {
        return getFieldAsString(field(7));
    }

    public X937CheckDetailAddendumCRecord endorsingBankConversionIndicator(String value) {
        setField(value, field(7));
        return this;
    }

    public String endorsingBankCorrectionIndicator() {
        return getFieldAsString(field(8));
    }

    public X937CheckDetailAddendumCRecord endorsingBankCorrectionIndicator(String value) {
        setField(value, field(8));
        return this;
    }

    public String returnReason() {
        return getFieldAsString(field(9));
    }

    public X937CheckDetailAddendumCRecord returnReason(String value) {
        setField(value, field(9));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(10));
    }

    public X937CheckDetailAddendumCRecord userField(String value) {
        setField(value, field(10));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(11));
    }

    public X937CheckDetailAddendumCRecord reserved(String value) {
        setField(value, field(11));
        return this;
    }

}

