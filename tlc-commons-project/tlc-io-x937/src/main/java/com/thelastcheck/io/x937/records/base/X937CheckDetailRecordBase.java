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

import com.thelastcheck.io.x937.records.X937CheckDetailRecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937CheckDetailRecordBase extends X9RecordImpl 
        implements X937CheckDetailRecord {



    /*
     * X937CheckDetailRecordBase
     */

    public X937CheckDetailRecordBase() {
        super();
        recordType(TYPE_CHECK_DETAIL);
    }

    public X937CheckDetailRecordBase(int stdLevel) {
        super(TYPE_CHECK_DETAIL, stdLevel);
    }

    public X937CheckDetailRecordBase(String encoding, int stdLevel) {
        super(TYPE_CHECK_DETAIL, encoding, stdLevel);
    }

    public X937CheckDetailRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String auxiliaryOnUs() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord auxiliaryOnUs(String value) {
        throw new InvalidStandardLevelException();
    }

    public String externalProcessingCode() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord externalProcessingCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber payorBankRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord payorBankRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String payorBankRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord payorBankRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payorBankRoutingNumberCheckDigit() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord payorBankRoutingNumberCheckDigit(String value) {
        throw new InvalidStandardLevelException();
    }

    public OnUsField onUs() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord onUs(OnUsField value) {
        throw new InvalidStandardLevelException();
    }

    public String onUsAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord onUs(String value) {
        throw new InvalidStandardLevelException();
    }

    public String itemAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord itemAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long itemAmountAsLong()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord itemAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String ECEInstitutionItemSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord ECEInstitutionItemSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String documentationTypeIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord documentationTypeIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String returnAcceptanceIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord returnAcceptanceIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String MICRValidIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord MICRValidIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord BOFDIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String checkDetailRecordAddendumCount() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord checkDetailRecordAddendumCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int checkDetailRecordAddendumCountAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord checkDetailRecordAddendumCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String correctionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord correctionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String archiveTypeIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord archiveTypeIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String onusFormatIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord onusFormatIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

