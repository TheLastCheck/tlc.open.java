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

package com.thelastcheck.io.x9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.RecordFilter;
import com.thelastcheck.io.x937.records.X937BundleControlRecord;
import com.thelastcheck.io.x937.records.X937CashLetterControlRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;
import com.thelastcheck.io.x937.records.X937FileControlRecord;
import com.thelastcheck.io.x937.records.X937ReturnRecord;

public class ControlTotalsRecordFilter implements RecordFilter {
    private static Logger          log    = LoggerFactory.getLogger(ControlTotalsRecordFilter.class);

    private int                 totalRecordCount;
    private int                 bundleItemCount;
    private long                bundleTotalAmount;
    private long                bundleMicrValidAmount;
    private int                 bundleImagesCount;
    private int                 cashLetterItemCount;
    private int                 cashLetterImagesCount;
    private long                cashLetterTotalAmount;
    private int                 fileItemCount;
    private long                fileTotalAmount;
    private int                 cashLetterBundleCount;
    private int                 fileCashLetterCount;

    public ControlTotalsRecordFilter() {
    }

    public Record filter(Record record) {
        X9Record x9Record = (X9Record) record;
        X937CheckDetailRecord cdr;
        X937ReturnRecord rr;
        X937BundleControlRecord bcr;
        X937CashLetterControlRecord clcr;
        X937FileControlRecord fcr;
        switch (x9Record.recordType()) {
        case X9Record.TYPE_CHECK_DETAIL:
            cdr = (X937CheckDetailRecord) x9Record;
            totalRecordCount++;
            bundleItemCount++;
            long itemAmount = 0;
            try {
                itemAmount = cdr.itemAmountAsLong();
            } catch (InvalidDataException e) {
                log.warn("Invalid item amount, cannot add to bundle total amount: s" + e.getLocalizedMessage());
            }
            bundleTotalAmount += itemAmount;
            if (cdr.MICRValidIndicator().equals("1")) {
                bundleMicrValidAmount += itemAmount;
            }
            break;
        case X9Record.TYPE_RETURN:
            rr = (X937ReturnRecord) x9Record;
            totalRecordCount++;
            bundleItemCount++;
            try {
                bundleTotalAmount += rr.itemAmountAsLong();
            } catch (InvalidDataException e) {
                log.warn("Invalid item amount, cannot add to bundle total amount: s" + e.getLocalizedMessage());
            }
            break;
        case X9Record.TYPE_IMAGE_VIEW_DATA:
            bundleImagesCount++;
            totalRecordCount++;
            break;
        case X9Record.TYPE_BUNDLE_CONTROL:
            bcr = (X937BundleControlRecord) x9Record;
            bcr.bundleTotalAmount(bundleTotalAmount);
            bcr.itemsWithinBundleCount(bundleItemCount);
            bcr.MICRValidTotalAmount(bundleMicrValidAmount);
            bcr.imagesWithinBundleCount(bundleImagesCount);
            cashLetterItemCount += bundleItemCount;
            cashLetterImagesCount += bundleImagesCount;
            cashLetterTotalAmount += bundleTotalAmount;
            cashLetterBundleCount++;
            bundleItemCount = 0;
            bundleImagesCount = 0;
            bundleTotalAmount = 0;
            bundleMicrValidAmount = 0;
            totalRecordCount++;
            break;
        case X9Record.TYPE_CASH_LETTER_CONTROL:
            clcr = (X937CashLetterControlRecord) x9Record;
            clcr.cashLetterTotalAmount(cashLetterTotalAmount);
            clcr.itemsWithinCashletterCount(cashLetterItemCount);
            clcr.imagesWithinCashLetterCount(cashLetterImagesCount);
            clcr.bundleCount(cashLetterBundleCount);
            fileItemCount += cashLetterItemCount;
            fileTotalAmount += cashLetterTotalAmount;
            fileCashLetterCount++;
            cashLetterTotalAmount = 0;
            cashLetterItemCount = 0;
            cashLetterImagesCount = 0;
            cashLetterBundleCount = 0;
            totalRecordCount++;
            break;
        case X9Record.TYPE_FILE_CONTROL:
            fcr = (X937FileControlRecord) x9Record;
            fcr.fileTotalAmount(fileTotalAmount);
            fcr.totalItemCount(fileItemCount);
            fcr.cashLetterCount(fileCashLetterCount);
            // count this record before setting final value
            totalRecordCount++;
            fcr.totalRecordCount(totalRecordCount);
            break;
        default:
            totalRecordCount++;
        }
        return record;
    }
}
