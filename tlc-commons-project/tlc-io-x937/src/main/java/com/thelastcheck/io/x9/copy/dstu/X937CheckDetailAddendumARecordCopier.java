package com.thelastcheck.io.x9.copy.dstu;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;

/**
 * @author Jerry Bowman
 */
public class X937CheckDetailAddendumARecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937CheckDetailAddendumARecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937CheckDetailAddendumARecord in = (X937CheckDetailAddendumARecord) input;
        X937CheckDetailAddendumARecord out = (X937CheckDetailAddendumARecord) output;

        out.checkDetailAddendumARecordNumber(in.checkDetailAddendumARecordNumber());
        out.BOFDRoutingNumber(in.BOFDRoutingNumber());
        Date date = in.BOFDBusinessDate();
        if (date != null) out.BOFDBusinessDate(date);
        out.BOFDItemSequenceNumber(in.BOFDItemSequenceNumber());
        out.depositAccountNumberAtBOFD(in.depositAccountNumberAtBOFD());
        out.BOFDDepositBranch(in.BOFDDepositBranch());
        out.payeeName(in.payeeName());
        out.truncationIndicator(in.truncationIndicator());
        out.BOFDConversionIndicator(in.BOFDConversionIndicator());
        out.BOFDCorrectionIndicator(in.BOFDCorrectionIndicator());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
