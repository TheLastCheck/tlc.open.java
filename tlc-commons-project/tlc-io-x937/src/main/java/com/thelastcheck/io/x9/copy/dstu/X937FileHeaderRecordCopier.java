package com.thelastcheck.io.x9.copy.dstu;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;

/**
 * @author Jerry Bowman
 */
public class X937FileHeaderRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937FileHeaderRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937FileHeaderRecord in = (X937FileHeaderRecord) input;
        X937FileHeaderRecord out = (X937FileHeaderRecord) output;

        out.standardLevel(in.standardLevel());
        out.testFileIndicator(in.testFileIndicator());
        out.immediateDestinationRoutingNumber(in.immediateDestinationRoutingNumber());
        out.immediateOriginRoutingNumber(in.immediateOriginRoutingNumber());
        Date date = in.fileCreationDate();
        if (date != null) out.fileCreationDate(date);
        date = in.fileCreationTime();
        if (date != null) out.fileCreationTime(date);
        out.resendIndicator(in.resendIndicator());
        out.immediateDestinationName(in.immediateDestinationName());
        out.immediateOriginName(in.immediateOriginName());
        out.fileIDModifier(in.fileIDModifier());
        out.countryCode(in.countryCode());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
