package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937ReturnAddendumBRecord;

/**
 * @author Jerry Bowman
 */
public class X937ReturnAddendumBRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937ReturnAddendumBRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937ReturnAddendumBRecord in = (X937ReturnAddendumBRecord) input;
        X937ReturnAddendumBRecord out = (X937ReturnAddendumBRecord) output;

        out.payorBankName(in.payorBankName());
        out.auxiliaryOnUs(in.auxiliaryOnUs());
        out.payorBankItemSequenceNumber(in.payorBankItemSequenceNumber());
        out.payorBankBusinessDate(in.payorBankBusinessDate());
        out.payorAccountName(in.payorAccountName());

        return out;
    }
}
