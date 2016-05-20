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

import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.RecordFilter;

public class RecordCountRecordFilter implements RecordFilter {
    private Logger log = LoggerFactory.getLogger(RecordCountRecordFilter.class);
    
    private int[]               recordCounters = new int[100];

    public RecordCountRecordFilter() {
    }

    public Record filter(Record record) {
        countRecord((X9Record) record);
        return record;
    }

    /**
     * Update the records counters and set the record position and offset
     * values. The record counters are an array of 100 counters, 1 each for each
     * of record type 01-99 and 1 for the total records (stored in array
     * position 0).
     * 
     * @param record
     */
    private void countRecord(X9Record record) {
        int recordType = record.recordType();
        recordCounters[recordType]++;
        recordCounters[0]++;

    }

    /**
     * This array of counters indicates how many of each record has been found
     * at the point this array is requested. Array position 0 contains the total
     * of all records read. Each other array position maps to the X9.37 record
     * type. Any slots for records which are not in the stream or are not
     * defined X9.37 records types will be zero.
     * 
     * @return the array of record counts
     */
    public int[] getRecordCounters() {
        return recordCounters;
    }
    
    public void logRecordCounters() {
        for (int i = 1; i < recordCounters.length; i++) {
            if (recordCounters[i] > 0) {
                log.info(String.format("Record type [%02d]) count: %7d", i, recordCounters[i]));
            }
        }
        log.info("------------------------");
        log.info(String.format("     Record total count: %7d", recordCounters[0]));
    }

}
