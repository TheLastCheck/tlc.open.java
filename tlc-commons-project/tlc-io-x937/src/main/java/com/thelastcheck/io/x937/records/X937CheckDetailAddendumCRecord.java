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
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.io.x9.X9Record;

import java.util.Date;

public interface X937CheckDetailAddendumCRecord extends X9Record {


    /*
     * X937CheckDetailAddendumCRecord
     */

    String checkDetailAddendumCRecordNumber();

    X937CheckDetailAddendumCRecord checkDetailAddendumCRecordNumber(String value);

    public int checkDetailAddendumCRecordNumberAsInt()
            throws InvalidDataException;

    X937CheckDetailAddendumCRecord checkDetailAddendumCRecordNumber(int value);

    RoutingNumber endorsingBankRoutingNumber();

    X937CheckDetailAddendumCRecord endorsingBankRoutingNumber(RoutingNumber value);

    String endorsingBankRoutingNumberAsString();

    X937CheckDetailAddendumCRecord endorsingBankRoutingNumber(String value);

    public Date endorsingBankEndorsementDate()
            throws InvalidDataException;

    X937CheckDetailAddendumCRecord endorsingBankEndorsementDate(Date value);

    String endorsingBankEndorsementDateAsString();

    X937CheckDetailAddendumCRecord endorsingBankEndorsementDate(String value);

    String endorsingBankItemSequenceNumber();

    X937CheckDetailAddendumCRecord endorsingBankItemSequenceNumber(String value);

    String truncationIndicator();

    X937CheckDetailAddendumCRecord truncationIndicator(String value);

    String endorsingBankConversionIndicator();

    X937CheckDetailAddendumCRecord endorsingBankConversionIndicator(String value);

    String endorsingBankCorrectionIndicator();

    X937CheckDetailAddendumCRecord endorsingBankCorrectionIndicator(String value);

    String returnReason();

    X937CheckDetailAddendumCRecord returnReason(String value);

    String userField();

    X937CheckDetailAddendumCRecord userField(String value);

    String reserved();

    X937CheckDetailAddendumCRecord reserved(String value);

}

