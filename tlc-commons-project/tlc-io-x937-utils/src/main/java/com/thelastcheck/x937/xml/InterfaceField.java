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

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3</a>, using an XML
 * Schema.
 * $Id: InterfaceField.java,v 1.2 2009/12/16 23:54:13 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class InterfaceField.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:13 $
 */
@SuppressWarnings("serial")
public class InterfaceField implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _type.
     */
    private String _type;

    /**
     * Field _name.
     */
    private String _name;

    /**
     * Field _setter.
     */
    private String _setter = "public";

    /**
     * Field _length.
     */
    private int _length = 0;

    /**
     * keeps track of state for field: _length
     */
    private boolean _has_length;

    /**
     * Field _fieldDynamic.
     */
    private boolean _fieldDynamic = false;

    /**
     * keeps track of state for field: _fieldDynamic
     */
    private boolean _has_fieldDynamic;


      //----------------/
     //- Constructors -/
    //----------------/

    public InterfaceField() {
        super();
        setSetter("public");
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteFieldDynamic(
    ) {
        this._has_fieldDynamic= false;
    }

    /**
     */
    public void deleteLength(
    ) {
        this._has_length= false;
    }

    /**
     * Returns the value of field 'fieldDynamic'.
     * 
     * @return the value of field 'FieldDynamic'.
     */
    public boolean getFieldDynamic(
    ) {
        return this._fieldDynamic;
    }

    /**
     * Returns the value of field 'length'.
     * 
     * @return the value of field 'Length'.
     */
    public int getLength(
    ) {
        return this._length;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'setter'.
     * 
     * @return the value of field 'Setter'.
     */
    public String getSetter(
    ) {
        return this._setter;
    }

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public String getType(
    ) {
        return this._type;
    }

    /**
     * Method hasFieldDynamic.
     * 
     * @return true if at least one FieldDynamic has been added
     */
    public boolean hasFieldDynamic(
    ) {
        return this._has_fieldDynamic;
    }

    /**
     * Method hasLength.
     * 
     * @return true if at least one Length has been added
     */
    public boolean hasLength(
    ) {
        return this._has_length;
    }

    /**
     * Returns the value of field 'fieldDynamic'.
     * 
     * @return the value of field 'FieldDynamic'.
     */
    public boolean isFieldDynamic(
    ) {
        return this._fieldDynamic;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'fieldDynamic'.
     * 
     * @param fieldDynamic the value of field 'fieldDynamic'.
     */
    public void setFieldDynamic(
            final boolean fieldDynamic) {
        this._fieldDynamic = fieldDynamic;
        this._has_fieldDynamic = true;
    }

    /**
     * Sets the value of field 'length'.
     * 
     * @param length the value of field 'length'.
     */
    public void setLength(
            final int length) {
        this._length = length;
        this._has_length = true;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'setter'.
     * 
     * @param setter the value of field 'setter'.
     */
    public void setSetter(
            final String setter) {
        this._setter = setter;
    }

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(
            final String type) {
        this._type = type;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled com.tlc.x937.xml.InterfaceField
     */
    public static InterfaceField unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (InterfaceField) org.exolab.castor.xml.Unmarshaller.unmarshal(InterfaceField.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
