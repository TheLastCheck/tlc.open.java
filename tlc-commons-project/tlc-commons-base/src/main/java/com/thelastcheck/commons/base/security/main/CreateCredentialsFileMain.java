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
package com.thelastcheck.commons.base.security.main;

import com.thelastcheck.commons.base.security.Credentials;
import com.thelastcheck.commons.base.security.CredentialsEncryptionException;
import com.thelastcheck.commons.base.security.CredentialsWriter;

import java.io.File;
import java.io.IOException;

public class CreateCredentialsFileMain {
    
    public static void main(String[] args) {
        try {
            validateArgs(args);
            run(args);
        } catch (Exception e) {
            System.out.println("Problem creating credentials file: " + e.getMessage());
        }
    }

    public static void run(String[] args) throws IOException, CredentialsEncryptionException {
        File file = new File(args[0]);
        String username = args[1];
        String password = args[2];
        String userData = null;
        
        if (args.length >= 4)
            userData = args[3];
        
        Credentials cred = new Credentials(username, password, userData);
        CredentialsWriter writer = new CredentialsWriter(file);
        writer.write(cred);
        
        System.out.println("Saved credentials in file: " + file.getAbsolutePath() );
    }

    private static void validateArgs(String[] args) {
        if (args.length < 3 || args.length > 4)
            throw new IllegalArgumentException("Invalid usage: must specify output_file username password [userdata]");
    }
}
