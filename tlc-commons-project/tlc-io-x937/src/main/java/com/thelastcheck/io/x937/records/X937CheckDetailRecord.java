/**
 * ****************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ****************************************************************************
 */

package com.thelastcheck.io.x937.records;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.io.x9.X9Record;

public interface X937CheckDetailRecord extends X9Record {


    /*
     * X937CheckDetailRecord
     */

    String auxiliaryOnUs();

    X937CheckDetailRecord auxiliaryOnUs(String value);

    String externalProcessingCode();

    X937CheckDetailRecord externalProcessingCode(String value);

    RoutingNumber payorBankRoutingNumber();

    X937CheckDetailRecord payorBankRoutingNumber(RoutingNumber value);

    String payorBankRoutingNumberAsString();

    X937CheckDetailRecord payorBankRoutingNumber(String value);

    String payorBankRoutingNumberCheckDigit();

    X937CheckDetailRecord payorBankRoutingNumberCheckDigit(String value);

    OnUsField onUs();

    X937CheckDetailRecord onUs(OnUsField value);

    String onUsAsString();

    X937CheckDetailRecord onUs(String value);

    String itemAmount();

    X937CheckDetailRecord itemAmount(String value);

    public long itemAmountAsLong()
            throws InvalidDataException;

    X937CheckDetailRecord itemAmount(long value);

    String ECEInstitutionItemSequenceNumber();

    X937CheckDetailRecord ECEInstitutionItemSequenceNumber(String value);

    String documentationTypeIndicator();

    X937CheckDetailRecord documentationTypeIndicator(String value);

    String returnAcceptanceIndicator();

    X937CheckDetailRecord returnAcceptanceIndicator(String value);

    String MICRValidIndicator();

    X937CheckDetailRecord MICRValidIndicator(String value);

    String BOFDIndicator();

    X937CheckDetailRecord BOFDIndicator(String value);

    String checkDetailRecordAddendumCount();

    X937CheckDetailRecord checkDetailRecordAddendumCount(String value);

    public int checkDetailRecordAddendumCountAsInt()
            throws InvalidDataException;

    X937CheckDetailRecord checkDetailRecordAddendumCount(int value);

    String correctionIndicator();

    X937CheckDetailRecord correctionIndicator(String value);

    String archiveTypeIndicator();

    X937CheckDetailRecord archiveTypeIndicator(String value);

    String onusFormatIndicator();

    X937CheckDetailRecord onusFormatIndicator(String value);

    String userField();

    X937CheckDetailRecord userField(String value);

    String reserved();

    X937CheckDetailRecord reserved(String value);

}

