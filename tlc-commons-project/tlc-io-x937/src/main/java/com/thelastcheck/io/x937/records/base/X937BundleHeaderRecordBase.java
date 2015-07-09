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
import com.thelastcheck.io.x937.records.X937BundleHeaderRecord;

import java.util.Date;

public abstract class X937BundleHeaderRecordBase extends X9RecordImpl
        implements X937BundleHeaderRecord {



    /*
     * X937BundleHeaderRecordBase
     */

    public X937BundleHeaderRecordBase() {
        super();
        recordType(TYPE_BUNDLE_HEADER);
    }

    public X937BundleHeaderRecordBase(int stdLevel) {
        super(TYPE_BUNDLE_HEADER, stdLevel);
    }

    public X937BundleHeaderRecordBase(String encoding, int stdLevel) {
        super(TYPE_BUNDLE_HEADER, encoding, stdLevel);
    }

    public X937BundleHeaderRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String collectionTypeIndicator() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord collectionTypeIndicator(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber destinationRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord destinationRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String destinationRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord destinationRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber ECEInstitutionRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord ECEInstitutionRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String ECEInstitutionRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord ECEInstitutionRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date bundleBusinessDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord bundleBusinessDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String bundleBusinessDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord bundleBusinessDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public Date bundleCreationDate()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord bundleCreationDate(Date value) {
        throw new InvalidStandardLevelException();
    }

    public String bundleCreationDateAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord bundleCreationDate(String value) {
        throw new InvalidStandardLevelException();
    }

    public String bundleID() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord bundleID(String value) {
        throw new InvalidStandardLevelException();
    }

    public String bundleSequenceNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord bundleSequenceNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String cycleNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord cycleNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public RoutingNumber returnLocationRoutingNumber() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord returnLocationRoutingNumber(RoutingNumber value) {
        throw new InvalidStandardLevelException();
    }

    public String returnLocationRoutingNumberAsString() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord returnLocationRoutingNumber(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleHeaderRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

