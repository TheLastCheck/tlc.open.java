package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937RoutingNumberSummaryRecord;

/**
 * @author Jerry Bowman
 */
public class X937RoutingNumberSummaryRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937RoutingNumberSummaryRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937RoutingNumberSummaryRecord in = (X937RoutingNumberSummaryRecord) input;
        X937RoutingNumberSummaryRecord out = (X937RoutingNumberSummaryRecord) output;

        out.routingNumberWithinCashLetter(in.routingNumberWithinCashLetter());
        out.routingNumberTotalAmount(in.routingNumberTotalAmount());
        out.routingNumberItemCount(in.routingNumberItemCount());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
