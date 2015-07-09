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

import com.thelastcheck.io.x937.records.X937FileHeaderRecord;

public interface X9RecordFactoryStrategy {

    String X937_STANDARD_1994  = "1994";
    String X937_STANDARD_2001  = "2001";
    String X937_STANDARD_DSTU  = "DSTU";
    String X937_STANDARD_SVPCO = "SVPCO";

    /**
     * Return a factory suitable for processing files based on the given
     * standard identifier.
     * 
     * @param standardIdentifier
     *            is an identifier of the standard to detemine what kind of
     *            factory need
     * @return an X9RecordFactory based on the given standard.
     */
    X9RecordFactory factory(String standardIdentifier);
    X9RecordFactory factory(String standardIdentifier, String encoding);

    /**
     * @param fileHeaderRecord
     * @return an X9RecordFactory based on the given standard.
     */
    X9RecordFactory factory(X937FileHeaderRecord fileHeaderRecord);
}
