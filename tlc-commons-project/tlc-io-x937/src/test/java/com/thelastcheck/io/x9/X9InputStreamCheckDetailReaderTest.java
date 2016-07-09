package com.thelastcheck.io.x9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelastcheck.io.x9.parser.X937CheckDetailGraph;
import com.thelastcheck.io.x9.parser.X937ImageViewRecords;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumCRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;

public class X9InputStreamCheckDetailReaderTest {
    private static final Logger log = LoggerFactory.getLogger(X9InputStreamCheckDetailReaderTest.class);


    @Test
    public void shouldUseThreadSafeGraph() throws Exception {
        X9InputStreamCheckDetailReader reader = buildReaderFromFile();
        X937CheckDetailGraph graph = reader.readNextCheckDetail();
        while (graph != null) {
            X937CheckDetailRecord detailRecord = graph.checkDetailRecord();
            log.info(detailRecord.toString());
            X937CheckDetailGraph newgraph = reader.readNextCheckDetail();
            if (newgraph != null) {
                assertDifferentHeaders(graph, newgraph);
                assertDifferentChecks(graph, newgraph);
            }
            graph = newgraph;
        }
        reader.close();
    }

    private void assertDifferentHeaders(X937CheckDetailGraph graph, X937CheckDetailGraph newgraph) {
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

    private void assertDifferentChecks(X937CheckDetailGraph graph, X937CheckDetailGraph newgraph) {
        assertNotEquals(newgraph.checkDetailRecord(), graph.checkDetailRecord());
        if (graph.checkDetailAddendumBRecord() != null && newgraph.checkDetailAddendumBRecord() != null) {
            assertNotEquals(newgraph.checkDetailAddendumBRecord(), graph.checkDetailAddendumBRecord());
        }
        assertEquals(newgraph.checkDetailAddendumARecords().size(), graph.checkDetailAddendumARecords().size());
        for (int i = 0; i < newgraph.checkDetailAddendumARecords().size(); i++) {
            X937CheckDetailAddendumARecord newAddendumARecord =
                    newgraph.checkDetailAddendumARecords().get(i);
            X937CheckDetailAddendumARecord addendumARecord = graph.checkDetailAddendumARecords().get(i);
            assertNotEquals(newAddendumARecord, addendumARecord);
        }
        assertEquals(newgraph.checkDetailAddendumCRecords().size(), graph.checkDetailAddendumCRecords().size());
        for (int i = 0; i < newgraph.checkDetailAddendumCRecords().size(); i++) {
            X937CheckDetailAddendumCRecord newAddendumCRecord =
                    newgraph.checkDetailAddendumCRecords().get(i);
            X937CheckDetailAddendumCRecord addendumCRecord = graph.checkDetailAddendumCRecords().get(i);
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
    public void shouldReadCheckDetail() throws Exception {
        X9InputStreamCheckDetailReader reader = buildReaderFromFile();
        X937CheckDetailGraph graph = reader.readNextCheckDetail();
        while (graph != null) {
            X937CheckDetailRecord detailRecord = graph.checkDetailRecord();
            log.info(detailRecord.toString());
            graph = reader.readNextCheckDetail();
        }
        reader.close();
    }

    @Test
    public void shouldIterateCheckDetail() throws Exception {
        X9InputStreamCheckDetailReader reader = buildReaderFromFile();
        for (X937CheckDetailGraph graph : reader) {
            X937CheckDetailRecord detailRecord = graph.checkDetailRecord();
            log.info(detailRecord.toString());
        }
        reader.close();
    }

    @Test
    public void shouldIterateCheckDetailWithReader() throws Exception {
        X9InputStreamRecordReader reader1 = X9InputStreamRecordReaderTest.buildReaderFromFile();
        RecordCountRecordFilter filter = new RecordCountRecordFilter();
        reader1.addFilter(filter);
        X9InputStreamCheckDetailReader reader = new X9InputStreamCheckDetailReader(reader1);
        int recordsFound = 0;
        for (X937CheckDetailGraph graph : reader) {
            X937CheckDetailRecord detailRecord = graph.checkDetailRecord();
            log.info(detailRecord.toString());
            recordsFound++;
        }
        reader.close();
        assertEquals(recordsFound, filter.getRecordCounters()[25]);
    }

    private X9InputStreamCheckDetailReader buildReaderFromFile() throws FileNotFoundException {

        FileInputStream is = new FileInputStream("target/test-classes/sample-with-cim.x937");

        X9InputStreamCheckDetailReader reader = new X9InputStreamCheckDetailReader(is, true);
        return reader;
    }
}