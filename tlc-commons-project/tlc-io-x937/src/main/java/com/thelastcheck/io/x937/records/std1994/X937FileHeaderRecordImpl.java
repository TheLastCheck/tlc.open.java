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

package com.thelastcheck.io.x937.records.std1994;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;
import com.thelastcheck.io.x937.records.base.X937FileHeaderRecordBase;

import java.util.Date;

public class X937FileHeaderRecordImpl extends X937FileHeaderRecordBase {

    private static int maxFieldNumber = 14;
    private static Field fields[] = new Field[maxFieldNumber + 1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("StandardLevel", 2, 2, 2, FieldType.STRING);
        fields[3] = new Field("TestFileIndicator", 3, 4, 1, FieldType.STRING);
        fields[4] = new Field("ImmediateDestinationRoutingNumber", 4, 5, 9, FieldType.ROUTING_NUMBER);
        fields[5] = new Field("ImmediateOriginRoutingNumber", 5, 14, 9, FieldType.ROUTING_NUMBER);
        fields[6] = new Field("FileCreationDate", 6, 23, 8, FieldType.DATE);
        fields[7] = new Field("FileCreationTime", 7, 31, 4, FieldType.TIME);
        fields[8] = new Field("ResendIndicator", 8, 35, 1, FieldType.STRING);
        fields[9] = new Field("ImmediateDestinationName", 9, 36, 18, FieldType.STRING);
        fields[10] = new Field("ImmediateOriginName", 10, 54, 18, FieldType.STRING);
        fields[11] = new Field("FileIDModifier", 11, 72, 1, FieldType.STRING);
        fields[12] = new Field("CountryCode", 12, 73, 2, FieldType.STRING);
        fields[13] = new Field("UserField", 13, 75, 4, FieldType.STRING);
        fields[14] = new Field("Reserved", 14, 79, 1, FieldType.STRING);
    }


    /*
     * X937FileHeaderRecordImpl
     */

    public X937FileHeaderRecordImpl() {
        super();
    }

    public X937FileHeaderRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937FileHeaderRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937FileHeaderRecordImpl(ByteArray record, int stdLevel) {
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


    public String standardLevel() {
        return getFieldAsString(field(2));
    }

    public X937FileHeaderRecord standardLevel(String value) {
        setField(value, field(2));
        return this;
    }

    public String testFileIndicator() {
        return getFieldAsString(field(3));
    }

    public X937FileHeaderRecord testFileIndicator(String value) {
        setField(value, field(3));
        return this;
    }

    public RoutingNumber immediateDestinationRoutingNumber() {
        return getFieldAsRoutingNumber(field(4));
    }

    public X937FileHeaderRecord immediateDestinationRoutingNumber(RoutingNumber value) {
        setField(value, field(4));
        return this;
    }

    public String immediateDestinationRoutingNumberAsString() {
        return getFieldAsString(field(4));
    }

    public X937FileHeaderRecord immediateDestinationRoutingNumber(String value) {
        setField(value, field(4));
        return this;
    }

    public RoutingNumber immediateOriginRoutingNumber() {
        return getFieldAsRoutingNumber(field(5));
    }

    public X937FileHeaderRecord immediateOriginRoutingNumber(RoutingNumber value) {
        setField(value, field(5));
        return this;
    }

    public String immediateOriginRoutingNumberAsString() {
        return getFieldAsString(field(5));
    }

    public X937FileHeaderRecord immediateOriginRoutingNumber(String value) {
        setField(value, field(5));
        return this;
    }

    public Date fileCreationDate()
            throws InvalidDataException {
        return getFieldAsDate(field(6), x9TimeZone);
    }

    public X937FileHeaderRecord fileCreationDate(Date value) {
        setFieldDate(value, field(6), x9TimeZone);
        return this;
    }

    public String fileCreationDateAsString() {
        return getFieldAsString(field(6));
    }

    public X937FileHeaderRecord fileCreationDate(String value) {
        setField(value, field(6));
        return this;
    }

    public Date fileCreationTime()
            throws InvalidDataException {
        return getFieldAsTime(field(7), x9TimeZone);
    }

    public X937FileHeaderRecord fileCreationTime(Date value) {
        setFieldTime(value, field(7), x9TimeZone);
        return this;
    }

    public String fileCreationTimeAsString() {
        return getFieldAsString(field(7));
    }

    public X937FileHeaderRecord fileCreationTime(String value) {
        setField(value, field(7));
        return this;
    }

    public String resendIndicator() {
        return getFieldAsString(field(8));
    }

    public X937FileHeaderRecord resendIndicator(String value) {
        setField(value, field(8));
        return this;
    }

    public String immediateDestinationName() {
        return getFieldAsString(field(9));
    }

    public X937FileHeaderRecord immediateDestinationName(String value) {
        setField(value, field(9));
        return this;
    }

    public String immediateOriginName() {
        return getFieldAsString(field(10));
    }

    public X937FileHeaderRecord immediateOriginName(String value) {
        setField(value, field(10));
        return this;
    }

    public String fileIDModifier() {
        return getFieldAsString(field(11));
    }

    public X937FileHeaderRecord fileIDModifier(String value) {
        setField(value, field(11));
        return this;
    }

    public String countryCode() {
        return getFieldAsString(field(12));
    }

    public X937FileHeaderRecord countryCode(String value) {
        setField(value, field(12));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(13));
    }

    public X937FileHeaderRecord userField(String value) {
        setField(value, field(13));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(14));
    }

    public X937FileHeaderRecord reserved(String value) {
        setField(value, field(14));
        return this;
    }

}

