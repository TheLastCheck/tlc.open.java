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
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;
import com.thelastcheck.io.x937.records.base.X937CheckDetailAddendumARecordBase;

public class X937CheckDetailAddendumARecordImpl extends X937CheckDetailAddendumARecordBase {

    private static int maxFieldNumber = 8;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("CorrectingInstitutionRoutingNumber", 2, 2, 9, FieldType.ROUTING_NUMBER);
        fields[3] = new Field("BOFDRoutingNumber", 3, 11, 9, FieldType.ROUTING_NUMBER);
        fields[4] = new Field("BOFDBusinessDate", 4, 20, 8, FieldType.DATE);
        fields[5] = new Field("BOFDItemSequenceNumber", 5, 28, 15, FieldType.STRING);
        fields[6] = new Field("DepositAccountNumberAtBOFD", 6, 43, 18, FieldType.STRING);
        fields[7] = new Field("BOFDDepositBranch", 7, 61, 5, FieldType.STRING);
        fields[8] = new Field("PayeeName", 8, 66, 14, FieldType.STRING);
    }


    /*
     * X937CheckDetailAddendumARecordImpl
     */

    public X937CheckDetailAddendumARecordImpl() {
        super();
    }

    public X937CheckDetailAddendumARecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937CheckDetailAddendumARecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937CheckDetailAddendumARecordImpl(ByteArray record, int stdLevel) {
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


    public RoutingNumber correctingInstitutionRoutingNumber() {
        return getFieldAsRoutingNumber(field(2));
    }

    public X937CheckDetailAddendumARecord correctingInstitutionRoutingNumber(RoutingNumber value) {
        setField(value, field(2));
        return this;
    }

    public String correctingInstitutionRoutingNumberAsString() {
        return getFieldAsString(field(2));
    }

    public X937CheckDetailAddendumARecord correctingInstitutionRoutingNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public RoutingNumber BOFDRoutingNumber() {
        return getFieldAsRoutingNumber(field(3));
    }

    public X937CheckDetailAddendumARecord BOFDRoutingNumber(RoutingNumber value) {
        setField(value, field(3));
        return this;
    }

    public String BOFDRoutingNumberAsString() {
        return getFieldAsString(field(3));
    }

    public X937CheckDetailAddendumARecord BOFDRoutingNumber(String value) {
        setField(value, field(3));
        return this;
    }

    public Date BOFDBusinessDate()
        throws InvalidDataException {
        return getFieldAsDate(field(4), x9TimeZone);
    }

    public X937CheckDetailAddendumARecord BOFDBusinessDate(Date value) {
        setFieldDate(value, field(4), x9TimeZone);        return this;
    }

    public String BOFDBusinessDateAsString() {
        return getFieldAsString(field(4));
    }

    public X937CheckDetailAddendumARecord BOFDBusinessDate(String value) {
        setField(value, field(4));
        return this;
    }

    public String BOFDItemSequenceNumber() {
        return getFieldAsString(field(5));
    }

    public X937CheckDetailAddendumARecord BOFDItemSequenceNumber(String value) {
        setField(value, field(5));
        return this;
    }

    public String depositAccountNumberAtBOFD() {
        return getFieldAsString(field(6));
    }

    public X937CheckDetailAddendumARecord depositAccountNumberAtBOFD(String value) {
        setField(value, field(6));
        return this;
    }

    public String BOFDDepositBranch() {
        return getFieldAsString(field(7));
    }

    public X937CheckDetailAddendumARecord BOFDDepositBranch(String value) {
        setField(value, field(7));
        return this;
    }

    public String payeeName() {
        return getFieldAsString(field(8));
    }

    public X937CheckDetailAddendumARecord payeeName(String value) {
        setField(value, field(8));
        return this;
    }

}

