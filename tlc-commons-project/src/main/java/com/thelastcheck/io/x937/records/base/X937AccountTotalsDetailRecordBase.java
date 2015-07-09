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

import com.thelastcheck.io.x937.records.X937AccountTotalsDetailRecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937AccountTotalsDetailRecordBase extends X9RecordImpl 
        implements X937AccountTotalsDetailRecord {



    /*
     * X937AccountTotalsDetailRecordBase
     */

    public X937AccountTotalsDetailRecordBase() {
        super();
        recordType(TYPE_ACCOUNT_TOTALS_DETAIL);
    }

    public X937AccountTotalsDetailRecordBase(int stdLevel) {
        super(TYPE_ACCOUNT_TOTALS_DETAIL, stdLevel);
    }

    public X937AccountTotalsDetailRecordBase(String encoding, int stdLevel) {
        super(TYPE_ACCOUNT_TOTALS_DETAIL, encoding, stdLevel);
    }

    public X937AccountTotalsDetailRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public RoutingNumber destinationRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord destinationRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String destinationRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord destinationRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String keyAccountOrLowAccountInKeyAccountRange() {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord keyAccountOrLowAccountInKeyAccountRange(String value) {
        throw new InvalidStandardLevelException();
    }

    public String keyAccountOrHighAccountInKeyAccountRange() {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord keyAccountOrHighAccountInKeyAccountRange(String value) {
        throw new InvalidStandardLevelException();
    }

    public String totalItemCount() {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord totalItemCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long totalItemCountAsLong()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord totalItemCount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String totalItemAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord totalItemAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long totalItemAmountAsLong()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord totalItemAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937AccountTotalsDetailRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

