package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937AccountTotalsDetailRecord;

/**
 * @author Jerry Bowman
 */
public class X937AccountTotalsDetailRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937AccountTotalsDetailRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937AccountTotalsDetailRecord in = (X937AccountTotalsDetailRecord) input;
        X937AccountTotalsDetailRecord out = (X937AccountTotalsDetailRecord) output;

        out.destinationRoutingNumber(in.destinationRoutingNumber());
        out.keyAccountOrLowAccountInKeyAccountRange(in.keyAccountOrLowAccountInKeyAccountRange());
        out.keyAccountOrHighAccountInKeyAccountRange(in.keyAccountOrHighAccountInKeyAccountRange());
        out.totalItemCount(in.totalItemCount());
        out.totalItemAmount(in.totalItemAmount());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
