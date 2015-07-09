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
 * $Id: X937GenRulesItem.java,v 1.2 2009/12/16 23:54:12 jbowman Exp $
 */

package com.thelastcheck.x937.xml;

/**
 * Class X937GenRulesItem.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:12 $
 */
@SuppressWarnings("serial")
public class X937GenRulesItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _interfaceDefinitionList.
     */
    private com.thelastcheck.x937.xml.InterfaceDefinitionList _interfaceDefinitionList;

    /**
     * Field _classDefinitionList.
     */
    private com.thelastcheck.x937.xml.ClassDefinitionList _classDefinitionList;


      //----------------/
     //- Constructors -/
    //----------------/

    public X937GenRulesItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'classDefinitionList'.
     * 
     * @return the value of field 'ClassDefinitionList'.
     */
    public com.thelastcheck.x937.xml.ClassDefinitionList getClassDefinitionList(
    ) {
        return this._classDefinitionList;
    }

    /**
     * Returns the value of field 'interfaceDefinitionList'.
     * 
     * @return the value of field 'InterfaceDefinitionList'.
     */
    public com.thelastcheck.x937.xml.InterfaceDefinitionList getInterfaceDefinitionList(
    ) {
        return this._interfaceDefinitionList;
    }

    /**
     * Sets the value of field 'classDefinitionList'.
     * 
     * @param classDefinitionList the value of field
     * 'classDefinitionList'.
     */
    public void setClassDefinitionList(
            final com.thelastcheck.x937.xml.ClassDefinitionList classDefinitionList) {
        this._classDefinitionList = classDefinitionList;
    }

    /**
     * Sets the value of field 'interfaceDefinitionList'.
     * 
     * @param interfaceDefinitionList the value of field
     * 'interfaceDefinitionList'.
     */
    public void setInterfaceDefinitionList(
            final com.thelastcheck.x937.xml.InterfaceDefinitionList interfaceDefinitionList) {
        this._interfaceDefinitionList = interfaceDefinitionList;
    }

}
