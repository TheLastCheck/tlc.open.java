package com.thelastcheck.io.x9.copy.dstu;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937ImageViewDataRecord;

/**
 * @author Jerry Bowman
 */
public class X937ImageViewDataRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937ImageViewDataRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937ImageViewDataRecord in = (X937ImageViewDataRecord) input;
        X937ImageViewDataRecord out = (X937ImageViewDataRecord) output;

        out.ECEInstitutionRoutingNumber(in.ECEInstitutionRoutingNumber());
        Date date = in.bundleBusinessDate();
        if (date != null) out.bundleBusinessDate(date);
        out.cycleNumber(in.cycleNumber());
        out.ECEInstitutionItemSequenceNumber(in.ECEInstitutionItemSequenceNumber());
        out.securityOriginatorName(in.securityOriginatorName());
        out.securityAuthenticatorName(in.securityAuthenticatorName());
        out.securityKeyName(in.securityKeyName());
        out.clippingOrigin(in.clippingOrigin());
        out.clippingCoordinateH1(in.clippingCoordinateH1());
        out.clippingCoordinateH2(in.clippingCoordinateH2());
        out.clippingCoordinateV1(in.clippingCoordinateV1());
        out.clippingCoordinateV2(in.clippingCoordinateV2());
        out.imageReferenceKey(in.imageReferenceKey());
        out.digitalSignature(in.digitalSignature());
        out.imageData(in.imageData());

        return out;
    }
}
