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
import com.thelastcheck.io.x937.records.X937CashLetterControlRecord;

import java.util.Date;

public abstract class X937CashLetterControlRecordBase extends X9RecordImpl
        implements X937CashLetterControlRecord {



    /*
     * X937CashLetterControlRecordBase
     */

    public X937CashLetterControlRecordBase() {
        super();
        recordType(TYPE_CASH_LETTER_CONTROL);
    }

    public X937CashLetterControlRecordBase(int stdLevel) {
        super(TYPE_CASH_LETTER_CONTROL, stdLevel);
    }

    public X937CashLetterControlRecordBase(String encoding, int stdLevel) {
        super(TYPE_CASH_LETTER_CONTROL, encoding, stdLevel);
    }

    public X937CashLetterControlRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String bundleCount() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord bundleCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int bundleCountAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord bundleCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String itemsWithinCashletterCount() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord itemsWithinCashletterCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int itemsWithinCashletterCountAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord itemsWithinCashletterCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String cashLetterTotalAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord cashLetterTotalAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long cashLetterTotalAmountAsLong()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord cashLetterTotalAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String imagesWithinCashLetterCount() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord imagesWithinCashLetterCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long imagesWithinCashLetterCountAsLong()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord imagesWithinCashLetterCount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String finalDestinationName() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord finalDestinationName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String ECEInstitutionName() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord ECEInstitutionName(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date settlementDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord settlementDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String settlementDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord settlementDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterControlRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

