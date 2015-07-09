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

package com.thelastcheck.io.x937.records.std2001;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937CashLetterControlRecord;
import com.thelastcheck.io.x937.records.base.X937CashLetterControlRecordBase;

import java.util.Date;

public class X937CashLetterControlRecordImpl extends X937CashLetterControlRecordBase {

    private static int maxFieldNumber = 8;
    private static Field fields[] = new Field[maxFieldNumber + 1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("BundleCount", 2, 2, 6, FieldType.INT);
        fields[3] = new Field("ItemsWithinCashletterCount", 3, 8, 8, FieldType.INT);
        fields[4] = new Field("CashLetterTotalAmount", 4, 16, 14, FieldType.LONG);
        fields[5] = new Field("FinalDestinationName", 5, 30, 18, FieldType.LONG);
        fields[6] = new Field("ECEInstitutionName", 6, 48, 18, FieldType.STRING);
        fields[7] = new Field("SettlementDate", 7, 66, 8, FieldType.DATE);
        fields[8] = new Field("Reserved", 8, 74, 6, FieldType.STRING);
    }


    /*
     * X937CashLetterControlRecordImpl
     */

    public X937CashLetterControlRecordImpl() {
        super();
    }

    public X937CashLetterControlRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937CashLetterControlRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937CashLetterControlRecordImpl(ByteArray record, int stdLevel) {
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


    public String bundleCount() {
        return getFieldAsString(field(2));
    }

    public X937CashLetterControlRecord bundleCount(String value) {
        setField(value, field(2));
        return this;
    }

    public int bundleCountAsInt()
            throws InvalidDataException {
        return getFieldAsInt(field(2));
    }

    public X937CashLetterControlRecord bundleCount(int value) {
        setField(value, field(2));
        return this;
    }

    public String itemsWithinCashletterCount() {
        return getFieldAsString(field(3));
    }

    public X937CashLetterControlRecord itemsWithinCashletterCount(String value) {
        setField(value, field(3));
        return this;
    }

    public int itemsWithinCashletterCountAsInt()
            throws InvalidDataException {
        return getFieldAsInt(field(3));
    }

    public X937CashLetterControlRecord itemsWithinCashletterCount(int value) {
        setField(value, field(3));
        return this;
    }

    public String cashLetterTotalAmount() {
        return getFieldAsString(field(4));
    }

    public X937CashLetterControlRecord cashLetterTotalAmount(String value) {
        setField(value, field(4));
        return this;
    }

    public long cashLetterTotalAmountAsLong()
            throws InvalidDataException {
        return getFieldAsLong(field(4));
    }

    public X937CashLetterControlRecord cashLetterTotalAmount(long value) {
        setField(value, field(4));
        return this;
    }

    public String finalDestinationName() {
        return getFieldAsString(field(5));
    }

    public X937CashLetterControlRecord finalDestinationName(String value) {
        setField(value, field(5));
        return this;
    }

    public long finalDestinationNameAsLong()
            throws InvalidDataException {
        return getFieldAsLong(field(5));
    }

    public X937CashLetterControlRecord finalDestinationName(long value) {
        setField(value, field(5));
        return this;
    }

    public String ECEInstitutionName() {
        return getFieldAsString(field(6));
    }

    public X937CashLetterControlRecord ECEInstitutionName(String value) {
        setField(value, field(6));
        return this;
    }

    public Date settlementDate()
            throws InvalidDataException {
        return getFieldAsDate(field(7), x9TimeZone);
    }

    public X937CashLetterControlRecord settlementDate(Date value) {
        setFieldDate(value, field(7), x9TimeZone);
        return this;
    }

    public String settlementDateAsString() {
        return getFieldAsString(field(7));
    }

    public X937CashLetterControlRecord settlementDate(String value) {
        setField(value, field(7));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(8));
    }

    public X937CashLetterControlRecord reserved(String value) {
        setField(value, field(8));
        return this;
    }

}

