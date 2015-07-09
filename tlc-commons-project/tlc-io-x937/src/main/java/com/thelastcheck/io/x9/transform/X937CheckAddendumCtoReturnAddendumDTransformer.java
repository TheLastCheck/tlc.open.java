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

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumCRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumDRecord;

public class X937CheckAddendumCtoReturnAddendumDTransformer
		extends X9Transformer<X937CheckDetailAddendumCRecord, X937ReturnAddendumDRecord> {

	public X937CheckAddendumCtoReturnAddendumDTransformer(X9RecordFactory factory) {
		super(factory);
	}

	@Override
	public X937ReturnAddendumDRecord apply(X937CheckDetailAddendumCRecord record) {
		try {
			return transform((X937CheckDetailAddendumCRecord) record);
		} catch (Exception e) {
			throw new TransformException(e);
		}
	}

	private X937ReturnAddendumDRecord transform(X937CheckDetailAddendumCRecord checkAddendumCRecord)
			throws InvalidDataException {
		X937ReturnAddendumDRecord record = (X937ReturnAddendumDRecord) factory
				.newX9Record(X9Record.TYPE_RETURN_ADDENDUM_D);
		record.returnAddendumDRecordNumber(checkAddendumCRecord.checkDetailAddendumCRecordNumberAsInt());
		record.endorsingBankRoutingNumber(checkAddendumCRecord.endorsingBankRoutingNumber());
		record.endorsingBankEndorsementDate(checkAddendumCRecord.endorsingBankEndorsementDateAsString());
		record.endorsingBankItemSequenceNumber(checkAddendumCRecord.endorsingBankItemSequenceNumber());
		record.truncationIndicator(checkAddendumCRecord.truncationIndicator());
		record.endorsingBankConversionIndicator(checkAddendumCRecord.endorsingBankConversionIndicator());
		record.endorsingBankCorrectionIndicator(checkAddendumCRecord.endorsingBankCorrectionIndicator());
		record.returnReason(checkAddendumCRecord.returnReason());
		return record;
	}

}
