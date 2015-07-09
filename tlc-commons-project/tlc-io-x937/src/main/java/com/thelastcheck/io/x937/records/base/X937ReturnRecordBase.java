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

package com.thelastcheck.io.x937.records.base;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;
import com.thelastcheck.io.x937.records.X937ReturnRecord;

import java.util.Date;

public abstract class X937ReturnRecordBase extends X9RecordImpl
        implements X937ReturnRecord {



    /*
     * X937ReturnRecordBase
     */

    public X937ReturnRecordBase() {
        super();
        recordType(TYPE_RETURN);
    }

    public X937ReturnRecordBase(int stdLevel) {
        super(TYPE_RETURN, stdLevel);
    }

    public X937ReturnRecordBase(String encoding, int stdLevel) {
        super(TYPE_RETURN, encoding, stdLevel);
    }

    public X937ReturnRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public RoutingNumber payorBankRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord payorBankRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String payorBankRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord payorBankRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payorBankRoutingNumberCheckDigit() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord payorBankRoutingNumberCheckDigit(String value) {
        throw new InvalidStandardLevelException();
    }

    public OnUsField onUsReturnRecord() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord onUsReturnRecord(OnUsField value) {
        throw new InvalidStandardLevelException();
    }

    public String onUsReturnRecordAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord onUsReturnRecord(String value) {
        throw new InvalidStandardLevelException();
    }

    public String itemAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord itemAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long itemAmountAsLong()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord itemAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String returnReason() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord returnReason(String value) {
        throw new InvalidStandardLevelException();
    }

    public String returnRecordAddendumCount() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord returnRecordAddendumCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int returnRecordAddendumCountAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord returnRecordAddendumCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String returnDocumentationTypeIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord returnDocumentationTypeIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date forwardBundleDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord forwardBundleDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String forwardBundleDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord forwardBundleDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String ECEInstitutionItemSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord ECEInstitutionItemSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String externalProcessingCode() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord externalProcessingCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public String returnNotificationIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord returnNotificationIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String returnArchiveTypeIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord returnArchiveTypeIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String numberOfTimesReturned() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord numberOfTimesReturned(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payorAccountName() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord payorAccountName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

}

