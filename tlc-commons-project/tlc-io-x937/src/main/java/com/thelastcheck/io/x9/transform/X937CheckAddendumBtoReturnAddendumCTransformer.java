/*
 * Copyright (c) 2009-2020 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.thelastcheck.io.x9.transform;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumARecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumCRecord;

public class X937CheckAddendumBtoReturnAddendumCTransformer extends
		X9Transformer<X937CheckDetailAddendumBRecord, X937ReturnAddendumCRecord> {

	public X937CheckAddendumBtoReturnAddendumCTransformer(X9RecordFactory factory) {
		super(factory);
	}

	@Override
	public X937ReturnAddendumCRecord apply(X937CheckDetailAddendumBRecord record) {
		try {
			return transform(record);
		} catch (Exception e) {
			throw new TransformException(e);
		}
	}

	private X937ReturnAddendumCRecord transform(X937CheckDetailAddendumBRecord checkAddendumBRecord)
			throws InvalidDataException {
		X937ReturnAddendumCRecord record = (X937ReturnAddendumCRecord) factory
				.newX9Record(X9Record.TYPE_RETURN_ADDENDUM_C);
		record.variableSizeRecordIndicator(checkAddendumBRecord.variableSizeRecordIndicator());
		record.description(checkAddendumBRecord.description());
		record.imageArchiveSequenceNumber(checkAddendumBRecord.imageArchiveSequenceNumber());
		record.microfilmArchiveSequenceNumber(checkAddendumBRecord.microfilmArchiveSequenceNumber());
		record.userField(checkAddendumBRecord.userField().trim());
		record.reserved(checkAddendumBRecord.reserved().trim());
		return record;
	}

}
