package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937BoxSummaryRecord;

/**
 * @author Jerry Bowman
 */
public class X937BoxSummaryRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937BoxSummaryRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937BoxSummaryRecord in = (X937BoxSummaryRecord) input;
        X937BoxSummaryRecord out = (X937BoxSummaryRecord) output;

        out.destinationRoutingNumber(in.destinationRoutingNumber());
        out.boxSequenceNumber(in.boxSequenceNumber());
        out.boxBundleCount(in.boxBundleCount());
        out.boxNumberID(in.boxNumberID());
        out.boxTotalAmount(in.boxTotalAmount());
        out.reserved(in.reserved());

        return out;
    }
}
