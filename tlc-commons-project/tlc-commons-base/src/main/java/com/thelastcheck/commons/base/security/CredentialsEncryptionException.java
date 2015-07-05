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


public class CredentialsEncryptionException extends Exception {

    private static final long serialVersionUID = -1539589352038493381L;

    public CredentialsEncryptionException() {
    }

    public CredentialsEncryptionException(String message) {
        super(message);
    }

    public CredentialsEncryptionException(Throwable cause) {
        super(cause);
    }

    public CredentialsEncryptionException(String message, Throwable cause) {
        super(message, cause);
    }

}
