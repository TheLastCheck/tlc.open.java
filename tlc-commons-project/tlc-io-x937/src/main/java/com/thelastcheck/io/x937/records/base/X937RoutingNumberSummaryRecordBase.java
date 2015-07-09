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

import com.thelastcheck.io.x937.records.X937RoutingNumberSummaryRecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937RoutingNumberSummaryRecordBase extends X9RecordImpl 
        implements X937RoutingNumberSummaryRecord {



    /*
     * X937RoutingNumberSummaryRecordBase
     */

    public X937RoutingNumberSummaryRecordBase() {
        super();
        recordType(TYPE_ROUTING_NUMBER_SUMMARY);
    }

    public X937RoutingNumberSummaryRecordBase(int stdLevel) {
        super(TYPE_ROUTING_NUMBER_SUMMARY, stdLevel);
    }

    public X937RoutingNumberSummaryRecordBase(String encoding, int stdLevel) {
        super(TYPE_ROUTING_NUMBER_SUMMARY, encoding, stdLevel);
    }

    public X937RoutingNumberSummaryRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public RoutingNumber routingNumberWithinCashLetter() {
        throw new InvalidStandardLevelException();
    }

    public X937RoutingNumberSummaryRecord routingNumberWithinCashLetter(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String routingNumberWithinCashLetterAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937RoutingNumberSummaryRecord routingNumberWithinCashLetter(String value) {
        throw new InvalidStandardLevelException();
    }

    public String routingNumberTotalAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937RoutingNumberSummaryRecord routingNumberTotalAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long routingNumberTotalAmountAsLong()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937RoutingNumberSummaryRecord routingNumberTotalAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String routingNumberItemCount() {
        throw new InvalidStandardLevelException();
    }

    public X937RoutingNumberSummaryRecord routingNumberItemCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int routingNumberItemCountAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937RoutingNumberSummaryRecord routingNumberItemCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937RoutingNumberSummaryRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937RoutingNumberSummaryRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

