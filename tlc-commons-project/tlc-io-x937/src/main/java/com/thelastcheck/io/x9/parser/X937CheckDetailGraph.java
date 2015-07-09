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
package com.thelastcheck.io.x9.parser;

import java.util.List;

import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumCRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;

public interface X937CheckDetailGraph extends X937BundleGraph {

    X937CheckDetailRecord checkDetailRecord();

    List<X937CheckDetailAddendumARecord> checkDetailAddendumARecords();

    X937CheckDetailAddendumBRecord checkDetailAddendumBRecord();

    List<X937CheckDetailAddendumCRecord> checkDetailAddendumCRecords();

    List<X937ImageViewRecords> imageViewRecords();

}
