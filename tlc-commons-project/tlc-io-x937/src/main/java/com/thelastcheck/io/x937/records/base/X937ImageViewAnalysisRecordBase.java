/*******************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.thelastcheck.io.x937.records.base;

import java.util.Date;

import com.thelastcheck.io.x937.records.X937ImageViewAnalysisRecord;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9RecordImpl;

public abstract class X937ImageViewAnalysisRecordBase extends X9RecordImpl 
        implements X937ImageViewAnalysisRecord {



    /*
     * X937ImageViewAnalysisRecordBase
     */

    public X937ImageViewAnalysisRecordBase() {
        super();
        recordType(TYPE_IMAGE_VIEW_ANALYSIS);
    }

    public X937ImageViewAnalysisRecordBase(int stdLevel) {
        super(TYPE_IMAGE_VIEW_ANALYSIS, stdLevel);
    }

    public X937ImageViewAnalysisRecordBase(String encoding, int stdLevel) {
        super(TYPE_IMAGE_VIEW_ANALYSIS, encoding, stdLevel);
    }

    public X937ImageViewAnalysisRecordBase(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    public String globalImageQuality() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord globalImageQuality(String value) {
        throw new InvalidStandardLevelException();
    }

    public String globalImageUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord globalImageUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String imagingBankSpecificTest() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord imagingBankSpecificTest(String value) {
        throw new InvalidStandardLevelException();
    }

    public String partialImage() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord partialImage(String value) {
        throw new InvalidStandardLevelException();
    }

    public String excessiveImageSkew() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord excessiveImageSkew(String value) {
        throw new InvalidStandardLevelException();
    }

    public String piggybackImage() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord piggybackImage(String value) {
        throw new InvalidStandardLevelException();
    }

    public String tooLightOrTooDark() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord tooLightOrTooDark(String value) {
        throw new InvalidStandardLevelException();
    }

    public String streaksAndOrBands() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord streaksAndOrBands(String value) {
        throw new InvalidStandardLevelException();
    }

    public String belowMinimumImageSize() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord belowMinimumImageSize(String value) {
        throw new InvalidStandardLevelException();
    }

    public String exceedsMaximumImageSize() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord exceedsMaximumImageSize(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved12() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved12(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved13() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved13(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved14() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved14(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved15() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved15(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved16() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved16(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved17() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved17(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved18() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved18(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved19() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved19(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved20() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved20(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved21() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved21(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved22() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved22(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved23() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved23(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved24() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved24(String value) {
        throw new InvalidStandardLevelException();
    }

    public String imageEnabledPOD() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord imageEnabledPOD(String value) {
        throw new InvalidStandardLevelException();
    }

    public String sourceDocumentBad() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord sourceDocumentBad(String value) {
        throw new InvalidStandardLevelException();
    }

    public String dateUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord dateUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payeeUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord payeeUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String convenienceAmountUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord convenienceAmountUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String legalAmountUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord legalAmountUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String signatureUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord signatureUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payorNameAndAddressUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord payorNameAndAddressUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String MICRLineUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord MICRLineUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String memoLineUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord memoLineUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payorBankNameAndAddressUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord payorBankNameAndAddressUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String payeeEndorsementUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord payeeEndorsementUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String bankOfFirstDepositEndorsementUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord bankOfFirstDepositEndorsementUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String transitEndorsementUsability() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord transitEndorsementUsability(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved39() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved39(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved40() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved40(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved41() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved41(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved42() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved42(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved43() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved43(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved44() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved44(String value) {
        throw new InvalidStandardLevelException();
    }

    public String userField() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord userField(String value) {
        throw new InvalidStandardLevelException();
    }

    public String reserved() {
        throw new InvalidStandardLevelException();
    }

    public X937ImageViewAnalysisRecord reserved(String value) {
        throw new InvalidStandardLevelException();
    }

}

