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

import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.exception.InvalidFormatException;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;
import com.thelastcheck.io.x937.records.X937ImageViewDataRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumCRecord;
import com.thelastcheck.io.x937.records.stddstu.X937AccountTotalsDetailRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937BoxSummaryRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937BundleControlRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937BundleHeaderRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937CashLetterControlRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937CashLetterHeaderRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937CheckDetailAddendumARecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937CheckDetailAddendumBRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937CheckDetailAddendumCRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937CheckDetailRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937FileControlRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937FileHeaderRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937ImageViewAnalysisRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937ImageViewDataRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937ImageViewDetailRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937NonHitTotalsDetailRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937ReturnAddendumARecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937ReturnAddendumBRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937ReturnAddendumCRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937ReturnAddendumDRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937ReturnRecordImpl;
import com.thelastcheck.io.x937.records.stddstu.X937RoutingNumberSummaryRecordImpl;

public class X937RecordFactoryDSTU implements X9RecordFactory {

	private static final String	BAD_RECORD_TYPE_NOT_A_VALID_2_DIGIT_NUMBER	= "Bad record type - not a valid 2-digit number";
	private static final String	RECORD_TYPE									= "Record type [";
	private static final String	IS_NOT_A_VALID_X9_37_TYPE					= "] is not a valid X9.37 type";
	private static final String	RECORD_SIZE_OF								= "Record size of [";
	private static final String	IS_LESS_THAN_MINIMUM_RECORD_SIZE			= "] is less than minimum record size.";

	private int					standardLevel								= X9Record.STANDARD_LEVEL_DSTU;
	private String				encoding;
	private ByteArray			emptyByteArray								= new ByteArray(0);									;

	public X937RecordFactoryDSTU() {
		encoding = X9Record.ENCODING_EBCDIC;
	}

	public X937RecordFactoryDSTU(String encoding) {
		this.encoding = encoding;
	}

