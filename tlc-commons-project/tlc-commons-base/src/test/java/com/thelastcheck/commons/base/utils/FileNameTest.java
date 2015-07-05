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

package com.thelastcheck.commons.base.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * @author Jerry Bowman
 *         (c) 2013, The Last Check, All Rights Reserved.
 */
public class FileNameTest {
    private static Logger log = LoggerFactory.getLogger(FileNameTest.class);

    @Test
    public void testGetSuffix() {
        FileName fn = new FileName("test.suf");
        assertEquals("suf", fn.getSuffix());

        fn = new FileName("test");
        assertEquals("", fn.getSuffix());

        fn = new FileName("my.test.file.suf");
        assertEquals("suf", fn.getSuffix());

        fn = new FileName("");
        assertEquals("", fn.getSuffix());

        fn = new FileName(".suf");
        assertEquals("suf", fn.getSuffix());
    }
}
