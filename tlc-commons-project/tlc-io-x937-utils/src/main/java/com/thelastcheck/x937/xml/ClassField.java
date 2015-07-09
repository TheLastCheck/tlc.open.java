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
 * $Id: ClassField.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class ClassField.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class ClassField implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _number.
     */
    private int _number;

    /**
     * keeps track of state for field: _number
     */
    private boolean _has_number;

    /**
     * Field _offset.
     */
    private int _offset;

    /**
     * keeps track of state for field: _offset
     */
    private boolean _has_offset;

    /**
     * Field _length.
     */
    private int _length;

    /**
     * keeps track of state for field: _length
     */
    private boolean _has_length;

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
     * Field _fieldDynamic.
     */
    private boolean _fieldDynamic = false;

    /**
     * keeps track of state for field: _fieldDynamic
     */
    private boolean _has_fieldDynamic;

    /**
     * Field _setDynamic.
     */
    private boolean _setDynamic = false;

    /**
     * keeps track of state for field: _setDynamic
     */
    private boolean _has_setDynamic;

    /**
     * internal content storage
     */
    private String _content = "";

    /**
     * Field _items.
     */
    private java.util.List<com.thelastcheck.x937.xml.ClassFieldItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public ClassField() {
        super();
        setSetter("public");
        setContent("");
        this._items = new java.util.ArrayList<com.thelastcheck.x937.xml.ClassFieldItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vClassFieldItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addClassFieldItem(
            final com.thelastcheck.x937.xml.ClassFieldItem vClassFieldItem)
    throws IndexOutOfBoundsException {
        this._items.add(vClassFieldItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vClassFieldItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addClassFieldItem(
            final int index,
            final com.thelastcheck.x937.xml.ClassFieldItem vClassFieldItem)
    throws IndexOutOfBoundsException {
        this._items.add(index, vClassFieldItem);
    }

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
     */
    public void deleteNumber(
    ) {
        this._has_number= false;
    }

    /**
     */
    public void deleteOffset(
    ) {
        this._has_offset= false;
    }

    /**
     */
    public void deleteSetDynamic(
    ) {
        this._has_setDynamic= false;
    }

    /**
     * Method enumerateClassFieldItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.thelastcheck.x937.xml.ClassFieldItem> enumerateClassFieldItem(
    ) {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getClassFieldItem.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the com.tlc.x937.xml.ClassFieldItem at
     * the given index
     */
    public com.thelastcheck.x937.xml.ClassFieldItem getClassFieldItem(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getClassFieldItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (com.thelastcheck.x937.xml.ClassFieldItem) _items.get(index);
    }

    /**
     * Method getClassFieldItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.thelastcheck.x937.xml.ClassFieldItem[] getClassFieldItem(
    ) {
        com.thelastcheck.x937.xml.ClassFieldItem[] array = new com.thelastcheck.x937.xml.ClassFieldItem[0];
        return (com.thelastcheck.x937.xml.ClassFieldItem[]) this._items.toArray(array);
    }

    /**
     * Method getClassFieldItemCount.
     * 
     * @return the size of this collection
     */
    public int getClassFieldItemCount(
    ) {
        return this._items.size();
    }

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public String getContent(
    ) {
        return this._content;
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
     * Returns the value of field 'number'.
     * 
     * @return the value of field 'Number'.
     */
    public int getNumber(
    ) {
        return this._number;
    }

    /**
     * Returns the value of field 'offset'.
     * 
     * @return the value of field 'Offset'.
     */
    public int getOffset(
    ) {
        return this._offset;
    }

    /**
     * Returns the value of field 'setDynamic'.
     * 
     * @return the value of field 'SetDynamic'.
     */
    public boolean getSetDynamic(
    ) {
        return this._setDynamic;
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
     * Method hasNumber.
     * 
     * @return true if at least one Number has been added
     */
    public boolean hasNumber(
    ) {
        return this._has_number;
    }

    /**
     * Method hasOffset.
     * 
     * @return true if at least one Offset has been added
     */
    public boolean hasOffset(
    ) {
        return this._has_offset;
    }

    /**
     * Method hasSetDynamic.
     * 
     * @return true if at least one SetDynamic has been added
     */
    public boolean hasSetDynamic(
    ) {
        return this._has_setDynamic;
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
     * Returns the value of field 'setDynamic'.
     * 
     * @return the value of field 'SetDynamic'.
     */
    public boolean isSetDynamic(
    ) {
        return this._setDynamic;
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
     * Method iterateClassFieldItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.thelastcheck.x937.xml.ClassFieldItem> iterateClassFieldItem(
    ) {
        return this._items.iterator();
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
     */
    public void removeAllClassFieldItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeClassFieldItem.
     * 
     * @param vClassFieldItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeClassFieldItem(
            final com.thelastcheck.x937.xml.ClassFieldItem vClassFieldItem) {
        boolean removed = _items.remove(vClassFieldItem);
        return removed;
    }

    /**
     * Method removeClassFieldItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.thelastcheck.x937.xml.ClassFieldItem removeClassFieldItemAt(
            final int index) {
        Object obj = this._items.remove(index);
        return (com.thelastcheck.x937.xml.ClassFieldItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vClassFieldItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setClassFieldItem(
            final int index,
            final com.thelastcheck.x937.xml.ClassFieldItem vClassFieldItem)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setClassFieldItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vClassFieldItem);
    }

    /**
     * 
     * 
     * @param vClassFieldItemArray
     */
    public void setClassFieldItem(
            final com.thelastcheck.x937.xml.ClassFieldItem[] vClassFieldItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vClassFieldItemArray.length; i++) {
                this._items.add(vClassFieldItemArray[i]);
        }
    }

    /**
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final String content) {
        this._content = content;
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
     * Sets the value of field 'number'.
     * 
     * @param number the value of field 'number'.
     */
    public void setNumber(
            final int number) {
        this._number = number;
        this._has_number = true;
    }

    /**
     * Sets the value of field 'offset'.
     * 
     * @param offset the value of field 'offset'.
     */
    public void setOffset(
            final int offset) {
        this._offset = offset;
        this._has_offset = true;
    }

    /**
     * Sets the value of field 'setDynamic'.
     * 
     * @param setDynamic the value of field 'setDynamic'.
     */
    public void setSetDynamic(
            final boolean setDynamic) {
        this._setDynamic = setDynamic;
        this._has_setDynamic = true;
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
     * @return the unmarshaled com.tlc.x937.xml.ClassField
     */
    public static ClassField unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (ClassField) org.exolab.castor.xml.Unmarshaller.unmarshal(ClassField.class, reader);
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
