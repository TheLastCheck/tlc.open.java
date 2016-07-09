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

import com.google.common.base.Throwables;
import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.base.utils.ToXmlBuilder;
import com.thelastcheck.commons.buffer.ByteArray;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.TimeZone;

public abstract class RecordImpl implements Record, Cloneable {

	private ByteArray record;
	private int recordPosition;
	private long offsetPosition;
	protected static final String INVALID_FIELD_NUMBER = "Invalid Field Number";

	public RecordImpl() {
		super();
	}

	public RecordImpl(int recordLength, String encoding) {
		this.record = new ByteArray(recordLength, encoding);
	}

	public RecordImpl(ByteArray record) {
		this.record = record;
	}

	@Override
	public Object clone() {
		RecordImpl object;
		try {
			object = (RecordImpl) super.clone();
			object.record = (ByteArray) record.clone();
			return object;
		} catch (CloneNotSupportedException e) {
			throw Throwables.propagate(e);
		}
	}

	public Object duplicate()  {
		RecordImpl object;
		try {
			object = (RecordImpl) super.clone();
			object.record = new ByteArray(record);
			return object;
		} catch (CloneNotSupportedException e) {
			throw Throwables.propagate(e);
		}
	}

	public Object getField(int fieldNumber) throws InvalidDataException {
		return getField(field(fieldNumber));
	}

	public Record setField(String value, int fieldNumber) {
		setField(value, field(fieldNumber));
		return this;
	}

	public Record setField(long value, int fieldNumber) {
		setField(value, field(fieldNumber));
		return this;
	}

	public Record setField(int value, int fieldNumber) {
		setField(value, field(fieldNumber));
		return this;
	}

	public String fieldName(int fieldNumber) {
		return field(fieldNumber).name();
	}

	/**
	 * 
	 * @param field
	 *            The field to be obtained.
	 * 
	 * @return A string containing the field from the buffer converted based on
	 *         the defined encoding.
	 * @throws InvalidDataException
	 */
	protected Object getField(Field field) throws InvalidDataException {
		return field.extract(record);
	}

	protected String getFieldAsString(Field field) {
		return field.extractAsString(record);
	}

	protected RoutingNumber getFieldAsRoutingNumber(Field field) {
		return field.extractStringAsRoutingNumber(record);
	}

	protected OnUsField getFieldAsOnUsField(Field field) {
		return field.extractStringAsOnUsField(record);
	}

	protected ByteArray getFieldAsByteArray(Field field) {
		return field.extractAsByteArray(record);
	}

	protected Date getFieldAsDate(Field field) throws InvalidDataException {
		return field.extractStringAsDate(record);
	}

	protected Date getFieldAsDate(Field field, TimeZone zone)
			throws InvalidDataException {
		return field.extractStringAsDate(record, zone);
	}

	protected Date getFieldAsTime(Field field) throws InvalidDataException {
		return field.extractStringAsTime(record);
	}

	protected Date getFieldAsTime(Field field, TimeZone zone)
			throws InvalidDataException {
		return field.extractStringAsTime(record, zone);
	}

	protected long getFieldAsLong(Field field) throws InvalidDataException {
		return field.extractStringAsLong(record);
	}

	protected int getFieldAsInt(Field field) throws InvalidDataException {
		return field.extractStringAsInt(record);
	}

	/**
	 * 
	 * @param value
	 *            The new value to be stored in the buffer.
	 * @param field
	 *            The field to be updated.
	 * 
	 */
	protected void setField(String value, Field field) {
		field.insert(value, record);
	}

	protected void setFieldRight(String value, Field field) {
		field.insertRight(value, record);
	}

	protected void setField(ByteArray value, Field field) {
		field.insert(value, record);
	}

	protected void setFieldDate(Date value, Field field) {
		field.insertDate(value, record);
	}

	protected void setFieldDate(Date value, Field field, TimeZone zone) {
		field.insertDate(value, record, zone);
	}

	protected void setFieldTime(Date value, Field field) {
		field.insertTime(value, record);
	}

	protected void setFieldTime(Date value, Field field, TimeZone zone) {
		field.insertTime(value, record, zone);
	}

