/*
 * XML Type:  UnsignedDataObjectPropertiesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML UnsignedDataObjectPropertiesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class UnsignedDataObjectPropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType
{
    
    public UnsignedDataObjectPropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName UNSIGNEDDATAOBJECTPROPERTY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedDataObjectProperty");
    private static final javax.xml.namespace.QName ID$2 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets array of all "UnsignedDataObjectProperty" elements
     */
    public org.etsi.uri.x01903.v13.AnyType[] getUnsignedDataObjectPropertyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(UNSIGNEDDATAOBJECTPROPERTY$0, targetList);
            org.etsi.uri.x01903.v13.AnyType[] result = new org.etsi.uri.x01903.v13.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "UnsignedDataObjectProperty" element
     */
    public org.etsi.uri.x01903.v13.AnyType getUnsignedDataObjectPropertyArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(UNSIGNEDDATAOBJECTPROPERTY$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "UnsignedDataObjectProperty" element
     */
    public int sizeOfUnsignedDataObjectPropertyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(UNSIGNEDDATAOBJECTPROPERTY$0);
        }
    }
    
    /**
     * Sets array of all "UnsignedDataObjectProperty" element
     */
    public void setUnsignedDataObjectPropertyArray(org.etsi.uri.x01903.v13.AnyType[] unsignedDataObjectPropertyArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(unsignedDataObjectPropertyArray, UNSIGNEDDATAOBJECTPROPERTY$0);
        }
    }
    
    /**
     * Sets ith "UnsignedDataObjectProperty" element
     */
    public void setUnsignedDataObjectPropertyArray(int i, org.etsi.uri.x01903.v13.AnyType unsignedDataObjectProperty)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(UNSIGNEDDATAOBJECTPROPERTY$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(unsignedDataObjectProperty);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "UnsignedDataObjectProperty" element
     */
    public org.etsi.uri.x01903.v13.AnyType insertNewUnsignedDataObjectProperty(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().insert_element_user(UNSIGNEDDATAOBJECTPROPERTY$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "UnsignedDataObjectProperty" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewUnsignedDataObjectProperty()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(UNSIGNEDDATAOBJECTPROPERTY$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "UnsignedDataObjectProperty" element
     */
    public void removeUnsignedDataObjectProperty(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(UNSIGNEDDATAOBJECTPROPERTY$0, i);
        }
    }
    
    /**
     * Gets the "Id" attribute
     */
    public java.lang.String getId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Id" attribute
     */
    public org.apache.xmlbeans.XmlID xgetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$2);
            return target;
        }
    }
    
    /**
     * True if has "Id" attribute
     */
    public boolean isSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ID$2) != null;
        }
    }
    
    /**
     * Sets the "Id" attribute
     */
    public void setId(java.lang.String id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$2);
            }
            target.setStringValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "Id" attribute
     */
    public void xsetId(org.apache.xmlbeans.XmlID id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$2);
            }
            target.set(id);
        }
    }
    
    /**
     * Unsets the "Id" attribute
     */
    public void unsetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ID$2);
        }
    }
}
