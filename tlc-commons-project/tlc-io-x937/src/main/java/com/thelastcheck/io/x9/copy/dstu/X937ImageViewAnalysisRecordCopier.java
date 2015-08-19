package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937ImageViewAnalysisRecord;

/**
 * @author Jerry Bowman
 */
public class X937ImageViewAnalysisRecordCopier implements X937RecordCopier {

    private X9RecordFactory factory;

    public X937ImageViewAnalysisRecordCopier(X9RecordFactory factory) {
        this.factory = factory;
    }

    public X9Record copy(X9Record input) throws InvalidDataException {
        Record output = factory.newX9Record(input.recordType());
        X937ImageViewAnalysisRecord in = (X937ImageViewAnalysisRecord) input;
        X937ImageViewAnalysisRecord out = (X937ImageViewAnalysisRecord) output;

        out.globalImageQuality(in.globalImageQuality());
        out.globalImageUsability(in.globalImageUsability());
        out.imagingBankSpecificTest(in.imagingBankSpecificTest());
        out.partialImage(in.partialImage());
        out.excessiveImageSkew(in.excessiveImageSkew());
        out.piggybackImage(in.piggybackImage());
        out.tooLightOrTooDark(in.tooLightOrTooDark());
        out.streaksAndOrBands(in.streaksAndOrBands());
        out.belowMinimumImageSize(in.belowMinimumImageSize());
        out.exceedsMaximumImageSize(in.exceedsMaximumImageSize());
        out.reserved12(in.reserved12());
        out.reserved13(in.reserved13());
        out.reserved14(in.reserved14());
        out.reserved15(in.reserved15());
        out.reserved16(in.reserved16());
        out.reserved17(in.reserved17());
        out.reserved18(in.reserved18());
        out.reserved19(in.reserved19());
        out.reserved20(in.reserved20());
        out.reserved21(in.reserved21());
        out.reserved22(in.reserved22());
        out.reserved23(in.reserved23());
        out.reserved24(in.reserved24());
        out.imageEnabledPOD(in.imageEnabledPOD());
        out.sourceDocumentBad(in.sourceDocumentBad());
        out.dateUsability(in.dateUsability());
        out.payeeUsability(in.payeeUsability());
        out.convenienceAmountUsability(in.convenienceAmountUsability());
        out.legalAmountUsability(in.legalAmountUsability());
        out.signatureUsability(in.signatureUsability());
        out.payorNameAndAddressUsability(in.payorNameAndAddressUsability());
        out.MICRLineUsability(in.MICRLineUsability());
        out.memoLineUsability(in.memoLineUsability());
        out.payorBankNameAndAddressUsability(in.payorBankNameAndAddressUsability());
        out.payeeEndorsementUsability(in.payeeEndorsementUsability());
        out.bankOfFirstDepositEndorsementUsability(in.bankOfFirstDepositEndorsementUsability());
        out.transitEndorsementUsability(in.transitEndorsementUsability());
        out.reserved39(in.reserved39());
        out.reserved40(in.reserved40());
        out.reserved41(in.reserved41());
        out.reserved42(in.reserved42());
        out.reserved43(in.reserved43());
        out.reserved44(in.reserved44());
        out.userField(in.userField());
        out.reserved(in.reserved());

        return out;
    }
}
