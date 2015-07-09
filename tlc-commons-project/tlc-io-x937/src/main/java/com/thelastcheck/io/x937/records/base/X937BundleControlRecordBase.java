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
import com.thelastcheck.io.x937.records.X937BundleControlRecord;

public abstract class X937BundleControlRecordBase extends X9RecordImpl
        implements X937BundleControlRecord {



    /*
     * X937BundleControlRecordBase
     */

    public X937BundleControlRecordBase() {
        super();
        recordType(TYPE_BUNDLE_CONTROL);
    }

    public X937BundleControlRecordBase(int stdLevel) {
        super(TYPE_BUNDLE_CONTROL, stdLevel);
    }

    public X937BundleControlRecordBase(String encoding, int stdLevel) {
        super(TYPE_BUNDLE_CONTROL, encoding, stdLevel);
    }

    public X937BundleControlRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String itemsWithinBundleCount() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord itemsWithinBundleCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int itemsWithinBundleCountAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord itemsWithinBundleCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String bundleTotalAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord bundleTotalAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long bundleTotalAmountAsLong()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord bundleTotalAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String MICRValidTotalAmount() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord MICRValidTotalAmount(String value) {
        throw new InvalidStandardLevelException();
    }

    public long MICRValidTotalAmountAsLong()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord MICRValidTotalAmount(long value) {
        throw new InvalidStandardLevelException();
    }

    public String imagesWithinBundleCount() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord imagesWithinBundleCount(String value) {
        throw new InvalidStandardLevelException();
    }

    public int imagesWithinBundleCountAsInt()
            throws InvalidDataException {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord imagesWithinBundleCount(int value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937BundleControlRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

