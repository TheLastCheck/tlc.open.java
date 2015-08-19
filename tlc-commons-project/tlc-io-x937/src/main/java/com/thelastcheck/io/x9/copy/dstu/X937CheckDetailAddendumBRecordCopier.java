package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;

/**
 * @author Jerry Bowman
 */
public class X937CheckDetailAddendumBRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937CheckDetailAddendumBRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937CheckDetailAddendumBRecord in = (X937CheckDetailAddendumBRecord) input;
        X937CheckDetailAddendumBRecord out = (X937CheckDetailAddendumBRecord) output;

        out.variableSizeRecordIndicator(in.variableSizeRecordIndicator());
        out.microfilmArchiveSequenceNumber(in.microfilmArchiveSequenceNumber());
        out.imageArchiveSequenceNumber(in.imageArchiveSequenceNumber());
        out.description(in.description());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
