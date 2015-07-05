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

public class ToXmlBuilder {

	private String elementName;
	private boolean useAttributes;
	private StringBuilder sb;

	public ToXmlBuilder(String elementName) {
		this(elementName, false, 256);
	}

	public ToXmlBuilder(String elementName, boolean useAttributes) {
		this(elementName, useAttributes, 256);
	}

	public ToXmlBuilder(String elementName, boolean useAttributes, int size) {
		this.elementName = elementName;
		this.useAttributes = useAttributes;
		this.sb = new StringBuilder(size);
		sb.append("<");
		sb.append(elementName);
		if (useAttributes == false) {
			sb.append(">");
		}
	}

	public void append(String tagName, String value) {
		if (useAttributes) {
			sb.append(" ");
			sb.append(tagName);
			sb.append("=\"");
			sb.append(value);
			sb.append("\"");
		} else {
			sb.append("<");
			sb.append(tagName);
			sb.append(">");
			sb.append(value);
			sb.append("</");
			sb.append(tagName);
			sb.append(">");
		}
	}

	public String toString() {
		if (useAttributes) {
			sb.append("/>");
		} else {
			sb.append("</");
			sb.append(elementName);
			sb.append(">");
		}
		return sb.toString();
	}
}
