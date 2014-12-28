package com.thelastcheck.commons.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import junit.framework.TestCase;

public class ByteArrayTest extends TestCase {

    ByteArray byteArray = null;

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testByteArrayInt() {
        byteArray = new ByteArray(200);
        assertTrue(byteArray.getLength() == 200);
    }

    public void testSlice() {
        byteArray = ByteArray.valueOf("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        ByteArray ba = byteArray.slice(10, 10);
        String s = ba.readAsString();
        assertTrue("KLMNOPQRST".equals(s));
        dumpBuffer("sliceBefore", byteArray.getBuffer());
        dumpBuffer("sliceAfter ", ba.getBuffer());
        ByteArrayDumpFormatter.dumpByteArray("sliceBefore", byteArray);
        ByteArrayDumpFormatter.dumpByteArray("sliceAfter", ba);
        byte[] bs = ba.read(0, ba.getLength());
        ByteArrayDumpFormatter.dumpByteArray("sliceBS", bs);
        dumpBuffer("sliceBS    ", ba.getBuffer());
    }
    
    private void dumpBuffer(String title, ByteBuffer bb) {
        System.out.print(title + ":");
        System.out.print("capacity . . " + bb.capacity());
        System.out.print(", limit  . . . " + bb.limit());
        System.out.print(", position . . " + bb.position());
        System.out.println();
    }
    
    public void testByteArrayIntString() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        assertTrue(byteArray.getLength() == 200);
        byteArray.write(" ", 0);
        assertTrue((byteArray.getArray().value[0] & 0x00ff) == 0x40);

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        assertTrue(byteArray.getLength() == 200);
        byteArray.write(" ", 0);
        assertTrue((byteArray.getArray().value[0] & 0x00ff) == 0x20);
    }

    public void testByteArrayByteArray() {
        byte[] bytes = new byte[200];
        byteArray = new ByteArray(bytes);
        assertTrue(byteArray.getLength() == 200);
    }

    public void testByteArrayByteArrayString() {
        byte[] bytes = new byte[200];
        byteArray = new ByteArray(bytes, ByteArray.EBCDIC_CHARSET_NAME);
        assertTrue(byteArray.getLength() == 200);
        byteArray.write(" ", 0);
        assertTrue((byteArray.getArray().value[0] & 0x00ff) == 0x40);

        byteArray = new ByteArray(bytes, ByteArray.ASCII_CHARSET_NAME);
        assertTrue(byteArray.getLength() == 200);
        byteArray.write(" ", 0);
        assertTrue((byteArray.getArray().value[0] & 0x00ff) == 0x20);
    }

    public void testByteArrayByteArray1() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        assertTrue(byteArray.getLength() == 200);
        byteArray.write(" ", 0);

