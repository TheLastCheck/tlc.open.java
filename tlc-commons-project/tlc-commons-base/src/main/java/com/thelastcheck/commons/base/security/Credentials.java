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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Credentials implements Externalizable {

    private String username;
    private String password;
    private String userData;

    public Credentials() {
        super();
        this.username = "";
        this.password = "";
        this.userData = "";
    }

    public Credentials(String userId, String password, String userData) {
        super();
        this.username = userId;
        this.password = password;
        this.userData = userData;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserData() {
        return userData;
    }

    @Override
    public void writeExternal(ObjectOutput output) throws IOException {
        output.writeObject(username);
        output.writeObject(password);
        output.writeObject(userData);
    }

    @Override
    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
        username = (String) input.readObject();
        password = (String) input.readObject();
        userData = (String) input.readObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credentials that = (Credentials) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return !(userData != null ? !userData.equals(that.userData) : that.userData != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userData != null ? userData.hashCode() : 0);
        return result;
    }
}
