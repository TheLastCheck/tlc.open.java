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

public interface X937FileHeaderRecord extends X9Record {


    /*
     * X937FileHeaderRecord
     */

    String standardLevel();

    X937FileHeaderRecord standardLevel(String value);

    public int standardLevelAsInt()
            throws InvalidDataException;

    X937FileHeaderRecord standardLevel(int value);

    String testFileIndicator();

    X937FileHeaderRecord testFileIndicator(String value);

    RoutingNumber immediateDestinationRoutingNumber();

    X937FileHeaderRecord immediateDestinationRoutingNumber(RoutingNumber value);

    String immediateDestinationRoutingNumberAsString();

    X937FileHeaderRecord immediateDestinationRoutingNumber(String value);

    RoutingNumber immediateOriginRoutingNumber();

    X937FileHeaderRecord immediateOriginRoutingNumber(RoutingNumber value);

    String immediateOriginRoutingNumberAsString();

    X937FileHeaderRecord immediateOriginRoutingNumber(String value);

    public Date fileCreationDate()
            throws InvalidDataException;

    X937FileHeaderRecord fileCreationDate(Date value);

    String fileCreationDateAsString();

    X937FileHeaderRecord fileCreationDate(String value);

    public Date fileCreationTime()
            throws InvalidDataException;

    X937FileHeaderRecord fileCreationTime(Date value);

    String fileCreationTimeAsString();

    X937FileHeaderRecord fileCreationTime(String value);

    String resendIndicator();

    X937FileHeaderRecord resendIndicator(String value);

    String immediateDestinationName();

    X937FileHeaderRecord immediateDestinationName(String value);

    String immediateOriginName();

    X937FileHeaderRecord immediateOriginName(String value);

    String fileIDModifier();

    X937FileHeaderRecord fileIDModifier(String value);

    String countryCode();

    X937FileHeaderRecord countryCode(String value);

    String userField();

    X937FileHeaderRecord userField(String value);

    String reserved();

    X937FileHeaderRecord reserved(String value);

}

