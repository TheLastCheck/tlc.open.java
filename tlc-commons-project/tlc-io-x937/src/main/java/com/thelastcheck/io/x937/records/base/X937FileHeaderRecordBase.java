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
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;

import java.util.Date;

public abstract class X937FileHeaderRecordBase extends X9RecordImpl
        implements X937FileHeaderRecord {



    /*
     * X937FileHeaderRecordBase
     */

    public X937FileHeaderRecordBase() {
        super();
        recordType(TYPE_FILE_HEADER);
    }

    public X937FileHeaderRecordBase(int stdLevel) {
        super(TYPE_FILE_HEADER, stdLevel);
    }

    public X937FileHeaderRecordBase(String encoding, int stdLevel) {
        super(TYPE_FILE_HEADER, encoding, stdLevel);
    }

    public X937FileHeaderRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String standardLevel() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord standardLevel(String value) {
        throw new InvalidStandardLevelException();
    }

    public int standardLevelAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord standardLevel(int value) {
        throw new InvalidStandardLevelException();
    }

    public String testFileIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord testFileIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber immediateDestinationRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord immediateDestinationRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String immediateDestinationRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord immediateDestinationRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber immediateOriginRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord immediateOriginRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String immediateOriginRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord immediateOriginRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date fileCreationDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord fileCreationDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String fileCreationDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord fileCreationDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date fileCreationTime()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord fileCreationTime(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String fileCreationTimeAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord fileCreationTime(String value) {
        throw new InvalidStandardLevelException();
    }

    public String resendIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord resendIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String immediateDestinationName() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord immediateDestinationName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String immediateOriginName() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord immediateOriginName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String fileIDModifier() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord fileIDModifier(String value) {
        throw new InvalidStandardLevelException();
    }

    public String countryCode() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord countryCode(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937FileHeaderRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

