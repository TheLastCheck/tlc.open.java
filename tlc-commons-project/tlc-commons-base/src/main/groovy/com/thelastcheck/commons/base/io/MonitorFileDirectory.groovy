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

package com.thelastcheck.commons.base.io

import com.thelastcheck.commons.base.exception.InvalidDirectoryException
import com.thelastcheck.commons.base.utils.FileUtilities
import groovy.util.logging.Slf4j

import java.util.concurrent.TimeUnit

@Slf4j
public class MonitorFileDirectory extends java.util.Observable implements Runnable {
    private String[] suffixes
    private String directoryName
    private File directory
    private Thread thread
    private long sleepTime = TimeUnit.SECONDS.toMillis(1)
    private Map<String, Long> currentFileMap = new HashMap<String, Long>()
    private Map<String, Long> previousFileMap
    private long stableTime = TimeUnit.SECONDS.toMillis(15)

    private MonitorFileDirectory() {
        super();
    }

    public MonitorFileDirectory(String directoryName, String... suffixes) {
        this();
        this.directoryName = directoryName.trim()
        this.suffixes = suffixes.collect {
            it.trim().toLowerCase()
        }
    }

    public void start() {
        this.directory = new File(directoryName)
        if (directoryName == null || directoryName.length() == 0) {
            throw new InvalidDirectoryException(
                    "Value specified for the directory to be monitored is missing")
        }
        directory = new File(directoryName)
        if (!directory.isDirectory()) {
            throw new InvalidDirectoryException(
                    "Value specified for the directory to be monitored is not a valid directory: '"
                            + directory.getAbsolutePath() + "'")
        }
        thread = new Thread(this)
        thread.start()
    }

    public void stop() {
        thread.interrupt()
    }

    public void run() {
        log.info("Monitor started for directory: $directoryName")
        log.info("Looking for files ending in $suffixes")
        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                if (pathname.isFile()) {
                    if (suffixes == null || suffixes.length == 0) {
                        return true;
                    }
                    String fileName = pathname.getAbsolutePath().toLowerCase();
                    for (int i = 0; i < suffixes.length; i++) {
                        if (fileName.endsWith(suffixes[i])) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        while (true) {
            checkForNewFiles(filter);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void checkForNewFiles(FileFilter filter) {
        previousFileMap = currentFileMap
        currentFileMap = new HashMap<String, Long>()
        File[] currentFileList = directory.listFiles(filter)
        currentFileList.each { file ->
            currentFileMap.put(file.getName(), file.lastModified())
        }
        long currentSystemTime = System.currentTimeMillis();
        currentFileMap.keySet().each { fileName ->
            if (previousFileMap.containsKey(fileName)) {
                long previousFileModifiedTime = previousFileMap.get(fileName)
                long currentFileModifiedTime = currentFileMap.get(fileName)
                if ((previousFileModifiedTime == currentFileModifiedTime)
                        && ((currentSystemTime - currentFileModifiedTime) > stableTime)) {
                    File fileReady = new File(directory, fileName)
                    log.info(fileName + " : Arrived")
                    log.info(fileName + " : Size . . . . . . . . " + fileReady.length())
                    log.info(fileName + " : Arrival Date/Time  . " + new Date(fileReady.lastModified()))
                    log.info(fileName + " : Arrival Location . . " + directory.getAbsolutePath())
                    File newFile = FileUtilities.renameFile(fileReady, ".processing", true)
                    setChanged()
                    notifyObservers(newFile)
                }
            }
        }
    }

    public void setSleepTime(int seconds) {
        this.sleepTime = TimeUnit.SECONDS.toMillis(seconds)
    }

    public void setStableTime(int seconds) {
        this.stableTime = TimeUnit.SECONDS.toMillis(seconds)
    }

}