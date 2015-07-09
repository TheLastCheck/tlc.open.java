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
import com.thelastcheck.io.x937.records.X937ReturnAddendumARecord;

import java.util.Date;

public abstract class X937ReturnAddendumARecordBase extends X9RecordImpl
        implements X937ReturnAddendumARecord {



    /*
     * X937ReturnAddendumARecordBase
     */

    public X937ReturnAddendumARecordBase() {
        super();
        recordType(TYPE_RETURN_ADDENDUM_A);
    }

    public X937ReturnAddendumARecordBase(int stdLevel) {
        super(TYPE_RETURN_ADDENDUM_A, stdLevel);
    }

    public X937ReturnAddendumARecordBase(String encoding, int stdLevel) {
        super(TYPE_RETURN_ADDENDUM_A, encoding, stdLevel);
    }

    public X937ReturnAddendumARecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String returnAddendumARecordNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord returnAddendumARecordNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public int returnAddendumARecordNumberAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord returnAddendumARecordNumber(int value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber BOFDRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDBusinessDateConfidenceIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDBusinessDateConfidenceIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date BOFDBusinessDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDBusinessDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDBusinessDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDBusinessDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDItemSequenceNumberConfidenceIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDItemSequenceNumberConfidenceIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDItemSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDItemSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String depositAccountNumberAtBOFDConfidenceIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord depositAccountNumberAtBOFDConfidenceIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String depositAccountNumberAtBOFD() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord depositAccountNumberAtBOFD(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDDepositBranchConfidenceIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDDepositBranchConfidenceIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDDepositBranch() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDDepositBranch(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payeeNameConfidenceIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord payeeNameConfidenceIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payeeName() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord payeeName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String truncationIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord truncationIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDConversionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDConversionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDCorrectionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord BOFDCorrectionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumARecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

