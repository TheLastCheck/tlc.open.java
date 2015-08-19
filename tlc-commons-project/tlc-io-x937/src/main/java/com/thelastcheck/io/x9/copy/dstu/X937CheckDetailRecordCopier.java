package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;

/**
 * @author Jerry Bowman
 */
public class X937CheckDetailRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937CheckDetailRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937CheckDetailRecord in = (X937CheckDetailRecord) input;
        X937CheckDetailRecord out = (X937CheckDetailRecord) output;

        out.auxiliaryOnUs(in.auxiliaryOnUs());
        out.externalProcessingCode(in.externalProcessingCode());
        out.payorBankRoutingNumber(in.payorBankRoutingNumber());
        out.payorBankRoutingNumberCheckDigit(in.payorBankRoutingNumberCheckDigit());
        out.onUs(in.onUs());
        out.itemAmount(in.itemAmount());
        out.ECEInstitutionItemSequenceNumber(in.ECEInstitutionItemSequenceNumber());
        out.documentationTypeIndicator(in.documentationTypeIndicator());
        out.returnAcceptanceIndicator(in.returnAcceptanceIndicator());
        out.MICRValidIndicator(in.MICRValidIndicator());
        out.BOFDIndicator(in.BOFDIndicator());
        out.checkDetailRecordAddendumCount(in.checkDetailRecordAddendumCount());
        out.correctionIndicator(in.correctionIndicator());
        out.archiveTypeIndicator(in.archiveTypeIndicator());

        return out;
    }
}
