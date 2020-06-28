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
 * $Id: InterfaceDefinitionList.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class InterfaceDefinitionList.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class InterfaceDefinitionList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _package.
     */
    private String _package;

    /**
     * Field _items.
     */
    private java.util.List<com.thelastcheck.x937.xml.InterfaceDefinitionListItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public InterfaceDefinitionList() {
        super();
        this._items = new java.util.ArrayList<com.thelastcheck.x937.xml.InterfaceDefinitionListItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vInterfaceDefinitionListItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addInterfaceDefinitionListItem(
            final com.thelastcheck.x937.xml.InterfaceDefinitionListItem vInterfaceDefinitionListItem)
    throws IndexOutOfBoundsException {
        this._items.add(vInterfaceDefinitionListItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vInterfaceDefinitionListItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addInterfaceDefinitionListItem(
            final int index,
            final com.thelastcheck.x937.xml.InterfaceDefinitionListItem vInterfaceDefinitionListItem)
    throws IndexOutOfBoundsException {
        this._items.add(index, vInterfaceDefinitionListItem);
    }

    /**
     * Method enumerateInterfaceDefinitionListItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.thelastcheck.x937.xml.InterfaceDefinitionListItem> enumerateInterfaceDefinitionListItem(
    ) {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getInterfaceDefinitionListItem.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.tlc.x937.xml.InterfaceDefinitionListItem at the given
     * index
     */
    public com.thelastcheck.x937.xml.InterfaceDefinitionListItem getInterfaceDefinitionListItem(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getInterfaceDefinitionListItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (com.thelastcheck.x937.xml.InterfaceDefinitionListItem) _items.get(index);
    }

    /**
     * Method getInterfaceDefinitionListItem.Returns the contents
     * of the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.thelastcheck.x937.xml.InterfaceDefinitionListItem[] getInterfaceDefinitionListItem(
    ) {
        com.thelastcheck.x937.xml.InterfaceDefinitionListItem[] array = new com.thelastcheck.x937.xml.InterfaceDefinitionListItem[0];
        return (com.thelastcheck.x937.xml.InterfaceDefinitionListItem[]) this._items.toArray(array);
    }

    /**
     * Method getInterfaceDefinitionListItemCount.
     * 
     * @return the size of this collection
     */
    public int getInterfaceDefinitionListItemCount(
    ) {
        return this._items.size();
    }

    /**
     * Returns the value of field 'package'.
     * 
     * @return the value of field 'Package'.
     */
    public String getPackage(
    ) {
        return this._package;
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
     * Method iterateInterfaceDefinitionListItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.thelastcheck.x937.xml.InterfaceDefinitionListItem> iterateInterfaceDefinitionListItem(
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
    public void removeAllInterfaceDefinitionListItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeInterfaceDefinitionListItem.
     * 
     * @param vInterfaceDefinitionListItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeInterfaceDefinitionListItem(
            final com.thelastcheck.x937.xml.InterfaceDefinitionListItem vInterfaceDefinitionListItem) {
        boolean removed = _items.remove(vInterfaceDefinitionListItem);
        return removed;
    }

    /**
     * Method removeInterfaceDefinitionListItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.thelastcheck.x937.xml.InterfaceDefinitionListItem removeInterfaceDefinitionListItemAt(
            final int index) {
        Object obj = this._items.remove(index);
        return (com.thelastcheck.x937.xml.InterfaceDefinitionListItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vInterfaceDefinitionListItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setInterfaceDefinitionListItem(
            final int index,
            final com.thelastcheck.x937.xml.InterfaceDefinitionListItem vInterfaceDefinitionListItem)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setInterfaceDefinitionListItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vInterfaceDefinitionListItem);
    }

    /**
     * 
     * 
     * @param vInterfaceDefinitionListItemArray
     */
    public void setInterfaceDefinitionListItem(
            final com.thelastcheck.x937.xml.InterfaceDefinitionListItem[] vInterfaceDefinitionListItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vInterfaceDefinitionListItemArray.length; i++) {
                this._items.add(vInterfaceDefinitionListItemArray[i]);
        }
    }

    /**
     * Sets the value of field 'package'.
     * 
     * @param _package the value of field 'package'.
     */
    public void setPackage(
            final String _package) {
        this._package = _package;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.tlc.x937.xml.InterfaceDefinitionList
     */
    public static InterfaceDefinitionList unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (InterfaceDefinitionList) org.exolab.castor.xml.Unmarshaller.unmarshal(InterfaceDefinitionList.class, reader);
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
