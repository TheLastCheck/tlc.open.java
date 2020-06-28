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
 * $Id: ClassDefinitionList.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class ClassDefinitionList.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class ClassDefinitionList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _standard.
     */
    private String _standard;

    /**
     * Field _package.
     */
    private String _package;

    /**
     * Field _items.
     */
    private java.util.List<com.thelastcheck.x937.xml.ClassDefinitionListItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public ClassDefinitionList() {
        super();
        this._items = new java.util.ArrayList<com.thelastcheck.x937.xml.ClassDefinitionListItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vClassDefinitionListItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addClassDefinitionListItem(
            final com.thelastcheck.x937.xml.ClassDefinitionListItem vClassDefinitionListItem)
    throws IndexOutOfBoundsException {
        this._items.add(vClassDefinitionListItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vClassDefinitionListItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addClassDefinitionListItem(
            final int index,
            final com.thelastcheck.x937.xml.ClassDefinitionListItem vClassDefinitionListItem)
    throws IndexOutOfBoundsException {
        this._items.add(index, vClassDefinitionListItem);
    }

    /**
     * Method enumerateClassDefinitionListItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.thelastcheck.x937.xml.ClassDefinitionListItem> enumerateClassDefinitionListItem(
    ) {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getClassDefinitionListItem.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.tlc.x937.xml.ClassDefinitionListItem at the given index
     */
    public com.thelastcheck.x937.xml.ClassDefinitionListItem getClassDefinitionListItem(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getClassDefinitionListItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (com.thelastcheck.x937.xml.ClassDefinitionListItem) _items.get(index);
    }

    /**
     * Method getClassDefinitionListItem.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.thelastcheck.x937.xml.ClassDefinitionListItem[] getClassDefinitionListItem(
    ) {
        com.thelastcheck.x937.xml.ClassDefinitionListItem[] array = new com.thelastcheck.x937.xml.ClassDefinitionListItem[0];
        return (com.thelastcheck.x937.xml.ClassDefinitionListItem[]) this._items.toArray(array);
    }

    /**
     * Method getClassDefinitionListItemCount.
     * 
     * @return the size of this collection
     */
    public int getClassDefinitionListItemCount(
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
     * Returns the value of field 'standard'.
     * 
     * @return the value of field 'Standard'.
     */
    public String getStandard(
    ) {
        return this._standard;
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
     * Method iterateClassDefinitionListItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.thelastcheck.x937.xml.ClassDefinitionListItem> iterateClassDefinitionListItem(
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
    public void removeAllClassDefinitionListItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeClassDefinitionListItem.
     * 
     * @param vClassDefinitionListItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeClassDefinitionListItem(
            final com.thelastcheck.x937.xml.ClassDefinitionListItem vClassDefinitionListItem) {
        boolean removed = _items.remove(vClassDefinitionListItem);
        return removed;
    }

    /**
     * Method removeClassDefinitionListItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.thelastcheck.x937.xml.ClassDefinitionListItem removeClassDefinitionListItemAt(
            final int index) {
        Object obj = this._items.remove(index);
        return (com.thelastcheck.x937.xml.ClassDefinitionListItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vClassDefinitionListItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setClassDefinitionListItem(
            final int index,
            final com.thelastcheck.x937.xml.ClassDefinitionListItem vClassDefinitionListItem)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setClassDefinitionListItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vClassDefinitionListItem);
    }

    /**
     * 
     * 
     * @param vClassDefinitionListItemArray
     */
    public void setClassDefinitionListItem(
            final com.thelastcheck.x937.xml.ClassDefinitionListItem[] vClassDefinitionListItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vClassDefinitionListItemArray.length; i++) {
                this._items.add(vClassDefinitionListItemArray[i]);
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
     * Sets the value of field 'standard'.
     * 
     * @param standard the value of field 'standard'.
     */
    public void setStandard(
            final String standard) {
        this._standard = standard;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled com.tlc.x937.xml.ClassDefinitionList
     */
    public static ClassDefinitionList unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (ClassDefinitionList) org.exolab.castor.xml.Unmarshaller.unmarshal(ClassDefinitionList.class, reader);
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
