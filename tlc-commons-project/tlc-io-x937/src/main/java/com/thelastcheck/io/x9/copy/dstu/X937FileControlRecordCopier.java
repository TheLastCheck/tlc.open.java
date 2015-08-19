package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937FileControlRecord;

/**
 * @author Jerry Bowman
 */
public class X937FileControlRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937FileControlRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937FileControlRecord in = (X937FileControlRecord) input;
        X937FileControlRecord out = (X937FileControlRecord) output;

        out.cashLetterCount(in.cashLetterCount());
        out.totalRecordCount(in.totalRecordCount());
        out.totalItemCount(in.totalItemCount());
        out.fileTotalAmount(in.fileTotalAmount());
        out.immediateOriginContactName(in.immediateOriginContactName());
        out.immediateOriginContactPhoneNumber(in.immediateOriginContactPhoneNumber());
        out.reserved(in.reserved());

        return out;
    }
}
