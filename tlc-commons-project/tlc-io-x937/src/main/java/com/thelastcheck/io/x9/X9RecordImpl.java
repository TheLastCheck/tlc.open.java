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

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.base.RecordImpl;

public abstract class X9RecordImpl extends RecordImpl implements X9Record {

	private int recordType;
	private int recordStandardLevel;
	protected static Field recordTypeField = new Field(FIELD_RECORD_TYPE_NAME,
			FIELD_RECORD_TYPE_NUMBER, 0, 2, FieldType.INT);
	private static Field headerStandardLevelField = new Field("standardLevel",
			2, 2, 2, FieldType.INT);

	/**
	 * Create an empty X9.37 record object as EBCDIC with no record type or
	 * standard level.
	 */
	public X9RecordImpl() {
		this(0, ENCODING_EBCDIC, X9Record.STANDARD_LEVEL_DSTU);
	}

	/**
	 * Create an X9.37 record object. Most records are 80 bytes in length.
	 * Exceptions are handled in the sub-classes.
	 * 
	 * @param recordType
	 *            is the X9.37 record type.
	 * @param stdLevel
	 *            is the standard level of the file. This affects certain field
	 *            locations.
	 */
	public X9RecordImpl(int recordType, int stdLevel) {
		this(recordType, ENCODING_EBCDIC, stdLevel);
	}

	/**
	 * Create an X9.37 record object. Most records are 80 bytes in length.
	 * Exceptions are handled in the sub-classes.
	 * 
	 * @param recordType
	 *            is the X9.37 record type.
	 * @param encoding
	 *            is one of 2 acceptable encodings - US_ASCII or EBCDIC.
	 * @param stdLevel
	 *            is the standard level of the file. This affects certain field
	 *            locations.
	 */
	public X9RecordImpl(int recordType, String encoding, int stdLevel) {
		super(80, encoding);
		clearRecord();
		recordStandardLevel(stdLevel);
		recordType(recordType);
	}

	/**
	 * Create an X9.37 record object based on the byteArray.
	 * 
	 * @param record
	 *            is a byte array containing an X9.37 record.
	 * @param standardLevel
	 *            is the standard level of the file. This affects certain field
	 *            locations.
	 */
	public X9RecordImpl(ByteArray record, int standardLevel) {
		super(record);
		initializeRecordType();
		if (recordType() == X9Record.TYPE_FILE_HEADER) {
			try {
				standardLevel = headerStandardLevelField
						.extractStringAsInt(record);
			} catch (InvalidDataException e1) {
			}
		}
		recordStandardLevel(standardLevel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tlc.io.Record#clearRecord()
	 */
	public void clearRecord() {
		int recType = recordType();
		record().fill();
		recordType(recType);
	}

	/**
	 * Parse the first two bytes of the X9.37 record to get the X9.37 record
	 * type.
	 */
	private void initializeRecordType() {
		int recordType = 0;
		try {
			recordType = recordTypeField.extractStringAsInt(record());
		} catch (InvalidDataException e) {
		}
		recordType(recordType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tlc.io.x9.X9Record#recordType()
	 */
	public int recordType() {
		return recordType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tlc.io.x9.X9Record#recordType(int)
	 */
	public X9Record recordType(int recordType) {
		this.recordType = recordType;
		setField(recordType, recordTypeField);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tlc.io.x9.X9Record#recordStandardLevel()
	 */
	public int recordStandardLevel() {
		return recordStandardLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tlc.io.x9.X9Record#recordStandardLevel(int)
	 */
	public X9Record recordStandardLevel(int recordStandardLevel) {
		this.recordStandardLevel = recordStandardLevel;
		return this;
	}

}
