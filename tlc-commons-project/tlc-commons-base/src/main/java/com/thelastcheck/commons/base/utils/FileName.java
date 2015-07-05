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

/**
 * 
 */
package com.thelastcheck.commons.base.utils;


public class FileName {

    private String              fileName;
    // IRC-106: Support for lower case comparison of file suffixes.
    private String              lcFileName;

    public FileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        int suffixPos = fileName.lastIndexOf('.');
        if (suffixPos < 0) {
            suffixPos = fileName.length() - 1;
        }
        return fileName.substring(suffixPos + 1);
    }

    public boolean endsWith(String suffix) {
        // IRC-106: Support for lower case comparison of file suffixes.
        if (lcFileName == null) {
            lcFileName = fileName.toLowerCase();
        }
        return lcFileName.endsWith(suffix.toLowerCase());
    }

    public FileName appendSuffix(String suffix) {
        String newFileName = fileName + suffix;
        return new FileName(newFileName);
    }

    public FileName stripSuffix() {
        return replaceSuffix("");
    }

    public FileName replaceSuffix(String newSuffix) {
        int suffixPos = fileName.lastIndexOf('.');
        if (suffixPos < 0) {
            suffixPos = fileName.length();
        }
        String newFileName = fileName.substring(0, suffixPos) + newSuffix;
        return new FileName(newFileName);
    }

    @Override
    public String toString() {
        return fileName;
    }
}
