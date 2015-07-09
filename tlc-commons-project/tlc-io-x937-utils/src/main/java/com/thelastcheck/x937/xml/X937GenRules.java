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
 * $Id: X937GenRules.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class X937GenRules.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class X937GenRules implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    private java.util.List<com.thelastcheck.x937.xml.X937GenRulesItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public X937GenRules() {
        super();
        this._items = new java.util.ArrayList<com.thelastcheck.x937.xml.X937GenRulesItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vX937GenRulesItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addX937GenRulesItem(
            final com.thelastcheck.x937.xml.X937GenRulesItem vX937GenRulesItem)
    throws IndexOutOfBoundsException {
        this._items.add(vX937GenRulesItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vX937GenRulesItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addX937GenRulesItem(
            final int index,
            final com.thelastcheck.x937.xml.X937GenRulesItem vX937GenRulesItem)
    throws IndexOutOfBoundsException {
        this._items.add(index, vX937GenRulesItem);
    }

    /**
     * Method enumerateX937GenRulesItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.thelastcheck.x937.xml.X937GenRulesItem> enumerateX937GenRulesItem(
    ) {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getX937GenRulesItem.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the com.tlc.x937.xml.X937GenRulesItem
     * at the given index
     */
    public com.thelastcheck.x937.xml.X937GenRulesItem getX937GenRulesItem(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getX937GenRulesItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (com.thelastcheck.x937.xml.X937GenRulesItem) _items.get(index);
    }

    /**
     * Method getX937GenRulesItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.thelastcheck.x937.xml.X937GenRulesItem[] getX937GenRulesItem(
    ) {
        com.thelastcheck.x937.xml.X937GenRulesItem[] array = new com.thelastcheck.x937.xml.X937GenRulesItem[0];
        return (com.thelastcheck.x937.xml.X937GenRulesItem[]) this._items.toArray(array);
    }

    /**
     * Method getX937GenRulesItemCount.
     * 
     * @return the size of this collection
     */
    public int getX937GenRulesItemCount(
    ) {
        return this._items.size();
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
     * Method iterateX937GenRulesItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.thelastcheck.x937.xml.X937GenRulesItem> iterateX937GenRulesItem(
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
    public void removeAllX937GenRulesItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeX937GenRulesItem.
     * 
     * @param vX937GenRulesItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeX937GenRulesItem(
            final com.thelastcheck.x937.xml.X937GenRulesItem vX937GenRulesItem) {
        boolean removed = _items.remove(vX937GenRulesItem);
        return removed;
    }

    /**
     * Method removeX937GenRulesItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.thelastcheck.x937.xml.X937GenRulesItem removeX937GenRulesItemAt(
            final int index) {
        Object obj = this._items.remove(index);
        return (com.thelastcheck.x937.xml.X937GenRulesItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vX937GenRulesItem
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setX937GenRulesItem(
            final int index,
            final com.thelastcheck.x937.xml.X937GenRulesItem vX937GenRulesItem)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setX937GenRulesItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vX937GenRulesItem);
    }

    /**
     * 
     * 
     * @param vX937GenRulesItemArray
     */
    public void setX937GenRulesItem(
            final com.thelastcheck.x937.xml.X937GenRulesItem[] vX937GenRulesItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vX937GenRulesItemArray.length; i++) {
                this._items.add(vX937GenRulesItemArray[i]);
        }
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled com.tlc.x937.xml.X937GenRules
     */
    public static X937GenRules unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (X937GenRules) org.exolab.castor.xml.Unmarshaller.unmarshal(X937GenRules.class, reader);
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
