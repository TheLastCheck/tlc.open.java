/*
 * Copyright (c) 2009-2020 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.ControlTotalsRecordFilter;
import com.thelastcheck.io.x9.RecordCountRecordFilter;
import com.thelastcheck.io.x9.X9InputStreamRecordReader;
import com.thelastcheck.io.x9.X9OutputStreamRecordWriter;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.factory.DefaultX9RecordFactoryStrategy;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x9.factory.X9RecordFactoryStrategy;
import com.thelastcheck.io.x9.transform.X937CheckAddendumAtoReturnAddendumATransformer;
import com.thelastcheck.io.x9.transform.X937CheckAddendumBtoReturnAddendumCTransformer;
import com.thelastcheck.io.x9.transform.X937CheckAddendumCtoReturnAddendumDTransformer;
import com.thelastcheck.io.x9.transform.X937ChecktoReturnAddendumBTransformer;
import com.thelastcheck.io.x9.transform.X937ChecktoReturnTransformer;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumCRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumBRecord;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;


public class X937CopierCheckToReturnTest {

    private static final String x9InputFilename = "../../../test-files/101_Ref.x937";
    private static final String x9OutputFilename = "../../../test-files/101_Ref_return.x937";

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Test
    public void copyTest() throws IOException, InvalidDataException {
        File inFile = new File(x9InputFilename);
        File outFile = new File(x9OutputFilename);

        try (
                X9InputStreamRecordReader reader = new X9InputStreamRecordReader(
                        new FileInputStream(inFile));
                X9OutputStreamRecordWriter writer = new X9OutputStreamRecordWriter(
                        new FileOutputStream(outFile))
        ) {
            RecordCountRecordFilter inputCountRecordFilter = new RecordCountRecordFilter();
            reader.addFilter(inputCountRecordFilter);
            RecordCountRecordFilter outputCountRecordFilter = new RecordCountRecordFilter();
            writer.addFilter(outputCountRecordFilter);
            ControlTotalsRecordFilter totalsRecordFilter = new ControlTotalsRecordFilter();
            writer.addFilter(totalsRecordFilter);
            X9RecordFactory factory = new DefaultX9RecordFactoryStrategy()
                    .factory(X9RecordFactoryStrategy.X937_STANDARD_DSTU, X9Record.ENCODING_EBCDIC);
            X937Copier copier = new X937Copier(factory);
            X937CheckAddendumAtoReturnAddendumATransformer addendumATransformer = new X937CheckAddendumAtoReturnAddendumATransformer(factory);
            X937CheckAddendumBtoReturnAddendumCTransformer addendumCTransformer = new X937CheckAddendumBtoReturnAddendumCTransformer(factory);
            X937CheckAddendumCtoReturnAddendumDTransformer addendumDTransformer = new X937CheckAddendumCtoReturnAddendumDTransformer(factory);
            X937ChecktoReturnAddendumBTransformer addendumBTransformer = new X937ChecktoReturnAddendumBTransformer(factory);
            X937ChecktoReturnTransformer checktoReturnTransformer = new X937ChecktoReturnTransformer(factory, LocalDate.now().format(dateFormatter));

            X9Record returnAddendemBRecord = null;
            for (Record record : reader) {
                X9Record x9Record = (X9Record) record;
                switch (x9Record.recordType()) {
                    case X9Record.TYPE_CHECK_DETAIL:
                        X9Record x9ReturnRecord = checktoReturnTransformer.apply((X937CheckDetailRecord) x9Record);
                        writer.write(copier.copy(x9ReturnRecord));
                        returnAddendemBRecord = addendumBTransformer.apply((X937CheckDetailRecord) x9Record);
                        break;
                    case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_A:
                        x9Record = addendumATransformer.apply((X937CheckDetailAddendumARecord) x9Record);
                        writer.write(copier.copy(x9Record));
                        if (returnAddendemBRecord != null) {
                            writer.write(copier.copy(returnAddendemBRecord));
                            returnAddendemBRecord = null;
                        }
                        break;
                    case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_B:
                        if (returnAddendemBRecord != null) {
                            writer.write(copier.copy(returnAddendemBRecord));
                            returnAddendemBRecord = null;
                        }
                        x9Record = addendumCTransformer.apply((X937CheckDetailAddendumBRecord) x9Record);
                        writer.write(copier.copy(x9Record));
                        break;
                    case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_C:
                        if (returnAddendemBRecord != null) {
                            writer.write(copier.copy(returnAddendemBRecord));
                            returnAddendemBRecord = null;
                        }
                        x9Record = addendumDTransformer.apply((X937CheckDetailAddendumCRecord) x9Record);
                        writer.write(copier.copy(x9Record));
                        break;
                    default:
                        writer.write(copier.copy(x9Record));
                }
            }
            inputCountRecordFilter.logRecordCounters();
            outputCountRecordFilter.logRecordCounters();
        }
//        assertEquals(inFile.length(), outFile.length());
    }

}