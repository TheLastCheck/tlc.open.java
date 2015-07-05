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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceHandler {

	private static HashMap resourceMap = null;

	private static Logger log = LoggerFactory.getLogger(ResourceHandler.class);

	/**
	 * Load a properties file using the fully qualified name of the property,
	 * i.e., "com.tlc.base.project". The file loaded must be in the CLASSPATH
	 * and the actual file to be located will be
	 * "com/tlc/base/project.properties". For different locales, the property
	 * file name (not the name being requested) will be appended with the locale
	 * ID. For example: "com/tlc/base/project.properties_fr" will be the name of
	 * the file for French locales.
	 * 
	 * @param resourceName
	 *            The name of the fully qualified property (without the property
	 *            suffix).
	 * @return A loaded Property object.
	 */
	public static Properties getProperties(String resourceName) {
		return getProperties(resourceName, null);
	}

	public static synchronized Properties getProperties(String resourceName,
			Properties defaultValues) {

		Properties properties = new Properties(defaultValues);

		ResourceBundle bundle;
		try {

			bundle = ResourceBundle.getBundle(resourceName);
			Enumeration<String> enum1 = bundle.getKeys();

			while (enum1.hasMoreElements()) {
				String key = enum1.nextElement();
				String value = bundle.getString(key);
				properties.put(key, value);
			}

		} catch (MissingResourceException e) {
		}

		return properties;

	}

}
