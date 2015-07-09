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

public interface X937BoxSummaryRecord extends X9Record {


    /*
     * X937BoxSummaryRecord
     */

    RoutingNumber destinationRoutingNumber();
    X937BoxSummaryRecord destinationRoutingNumber(RoutingNumber value);

    String destinationRoutingNumberAsString();
    X937BoxSummaryRecord destinationRoutingNumber(String value);

    String boxSequenceNumber();
    X937BoxSummaryRecord boxSequenceNumber(String value);

    String boxBundleCount();
    X937BoxSummaryRecord boxBundleCount(String value);

    public int boxBundleCountAsInt()
        throws InvalidDataException;
    X937BoxSummaryRecord boxBundleCount(int value);

    String boxNumberID();
    X937BoxSummaryRecord boxNumberID(String value);

    String boxTotalAmount();
    X937BoxSummaryRecord boxTotalAmount(String value);

    public long boxTotalAmountAsLong()
        throws InvalidDataException;
    X937BoxSummaryRecord boxTotalAmount(long value);

    String reserved();
    X937BoxSummaryRecord reserved(String value);

}

