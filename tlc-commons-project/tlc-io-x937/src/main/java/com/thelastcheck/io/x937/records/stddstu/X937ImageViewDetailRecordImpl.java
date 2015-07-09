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
import com.thelastcheck.io.x937.records.X937ImageViewDetailRecord;
import com.thelastcheck.io.x937.records.base.X937ImageViewDetailRecordBase;

public class X937ImageViewDetailRecordImpl extends X937ImageViewDetailRecordBase {

    private static int maxFieldNumber = 19;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("ImageIndicator", 2, 2, 1, FieldType.STRING);
        fields[3] = new Field("ImageCreatorRoutingNumber", 3, 3, 9, FieldType.ROUTING_NUMBER);
        fields[4] = new Field("ImageCreatorDate", 4, 12, 8, FieldType.DATE);
        fields[5] = new Field("ImageViewFormatIndicator", 5, 20, 2, FieldType.STRING);
        fields[6] = new Field("ImageViewCompressionAlgorithmIdentifier", 6, 22, 2, FieldType.STRING);
        fields[7] = new Field("ImageViewDataSize", 7, 24, 7, FieldType.INT);
        fields[8] = new Field("ViewSideIndicator", 8, 31, 1, FieldType.STRING);
        fields[9] = new Field("ViewDescriptor", 9, 32, 2, FieldType.STRING);
        fields[10] = new Field("DigitalSignatureIndicator", 10, 34, 1, FieldType.STRING);
        fields[11] = new Field("DigitalSignatureMethod", 11, 35, 2, FieldType.STRING);
        fields[12] = new Field("SecurityKeySize", 12, 37, 5, FieldType.INT);
        fields[13] = new Field("StartOfProtectedData", 13, 42, 7, FieldType.STRING);
        fields[14] = new Field("LengthofProtectedData", 14, 49, 7, FieldType.INT);
        fields[15] = new Field("ImageRecreateIndicator", 15, 56, 1, FieldType.STRING);
        fields[16] = new Field("UserField", 16, 57, 8, FieldType.STRING);
        fields[17] = new Field("ImageTiffVarianceIndicator", 17, 65, 1, FieldType.STRING);
        fields[18] = new Field("OverrideIndicator", 18, 66, 1, FieldType.STRING);
        fields[19] = new Field("Reserved", 19, 67, 13, FieldType.STRING);
    }


    /*
     * X937ImageViewDetailRecordImpl
     */

    public X937ImageViewDetailRecordImpl() {
        super();
    }

    public X937ImageViewDetailRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937ImageViewDetailRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937ImageViewDetailRecordImpl(ByteArray record, int stdLevel) {
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


    public String imageIndicator() {
        return getFieldAsString(field(2));
    }

    public X937ImageViewDetailRecord imageIndicator(String value) {
        setField(value, field(2));
        return this;
    }

    public RoutingNumber imageCreatorRoutingNumber() {
        return getFieldAsRoutingNumber(field(3));
    }

    public X937ImageViewDetailRecord imageCreatorRoutingNumber(RoutingNumber value) {
        setField(value, field(3));
        return this;
    }

    public String imageCreatorRoutingNumberAsString() {
        return getFieldAsString(field(3));
    }

    public X937ImageViewDetailRecord imageCreatorRoutingNumber(String value) {
        setField(value, field(3));
        return this;
    }

    public Date imageCreatorDate()
        throws InvalidDataException {
        return getFieldAsDate(field(4), x9TimeZone);
    }

    public X937ImageViewDetailRecord imageCreatorDate(Date value) {
        setFieldDate(value, field(4), x9TimeZone);        return this;
    }

    public String imageCreatorDateAsString() {
        return getFieldAsString(field(4));
    }

    public X937ImageViewDetailRecord imageCreatorDate(String value) {
        setField(value, field(4));
        return this;
    }

    public String imageViewFormatIndicator() {
        return getFieldAsString(field(5));
    }

    public X937ImageViewDetailRecord imageViewFormatIndicator(String value) {
        setField(value, field(5));
        return this;
    }

    public String imageViewCompressionAlgorithmIdentifier() {
        return getFieldAsString(field(6));
    }

    public X937ImageViewDetailRecord imageViewCompressionAlgorithmIdentifier(String value) {
        setField(value, field(6));
        return this;
    }

    public String imageViewDataSize() {
        return getFieldAsString(field(7));
    }

    public X937ImageViewDetailRecord imageViewDataSize(String value) {
        setField(value, field(7));
        return this;
    }

    public int imageViewDataSizeAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(7));
    }

    public X937ImageViewDetailRecord imageViewDataSize(int value) {
        setField(value, field(7));
        return this;
    }

    public String viewSideIndicator() {
        return getFieldAsString(field(8));
    }

    public X937ImageViewDetailRecord viewSideIndicator(String value) {
        setField(value, field(8));
        return this;
    }

    public String viewDescriptor() {
        return getFieldAsString(field(9));
    }

    public X937ImageViewDetailRecord viewDescriptor(String value) {
        setField(value, field(9));
        return this;
    }

    public String digitalSignatureIndicator() {
        return getFieldAsString(field(10));
    }

    public X937ImageViewDetailRecord digitalSignatureIndicator(String value) {
        setField(value, field(10));
        return this;
    }

    public String digitalSignatureMethod() {
        return getFieldAsString(field(11));
    }

    public X937ImageViewDetailRecord digitalSignatureMethod(String value) {
        setField(value, field(11));
        return this;
    }

    public String securityKeySize() {
        return getFieldAsString(field(12));
    }

    public X937ImageViewDetailRecord securityKeySize(String value) {
        setField(value, field(12));
        return this;
    }

    public int securityKeySizeAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(12));
    }

    public X937ImageViewDetailRecord securityKeySize(int value) {
        setField(value, field(12));
        return this;
    }

    public String startOfProtectedData() {
        return getFieldAsString(field(13));
    }

    public X937ImageViewDetailRecord startOfProtectedData(String value) {
        setField(value, field(13));
        return this;
    }

    public String lengthofProtectedData() {
        return getFieldAsString(field(14));
    }

    public X937ImageViewDetailRecord lengthofProtectedData(String value) {
        setField(value, field(14));
        return this;
    }

    public int lengthofProtectedDataAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(14));
    }

    public X937ImageViewDetailRecord lengthofProtectedData(int value) {
        setField(value, field(14));
        return this;
    }

    public String imageRecreateIndicator() {
        return getFieldAsString(field(15));
    }

    public X937ImageViewDetailRecord imageRecreateIndicator(String value) {
        setField(value, field(15));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(16));
    }

    public X937ImageViewDetailRecord userField(String value) {
        setField(value, field(16));
        return this;
    }

    public String imageTiffVarianceIndicator() {
        return getFieldAsString(field(17));
    }

    public X937ImageViewDetailRecord imageTiffVarianceIndicator(String value) {
        setField(value, field(17));
        return this;
    }

    public String overrideIndicator() {
        return getFieldAsString(field(18));
    }

    public X937ImageViewDetailRecord overrideIndicator(String value) {
        setField(value, field(18));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(19));
    }

    public X937ImageViewDetailRecord reserved(String value) {
        setField(value, field(19));
        return this;
    }

}

