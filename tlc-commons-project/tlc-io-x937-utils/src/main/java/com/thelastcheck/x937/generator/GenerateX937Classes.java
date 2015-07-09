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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.x937.xml.ClassDefinition;
import com.thelastcheck.x937.xml.ClassDefinitionItem;
import com.thelastcheck.x937.xml.ClassDefinitionList;
import com.thelastcheck.x937.xml.ClassDefinitionListItem;
import com.thelastcheck.x937.xml.ClassField;
import com.thelastcheck.x937.xml.ClassFieldItem;
import com.thelastcheck.x937.xml.InterfaceDefinition;
import com.thelastcheck.x937.xml.InterfaceDefinitionItem;
import com.thelastcheck.x937.xml.InterfaceDefinitionList;
import com.thelastcheck.x937.xml.InterfaceDefinitionListItem;
import com.thelastcheck.x937.xml.InterfaceField;
import com.thelastcheck.x937.xml.X937GenRules;
import com.thelastcheck.x937.xml.X937GenRulesItem;

public class GenerateX937Classes {

    private static Logger log = LoggerFactory
            .getLogger(GenerateX937Classes.class);
    private String currentClassName = null;
    private int currentFieldMax = 0;
    private String rulesFile = null;
    private String packageName = null;
    private String sourceLocation = null;
    private File packageFile = null;
    private StringWriter classTop = null;
    private StringWriter classMain = null;
    private PrintWriter classWriterTop = null;
    private PrintWriter classWriterMain = null;
    private boolean generatingBaseClasses = false;
    private boolean generatingInterfaces = false;
    private String packageNameInterface;

    /*
     * C,X937CheckDetailRecordImpl F,AuxiliaryOn-Us,C,03,17,15,NBSM
     * F,ExternalProcessingCode,C,18,18,1,ANS
     */

    public static void main(String[] args) {
        GenerateX937Classes generator = new GenerateX937Classes();
        generator.run(args);
    }

    public void run(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String string = args[i];
            if (string.equals("-f")) {
                rulesFile = args[i + 1];
                i++;
            }
            if (string.equals("-p")) {
                packageName = args[i + 1];
                i++;
            }
            if (string.equals("-s")) {
                sourceLocation = args[i + 1];
                i++;
            }
        }
        if (rulesFile == null) {
            rulesFile = "./src/test/resources/rules.txt";
        }
        if (sourceLocation == null) {
            sourceLocation = "./";
        }
        if (packageName == null) {
            packageName = "test";
        }

        X937GenRules genRules = null;
        try {
            Reader reader = new FileReader(rulesFile);
            genRules = X937GenRules.unmarshal(reader);
            reader.close();
        } catch (MarshalException e) {
            e.printStackTrace();
            return;
        } catch (ValidationException e) {
            e.printStackTrace();
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        for (X937GenRulesItem genItem : genRules.getX937GenRulesItem()) {
            InterfaceDefinitionList iList = genItem
                    .getInterfaceDefinitionList();
            if (iList != null) {
                generatingInterfaces = true;
                processInterfaceList(iList);
                generatingInterfaces = false;
                generatingBaseClasses = true;
                processClassBaseList(iList);
            }
            ClassDefinitionList cList = genItem.getClassDefinitionList();
            if (cList != null) {
                processClassImplList(cList);
            }
        }

    }

    private void processClassImplList(ClassDefinitionList list) {
        packageNameInterface = list.getPackage();
        packageName = packageNameInterface + ".std" + list.getStandard();
        String packagePath = packageName.replace('.', '/');
        packageFile = new File(sourceLocation, packagePath);
        packageFile.mkdirs();
        ClassDefinitionListItem[] items = list.getClassDefinitionListItem();
        for (ClassDefinitionListItem item : items) {
            ClassDefinition classDef = item.getClassDefinition();
            generateClassImpl(classDef);
            String interfaceName = classDef.getName();
            ClassDefinitionItem[] classDefItemList = classDef
                    .getClassDefinitionItem();
            for (ClassDefinitionItem classDefItem : classDefItemList) {
                ClassField classField = classDefItem.getClassField();
                generateFieldImpl(interfaceName, classField);
            }
            flushClass();
        }
    }

