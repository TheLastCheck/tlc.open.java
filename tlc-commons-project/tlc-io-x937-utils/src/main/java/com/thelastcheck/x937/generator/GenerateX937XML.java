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

package com.thelastcheck.x937.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelastcheck.x937.xml.InterfaceDefinition;
import com.thelastcheck.x937.xml.InterfaceDefinitionItem;
import com.thelastcheck.x937.xml.InterfaceDefinitionList;
import com.thelastcheck.x937.xml.InterfaceDefinitionListItem;
import com.thelastcheck.x937.xml.InterfaceField;
import com.thelastcheck.x937.xml.X937GenRules;
import com.thelastcheck.x937.xml.X937GenRulesItem;

public class GenerateX937XML {
	private static Logger log = LoggerFactory.getLogger(GenerateX937XML.class);
	private String currentClassName = null;
	private String rulesFiles = null;
	private String packageNameImpl = null;
	private String sourceLocation = null;
	private File packageFileImpl = null;
	private int fieldArrayCount = 0;
	X937GenRules genRules = null;
	X937GenRulesItem genItem = null;
	InterfaceDefinitionList interfaceDefinitionList = null;
	InterfaceDefinition interfaceDefinition = null;

	/*
	 * C,X937CheckDetailRecordImpl F,AuxiliaryOn-Us,C,03,17,15,NBSM
	 * F,ExternalProcessingCode,C,18,18,1,ANS
	 */

	public static void main(String[] args) {
		new GenerateX937XML().run(args);
	}

	private void run(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String string = args[i];
			if (string.equals("-f")) {
				rulesFiles = args[i + 1];
				i++;
			}
			if (string.equals("-s")) {
				sourceLocation = args[i + 1];
				i++;
			}
			if (string.equals("-p")) {
				packageNameImpl = args[i + 1];
				i++;
			}
		}
		if (rulesFiles == null) {
			rulesFiles = "./gendata/rules.txt";
		}
		if (sourceLocation == null) {
			sourceLocation = "./";
		}
		// String packagePath = packageNameImpl.replace('.', '/');
		packageFileImpl = new File(sourceLocation, "");
		packageFileImpl.mkdirs();

		File xmlFile = new File(packageFileImpl, "genrules_x937.xml");
		if (xmlFile.exists()) {
			xmlFile.delete();
		}

		genRules = new X937GenRules();
		genItem = new X937GenRulesItem();
		genRules.addX937GenRulesItem(genItem);
		interfaceDefinitionList = new InterfaceDefinitionList();
		genItem.setInterfaceDefinitionList(interfaceDefinitionList);
		interfaceDefinitionList.setPackage(packageNameImpl);

		List<String[]> items = readTokensFromFile(rulesFiles);
		for (Iterator<String[]> iter = items.iterator(); iter.hasNext();) {
			String[] element = iter.next();
			if (element[0] == null) {
				continue;
			}
			if (element[0].equals("C")) {
				newClass(element);
			}
			if (element[0].equals("F")) {
				newField(element);
			}
		}
		if (currentClassName != null) {
			flushClass();
		}
		Writer writer = null;
		try {
			writer = new PrintWriter(xmlFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			genRules.marshal(writer);
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	private void flushClass() {
	}

	private void newField(String[] fieldArgs) {
		String argString = formatArgs(fieldArgs);
		if (fieldArgs.length < 8) {
			log.warn("Missing field arguments");
			log.warn(argString);
		}
		String fieldNumber = fieldArgs[1];
		String fieldName = fieldArgs[2];
		String fieldStart = fieldArgs[4];
		String fieldLength = fieldArgs[6];
		String fieldType = fieldArgs[7];

		int fieldNumberInt = 0;
		try {
			fieldNumberInt = Integer.parseInt(fieldNumber);
		} catch (NumberFormatException e) {
			log.warn("invalid field number value");
			log.warn(argString);
		}
		// skip field one - in base X9.37Record class.
		// if (fieldNumberInt == 1) {
		// return;
		// }

		int fieldStartInt = 0;
		try {
			fieldStartInt = Integer.parseInt(fieldStart);
		} catch (NumberFormatException e) {
			log.warn("invalid start value");
			log.warn(argString);
		}
		// make it relative to 0.
		fieldStartInt--;

		InterfaceField field = new InterfaceField();
		field.setName(fieldName);
		field.setType(fieldType);
		InterfaceDefinitionItem interfaceDefinitionItem = new InterfaceDefinitionItem();
		interfaceDefinitionItem.setInterfaceField(field);
		interfaceDefinition.addInterfaceDefinitionItem(interfaceDefinitionItem);
		fieldArrayCount++;
	}

	private String formatArgs(String[] fieldArgs) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < fieldArgs.length; i++) {
			String string = fieldArgs[i];
			if (i > 0) {
				sb.append(" : ");
			}
			if (string == null) {
				sb.append("");
			} else {
				sb.append(string);
			}
		}
		return sb.toString();
	}

	private void newClass(String[] args) {
		String argString = formatArgs(args);
		String className = args[1];
		if (currentClassName != null) {
			flushClass();
		}
		currentClassName = className;
		log.info("Generate class: " + className);

		String recordNumber = args[2];
		if (recordNumber.length() < 2) {
			recordNumber = "0" + recordNumber;
		}

		int currentFieldMax = 0;
		try {
			currentFieldMax = Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			log.warn("invalid number of fields value");
			log.warn(argString);
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 4; i < args.length; i++) {
			if (args[i] != null) {
				if (i > 4) {
					sb.append("_");
				}
				sb.append(args[i]);
			}
		}
		String recordType = sb.toString();

		interfaceDefinition = new InterfaceDefinition();
		interfaceDefinition.setName(className);
		interfaceDefinition.setType(recordType);
		interfaceDefinition.setRecordType(recordNumber);
		InterfaceDefinitionListItem listItem = new InterfaceDefinitionListItem();
		listItem.setInterfaceDefinition(interfaceDefinition);
		interfaceDefinitionList.addInterfaceDefinitionListItem(listItem);
	}

	private List<String[]> readTokensFromFile(String fileName) {
		List<String[]> items = new ArrayList<String[]>();
		try {
			FileReader f = new FileReader(fileName);
			Reader r = new BufferedReader(f);
			StreamTokenizer st = new StreamTokenizer(r);
			st.eolIsSignificant(true);
			String[] args = new String[10];
			int argument = 0;
			while (true) {
				st.nextToken();
				switch (st.ttype) {
				case StreamTokenizer.TT_EOF:
					break;
				case StreamTokenizer.TT_EOL:
					if (args[0] != null && args[0].charAt(0) != ';') {
						items.add(args);
					}
					args = new String[10];
					argument = 0;
					break;
				case StreamTokenizer.TT_NUMBER:
					if (argument >= 10) {
						log.warn("Found too many arguments at " + st.lineno()
								+ " value " + st.nval);
					} else {
						int ival = (int) st.nval;
						args[argument++] = Integer.toString(ival);
					}
					break;
				case StreamTokenizer.TT_WORD:
					if (argument >= 10) {
						log.warn("Found too many arguments at " + st.lineno()
								+ " value " + st.sval);
					} else {
						args[argument++] = st.sval;
					}
					break;
				}
				if (st.ttype == StreamTokenizer.TT_EOF) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}

}
