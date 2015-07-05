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

import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jerry Bowman
 *         (c) 2013, The Last Check, All Rights Reserved.
 */
public class RoutingNumberTest {
    private static Logger log = LoggerFactory.getLogger(RoutingNumberTest.class);

    @Test
    public void testRt9() {
        RoutingNumber rt = RoutingNumber.valueOf("084000026");
        Assert.assertEquals("084000026", rt.toString());
        Assert.assertEquals("08400002", rt.getRoutingNumber());
        Assert.assertEquals('6', rt.getCheckDigit());
        Assert.assertTrue(rt.hasCheckDigit());
        Assert.assertTrue(rt.isValid());
    }

    @Test
    public void testRt8NoDash() {
        RoutingNumber rt = RoutingNumber.valueOf("08400002");
        Assert.assertTrue(rt.isValid());
        Assert.assertEquals("08400002 ", rt.toString());

        rt = RoutingNumber.valueOf("0840");
        Assert.assertFalse(rt.isValid());
        Assert.assertEquals("0840     ", rt.toString());
    }

    @Test
    public void testRt8() {
        RoutingNumber rt = RoutingNumber.valueOf("0840-0002");
        Assert.assertEquals("08400002 ", rt.toString());
        Assert.assertEquals("08400002", rt.getRoutingNumber());
        Assert.assertEquals(' ', rt.getCheckDigit());
        Assert.assertFalse(rt.hasCheckDigit());
        Assert.assertTrue(rt.isValid());

        rt.calculateAndSaveCheckDigit();
        Assert.assertEquals("084000026", rt.toString());
        Assert.assertEquals("08400002", rt.getRoutingNumber());
        Assert.assertEquals('6', rt.getCheckDigit());
        Assert.assertTrue(rt.hasCheckDigit());
        Assert.assertTrue(rt.isValid());
    }

    @Test
    public void testBadRt() {
        RoutingNumber rt = RoutingNumber.valueOf("084000025");
        Assert.assertFalse(rt.isValid());

        rt = RoutingNumber.valueOf("0876*7777");
        Assert.assertFalse(rt.isValid());
    }

    @Test
    public void testCanadian() {
        RoutingNumber rt = RoutingNumber.valueOf("084000026");
        Assert.assertFalse(rt.isCanadian());

        rt = RoutingNumber.valueOf("08304-222");
        Assert.assertTrue(rt.isCanadian());
        Assert.assertFalse(rt.hasCheckDigit());
        Assert.assertTrue(rt.isValid());
    }
}
