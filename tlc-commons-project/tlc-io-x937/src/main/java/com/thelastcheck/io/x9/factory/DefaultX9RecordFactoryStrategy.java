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

package com.thelastcheck.io.x9.factory;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;

public class DefaultX9RecordFactoryStrategy implements X9RecordFactoryStrategy {

    public X9RecordFactory factory(X937FileHeaderRecord fileHeaderRecord) {
        String standardIdentifier = null;
        int standardLevel = 0;
        try {
            standardLevel = fileHeaderRecord.standardLevelAsInt();
        } catch (InvalidDataException e) {
            throw new InvalidStandardLevelException();
        }
        String encoding = fileHeaderRecord.record().getEncoding();
        if (standardLevel == X9Record.STANDARD_LEVEL_DSTU) {
            standardIdentifier = X937_STANDARD_DSTU;
        } else if (standardLevel == X9Record.STANDARD_LEVEL_SVPCO) {
            standardIdentifier = X937_STANDARD_SVPCO;
        } else if (standardLevel == X9Record.STANDARD_LEVEL_2001) {
            standardIdentifier = X937_STANDARD_2001;
        } else if (standardLevel == X9Record.STANDARD_LEVEL_1994) {
            standardIdentifier = X937_STANDARD_1994;
        } else {
            throw new InvalidStandardLevelException();
        }
        X9RecordFactory factory = factory(standardIdentifier, encoding);
        return factory;
    }

    public X9RecordFactory factory(String standardIdentifier) {
        return factory(standardIdentifier, X9Record.ENCODING_EBCDIC);
    }

    public X9RecordFactory factory(String standardIdentifier, String encoding) {
        X9RecordFactory factory = null;
        if (standardIdentifier.equals(X937_STANDARD_DSTU)) {
            factory = new X937RecordFactoryDSTU(encoding);
        } else if (standardIdentifier.equals(X937_STANDARD_SVPCO)) {
            factory = new X937RecordFactorySVPCO(encoding);
        } else if (standardIdentifier.equals(X937_STANDARD_1994)) {
            factory = new X937RecordFactory1994(encoding);
        } else if (standardIdentifier.equals(X937_STANDARD_2001)) {
            factory = new X937RecordFactory2001(encoding);
        } else {
            throw new InvalidStandardLevelException();
        }
        return factory;
    }

}
