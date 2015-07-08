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


public class Timer {

	private static Object synch = new Object();
	private static int unnamedCounter;
	private String name;
	private long milliseconds;
	private long startTime;

	public Timer() {
		synchronized (synch) {
			unnamedCounter++;
		}
		setName("Timer " + unnamedCounter);
	}

	public Timer(String name) {
		setName(name);
	}

	public void reset() {
		milliseconds = 0;
		startTime = 0;
	}

	public void start() {
		startTime = System.currentTimeMillis();
	}

	public void stop() {
		if (startTime == 0) {
			throw new IllegalStateException("Timer not started");
		}
		milliseconds += (System.currentTimeMillis() - startTime);
		startTime = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String string) {
		name = string;
	}

	public String toString() {
		return name + " - elapsed time (ms): " + milliseconds;
	}

	public long getMilliseconds() {
		return milliseconds;
	}

}
