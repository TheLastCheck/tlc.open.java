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

public interface X937RoutingNumberSummaryRecord extends X9Record {


    /*
     * X937RoutingNumberSummaryRecord
     */

    RoutingNumber routingNumberWithinCashLetter();

    X937RoutingNumberSummaryRecord routingNumberWithinCashLetter(RoutingNumber value);

    String routingNumberWithinCashLetterAsString();

    X937RoutingNumberSummaryRecord routingNumberWithinCashLetter(String value);

    String routingNumberTotalAmount();

    X937RoutingNumberSummaryRecord routingNumberTotalAmount(String value);

    public long routingNumberTotalAmountAsLong()
            throws InvalidDataException;

    X937RoutingNumberSummaryRecord routingNumberTotalAmount(long value);

    String routingNumberItemCount();

    X937RoutingNumberSummaryRecord routingNumberItemCount(String value);

    public int routingNumberItemCountAsInt()
            throws InvalidDataException;

    X937RoutingNumberSummaryRecord routingNumberItemCount(int value);

    String userField();

    X937RoutingNumberSummaryRecord userField(String value);

    String reserved();

    X937RoutingNumberSummaryRecord reserved(String value);

}

