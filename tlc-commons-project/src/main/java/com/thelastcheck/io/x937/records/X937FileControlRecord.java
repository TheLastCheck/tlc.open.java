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

public interface X937FileControlRecord extends X9Record {


    /*
     * X937FileControlRecord
     */

    String cashLetterCount();
    X937FileControlRecord cashLetterCount(String value);

    public int cashLetterCountAsInt()
        throws InvalidDataException;
    X937FileControlRecord cashLetterCount(int value);

    String totalRecordCount();
    X937FileControlRecord totalRecordCount(String value);

    public int totalRecordCountAsInt()
        throws InvalidDataException;
    X937FileControlRecord totalRecordCount(int value);

    String totalItemCount();
    X937FileControlRecord totalItemCount(String value);

    public int totalItemCountAsInt()
        throws InvalidDataException;
    X937FileControlRecord totalItemCount(int value);

    String fileTotalAmount();
    X937FileControlRecord fileTotalAmount(String value);

    public long fileTotalAmountAsLong()
        throws InvalidDataException;
    X937FileControlRecord fileTotalAmount(long value);

    String immediateOriginContactName();
    X937FileControlRecord immediateOriginContactName(String value);

    String immediateOriginContactPhoneNumber();
    X937FileControlRecord immediateOriginContactPhoneNumber(String value);

    String reserved();
    X937FileControlRecord reserved(String value);

}

