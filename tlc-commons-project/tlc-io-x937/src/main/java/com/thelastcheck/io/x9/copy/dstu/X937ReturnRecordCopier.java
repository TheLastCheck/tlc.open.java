package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937ReturnRecord;

/**
 * @author Jerry Bowman
 */
public class X937ReturnRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937ReturnRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937ReturnRecord in = (X937ReturnRecord) input;
        X937ReturnRecord out = (X937ReturnRecord) output;

        out.payorBankRoutingNumber(in.payorBankRoutingNumber());
        out.payorBankRoutingNumberCheckDigit(in.payorBankRoutingNumberCheckDigit());
        out.onUsReturnRecord(in.onUsReturnRecord());
        out.itemAmount(in.itemAmount());
        out.returnReason(in.returnReason());
        out.returnRecordAddendumCount(in.returnRecordAddendumCount());
        out.returnDocumentationTypeIndicator(in.returnDocumentationTypeIndicator());
        out.forwardBundleDate(in.forwardBundleDate());
        out.ECEInstitutionItemSequenceNumber(in.ECEInstitutionItemSequenceNumber());
        out.externalProcessingCode(in.externalProcessingCode());
        out.returnNotificationIndicator(in.returnNotificationIndicator());
        out.returnArchiveTypeIndicator(in.returnArchiveTypeIndicator());
        out.numberOfTimesReturned(in.numberOfTimesReturned());
        out.reserved(in.reserved());

        return out;
    }
}
