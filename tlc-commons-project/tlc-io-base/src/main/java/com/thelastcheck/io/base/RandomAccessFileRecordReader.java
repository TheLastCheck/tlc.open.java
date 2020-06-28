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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileRecordReader {

	private RandomAccessFile raf;
	/**
	 * Used as an internal buffer when reading into ByteArray that does not have
	 * an underlying array. This buffer is allocated when needed and is always
	 * the largest buffer ever needed during the read process.
	 */
	private byte[] maxBuffer;

	public RandomAccessFileRecordReader(File file) throws FileNotFoundException {
		super();
		this.raf = new RandomAccessFile(file, "r");
	}

	public void close() throws IOException {
		if (raf != null) {
			raf.close();
			raf = null;
		}
	}

	/**
	 * @return a boolean indicating if the stream is available
	 */
	protected boolean isStreamAvailable() {
		if (raf == null) {
			return false;
		}
		return true;
	}

	protected void seek(long pos) throws IOException {
		raf.seek(pos);
	}

	protected int read(ByteArray record) throws IOException {
		return read(record, 0, record.getLength());
	}

	protected int read(ByteArray record, int displacement, int length)
			throws IOException {
		int count;
		if (record.hasArray()) {
			ByteArray.UnderlyingArray array = record.getArray();
			count = read(array.value, array.offset + displacement, length);
		} else {
			byte[] buffer = getBuffer(length);
			count = read(buffer, 0, length);
			record.write(buffer, 0, count, displacement);
		}
		return count;
	}

	private byte[] getBuffer(int size) {
		if (maxBuffer == null || maxBuffer.length < size) {
			maxBuffer = new byte[size];
		}
		return maxBuffer;
	}

	private int read(byte[] data, int displacement, int length)
			throws IOException {
		int totalBytesRead = 0;
		int bytesRemaining = length;
		int currentDisplacement = displacement;
		while (bytesRemaining > 0) {
			int bytesRead = raf.read(data, currentDisplacement, bytesRemaining);
			if (bytesRead == -1) {
				break;
			}
			bytesRemaining -= bytesRead;
			currentDisplacement += bytesRead;
			totalBytesRead += bytesRead;
		}
		return totalBytesRead;
	}

}