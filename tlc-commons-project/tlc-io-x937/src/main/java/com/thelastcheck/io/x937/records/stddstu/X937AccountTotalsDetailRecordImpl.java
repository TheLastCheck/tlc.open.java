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
import com.thelastcheck.io.x937.records.X937AccountTotalsDetailRecord;
import com.thelastcheck.io.x937.records.base.X937AccountTotalsDetailRecordBase;

public class X937AccountTotalsDetailRecordImpl extends X937AccountTotalsDetailRecordBase {

    private static int maxFieldNumber = 8;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("DestinationRoutingNumber", 2, 2, 9, FieldType.ROUTING_NUMBER);
        fields[3] = new Field("KeyAccountOrLowAccountInKeyAccountRange", 3, 11, 18, FieldType.STRING);
        fields[4] = new Field("KeyAccountOrHighAccountInKeyAccountRange", 4, 29, 18, FieldType.STRING);
        fields[5] = new Field("TotalItemCount", 5, 47, 12, FieldType.LONG);
        fields[6] = new Field("TotalItemAmount", 6, 59, 14, FieldType.LONG);
        fields[7] = new Field("UserField", 7, 73, 4, FieldType.STRING);
        fields[8] = new Field("Reserved", 8, 77, 3, FieldType.STRING);
    }


    /*
     * X937AccountTotalsDetailRecordImpl
     */

    public X937AccountTotalsDetailRecordImpl() {
        super();
    }

    public X937AccountTotalsDetailRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937AccountTotalsDetailRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937AccountTotalsDetailRecordImpl(ByteArray record, int stdLevel) {
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


    public RoutingNumber destinationRoutingNumber() {
        return getFieldAsRoutingNumber(field(2));
    }

    public X937AccountTotalsDetailRecord destinationRoutingNumber(RoutingNumber value) {
        setField(value, field(2));
        return this;
    }

    public String destinationRoutingNumberAsString() {
        return getFieldAsString(field(2));
    }

    public X937AccountTotalsDetailRecord destinationRoutingNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public String keyAccountOrLowAccountInKeyAccountRange() {
        return getFieldAsString(field(3));
    }

    public X937AccountTotalsDetailRecord keyAccountOrLowAccountInKeyAccountRange(String value) {
        setField(value, field(3));
        return this;
    }

    public String keyAccountOrHighAccountInKeyAccountRange() {
        return getFieldAsString(field(4));
    }

    public X937AccountTotalsDetailRecord keyAccountOrHighAccountInKeyAccountRange(String value) {
        setField(value, field(4));
        return this;
    }

    public String totalItemCount() {
        return getFieldAsString(field(5));
    }

    public X937AccountTotalsDetailRecord totalItemCount(String value) {
        setField(value, field(5));
        return this;
    }

    public long totalItemCountAsLong()
        throws InvalidDataException {
        return getFieldAsLong(field(5));
    }

    public X937AccountTotalsDetailRecord totalItemCount(long value) {
        setField(value, field(5));
        return this;
    }

    public String totalItemAmount() {
        return getFieldAsString(field(6));
    }

    public X937AccountTotalsDetailRecord totalItemAmount(String value) {
        setField(value, field(6));
        return this;
    }

    public long totalItemAmountAsLong()
        throws InvalidDataException {
        return getFieldAsLong(field(6));
    }

    public X937AccountTotalsDetailRecord totalItemAmount(long value) {
        setField(value, field(6));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(7));
    }

    public X937AccountTotalsDetailRecord userField(String value) {
        setField(value, field(7));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(8));
    }

    public X937AccountTotalsDetailRecord reserved(String value) {
        setField(value, field(8));
        return this;
    }

}

