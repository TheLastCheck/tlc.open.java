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

public interface X937ReturnAddendumARecord extends X9Record {


    /*
     * X937ReturnAddendumARecord
     */

    String returnAddendumARecordNumber();
    X937ReturnAddendumARecord returnAddendumARecordNumber(String value);

    public int returnAddendumARecordNumberAsInt()
        throws InvalidDataException;
    X937ReturnAddendumARecord returnAddendumARecordNumber(int value);

    RoutingNumber BOFDRoutingNumber();
    X937ReturnAddendumARecord BOFDRoutingNumber(RoutingNumber value);

    String BOFDRoutingNumberAsString();
    X937ReturnAddendumARecord BOFDRoutingNumber(String value);

    String BOFDBusinessDateConfidenceIndicator();
    X937ReturnAddendumARecord BOFDBusinessDateConfidenceIndicator(String value);

    public Date BOFDBusinessDate()
        throws InvalidDataException;
    X937ReturnAddendumARecord BOFDBusinessDate(Date value);

    String BOFDBusinessDateAsString();
    X937ReturnAddendumARecord BOFDBusinessDate(String value);

    String BOFDItemSequenceNumberConfidenceIndicator();
    X937ReturnAddendumARecord BOFDItemSequenceNumberConfidenceIndicator(String value);

    String BOFDItemSequenceNumber();
    X937ReturnAddendumARecord BOFDItemSequenceNumber(String value);

    String depositAccountNumberAtBOFDConfidenceIndicator();
    X937ReturnAddendumARecord depositAccountNumberAtBOFDConfidenceIndicator(String value);

    String depositAccountNumberAtBOFD();
    X937ReturnAddendumARecord depositAccountNumberAtBOFD(String value);

    String BOFDDepositBranchConfidenceIndicator();
    X937ReturnAddendumARecord BOFDDepositBranchConfidenceIndicator(String value);

    String BOFDDepositBranch();
    X937ReturnAddendumARecord BOFDDepositBranch(String value);

    String payeeNameConfidenceIndicator();
    X937ReturnAddendumARecord payeeNameConfidenceIndicator(String value);

    String payeeName();
    X937ReturnAddendumARecord payeeName(String value);

    String truncationIndicator();
    X937ReturnAddendumARecord truncationIndicator(String value);

    String BOFDConversionIndicator();
    X937ReturnAddendumARecord BOFDConversionIndicator(String value);

    String BOFDCorrectionIndicator();
    X937ReturnAddendumARecord BOFDCorrectionIndicator(String value);

    String userField();
    X937ReturnAddendumARecord userField(String value);

    String reserved();
    X937ReturnAddendumARecord reserved(String value);

}

