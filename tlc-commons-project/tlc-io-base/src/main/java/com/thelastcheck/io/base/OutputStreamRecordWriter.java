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

package com.thelastcheck.io.base;

import com.thelastcheck.commons.buffer.ByteArray;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class OutputStreamRecordWriter implements Closeable {

	private OutputStream os;
	private List<RecordFilter> filterList = new ArrayList<RecordFilter>();
	/**
	 * Used as an internal buffer when writing from ByteArray that does not have
	 * an underlying array. This buffer is allocated when needed and is always
	 * the largest buffer ever needed during the write process.
	 */
	private byte[] maxBuffer;

	public OutputStreamRecordWriter(OutputStream os) {
		this.os = os;
	}

	public void close() throws IOException {
		if (os != null) {
			os.close();
			os = null;
		}
	}

	public void write(Record record) throws IOException {
		if (os == null) {
			throw new IllegalStateException(
					"Stream is not open or available for writing");
		}
		record = processFilters(record);
		if (record != null) {
			writeRecord(record);
		}
	}

	protected final void write(ByteArray byteArray) throws IOException {
		write(byteArray, 0, byteArray.getLength());
	}

	protected final void write(ByteArray byteArray, int offset, int length)
			throws IOException {
		if (byteArray.hasArray()) {
			ByteArray.UnderlyingArray array = byteArray.getArray();
			write(array.value, array.offset + offset, length);
		} else {
			byte[] buffer = getBuffer(length);
			byteArray.read(offset, buffer, 0, length);
			write(buffer);
		}
	}

	private byte[] getBuffer(int size) {
		if (maxBuffer == null || maxBuffer.length < size) {
			maxBuffer = new byte[size];
		}
		return maxBuffer;
	}

	protected final void write(byte[] bytes) throws IOException {
		os.write(bytes);
	}

	protected final void write(byte[] bytes, int offset, int length)
			throws IOException {
		os.write(bytes, offset, length);
	}

	private Record processFilters(Record record) {
		for (RecordFilter filter : filterList) {
			record = filter.filter(record);
			if (record == null) {
				return null;
			}
		}
		return record;
	}

	public void addFilter(RecordFilter inputStreamFilter) {
		filterList.add(inputStreamFilter);
	}

	protected abstract void writeRecord(Record record) throws IOException;

}
