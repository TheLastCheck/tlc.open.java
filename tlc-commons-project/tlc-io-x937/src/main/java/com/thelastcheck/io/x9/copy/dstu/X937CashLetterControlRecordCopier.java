package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CashLetterControlRecord;

/**
 * @author Jerry Bowman
 */
public class X937CashLetterControlRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937CashLetterControlRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937CashLetterControlRecord in = (X937CashLetterControlRecord) input;
        X937CashLetterControlRecord out = (X937CashLetterControlRecord) output;

        out.bundleCount(in.bundleCount());
        out.itemsWithinCashletterCount(in.itemsWithinCashletterCount());
        out.cashLetterTotalAmount(in.cashLetterTotalAmount());
        out.imagesWithinCashLetterCount(in.imagesWithinCashLetterCount());
        out.ECEInstitutionName(in.ECEInstitutionName());
        out.settlementDate(in.settlementDate());
        out.reserved(in.reserved());

        return out;
    }
}
