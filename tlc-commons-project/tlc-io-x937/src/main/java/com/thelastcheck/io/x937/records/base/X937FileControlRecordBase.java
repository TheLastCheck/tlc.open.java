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

import com.thelastcheck.io.x937.records.X937FileControlRecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937FileControlRecordBase extends X9RecordImpl 
        implements X937FileControlRecord {



    /*
     * X937FileControlRecordBase
     */

    public X937FileControlRecordBase() {
        super();
        recordType(TYPE_FILE_CONTROL);
    }

    public X937FileControlRecordBase(int stdLevel) {
        super(TYPE_FILE_CONTROL, stdLevel);
    }

    public X937FileControlRecordBase(String encoding, int stdLevel) {
        super(TYPE_FILE_CONTROL, encoding, stdLevel);
    }

    public X937FileControlRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String cashLetterCount() {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord cashLetterCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int cashLetterCountAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord cashLetterCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String totalRecordCount() {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord totalRecordCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int totalRecordCountAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord totalRecordCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String totalItemCount() {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord totalItemCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int totalItemCountAsInt()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord totalItemCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String fileTotalAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord fileTotalAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long fileTotalAmountAsLong()
        throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord fileTotalAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String immediateOriginContactName() {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord immediateOriginContactName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String immediateOriginContactPhoneNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord immediateOriginContactPhoneNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937FileControlRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