	protected void setField(RoutingNumber value, Field field) {
		field.insert(value, record);
	}

	protected void setField(OnUsField value, Field field) {
		field.insert(value, record);
	}

	protected void setField(long value, Field field) {
		field.insertAsString(value, record);
	}

	protected void setField(int value, Field field) {
		field.insertAsString(value, record);
	}

	/**
	 * This is a routine that is used to allocate a new record for a field that
	 * has changed length. A new buffer is allocated and all of the defined
	 * fields for this record are copied, except for the field being replaced.
	 * It is up to the caller of this routine to actually store the new field
	 * value.
	 * 
	 * @param currentLength
	 *            the current length of the field
	 * @param newLength
	 *            the new length of the field
	 * @param replaceLengthField
	 *            the field number of the length field associated with the field
	 * @param replacedField
	 *            the field number of field being replaced
	 */
	protected void allocateNewRecord(int currentLength, int newLength,
			int replaceLengthField, int replacedField) {
		ByteArray currentRecord = record();
		ByteArray newRecord = new ByteArray(currentRecord.getLength()
				- currentLength + newLength, currentRecord.getEncoding());
		// save off all the current component fields
		ByteArray[] currentValues = new ByteArray[numberOfFields() + 1];
		for (int i = 1; i <= numberOfFields(); i++) {
			Field field = field(i);
			if (i == replacedField)
				continue;
			currentValues[i] = field.extractAsByteArray(currentRecord);
		}
		record(newRecord);
		resetDynamicFields();
		// set values back into the current component fields
		for (int i = 1; i <= numberOfFields(); i++) {
			Field field = field(i);
			if (i == replacedField)
				continue;
			// if this is the field containing the length, use the new length
			// value.
			if (i == replaceLengthField) {
				field.insertAsString(newLength, newRecord);
			} else {
				field.insert(currentValues[i], newRecord);
			}
		}
		return;
	}

	public int length() {
		return record.getLength();
	}

	public ByteArray record() {
		return record;
	}

	/**
	 * Certain records are variable in size and can change size based on the
	 * values placed in the fields. This method is for sub-classes who need to
	 * reset the record after a field manipulation requires a change in the
	 * record size.
	 * 
	 * @param record
	 *            the record to set
	 */
	protected void record(ByteArray record) {
		this.record = record;
	}

	public int recordPosition() {
		return recordPosition;
	}

	public Record recordPosition(int position) {
		recordPosition = position;
		return this;
	}

	public long offsetPosition() {
		return offsetPosition;
	}

	public Record offsetPosition(long offset) {
		offsetPosition = offset;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tlc.io.Record#numberOfFields()
	 */
	public abstract int numberOfFields();

	/**
	 * Get the field definition for a specified field based on the field number.
	 * 
	 * @param fieldNumber
	 * @return An Field object defining the field.
	 */
	protected abstract Field field(int fieldNumber);

	/**
	 * This method is intended to allow the reset of dynamic fields back to
	 * their initial state. This is for fields whose definition may depend on
	 * values of other fields. If those fields change, reseting the dynamic
	 * fields forces them to be recalculated when needed.
	 */
	protected abstract void resetDynamicFields();

	public String toString() {
		ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
		ToStringBuilder sb = new ToStringBuilder(this, style);
		sb.append("position", recordPosition);
		sb.append("offset", offsetPosition);
		int fieldNumber = 1;
		int maxFields = numberOfFields();
		while (fieldNumber <= maxFields) {
			Field field = field(fieldNumber);
			field.formatToString(record, sb);
			fieldNumber++;
		}
		return sb.toString();
	}

	public String toXml() {
		return toXml(true);
	}

	public String toXml(boolean useAttributes) {
		String recordName = this.getClass().getSimpleName();
		int pos = recordName.lastIndexOf("Impl");
		if (pos != -1) {
			recordName = recordName.substring(0, pos);
		}
		ToXmlBuilder xb = new ToXmlBuilder(recordName, useAttributes, 512);
		int fieldNumber = 1;
		int maxFields = numberOfFields();
		while (fieldNumber <= maxFields) {
			Field field = field(fieldNumber);
			field.formatToXml(record, xb);
			fieldNumber++;
		}
		return xb.toString();
	}

}