    private void processInterfaceList(InterfaceDefinitionList list) {
        packageNameInterface = list.getPackage();
        packageName = list.getPackage();
        String packagePath = packageName.replace('.', '/');
        packageFile = new File(sourceLocation, packagePath);
        packageFile.mkdirs();

        InterfaceDefinitionListItem[] items = list
                .getInterfaceDefinitionListItem();
        for (InterfaceDefinitionListItem item : items) {
            InterfaceDefinition classDef = item.getInterfaceDefinition();
            generateClassInterface(classDef);
            String interfaceName = classDef.getName();
            InterfaceDefinitionItem[] classDefItemList = classDef
                    .getInterfaceDefinitionItem();
            for (InterfaceDefinitionItem classDefItem : classDefItemList) {
                InterfaceField classField = classDefItem.getInterfaceField();
                generateFieldInterface(interfaceName, classField);
            }
            flushClass();
        }
    }

    private void processClassBaseList(InterfaceDefinitionList list) {
        packageNameInterface = list.getPackage();
        packageName = list.getPackage() + ".base";
        String packagePath = packageName.replace('.', '/');
        packageFile = new File(sourceLocation, packagePath);
        packageFile.mkdirs();

        InterfaceDefinitionListItem[] items = list
                .getInterfaceDefinitionListItem();
        for (InterfaceDefinitionListItem item : items) {
            InterfaceDefinition classDef = item.getInterfaceDefinition();
            generateClassBase(classDef);
            String interfaceName = classDef.getName();
            InterfaceDefinitionItem[] classDefItemList = classDef
                    .getInterfaceDefinitionItem();
            for (InterfaceDefinitionItem classDefItem : classDefItemList) {
                InterfaceField classField = classDefItem.getInterfaceField();
                generateFieldBase(interfaceName, classField);
            }
            flushClass();
        }
    }

    private void generateFieldImpl(String interfaceName, ClassField classField) {
        int fieldNumber = classField.getNumber();
        String fieldName = classField.getName();
        int fieldStart = classField.getOffset();
        int fieldLength = classField.getLength();
        boolean dynamicField = classField.getFieldDynamic();
        boolean setterPrivate = classField.getSetter().equals("private");
        String setterCode = null;
        if (classField.getClassFieldItemCount() > 0) {
            ClassFieldItem cfi = classField.getClassFieldItem(0);
            setterCode = cfi.getSetField();
            if (setterCode != null) {
                setterCode = setterCode.trim();
            }
        }
        // For now we will default field type to string.
        // String fieldType = fieldArgs[7];
        String fieldType = null;
        if (classField.getType().length() > 0) {
            fieldType = classField.getType();
        }

        // skip field one - in base X9.37Record class.
        if (fieldNumber == 1) {
            return;
        }

        // make it relative to 0.
        // fieldStart--;
        FieldType fieldTypeValue = null;

        String fieldNameOriginal = fieldName;

        // make the first character lower case, but only if the second character
        // is already lower case. This allows for names beginning with an
        // acronym to not be changed.
        if (Character.isLowerCase(fieldName.charAt(1))) {
            fieldName = fieldName.substring(0, 1).toLowerCase()
                        + fieldName.substring(1);
        }

        if (fieldType != null && fieldType.length() > 0) {
            fieldTypeValue = calculateFieldType(fieldType, fieldLength);
        } else {
            fieldTypeValue = FieldType.STRING;
        }
        if (dynamicField) {
            classWriterTop.println("        fields[" + fieldNumber
                                   + "] = null;");
        } else {
            classWriterTop.println("        fields[" + fieldNumber + "] = "
                                   + "new Field(\"" + fieldNameOriginal + "\", " + fieldNumber
                                   + ", " + fieldStart + ", " + fieldLength + ", FieldType."
                                   + fieldTypeValue + ");");
        }
        switch (fieldTypeValue) {
            case STRING:
                stringMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                break;
            case INT:
                stringMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                intMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case LONG:
                stringMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                longMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case BINARY:
                binaryMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                break;
            case ROUTING_NUMBER:
                routingNumberMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                stringMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case ONUS:
                onUsMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                stringMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case DATE:
                dateMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                stringMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case TIME:
                timeMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                stringMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
        }
        classWriterMain.println();
    }

    private void generateFieldBase(String interfaceName, InterfaceField classField) {
        String fieldName = classField.getName();
        int fieldLength = classField.getLength();
        // For now we will default field type to string.
        // String fieldType = fieldArgs[7];
        String fieldType = null;
        if (classField.getType().length() > 0) {
            fieldType = classField.getType();
        }

        boolean setterPrivate = false;
        String setterType = classField.getSetter();
        if (setterType != null && setterType.equalsIgnoreCase("private")) {
            setterPrivate = true;
        }
        createFieldMethods(interfaceName, 0, fieldName, 1, fieldLength, fieldType,
                setterPrivate, null);
    }

