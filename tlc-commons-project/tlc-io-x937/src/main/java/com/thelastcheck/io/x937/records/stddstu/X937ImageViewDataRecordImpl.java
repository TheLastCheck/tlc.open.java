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
import com.thelastcheck.io.x937.records.X937ImageViewDataRecord;
import com.thelastcheck.io.x937.records.base.X937ImageViewDataRecordBase;

public class X937ImageViewDataRecordImpl extends X937ImageViewDataRecordBase {

    private static int maxFieldNumber = 19;
    private static Field fields[] = new Field[maxFieldNumber+1];
    private Field localFieldCache[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("ECEInstitutionRoutingNumber", 2, 2, 9, FieldType.ROUTING_NUMBER);
        fields[3] = new Field("BundleBusinessDate", 3, 11, 8, FieldType.DATE);
        fields[4] = new Field("CycleNumber", 4, 19, 2, FieldType.STRING);
        fields[5] = new Field("ECEInstitutionItemSequenceNumber", 5, 21, 15, FieldType.STRING);
        fields[6] = new Field("SecurityOriginatorName", 6, 36, 16, FieldType.STRING);
        fields[7] = new Field("SecurityAuthenticatorName", 7, 52, 16, FieldType.STRING);
        fields[8] = new Field("SecurityKeyName", 8, 68, 16, FieldType.STRING);
        fields[9] = new Field("ClippingOrigin", 9, 84, 1, FieldType.STRING);
        fields[10] = new Field("ClippingCoordinateH1", 10, 85, 4, FieldType.STRING);
        fields[11] = new Field("ClippingCoordinateH2", 11, 89, 4, FieldType.STRING);
        fields[12] = new Field("ClippingCoordinateV1", 12, 93, 4, FieldType.STRING);
        fields[13] = new Field("ClippingCoordinateV2", 13, 97, 4, FieldType.STRING);
        fields[14] = new Field("LengthOfImageReferenceKey", 14, 101, 4, FieldType.INT);
        fields[15] = null;
        fields[16] = null;
        fields[17] = null;
        fields[18] = null;
        fields[19] = null;
    }


    /*
     * X937ImageViewDataRecordImpl
     */

    public X937ImageViewDataRecordImpl() {
        super();
    }

