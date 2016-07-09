package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.RecordCountRecordFilter;
import com.thelastcheck.io.x9.X9InputStreamRecordReader;
import com.thelastcheck.io.x9.X9OutputStreamRecordWriter;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.factory.DefaultX9RecordFactoryStrategy;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x9.factory.X9RecordFactoryStrategy;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class X937CopierTest {

    private static final String x9InputFilename = "target/test-classes/ST001.test_send.x937";
    private static final String x9OutputFilename = "target/ST001.test_send.x937.out";

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
            RecordCountRecordFilter recordCountRecordFilter = new RecordCountRecordFilter();
            reader.addFilter(recordCountRecordFilter);
            X9RecordFactory factory = new DefaultX9RecordFactoryStrategy()
                    .factory(X9RecordFactoryStrategy.X937_STANDARD_DSTU, X9Record.ENCODING_EBCDIC);
            X937Copier copier = new X937Copier(factory);

            for (Record record : reader) {
                writer.write(copier.copy((X9Record) record));
            }
            recordCountRecordFilter.logRecordCounters();
        }
        assertEquals(inFile.length(), outFile.length());
    }

}