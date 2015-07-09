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
 * $Id: ClassFieldItem.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class ClassFieldItem.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class ClassFieldItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _calculateField.
     */
    private String _calculateField;

    /**
     * Field _setField.
     */
    private String _setField;


      //----------------/
     //- Constructors -/
    //----------------/

    public ClassFieldItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'calculateField'.
     * 
     * @return the value of field 'CalculateField'.
     */
    public String getCalculateField(
    ) {
        return this._calculateField;
    }

    /**
     * Returns the value of field 'setField'.
     * 
     * @return the value of field 'SetField'.
     */
    public String getSetField(
    ) {
        return this._setField;
    }

    /**
     * Sets the value of field 'calculateField'.
     * 
     * @param calculateField the value of field 'calculateField'.
     */
    public void setCalculateField(
            final String calculateField) {
        this._calculateField = calculateField;
    }

    /**
     * Sets the value of field 'setField'.
     * 
     * @param setField the value of field 'setField'.
     */
    public void setSetField(
            final String setField) {
        this._setField = setField;
    }

}
