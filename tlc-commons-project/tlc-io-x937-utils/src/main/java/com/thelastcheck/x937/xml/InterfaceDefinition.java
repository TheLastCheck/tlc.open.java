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
 * $Id: InterfaceDefinition.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class InterfaceDefinition.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class InterfaceDefinition implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _recordType.
     */
    private String _recordType;

    /**
     * Field _type.
     */
    private String _type;

    /**
     * Field _name.
     */
    private String _name;

    /**
     * Field _items.
     */
    private java.util.List<com.thelastcheck.x937.xml.InterfaceDefinitionItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public InterfaceDefinition() {
        super();
        this._items = new java.util.ArrayList<com.thelastcheck.x937.xml.InterfaceDefinitionItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vInterfaceDefinitionItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addInterfaceDefinitionItem(
            final com.thelastcheck.x937.xml.InterfaceDefinitionItem vInterfaceDefinitionItem)
    throws IndexOutOfBoundsException {
        this._items.add(vInterfaceDefinitionItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vInterfaceDefinitionItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addInterfaceDefinitionItem(
            final int index,
            final com.thelastcheck.x937.xml.InterfaceDefinitionItem vInterfaceDefinitionItem)
    throws IndexOutOfBoundsException {
        this._items.add(index, vInterfaceDefinitionItem);
    }

    /**
     * Method enumerateInterfaceDefinitionItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.thelastcheck.x937.xml.InterfaceDefinitionItem> enumerateInterfaceDefinitionItem(
    ) {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getInterfaceDefinitionItem.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.tlc.x937.xml.InterfaceDefinitionItem at the given index
     */
    public com.thelastcheck.x937.xml.InterfaceDefinitionItem getInterfaceDefinitionItem(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getInterfaceDefinitionItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (com.thelastcheck.x937.xml.InterfaceDefinitionItem) _items.get(index);
    }

    /**
     * Method getInterfaceDefinitionItem.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.thelastcheck.x937.xml.InterfaceDefinitionItem[] getInterfaceDefinitionItem(
    ) {
        com.thelastcheck.x937.xml.InterfaceDefinitionItem[] array = new com.thelastcheck.x937.xml.InterfaceDefinitionItem[0];
        return (com.thelastcheck.x937.xml.InterfaceDefinitionItem[]) this._items.toArray(array);
    }

    /**
     * Method getInterfaceDefinitionItemCount.
     * 
     * @return the size of this collection
     */
    public int getInterfaceDefinitionItemCount(
    ) {
        return this._items.size();
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
     * Returns the value of field 'recordType'.
     * 
     * @return the value of field 'RecordType'.
     */
    public String getRecordType(
    ) {
        return this._recordType;
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
     * Method iterateInterfaceDefinitionItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.thelastcheck.x937.xml.InterfaceDefinitionItem> iterateInterfaceDefinitionItem(
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
    public void removeAllInterfaceDefinitionItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeInterfaceDefinitionItem.
     * 
     * @param vInterfaceDefinitionItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeInterfaceDefinitionItem(
            final com.thelastcheck.x937.xml.InterfaceDefinitionItem vInterfaceDefinitionItem) {
        boolean removed = _items.remove(vInterfaceDefinitionItem);
        return removed;
    }

    /**
     * Method removeInterfaceDefinitionItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.thelastcheck.x937.xml.InterfaceDefinitionItem removeInterfaceDefinitionItemAt(
            final int index) {
        Object obj = this._items.remove(index);
        return (com.thelastcheck.x937.xml.InterfaceDefinitionItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vInterfaceDefinitionItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setInterfaceDefinitionItem(
            final int index,
            final com.thelastcheck.x937.xml.InterfaceDefinitionItem vInterfaceDefinitionItem)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setInterfaceDefinitionItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vInterfaceDefinitionItem);
    }

    /**
     * 
     * 
     * @param vInterfaceDefinitionItemArray
     */
    public void setInterfaceDefinitionItem(
            final com.thelastcheck.x937.xml.InterfaceDefinitionItem[] vInterfaceDefinitionItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vInterfaceDefinitionItemArray.length; i++) {
                this._items.add(vInterfaceDefinitionItemArray[i]);
        }
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
     * Sets the value of field 'recordType'.
     * 
     * @param recordType the value of field 'recordType'.
     */
    public void setRecordType(
            final String recordType) {
        this._recordType = recordType;
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
     * @return the unmarshaled com.tlc.x937.xml.InterfaceDefinition
     */
    public static InterfaceDefinition unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (InterfaceDefinition) org.exolab.castor.xml.Unmarshaller.unmarshal(InterfaceDefinition.class, reader);
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
