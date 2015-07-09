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
import com.thelastcheck.io.x937.records.X937CashLetterHeaderRecord;
import com.thelastcheck.io.x937.records.base.X937CashLetterHeaderRecordBase;

import java.util.Date;

public class X937CashLetterHeaderRecordImpl extends X937CashLetterHeaderRecordBase {

    private static int maxFieldNumber = 14;
    private static Field fields[] = new Field[maxFieldNumber + 1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("CollectionTypeIndicator", 2, 2, 2, FieldType.STRING);
        fields[3] = new Field("DestinationRoutingNumber", 3, 4, 9, FieldType.ROUTING_NUMBER);
        fields[4] = new Field("ECEInstitutionRoutingNumber", 4, 13, 9, FieldType.ROUTING_NUMBER);
        fields[5] = new Field("CashLetterBusinessDate", 5, 22, 8, FieldType.DATE);
        fields[6] = new Field("CashLetterCreationDate", 6, 30, 8, FieldType.DATE);
        fields[7] = new Field("CashLetterCreationTime", 7, 38, 4, FieldType.TIME);
        fields[8] = new Field("EmptyCashLetterIndicator", 8, 42, 1, FieldType.STRING);
        fields[9] = new Field("TruncationIndicator", 9, 43, 1, FieldType.STRING);
        fields[10] = new Field("CashLetterID", 10, 44, 8, FieldType.STRING);
        fields[11] = new Field("OriginatorContactName", 11, 52, 14, FieldType.STRING);
        fields[12] = new Field("OriginatorContactPhoneNumber", 12, 66, 10, FieldType.STRING);
        fields[13] = new Field("UserField", 13, 76, 3, FieldType.STRING);
        fields[14] = new Field("Reserved", 14, 79, 1, FieldType.STRING);
    }


    /*
     * X937CashLetterHeaderRecordImpl
     */

    public X937CashLetterHeaderRecordImpl() {
        super();
    }

    public X937CashLetterHeaderRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937CashLetterHeaderRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937CashLetterHeaderRecordImpl(ByteArray record, int stdLevel) {
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


    public String collectionTypeIndicator() {
        return getFieldAsString(field(2));
    }

    public X937CashLetterHeaderRecord collectionTypeIndicator(String value) {
        setField(value, field(2));
        return this;
    }

    public RoutingNumber destinationRoutingNumber() {
        return getFieldAsRoutingNumber(field(3));
    }

    public X937CashLetterHeaderRecord destinationRoutingNumber(RoutingNumber value) {
        setField(value, field(3));
        return this;
    }

    public String destinationRoutingNumberAsString() {
        return getFieldAsString(field(3));
    }

    public X937CashLetterHeaderRecord destinationRoutingNumber(String value) {
        setField(value, field(3));
        return this;
    }

    public RoutingNumber ECEInstitutionRoutingNumber() {
        return getFieldAsRoutingNumber(field(4));
    }

    public X937CashLetterHeaderRecord ECEInstitutionRoutingNumber(RoutingNumber value) {
        setField(value, field(4));
        return this;
    }

    public String ECEInstitutionRoutingNumberAsString() {
        return getFieldAsString(field(4));
    }

    public X937CashLetterHeaderRecord ECEInstitutionRoutingNumber(String value) {
        setField(value, field(4));
        return this;
    }

    public Date cashLetterBusinessDate()
            throws InvalidDataException {
        return getFieldAsDate(field(5), x9TimeZone);
    }

    public X937CashLetterHeaderRecord cashLetterBusinessDate(Date value) {
        setFieldDate(value, field(5), x9TimeZone);
        return this;
    }

    public String cashLetterBusinessDateAsString() {
        return getFieldAsString(field(5));
    }

    public X937CashLetterHeaderRecord cashLetterBusinessDate(String value) {
        setField(value, field(5));
        return this;
    }

    public Date cashLetterCreationDate()
            throws InvalidDataException {
        return getFieldAsDate(field(6), x9TimeZone);
    }

    public X937CashLetterHeaderRecord cashLetterCreationDate(Date value) {
        setFieldDate(value, field(6), x9TimeZone);
        return this;
    }

    public String cashLetterCreationDateAsString() {
        return getFieldAsString(field(6));
    }

    public X937CashLetterHeaderRecord cashLetterCreationDate(String value) {
        setField(value, field(6));
        return this;
    }

    public Date cashLetterCreationTime()
            throws InvalidDataException {
        return getFieldAsTime(field(7), x9TimeZone);
    }

    public X937CashLetterHeaderRecord cashLetterCreationTime(Date value) {
        setFieldTime(value, field(7), x9TimeZone);
        return this;
    }

    public String cashLetterCreationTimeAsString() {
        return getFieldAsString(field(7));
    }

    public X937CashLetterHeaderRecord cashLetterCreationTime(String value) {
        setField(value, field(7));
        return this;
    }

    public String emptyCashLetterIndicator() {
        return getFieldAsString(field(8));
    }

    public X937CashLetterHeaderRecord emptyCashLetterIndicator(String value) {
        setField(value, field(8));
        return this;
    }

    public String truncationIndicator() {
        return getFieldAsString(field(9));
    }

    public X937CashLetterHeaderRecord truncationIndicator(String value) {
        setField(value, field(9));
        return this;
    }

    public String cashLetterID() {
        return getFieldAsString(field(10));
    }

    public X937CashLetterHeaderRecord cashLetterID(String value) {
        setField(value, field(10));
        return this;
    }

    public String originatorContactName() {
        return getFieldAsString(field(11));
    }

    public X937CashLetterHeaderRecord originatorContactName(String value) {
        setField(value, field(11));
        return this;
    }

    public String originatorContactPhoneNumber() {
        return getFieldAsString(field(12));
    }

    public X937CashLetterHeaderRecord originatorContactPhoneNumber(String value) {
        setField(value, field(12));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(13));
    }

    public X937CashLetterHeaderRecord userField(String value) {
        setField(value, field(13));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(14));
    }

    public X937CashLetterHeaderRecord reserved(String value) {
        setField(value, field(14));
        return this;
    }

}

