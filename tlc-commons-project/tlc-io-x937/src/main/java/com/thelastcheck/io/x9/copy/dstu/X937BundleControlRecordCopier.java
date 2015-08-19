package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937BundleControlRecord;

/**
 * @author Jerry Bowman
 */
public class X937BundleControlRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937BundleControlRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937BundleControlRecord in = (X937BundleControlRecord) input;
        X937BundleControlRecord out = (X937BundleControlRecord) output;

        out.itemsWithinBundleCount(in.itemsWithinBundleCount());
        out.bundleTotalAmount(in.bundleTotalAmount());
        out.MICRValidTotalAmount(in.MICRValidTotalAmount());
        out.imagesWithinBundleCount(in.imagesWithinBundleCount());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
