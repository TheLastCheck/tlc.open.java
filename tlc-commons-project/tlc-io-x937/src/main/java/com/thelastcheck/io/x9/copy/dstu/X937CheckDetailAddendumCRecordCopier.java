package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumCRecord;

/**
 * @author Jerry Bowman
 */
public class X937CheckDetailAddendumCRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937CheckDetailAddendumCRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937CheckDetailAddendumCRecord in = (X937CheckDetailAddendumCRecord) input;
        X937CheckDetailAddendumCRecord out = (X937CheckDetailAddendumCRecord) output;

        out.checkDetailAddendumCRecordNumber(in.checkDetailAddendumCRecordNumber());
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
