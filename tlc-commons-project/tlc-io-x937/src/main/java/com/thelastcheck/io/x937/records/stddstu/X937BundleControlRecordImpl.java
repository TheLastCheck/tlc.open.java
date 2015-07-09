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
import com.thelastcheck.io.x937.records.X937BundleControlRecord;
import com.thelastcheck.io.x937.records.base.X937BundleControlRecordBase;

public class X937BundleControlRecordImpl extends X937BundleControlRecordBase {

    private static int maxFieldNumber = 7;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("ItemsWithinBundleCount", 2, 2, 4, FieldType.INT);
        fields[3] = new Field("BundleTotalAmount", 3, 6, 12, FieldType.LONG);
        fields[4] = new Field("MICRValidTotalAmount", 4, 18, 12, FieldType.LONG);
        fields[5] = new Field("ImagesWithinBundleCount", 5, 30, 5, FieldType.INT);
        fields[6] = new Field("UserField", 6, 35, 20, FieldType.STRING);
        fields[7] = new Field("Reserved", 7, 55, 25, FieldType.STRING);
    }


    /*
     * X937BundleControlRecordImpl
     */

    public X937BundleControlRecordImpl() {
        super();
    }

    public X937BundleControlRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937BundleControlRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937BundleControlRecordImpl(ByteArray record, int stdLevel) {
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


    public String itemsWithinBundleCount() {
        return getFieldAsString(field(2));
    }

    public X937BundleControlRecord itemsWithinBundleCount(String value) {
        setField(value, field(2));
        return this;
    }

    public int itemsWithinBundleCountAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(2));
    }

    public X937BundleControlRecord itemsWithinBundleCount(int value) {
        setField(value, field(2));
        return this;
    }

    public String bundleTotalAmount() {
        return getFieldAsString(field(3));
    }

    public X937BundleControlRecord bundleTotalAmount(String value) {
        setField(value, field(3));
        return this;
    }

    public long bundleTotalAmountAsLong()
        throws InvalidDataException {
        return getFieldAsLong(field(3));
    }

    public X937BundleControlRecord bundleTotalAmount(long value) {
        setField(value, field(3));
        return this;
    }

    public String MICRValidTotalAmount() {
        return getFieldAsString(field(4));
    }

    public X937BundleControlRecord MICRValidTotalAmount(String value) {
        setField(value, field(4));
        return this;
    }

    public long MICRValidTotalAmountAsLong()
        throws InvalidDataException {
        return getFieldAsLong(field(4));
    }

    public X937BundleControlRecord MICRValidTotalAmount(long value) {
        setField(value, field(4));
        return this;
    }

    public String imagesWithinBundleCount() {
        return getFieldAsString(field(5));
    }

    public X937BundleControlRecord imagesWithinBundleCount(String value) {
        setField(value, field(5));
        return this;
    }

    public int imagesWithinBundleCountAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(5));
    }

    public X937BundleControlRecord imagesWithinBundleCount(int value) {
        setField(value, field(5));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(6));
    }

    public X937BundleControlRecord userField(String value) {
        setField(value, field(6));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(7));
    }

    public X937BundleControlRecord reserved(String value) {
        setField(value, field(7));
        return this;
    }

}

