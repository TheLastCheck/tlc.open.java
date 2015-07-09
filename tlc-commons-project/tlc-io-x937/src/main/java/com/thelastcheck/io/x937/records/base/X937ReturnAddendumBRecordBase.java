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
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;
import com.thelastcheck.io.x937.records.X937ReturnAddendumBRecord;

import java.util.Date;

public abstract class X937ReturnAddendumBRecordBase extends X9RecordImpl
        implements X937ReturnAddendumBRecord {



    /*
     * X937ReturnAddendumBRecordBase
     */

    public X937ReturnAddendumBRecordBase() {
        super();
        recordType(TYPE_RETURN_ADDENDUM_B);
    }

    public X937ReturnAddendumBRecordBase(int stdLevel) {
        super(TYPE_RETURN_ADDENDUM_B, stdLevel);
    }

    public X937ReturnAddendumBRecordBase(String encoding, int stdLevel) {
        super(TYPE_RETURN_ADDENDUM_B, encoding, stdLevel);
    }

    public X937ReturnAddendumBRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String payorBankName() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumBRecord payorBankName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String auxiliaryOnUs() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumBRecord auxiliaryOnUs(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payorBankItemSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumBRecord payorBankItemSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date payorBankBusinessDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumBRecord payorBankBusinessDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String payorBankBusinessDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumBRecord payorBankBusinessDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payorAccountName() {
        throw new InvalidStandardLevelException();
    }

    public X937ReturnAddendumBRecord payorAccountName(String value) {
        throw new InvalidStandardLevelException();
    }

}

