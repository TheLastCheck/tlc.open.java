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
import com.google.common.io.ByteSource;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class CredentialsIoTest {

    private File workingDir = new File("target/work");

    @Before
    public void ensureWorkingDirExists() throws IOException {
        if (!workingDir.exists())
            FileUtils.forceMkdir(workingDir);
    }

    @Test
    public void testSinkSource() throws IOException, CredentialsEncryptionException {

        Credentials c = new Credentials("username", "password", "user data");

        final ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        ByteSink sink = new ByteSink() {
            @Override
            public OutputStream openStream() throws IOException {
                return baos;
            }
        };
        CredentialsWriter writer = new CredentialsWriter(sink);
        writer.write(c);

        ByteSource source = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return new ByteArrayInputStream(baos.toByteArray());
            }
        };
        CredentialsReader reader = new CredentialsReader(source);
        Credentials c2 = reader.read();
        assertEquals(c, c2);
    }

    @Test
    public void testFile() throws IOException, CredentialsEncryptionException {
        Credentials c = new Credentials("username", "password", "user data");

        File file = new File(workingDir, "credentials.dat");

        CredentialsWriter writer = new CredentialsWriter(file);
        writer.write(c);

        CredentialsReader reader = new CredentialsReader(file);
        Credentials c2 = reader.read();

        assertEquals(c, c2);
    }
}