    private void generateFieldInterface(String interfaceName, InterfaceField classField) {
        String fieldName = classField.getName();
        int fieldLength = classField.getLength();
        // For now we will default field type to string.
        // String fieldType = fieldArgs[7];
        String fieldType = null;
        if (classField.getType().length() > 0) {
            fieldType = classField.getType();
        }

        boolean setterPrivate = false;
        String setterType = classField.getSetter();
        if (setterType != null && setterType.equalsIgnoreCase("private")) {
            setterPrivate = true;
        }
        createFieldMethods(interfaceName, 0, fieldName, 1, fieldLength, fieldType,
                setterPrivate, null);
    }

    /**
     * @param fieldNumber
     * @param fieldName
     * @param fieldStart
     * @param fieldLength
     * @param fieldType
     * @param setterPrivate TODO
     * @param setterCode TODO
     */
    private void createFieldMethods(String interfaceName, int fieldNumber, String fieldName,
            int fieldStart, int fieldLength, String fieldType,
            boolean setterPrivate, String setterCode) {
        // skip field one - in base X9.37Record class.
        if (fieldNumber == 1) {
            return;
        }
        if (fieldName.equals("RecordType")) {
            return;
        }

        // make it relative to 0.
        fieldStart--;
        FieldType fieldTypeValue = null;

        String fieldNameOriginal = fieldName;

        // make the first character lower case, but only if the second character
        // is already lower case. This allows for names beginning with an
        // acronym to not be changed.
        if (Character.isLowerCase(fieldName.charAt(1))) {
            fieldName = fieldName.substring(0, 1).toLowerCase()
                        + fieldName.substring(1);
        }

        if (fieldType != null && fieldType.length() > 0) {
            fieldTypeValue = calculateFieldType(fieldType, fieldLength);
        } else {
            fieldTypeValue = FieldType.STRING;
        }

        switch (fieldTypeValue) {
            case STRING:
                stringMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                break;
            case INT:
                stringMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                intMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case LONG:
                stringMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                longMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case BINARY:
                binaryMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                break;
            case ROUTING_NUMBER:
                routingNumberMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                stringMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case ONUS:
                onUsMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                stringMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case DATE:
                dateMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                stringMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
            case TIME:
                timeMethods(interfaceName, fieldNumber, fieldName, false, setterPrivate, setterCode);
                classWriterMain.println();
                stringMethods(interfaceName, fieldNumber, fieldName, true, setterPrivate, setterCode);
                break;
        }
        classWriterMain.println();
    }

    /**
     * @param fieldNumber TODO
     * @param fieldName
     * @param setterPrivate TODO
     * @param setterText TODO
     */
    private void stringMethods(String interfaceName, int fieldNumber, String fieldName,
            boolean asString, boolean setterPrivate, String setterCode) {
        if (generatingInterfaces) {
            if (asString) {
                classWriterMain.println("    String " + fieldName
                                        + "AsString();");
            } else {
                classWriterMain.println("    String " + fieldName
                                        + "();");
            }
        } else {
            if (asString) {
                classWriterMain.println("    public String " + fieldName
                                        + "AsString() {");
            } else {
                classWriterMain.println("    public String " + fieldName
                                        + "() {");
            }
            if (generatingBaseClasses) {
                classWriterMain
                        .println("        throw new InvalidStandardLevelException();");
            } else {
                classWriterMain
                        .println("        return getFieldAsString(field("
                                 + fieldNumber + "));");
            }
            classWriterMain.println("    }");
            classWriterMain.println();
        }
        String dataType = "String";
        setterMethod(interfaceName, fieldNumber, fieldName, setterPrivate, setterCode,
                dataType);
    }

