package com.thelastcheck.io.x9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelastcheck.io.x9.parser.X937CheckDetailGraph;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;

public class X9InputStreamCheckDetailReaderTest {
    private static final Logger log = LoggerFactory.getLogger(X9InputStreamCheckDetailReaderTest.class);


    @Test
    public void shouldReadCheckDetail() throws Exception {
        X9InputStreamCheckDetailReader reader = buildReaderFromFile();
        int recordsFound = 0;
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
        int recordsFound = 0;
        for (X937CheckDetailGraph graph : reader) {
            X937CheckDetailRecord detailRecord = graph.checkDetailRecord();
            log.info(detailRecord.toString());
        }
        reader.close();
    }

    private X9InputStreamCheckDetailReader buildReaderFromFile() throws FileNotFoundException {

        FileInputStream is = new FileInputStream("target/test-classes/sample-with-cim.x937");

        X9InputStreamCheckDetailReader reader = new X9InputStreamCheckDetailReader(is, true);
        return reader;
    }
}