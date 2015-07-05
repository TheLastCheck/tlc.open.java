package com.thelastcheck.commons.buffer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.nio.CharBuffer;

import org.junit.Before;
import org.junit.Test;

public class ByteArrayTest2 {

	private static final String ASCII = ByteArray.ASCII_CHARSET_NAME;
	private static final String EBCDIC = ByteArray.EBCDIC_CHARSET_NAME;
	private ByteArray byteArray;

    @Before
	public void setUp() throws Exception {
		byteArray = new ByteArray(256, EBCDIC);
	}

    @Test
	public void testCharBuffer() {
	    ByteArray ba = ByteArray.valueOf("TEST");
	    CharBuffer cb = ba.getBuffer().asCharBuffer();
        System.out.println("ba hasArray(): " + ba.getBuffer().hasArray());
        System.out.println("cb hasArray(): " + cb.hasArray());
	}
	
    @Test
	public void testWrite() {
		byteArray.write((double) 3.14159, 0);
		byteArray.write((int) 3, 8);
		byteArray.write("This is a test", 12, 14, true);
		byteArray.write("More Test Data", 27, 30, true);
		ByteArrayDumpFormatter.dumpByteArray("testWrite", byteArray);

		System.out.println(byteArray.readAsDouble(0));
		System.out.println(byteArray.readAsInt(8));
		String value = byteArray.readAsString(12, 30, true);
		System.out.println("[" + value + "]" + " - (" + value.length() + ")");
		value = byteArray.readAsString(27, 30, true);
		System.out.println("[" + value + "]" + " - (" + value.length() + ")");
	}

    @Test
	public void testEqual() {
		byteArray.write("123456", 0);
		ByteArray byteArray2 = new ByteArray(256, EBCDIC);
		byteArray2.write("123456", 0);
		ByteArray byteArray3 = new ByteArray(256);
		byteArray3.write("123456", 0);
		assertTrue(byteArray.equals(byteArray));
		assertTrue(byteArray.equals(byteArray2));
		assertFalse(byteArray.equals(byteArray3));
	}

	private static final String convert = " `1234567890-=~!@#$%^&*()_+qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM[]\\{}|;\':\",./<>?";

    @Test
	public void testConvert() {
		byteArray.write(convert, 0);
		String result = byteArray.readAsString(0, convert.length());
		assertTrue(result.equals(convert));
		ByteArrayDumpFormatter.dumpByteArray("testConvert", byteArray);
	}
}
