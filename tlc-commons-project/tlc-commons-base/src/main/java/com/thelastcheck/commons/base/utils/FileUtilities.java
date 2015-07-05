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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtilities {

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public static String suffix(File file) {
        String fileName = file.getAbsolutePath();
        FileName fn = new FileName(fileName);
        return fn.getSuffix();
    }

    public static String renameFile(String fileName, String newSuffix) {
        return renameFile(fileName, newSuffix, false);
    }

    public static File renameFile(File file, String newSuffix) {
        return renameFile(file, newSuffix, false);
    }

    public static String renameFile(String fileName, String newSuffix, boolean append) {
    	return renameFile(new File(fileName), newSuffix, append).getAbsolutePath();
    }

    public static File renameFile(File file, String newSuffix, boolean append) {
    	String fileName = file.getAbsolutePath();
        FileName fn = new FileName(fileName);
        if (append) {
            fn = fn.appendSuffix(newSuffix);
        } else {
            fn = fn.replaceSuffix(newSuffix);
        }
        String newFileName = fn.toString();
        if (fileName.equals(newFileName)) {
            return new File(newFileName);
        }
        File newFile = new File(newFileName);
        if (newFile.exists()) {
            newFile.delete();
        }
        file.renameTo(newFile);
        return new File(newFileName);
    }
}
