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

package com.thelastcheck.io.x937.records.stddstu;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937ImageViewAnalysisRecord;
import com.thelastcheck.io.x937.records.base.X937ImageViewAnalysisRecordBase;

public class X937ImageViewAnalysisRecordImpl extends X937ImageViewAnalysisRecordBase {

    private static int maxFieldNumber = 46;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("GlobalImageQuality", 2, 2, 1, FieldType.STRING);
        fields[3] = new Field("GlobalImageUsability", 3, 3, 1, FieldType.STRING);
        fields[4] = new Field("ImagingBankSpecificTest", 4, 4, 1, FieldType.STRING);
        fields[5] = new Field("PartialImage", 5, 5, 1, FieldType.STRING);
        fields[6] = new Field("ExcessiveImageSkew", 6, 6, 1, FieldType.STRING);
        fields[7] = new Field("PiggybackImage", 7, 7, 1, FieldType.STRING);
        fields[8] = new Field("TooLightOrTooDark", 8, 8, 1, FieldType.STRING);
        fields[9] = new Field("StreaksAndOrBands", 9, 9, 1, FieldType.STRING);
        fields[10] = new Field("BelowMinimumImageSize", 10, 10, 1, FieldType.STRING);
        fields[11] = new Field("ExceedsMaximumImageSize", 11, 11, 1, FieldType.STRING);
        fields[12] = new Field("Reserved12", 12, 12, 1, FieldType.STRING);
        fields[13] = new Field("Reserved13", 13, 13, 1, FieldType.STRING);
        fields[14] = new Field("Reserved14", 14, 14, 1, FieldType.STRING);
        fields[15] = new Field("Reserved15", 15, 15, 1, FieldType.STRING);
        fields[16] = new Field("Reserved16", 16, 16, 1, FieldType.STRING);
        fields[17] = new Field("Reserved17", 17, 17, 1, FieldType.STRING);
        fields[18] = new Field("Reserved18", 18, 18, 1, FieldType.STRING);
        fields[19] = new Field("Reserved19", 19, 19, 1, FieldType.STRING);
        fields[20] = new Field("Reserved20", 20, 20, 1, FieldType.STRING);
        fields[21] = new Field("Reserved21", 21, 21, 1, FieldType.STRING);
        fields[22] = new Field("Reserved22", 22, 22, 1, FieldType.STRING);
        fields[23] = new Field("Reserved23", 23, 23, 1, FieldType.STRING);
        fields[24] = new Field("Reserved24", 24, 24, 1, FieldType.STRING);
        fields[25] = new Field("ImageEnabledPOD", 25, 25, 1, FieldType.STRING);
        fields[26] = new Field("SourceDocumentBad", 26, 26, 1, FieldType.STRING);
        fields[27] = new Field("DateUsability", 27, 27, 1, FieldType.STRING);
        fields[28] = new Field("PayeeUsability", 28, 28, 1, FieldType.STRING);
        fields[29] = new Field("ConvenienceAmountUsability", 29, 29, 1, FieldType.STRING);
        fields[30] = new Field("LegalAmountUsability", 30, 30, 1, FieldType.STRING);
        fields[31] = new Field("SignatureUsability", 31, 31, 1, FieldType.STRING);
        fields[32] = new Field("PayorNameAndAddressUsability", 32, 32, 1, FieldType.STRING);
        fields[33] = new Field("MICRLineUsability", 33, 33, 1, FieldType.STRING);
        fields[34] = new Field("MemoLineUsability", 34, 34, 1, FieldType.STRING);
        fields[35] = new Field("PayorBankNameAndAddressUsability", 35, 35, 1, FieldType.STRING);
        fields[36] = new Field("PayeeEndorsementUsability", 36, 36, 1, FieldType.STRING);
        fields[37] = new Field("BankOfFirstDepositEndorsementUsability", 37, 37, 1, FieldType.STRING);
        fields[38] = new Field("TransitEndorsementUsability", 38, 38, 1, FieldType.STRING);
        fields[39] = new Field("Reserved39", 39, 39, 1, FieldType.STRING);
        fields[40] = new Field("Reserved40", 40, 40, 1, FieldType.STRING);
        fields[41] = new Field("Reserved41", 41, 41, 1, FieldType.STRING);
        fields[42] = new Field("Reserved42", 42, 42, 1, FieldType.STRING);
        fields[43] = new Field("Reserved43", 43, 43, 1, FieldType.STRING);
        fields[44] = new Field("Reserved44", 44, 44, 1, FieldType.STRING);
        fields[45] = new Field("UserField", 45, 45, 20, FieldType.STRING);
        fields[46] = new Field("Reserved", 46, 65, 15, FieldType.STRING);
    }


    /*
     * X937ImageViewAnalysisRecordImpl
     */

    public X937ImageViewAnalysisRecordImpl() {
        super();
    }

    public X937ImageViewAnalysisRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937ImageViewAnalysisRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937ImageViewAnalysisRecordImpl(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    @Override
    protected void resetDynamicFields() {
    }

    @Override
    public int numberOfFields() {
        return maxFieldNumber;
    }

    @Override
    protected Field field(int fieldNumber) {
        if (fieldNumber < 1 || fieldNumber > maxFieldNumber) {
            throw new IllegalArgumentException(INVALID_FIELD_NUMBER);
        }
        return fields[fieldNumber];
    }


    public String globalImageQuality() {
        return getFieldAsString(field(2));
    }

    public X937ImageViewAnalysisRecord globalImageQuality(String value) {
        setField(value, field(2));
        return this;
    }

    public String globalImageUsability() {
        return getFieldAsString(field(3));
    }

    public X937ImageViewAnalysisRecord globalImageUsability(String value) {
        setField(value, field(3));
        return this;
    }

    public String imagingBankSpecificTest() {
        return getFieldAsString(field(4));
    }

    public X937ImageViewAnalysisRecord imagingBankSpecificTest(String value) {
        setField(value, field(4));
        return this;
    }

    public String partialImage() {
        return getFieldAsString(field(5));
    }

    public X937ImageViewAnalysisRecord partialImage(String value) {
        setField(value, field(5));
        return this;
    }

    public String excessiveImageSkew() {
        return getFieldAsString(field(6));
    }

    public X937ImageViewAnalysisRecord excessiveImageSkew(String value) {
        setField(value, field(6));
        return this;
    }

    public String piggybackImage() {
        return getFieldAsString(field(7));
    }

    public X937ImageViewAnalysisRecord piggybackImage(String value) {
        setField(value, field(7));
        return this;
    }

    public String tooLightOrTooDark() {
        return getFieldAsString(field(8));
    }

    public X937ImageViewAnalysisRecord tooLightOrTooDark(String value) {
        setField(value, field(8));
        return this;
    }

    public String streaksAndOrBands() {
        return getFieldAsString(field(9));
    }

    public X937ImageViewAnalysisRecord streaksAndOrBands(String value) {
        setField(value, field(9));
        return this;
    }

    public String belowMinimumImageSize() {
        return getFieldAsString(field(10));
    }

    public X937ImageViewAnalysisRecord belowMinimumImageSize(String value) {
        setField(value, field(10));
        return this;
    }

    public String exceedsMaximumImageSize() {
        return getFieldAsString(field(11));
    }

    public X937ImageViewAnalysisRecord exceedsMaximumImageSize(String value) {
        setField(value, field(11));
        return this;
    }

    public String reserved12() {
        return getFieldAsString(field(12));
    }

    public X937ImageViewAnalysisRecord reserved12(String value) {
        setField(value, field(12));
        return this;
    }

    public String reserved13() {
        return getFieldAsString(field(13));
    }

    public X937ImageViewAnalysisRecord reserved13(String value) {
        setField(value, field(13));
        return this;
    }

    public String reserved14() {
        return getFieldAsString(field(14));
    }

    public X937ImageViewAnalysisRecord reserved14(String value) {
        setField(value, field(14));
        return this;
    }

    public String reserved15() {
        return getFieldAsString(field(15));
    }

    public X937ImageViewAnalysisRecord reserved15(String value) {
        setField(value, field(15));
        return this;
    }

    public String reserved16() {
        return getFieldAsString(field(16));
    }

    public X937ImageViewAnalysisRecord reserved16(String value) {
        setField(value, field(16));
        return this;
    }

    public String reserved17() {
        return getFieldAsString(field(17));
    }

    public X937ImageViewAnalysisRecord reserved17(String value) {
        setField(value, field(17));
        return this;
    }

    public String reserved18() {
        return getFieldAsString(field(18));
    }

    public X937ImageViewAnalysisRecord reserved18(String value) {
        setField(value, field(18));
        return this;
    }

    public String reserved19() {
        return getFieldAsString(field(19));
    }

    public X937ImageViewAnalysisRecord reserved19(String value) {
        setField(value, field(19));
        return this;
    }

    public String reserved20() {
        return getFieldAsString(field(20));
    }

    public X937ImageViewAnalysisRecord reserved20(String value) {
        setField(value, field(20));
        return this;
    }

    public String reserved21() {
        return getFieldAsString(field(21));
    }

    public X937ImageViewAnalysisRecord reserved21(String value) {
        setField(value, field(21));
        return this;
    }

    public String reserved22() {
        return getFieldAsString(field(22));
    }

    public X937ImageViewAnalysisRecord reserved22(String value) {
        setField(value, field(22));
        return this;
    }

    public String reserved23() {
        return getFieldAsString(field(23));
    }

    public X937ImageViewAnalysisRecord reserved23(String value) {
        setField(value, field(23));
        return this;
    }

    public String reserved24() {
        return getFieldAsString(field(24));
    }

    public X937ImageViewAnalysisRecord reserved24(String value) {
        setField(value, field(24));
        return this;
    }

    public String imageEnabledPOD() {
        return getFieldAsString(field(25));
    }

    public X937ImageViewAnalysisRecord imageEnabledPOD(String value) {
        setField(value, field(25));
        return this;
    }

    public String sourceDocumentBad() {
        return getFieldAsString(field(26));
    }

    public X937ImageViewAnalysisRecord sourceDocumentBad(String value) {
        setField(value, field(26));
        return this;
    }

    public String dateUsability() {
        return getFieldAsString(field(27));
    }

    public X937ImageViewAnalysisRecord dateUsability(String value) {
        setField(value, field(27));
        return this;
    }

    public String payeeUsability() {
        return getFieldAsString(field(28));
    }

    public X937ImageViewAnalysisRecord payeeUsability(String value) {
        setField(value, field(28));
        return this;
    }

    public String convenienceAmountUsability() {
        return getFieldAsString(field(29));
    }

    public X937ImageViewAnalysisRecord convenienceAmountUsability(String value) {
        setField(value, field(29));
        return this;
    }

    public String legalAmountUsability() {
        return getFieldAsString(field(30));
    }

    public X937ImageViewAnalysisRecord legalAmountUsability(String value) {
        setField(value, field(30));
        return this;
    }

    public String signatureUsability() {
        return getFieldAsString(field(31));
    }

    public X937ImageViewAnalysisRecord signatureUsability(String value) {
        setField(value, field(31));
        return this;
    }

    public String payorNameAndAddressUsability() {
        return getFieldAsString(field(32));
    }

    public X937ImageViewAnalysisRecord payorNameAndAddressUsability(String value) {
        setField(value, field(32));
        return this;
    }

    public String MICRLineUsability() {
        return getFieldAsString(field(33));
    }

    public X937ImageViewAnalysisRecord MICRLineUsability(String value) {
        setField(value, field(33));
        return this;
    }

    public String memoLineUsability() {
        return getFieldAsString(field(34));
    }

    public X937ImageViewAnalysisRecord memoLineUsability(String value) {
        setField(value, field(34));
        return this;
    }

    public String payorBankNameAndAddressUsability() {
        return getFieldAsString(field(35));
    }

    public X937ImageViewAnalysisRecord payorBankNameAndAddressUsability(String value) {
        setField(value, field(35));
        return this;
    }

    public String payeeEndorsementUsability() {
        return getFieldAsString(field(36));
    }

    public X937ImageViewAnalysisRecord payeeEndorsementUsability(String value) {
        setField(value, field(36));
        return this;
    }

    public String bankOfFirstDepositEndorsementUsability() {
        return getFieldAsString(field(37));
    }

    public X937ImageViewAnalysisRecord bankOfFirstDepositEndorsementUsability(String value) {
        setField(value, field(37));
        return this;
    }

    public String transitEndorsementUsability() {
        return getFieldAsString(field(38));
    }

    public X937ImageViewAnalysisRecord transitEndorsementUsability(String value) {
        setField(value, field(38));
        return this;
    }

    public String reserved39() {
        return getFieldAsString(field(39));
    }

    public X937ImageViewAnalysisRecord reserved39(String value) {
        setField(value, field(39));
        return this;
    }

    public String reserved40() {
        return getFieldAsString(field(40));
    }

    public X937ImageViewAnalysisRecord reserved40(String value) {
        setField(value, field(40));
        return this;
    }

    public String reserved41() {
        return getFieldAsString(field(41));
    }

    public X937ImageViewAnalysisRecord reserved41(String value) {
        setField(value, field(41));
        return this;
    }

    public String reserved42() {
        return getFieldAsString(field(42));
    }

    public X937ImageViewAnalysisRecord reserved42(String value) {
        setField(value, field(42));
        return this;
    }

    public String reserved43() {
        return getFieldAsString(field(43));
    }

    public X937ImageViewAnalysisRecord reserved43(String value) {
        setField(value, field(43));
        return this;
    }

    public String reserved44() {
        return getFieldAsString(field(44));
    }

    public X937ImageViewAnalysisRecord reserved44(String value) {
        setField(value, field(44));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(45));
    }

    public X937ImageViewAnalysisRecord userField(String value) {
        setField(value, field(45));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(46));
    }

    public X937ImageViewAnalysisRecord reserved(String value) {
        setField(value, field(46));
        return this;
    }

}

