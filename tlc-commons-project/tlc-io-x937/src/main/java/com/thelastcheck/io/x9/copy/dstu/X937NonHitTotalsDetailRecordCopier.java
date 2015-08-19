package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937NonHitTotalsDetailRecord;

/**
 * @author Jerry Bowman
 */
public class X937NonHitTotalsDetailRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937NonHitTotalsDetailRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937NonHitTotalsDetailRecord in = (X937NonHitTotalsDetailRecord) input;
        X937NonHitTotalsDetailRecord out = (X937NonHitTotalsDetailRecord) output;

        out.destinationRoutingNumber(in.destinationRoutingNumber());
        out.nonHitIndicator(in.nonHitIndicator());
        out.totalItemCount(in.totalItemCount());
        out.totalItemAmount(in.totalItemAmount());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