    /**
     * @param fieldNumber
     * @param fieldName
     * @param setterPrivate
     * @param setterCode
     * @param dataType
     */
    private void setterMethod(String interfaceName, int fieldNumber, String fieldName,
            boolean setterPrivate, String setterCode, String dataType) {
        if (generatingInterfaces && !setterPrivate) {
            classWriterMain.println("    " +
                                    interfaceName +
                                    " " + fieldName + "("
                                    + (dataType.equals("Time") ? "Date" : dataType)
                                    + " value);");
        }
        if (generatingBaseClasses && !setterPrivate) {
            classWriterMain.println("    public " +
                                    interfaceName +
                                    " " + fieldName + "("
                                    + (dataType.equals("Time") ? "Date" : dataType)
                                    + " value) {");
            classWriterMain
                    .println("        throw new InvalidStandardLevelException();");
            classWriterMain.println("    }");
        }
        if (!generatingBaseClasses && !generatingInterfaces) {
            classWriterMain.print("    "
                                  + (setterPrivate ? "private" : "public"));
            classWriterMain.println(" " +
                                    interfaceName +
                                    " " + fieldName + "("
                                    + (dataType.equals("Time") ? "Date" : dataType)
                                    + " value) {");
            if (setterCode == null) {
                classWriterMain.print("        setField");
                if (dataType.equals("Date")) {
                    classWriterMain.print("Date(value, field(" + fieldNumber
                                          + "), x9TimeZone);");
                } else if (dataType.equals("Time")) {
                    classWriterMain.print("Time(value, field(" + fieldNumber
                                          + "), x9TimeZone);");
                } else {
                    classWriterMain.println("(value, field(" + fieldNumber
                                            + "));");
                }
            } else {
                classWriterMain.println("        " + setterCode);
            }
            classWriterMain.println("        return this;");
            classWriterMain.println("    }");
        }
    }

    /**
     * @param fieldName
     * @param asInt
     * @param setterPrivate TODO
     * @param setterCode TODO
     */
    private void intMethods(String interfaceName, int fieldNumber, String fieldName, boolean asInt,
            boolean setterPrivate, String setterCode) {
        if (asInt) {
            classWriterMain.println("    public int " + fieldName + "AsInt()");
        } else {
            classWriterMain.println("    public int " + fieldName + "()");
        }
        if (generatingInterfaces) {
            classWriterMain.println("        throws InvalidDataException;");
        } else {
            classWriterMain.println("        throws InvalidDataException {");
            if (generatingBaseClasses) {
                classWriterMain
                        .println("        throw new InvalidStandardLevelException();");
            } else {
                classWriterMain.println("        return getFieldAsInt(field("
                                        + fieldNumber + "));");
            }
            classWriterMain.println("    }");
            classWriterMain.println();
        }
        String dataType = "int";
        setterMethod(interfaceName, fieldNumber, fieldName, setterPrivate, setterCode,
                dataType);
    }

    /**
     * @param fieldName
     * @param asLong
     * @param setterPrivate TODO
     * @param setterCode TODO
     */
    private void longMethods(String interfaceName, int fieldNumber, String fieldName, boolean asLong,
            boolean setterPrivate, String setterCode) {
        if (asLong) {
            classWriterMain
                    .println("    public long " + fieldName + "AsLong()");
        } else {
            classWriterMain.println("    public long " + fieldName + "()");
        }
        if (generatingInterfaces) {
            classWriterMain.println("        throws InvalidDataException;");
        } else {
            classWriterMain.println("        throws InvalidDataException {");
            if (generatingBaseClasses) {
                classWriterMain
                        .println("        throw new InvalidStandardLevelException();");
            } else {
                classWriterMain.println("        return getFieldAsLong(field("
                                        + fieldNumber + "));");
            }
            classWriterMain.println("    }");
            classWriterMain.println();
        }
        String dataType = "long";
        setterMethod(interfaceName, fieldNumber, fieldName, setterPrivate, setterCode,
                dataType);
    }

    /**
     * @param fieldName
     * @param asDate
     * @param setterPrivate TODO
     * @param setterCode TODO
     */
    private void dateMethods(String interfaceName, int fieldNumber, String fieldName, boolean asDate,
            boolean setterPrivate, String setterCode) {
        if (asDate) {
            classWriterMain
                    .println("    public Date " + fieldName + "AsDate()");
        } else {
            classWriterMain.println("    public Date " + fieldName + "()");
        }
        if (generatingInterfaces) {
            classWriterMain.println("        throws InvalidDataException;");
        } else {
            classWriterMain.println("        throws InvalidDataException {");
            if (generatingBaseClasses) {
                classWriterMain
                        .println("        throw new InvalidStandardLevelException();");
            } else {
                classWriterMain.println("        return getFieldAsDate(field("
                                        + fieldNumber + "), x9TimeZone);");
            }
            classWriterMain.println("    }");
            classWriterMain.println();
        }
        String dataType = "Date";
        setterMethod(interfaceName, fieldNumber, fieldName, setterPrivate, setterCode,
                dataType);
    }

