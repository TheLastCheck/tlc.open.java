/**
 * ****************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ****************************************************************************
 */

package com.thelastcheck.io.x937.records.stddstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937RoutingNumberSummaryRecord;
import com.thelastcheck.io.x937.records.base.X937RoutingNumberSummaryRecordBase;

public class X937RoutingNumberSummaryRecordImpl extends X937RoutingNumberSummaryRecordBase {

    private static int maxFieldNumber = 6;
    private static Field fields[] = new Field[maxFieldNumber + 1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("RoutingNumberWithinCashLetter", 2, 2, 9, FieldType.ROUTING_NUMBER);
        fields[3] = new Field("RoutingNumberTotalAmount", 3, 11, 14, FieldType.LONG);
        fields[4] = new Field("RoutingNumberItemCount", 4, 25, 6, FieldType.INT);
        fields[5] = new Field("UserField", 5, 31, 24, FieldType.STRING);
        fields[6] = new Field("Reserved", 6, 55, 25, FieldType.STRING);
    }


    /*
     * X937RoutingNumberSummaryRecordImpl
     */

    public X937RoutingNumberSummaryRecordImpl() {
        super();
    }

    public X937RoutingNumberSummaryRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937RoutingNumberSummaryRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937RoutingNumberSummaryRecordImpl(ByteArray record, int stdLevel) {
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


    public RoutingNumber routingNumberWithinCashLetter() {
        return getFieldAsRoutingNumber(field(2));
    }

    public X937RoutingNumberSummaryRecord routingNumberWithinCashLetter(RoutingNumber value) {
        setField(value, field(2));
        return this;
    }

    public String routingNumberWithinCashLetterAsString() {
        return getFieldAsString(field(2));
    }

    public X937RoutingNumberSummaryRecord routingNumberWithinCashLetter(String value) {
        setField(value, field(2));
        return this;
    }

    public String routingNumberTotalAmount() {
        return getFieldAsString(field(3));
    }

    public X937RoutingNumberSummaryRecord routingNumberTotalAmount(String value) {
        setField(value, field(3));
        return this;
    }

    public long routingNumberTotalAmountAsLong()
            throws InvalidDataException {
        return getFieldAsLong(field(3));
    }

    public X937RoutingNumberSummaryRecord routingNumberTotalAmount(long value) {
        setField(value, field(3));
        return this;
    }

    public String routingNumberItemCount() {
        return getFieldAsString(field(4));
    }

    public X937RoutingNumberSummaryRecord routingNumberItemCount(String value) {
        setField(value, field(4));
        return this;
    }

    public int routingNumberItemCountAsInt()
            throws InvalidDataException {
        return getFieldAsInt(field(4));
    }

    public X937RoutingNumberSummaryRecord routingNumberItemCount(int value) {
        setField(value, field(4));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(5));
    }

    public X937RoutingNumberSummaryRecord userField(String value) {
        setField(value, field(5));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(6));
    }

    public X937RoutingNumberSummaryRecord reserved(String value) {
        setField(value, field(6));
        return this;
    }

}

