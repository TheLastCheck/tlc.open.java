package com.thelastcheck.io.x9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.RecordFilter;
import com.thelastcheck.io.base.exception.RecordReaderException;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;

/**
 * @author Jerry Bowman
 */
public class X9InputStreamRecordReaderTest {
    private static final Logger log = LoggerFactory.getLogger(X9InputStreamRecordReaderTest.class);

    @Test(expected = RecordReaderException.class)
    public void shouldFailBadRecord() throws Exception {
        X9InputStreamRecordReader reader = buildReader(false);
        int recordsFound = 0;
        try {
            for (Record record : reader) {
                log.info(record.toString());
                recordsFound++;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            assertEquals(4, recordsFound);
            reader.close();
        }
    }


    @Test
    public void shouldUseNullFilter() throws Exception {
        final X9InputStreamRecordReader reader = buildReaderFromFile();
        reader.addFilter(new RecordFilter() {
            @Override
            public Record filter(Record record) {
                if (record instanceof X937CheckDetailRecord) return record;
                return null;
            }
        });
        for (Record record : reader) {
            log.info(record.toString());
        }
        reader.close();
    }


    @Test
    public void shouldSkipBadRecord() throws Exception {
        X9InputStreamRecordReader reader = buildReader(true);
        int recordsFound = 0;
        try {
            for (Record record : reader) {
                log.info(record.toString());
                recordsFound++;
                if (recordsFound == 5) {
                    assertEquals(400, record.offsetPosition());
                    assertEquals(6, record.recordPosition());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            reader.close();
        }
        assertEquals(5, recordsFound);
    }


    public static X9InputStreamRecordReader buildReaderFromFile() throws FileNotFoundException {

        FileInputStream is = new FileInputStream("target/test-classes/sample-with-cim.x937");

        X9InputStreamRecordReader reader = new X9InputStreamRecordReader(is, true);
        return reader;
    }

    public static X9InputStreamRecordReader buildReader(boolean skipRecords) {
        byte[] buffer = buildX9Input();
        ByteArrayInputStream is = new ByteArrayInputStream(buffer);
        X9InputStreamRecordReader reader = new X9InputStreamRecordReader(is, skipRecords);
        return reader;
    }

    public static byte[] buildX9Input() {
        ByteArray ba = new ByteArray(80 * 6);
        int offset = 0;
        ba.fill();
        ba.write("0103P900102008122000496200409102307NCOMERICA BANK     UBOC - MPSC       BUS", offset, 80);
        offset += 80;
        ba.write("100112113752212200049620040910200409102259FG04122590ERNANI CRUZ   3102971484", offset, 80);
        offset += 80;
        ba.write("2001121137522122000496200409102004091066660666602514  122000496", offset, 80);
        offset += 80;
        ba.write("25                121137522                    0000003873000001111077640 64N010 ", offset, 80);
        offset += 80;
        ba.write("68", offset, 80);
        offset += 80;
        ba.write("25                121137522                    0000003873000001111077640 64N010 ", offset, 80);
        return ba.getBytes();
    }
}