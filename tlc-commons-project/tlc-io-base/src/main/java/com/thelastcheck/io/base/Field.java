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
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.base.utils.ToXmlBuilder;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.exception.InvalidLengthException;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Field {
	private static final String SPACES_80 = "                    "
			+ "                    " + "                    "
			+ "                    ";

	private int offset;
	private int length;
	private com.thelastcheck.io.base.FieldType type;
	private String name;
	private int number;
	private static Map<String, DateFormat> dateFormatterMap = Collections
			.synchronizedMap(new HashMap<String, DateFormat>());
	private static Map<String, DateFormat> timeFormatterHHmmssMap = Collections
			.synchronizedMap(new HashMap<String, DateFormat>());
	private static Map<String, DateFormat> timeFormatterHHmmMap = Collections
			.synchronizedMap(new HashMap<String, DateFormat>());

	private static NumberFormat numberFormatter = NumberFormat.getInstance();

	protected static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd");
	protected static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

	public Field() {
		super();
	}

	public Field(int offset, int length) {
		this.offset = offset;
		this.length = length;
		this.type = com.thelastcheck.io.base.FieldType.STRING;
	}

	public Field(int offset, int length, FieldType type) {
		this.offset = offset;
		this.length = length;
		this.type = type;
	}

	public Field(String fieldName, int fieldNumber, int offset, int length) {
		this(offset, length);
		this.name = fieldName;
		this.number = fieldNumber;
	}

	public Field(String fieldName, int fieldNumber, int offset, int length,
			FieldType type) {
		this(offset, length, type);
		this.name = fieldName;
		this.number = fieldNumber;
	}

	public boolean isType(FieldType type) {
		return type.equals(this.type);
	}

	public FieldType type() {
		return this.type;
	}

	/**
	 * Extract a data field from a ByteArray.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return An Object containing the extracted data. The type of object
	 *         returned depends on the field type.
	 * @throws InvalidDataException
	 */
	public Object extract(ByteArray record) throws InvalidDataException {
		Object value = null;
		switch (type) {
		case STRING:
			value = extractAsString(record);
			break;
		case BINARY:
			value = extractAsByteArray(record);
			break;
		case INT:
			value = extractStringAsInt(record);
			break;
		case LONG:
			value = extractStringAsLong(record);
			break;
		case DATE:
			value = extractStringAsDate(record);
			break;
		case TIME:
			value = extractStringAsTime(record);
			break;
		case ROUTING_NUMBER:
			value = new RoutingNumber(extractAsString(record));
			break;
		case ONUS:
			value = new OnUsField(extractAsString(record));
			break;
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as byte[].
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A byte array containing the extracted data.
	 */
	public byte[] extractAsBytes(ByteArray record) {
		return record.read(offset, length);
	}

	/**
	 * Extract a data field from a ByteArray as a ByteArray.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A ByteArray containing the extracted data.
	 */
	public ByteArray extractAsByteArray(ByteArray record) {
		return record.readAsByteArray(offset, length);
	}

	/**
	 * Extract a data field from a ByteArray record as a String.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A String containing the extracted data.
	 */
	public String extractAsString(ByteArray record) {
		return record.readAsString(offset, length);
	}

	/**
	 * Extract a data field from a ByteArray as a String and convert value to a
	 * long.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A long containing the extracted data.
	 * @throws InvalidDataException
	 */
	public long extractStringAsLong(ByteArray record)
			throws InvalidDataException {
		String s = extractAsString(record).trim();
		long value = 0;
		// Give a default value of zero to a blank string
		if (s.length() > 0) {
			try {
				value = Long.parseLong(s);
			} catch (NumberFormatException e) {
				throw new InvalidDataException(e);
			}
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as a long.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A long containing the extracted data.
	 */
	public long extractAsLong(ByteArray record) {
		long value;
		switch (length) {
		case 8:
			value = record.readAsLong(offset);
			break;
		case 4:
			value = record.readAsInt(offset);
			break;
		case 2:
			value = record.readAsShort(offset);
			break;
		case 1:
			value = record.readAsByte(offset);
			break;
		default:
			throw new InvalidLengthException();
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as a String and convert value to an
	 * int.
	 * 
	 * @param record
	 *            A ByteArray containing a ASN.1 format record
	 * @return An int containing the extracted data.
	 * @throws InvalidDataException
	 */
	public int extractStringAsInt(ByteArray record) throws InvalidDataException {
		String s = extractAsString(record).trim();
		int value = 0;
		// Give a default value of zero to a blank string
		if (s.length() > 0) {
			try {
				value = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				throw new InvalidDataException(e);
			}
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as a long.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return An int containing the extracted data.
	 */
	public byte extractAsByte(ByteArray record) {
		byte value;
		switch (length) {
		case 1:
			value = record.readAsByte(offset);
			break;
		case 2:
			value = (byte) record.readAsShort(offset);
			break;
		case 4:
			value = (byte) record.readAsInt(offset);
			break;
		default:
			throw new InvalidLengthException();
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as a long.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return An int containing the extracted data.
	 */
	public short extractAsShort(ByteArray record) {
		short value;
		switch (length) {
		case 2:
			value = record.readAsShort(offset);
			break;
		case 1:
			value = (short) record.readAsByte(offset);
			break;
		case 4:
			value = (short) record.readAsInt(offset);
			break;
		default:
			throw new InvalidLengthException();
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as a long.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return An int containing the extracted data.
	 */
	public int extractAsInt(ByteArray record) {
		int value;
		switch (length) {
		case 4:
			value = record.readAsInt(offset);
			break;
		case 2:
			value = record.readAsShort(offset);
			break;
		case 1:
			value = record.readAsByte(offset);
			break;
		default:
			throw new InvalidLengthException();
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray converting from PNS to a String.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A string containing the unpacked data
	 */
	public String extractPnsAsString(ByteArray record) {
		return record.readPns(offset, length);
	}

	/**
	 * Extract a data field from a ByteArray as a Date.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A Date containing the extracted data.
	 * @throws InvalidDataException
	 */
	public Date extractStringAsDate(ByteArray record)
			throws InvalidDataException {
		return extractStringAsDate(record, null);
	}

	/**
	 * Extract a data field from a ByteArray as a Date.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A Date containing the extracted data.
	 * @throws InvalidDataException
	 */
	public Date extractStringAsDate(ByteArray record, TimeZone zone)
			throws InvalidDataException {
		String date = extractAsString(record);
		if (date.length() != 8) {
			throw new InvalidDataException(
					"Date field must be 8 characters in length");
		}
		DateFormat format = dateFormatForZone(zone);
		Date value = null;
		if (date.trim().length() > 0) {
			synchronized (format) {
				try {
					value = format.parse(date);
				} catch (ParseException e) {
					throw new InvalidDataException(e);
				}
			}
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as a Date object containing only
	 * time values.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @return A time value formatted as Date containing the extracted data.
	 * @throws InvalidDataException
	 */
	public Date extractStringAsTime(ByteArray record)
			throws InvalidDataException {
		return extractStringAsTime(record, null);
	}

	/**
	 * Extract a data field from a ByteArray as a Date object containing only
	 * time values.
	 * 
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 * @param zone
	 *            A TimeZone object identifying the time zone for the time being
	 *            extracted
	 * @return A time value formatted as Date containing the extracted data.
	 * @throws InvalidDataException
	 */
	public Date extractStringAsTime(ByteArray record, TimeZone zone)
			throws InvalidDataException {
		String time = extractAsString(record);
		if (time.length() != 4 && time.length() != 6) {
			throw new InvalidDataException(
					"Time field must be 4 or 6 characters in length");
		}
		DateFormat format = null;
		if (time.length() == 4) {
			format = timeFormatHHmmForZone(zone);
		} else {
			format = timeFormatHHmmssForZone(zone);
		}
		Date value = null;
		if (time.trim().length() > 0) {
			synchronized (format) {
				try {
					value = format.parse(time);
				} catch (ParseException e) {
					throw new InvalidDataException(e);
				}
			}
		}
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as a String and convert value to an
	 * RoutingNumber.
	 * 
	 * @param record
	 *            A ByteArray containing a ASN.1 format record
	 * @return A RoutingNumber containing the extracted data.
	 * @throws InvalidDataException
	 */
	public RoutingNumber extractStringAsRoutingNumber(ByteArray record) {
		String s = extractAsString(record).trim();
		RoutingNumber value = new RoutingNumber(s);
		return value;
	}

	/**
	 * Extract a data field from a ByteArray as a String and convert value to an
	 * OnusField.
	 * 
	 * @param record
	 *            A ByteArray containing a ASN.1 format record
	 * @return A OnUsField containing the extracted data.
	 * @throws InvalidDataException
	 */
	public OnUsField extractStringAsOnUsField(ByteArray record) {
		String s = extractAsString(record).trim();
		OnUsField value = new OnUsField(s);
		return value;
	}

	/**
	 * @param value
	 *            A field value contained in an array of bytes.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(byte[] value, ByteArray record) {
		record.write(value, 0, length, offset);
	}

	/**
	 * @param value
	 *            A field value contained in a ByteArray to be stored into the
	 *            ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(ByteArray value, ByteArray record) {
		record.write(value, offset, length);
	}

	/**
	 * @param value
	 *            A field value contained in a String to be stored into the
	 *            ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(String value, ByteArray record) {
		record.write(value, offset, length, false);
	}

	/**
	 * @param value
	 *            A field value contained in a String to be stored into the
	 *            ByteArray right justified and space filled.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insertRight(String value, ByteArray record) {
		if (value.length() > length) {
			value = value.substring(value.length() - length);
		}
		while (value.length() < length) {
			if ((length - value.length()) > 80) {
				value = SPACES_80 + value;
			} else {
				value = SPACES_80.substring(0, length - value.length()) + value;
			}
		}
		record.write(value, offset, length, false);
	}

	/**
	 * @param value
	 *            A field value contained in a long to be converted to a String
	 *            and then to be stored into the ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insertAsString(long value, ByteArray record) {
		String s = null;
		synchronized (numberFormatter) {
			numberFormatter.setGroupingUsed(false);
			numberFormatter.setMinimumIntegerDigits(length);
			s = numberFormatter.format(value);
		}
		insert(s, record);
	}

	/**
	 * @param value
	 *            A field value contained in an int to be converted to a String
	 *            and then to be stored into the ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insertAsString(int value, ByteArray record) {
		String s = null;
		synchronized (numberFormatter) {
			numberFormatter.setGroupingUsed(false);
			numberFormatter.setMinimumIntegerDigits(length);
			s = numberFormatter.format(value);
		}
		insert(s, record);
	}

	/**
	 * @param value
	 *            A field value contained in an int and stored in big endion
	 *            binary format in the ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(byte value, ByteArray record) {
		switch (length) {
		case 1:
			record.write((byte) value, offset);
			break;
		case 2:
			record.write((short) value, offset);
			break;
		case 4:
			record.write((int) value, offset);
			break;
		default:
			throw new InvalidLengthException();
		}
	}

	/**
	 * @param value
	 *            A field value contained in an int and stored in big endion
	 *            binary format in the ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(short value, ByteArray record) {
		switch (length) {
		case 2:
			record.write((short) value, offset);
			break;
		case 1:
			record.write((byte) value, offset);
			break;
		case 4:
			record.write((int) value, offset);
			break;
		default:
			throw new InvalidLengthException();
		}
	}

	/**
	 * @param value
	 *            A field value contained in an int and stored in big endion
	 *            binary format in the ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(int value, ByteArray record) {
		switch (length) {
		case 4:
			record.write(value, offset);
			break;
		case 2:
			record.write((short) value, offset);
			break;
		case 1:
			record.write((byte) value, offset);
			break;
		default:
			throw new InvalidLengthException();
		}
	}

	/**
	 * @param value
	 *            A field value contained in an long and stored in big endion
	 *            binary format in the ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(long value, ByteArray record) {
		switch (length) {
		case 8:
			record.write(value, offset);
			break;
		case 4:
			record.write((int) value, offset);
			break;
		case 2:
			record.write((short) value, offset);
			break;
		case 1:
			record.write((byte) value, offset);
			break;
		default:
			throw new InvalidLengthException();
		}
	}

	/**
	 * @param value
	 *            A field value contained in a Date object to be converted to a
	 *            String and then to be stored into the ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insertDate(Date value, ByteArray record) {
		insertDate(value, record, null);
	}

	/**
	 * @param value
	 *            A field value contained in a Date object to be converted to a
	 *            String and then to be stored into the ByteArray.
	 * @param zone
	 *            A TimeZone object identifying the time zone for the date being
	 *            extracted. Based on the time value in the date object, this
	 *            could result in the date value being a different date than the
	 *            local date.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insertDate(Date value, ByteArray record, TimeZone zone) {
		DateFormat df = dateFormatForZone(zone);
		String date = null;
		synchronized (df) {
			date = df.format(value);
		}
		insert(date, record);
	}

	/**
	 * @param value
	 *            A field value contained in a Date object as a time value to be
	 *            converted to a String and then to be stored into the
	 *            ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insertTime(Date value, ByteArray record) {
		insertTime(value, record, null);
	}

	/**
	 * @param value
	 *            A field value contained in a Date object as a time value to be
	 *            converted to a String and then to be stored into the
	 *            ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insertTime(Date value, ByteArray record, TimeZone zone) {
		DateFormat df = null;
		if (length == 4) {
			df = timeFormatHHmmForZone(zone);
		} else {
			df = timeFormatHHmmssForZone(zone);
		}

		String time = null;
		synchronized (df) {
			time = df.format(value);
		}
		insert(time, record);
	}

	private DateFormat dateFormatForZone(TimeZone zone) {
		return formatForZone(dateFormatterMap, "yyyyMMdd", zone);
	}

	private DateFormat timeFormatHHmmssForZone(TimeZone zone) {
		return formatForZone(timeFormatterHHmmssMap, "HHmmss", zone);
	}

	private DateFormat timeFormatHHmmForZone(TimeZone zone) {
		return formatForZone(timeFormatterHHmmMap, "HHmm", zone);
	}

	private DateFormat formatForZone(Map<String, DateFormat> map,
			String format, TimeZone zone) {
		DateFormat df = null;
		if (zone == null) {
			df = map.get(null);
		} else {
			df = map.get(zone.getID());
		}
		if (df == null) {
			df = new SimpleDateFormat(format);
			if (zone == null) {
				map.put(null, df);
			} else {
				Calendar cal = Calendar.getInstance(zone);
				df.setCalendar(cal);
				map.put(zone.getID(), df);
			}
		}
		return df;
	}

	/**
	 * @param value
	 *            A field value contained in a RoutingNumber to be stored into
	 *            the ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(RoutingNumber value, ByteArray record) {
		record.write(value.toString(), offset, length, false);
	}

	/**
	 * @param value
	 *            A field value contained in a OnUsField to be stored into the
	 *            ByteArray.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insert(OnUsField value, ByteArray record) {
		record.write(value.toString(), offset, length, false);
	}

	/**
	 * @param value
	 *            A field value to be stored as a PNS value in the ByteArray
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void insertPns(String value, ByteArray record) {
		record.writeAsPns(value, offset, length);
	}

	/**
	 * @param mask
	 *            A mask value to indicate which bits are to be set.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void setBit(byte mask, ByteArray record) {
		record.setBit(offset, mask);
	}

	/**
	 * @param mask
	 *            A mask value to indicate which bits are to be cleared.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public void clearBit(byte mask, ByteArray record) {
		record.clearBit(offset, mask);
	}

	/**
	 * @param mask
	 *            A mask value to indicate which bits are to be tested.
	 * @param record
	 *            A ByteArray containing an ASN.1 format record
	 */
	public boolean testBit(byte mask, ByteArray record) {
		return record.testBit(offset, mask);
	}

	public String name() {
		return this.name;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this);
		sb.append("Offset", offset);
		sb.append("Length", length);
		sb.append("Type", type);
		sb.append("Number", number);
		sb.append("Name", name);
		return sb.toString();
	}

	/**
	 * Format the field into a ToStringBuilder object.
	 * 
	 * @param record
	 * @param sb
	 */

	public void formatToString(ByteArray record, ToStringBuilder sb) {
		switch (type) {
		case BINARY:
			int len = (length < 16) ? length : 16;
			String data = record.readPns(offset, len);
			sb.append(name, "BINARY DATA[LEN=" + length + ",x'" + data + "']");
			break;
		case DATE:
			synchronized (sdfDate) {
				try {
					sb.append(name, sdfDate.format(extract(record)));
				} catch (Exception e) {
					sb.append(name, extractAsString(record));
				}
			}
			break;
		case TIME:
			synchronized (sdfTime) {
				try {
					sb.append(name, sdfTime.format(extract(record)));
				} catch (Exception e) {
					sb.append(name, extractAsString(record));
				}
			}
			break;
		default:
			sb.append(name, extractAsString(record));
		}
	}

	/**
	 * Format the field into a ToStringBuilder object.
	 * 
	 * @param record
	 * @param xb
	 */
	public void formatToXml(ByteArray record, ToXmlBuilder xb) {
		switch (type) {
		case BINARY:
			int len = (length < 16) ? length : 16;
			String data = record.readPns(offset, len);
			xb.append(name, "BINARY DATA[LEN=" + length + ",x'" + data + "']");
			break;
		case DATE:
			synchronized (sdfDate) {
				try {
					xb.append(name, sdfDate.format(extract(record)));
				} catch (Exception e) {
					xb.append(name, extractAsString(record));
				}
			}
			break;
		case TIME:
			synchronized (sdfTime) {
				try {
					xb.append(name, sdfTime.format(extract(record)));
				} catch (Exception e) {
					xb.append(name, extractAsString(record));
				}
			}
			break;
		default:
			xb.append(name, extractAsString(record));
		}
	}

}