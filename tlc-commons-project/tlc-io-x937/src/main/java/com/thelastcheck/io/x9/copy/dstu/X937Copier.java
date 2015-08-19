package com.thelastcheck.io.x9.copy.dstu;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.copy.X937RecordCopier;
import com.thelastcheck.io.x9.factory.X9RecordFactory;

/**
 * Created by Jerry Bowman
 */
public class X937Copier implements X937RecordCopier {

    private X9RecordFactory factory;
    private X937RecordCopier[] recordCopiers = new X937RecordCopier[100];



    public X937Copier(X9RecordFactory factory) {
        this.factory = factory;
        recordCopiers[X9Record.TYPE_FILE_HEADER] = new X937FileHeaderRecordCopier(factory);
        recordCopiers[X9Record.TYPE_CASH_LETTER_HEADER] = new X937CashLetterHeaderRecordCopier(factory);
        recordCopiers[X9Record.TYPE_BUNDLE_HEADER] = new X937BundleHeaderRecordCopier(factory);
        recordCopiers[X9Record.TYPE_CHECK_DETAIL] = new X937CheckDetailRecordCopier(factory);
        recordCopiers[X9Record.TYPE_CHECK_DETAIL_ADDENDUM_A] = new X937CheckDetailAddendumARecordCopier(factory);
        recordCopiers[X9Record.TYPE_CHECK_DETAIL_ADDENDUM_B] = new X937CheckDetailAddendumBRecordCopier(factory);
        recordCopiers[X9Record.TYPE_CHECK_DETAIL_ADDENDUM_C] = new X937CheckDetailAddendumCRecordCopier(factory);
        recordCopiers[X9Record.TYPE_RETURN] = new X937ReturnRecordCopier(factory);
        recordCopiers[X9Record.TYPE_RETURN_ADDENDUM_A] = new X937ReturnAddendumARecordCopier(factory);
        recordCopiers[X9Record.TYPE_RETURN_ADDENDUM_B] = new X937ReturnAddendumBRecordCopier(factory);
        recordCopiers[X9Record.TYPE_RETURN_ADDENDUM_C] = new X937ReturnAddendumCRecordCopier(factory);
        recordCopiers[X9Record.TYPE_RETURN_ADDENDUM_D] = new X937ReturnAddendumDRecordCopier(factory);
        recordCopiers[X9Record.TYPE_ACCOUNT_TOTALS_DETAIL] = new X937AccountTotalsDetailRecordCopier(factory);
        recordCopiers[X9Record.TYPE_NON_HIT_TOTALS_DETAIL] = new X937NonHitTotalsDetailRecordCopier(factory);
        recordCopiers[X9Record.TYPE_IMAGE_VIEW_DETAIL] = new X937ImageViewDetailRecordCopier(factory);
        recordCopiers[X9Record.TYPE_IMAGE_VIEW_DATA] = new X937ImageViewDataRecordCopier(factory);
        recordCopiers[X9Record.TYPE_IMAGE_VIEW_ANALYSIS] = new X937ImageViewAnalysisRecordCopier(factory);
        recordCopiers[X9Record.TYPE_BUNDLE_CONTROL] = new X937BundleControlRecordCopier(factory);
        recordCopiers[X9Record.TYPE_BOX_SUMMARY] = new X937BoxSummaryRecordCopier(factory);
        recordCopiers[X9Record.TYPE_ROUTING_NUMBER_SUMMARY] = new X937RoutingNumberSummaryRecordCopier(factory);
        recordCopiers[X9Record.TYPE_CASH_LETTER_CONTROL] = new X937CashLetterControlRecordCopier(factory);
        recordCopiers[X9Record.TYPE_FILE_CONTROL] = new X937FileControlRecordCopier(factory);
    }

    @Override
    public X9Record copy(X9Record input) throws InvalidDataException {
        X937RecordCopier copier = recordCopiers[input.recordType()];
        if (copier == null) {
            throw new InvalidDataException("Unknown record type" + input.recordType());
        }
        return copier.copy(input);
    }

}