        ByteArray byteArray2 = new ByteArray(byteArray);
        assertTrue((byteArray2.getArray().value[0] & 0x00ff) == 0x40);
        assertTrue(byteArray.equals(byteArray2));
        assertTrue(byteArray.getArray().value == byteArray2.getArray().value);
    }

    public void testClone() throws CloneNotSupportedException {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write((int) 10, 0); 
        byteArray.write((long) 33333, 4); 
        byteArray.write("TESTTESTTEST", 12); 
        ByteArray byteArrayClone = (ByteArray) byteArray.clone();
        assertTrue(byteArray.equals(byteArrayClone));
        assertTrue(byteArray.getArray().value!= byteArrayClone.getArray().value);
        assertTrue(byteArray.getLength() == byteArrayClone.getLength());
        assertTrue(byteArray.getEncoding().equals(byteArrayClone.getEncoding()));
        assertTrue(byteArray.getOrder() == byteArrayClone.getOrder());
        String s1 = byteArray.readPns(0, 200);
        String s2 = byteArrayClone.readPns(0, 200);
        assertTrue(s1.equals(s2));
    }

    public void testReadAsByteArray() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byte[] value = { 0, 1, 2, 3, 4 };
        byteArray.write(value, 0);
        byteArray.write(value, 20);
        ByteArray bytes1 = byteArray.readAsByteArray(0, 5);
        ByteArray bytes2 = byteArray.readAsByteArray(20, 5);
        assertTrue(bytes1.equals(bytes2));
    }

    public void testRead() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byte[] value = { 0, 1, 2, 3, 4 };
        byteArray.write(value, 0);
        byteArray.write(value, 20);
        // byteArray.write((byte) 0, 0);
        byte[] bytes1 = byteArray.read(0, 5);
        byte[] bytes2 = byteArray.read(20, 5);
        for (int i = 0; i < bytes2.length; i++) {
            assertTrue(bytes1[i] == bytes2[i]);
        }
    }

    public void testReadIntCharArrayIntInt() {
        byteArray = new ByteArray(20, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 10);
        char[] chars = new char[5];
        byteArray.read(11, chars, 1, 3);
        byte[] bytes = new byte[5];
        for (int i = 0; i < 5;i++) {
            bytes[i] = (byte) chars[i];
        }
        ByteArray testBA = new ByteArray(bytes);
        String value = testBA.readPns(0, 5, false);
        assertTrue(value.equals("0045535400"));
        ByteArrayDumpFormatter.dumpByteArray("testReadIntCharArrayIntInt", bytes);
        ByteArrayDumpFormatter.dumpByteArray("testReadIntCharArrayIntIntBA", byteArray);
    }

    public void testReadIntByteArrayIntInt() {
        byteArray = new ByteArray(20, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 10);
        byte[] bytes = new byte[5];
        byteArray.read(11, bytes, 1, 3);
        ByteArray testBA = new ByteArray(bytes);
        String value = testBA.readPns(0, 5, false);
        assertTrue(value.equals("00C5E2E300"));
        ByteArrayDumpFormatter.dumpByteArray("testReadIntByteArrayIntInt", bytes);
        ByteArrayDumpFormatter.dumpByteArray("testReadIntByteArrayIntIntBA", byteArray);
    }

    public void testReadAsCharArray() {
        byteArray = new ByteArray(20, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        char[] value = byteArray.readAsCharArray(0, 4);
        assertTrue(value.length == 4);
        // char[] test = { 0x00E3, 0x00C5, 0x00E2, 0x00E3 };
        char[] test = new String("TEST").toCharArray();
        ByteArrayDumpFormatter.dumpByteArray("TEST", byteArray.getArray().value);
        for (int i = 0; i < test.length; i++) {
            assertTrue(test[i] == (value[i] & 0x00ff));
        }
    }

    public void testReadAsString() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        String value = byteArray.readAsString();
        assertTrue(value.length() == 200);
        assertFalse("TEST".equals(value));
        assertTrue("TEST".equals(value.substring(0, 4)));

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        byteArray.write("TEST", 0);
        value = byteArray.readAsString();
        assertTrue(value.length() == 200);
        assertFalse("TEST".equals(value));
        assertTrue("TEST".equals(value.substring(0, 4)));
    }

    public void testReadAsStringInt() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        String value = byteArray.readAsString(1);
        assertTrue(value.length() == 199);
        assertFalse("TEST".equals(value));
        assertFalse("EST".equals(value));
        assertTrue("EST".equals(value.substring(0, 3)));

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        byteArray.write("TEST", 0);
        value = byteArray.readAsString(1);
        assertTrue(value.length() == 199);
        assertFalse("TEST".equals(value));
        assertFalse("EST".equals(value));
        assertTrue("EST".equals(value.substring(0, 3)));
    }

    public void testReadAsStringIntInt() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        String value = byteArray.readAsString(0, 4);
        assertTrue(value.length() == 4);
        assertTrue("TEST".equals(value));

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        byteArray.write("TEST", 0);
        value = byteArray.readAsString(0, 4);
        assertTrue(value.length() == 4);
        assertTrue("TEST".equals(value));
    }

    public void testReadAsStringIntIntBoolean() {
        byteArray = new ByteArray(200, ByteArray.EBCDIC_CHARSET_NAME);
        byteArray.write("TEST", 0);
        String value = byteArray.readAsString(0, 8, true);
        assertTrue(value.length() == 4);
        assertTrue("TEST".equals(value));

        byteArray = new ByteArray(200, ByteArray.ASCII_CHARSET_NAME);
        byteArray.write("TEST", 0);
        value = byteArray.readAsString(0, 8, true);
        assertTrue(value.length() == 4);
        assertTrue("TEST".equals(value));
        value = byteArray.readAsString(0, 8, false);
        assertTrue(value.length() == 8);
        assertFalse("TEST".equals(value));
    }

    public void testTestBit() {
        byte[] ba = new byte[2];
        ba[0] = (byte) 0x41;
        ba[1] = (byte) 0x81;
        byteArray = new ByteArray(ba);
        assertTrue(byteArray.testBit(0, (byte) 0x01));
        assertFalse(byteArray.testBit(0, (byte) 0x02));
        assertTrue(byteArray.testBit(1, (byte) 0x80));
        assertTrue(byteArray.testBit(1, (byte) 0x81));
        assertTrue(byteArray.testBit(1, (byte) 0x01));
        assertFalse(byteArray.testBit(1, (byte) 0x83));
        System.out.println("TEST: " + byteArray.readPns(0, 2));
    }

    public void testSetBit() {
        byte[] ba = new byte[2];
        byteArray = new ByteArray(ba);
        byteArray.setBit(0, (byte) 0x41);
        byteArray.setBit(1, (byte) 0x01);
        assertTrue(byteArray.testBit(0, (byte) 0x01));
        assertTrue(byteArray.testBit(0, (byte) 0x40));
        assertTrue(byteArray.testBit(0, (byte) 0x41));
        assertFalse(byteArray.testBit(0, (byte) 0x02));
        assertFalse(byteArray.testBit(1, (byte) 0x80));
        assertFalse(byteArray.testBit(1, (byte) 0x81));
        assertTrue(byteArray.testBit(1, (byte) 0x01));
        assertFalse(byteArray.testBit(1, (byte) 0x83));
        System.out.println("SET : " + byteArray.readPns(0, 2));
    }

    public void testClearBit() {
        byte[] ba = new byte[2];
        byteArray = new ByteArray(ba);
        byteArray.setBit(0, (byte) 0x41);
        byteArray.setBit(1, (byte) 0x01);
        assertTrue(byteArray.testBit(0, (byte) 0x01));
        assertTrue(byteArray.testBit(0, (byte) 0x40));
        assertTrue(byteArray.testBit(0, (byte) 0x41));
        assertFalse(byteArray.testBit(0, (byte) 0x02));
        byteArray.clearBit(0, (byte) 0x40);
        assertTrue(byteArray.testBit(0, (byte) 0x01));
        assertFalse(byteArray.testBit(0, (byte) 0x40));
        System.out.println("CLR : " + byteArray.readPns(0, 2));
        byteArray.clearBit(0, (byte) 0x80);
        System.out.println("CLR : " + byteArray.readPns(0, 2));
        byteArray.clearBit(0, (byte) 0x01);
        byteArray.setBit(0, (byte) 0x42);
        System.out.println("CLR : " + byteArray.readPns(0, 2));
    }

    public void testReadAsDoubleInt() {
        byteArray = new ByteArray(10);
        byteArray.write(1.5, 0);
        double d = byteArray.readAsDouble(0);
        assertTrue(d == 1.5);
        ByteArrayDumpFormatter.dumpByteArray("readAsDouble", byteArray);
    }

    public void testReadSwappedAsDoubleInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.write(1.5, 0);
        double d = byteArray.readAsDouble(0);
        assertTrue(d == 1.5);
        ByteArrayDumpFormatter.dumpByteArray("readSwappedAsDouble", byteArray);
    }

    public void testReadAsIntInt() {
        byteArray = new ByteArray(10);
        byteArray.write(15, 0);
        int i = byteArray.readAsInt(0);
        assertTrue(i == 15);
        ByteArrayDumpFormatter.dumpByteArray("readAsInt", byteArray);
    }

    public void testReadSwappedAsIntInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.write(15, 2);
        int i = byteArray.readAsInt(2);
        assertTrue(i == 15);
        ByteArrayDumpFormatter.dumpByteArray("readSwappedAsInt", byteArray);
    }

    public void testReadAsLongInt() {
        byteArray = new ByteArray(10);
        byteArray.write((long) 15, 2);
        long i = byteArray.readAsLong(2);
        assertTrue(i == 15);
        ByteArrayDumpFormatter.dumpByteArray("readAsLong", byteArray);
    }

    public void testReadSwappedAsLongInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.write((long) 15, 2);
        long i = byteArray.readAsLong(2);
        assertTrue(i == 15);
        ByteArrayDumpFormatter.dumpByteArray("readSwappedAsLong", byteArray);
    }

    public void testReadPnsIntInt() {
        byteArray = new ByteArray(20);
        byteArray.writeAsPns("1234-5", 0, 4);
        String value = byteArray.readPns(0, 4);
        assertTrue(value.equals("AA1234D5"));
        byte[] test = { (byte) 0xAA, (byte) 0x12, (byte) 0x34, (byte) 0xD5 };
        byte[] bytes = byteArray.getArray().value;
        for (int i = 0; i < test.length; i++) {
            assertTrue(test[i] == bytes[i]);
        }
    }

    public void testReadPnsIntIntBoolean() {
        byteArray = new ByteArray(20);
        byteArray.writeAsPns("1-2345", 4, 5);
        String value = byteArray.readPns(4, 5, false);
        assertTrue(value.equals("AAAA1D2345"));
        value = byteArray.readPns(4, 5, true);
        assertTrue(value.equals("    1-2345"));
    }

    public void testFillByte() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xee);
        ByteArrayDumpFormatter.dumpByteArray("fill", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("EEEEEEEEEEEEEEEEEEEE"));
    }

    public void testFillByteIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xee, 1, 8);
        ByteArrayDumpFormatter.dumpByteArray("fill 1 8", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("00EEEEEEEEEEEEEEEE00"));
    }

    public void testWriteCharArrayInt() {
        byteArray = new ByteArray(10);
        char[] charArray = { 'a', 'b', 'c' };
        byteArray.write(charArray, 1);
        ByteArrayDumpFormatter.dumpByteArray("WriteCharArrayInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("00616263000000000000"));
    }

    public void testWriteByteArrayIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xAA);
        ByteArray ba = new ByteArray(8);
        ba.write("TEST", 0);
        byteArray.write(ba, 1, 5);
        ByteArrayDumpFormatter.dumpByteArray("WriteByteArrayIntInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("AA5445535400AAAAAAAA"));
    }

    public void testWriteByteArrayInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        ByteArray ba = new ByteArray(8);
        ba.write("TEST", 0);
        byteArray.write(ba, 1);
        ByteArrayDumpFormatter.dumpByteArray("fWriteByteArrayInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("BB5445535400000000BB"));
    }

    public void testWriteStringInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write("TEST", 1);
        ByteArrayDumpFormatter.dumpByteArray("WriteStringInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("BB54455354BBBBBBBBBB"));
    }

    public void testWriteStringIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write("TES", 7, 3);
        ByteArrayDumpFormatter.dumpByteArray("WriteStringIntInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("BBBBBBBBBBBBBB544553"));
    }

    public void testWriteStringIntIntBoolean() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write("TEST", 5, 5, true);
        ByteArrayDumpFormatter.dumpByteArray("WriteStringIntIntBooleanTrue", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("BBBBBBBBBB5445535400"));

        byteArray.fill((byte) 0xBB);
        byteArray.write("TEST", 5, 5, false);
        ByteArrayDumpFormatter.dumpByteArray("WriteStringIntIntBooleanFalse", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("BBBBBBBBBB5445535420"));
    }

    public void testWriteIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((int) 123, 0);
        byteArray.write((int) 255, 6);
        ByteArrayDumpFormatter.dumpByteArray("WriteIntInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("0000007BBBBB000000FF"));
    }

    public void testWriteSwappedIntInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.fill((byte) 0xBB);
        byteArray.write((int) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteSwappedIntInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("7B000000BBBBBBBBBBBB"));
    }

    public void testWriteLongInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((long) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteLongInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("000000000000007BBBBB"));
    }

    public void testWriteSwappedLongInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.fill((byte) 0xBB);
        byteArray.write((long) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteSwappedLongInt", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("7B00000000000000BBBB"));
    }

    public void testWriteFloatInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((float) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteFloatInt", byteArray);
        float f = byteArray.readAsFloat(0);
        assertTrue(f == 123.0);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("42F60000BBBBBBBBBBBB"));
    }

    public void testWriteSwappedFloatInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.fill((byte) 0xBB);
        byteArray.write((float) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteSwappedFloatInt", byteArray);
        float f = byteArray.readAsFloat(0);
        assertTrue(f == 123.0);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("0000F642BBBBBBBBBBBB"));
    }

    public void testWriteDoubleInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((double) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteDoubleInt", byteArray);
        double d = byteArray.readAsDouble(0);
        assertTrue(d == 123.0);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("405EC00000000000BBBB"));
    }

    public void testWriteSwappedDoubleInt() {
        byteArray = new ByteArray(10, ByteOrder.LITTLE_ENDIAN);
        byteArray.fill((byte) 0xBB);
        byteArray.write((double) 123, 0);
        ByteArrayDumpFormatter.dumpByteArray("testWriteSwappedDoubleInt", byteArray);
        double d = byteArray.readAsDouble(0);
        assertTrue(d == 123.0);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("0000000000C05E40BBBB"));
    }

    public void testWriteAsPs() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);

        byteArray.writeAsPs("1234+", 0, 3);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPs1234+", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("01234CBBBBBBBBBBBBBB"));

        byteArray.writeAsPs("+1234", 0, 3);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPs+1234", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("01234CBBBBBBBBBBBBBB"));

        byteArray.writeAsPs("1234-", 1, 4);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPs1234-", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("010001234DBBBBBBBBBB"));

        byteArray.writeAsPs("1234", 0, 3);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPs1234", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("01234F234DBBBBBBBBBB"));
    }

    public void testWriteAsPnsStringIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);

        byteArray.writeAsPns("1234A", 0, 3);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPnsStringIntInt1234A", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("A1234ABBBBBBBBBBBBBB"));

        byteArray.writeAsPns("  123-42", 0, 5);
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPnsStringIntInt123-42", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("AAAA123D42BBBBBBBBBB"));
    }

    public void testWriteAsPnsStringIntIntChar() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);

        byteArray.writeAsPns("1234A", 0, 3, '0');
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPnsStringIntIntChar0", byteArray);
        String value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("01234ABBBBBBBBBBBBBB"));

        byteArray.writeAsPns("  123-42", 0, 5, '0');
        ByteArrayDumpFormatter.dumpByteArray("testWriteAsPnsStringIntIntChar 0", byteArray);
        value = byteArray.readPns(0, 10, false);
        assertTrue(value.equals("00AA123D42BBBBBBBBBB"));
    }

    public void testGetSpaceFiller() {
        byte filler = ByteArray.getSpaceFiller(ByteArray.ASCII_CHARSET_NAME);
        assertTrue(filler == 0x20);
        filler = ByteArray.getSpaceFiller(ByteArray.EBCDIC_CHARSET_NAME);
        assertTrue(filler == 0x40);
    }

    public void testStringToByteArray() {
        byteArray = ByteArray.valueOf("  12--TEST  ");
        String s = byteArray.readAsString();
        assertTrue(s.equals("  12--TEST  "));
    }

    public void testEqualsByteArray() {
        byteArray = ByteArray.valueOf("  12--TEST  ");
        assertTrue(byteArray.equals(byteArray));

        ByteArray ba = ByteArray.valueOf("  12--TEST  ");
        assertTrue(byteArray.equals(ba));

        ba = ByteArray.valueOf("  12--TESTNON");
        assertFalse(byteArray.equals(ba));

        ba = ByteArray.valueOf("  12--TEST     ");
        assertFalse(byteArray.equals(ba));
    }

    public void testEqualTo() {
        byteArray = ByteArray.valueOf("  12--TEST  ");
        ByteArray ba = ByteArray.valueOf("  12--TEST  ");
        assertTrue(ByteArray.equalTo(byteArray, ba));
    }

    public void testEqualSameByte() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        assertTrue(byteArray.equalSame((byte) 0xBB));
    }

    public void testEqualSameByteIntInt() {
        byteArray = new ByteArray(10);
        byteArray.fill((byte) 0xBB);
        byteArray.write((byte) 0xAA, 3);
        byteArray.write((byte) 0xAA, 4);
        byteArray.write((byte) 0xAA, 5);
        assertTrue(byteArray.equalSame((byte) 0xBB, 0, 3));
        assertTrue(byteArray.equalSame((byte) 0xAA, 3, 3));
        assertTrue(byteArray.equalSame((byte) 0xBB, 6, 4));
    }

    public void testGetLength() {
        byteArray = new ByteArray(10);
        assertTrue(byteArray.getLength() == 10);
    }

    public void testGetByteArray() {
        byte[] ba = new byte[20];
        byteArray = new ByteArray(ba);
        doGetByteArray(byteArray);
    }

    public void testGetByteArrayDirect() {
        ByteBuffer bb = ByteBuffer.allocateDirect(20);
        byteArray = new ByteArray(bb);
        doGetByteArray(byteArray);
    }

    public void doGetByteArray(ByteArray byteArray) {
        byteArray.write("TESTTESTTEST", 0);
        byte[] ba = byteArray.getBytes();
        assertTrue(ba.length == 20);
        ByteArray ba2 = new ByteArray(ba);
        String s = ba2.readAsString(0, 20, true);
        assertTrue(s.equals("TESTTESTTEST"));
    }

    public void testSetEncoding() {
    }

}
