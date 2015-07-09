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
import com.thelastcheck.io.x937.records.X937CashLetterHeaderRecord;

import java.util.Date;

public abstract class X937CashLetterHeaderRecordBase extends X9RecordImpl
        implements X937CashLetterHeaderRecord {



    /*
     * X937CashLetterHeaderRecordBase
     */

    public X937CashLetterHeaderRecordBase() {
        super();
        recordType(TYPE_CASH_LETTER_HEADER);
    }

    public X937CashLetterHeaderRecordBase(int stdLevel) {
        super(TYPE_CASH_LETTER_HEADER, stdLevel);
    }

    public X937CashLetterHeaderRecordBase(String encoding, int stdLevel) {
        super(TYPE_CASH_LETTER_HEADER, encoding, stdLevel);
    }

    public X937CashLetterHeaderRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String collectionTypeIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord collectionTypeIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber destinationRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord destinationRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String destinationRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord destinationRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber ECEInstitutionRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord ECEInstitutionRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String ECEInstitutionRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord ECEInstitutionRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date cashLetterBusinessDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterBusinessDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String cashLetterBusinessDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterBusinessDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date cashLetterCreationDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterCreationDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String cashLetterCreationDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterCreationDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date cashLetterCreationTime()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterCreationTime(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String cashLetterCreationTimeAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterCreationTime(String value) {
        throw new InvalidStandardLevelException();
    }

    public String cashLetterRecordTypeIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterRecordTypeIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String cashLetterDocumentationTypeIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterDocumentationTypeIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String emptyCashLetterIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord emptyCashLetterIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String truncationIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord truncationIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public String cashLetterID() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord cashLetterID(String value) {
        throw new InvalidStandardLevelException();
    }

    public String originatorContactName() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord originatorContactName(String value) {
        throw new InvalidStandardLevelException();
    }

    public String originatorContactPhoneNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord originatorContactPhoneNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String fedWorkType() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord fedWorkType(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937CashLetterHeaderRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