    public X937ImageViewDataRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937ImageViewDataRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937ImageViewDataRecordImpl(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    @Override
    protected void resetDynamicFields() {
        localFieldCache[15] = null;
        localFieldCache[16] = null;
        localFieldCache[17] = null;
        localFieldCache[18] = null;
        localFieldCache[19] = null;
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
        if (localFieldCache[fieldNumber] == null) {
            switch (fieldNumber) {
            case 15:
                localFieldCache[15] = calculateFieldImageReferenceKey();
                break;
            case 16:
                localFieldCache[16] = calculateFieldLengthOfDigitalSignature();
                break;
            case 17:
                localFieldCache[17] = calculateFieldDigitalSignature();
                break;
            case 18:
                localFieldCache[18] = calculateFieldLengthOfImageData();
                break;
            case 19:
                localFieldCache[19] = calculateFieldImageData();
                break;
            default:
                localFieldCache[fieldNumber] = fields[fieldNumber];
            }
        }
        return localFieldCache[fieldNumber];
    }

    private Field calculateFieldImageReferenceKey() {
        // following is code for dynamically building field
        int X = 0;
        try {
            X = lengthOfImageReferenceKeyAsInt();
        } catch (InvalidDataException e) {
        }
        return new Field("ImageReferenceKey", 15, 105, X, FieldType.STRING);
        // above is code for dynamically building field
    }

    private Field calculateFieldLengthOfDigitalSignature() {
        // following is code for dynamically building field
        int X = 0;
        try {
            X = lengthOfImageReferenceKeyAsInt();
        } catch (InvalidDataException e) {
        }
        return new Field("LengthOfDigitalSignature", 16, 105+X, 5, FieldType.INT);
        // above is code for dynamically building field
    }

    private Field calculateFieldDigitalSignature() {
        // following is code for dynamically building field
        int X = 0;
        int Y = 0;
        try {
            X = lengthOfImageReferenceKeyAsInt();
        } catch (InvalidDataException e) {
        }
        try {
            Y = lengthOfDigitalSignatureAsInt();
        } catch (InvalidDataException e) {
        }
        return new Field("DigitalSignature", 17, 110+X, Y, FieldType.BINARY);
        // above is code for dynamically building field
    }

    private Field calculateFieldLengthOfImageData() {
        // following is code for dynamically building field
        int X = 0;
        int Y = 0;
        try {
            X = lengthOfImageReferenceKeyAsInt();
        } catch (InvalidDataException e) {
        }
        try {
            Y = lengthOfDigitalSignatureAsInt();
        } catch (InvalidDataException e) {
        }
        return new Field("LengthOfImageData", 18, 110+X+Y, 7, FieldType.INT);
        // above is code for dynamically building field
    }

    private Field calculateFieldImageData() {
        // following is code for dynamically building field
        int X = 0;
        int Y = 0;
        int Z = 0;
        try {
            X = lengthOfImageReferenceKeyAsInt();
        } catch (InvalidDataException e) {
        }
        try {
            Y = lengthOfDigitalSignatureAsInt();
        } catch (InvalidDataException e) {
        }
        try {
            Z = lengthOfImageDataAsInt();
        } catch (InvalidDataException e) {
        }
        return new Field("ImageData", 19, 117+X+Y, Z, FieldType.BINARY);
        // above is code for dynamically building field
    }


    public RoutingNumber ECEInstitutionRoutingNumber() {
        return getFieldAsRoutingNumber(field(2));
    }

    public X937ImageViewDataRecord ECEInstitutionRoutingNumber(RoutingNumber value) {
        setField(value, field(2));
        return this;
    }

    public String ECEInstitutionRoutingNumberAsString() {
        return getFieldAsString(field(2));
    }

    public X937ImageViewDataRecord ECEInstitutionRoutingNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public Date bundleBusinessDate()
        throws InvalidDataException {
        return getFieldAsDate(field(3), x9TimeZone);
    }

    public X937ImageViewDataRecord bundleBusinessDate(Date value) {
        setFieldDate(value, field(3), x9TimeZone);        return this;
    }

    public String bundleBusinessDateAsString() {
        return getFieldAsString(field(3));
    }

    public X937ImageViewDataRecord bundleBusinessDate(String value) {
        setField(value, field(3));
        return this;
    }

    public String cycleNumber() {
        return getFieldAsString(field(4));
    }

    public X937ImageViewDataRecord cycleNumber(String value) {
        setField(value, field(4));
        return this;
    }

    public String ECEInstitutionItemSequenceNumber() {
        return getFieldAsString(field(5));
    }

    public X937ImageViewDataRecord ECEInstitutionItemSequenceNumber(String value) {
        setField(value, field(5));
        return this;
    }

    public String securityOriginatorName() {
        return getFieldAsString(field(6));
    }

    public X937ImageViewDataRecord securityOriginatorName(String value) {
        setField(value, field(6));
        return this;
    }

    public String securityAuthenticatorName() {
        return getFieldAsString(field(7));
    }

    public X937ImageViewDataRecord securityAuthenticatorName(String value) {
        setField(value, field(7));
        return this;
    }

    public String securityKeyName() {
        return getFieldAsString(field(8));
    }

    public X937ImageViewDataRecord securityKeyName(String value) {
        setField(value, field(8));
        return this;
    }

    public String clippingOrigin() {
        return getFieldAsString(field(9));
    }

    public X937ImageViewDataRecord clippingOrigin(String value) {
        setField(value, field(9));
        return this;
    }

    public String clippingCoordinateH1() {
        return getFieldAsString(field(10));
    }

    public X937ImageViewDataRecord clippingCoordinateH1(String value) {
        setField(value, field(10));
        return this;
    }

    public String clippingCoordinateH2() {
        return getFieldAsString(field(11));
    }

    public X937ImageViewDataRecord clippingCoordinateH2(String value) {
        setField(value, field(11));
        return this;
    }

    public String clippingCoordinateV1() {
        return getFieldAsString(field(12));
    }

    public X937ImageViewDataRecord clippingCoordinateV1(String value) {
        setField(value, field(12));
        return this;
    }

    public String clippingCoordinateV2() {
        return getFieldAsString(field(13));
    }

    public X937ImageViewDataRecord clippingCoordinateV2(String value) {
        setField(value, field(13));
        return this;
    }

    public String lengthOfImageReferenceKey() {
        return getFieldAsString(field(14));
    }

    private X937ImageViewDataRecord lengthOfImageReferenceKey(String value) {
        setField(value, field(14));
        return this;
    }

    public int lengthOfImageReferenceKeyAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(14));
    }

    private X937ImageViewDataRecord lengthOfImageReferenceKey(int value) {
        setField(value, field(14));
        return this;
    }

    public String imageReferenceKey() {
        return getFieldAsString(field(15));
    }

    public X937ImageViewDataRecord imageReferenceKey(String value) {
        int currentLength = 0;
        try {
            currentLength = lengthOfImageReferenceKeyAsInt();
        } catch (InvalidDataException e) {
        }
        if (currentLength != value.length()) {
            allocateNewRecord(currentLength, value.length(), 14, 15);
        }
        setField(value, field(15));
        lengthOfImageReferenceKey(value.length());
        return this;
    }

    public String lengthOfDigitalSignature() {
        return getFieldAsString(field(16));
    }

    private X937ImageViewDataRecord lengthOfDigitalSignature(String value) {
        setField(value, field(16));
        return this;
    }

    public int lengthOfDigitalSignatureAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(16));
    }

    private X937ImageViewDataRecord lengthOfDigitalSignature(int value) {
        setField(value, field(16));
        return this;
    }

    public ByteArray digitalSignature() {
        return getFieldAsByteArray(field(17));
    }

    public X937ImageViewDataRecord digitalSignature(ByteArray value) {
        int currentLength = 0;
        try {
            currentLength = lengthOfDigitalSignatureAsInt();
        } catch (InvalidDataException e) {
        }
        if (currentLength != value.getLength()) {
            allocateNewRecord(currentLength, value.getLength(), 16, 17);
        }
        setField(value, field(17));
        lengthOfDigitalSignature(value.getLength());
        return this;
    }

    public String lengthOfImageData() {
        return getFieldAsString(field(18));
    }

    private X937ImageViewDataRecord lengthOfImageData(String value) {
        setField(value, field(18));
        return this;
    }

    public int lengthOfImageDataAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(18));
    }

    private X937ImageViewDataRecord lengthOfImageData(int value) {
        setField(value, field(18));
        return this;
    }

    public ByteArray imageData() {
        return getFieldAsByteArray(field(19));
    }

    public X937ImageViewDataRecord imageData(ByteArray value) {
        int currentLength = 0;
        try {
            currentLength = lengthOfImageDataAsInt();
        } catch (InvalidDataException e) {
        }
        if (currentLength != value.getLength()) {
            allocateNewRecord(currentLength, value.getLength(), 18, 19);
        }
        setField(value, field(19));
        lengthOfImageData(value.getLength());
        return this;
    }

}