	protected void standardLevel(int standardLevel) {
		this.standardLevel = standardLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tlc.io.x9.factory.X9RecordFactory#newX9Record(int)
	 */
	public Record newX9Record(int recordType) throws InvalidFormatException {
		Record x9Record = null;
		switch (recordType) {
		case X9Record.TYPE_CHECK_DETAIL:
			x9Record = new X937CheckDetailRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_A:
			x9Record = new X937CheckDetailAddendumARecordImpl(encoding,	standardLevel);
			break;
		case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_B:
			x9Record = new X937CheckDetailAddendumBRecordImpl(encoding,	standardLevel);
			// since we have a default size record of 80 bytes, need to set
			// fixed length indicator.
			X937CheckDetailAddendumBRecord xcdabr = (X937CheckDetailAddendumBRecord) x9Record;
			xcdabr.variableSizeRecordIndicator("0");
			break;
		case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_C:
			x9Record = new X937CheckDetailAddendumCRecordImpl(encoding,	standardLevel);
			break;
		case X9Record.TYPE_RETURN:
			x9Record = new X937ReturnRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_RETURN_ADDENDUM_A:
			x9Record = new X937ReturnAddendumARecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_RETURN_ADDENDUM_B:
			x9Record = new X937ReturnAddendumBRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_RETURN_ADDENDUM_C:
			x9Record = new X937ReturnAddendumCRecordImpl(encoding, standardLevel);
			// since we have a default size record of 80 bytes, need to set
			// fixed length indicator.
			X937ReturnAddendumCRecord xracr = (X937ReturnAddendumCRecord) x9Record;
			xracr.variableSizeRecordIndicator("0");
			break;
		case X9Record.TYPE_RETURN_ADDENDUM_D:
			x9Record = new X937ReturnAddendumDRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_IMAGE_VIEW_DETAIL:
			x9Record = new X937ImageViewDetailRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_IMAGE_VIEW_DATA:
			// record size of 117 must be used instead of default of 80
			ByteArray newRecord = new ByteArray(117, encoding);
			newRecord.fill();
			newRecord.write("52", 0);
			x9Record = new X937ImageViewDataRecordImpl(newRecord, standardLevel);
			X937ImageViewDataRecord ivdr = (X937ImageViewDataRecord) x9Record;
			// The following sets will ensure the lengths are set to 0.
			ivdr.imageReferenceKey("");
			ivdr.digitalSignature(emptyByteArray);
			ivdr.imageData(emptyByteArray);
			break;
		case X9Record.TYPE_IMAGE_VIEW_ANALYSIS:
			x9Record = new X937ImageViewAnalysisRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_BUNDLE_CONTROL:
			x9Record = new X937BundleControlRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_BUNDLE_HEADER:
			x9Record = new X937BundleHeaderRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_CASH_LETTER_CONTROL:
			x9Record = new X937CashLetterControlRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_CASH_LETTER_HEADER:
			x9Record = new X937CashLetterHeaderRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_FILE_CONTROL:
			x9Record = new X937FileControlRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_FILE_HEADER:
			x9Record = new X937FileHeaderRecordImpl(encoding, standardLevel);
			X937FileHeaderRecord fhr = (X937FileHeaderRecord) x9Record;
			fhr.standardLevel(standardLevel);
			break;
		case X9Record.TYPE_ACCOUNT_TOTALS_DETAIL:
			x9Record = new X937AccountTotalsDetailRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_BOX_SUMMARY:
			x9Record = new X937BoxSummaryRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_NON_HIT_TOTALS_DETAIL:
			x9Record = new X937NonHitTotalsDetailRecordImpl(encoding, standardLevel);
			break;
		case X9Record.TYPE_ROUTING_NUMBER_SUMMARY:
			x9Record = new X937RoutingNumberSummaryRecordImpl(encoding, standardLevel);
			break;
		default:
			throw new InvalidFormatException(RECORD_TYPE + recordType
					+ IS_NOT_A_VALID_X9_37_TYPE);
		}

		return x9Record;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tlc.io.x9.factory.X9RecordFactory#newX9Record(com.tlc.base.utils. ByteArray)
	 */
	public X9Record newX9Record(ByteArray record) throws InvalidFormatException {
		if (record.getLength() < 2) {
			throw new InvalidFormatException(RECORD_SIZE_OF
					+ record.getLength() + IS_LESS_THAN_MINIMUM_RECORD_SIZE);
		}
		X9Record x9Record = null;
		String recordTypeString = record.readAsString(0, 2, false);
		int recordType = 0;
		try {
			recordType = Integer.parseInt(recordTypeString);
		} catch (NumberFormatException e) {
			throw new InvalidFormatException(
					BAD_RECORD_TYPE_NOT_A_VALID_2_DIGIT_NUMBER);
		}
		switch (recordType) {
		case X9Record.TYPE_CHECK_DETAIL:
			x9Record = new X937CheckDetailRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_A:
			x9Record = new X937CheckDetailAddendumARecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_B:
			x9Record = new X937CheckDetailAddendumBRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_C:
			x9Record = new X937CheckDetailAddendumCRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_RETURN:
			x9Record = new X937ReturnRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_RETURN_ADDENDUM_A:
			x9Record = new X937ReturnAddendumARecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_RETURN_ADDENDUM_B:
			x9Record = new X937ReturnAddendumBRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_RETURN_ADDENDUM_C:
			x9Record = new X937ReturnAddendumCRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_RETURN_ADDENDUM_D:
			x9Record = new X937ReturnAddendumDRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_IMAGE_VIEW_DETAIL:
			x9Record = new X937ImageViewDetailRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_IMAGE_VIEW_DATA:
			x9Record = new X937ImageViewDataRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_IMAGE_VIEW_ANALYSIS:
			x9Record = new X937ImageViewAnalysisRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_BUNDLE_CONTROL:
			x9Record = new X937BundleControlRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_BUNDLE_HEADER:
			x9Record = new X937BundleHeaderRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_CASH_LETTER_CONTROL:
			x9Record = new X937CashLetterControlRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_CASH_LETTER_HEADER:
			x9Record = new X937CashLetterHeaderRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_FILE_CONTROL:
			x9Record = new X937FileControlRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_FILE_HEADER:
			x9Record = new X937FileHeaderRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_ACCOUNT_TOTALS_DETAIL:
			x9Record = new X937AccountTotalsDetailRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_BOX_SUMMARY:
			x9Record = new X937BoxSummaryRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_NON_HIT_TOTALS_DETAIL:
			x9Record = new X937NonHitTotalsDetailRecordImpl(record, standardLevel);
			break;
		case X9Record.TYPE_ROUTING_NUMBER_SUMMARY:
			x9Record = new X937RoutingNumberSummaryRecordImpl(record, standardLevel);
			break;
		default:
			throw new InvalidFormatException(RECORD_TYPE + recordType
					+ IS_NOT_A_VALID_X9_37_TYPE);
		}

		return x9Record;
	}

}
