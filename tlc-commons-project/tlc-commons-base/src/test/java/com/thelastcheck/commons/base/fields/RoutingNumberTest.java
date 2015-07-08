/*******************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.thelastcheck.commons.base.fields;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoutingNumberTest {

    @Test
    public void testRt9() {
        RoutingNumber rt = RoutingNumber.valueOf("084000026");
        assertEquals("084000026", rt.toString());
        assertEquals("08400002", rt.getRoutingNumber());
        assertEquals('6', rt.getCheckDigit());
        assertTrue(rt.hasCheckDigit());
        assertTrue(rt.isValid());
    }

    @Test
    public void testRt8NoDash() {
        RoutingNumber rt = RoutingNumber.valueOf("08400002");
        assertTrue(rt.isValid());
        assertEquals("08400002 ", rt.toString());
    }

    @Test
    public void testInvalidRt() {
        RoutingNumber rt = RoutingNumber.valueOf("0840");
        assertFalse(rt.isValid());
        assertEquals("0840     ", rt.toString());
    }

    @Test
    public void testRt8() {
        RoutingNumber rt = RoutingNumber.valueOf("0840-0002");
        assertEquals("08400002 ", rt.toString());
        assertEquals("08400002", rt.getRoutingNumber());
        assertEquals(' ', rt.getCheckDigit());
        assertFalse(rt.hasCheckDigit());
        assertTrue(rt.isValid());

        rt.calculateAndSaveCheckDigit();
        assertEquals("084000026", rt.toString());
        assertEquals("08400002", rt.getRoutingNumber());
        assertEquals('6', rt.getCheckDigit());
        assertTrue(rt.hasCheckDigit());
        assertTrue(rt.isValid());
    }

    @Test
    public void testBadRt() {
        RoutingNumber rt = RoutingNumber.valueOf("084000025");
        assertFalse(rt.isValid());

        rt = RoutingNumber.valueOf("0876*7777");
        assertFalse(rt.isValid());
    }

    @Test
    public void testCanadian() {
        RoutingNumber rt = RoutingNumber.valueOf("084000026");
        assertFalse(rt.isCanadian());

        rt = RoutingNumber.valueOf("08304-222");
        assertTrue(rt.isCanadian());
        assertFalse(rt.hasCheckDigit());
        assertTrue(rt.isValid());
    }
}
