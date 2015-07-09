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

import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;
import com.thelastcheck.io.x937.records.X937ReturnRecord;

public class X937ChecktoReturnTransformer extends X9Transformer<X937CheckDetailRecord, X937ReturnRecord> {

	private String	forwardBundleDate;

	public X937ChecktoReturnTransformer(X9RecordFactory factory, String forwardBundleDate) {
		super(factory);
		this.forwardBundleDate = forwardBundleDate == null ? "" : forwardBundleDate;
	}

	@Override
	public X937ReturnRecord apply(X937CheckDetailRecord checkRecord) {
		X937ReturnRecord record = (X937ReturnRecord) factory.newX9Record(X9Record.TYPE_RETURN);
		record.externalProcessingCode(checkRecord.externalProcessingCode());
		record.itemAmount(checkRecord.itemAmount());
		record.onUsReturnRecord(checkRecord.onUs());
		record.payorBankRoutingNumber(checkRecord.payorBankRoutingNumber());
		if (checkRecord.payorBankRoutingNumberCheckDigit().equals(" ")) {
			record.payorBankRoutingNumberCheckDigit(RoutingNumber.calculateCheckDigit(checkRecord
					.payorBankRoutingNumberAsString()));
		} else {
			record.payorBankRoutingNumberCheckDigit(checkRecord.payorBankRoutingNumberCheckDigit());
		}
		record.ECEInstitutionItemSequenceNumber(checkRecord.ECEInstitutionItemSequenceNumber());
		record.forwardBundleDate(forwardBundleDate);
		record.returnDocumentationTypeIndicator("G");
		record.returnReason(" ");
		record.returnNotificationIndicator("2");
		record.numberOfTimesReturned("0");
		record.returnRecordAddendumCount(0);
		return record;
	}

}
