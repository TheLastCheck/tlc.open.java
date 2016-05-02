package com.thelastcheck.io.x9.utils;

import com.google.common.base.Throwables;
import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.x937.records.X937ImageViewDataRecord;


public class X937ImageViewDataRecordImageOffset {

    private X937ImageViewDataRecord record;

    public X937ImageViewDataRecordImageOffset(X937ImageViewDataRecord record) {
        this.record = record;
    }

    public long offset() {
        long offset = record.offsetPosition() + 4; // starting position plus prefix
        offset += record.length();
        try {
            offset -= record.lengthOfImageDataAsInt();
        } catch (InvalidDataException e) {
            Throwables.propagate(e);
        }
        return offset;
    }
}
