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

import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937CheckDetailAddendumBRecordBase extends X9RecordImpl 
        implements X937CheckDetailAddendumBRecord {



    /*
     * X937CheckDetailAddendumBRecordBase
     */

    public X937CheckDetailAddendumBRecordBase() {
        super();
        recordType(TYPE_CHECK_DETAIL_ADDENDUM_B);
    }

    public X937CheckDetailAddendumBRecordBase(int stdLevel) {
        super(TYPE_CHECK_DETAIL_ADDENDUM_B, stdLevel);
    }

    public X937CheckDetailAddendumBRecordBase(String encoding, int stdLevel) {
        super(TYPE_CHECK_DETAIL_ADDENDUM_B, encoding, stdLevel);
    }

    public X937CheckDetailAddendumBRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String variableSizeRecordIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumBRecord variableSizeRecordIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String microfilmArchiveSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumBRecord microfilmArchiveSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String lengthOfImageArchiveSequenceNumber() {
        throw new InvalidStandardLevelException();
    }


    public int lengthOfImageArchiveSequenceNumberAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }


    public String imageArchiveSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumBRecord imageArchiveSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String description() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumBRecord description(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumBRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937CheckDetailAddendumBRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

