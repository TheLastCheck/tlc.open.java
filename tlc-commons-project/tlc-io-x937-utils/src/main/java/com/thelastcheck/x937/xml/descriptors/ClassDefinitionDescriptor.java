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
 * $Id: ClassDefinitionDescriptor.java,v 1.2 2009/12/16 23:54:13 jbowman Exp $
 */

package com.thelastcheck.x937.xml.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import com.thelastcheck.x937.xml.ClassDefinition;

/**
 * Class ClassDefinitionDescriptor.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/12/16 23:54:13 $
 */
public class ClassDefinitionDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _elementDefinition.
     */
    private boolean _elementDefinition;

    /**
     * Field _nsPrefix.
     */
    private String _nsPrefix;

    /**
     * Field _nsURI.
     */
    private String _nsURI;

    /**
     * Field _xmlName.
     */
    private String _xmlName;

    /**
     * Field _identity.
     */
    private org.exolab.castor.xml.XMLFieldDescriptor _identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public ClassDefinitionDescriptor() {
        super();
        _xmlName = "classDefinition";
        _elementDefinition = false;
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors

        //-- _recordType
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(Integer.TYPE, "_recordType", "recordType", org.exolab.castor.xml.NodeType.Attribute);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public Object getValue( Object object )
                throws IllegalStateException
            {
                ClassDefinition target = (ClassDefinition) object;
                if (!target.hasRecordType()) { return null; }
                return new Integer(target.getRecordType());
            }
            @Override
            public void setValue( Object object, Object value)
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ClassDefinition target = (ClassDefinition) object;
                    // ignore null values for non optional primitives
                    if (value == null) { return; }

                    target.setRecordType( ((Integer) value).intValue());
                } catch (Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public Object newInstance(Object parent) {
                return null;
            }
        };
        desc.setSchemaType("int");
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);

        //-- validation code for: _recordType
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            org.exolab.castor.xml.validators.IntValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.IntValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setMinInclusive(-2147483648);
            typeValidator.setMaxInclusive(2147483647);
        }
        desc.setValidator(fieldValidator);
        //-- _name
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(String.class, "_name", "name", org.exolab.castor.xml.NodeType.Attribute);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public Object getValue( Object object )
                throws IllegalStateException
            {
                ClassDefinition target = (ClassDefinition) object;
                return target.getName();
            }
            @Override
            public void setValue( Object object, Object value)
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ClassDefinition target = (ClassDefinition) object;
                    target.setName( (String) value);
                } catch (Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public Object newInstance(Object parent) {
                return null;
            }
        };
        desc.setSchemaType("string");
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);

        //-- validation code for: _name
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setWhiteSpace("preserve");
        }
        desc.setValidator(fieldValidator);
        //-- initialize element descriptors

        //-- _items
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.thelastcheck.x937.xml.ClassDefinitionItem.class, "_items", (String) null, org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public Object getValue( Object object )
                throws IllegalStateException
            {
                ClassDefinition target = (ClassDefinition) object;
                return target.getClassDefinitionItem();
            }
            @Override
            public void setValue( Object object, Object value)
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ClassDefinition target = (ClassDefinition) object;
                    target.addClassDefinitionItem( (com.thelastcheck.x937.xml.ClassDefinitionItem) value);
                } catch (Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    ClassDefinition target = (ClassDefinition) object;
                    target.removeAllClassDefinitionItem();
                } catch (Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public Object newInstance(Object parent) {
                return new com.thelastcheck.x937.xml.ClassDefinitionItem();
            }
        };
        desc.setSchemaType("list");
        desc.setComponentType("com.tlc.x937.xml.ClassDefinitionItem");
        desc.setHandler(handler);
        desc.setContainer(true);
        desc.setClassDescriptor(new com.thelastcheck.x937.xml.descriptors.ClassDefinitionItemDescriptor());
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _items
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode.
     * 
     * @return the access mode specified for this class.
     */
    @Override()
    public org.exolab.castor.mapping.AccessMode getAccessMode(
    ) {
        return null;
    }

    /**
     * Method getIdentity.
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    @Override()
    public org.exolab.castor.mapping.FieldDescriptor getIdentity(
    ) {
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    @Override()
    public Class getJavaClass(
    ) {
        return com.thelastcheck.x937.xml.ClassDefinition.class;
    }

    /**
     * Method getNameSpacePrefix.
     * 
     * @return the namespace prefix to use when marshaling as XML.
     */
    @Override()
    public String getNameSpacePrefix(
    ) {
        return _nsPrefix;
    }

    /**
     * Method getNameSpaceURI.
     * 
     * @return the namespace URI used when marshaling and
     * unmarshaling as XML.
     */
    @Override()
    public String getNameSpaceURI(
    ) {
        return _nsURI;
    }

    /**
     * Method getValidator.
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    @Override()
    public org.exolab.castor.xml.TypeValidator getValidator(
    ) {
        return this;
    }

    /**
     * Method getXMLName.
     * 
     * @return the XML Name for the Class being described.
     */
    @Override()
    public String getXMLName(
    ) {
        return _xmlName;
    }

    /**
     * Method isElementDefinition.
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition(
    ) {
        return _elementDefinition;
    }

}