    /**
     * @param fieldName
     * @param asTime
     * @param setterPrivate TODO
     * @param setterCode TODO
     */
    private void timeMethods(String interfaceName, int fieldNumber, String fieldName, boolean asTime,
            boolean setterPrivate, String setterCode) {
        if (asTime) {
            classWriterMain
                    .println("    public Date " + fieldName + "AsTime()");
        } else {
            classWriterMain.println("    public Date " + fieldName + "()");
        }
        if (generatingInterfaces) {
            classWriterMain.println("        throws InvalidDataException;");
        } else {
            classWriterMain.println("        throws InvalidDataException {");
            if (generatingBaseClasses) {
                classWriterMain
                        .println("        throw new InvalidStandardLevelException();");
            } else {
                classWriterMain.println("        return getFieldAsTime(field("
                                        + fieldNumber + "), x9TimeZone);");
            }
            classWriterMain.println("    }");
            classWriterMain.println();
        }
        String dataType = "Time";
        setterMethod(interfaceName, fieldNumber, fieldName, setterPrivate, setterCode,
                dataType);
    }

    /**
     * @param fieldName
     * @param asRoutingNumber
     * @param setterPrivate TODO
     * @param setterCode TODO
     */
    private void routingNumberMethods(String interfaceName, int fieldNumber, String fieldName,
            boolean asRoutingNumber, boolean setterPrivate, String setterCode) {
        if (generatingInterfaces) {
            if (asRoutingNumber) {
                classWriterMain.println("    RoutingNumber " + fieldName
                                        + "AsRoutingNumber();");
            } else {
                classWriterMain.println("    RoutingNumber " + fieldName
                                        + "();");
            }
        } else {
            if (asRoutingNumber) {
                classWriterMain.println("    public RoutingNumber " + fieldName
                                        + "AsRoutingNumber() {");
            } else {
                classWriterMain.println("    public RoutingNumber " + fieldName
                                        + "() {");
            }
            if (generatingBaseClasses) {
                classWriterMain
                        .println("        throw new InvalidStandardLevelException();");
            } else {
                classWriterMain
                        .println("        return getFieldAsRoutingNumber(field("
                                 + fieldNumber + "));");
            }
            classWriterMain.println("    }");
            classWriterMain.println();
        }
        String dataType = "RoutingNumber";
        setterMethod(interfaceName, fieldNumber, fieldName, setterPrivate, setterCode,
                dataType);
    }

    /**
     * @param fieldName
     * @param asOnUsField
     * @param setterPrivate TODO
     * @param setterCode TODO
     */
    private void onUsMethods(String interfaceName, int fieldNumber, String fieldName,
            boolean asOnUsField, boolean setterPrivate, String setterCode) {
        if (generatingInterfaces) {
            if (asOnUsField) {
                classWriterMain.println("    OnUsField " + fieldName
                                        + "AsOnUsField();");
            } else {
                classWriterMain.println("    OnUsField " + fieldName
                                        + "();");
            }
        } else {
            if (asOnUsField) {
                classWriterMain.println("    public OnUsField " + fieldName
                                        + "AsOnUsField() {");
            } else {
                classWriterMain.println("    public OnUsField " + fieldName
                                        + "() {");
            }
            if (generatingBaseClasses) {
                classWriterMain
                        .println("        throw new InvalidStandardLevelException();");
            } else {
                classWriterMain
                        .println("        return new OnUsField(getFieldAsString(field("
                                 + fieldNumber + ")));");
            }
            classWriterMain.println("    }");
            classWriterMain.println();
        }
        String dataType = "OnUsField";
        setterMethod(interfaceName, fieldNumber, fieldName, setterPrivate, setterCode,
                dataType);
    }

    /**
     * @param fieldName
     * @param asByteArray
     * @param setterPrivate TODO
     * @param setterCode TODO
     */
    private void binaryMethods(String interfaceName, int fieldNumber, String fieldName,
            boolean asByteArray, boolean setterPrivate, String setterCode) {
        if (generatingInterfaces) {
            if (asByteArray) {
                classWriterMain.println("    ByteArray " + fieldName
                                        + "AsByteArray();");
            } else {
                classWriterMain.println("    ByteArray " + fieldName
                                        + "();");
            }
        } else {
            if (asByteArray) {
                classWriterMain.println("    public ByteArray " + fieldName
                                        + "AsByteArray() {");
            } else {
                classWriterMain.println("    public ByteArray " + fieldName
                                        + "() {");
            }
            if (generatingBaseClasses) {
                classWriterMain
                        .println("        throw new InvalidStandardLevelException();");
            } else {
                classWriterMain
                        .println("        return getFieldAsByteArray(field("
                                 + fieldNumber + "));");
            }
            classWriterMain.println("    }");
            classWriterMain.println();
        }
        String dataType = "ByteArray";
        setterMethod(interfaceName, fieldNumber, fieldName, setterPrivate, setterCode,
                dataType);
    }

