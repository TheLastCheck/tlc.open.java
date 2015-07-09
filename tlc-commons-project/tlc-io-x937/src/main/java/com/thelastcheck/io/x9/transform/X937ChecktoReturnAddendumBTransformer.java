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

package com.thelastcheck.io.x9.transform;

import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumBRecord;

public class X937ChecktoReturnAddendumBTransformer extends
		X9Transformer<X937CheckDetailRecord, X937ReturnAddendumBRecord> {

	public X937ChecktoReturnAddendumBTransformer(X9RecordFactory factory) {
		super(factory);
	}

	@Override
	public X937ReturnAddendumBRecord apply(X937CheckDetailRecord checkRecord) {
		X937ReturnAddendumBRecord record = (X937ReturnAddendumBRecord) factory
				.newX9Record(X9Record.TYPE_RETURN_ADDENDUM_B);
		record.auxiliaryOnUs(checkRecord.auxiliaryOnUs());
		return record;
	}

}
