package com.thelastcheck.io.x9.copy.dstu;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937BundleHeaderRecord;

/**
 * @author Jerry Bowman
 */
public class X937BundleHeaderRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937BundleHeaderRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937BundleHeaderRecord in = (X937BundleHeaderRecord) input;
        X937BundleHeaderRecord out = (X937BundleHeaderRecord) output;

        out.collectionTypeIndicator(in.collectionTypeIndicator());
        out.destinationRoutingNumber(in.destinationRoutingNumber());
        out.ECEInstitutionRoutingNumber(in.ECEInstitutionRoutingNumber());
        Date date = in.bundleBusinessDate();
        if (date != null) out.bundleBusinessDate(date);
        date = in.bundleCreationDate();
        if (date != null) out.bundleCreationDate(date);
        out.bundleID(in.bundleID());
        out.bundleSequenceNumber(in.bundleSequenceNumber());
        out.cycleNumber(in.cycleNumber());
        out.returnLocationRoutingNumber(in.returnLocationRoutingNumber());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
