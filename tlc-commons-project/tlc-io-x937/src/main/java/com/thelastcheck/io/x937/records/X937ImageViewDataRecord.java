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
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.x9.X9Record;

import java.util.Date;

public interface X937ImageViewDataRecord extends X9Record {


    /*
     * X937ImageViewDataRecord
     */

    RoutingNumber ECEInstitutionRoutingNumber();

    X937ImageViewDataRecord ECEInstitutionRoutingNumber(RoutingNumber value);

    String ECEInstitutionRoutingNumberAsString();

    X937ImageViewDataRecord ECEInstitutionRoutingNumber(String value);

    public Date bundleBusinessDate()
            throws InvalidDataException;

    X937ImageViewDataRecord bundleBusinessDate(Date value);

    String bundleBusinessDateAsString();

    X937ImageViewDataRecord bundleBusinessDate(String value);

    String cycleNumber();

    X937ImageViewDataRecord cycleNumber(String value);

    String ECEInstitutionItemSequenceNumber();

    X937ImageViewDataRecord ECEInstitutionItemSequenceNumber(String value);

    String securityOriginatorName();

    X937ImageViewDataRecord securityOriginatorName(String value);

    String securityAuthenticatorName();

    X937ImageViewDataRecord securityAuthenticatorName(String value);

    String securityKeyName();

    X937ImageViewDataRecord securityKeyName(String value);

    String clippingOrigin();

    X937ImageViewDataRecord clippingOrigin(String value);

    String clippingCoordinateH1();

    X937ImageViewDataRecord clippingCoordinateH1(String value);

    String clippingCoordinateH2();

    X937ImageViewDataRecord clippingCoordinateH2(String value);

    String clippingCoordinateV1();

    X937ImageViewDataRecord clippingCoordinateV1(String value);

    String clippingCoordinateV2();

    X937ImageViewDataRecord clippingCoordinateV2(String value);

    String lengthOfImageReferenceKey();

    public int lengthOfImageReferenceKeyAsInt()
            throws InvalidDataException;

    String imageReferenceKey();

    X937ImageViewDataRecord imageReferenceKey(String value);

    String lengthOfDigitalSignature();

    public int lengthOfDigitalSignatureAsInt()
            throws InvalidDataException;

    ByteArray digitalSignature();

    X937ImageViewDataRecord digitalSignature(ByteArray value);

    String lengthOfImageData();

    public int lengthOfImageDataAsInt()
            throws InvalidDataException;

    ByteArray imageData();

    X937ImageViewDataRecord imageData(ByteArray value);

}

