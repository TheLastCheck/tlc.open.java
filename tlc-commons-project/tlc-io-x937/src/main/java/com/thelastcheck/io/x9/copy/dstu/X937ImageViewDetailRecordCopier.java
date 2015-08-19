package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937ImageViewDetailRecord;

/**
 * @author Jerry Bowman
 */
public class X937ImageViewDetailRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937ImageViewDetailRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937ImageViewDetailRecord in = (X937ImageViewDetailRecord) input;
        X937ImageViewDetailRecord out = (X937ImageViewDetailRecord) output;

        out.imageIndicator(in.imageIndicator());
        out.imageCreatorRoutingNumber(in.imageCreatorRoutingNumber());
        out.imageCreatorDate(in.imageCreatorDate());
        out.imageViewFormatIndicator(in.imageViewFormatIndicator());
        out.imageViewCompressionAlgorithmIdentifier(in.imageViewCompressionAlgorithmIdentifier());
        out.imageViewDataSize(in.imageViewDataSize());
        out.viewSideIndicator(in.viewSideIndicator());
        out.viewDescriptor(in.viewDescriptor());
        out.digitalSignatureIndicator(in.digitalSignatureIndicator());
        out.digitalSignatureMethod(in.digitalSignatureMethod());
        out.securityKeySize(in.securityKeySize());
        out.startOfProtectedData(in.startOfProtectedData());
        out.lengthofProtectedData(in.lengthofProtectedData());
        out.imageRecreateIndicator(in.imageRecreateIndicator());
        out.userField(in.userField());
        out.imageTiffVarianceIndicator(in.imageTiffVarianceIndicator());
        out.overrideIndicator(in.overrideIndicator());
        out.reserved(in.reserved());

        return out;
    }
}
