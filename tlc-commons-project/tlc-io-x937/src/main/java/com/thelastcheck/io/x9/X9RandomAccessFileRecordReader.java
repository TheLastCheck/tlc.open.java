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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.RandomAccessFileRecordReader;
import com.thelastcheck.io.base.exception.InvalidFormatException;
import com.thelastcheck.io.base.exception.InvalidStandardLevelException;
import com.thelastcheck.io.x9.factory.DefaultX9RecordFactoryStrategy;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x9.factory.X9RecordFactoryStrategy;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;
import com.thelastcheck.io.x937.records.stddstu.X937FileHeaderRecordImpl;

public class X9RandomAccessFileRecordReader extends
		RandomAccessFileRecordReader {

	private static final String US_ASCII = ByteArray.ASCII_CHARSET_NAME;
	private static final String EBCDIC = ByteArray.EBCDIC_CHARSET_NAME;

	private static final String END_OF_STREAM_ERROR = "End of stream reached before finished processing expected data.";
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
	 * @param file
	 *            is a File object representing a file with parsable X9 records.
	 */
	public X9RandomAccessFileRecordReader(File file)
			throws FileNotFoundException {
		this(file, null);
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
	 * @param file
	 *            is a File object representing a file with parsable X9 records.
	 * @param factoryStrategy
	 *            is an X9RecordFactoryStrategy used to determine the
	 *            appropriate X9 Factory to be used.
	 */
	public X9RandomAccessFileRecordReader(File file,
			X9RecordFactoryStrategy factoryStrategy)
			throws FileNotFoundException {
		super(file);
		if (factoryStrategy == null) {
			this.factoryStrategy = new DefaultX9RecordFactoryStrategy();
		} else {
			this.factoryStrategy = factoryStrategy;
		}
	}

	/**
	 * This method returns the next X9.37 record in the stream. If there are no
	 * more records, return null.
	 * 
	 * @param pos
	 *            - a long value indicating the location to read the next
	 *            record.
	 * 
	 * @return the next X9.37 record in the stream or null.
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws InvalidDataException
	 * @throws InvalidStandardLevelException
	 */
	public X9Record readRecord(long pos) throws IOException,
			InvalidFormatException, InvalidDataException,
			InvalidStandardLevelException {

		if (!isStreamAvailable()) {
			close();
			return null;
		}

		X9Record x9Record = null;
		if (firstTime) {
			firstTime = false;
			processFirstRecord();
		}

		// Position to the location to read
		seek(pos);

		recordSize = 80;
		if (variableLength) {
			int bytesRead = read(lengthPrefixBuffer);
			if (bytesRead < lengthPrefixBuffer.getLength()) {
				throw new IOException(END_OF_STREAM_ERROR);
			}
			recordSize = lengthPrefixBuffer.readAsInt(0);
		}

		ByteArray record = new ByteArray(recordSize, this.encoding);
		int bytesRead = read(record);
		if (bytesRead < recordSize) {
			throw new IOException(END_OF_STREAM_ERROR);
		}
		x9Record = factory.newX9Record(record);

		x9Record.offsetPosition(pos);

		return x9Record;
	}

	private void processFirstRecord() throws IOException,
			InvalidFormatException, InvalidDataException,
			InvalidStandardLevelException {
		ByteArray record = null;
		int bytesRead = read(lengthPrefixBuffer);
		if (bytesRead < lengthPrefixBuffer.getLength()) {
			throw new IOException(END_OF_STREAM_ERROR);
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
				throw new IOException(END_OF_STREAM_ERROR);
			}
		} else {
			record = new ByteArray(80);
			record.write(lengthPrefixBuffer, 0, 4);
			bytesRead = read(record, 4, 76);
			if (bytesRead < 76) {
				throw new IOException(END_OF_STREAM_ERROR);
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
		factory = factoryStrategy.factory(header);
	}

	/**
	 * Return the name of the character encoding being used by this stream.
	 * 
	 * <p>
	 * If this instance was created with the
	 * {@link #X9InputStreamParser(InputStream, String)} constructor then the
	 * returned name, being unique for the encoding, may differ from the name
	 * passed to the constructor.
	 * </p>
	 * 
	 * @return The historical name of this encoding.
	 * 
	 */
	public String getEncoding() {
		return encoding;
	}

}
