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
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumARecord;

public class X937CheckAddendumAtoReturnAddendumATransformer extends
		X9Transformer<X937CheckDetailAddendumARecord, X937ReturnAddendumARecord> {

	public X937CheckAddendumAtoReturnAddendumATransformer(X9RecordFactory factory) {
		super(factory);
	}

	@Override
	public X937ReturnAddendumARecord apply(X937CheckDetailAddendumARecord record) {
		try {
			return transform(record);
		} catch (Exception e) {
			throw new TransformException(e);
		}
	}

	private X937ReturnAddendumARecord transform(X937CheckDetailAddendumARecord checkAddendumARecord)
			throws InvalidDataException {
		X937ReturnAddendumARecord record = (X937ReturnAddendumARecord) factory
				.newX9Record(X9Record.TYPE_RETURN_ADDENDUM_A);
		record.returnAddendumARecordNumber(checkAddendumARecord.checkDetailAddendumARecordNumberAsInt());
		record.BOFDBusinessDate(checkAddendumARecord.BOFDBusinessDateAsString());
		record.BOFDConversionIndicator(checkAddendumARecord.BOFDConversionIndicator());
		record.BOFDCorrectionIndicator(checkAddendumARecord.BOFDCorrectionIndicator());
		record.BOFDDepositBranch(checkAddendumARecord.BOFDDepositBranch());
		record.BOFDItemSequenceNumber(checkAddendumARecord.BOFDItemSequenceNumber());
		record.BOFDRoutingNumber(checkAddendumARecord.BOFDRoutingNumber());
		record.depositAccountNumberAtBOFD(checkAddendumARecord.depositAccountNumberAtBOFD());
		record.truncationIndicator(checkAddendumARecord.truncationIndicator());
		record.payeeName(checkAddendumARecord.payeeName());
		return record;
	}

}
