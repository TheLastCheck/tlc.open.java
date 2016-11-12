/*******************************************************************************
 * Copyright (c) 2009-2016 The Last Check, LLC, All Rights Reserved
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

package com.thelastcheck.io.cims;

import org.junit.Test;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;

import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class ImageObjectTest {

    @Test
    public void getSegmentType() throws Exception {
        byte[] imageIntel = readTiffImage();
        byte[] imageMotorola = readTiffImageMotorola();
        ImageObject io = new ImageObject();
        io.addSegment(1, imageIntel);
        io.addSegment(2, imageMotorola);
        assertEquals("should be TIFF", ImageObject.TYPE_TIFF, io.getSegmentType(1));
        assertEquals("should be TIFF", ImageObject.TYPE_TIFF, io.getSegmentType(2));
    }

    private byte[] readTiffImage() {
        try {
            ByteSource byteSource = Resources.asByteSource(new URL("file:target/test-classes/Classic.tif"));
            byte[] bytes = byteSource.read();
            return bytes;
        } catch (Exception e) {
            System.out.println(e);
        }
        byte[] array = new byte[4];
        array[0] = 'I';
        array[1] = 'I';
        array[2] = 42;
        array[3] = 0;
        return array;
    }

    private byte[] readTiffImageMotorola() {
        byte[] array = new byte[4];
        array[0] = 'M';
        array[1] = 'M';
        array[2] = 0;
        array[3] = 42;
        return array;
    }

}