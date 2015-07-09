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

package com.thelastcheck.io.x937.records;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.x9.X9Record;

public interface X937ImageViewAnalysisRecord extends X9Record {


    /*
     * X937ImageViewAnalysisRecord
     */

    String globalImageQuality();
    X937ImageViewAnalysisRecord globalImageQuality(String value);

    String globalImageUsability();
    X937ImageViewAnalysisRecord globalImageUsability(String value);

    String imagingBankSpecificTest();
    X937ImageViewAnalysisRecord imagingBankSpecificTest(String value);

    String partialImage();
    X937ImageViewAnalysisRecord partialImage(String value);

    String excessiveImageSkew();
    X937ImageViewAnalysisRecord excessiveImageSkew(String value);

    String piggybackImage();
    X937ImageViewAnalysisRecord piggybackImage(String value);

    String tooLightOrTooDark();
    X937ImageViewAnalysisRecord tooLightOrTooDark(String value);

    String streaksAndOrBands();
    X937ImageViewAnalysisRecord streaksAndOrBands(String value);

    String belowMinimumImageSize();
    X937ImageViewAnalysisRecord belowMinimumImageSize(String value);

    String exceedsMaximumImageSize();
    X937ImageViewAnalysisRecord exceedsMaximumImageSize(String value);

    String reserved12();
    X937ImageViewAnalysisRecord reserved12(String value);

    String reserved13();
    X937ImageViewAnalysisRecord reserved13(String value);

    String reserved14();
    X937ImageViewAnalysisRecord reserved14(String value);

    String reserved15();
    X937ImageViewAnalysisRecord reserved15(String value);

    String reserved16();
    X937ImageViewAnalysisRecord reserved16(String value);

    String reserved17();
    X937ImageViewAnalysisRecord reserved17(String value);

    String reserved18();
    X937ImageViewAnalysisRecord reserved18(String value);

    String reserved19();
    X937ImageViewAnalysisRecord reserved19(String value);

    String reserved20();
    X937ImageViewAnalysisRecord reserved20(String value);

    String reserved21();
    X937ImageViewAnalysisRecord reserved21(String value);

    String reserved22();
    X937ImageViewAnalysisRecord reserved22(String value);

    String reserved23();
    X937ImageViewAnalysisRecord reserved23(String value);

    String reserved24();
    X937ImageViewAnalysisRecord reserved24(String value);

    String imageEnabledPOD();
    X937ImageViewAnalysisRecord imageEnabledPOD(String value);

    String sourceDocumentBad();
    X937ImageViewAnalysisRecord sourceDocumentBad(String value);

    String dateUsability();
    X937ImageViewAnalysisRecord dateUsability(String value);

    String payeeUsability();
    X937ImageViewAnalysisRecord payeeUsability(String value);

    String convenienceAmountUsability();
    X937ImageViewAnalysisRecord convenienceAmountUsability(String value);

    String legalAmountUsability();
    X937ImageViewAnalysisRecord legalAmountUsability(String value);

    String signatureUsability();
    X937ImageViewAnalysisRecord signatureUsability(String value);

    String payorNameAndAddressUsability();
    X937ImageViewAnalysisRecord payorNameAndAddressUsability(String value);

    String MICRLineUsability();
    X937ImageViewAnalysisRecord MICRLineUsability(String value);

    String memoLineUsability();
    X937ImageViewAnalysisRecord memoLineUsability(String value);

    String payorBankNameAndAddressUsability();
    X937ImageViewAnalysisRecord payorBankNameAndAddressUsability(String value);

    String payeeEndorsementUsability();
    X937ImageViewAnalysisRecord payeeEndorsementUsability(String value);

    String bankOfFirstDepositEndorsementUsability();
    X937ImageViewAnalysisRecord bankOfFirstDepositEndorsementUsability(String value);

    String transitEndorsementUsability();
    X937ImageViewAnalysisRecord transitEndorsementUsability(String value);

    String reserved39();
    X937ImageViewAnalysisRecord reserved39(String value);

    String reserved40();
    X937ImageViewAnalysisRecord reserved40(String value);

    String reserved41();
    X937ImageViewAnalysisRecord reserved41(String value);

    String reserved42();
    X937ImageViewAnalysisRecord reserved42(String value);

    String reserved43();
    X937ImageViewAnalysisRecord reserved43(String value);

    String reserved44();
    X937ImageViewAnalysisRecord reserved44(String value);

    String userField();
    X937ImageViewAnalysisRecord userField(String value);

    String reserved();
    X937ImageViewAnalysisRecord reserved(String value);

}

