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

public interface X937ReturnRecord extends X9Record {


    /*
     * X937ReturnRecord
     */

    RoutingNumber payorBankRoutingNumber();
    X937ReturnRecord payorBankRoutingNumber(RoutingNumber value);

    String payorBankRoutingNumberAsString();
    X937ReturnRecord payorBankRoutingNumber(String value);

    String payorBankRoutingNumberCheckDigit();
    X937ReturnRecord payorBankRoutingNumberCheckDigit(String value);

    OnUsField onUsReturnRecord();
    X937ReturnRecord onUsReturnRecord(OnUsField value);

    String onUsReturnRecordAsString();
    X937ReturnRecord onUsReturnRecord(String value);

    String itemAmount();
    X937ReturnRecord itemAmount(String value);

    public long itemAmountAsLong()
        throws InvalidDataException;
    X937ReturnRecord itemAmount(long value);

    String returnReason();
    X937ReturnRecord returnReason(String value);

    String returnRecordAddendumCount();
    X937ReturnRecord returnRecordAddendumCount(String value);

    public int returnRecordAddendumCountAsInt()
        throws InvalidDataException;
    X937ReturnRecord returnRecordAddendumCount(int value);

    String returnDocumentationTypeIndicator();
    X937ReturnRecord returnDocumentationTypeIndicator(String value);

    public Date forwardBundleDate()
        throws InvalidDataException;
    X937ReturnRecord forwardBundleDate(Date value);

    String forwardBundleDateAsString();
    X937ReturnRecord forwardBundleDate(String value);

    String ECEInstitutionItemSequenceNumber();
    X937ReturnRecord ECEInstitutionItemSequenceNumber(String value);

    String externalProcessingCode();
    X937ReturnRecord externalProcessingCode(String value);

    String returnNotificationIndicator();
    X937ReturnRecord returnNotificationIndicator(String value);

    String returnArchiveTypeIndicator();
    X937ReturnRecord returnArchiveTypeIndicator(String value);

    String numberOfTimesReturned();
    X937ReturnRecord numberOfTimesReturned(String value);

    String reserved();
    X937ReturnRecord reserved(String value);

    String payorAccountName();
    X937ReturnRecord payorAccountName(String value);

    String userField();
    X937ReturnRecord userField(String value);

}

