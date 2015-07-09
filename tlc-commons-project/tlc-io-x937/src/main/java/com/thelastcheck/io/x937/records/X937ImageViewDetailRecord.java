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

public interface X937ImageViewDetailRecord extends X9Record {


    /*
     * X937ImageViewDetailRecord
     */

    String imageIndicator();

    X937ImageViewDetailRecord imageIndicator(String value);

    RoutingNumber imageCreatorRoutingNumber();

    X937ImageViewDetailRecord imageCreatorRoutingNumber(RoutingNumber value);

    String imageCreatorRoutingNumberAsString();

    X937ImageViewDetailRecord imageCreatorRoutingNumber(String value);

    public Date imageCreatorDate()
            throws InvalidDataException;

    X937ImageViewDetailRecord imageCreatorDate(Date value);

    String imageCreatorDateAsString();

    X937ImageViewDetailRecord imageCreatorDate(String value);

    String imageViewFormatIndicator();

    X937ImageViewDetailRecord imageViewFormatIndicator(String value);

    String imageViewCompressionAlgorithmIdentifier();

    X937ImageViewDetailRecord imageViewCompressionAlgorithmIdentifier(String value);

    String imageViewDataSize();

    X937ImageViewDetailRecord imageViewDataSize(String value);

    public int imageViewDataSizeAsInt()
            throws InvalidDataException;

    X937ImageViewDetailRecord imageViewDataSize(int value);

    String viewSideIndicator();

    X937ImageViewDetailRecord viewSideIndicator(String value);

    String viewDescriptor();

    X937ImageViewDetailRecord viewDescriptor(String value);

    String digitalSignatureIndicator();

    X937ImageViewDetailRecord digitalSignatureIndicator(String value);

    String digitalSignatureMethod();

    X937ImageViewDetailRecord digitalSignatureMethod(String value);

    String securityKeySize();

    X937ImageViewDetailRecord securityKeySize(String value);

    public int securityKeySizeAsInt()
            throws InvalidDataException;

    X937ImageViewDetailRecord securityKeySize(int value);

    String startOfProtectedData();

    X937ImageViewDetailRecord startOfProtectedData(String value);

    String lengthofProtectedData();

    X937ImageViewDetailRecord lengthofProtectedData(String value);

    public int lengthofProtectedDataAsInt()
            throws InvalidDataException;

    X937ImageViewDetailRecord lengthofProtectedData(int value);

    String imageRecreateIndicator();

    X937ImageViewDetailRecord imageRecreateIndicator(String value);

    String userField();

    X937ImageViewDetailRecord userField(String value);

    String imageTiffVarianceIndicator();

    X937ImageViewDetailRecord imageTiffVarianceIndicator(String value);

    String overrideIndicator();

    X937ImageViewDetailRecord overrideIndicator(String value);

    String reserved();

    X937ImageViewDetailRecord reserved(String value);

}

