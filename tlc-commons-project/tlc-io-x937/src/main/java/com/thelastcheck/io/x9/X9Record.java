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

package com.thelastcheck.io.x9;

import java.util.TimeZone;

import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Record;


public interface X9Record extends Record {

	String ENCODING_US_ASCII = ByteArray.ASCII_CHARSET_NAME;
	String ENCODING_EBCDIC = ByteArray.EBCDIC_CHARSET_NAME;

	String FIELD_RECORD_TYPE_NAME = "RecordType";
	int FIELD_RECORD_TYPE_NUMBER = 1;
	int STANDARD_LEVEL_1994 = 1;
	int STANDARD_LEVEL_2001 = 2;
	int STANDARD_LEVEL_DSTU = 3;
	int STANDARD_LEVEL_SVPCO = 13;
	int TYPE_FILE_HEADER = 01;
	int TYPE_CASH_LETTER_HEADER = 10;
	int TYPE_BUNDLE_HEADER = 20;
	int TYPE_CHECK_DETAIL = 25;
	int TYPE_CHECK_DETAIL_ADDENDUM_A = 26;
	int TYPE_CHECK_DETAIL_ADDENDUM_B = 27;
	int TYPE_CHECK_DETAIL_ADDENDUM_C = 28;
	int TYPE_RETURN = 31;
	int TYPE_RETURN_ADDENDUM_A = 32;
	int TYPE_RETURN_ADDENDUM_B = 33;
	int TYPE_RETURN_ADDENDUM_C = 34;
	int TYPE_RETURN_ADDENDUM_D = 35;
	int TYPE_ACCOUNT_TOTALS_DETAIL = 40;
	int TYPE_NON_HIT_TOTALS_DETAIL = 41;
	int TYPE_IMAGE_VIEW_DETAIL = 50;
	int TYPE_IMAGE_VIEW_DATA = 52;
	int TYPE_IMAGE_VIEW_ANALYSIS = 54;
	int TYPE_BUNDLE_CONTROL = 70;
	int TYPE_BOX_SUMMARY = 75;
	int TYPE_ROUTING_NUMBER_SUMMARY = 85;
	int TYPE_CASH_LETTER_CONTROL = 90;
	int TYPE_FILE_CONTROL = 99;
	
	TimeZone x9TimeZone = TimeZone.getTimeZone("US/Eastern");

	/**
	 * @return the record type of this record.
	 */
	int recordType();

	/**
	 * @param recordType
	 *            the record type for this record.
	 */
	X9Record recordType(int recordType);

	/**
	 * @return the standard level for this record.
	 */
	int recordStandardLevel();

	/**
	 * @param standardLevel
	 *            the standard level for this record.
	 */
	X9Record recordStandardLevel(int standardLevel);

}