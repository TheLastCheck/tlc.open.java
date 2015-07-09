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

public interface X937CashLetterHeaderRecord extends X9Record {


    /*
     * X937CashLetterHeaderRecord
     */

    String collectionTypeIndicator();
    X937CashLetterHeaderRecord collectionTypeIndicator(String value);

    RoutingNumber destinationRoutingNumber();
    X937CashLetterHeaderRecord destinationRoutingNumber(RoutingNumber value);

    String destinationRoutingNumberAsString();
    X937CashLetterHeaderRecord destinationRoutingNumber(String value);

    RoutingNumber ECEInstitutionRoutingNumber();
    X937CashLetterHeaderRecord ECEInstitutionRoutingNumber(RoutingNumber value);

    String ECEInstitutionRoutingNumberAsString();
    X937CashLetterHeaderRecord ECEInstitutionRoutingNumber(String value);

    public Date cashLetterBusinessDate()
        throws InvalidDataException;
    X937CashLetterHeaderRecord cashLetterBusinessDate(Date value);

    String cashLetterBusinessDateAsString();
    X937CashLetterHeaderRecord cashLetterBusinessDate(String value);

    public Date cashLetterCreationDate()
        throws InvalidDataException;
    X937CashLetterHeaderRecord cashLetterCreationDate(Date value);

    String cashLetterCreationDateAsString();
    X937CashLetterHeaderRecord cashLetterCreationDate(String value);

    public Date cashLetterCreationTime()
        throws InvalidDataException;
    X937CashLetterHeaderRecord cashLetterCreationTime(Date value);

    String cashLetterCreationTimeAsString();
    X937CashLetterHeaderRecord cashLetterCreationTime(String value);

    String cashLetterRecordTypeIndicator();
    X937CashLetterHeaderRecord cashLetterRecordTypeIndicator(String value);

    String cashLetterDocumentationTypeIndicator();
    X937CashLetterHeaderRecord cashLetterDocumentationTypeIndicator(String value);

    String emptyCashLetterIndicator();
    X937CashLetterHeaderRecord emptyCashLetterIndicator(String value);

    String truncationIndicator();
    X937CashLetterHeaderRecord truncationIndicator(String value);

    String cashLetterID();
    X937CashLetterHeaderRecord cashLetterID(String value);

    String originatorContactName();
    X937CashLetterHeaderRecord originatorContactName(String value);

    String originatorContactPhoneNumber();
    X937CashLetterHeaderRecord originatorContactPhoneNumber(String value);

    String fedWorkType();
    X937CashLetterHeaderRecord fedWorkType(String value);

    String userField();
    X937CashLetterHeaderRecord userField(String value);

    String reserved();
    X937CashLetterHeaderRecord reserved(String value);

}

