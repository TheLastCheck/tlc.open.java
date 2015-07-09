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

package com.thelastcheck.io.x937.records.base;

import java.util.Date;

import com.thelastcheck.io.x937.records.X937ImageViewDataRecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937ImageViewDataRecordBase extends X9RecordImpl 
        implements X937ImageViewDataRecord {



    /*
     * X937ImageViewDataRecordBase
     */

    public X937ImageViewDataRecordBase() {
        super();
        recordType(TYPE_IMAGE_VIEW_DATA);
    }

    public X937ImageViewDataRecordBase(int stdLevel) {
        super(TYPE_IMAGE_VIEW_DATA, stdLevel);
    }

    public X937ImageViewDataRecordBase(String encoding, int stdLevel) {
        super(TYPE_IMAGE_VIEW_DATA, encoding, stdLevel);
    }

    public X937ImageViewDataRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public RoutingNumber ECEInstitutionRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord ECEInstitutionRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String ECEInstitutionRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord ECEInstitutionRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date bundleBusinessDate()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord bundleBusinessDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String bundleBusinessDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord bundleBusinessDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String cycleNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord cycleNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String ECEInstitutionItemSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord ECEInstitutionItemSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String securityOriginatorName() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord securityOriginatorName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String securityAuthenticatorName() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord securityAuthenticatorName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String securityKeyName() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord securityKeyName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String clippingOrigin() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord clippingOrigin(String value) {
        throw new InvalidStandardLevelException();
    }

    public String clippingCoordinateH1() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord clippingCoordinateH1(String value) {
        throw new InvalidStandardLevelException();
    }

    public String clippingCoordinateH2() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord clippingCoordinateH2(String value) {
        throw new InvalidStandardLevelException();
    }

    public String clippingCoordinateV1() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord clippingCoordinateV1(String value) {
        throw new InvalidStandardLevelException();
    }

    public String clippingCoordinateV2() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord clippingCoordinateV2(String value) {
        throw new InvalidStandardLevelException();
    }

    public String lengthOfImageReferenceKey() {
        throw new InvalidStandardLevelException();
    }


    public int lengthOfImageReferenceKeyAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }


    public String imageReferenceKey() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord imageReferenceKey(String value) {
        throw new InvalidStandardLevelException();
    }

    public String lengthOfDigitalSignature() {
        throw new InvalidStandardLevelException();
    }


    public int lengthOfDigitalSignatureAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }


    public ByteArray digitalSignature() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord digitalSignature(ByteArray value) {
        throw new InvalidStandardLevelException();
    }

    public String lengthOfImageData() {
        throw new InvalidStandardLevelException();
    }


    public int lengthOfImageDataAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }


    public ByteArray imageData() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDataRecord imageData(ByteArray value) {
        throw new InvalidStandardLevelException();
    }

}

