package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937ReturnAddendumDRecord;

/**
 * @author Jerry Bowman
 */
public class X937ReturnAddendumDRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937ReturnAddendumDRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937ReturnAddendumDRecord in = (X937ReturnAddendumDRecord) input;
        X937ReturnAddendumDRecord out = (X937ReturnAddendumDRecord) output;

        out.returnAddendumDRecordNumber(in.returnAddendumDRecordNumber());
        out.endorsingBankRoutingNumber(in.endorsingBankRoutingNumber());
        out.endorsingBankEndorsementDate(in.endorsingBankEndorsementDate());
        out.endorsingBankItemSequenceNumber(in.endorsingBankItemSequenceNumber());
        out.truncationIndicator(in.truncationIndicator());
        out.endorsingBankConversionIndicator(in.endorsingBankConversionIndicator());
        out.endorsingBankCorrectionIndicator(in.endorsingBankCorrectionIndicator());
        out.returnReason(in.returnReason());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
