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

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.InputStreamRecordReader;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.exception.InvalidFormatException;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.factory.DefaultX9RecordFactoryStrategy;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x9.factory.X9RecordFactoryStrategy;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;
import com.thelastcheck.io.x937.records.stddstu.X937FileHeaderRecordImpl;

public class X9InputStreamRecordReader extends InputStreamRecordReader {

	private static final String FIRST_BYTE_MUST_BE_AN_ASCII_OR_EBCDIC_0 = "First byte must be an ASCII or EBCDIC '0'";
	private static final String FILE_HEADER_MUST_HAVE_A_RECORD_TYPE_01 = "File Header must have a record type '01'";
	private static final String FILE_HEADER_RECORD_MUST_BE_80_BYTES_IN_LENGTH = "File header record must be 80 bytes in length.";

	private String encoding = EBCDIC;
	private boolean variableLength = false;
	private X9RecordFactoryStrategy factoryStrategy;
	private X9RecordFactory factory;

	private boolean firstTime = true;
	private ByteArray lengthPrefixBuffer = new ByteArray(4);

	private int recordSize;

	/**
	 * @param inputStream
	 *            is an InputStream containing parsable X9 records.
	 */
	public X9InputStreamRecordReader(InputStream inputStream) {
		this(inputStream, null);
	}

	public X9InputStreamRecordReader(InputStream inputStream, boolean skipInvolidRecords) {
		this(inputStream, null, skipInvolidRecords);
	}

	/**
	 * Since this is intended to be an extensible framework, other users of this
	 * class may have a different X9.37 factory strategy to be used for
	 * allocating the factory that will create X9 records. This allows
	 * overriding the standard usage of records and fields in the X9.37 file. By
	 * allowing the calling user to specify an alternate factory strategy, this
	 * can be accommodated. If a factory strategy is not provided, then the
	 * default strategy is used.
	 * 
	 * @param inputStream
	 *            is an InputStream containing parsable X9 records.
	 * @param factoryStrategy
	 *            is an X9RecordFactoryStrategy used to determine the
	 *            appropriate X9 Factory to be used.
	 */
	public X9InputStreamRecordReader(InputStream inputStream,
			X9RecordFactoryStrategy factoryStrategy) {
		this(inputStream, factoryStrategy, false);
	}

	public X9InputStreamRecordReader(InputStream inputStream, X9RecordFactoryStrategy factoryStrategy, boolean skipInvalidRecords) {
		super(inputStream, skipInvalidRecords);
		if (factoryStrategy == null) {
			this.factoryStrategy = new DefaultX9RecordFactoryStrategy();
		} else {
			this.factoryStrategy = factoryStrategy;
		}
	}

	@Override
	protected Record readNextRecord() throws IOException, EOFException {
		X9Record x9Record;
		if (firstTime) {
			firstTime = false;
			x9Record = processFirstRecord();
			return x9Record;
		}

		recordSize = 80;
		if (variableLength) {
			int bytesRead = read(lengthPrefixBuffer);
			if (bytesRead < lengthPrefixBuffer.getLength()) {
				throw new EOFException(END_OF_STREAM_ERROR);
			}
			recordSize = lengthPrefixBuffer.readAsInt(0);
		}

		ByteArray record = new ByteArray(recordSize, this.encoding);
		int bytesRead = read(record);
		if (bytesRead < recordSize) {
			throw new EOFException(END_OF_STREAM_ERROR);
		}
		x9Record = factory.newX9Record(record);
		return x9Record;
	}

	/**
	 * This method reads the first record from the file which should be a file
	 * header record. Specail processing of the record is done to determine
	 * certain format characteristics of the file, like standard level,
	 * encoding, variable length records, etc.
	 * 
	 * @return the X9 file header record as the first record in the file.
	 * @throws IOException
	 * @throws EOFException
	 * @throws InvalidFormatException
	 * @throws InvalidDataException
	 * @throws InvalidStandardLevelException
	 */
	private X9Record processFirstRecord() throws IOException, EOFException,
			InvalidFormatException, InvalidStandardLevelException {
		ByteArray record = null;
		X9Record x937Record = null;
		int bytesRead = read(lengthPrefixBuffer);
		if (bytesRead < lengthPrefixBuffer.getLength()) {
			throw new EOFException(END_OF_STREAM_ERROR);
		}
		if (lengthPrefixBuffer.readAsByte(0) == 0x00) {
			variableLength = true;
			recordSize = lengthPrefixBuffer.readAsInt(0);
			if (recordSize != 80) {
				throw new InvalidFormatException(
						FILE_HEADER_RECORD_MUST_BE_80_BYTES_IN_LENGTH);
			}
			record = new ByteArray(recordSize);
			bytesRead = read(record);
			if (bytesRead < recordSize) {
				throw new EOFException(END_OF_STREAM_ERROR);
			}
		} else {
			record = new ByteArray(80);
			record.write(lengthPrefixBuffer, 0, 4);
			bytesRead = read(record, 4, 76);
			if (bytesRead < 76) {
				throw new EOFException(END_OF_STREAM_ERROR);
			}
		}
		if ((record.readAsByte(0) & 0xFF) == 0xF0) {
			encoding = EBCDIC;
			record.setEncoding(encoding);
		} else if ((record.readAsByte(0) & 0xFF) == 0x30) {
			encoding = US_ASCII;
			record.setEncoding(encoding);
		} else {
			throw new InvalidFormatException(
					FIRST_BYTE_MUST_BE_AN_ASCII_OR_EBCDIC_0);
		}
		X937FileHeaderRecord header = new X937FileHeaderRecordImpl(record, 0);
		int recordType = header.recordType();
		if (recordType != 1) {
			throw new InvalidFormatException(
					FILE_HEADER_MUST_HAVE_A_RECORD_TYPE_01);
		}
		// IO-2: Allow users of the X9InputStreamParser to pass in the X9.37
		// factory strategy to use in creating the X9.37 records. Use the
		// strategy to get the factory. If no factory was provided, the the
		// default strategy will be used (see constructor)..
		factory = factoryStrategy.factory(header);
		x937Record = factory.newX9Record(record);
		return x937Record;
	}

	/**
	 * Return the name of the character encoding being used by this stream.
	 *
	 * @return The historical name of this encoding.
	 * 
	 */
	public String getEncoding() {
		return encoding;
	}

}
