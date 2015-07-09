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
import com.thelastcheck.io.x937.records.X937BundleHeaderRecord;
import com.thelastcheck.io.x937.records.base.X937BundleHeaderRecordBase;

public class X937BundleHeaderRecordImpl extends X937BundleHeaderRecordBase {

    private static int maxFieldNumber = 12;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("CollectionTypeIndicator", 2, 2, 2, FieldType.STRING);
        fields[3] = new Field("DestinationRoutingNumber", 3, 4, 9, FieldType.ROUTING_NUMBER);
        fields[4] = new Field("ECEInstitutionRoutingNumber", 4, 13, 9, FieldType.ROUTING_NUMBER);
        fields[5] = new Field("BundleBusinessDate", 5, 22, 8, FieldType.DATE);
        fields[6] = new Field("BundleCreationDate", 6, 30, 8, FieldType.DATE);
        fields[7] = new Field("BundleID", 7, 38, 10, FieldType.STRING);
        fields[8] = new Field("BundleSequenceNumber", 8, 48, 4, FieldType.STRING);
        fields[9] = new Field("CycleNumber", 9, 52, 2, FieldType.STRING);
        fields[10] = new Field("ReturnLocationRoutingNumber", 10, 54, 9, FieldType.ROUTING_NUMBER);
        fields[11] = new Field("UserField", 11, 63, 5, FieldType.STRING);
        fields[12] = new Field("Reserved", 12, 68, 12, FieldType.STRING);
    }


    /*
     * X937BundleHeaderRecordImpl
     */

    public X937BundleHeaderRecordImpl() {
        super();
    }

    public X937BundleHeaderRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937BundleHeaderRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937BundleHeaderRecordImpl(ByteArray record, int stdLevel) {
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

    public X937BundleHeaderRecord collectionTypeIndicator(String value) {
        setField(value, field(2));
        return this;
    }

    public RoutingNumber destinationRoutingNumber() {
        return getFieldAsRoutingNumber(field(3));
    }

    public X937BundleHeaderRecord destinationRoutingNumber(RoutingNumber value) {
        setField(value, field(3));
        return this;
    }

    public String destinationRoutingNumberAsString() {
        return getFieldAsString(field(3));
    }

    public X937BundleHeaderRecord destinationRoutingNumber(String value) {
        setField(value, field(3));
        return this;
    }

    public RoutingNumber ECEInstitutionRoutingNumber() {
        return getFieldAsRoutingNumber(field(4));
    }

    public X937BundleHeaderRecord ECEInstitutionRoutingNumber(RoutingNumber value) {
        setField(value, field(4));
        return this;
    }

    public String ECEInstitutionRoutingNumberAsString() {
        return getFieldAsString(field(4));
    }

    public X937BundleHeaderRecord ECEInstitutionRoutingNumber(String value) {
        setField(value, field(4));
        return this;
    }

    public Date bundleBusinessDate()
        throws InvalidDataException {
        return getFieldAsDate(field(5), x9TimeZone);
    }

    public X937BundleHeaderRecord bundleBusinessDate(Date value) {
        setFieldDate(value, field(5), x9TimeZone);        return this;
    }

    public String bundleBusinessDateAsString() {
        return getFieldAsString(field(5));
    }

    public X937BundleHeaderRecord bundleBusinessDate(String value) {
        setField(value, field(5));
        return this;
    }

    public Date bundleCreationDate()
        throws InvalidDataException {
        return getFieldAsDate(field(6), x9TimeZone);
    }

    public X937BundleHeaderRecord bundleCreationDate(Date value) {
        setFieldDate(value, field(6), x9TimeZone);        return this;
    }

    public String bundleCreationDateAsString() {
        return getFieldAsString(field(6));
    }

    public X937BundleHeaderRecord bundleCreationDate(String value) {
        setField(value, field(6));
        return this;
    }

    public String bundleID() {
        return getFieldAsString(field(7));
    }

    public X937BundleHeaderRecord bundleID(String value) {
        setField(value, field(7));
        return this;
    }

    public String bundleSequenceNumber() {
        return getFieldAsString(field(8));
    }

    public X937BundleHeaderRecord bundleSequenceNumber(String value) {
        setField(value, field(8));
        return this;
    }

    public String cycleNumber() {
        return getFieldAsString(field(9));
    }

    public X937BundleHeaderRecord cycleNumber(String value) {
        setField(value, field(9));
        return this;
    }

    public RoutingNumber returnLocationRoutingNumber() {
        return getFieldAsRoutingNumber(field(10));
    }

    public X937BundleHeaderRecord returnLocationRoutingNumber(RoutingNumber value) {
        setField(value, field(10));
        return this;
    }

    public String returnLocationRoutingNumberAsString() {
        return getFieldAsString(field(10));
    }

    public X937BundleHeaderRecord returnLocationRoutingNumber(String value) {
        setField(value, field(10));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(11));
    }

    public X937BundleHeaderRecord userField(String value) {
        setField(value, field(11));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(12));
    }

    public X937BundleHeaderRecord reserved(String value) {
        setField(value, field(12));
        return this;
    }

}

