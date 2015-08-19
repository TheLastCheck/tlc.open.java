package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CashLetterHeaderRecord;

/**
 * @author Jerry Bowman
 */
public class X937CashLetterHeaderRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937CashLetterHeaderRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937CashLetterHeaderRecord in = (X937CashLetterHeaderRecord) input;
        X937CashLetterHeaderRecord out = (X937CashLetterHeaderRecord) output;

        out.collectionTypeIndicator(in.collectionTypeIndicator());
        out.destinationRoutingNumber(in.destinationRoutingNumber());
        out.ECEInstitutionRoutingNumber(in.ECEInstitutionRoutingNumber());
        out.cashLetterBusinessDate(in.cashLetterBusinessDate());
        out.cashLetterCreationDate(in.cashLetterCreationDate());
        out.cashLetterCreationTime(in.cashLetterCreationTime());
        out.cashLetterRecordTypeIndicator(in.cashLetterRecordTypeIndicator());
        out.cashLetterDocumentationTypeIndicator(in.cashLetterDocumentationTypeIndicator());
        out.cashLetterID(in.cashLetterID());
        out.originatorContactName(in.originatorContactName());
        out.originatorContactPhoneNumber(in.originatorContactPhoneNumber());
        out.fedWorkType(in.fedWorkType());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
