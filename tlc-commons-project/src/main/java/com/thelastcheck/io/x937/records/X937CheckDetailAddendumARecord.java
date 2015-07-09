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

public interface X937CheckDetailAddendumARecord extends X9Record {


    /*
     * X937CheckDetailAddendumARecord
     */

    String checkDetailAddendumARecordNumber();
    X937CheckDetailAddendumARecord checkDetailAddendumARecordNumber(String value);

    public int checkDetailAddendumARecordNumberAsInt()
        throws InvalidDataException;
    X937CheckDetailAddendumARecord checkDetailAddendumARecordNumber(int value);

    RoutingNumber BOFDRoutingNumber();
    X937CheckDetailAddendumARecord BOFDRoutingNumber(RoutingNumber value);

    String BOFDRoutingNumberAsString();
    X937CheckDetailAddendumARecord BOFDRoutingNumber(String value);

    public Date BOFDBusinessDate()
        throws InvalidDataException;
    X937CheckDetailAddendumARecord BOFDBusinessDate(Date value);

    String BOFDBusinessDateAsString();
    X937CheckDetailAddendumARecord BOFDBusinessDate(String value);

    String BOFDItemSequenceNumber();
    X937CheckDetailAddendumARecord BOFDItemSequenceNumber(String value);

    String depositAccountNumberAtBOFD();
    X937CheckDetailAddendumARecord depositAccountNumberAtBOFD(String value);

    String BOFDDepositBranch();
    X937CheckDetailAddendumARecord BOFDDepositBranch(String value);

    String payeeName();
    X937CheckDetailAddendumARecord payeeName(String value);

    String truncationIndicator();
    X937CheckDetailAddendumARecord truncationIndicator(String value);

    String BOFDConversionIndicator();
    X937CheckDetailAddendumARecord BOFDConversionIndicator(String value);

    String BOFDCorrectionIndicator();
    X937CheckDetailAddendumARecord BOFDCorrectionIndicator(String value);

    String userField();
    X937CheckDetailAddendumARecord userField(String value);

    String reserved();
    X937CheckDetailAddendumARecord reserved(String value);

}

