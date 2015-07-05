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

import groovy.util.logging.Slf4j
import org.junit.Test

import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

@Slf4j
class MonitorFileDirectoryTest implements Observer {

    int fileCount

    private void setup() {
        Path path1 = Paths.get("target", "test1.tst1")
        def file = path1.toFile()
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
        Path path2 = Paths.get("target", "test1.tst2")
        file = path2.toFile()
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
    }

    @Test
    public void testMonitor() {
        setup()
        fileCount = 0
        MonitorFileDirectory monitor = new MonitorFileDirectory("target", "tst1", "tst2")
        monitor.addObserver(this)
        monitor.setStableTime(2)
        monitor.start()
        Thread.sleep(TimeUnit.SECONDS.toMillis(5))
        monitor.stop()
        assert fileCount == 2
    }

    @Override
    void update(Observable o, Object arg) {
        log.info("Processing file: $arg")
        fileCount++
    }
}
