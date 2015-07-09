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

public interface X937AccountTotalsDetailRecord extends X9Record {


    /*
     * X937AccountTotalsDetailRecord
     */

    RoutingNumber destinationRoutingNumber();

    X937AccountTotalsDetailRecord destinationRoutingNumber(RoutingNumber value);

    String destinationRoutingNumberAsString();

    X937AccountTotalsDetailRecord destinationRoutingNumber(String value);

    String keyAccountOrLowAccountInKeyAccountRange();

    X937AccountTotalsDetailRecord keyAccountOrLowAccountInKeyAccountRange(String value);

    String keyAccountOrHighAccountInKeyAccountRange();

    X937AccountTotalsDetailRecord keyAccountOrHighAccountInKeyAccountRange(String value);

    String totalItemCount();

    X937AccountTotalsDetailRecord totalItemCount(String value);

    public long totalItemCountAsLong()
            throws InvalidDataException;

    X937AccountTotalsDetailRecord totalItemCount(long value);

    String totalItemAmount();

    X937AccountTotalsDetailRecord totalItemAmount(String value);

    public long totalItemAmountAsLong()
            throws InvalidDataException;

    X937AccountTotalsDetailRecord totalItemAmount(long value);

    String userField();

    X937AccountTotalsDetailRecord userField(String value);

    String reserved();

    X937AccountTotalsDetailRecord reserved(String value);

}

