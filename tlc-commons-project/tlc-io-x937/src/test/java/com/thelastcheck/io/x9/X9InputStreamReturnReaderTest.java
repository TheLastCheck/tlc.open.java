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

package com.thelastcheck.io.x9;

import com.thelastcheck.io.x9.parser.X937ImageViewRecords;
import com.thelastcheck.io.x9.parser.X937ReturnGraph;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumARecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumDRecord;
import com.thelastcheck.io.x937.records.X937ReturnRecord;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class X9InputStreamReturnReaderTest {
    private static final Logger log = LoggerFactory.getLogger(X9InputStreamReturnReaderTest.class);

    private static final String TEST_FILES_DIR = "../../../test-files/";

    @Test
    public void shouldUseThreadSafeGraph() throws Exception {
        X9InputStreamReturnReader reader = buildReaderFromFile();
        X937ReturnGraph graph = reader.readNextReturn();
        while (graph != null) {
            X937ReturnRecord detailRecord = graph.returnRecord();
            X937ReturnGraph newgraph = reader.readNextReturn();
            if (newgraph != null) {
                assertDifferentHeaders(graph, newgraph);
                assertDifferentReturns(graph, newgraph);
            }
            graph = newgraph;
        }
        reader.close();
    }

    private void assertDifferentHeaders(X937ReturnGraph graph, X937ReturnGraph newgraph) {
        assertNotEquals(newgraph.fileHeaderRecord(), graph.fileHeaderRecord());
        assertNotEquals(newgraph.cashLetterHeaderRecord(), graph.cashLetterHeaderRecord());
        assertNotEquals(newgraph.bundleHeaderRecord(), graph.bundleHeaderRecord());

        assertEquals(newgraph.fileHeaderRecord().record(), graph.fileHeaderRecord().record());
        assertEquals(newgraph.cashLetterHeaderRecord().record(), graph.cashLetterHeaderRecord().record());
        assertEquals(newgraph.bundleHeaderRecord().record(), graph.bundleHeaderRecord().record());

        assertEquals(newgraph.fileHeaderRecord().recordStandardLevel(), graph.fileHeaderRecord().recordStandardLevel());
        assertEquals(newgraph.fileHeaderRecord().recordPosition(), graph.fileHeaderRecord().recordPosition());
        assertEquals(newgraph.fileHeaderRecord().offsetPosition(), graph.fileHeaderRecord().offsetPosition());

        assertEquals(newgraph.cashLetterHeaderRecord().recordStandardLevel(), graph.cashLetterHeaderRecord().recordStandardLevel());
        assertEquals(newgraph.cashLetterHeaderRecord().recordPosition(), graph.cashLetterHeaderRecord().recordPosition());
        assertEquals(newgraph.cashLetterHeaderRecord().offsetPosition(), graph.cashLetterHeaderRecord().offsetPosition());

        assertEquals(newgraph.bundleHeaderRecord().recordStandardLevel(), graph.bundleHeaderRecord().recordStandardLevel());
        assertEquals(newgraph.bundleHeaderRecord().recordPosition(), graph.bundleHeaderRecord().recordPosition());
        assertEquals(newgraph.bundleHeaderRecord().offsetPosition(), graph.bundleHeaderRecord().offsetPosition());
    }

    private void assertDifferentReturns(X937ReturnGraph graph, X937ReturnGraph newgraph) {
        assertNotEquals(newgraph.returnRecord(), graph.returnRecord());
        if (graph.returnAddendumBRecord() != null && newgraph.returnAddendumBRecord() != null) {
            assertNotEquals(newgraph.returnAddendumBRecord(), graph.returnAddendumBRecord());
        }
        if (graph.returnAddendumCRecord() != null && newgraph.returnAddendumCRecord() != null) {
            assertNotEquals(newgraph.returnAddendumCRecord(), graph.returnAddendumCRecord());
        }
        assertEquals(newgraph.returnAddendumARecords().size(), graph.returnAddendumARecords().size());
        for (int i = 0; i < newgraph.returnAddendumARecords().size(); i++) {
            X937ReturnAddendumARecord newAddendumARecord = newgraph.returnAddendumARecords().get(i);
            X937ReturnAddendumARecord addendumARecord = graph.returnAddendumARecords().get(i);
            assertNotEquals(newAddendumARecord, addendumARecord);
        }
        assertEquals(newgraph.returnAddendumDRecords().size(), graph.returnAddendumDRecords().size());
        for (int i = 0; i < newgraph.returnAddendumDRecords().size(); i++) {
            X937ReturnAddendumDRecord newAddendumCRecord = newgraph.returnAddendumDRecords().get(i);
            X937ReturnAddendumDRecord addendumCRecord = graph.returnAddendumDRecords().get(i);
            assertNotEquals(newAddendumCRecord, addendumCRecord);
        }
        assertEquals(newgraph.imageViewRecords().size(), graph.imageViewRecords().size());
        for (int i = 0; i < newgraph.imageViewRecords().size(); i++) {
            X937ImageViewRecords newImageViewRecord = newgraph.imageViewRecords().get(i);
            X937ImageViewRecords imageViewRecord = graph.imageViewRecords().get(i);
            assertNotEquals(newImageViewRecord, imageViewRecord);
        }
    }

    @Test
    public void shouldReadReturn() throws Exception {
        X9InputStreamReturnReader reader = buildReaderFromFile();

        long count = reader.stream().count();
        reader.close();

        reader = buildReaderFromFile();
        long streamTotalRecords = reader.stream().count();
        reader.close();

        int iteratorTotalRecords = 0;
        reader = buildReaderFromFile();
        for (X937ReturnGraph graph : reader) {
            iteratorTotalRecords++;
        }
        reader.close();

        int readNextTotalRecords = 0;
        reader = buildReaderFromFile();
        X937ReturnGraph graph = reader.readNextReturn();
        while (graph != null) {
            X937ReturnRecord detailRecord = graph.returnRecord();
            readNextTotalRecords++;
            graph = reader.readNextReturn();
        }
        reader.close();
        assertEquals(count, iteratorTotalRecords);
        assertEquals(count, readNextTotalRecords);
        assertEquals(count, streamTotalRecords);
    }

    @Test
    public void shouldIterateReturn() throws Exception {
        X9InputStreamReturnReader reader = buildReaderFromFile();
        for (X937ReturnGraph graph : reader) {
            X937ReturnRecord detailRecord = graph.returnRecord();
            log.info(detailRecord.toString());
        }
        reader.close();
    }

    @Test
    public void shouldStreamReturn() throws Exception {
        X9InputStreamReturnReader reader = buildReaderFromFile();
        reader.stream().forEach(graph -> {
            X937ReturnRecord detailRecord = graph.returnRecord();
            log.info(detailRecord.toString());
        });
        reader.close();
    }

    @Test
    public void shouldStreamReturnWithReader() throws Exception {
        try (X9InputStreamRecordReader reader1 = X9InputStreamRecordReaderTest.buildReaderFromFile()) {
            RecordCountRecordFilter filter = new RecordCountRecordFilter();
            reader1.addFilter(filter);
            X9InputStreamCheckDetailReader reader = new X9InputStreamCheckDetailReader(reader1);
            final int[] recordsFound = {0};
            reader.stream().forEach(graph -> {
                X937CheckDetailRecord detailRecord = graph.checkDetailRecord();
//            log.info(detailRecord.toString());
                recordsFound[0]++;
            });
            assertEquals(recordsFound[0], filter.getRecordCounters()[25]);
        }
    }

    private X9InputStreamReturnReader buildReaderFromFile() throws FileNotFoundException {

        FileInputStream is = new FileInputStream(TEST_FILES_DIR + "ST001.test_send_return.x937");

        X9InputStreamReturnReader reader = new X9InputStreamReturnReader(is, true);
        return reader;
    }
}