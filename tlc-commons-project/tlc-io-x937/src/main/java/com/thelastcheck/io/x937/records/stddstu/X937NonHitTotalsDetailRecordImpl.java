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
import com.thelastcheck.io.x937.records.X937NonHitTotalsDetailRecord;
import com.thelastcheck.io.x937.records.base.X937NonHitTotalsDetailRecordBase;

public class X937NonHitTotalsDetailRecordImpl extends X937NonHitTotalsDetailRecordBase {

    private static int maxFieldNumber = 7;
    private static Field fields[] = new Field[maxFieldNumber + 1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("DestinationRoutingNumber", 2, 2, 9, FieldType.ROUTING_NUMBER);
        fields[3] = new Field("NonHitIndicator", 3, 11, 1, FieldType.STRING);
        fields[4] = new Field("TotalItemCount", 4, 12, 12, FieldType.LONG);
        fields[5] = new Field("TotalItemAmount", 5, 24, 14, FieldType.LONG);
        fields[6] = new Field("UserField", 6, 38, 12, FieldType.STRING);
        fields[7] = new Field("Reserved", 7, 50, 30, FieldType.STRING);
    }


    /*
     * X937NonHitTotalsDetailRecordImpl
     */

    public X937NonHitTotalsDetailRecordImpl() {
        super();
    }

    public X937NonHitTotalsDetailRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937NonHitTotalsDetailRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937NonHitTotalsDetailRecordImpl(ByteArray record, int stdLevel) {
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

    public X937NonHitTotalsDetailRecord destinationRoutingNumber(RoutingNumber value) {
        setField(value, field(2));
        return this;
    }

    public String destinationRoutingNumberAsString() {
        return getFieldAsString(field(2));
    }

    public X937NonHitTotalsDetailRecord destinationRoutingNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public String nonHitIndicator() {
        return getFieldAsString(field(3));
    }

    public X937NonHitTotalsDetailRecord nonHitIndicator(String value) {
        setField(value, field(3));
        return this;
    }

    public String totalItemCount() {
        return getFieldAsString(field(4));
    }

    public X937NonHitTotalsDetailRecord totalItemCount(String value) {
        setField(value, field(4));
        return this;
    }

    public long totalItemCountAsLong()
            throws InvalidDataException {
        return getFieldAsLong(field(4));
    }

    public X937NonHitTotalsDetailRecord totalItemCount(long value) {
        setField(value, field(4));
        return this;
    }

    public String totalItemAmount() {
        return getFieldAsString(field(5));
    }

    public X937NonHitTotalsDetailRecord totalItemAmount(String value) {
        setField(value, field(5));
        return this;
    }

    public long totalItemAmountAsLong()
            throws InvalidDataException {
        return getFieldAsLong(field(5));
    }

    public X937NonHitTotalsDetailRecord totalItemAmount(long value) {
        setField(value, field(5));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(6));
    }

    public X937NonHitTotalsDetailRecord userField(String value) {
        setField(value, field(6));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(7));
    }

    public X937NonHitTotalsDetailRecord reserved(String value) {
        setField(value, field(7));
        return this;
    }

}

