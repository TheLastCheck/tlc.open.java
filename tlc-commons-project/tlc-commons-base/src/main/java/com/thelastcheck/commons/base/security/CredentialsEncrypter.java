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

package com.thelastcheck.commons.base.security;

import com.google.common.base.Preconditions;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CredentialsEncrypter {

    private static final String BLOWFISH = "Blowfish";
    private byte[] key = {(byte) 0x54, (byte) 0x89, (byte) 0xab, (byte) 0xef, (byte) 0x91};

    public byte[] encrypt(Credentials credentials) throws CredentialsEncryptionException {
        Preconditions.checkNotNull(credentials);
        byte[] cipherValue;
        try {
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
            cipherValue = cipher.doFinal(encode(credentials));
        } catch (Exception e) {
            throw new CredentialsEncryptionException(e);
        }
        return cipherValue;
    }

    public Credentials decrypt(byte[] cipherValue) throws CredentialsEncryptionException {
        Preconditions.checkNotNull(cipherValue);
        Credentials credentials;
        try {
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
            credentials = decode(cipher.doFinal(cipherValue));
        } catch (Exception e) {
            throw new CredentialsEncryptionException(e);
        }
        return credentials;
    }

    private Cipher getCipher(int encryptMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec certificate = new SecretKeySpec(key, BLOWFISH);
        Cipher cipher = Cipher.getInstance(BLOWFISH);
        cipher.init(encryptMode, certificate);
        return cipher;
    }

    private byte[] encode(Credentials credentials) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream temp = new ObjectOutputStream(bout);
        temp.writeObject(credentials);
        temp.close();
        return bout.toByteArray();
    }

    private Credentials decode(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        ObjectInputStream oin = new ObjectInputStream(bin);
        return (Credentials) oin.readObject();
    }

}
