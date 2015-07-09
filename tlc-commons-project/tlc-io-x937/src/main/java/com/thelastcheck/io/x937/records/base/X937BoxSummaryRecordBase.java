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
import com.thelastcheck.io.x937.records.X937BoxSummaryRecord;

public abstract class X937BoxSummaryRecordBase extends X9RecordImpl
        implements X937BoxSummaryRecord {



    /*
     * X937BoxSummaryRecordBase
     */

    public X937BoxSummaryRecordBase() {
        super();
        recordType(TYPE_BOX_SUMMARY);
    }

    public X937BoxSummaryRecordBase(int stdLevel) {
        super(TYPE_BOX_SUMMARY, stdLevel);
    }

    public X937BoxSummaryRecordBase(String encoding, int stdLevel) {
        super(TYPE_BOX_SUMMARY, encoding, stdLevel);
    }

    public X937BoxSummaryRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public RoutingNumber destinationRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord destinationRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String destinationRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord destinationRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String boxSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord boxSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String boxBundleCount() {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord boxBundleCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int boxBundleCountAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord boxBundleCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String boxNumberID() {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord boxNumberID(String value) {
        throw new InvalidStandardLevelException();
    }

    public String boxTotalAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord boxTotalAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long boxTotalAmountAsLong()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord boxTotalAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937BoxSummaryRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

