package com.thelastcheck.commons.buffer;

import java.io.IOException;

import junit.framework.TestCase;

public class ByteArrayReaderTest extends TestCase {

    public void testReadCharArrayIntInt() throws IOException {
        String s = "ABCDEFG1234--ABCDEFG1234--ABCDEFG1234--12345";

        ByteArray ba = new ByteArray(s.length());
        ba.write(s, 0);
        read(s, ba);

        ba = new ByteArray(s.length(), ByteArray.EBCDIC_CHARSET_NAME);
        ba.write(s, 0);
        read(s, ba);

    }

    private void read(String s, ByteArray ba) throws IOException {
        ByteArrayReader reader = new ByteArrayReader(ba);

        ByteArrayDumpFormatter.dumpByteArray("original", ba);
        
        int i;
        reader.mark(0);
        while ((i = reader.read()) != -1) {
            System.out.print((char) i);
        }
        System.out.println();

        reader.reset();
        char[] ca = new char[s.length()];
        System.out.println("Count: " + reader.read(ca));
        System.out.println(ca);

        reader.reset();
        ca = new char[s.length() + 4];
        System.out.println("Count: " + reader.read(ca));
        System.out.println(ca);

        reader.reset();
        ca = new char[s.length() - 4];
        System.out.println("Count: " + reader.read(ca));
        System.out.println(ca);

        System.out.println("Count: " + reader.read(ca));
        System.out.println(ca);
    }

}
