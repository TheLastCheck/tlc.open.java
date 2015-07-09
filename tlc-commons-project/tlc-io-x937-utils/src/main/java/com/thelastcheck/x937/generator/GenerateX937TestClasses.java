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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateX937TestClasses {
	private static Logger log = LoggerFactory.getLogger(GenerateX937TestClasses.class);
	private String currentClassName = null;
	private int currentFieldMax = 0;
	private String rulesFiles = null;
	private String packageName = null;
	private String sourceLocation = null;
	private File packageFile = null;
	private StringWriter classTop = null;
	private StringWriter classMain = null;
	private PrintWriter classWriterTop = null;
	private PrintWriter classWriterMain = null;
	private int fieldArrayCount = 0;

	/*
	 * C,X937CheckDetailRecordImpl F,AuxiliaryOn-Us,C,03,17,15,NBSM
	 * F,ExternalProcessingCode,C,18,18,1,ANS
	 */

	public static void main(String[] args) {
		GenerateX937TestClasses generator = new GenerateX937TestClasses();
		for (int i = 0; i < args.length; i++) {
			String string = args[i];
			if (string.equals("-f")) {
				generator.rulesFiles = args[i + 1];
				i++;
			}
			if (string.equals("-p")) {
				generator.packageName = args[i + 1];
				i++;
			}
			if (string.equals("-s")) {
				generator.sourceLocation = args[i + 1];
				i++;
			}
		}
		if (generator.rulesFiles == null) {
			generator.rulesFiles = "./gendata/rules.txt";
		}
		if (generator.sourceLocation == null) {
			generator.sourceLocation = "./";
		}
		if (generator.packageName == null) {
			generator.packageName = "test";
		}
		String packagePath = generator.packageName.replace('.', '/');
		generator.packageFile = new File(generator.sourceLocation, packagePath);
		generator.packageFile.mkdirs();

		List<String[]> items = generator.readTokensFromFile(generator.rulesFiles);
		for (Iterator<String[]> iter = items.iterator(); iter.hasNext();) {
			String[] element = iter.next();
			if (element[0] == null) {
				continue;
			}
			if (element[0].equals("C")) {
				generator.newClass(element);
			}
			if (element[0].equals("F")) {
				generator.newField(element);
			}
		}
		if (generator.currentClassName != null) {
			generator.flushClass();
		}
	}

	private void flushClass() {
		classWriterTop.println("    }");
		classWriterTop.println();
		classWriterMain.println("}");

		PrintStream classStream = null;
		File classFile = new File(packageFile, currentClassName + ".java");
		if (classFile.exists()) {
			classFile.delete();
		}
		try {
			classStream = new PrintStream(new FileOutputStream(classFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		classStream.println(classTop.toString());
		classStream.println(classMain.toString());
		classStream.close();
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
		
		int fieldNumberInt = 0;
		try {
			fieldNumberInt = Integer.parseInt(fieldNumber);
		} catch (NumberFormatException e) {
			log.warn("invalid field number value");
			log.warn(argString);
		}
		// skip field one - in base X9.37Record class.
		if (fieldNumberInt == 1) {
			return;
		}
		
		int fieldStartInt = 0;
		try {
			fieldStartInt = Integer.parseInt(fieldStart);
		} catch (NumberFormatException e) {
			log.warn("invalid start value");
			log.warn(argString);
		}
		// make it relative to 0.
		fieldStartInt--; 
		
		classWriterTop.println(
				"        fields["+fieldArrayCount+"] = " +
				"new X9Field(\""  +
						fieldName   + "\", " +
						fieldNumber + ", " +
						fieldStartInt  + ", " +
						fieldLength + ");"
        );
		
		classWriterMain.println("    /*");
		classWriterMain.println("     * " + argString);
		classWriterMain.println("     */");
		classWriterMain.println("    public String get" + fieldName + "() {");
		classWriterMain.println("        return getFieldAsString(fields["+fieldArrayCount+"]);");
		classWriterMain.println("    }");
		classWriterMain.println();
		classWriterMain.println("    public void set" + fieldName + "(String value) {");
		classWriterMain.println("        setField(value, fields["+fieldArrayCount+"]);");
		classWriterMain.println("    }");
		classWriterMain.println();
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
		
		currentFieldMax = 0;
		try {
			currentFieldMax = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			log.warn("invalid number of fields value");
			log.warn(argString);
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 3; i < args.length; i++) {
			if (args[i] != null) {
				
				if (i > 3) {
					sb.append("_");
				}
				sb.append(args[i]);
			}
		}
		classTop = new StringWriter(2048);
		classWriterTop = new PrintWriter(classTop);
		classMain = new StringWriter(8096);
		classWriterMain = new PrintWriter(classMain);
		String recordType = sb.toString();
		classWriterTop.println("package " + packageName + ";");
		classWriterTop.println();

		classWriterTop.println("import com.tlc.base.utils.ByteArray;");
		classWriterTop.println("import com.tlc.io.x937.records.stddstu.X937Field;");
		classWriterTop.println("import com.tlc.io.x937.records.stddstu.X937RecordImpl;");
		classWriterTop.println();
		
		classWriterTop.println("/**");
		classWriterTop.println(" * @author Jerry Bowman");
		classWriterTop.println(" * @copyright (c) 2009, TheLastCheck.com");
		classWriterTop.println(" * All Rights Reserved.");
		classWriterTop.println(" */");
		classWriterTop.println();
		classWriterTop.println("public class " + className 	+ " extends X9RecordImpl {");
		classWriterTop.println();
		classWriterTop.println("    private static int maxFieldNumber = "+currentFieldMax+";");
		classWriterTop.println("    private static X9Field fields[] = new X9Field[maxFieldNumber+1];");
		classWriterTop.println();
		
		classWriterTop.println("    static {");
		classWriterTop.println("        fields[0] = null;");
        classWriterTop.println("        fields[1] = recordTypeField;");
        fieldArrayCount = 2;
		
		classWriterMain.println("    /*");
		classWriterMain.println("     * " + argString);
		classWriterMain.println("     */");
		classWriterMain.println();
		
		classWriterMain.println("    public " + className + "() {");
		classWriterMain.println("        super();");
		classWriterMain.println("        setRecordType(X9RecordImpl." + recordType + ");");
		classWriterMain.println("    }");
		classWriterMain.println();

		classWriterMain.println("    public " + className + "(int stdLevel) {");
		classWriterMain.println("        super(X9RecordImpl." + recordType + ", stdLevel);");
		classWriterMain.println("    }");
		classWriterMain.println();

		classWriterMain.println("    public " + className + "(String encoding, int stdLevel) {");
		classWriterMain.println("        super(X9RecordImpl." + recordType + ", encoding, stdLevel);");
		classWriterMain.println("    }");
		classWriterMain.println();

		classWriterMain.println("    public " + className + "(ByteArray record, int stdLevel) {");
		classWriterMain.println("        super(record, stdLevel);");
		classWriterMain.println("    }");
		classWriterMain.println();

		classWriterMain.println("    @Override");
		classWriterMain.println("    protected X9Field field(int fieldNumber) {");
		classWriterMain.println("        if (fieldNumber < 1 || fieldNumber > maxFieldNumber) {");
		classWriterMain.println("            throw new IllegalArgumentException(INVALID_FIELD_NUMBER);");
		classWriterMain.println("        }");
		classWriterMain.println("        return fields[fieldNumber];");
		classWriterMain.println("    }");
		classWriterMain.println();

		classWriterMain.println();
	}

	private List<String[]> readTokensFromFile(String fileName) {
		List<String[]> items = new ArrayList<String[]>();
		try {
			FileReader f = new FileReader(fileName);
			Reader r = new BufferedReader(f);
			StreamTokenizer st = new StreamTokenizer(r);
			st.eolIsSignificant(true);
			String[] args = new String[8];
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
					args = new String[8];
					argument = 0;
					break;
				case StreamTokenizer.TT_NUMBER:
					if (argument >= 8) {
						log.warn("Found too many arguments at " + st.lineno()
								+ " value " + st.nval);
					} else {
						int ival = (int) st.nval;
						args[argument++] = Integer.toString(ival);
					}
					break;
				case StreamTokenizer.TT_WORD:
					if (argument >= 8) {
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
