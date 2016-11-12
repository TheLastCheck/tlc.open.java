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

import com.google.common.io.ByteSource;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class CredentialsReader {

    private ByteSource source;

    public CredentialsReader(File file) {
        this(Files.asByteSource(file));
    }

    public CredentialsReader(ByteSource source) {
        this.source = source;
    }

    public Credentials read() throws IOException, CredentialsEncryptionException {
        byte[] ba = source.read();
        CredentialsEncrypter encrypter = new CredentialsEncrypter();
        return encrypter.decrypt(ba);
    }
}
