/*******************************************************************************
 * The Last Check, LLC
 * 9499 Grove Trail Lane
 * Germantown, TN 38139
 *
 * Unauthorized distribution, adaptation or use may be subject to civil and
 * criminal penalties.
 *
 * Copyright (c) 2015, The Last Check, LLC, All rights reserved.
 ******************************************************************************/

package com.thelastcheck.commons.base.security;

import com.google.common.io.ByteSink;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class CredentialsWriter {

    final private ByteSink sink;

    public CredentialsWriter(File file) {
        this(Files.asByteSink(file));
    }

    public CredentialsWriter(ByteSink sink) {
        this.sink = sink;
    }

    public void write(Credentials credentials) throws IOException, CredentialsEncryptionException {
        CredentialsEncrypter encrypter = new CredentialsEncrypter();
        byte[] bytes = encrypter.encrypt(credentials);
        sink.write(bytes);
    }
}