    private FieldType calculateFieldType(String fieldType, int fieldLength) {
        if (fieldType.equalsIgnoreCase("S")) {
            return FieldType.STRING;
        }
        if (fieldType.equalsIgnoreCase("D")) {
            return FieldType.DATE;
        }
        if (fieldType.equalsIgnoreCase("T")) {
            return FieldType.TIME;
        }
        if (fieldType.equalsIgnoreCase("B")) {
            return FieldType.BINARY;
        }
        if (fieldType.equalsIgnoreCase("R")) {
            return FieldType.ROUTING_NUMBER;
        }
        if (fieldType.equalsIgnoreCase("U")) {
            return FieldType.ONUS;
        }
        if (fieldType.equalsIgnoreCase("N")) {
            if (fieldLength <= 8) {
                return FieldType.INT;
            }
            return FieldType.LONG;
        }
        if (fieldType.equalsIgnoreCase("A")) {
            if (fieldLength <= 8) {
                return FieldType.INT;
            }
            return FieldType.LONG;
        }
        return null;
    }

    private void generateClassImpl(ClassDefinition classDef) {
        String className = classDef.getName();
        String classNameImpl = className + "Impl";
        String classNameBase = className + "Base";
        currentClassName = classNameImpl;
        log.info("Generate class: " + className);

        currentFieldMax = classDef.getClassDefinitionItem().length;

        boolean hasDynamicFields = false;
        ClassDefinitionItem[] classDefItemList = classDef
                .getClassDefinitionItem();
        for (ClassDefinitionItem classDefItem : classDefItemList) {
            ClassField classField = classDefItem.getClassField();
            if (classField.getFieldDynamic()) {
                hasDynamicFields = true;
                break;
            }
        }

        StringBuffer sb = new StringBuffer();
        String recordType = className;
        if (recordType.startsWith("X937")) {
            recordType = recordType.substring(4);
        }
        if (recordType.endsWith("Record")) {
            recordType = recordType.substring(0, recordType.length() - 6);
        }
        for (int i = 0; i < recordType.length(); i++) {
            if (Character.isUpperCase(recordType.charAt(i))) {
                if (i == 0) {
                    sb.append("TYPE_");
                } else {
                    sb.append("_");
                }
                sb.append(Character.toUpperCase(recordType.charAt(i)));
            }
        }
        recordType = sb.toString();

        classTop = new StringWriter(4096);
        classWriterTop = new PrintWriter(classTop);
        classMain = new StringWriter(8192);
        classWriterMain = new PrintWriter(classMain);

        classWriterTop.println(
                "/*******************************************************************************\n" +
                " * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved\n" +
                " *\n" +
                " * Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                " * You may not use this file except in compliance with the License.\n" +
                " * You may obtain a copy of the License at\n" +
                " *\n" +
                " *     http://www.apache.org/licenses/LICENSE-2.0\n" +
                " *\n" +
                " * Unless required by applicable law or agreed to in writing, software\n" +
                " * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                " * See the License for the specific language governing permissions and\n" +
                " * limitations under the License.\n" +
                " ******************************************************************************/");
        classWriterTop.println();
        classWriterTop.println("package " + packageName + ";");
        classWriterTop.println();

        classWriterTop.println("import java.util.Date;");
        classWriterTop.println();

        classWriterTop.println("import com.thelastcheck.commons.base.exception.InvalidDataException;");
        classWriterTop.println("import com.thelastcheck.commons.base.fields.OnUsField;");
        classWriterTop.println("import com.thelastcheck.commons.base.fields.RoutingNumber;");
        classWriterTop.println("import com.thelastcheck.commons.buffer.ByteArray;");
        classWriterTop.println("import com.thelastcheck.io.base.Field;");
        classWriterTop.println("import com.thelastcheck.io.base.FieldType;");
        classWriterTop.println("import " + packageNameInterface + "." + className + ";");
        classWriterTop.println("import " + packageNameInterface + ".base." + classNameBase + ";");

        classWriterTop.println();

        classWriterTop.println("public class " + classNameImpl + " extends "
                               + classNameBase + " {");
        classWriterTop.println();
        classWriterTop.println("    private static int maxFieldNumber = "
                               + currentFieldMax + ";");
        classWriterTop
                .println("    private static Field fields[] = new Field[maxFieldNumber+1];");
        if (hasDynamicFields) {
            classWriterTop
                    .println("    private Field localFieldCache[] = new Field[maxFieldNumber+1];");
        }
        classWriterTop.println();

        classWriterTop.println("    static {");
        classWriterTop.println("        fields[0] = null;");
        classWriterTop.println("        fields[1] = recordTypeField;");

        classWriterMain.println("    /*");
        classWriterMain.println("     * " + classNameImpl);
        classWriterMain.println("     */");
        classWriterMain.println();

        classWriterMain.println("    public " + classNameImpl + "() {");
        classWriterMain.println("        super();");
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    public " + classNameImpl
                                + "(int stdLevel) {");
        classWriterMain.println("        super(stdLevel);");
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    public " + classNameImpl
                                + "(String encoding, int stdLevel) {");
        classWriterMain.println("        super(encoding, stdLevel);");
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    public " + classNameImpl
                                + "(ByteArray record, int stdLevel) {");
        classWriterMain.println("        super(record, stdLevel);");
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    @Override");
        classWriterMain.println("    protected void resetDynamicFields() {");

        classDefItemList = classDef.getClassDefinitionItem();
        for (ClassDefinitionItem classDefItem : classDefItemList) {
            ClassField classField = classDefItem.getClassField();
            if (classField.getFieldDynamic()) {
                int i = classField.getNumber();
                classWriterMain.println("        localFieldCache[" + i
                                        + "] = null;");
            }
        }
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    @Override");
        classWriterMain.println("    public int numberOfFields() {");
        classWriterMain.println("        return maxFieldNumber;");
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    @Override");
        classWriterMain.println("    protected Field field(int fieldNumber) {");
        classWriterMain
                .println("        if (fieldNumber < 1 || fieldNumber > maxFieldNumber) {");
        classWriterMain
                .println("            throw new IllegalArgumentException(INVALID_FIELD_NUMBER);");
        classWriterMain.println("        }");

        if (hasDynamicFields) {
            classWriterMain
                    .println("        if (localFieldCache[fieldNumber] == null) {");
            classWriterMain.println("            switch (fieldNumber) {");
            classDefItemList = classDef.getClassDefinitionItem();
            for (ClassDefinitionItem classDefItem : classDefItemList) {
                ClassField classField = classDefItem.getClassField();
                if (classField.getFieldDynamic()) {
                    int i = classField.getNumber();
                    String s = classField.getName();
                    classWriterMain.println("            case " + i + ":");
                    classWriterMain.println("                localFieldCache["
                                            + i + "] = calculateField" + s + "();");
                    classWriterMain.println("                break;");
                }
            }

            classWriterMain.println("            default:");
            classWriterMain
                    .println("                localFieldCache[fieldNumber] = fields[fieldNumber];");
            classWriterMain.println("            }");
            classWriterMain.println("        }");
            classWriterMain
                    .println("        return localFieldCache[fieldNumber];");
        } else {
            classWriterMain.println("        return fields[fieldNumber];");
        }
        classWriterMain.println("    }");
        classWriterMain.println();

        if (hasDynamicFields) {
            classDefItemList = classDef.getClassDefinitionItem();
            for (ClassDefinitionItem classDefItem : classDefItemList) {
                ClassField classField = classDefItem.getClassField();
                if (classField.getFieldDynamic()) {
                    String s = classField.getName();
                    classWriterMain.println("    private Field calculateField" + s + "() {");
                    classWriterMain.println("        // following is code for dynamically building field");
                    if (classField.getClassFieldItemCount() > 0) {
                        ClassFieldItem cfi = classField.getClassFieldItem(0);
                        String fieldCode = cfi.getCalculateField();
                        if (fieldCode != null) {
                            classWriterMain.println("        " + fieldCode.trim());
                        }
                    }
                    classWriterMain.println("        // above is code for dynamically building field");
                    classWriterMain.println("    }");
                    classWriterMain.println();
                }
            }

        }

        classWriterMain.println();
    }

    private void generateClassBase(InterfaceDefinition classDef) {
        String className = classDef.getName();
        String classNameBase = className + "Base";
        currentClassName = classNameBase;
        log.info("Generate class: " + className);

        currentFieldMax = classDef.getInterfaceDefinitionItem().length;

        String recordType = classDef.getType();

        classTop = new StringWriter(4096);
        classWriterTop = new PrintWriter(classTop);
        classMain = new StringWriter(8192);
        classWriterMain = new PrintWriter(classMain);

        classWriterTop.println(
                "/*******************************************************************************\n" +
                " * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved\n" +
                " *\n" +
                " * Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                " * You may not use this file except in compliance with the License.\n" +
                " * You may obtain a copy of the License at\n" +
                " *\n" +
                " *     http://www.apache.org/licenses/LICENSE-2.0\n" +
                " *\n" +
                " * Unless required by applicable law or agreed to in writing, software\n" +
                " * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                " * See the License for the specific language governing permissions and\n" +
                " * limitations under the License.\n" +
                " ******************************************************************************/");
        classWriterTop.println();
        classWriterTop.println("package " + packageName + ";");
        classWriterTop.println();

        classWriterTop.println("import java.util.Date;");
        classWriterTop.println();

        classWriterTop.println("import " + packageNameInterface + "." + className + ";");
        classWriterTop.println();

        classWriterTop.println("import com.thelastcheck.commons.base.exception.InvalidDataException;");
        classWriterTop.println("import com.thelastcheck.commons.base.fields.OnUsField;");
        classWriterTop.println("import com.thelastcheck.commons.base.fields.RoutingNumber;");
        classWriterTop.println("import com.thelastcheck.commons.buffer.ByteArray;");
        classWriterTop.println("import com.thelastcheck.io.base.exception.InvalidStandardLevelException;");
        classWriterTop.println("import com.thelastcheck.io.x9.X9RecordImpl;");

        classWriterTop.println();

        classWriterTop.println("public abstract class " + classNameBase + " extends X9RecordImpl ");
        classWriterTop.println("        implements " + className + " {");
        classWriterTop.println();

        classWriterMain.println("    /*");
        classWriterMain.println("     * " + classNameBase);
        classWriterMain.println("     */");
        classWriterMain.println();

        classWriterMain.println("    public " + classNameBase + "() {");
        classWriterMain.println("        super();");
        classWriterMain.println("        recordType(" + recordType + ");");
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    public " + classNameBase + "(int stdLevel) {");
        classWriterMain.println("        super(" + recordType + ", stdLevel);");
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    public " + classNameBase + "(String encoding, int stdLevel) {");
        classWriterMain.println("        super(" + recordType
                                + ", encoding, stdLevel);");
        classWriterMain.println("    }");
        classWriterMain.println();

        classWriterMain.println("    public " + classNameBase + "(ByteArray record, int stdLevel) {");
        classWriterMain.println("        super(record, stdLevel);");
        classWriterMain.println("    }");
        classWriterMain.println();
    }

    private void generateClassInterface(InterfaceDefinition classDef) {
        String className = classDef.getName();
        String classNameBase = className;
        currentClassName = classNameBase;
        log.info("Generate class: " + className);

        currentFieldMax = classDef.getInterfaceDefinitionItem().length;

        classTop = new StringWriter(2048);
        classWriterTop = new PrintWriter(classTop);
        classMain = new StringWriter(8096);
        classWriterMain = new PrintWriter(classMain);

        classWriterTop.println(
                "/*******************************************************************************\n" +
                " * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved\n" +
                " *\n" +
                " * Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                " * You may not use this file except in compliance with the License.\n" +
                " * You may obtain a copy of the License at\n" +
                " *\n" +
                " *     http://www.apache.org/licenses/LICENSE-2.0\n" +
                " *\n" +
                " * Unless required by applicable law or agreed to in writing, software\n" +
                " * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                " * See the License for the specific language governing permissions and\n" +
                " * limitations under the License.\n" +
                " ******************************************************************************/");
        classWriterTop.println();
        classWriterTop.println("package " + packageName + ";");
        classWriterTop.println();

        classWriterTop.println("import java.util.Date;");
        classWriterTop.println();

        classWriterTop.println("import com.thelastcheck.commons.base.exception.InvalidDataException;");
        classWriterTop.println("import com.thelastcheck.commons.base.fields.OnUsField;");
        classWriterTop.println("import com.thelastcheck.commons.base.fields.RoutingNumber;");
        classWriterTop.println("import com.thelastcheck.commons.buffer.ByteArray;");
        classWriterTop.println("import com.thelastcheck.io.x9.X9Record;");

        classWriterTop.println();

        classWriterTop.println("public interface " + classNameBase
                               + " extends X9Record {");

        classWriterMain.println("    /*");
        classWriterMain.println("     * " + classNameBase);
        classWriterMain.println("     */");
        classWriterMain.println();
    }

    private void flushClass() {
        if (!generatingBaseClasses && !generatingInterfaces) {
            classWriterTop.println("    }");
        }
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

}
