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

public interface X937ReturnAddendumDRecord extends X9Record {


    /*
     * X937ReturnAddendumDRecord
     */

    String returnAddendumDRecordNumber();

    X937ReturnAddendumDRecord returnAddendumDRecordNumber(String value);

    public int returnAddendumDRecordNumberAsInt()
            throws InvalidDataException;

    X937ReturnAddendumDRecord returnAddendumDRecordNumber(int value);

    RoutingNumber endorsingBankRoutingNumber();

    X937ReturnAddendumDRecord endorsingBankRoutingNumber(RoutingNumber value);

    String endorsingBankRoutingNumberAsString();

    X937ReturnAddendumDRecord endorsingBankRoutingNumber(String value);

    public Date endorsingBankEndorsementDate()
            throws InvalidDataException;

    X937ReturnAddendumDRecord endorsingBankEndorsementDate(Date value);

    String endorsingBankEndorsementDateAsString();

    X937ReturnAddendumDRecord endorsingBankEndorsementDate(String value);

    String endorsingBankItemSequenceNumber();

    X937ReturnAddendumDRecord endorsingBankItemSequenceNumber(String value);

    String truncationIndicator();

    X937ReturnAddendumDRecord truncationIndicator(String value);

    String endorsingBankConversionIndicator();

    X937ReturnAddendumDRecord endorsingBankConversionIndicator(String value);

    String endorsingBankCorrectionIndicator();

    X937ReturnAddendumDRecord endorsingBankCorrectionIndicator(String value);

    String returnReason();

    X937ReturnAddendumDRecord returnReason(String value);

    String userField();

    X937ReturnAddendumDRecord userField(String value);

    String reserved();

    X937ReturnAddendumDRecord reserved(String value);

}

