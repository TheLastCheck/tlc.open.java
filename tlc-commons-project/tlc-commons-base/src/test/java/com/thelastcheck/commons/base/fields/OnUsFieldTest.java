package com.thelastcheck.commons.base.fields;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OnUsFieldTest {

    private OnUsField testField;

    @Before
    public void setup() {
        testField = OnUsField.valueOf("12345/20");
    }

    @Test
    public void testGetOptionalField4() throws Exception {
        assertEquals("", testField.getOptionalField4());
    }

    @Test
    public void testGetAccountNumber() throws Exception {
        assertEquals("12345", testField.getAccountNumber());
    }

    @Test
    public void testGetTranCode() throws Exception {
        assertEquals("20", testField.getTranCode());
    }

    @Test
    public void testEquals() throws Exception {
        OnUsField f = new OnUsField("", "12345", "20");
        assertEquals(f, testField);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("            12345/20", testField.toString());
    }
}