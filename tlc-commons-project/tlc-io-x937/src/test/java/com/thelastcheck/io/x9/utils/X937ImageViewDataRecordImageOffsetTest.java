package com.thelastcheck.io.x9.utils;

import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9InputStreamRecordReader;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x937.records.X937ImageViewDataRecord;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class X937ImageViewDataRecordImageOffsetTest {
    private static Logger log = LoggerFactory.getLogger(X937ImageViewDataRecordImageOffsetTest.class);

    private static final String x9InputFilename = "../../../test-files/ST001.test_send.x937";
    private static final byte[] tiffMarker = new byte[]{0x49, 0x49, 0x2a};

    @Test
    public void testOffset() throws Exception {


        File inFile = new File(x9InputFilename);

        try (
                X9InputStreamRecordReader reader = new X9InputStreamRecordReader(new FileInputStream(inFile));
                RandomAccessFile raf = new RandomAccessFile(inFile, "r");
        ) {
            byte[] buffer = new byte[3];

            for (Record record : reader) {
                X9Record x9Record = (X9Record) record;
                log.debug("Record number: " + record.recordPosition() +
                        ", type: " + x9Record.recordType() +
                        ", offset: " + record.offsetPosition());
                if (x9Record.recordType() == X9Record.TYPE_IMAGE_VIEW_DATA) {
                    X937ImageViewDataRecord imageRecord = (X937ImageViewDataRecord) x9Record;
                    long offset = new X937ImageViewDataRecordImageOffset(imageRecord).offset();
                    log.debug("  ... offset to image " + offset);
                    long offset2 = record.offsetPosition() + 4 + imageRecord.length() - imageRecord.lengthOfImageDataAsInt();
                    assertEquals(offset2, offset);

                    raf.seek(offset);
                    raf.read(buffer);
                    assertArrayEquals(tiffMarker, buffer);
                }
            }
        }
    }

}