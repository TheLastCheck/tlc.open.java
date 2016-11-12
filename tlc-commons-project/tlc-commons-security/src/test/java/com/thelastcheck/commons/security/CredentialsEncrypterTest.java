/**
 * ****************************************************************************
 * The Last Check, LLC
 * 9499 Grove Trail Lane
 * Germantown, TN 38139
 * <p/>
 * Unauthorized distribution, adaptation or use may be subject to civil and
 * criminal penalties.
 * <p/>
 * Copyright (c) 2015, The Last Check, LLC, All rights reserved.
 * ****************************************************************************
 */

package com.thelastcheck.commons.security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CredentialsEncrypterTest {

    @Test
    public void testCipher() throws CredentialsEncryptionException {
        Credentials c = new Credentials("username", "password", "user data");
        CredentialsEncrypter encrypter = new CredentialsEncrypter();
        byte[] bytes = encrypter.encrypt(c);
        Credentials c2 = encrypter.decrypt(bytes);
        assertEquals(c, c2);
    }
}
