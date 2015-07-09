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
import com.thelastcheck.io.x937.records.X937FileControlRecord;
import com.thelastcheck.io.x937.records.base.X937FileControlRecordBase;

public class X937FileControlRecordImpl extends X937FileControlRecordBase {

    private static int maxFieldNumber = 8;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("CashLetterCount", 2, 2, 6, FieldType.INT);
        fields[3] = new Field("TotalRecordCount", 3, 8, 8, FieldType.INT);
        fields[4] = new Field("TotalItemCount", 4, 16, 8, FieldType.INT);
        fields[5] = new Field("FileTotalAmount", 5, 24, 16, FieldType.LONG);
        fields[6] = new Field("ImmediateOriginContactName", 6, 40, 14, FieldType.STRING);
        fields[7] = new Field("ImmediateOriginContactPhoneNumber", 7, 54, 10, FieldType.STRING);
        fields[8] = new Field("Reserved", 8, 64, 16, FieldType.STRING);
    }


    /*
     * X937FileControlRecordImpl
     */

    public X937FileControlRecordImpl() {
        super();
    }

    public X937FileControlRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937FileControlRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937FileControlRecordImpl(ByteArray record, int stdLevel) {
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


    public String cashLetterCount() {
        return getFieldAsString(field(2));
    }

    public X937FileControlRecord cashLetterCount(String value) {
        setField(value, field(2));
        return this;
    }

    public int cashLetterCountAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(2));
    }

    public X937FileControlRecord cashLetterCount(int value) {
        setField(value, field(2));
        return this;
    }

    public String totalRecordCount() {
        return getFieldAsString(field(3));
    }

    public X937FileControlRecord totalRecordCount(String value) {
        setField(value, field(3));
        return this;
    }

    public int totalRecordCountAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(3));
    }

    public X937FileControlRecord totalRecordCount(int value) {
        setField(value, field(3));
        return this;
    }

    public String totalItemCount() {
        return getFieldAsString(field(4));
    }

    public X937FileControlRecord totalItemCount(String value) {
        setField(value, field(4));
        return this;
    }

    public int totalItemCountAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(4));
    }

    public X937FileControlRecord totalItemCount(int value) {
        setField(value, field(4));
        return this;
    }

    public String fileTotalAmount() {
        return getFieldAsString(field(5));
    }

    public X937FileControlRecord fileTotalAmount(String value) {
        setField(value, field(5));
        return this;
    }

    public long fileTotalAmountAsLong()
        throws InvalidDataException {
        return getFieldAsLong(field(5));
    }

    public X937FileControlRecord fileTotalAmount(long value) {
        setField(value, field(5));
        return this;
    }

    public String immediateOriginContactName() {
        return getFieldAsString(field(6));
    }

    public X937FileControlRecord immediateOriginContactName(String value) {
        setField(value, field(6));
        return this;
    }

    public String immediateOriginContactPhoneNumber() {
        return getFieldAsString(field(7));
    }

    public X937FileControlRecord immediateOriginContactPhoneNumber(String value) {
        setField(value, field(7));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(8));
    }

    public X937FileControlRecord reserved(String value) {
        setField(value, field(8));
        return this;
    }

}

