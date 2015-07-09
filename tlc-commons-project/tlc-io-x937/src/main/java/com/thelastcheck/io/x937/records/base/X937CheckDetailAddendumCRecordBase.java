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
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumCRecord;

import java.util.Date;

public abstract class X937CheckDetailAddendumCRecordBase extends X9RecordImpl
        implements X937CheckDetailAddendumCRecord {



    /*
     * X937CheckDetailAddendumCRecordBase
     */

    public X937CheckDetailAddendumCRecordBase() {
        super();
        recordType(TYPE_CHECK_DETAIL_ADDENDUM_C);
    }

    public X937CheckDetailAddendumCRecordBase(int stdLevel) {
        super(TYPE_CHECK_DETAIL_ADDENDUM_C, stdLevel);
    }

    public X937CheckDetailAddendumCRecordBase(String encoding, int stdLevel) {
        super(TYPE_CHECK_DETAIL_ADDENDUM_C, encoding, stdLevel);
    }

    public X937CheckDetailAddendumCRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String checkDetailAddendumCRecordNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord checkDetailAddendumCRecordNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public int checkDetailAddendumCRecordNumberAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord checkDetailAddendumCRecordNumber(int value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber endorsingBankRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord endorsingBankRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord endorsingBankRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date endorsingBankEndorsementDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord endorsingBankEndorsementDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankEndorsementDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord endorsingBankEndorsementDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankItemSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord endorsingBankItemSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String truncationIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord truncationIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankConversionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord endorsingBankConversionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankCorrectionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord endorsingBankCorrectionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String returnReason() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord returnReason(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumCRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

