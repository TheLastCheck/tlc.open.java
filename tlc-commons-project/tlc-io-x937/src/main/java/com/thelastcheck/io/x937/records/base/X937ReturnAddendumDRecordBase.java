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
import com.thelastcheck.io.x937.records.X937ReturnAddendumDRecord;

import java.util.Date;

public abstract class X937ReturnAddendumDRecordBase extends X9RecordImpl
        implements X937ReturnAddendumDRecord {



    /*
     * X937ReturnAddendumDRecordBase
     */

    public X937ReturnAddendumDRecordBase() {
        super();
        recordType(TYPE_RETURN_ADDENDUM_D);
    }

    public X937ReturnAddendumDRecordBase(int stdLevel) {
        super(TYPE_RETURN_ADDENDUM_D, stdLevel);
    }

    public X937ReturnAddendumDRecordBase(String encoding, int stdLevel) {
        super(TYPE_RETURN_ADDENDUM_D, encoding, stdLevel);
    }

    public X937ReturnAddendumDRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String returnAddendumDRecordNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord returnAddendumDRecordNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public int returnAddendumDRecordNumberAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord returnAddendumDRecordNumber(int value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber endorsingBankRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord endorsingBankRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord endorsingBankRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date endorsingBankEndorsementDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord endorsingBankEndorsementDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankEndorsementDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord endorsingBankEndorsementDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankItemSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord endorsingBankItemSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String truncationIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord truncationIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankConversionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord endorsingBankConversionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String endorsingBankCorrectionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord endorsingBankCorrectionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String returnReason() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord returnReason(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumDRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

