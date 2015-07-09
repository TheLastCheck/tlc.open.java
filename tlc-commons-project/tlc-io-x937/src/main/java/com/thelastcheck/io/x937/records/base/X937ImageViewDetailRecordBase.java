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

import com.thelastcheck.io.x937.records.X937ImageViewDetailRecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937ImageViewDetailRecordBase extends X9RecordImpl 
        implements X937ImageViewDetailRecord {



    /*
     * X937ImageViewDetailRecordBase
     */

    public X937ImageViewDetailRecordBase() {
        super();
        recordType(TYPE_IMAGE_VIEW_DETAIL);
    }

    public X937ImageViewDetailRecordBase(int stdLevel) {
        super(TYPE_IMAGE_VIEW_DETAIL, stdLevel);
    }

    public X937ImageViewDetailRecordBase(String encoding, int stdLevel) {
        super(TYPE_IMAGE_VIEW_DETAIL, encoding, stdLevel);
    }

    public X937ImageViewDetailRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String imageIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber imageCreatorRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageCreatorRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String imageCreatorRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageCreatorRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date imageCreatorDate()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageCreatorDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String imageCreatorDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageCreatorDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String imageViewFormatIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageViewFormatIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String imageViewCompressionAlgorithmIdentifier() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageViewCompressionAlgorithmIdentifier(String value) {
        throw new InvalidStandardLevelException();
    }

    public String imageViewDataSize() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageViewDataSize(String value) {
        throw new InvalidStandardLevelException();
    }

    public int imageViewDataSizeAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageViewDataSize(int value) {
        throw new InvalidStandardLevelException();
    }

    public String viewSideIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord viewSideIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String viewDescriptor() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord viewDescriptor(String value) {
        throw new InvalidStandardLevelException();
    }

    public String digitalSignatureIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord digitalSignatureIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String digitalSignatureMethod() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord digitalSignatureMethod(String value) {
        throw new InvalidStandardLevelException();
    }

    public String securityKeySize() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord securityKeySize(String value) {
        throw new InvalidStandardLevelException();
    }

    public int securityKeySizeAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord securityKeySize(int value) {
        throw new InvalidStandardLevelException();
    }

    public String startOfProtectedData() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord startOfProtectedData(String value) {
        throw new InvalidStandardLevelException();
    }

    public String lengthofProtectedData() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord lengthofProtectedData(String value) {
        throw new InvalidStandardLevelException();
    }

    public int lengthofProtectedDataAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord lengthofProtectedData(int value) {
        throw new InvalidStandardLevelException();
    }

    public String imageRecreateIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageRecreateIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String imageTiffVarianceIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord imageTiffVarianceIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String overrideIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord overrideIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewDetailRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

