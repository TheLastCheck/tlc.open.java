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

import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937CheckDetailAddendumARecordBase extends X9RecordImpl 
        implements X937CheckDetailAddendumARecord {



    /*
     * X937CheckDetailAddendumARecordBase
     */

    public X937CheckDetailAddendumARecordBase() {
        super();
        recordType(TYPE_CHECK_DETAIL_ADDENDUM_A);
    }

    public X937CheckDetailAddendumARecordBase(int stdLevel) {
        super(TYPE_CHECK_DETAIL_ADDENDUM_A, stdLevel);
    }

    public X937CheckDetailAddendumARecordBase(String encoding, int stdLevel) {
        super(TYPE_CHECK_DETAIL_ADDENDUM_A, encoding, stdLevel);
    }

    public X937CheckDetailAddendumARecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String checkDetailAddendumARecordNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord checkDetailAddendumARecordNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public int checkDetailAddendumARecordNumberAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord checkDetailAddendumARecordNumber(int value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber BOFDRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord BOFDRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord BOFDRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date BOFDBusinessDate()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord BOFDBusinessDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDBusinessDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord BOFDBusinessDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDItemSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord BOFDItemSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String depositAccountNumberAtBOFD() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord depositAccountNumberAtBOFD(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDDepositBranch() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord BOFDDepositBranch(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payeeName() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord payeeName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String truncationIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord truncationIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDConversionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord BOFDConversionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String BOFDCorrectionIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord BOFDCorrectionIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumARecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

