package com.thelastcheck.commons.security;

import java.util.ListResourceBundle;

public class EncryptBytes extends ListResourceBundle {
    private static byte[] key = {(byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0xaa, (byte) 0xff};
    @Override
    protected Object[][] getContents() {

        // A way to create a set of unique bytes without exposing the bytes used in the code.
        byte[] modifiedKey = new byte[key.length];
        int i = 0;
        for (byte b : key) {
            modifiedKey[i++] = (byte) (b &  0x00e3);
        }

        return new Object[][]{
                {"key", modifiedKey}
        };
    }
}
