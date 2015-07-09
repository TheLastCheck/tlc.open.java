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

public interface X937CashLetterControlRecord extends X9Record {


    /*
     * X937CashLetterControlRecord
     */

    String bundleCount();
    X937CashLetterControlRecord bundleCount(String value);

    public int bundleCountAsInt()
        throws InvalidDataException;
    X937CashLetterControlRecord bundleCount(int value);

    String itemsWithinCashletterCount();
    X937CashLetterControlRecord itemsWithinCashletterCount(String value);

    public int itemsWithinCashletterCountAsInt()
        throws InvalidDataException;
    X937CashLetterControlRecord itemsWithinCashletterCount(int value);

    String cashLetterTotalAmount();
    X937CashLetterControlRecord cashLetterTotalAmount(String value);

    public long cashLetterTotalAmountAsLong()
        throws InvalidDataException;
    X937CashLetterControlRecord cashLetterTotalAmount(long value);

    String imagesWithinCashLetterCount();
    X937CashLetterControlRecord imagesWithinCashLetterCount(String value);

    public long imagesWithinCashLetterCountAsLong()
        throws InvalidDataException;
    X937CashLetterControlRecord imagesWithinCashLetterCount(long value);

    String finalDestinationName();
    X937CashLetterControlRecord finalDestinationName(String value);

    String ECEInstitutionName();
    X937CashLetterControlRecord ECEInstitutionName(String value);

    public Date settlementDate()
        throws InvalidDataException;
    X937CashLetterControlRecord settlementDate(Date value);

    String settlementDateAsString();
    X937CashLetterControlRecord settlementDate(String value);

    String reserved();
    X937CashLetterControlRecord reserved(String value);

}

