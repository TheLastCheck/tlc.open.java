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

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.buffer.ByteArray;

public interface Record {

	/**
	 * Clear the entire record area to the fill character required. Typically
	 * this is spaces or nulls. If a space is the value, then the space value
	 * should be based on the encoding.
	 */
	public abstract void clearRecord();

	/**
	 * The number of fields defined for this record.
	 * 
	 * @return an int for the number of fields.
	 */
	public abstract int numberOfFields();

	/**
	 * The field value.
	 * 
	 * @param fieldNumber
	 * @return An object containing the value of the field for the specified
	 *         field number.
	 * @throws InvalidDataException
	 */
	public abstract Object getField(int fieldNumber)
			throws InvalidDataException;

	/**
	 * Set the contents of the field to the specified string value.
	 * 
	 * @param value
	 * @param fieldNumber
	 */
	public abstract Record setField(String value, int fieldNumber);

	/**
	 * Set the contents of the field to the specified long value.
	 * 
	 * @param value
	 * @param fieldNumber
	 */
	public abstract Record setField(long value, int fieldNumber);

	/**
	 * Set the contents of the field to the specified int value.
	 * 
	 * @param value
	 * @param fieldNumber
	 */
	public abstract Record setField(int value, int fieldNumber);

	/**
	 * This is the length of the current record. Normally this is the size of
	 * the record buffer area. However, we know we are going to allow the I/O
	 * routines to read short records and for those record types the subclass
	 * may override this to give the true size of the record, not just the in
	 * memory buffer area.
	 * 
	 * @return the length of the record.
	 */
	public abstract int length();

	/**
	 * @return the record
	 */
	public abstract ByteArray record();

	/**
	 * The name of the field.
	 * 
	 * @param fieldNumber
	 * @return A String containing the name of the field for the specified field
	 *         number.
	 */
	public abstract String fieldName(int fieldNumber);

	/**
	 * The position of this record in an input stream. If the record is not from
	 * a input stream, then this value will be 0. The first record is always
	 * record number 1.
	 * 
	 * @return an int indicating position of the record in an input stream.
	 */
	public abstract int recordPosition();

	/**
	 * This is used to set the current record position of this record in the
	 * input stream.
	 * 
	 * @param position
	 */
	public abstract Record recordPosition(int position);

	/**
	 * The offset of this record in an input stream. If the record is not from a
	 * input stream, then this value will be 0. This offset is relative to 0
	 * from the beginning of the stream. So the offset for the first record will
	 * be 0.
	 * 
	 * @return a long indicating the offset of the record in an input stream.
	 */
	public abstract long offsetPosition();

	/**
	 * This is used to set the current record offset of this record in the input
	 * stream.
	 * 
	 * @param offset
	 */
	public abstract Record offsetPosition(long offset);

	/**
	 * Creates an XML representation of the record based on it's fields. Default
	 * is to create field values as attributes.
	 * 
	 * @return a string of XML data
	 */
	public abstract String toXml();

	/**
	 * Creates an XML representation of the record based on it's fields.
	 * 
	 * @param useAttributes
	 *            if true, then fields are attributes, if false, then they are
	 *            tagged and subordinate to the record tags.
	 * @return a string of XML data
	 */
	public abstract String toXml(boolean useAttributes);

}