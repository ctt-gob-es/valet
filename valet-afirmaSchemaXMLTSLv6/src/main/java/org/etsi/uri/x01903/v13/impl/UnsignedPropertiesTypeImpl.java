/*
 * XML Type:  UnsignedPropertiesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.UnsignedPropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML UnsignedPropertiesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class UnsignedPropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.UnsignedPropertiesType
{
    
    public UnsignedPropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName UNSIGNEDSIGNATUREPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedSignatureProperties");
    private static final javax.xml.namespace.QName UNSIGNEDDATAOBJECTPROPERTIES$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedDataObjectProperties");
    private static final javax.xml.namespace.QName ID$4 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets the "UnsignedSignatureProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType getUnsignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType)get_store().find_element_user(UNSIGNEDSIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "UnsignedSignatureProperties" element
     */
    public boolean isSetUnsignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(UNSIGNEDSIGNATUREPROPERTIES$0) != 0;
        }
    }
    
    /**
     * Sets the "UnsignedSignatureProperties" element
     */
    public void setUnsignedSignatureProperties(org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType unsignedSignatureProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType)get_store().find_element_user(UNSIGNEDSIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType)get_store().add_element_user(UNSIGNEDSIGNATUREPROPERTIES$0);
            }
            target.set(unsignedSignatureProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "UnsignedSignatureProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType addNewUnsignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType)get_store().add_element_user(UNSIGNEDSIGNATUREPROPERTIES$0);
            return target;
        }
    }
    
    /**
     * Unsets the "UnsignedSignatureProperties" element
     */
    public void unsetUnsignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(UNSIGNEDSIGNATUREPROPERTIES$0, 0);
        }
    }
    
    /**
     * Gets the "UnsignedDataObjectProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType getUnsignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType)get_store().find_element_user(UNSIGNEDDATAOBJECTPROPERTIES$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "UnsignedDataObjectProperties" element
     */
    public boolean isSetUnsignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(UNSIGNEDDATAOBJECTPROPERTIES$2) != 0;
        }
    }
    
    /**
     * Sets the "UnsignedDataObjectProperties" element
     */
    public void setUnsignedDataObjectProperties(org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType unsignedDataObjectProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType)get_store().find_element_user(UNSIGNEDDATAOBJECTPROPERTIES$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType)get_store().add_element_user(UNSIGNEDDATAOBJECTPROPERTIES$2);
            }
            target.set(unsignedDataObjectProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "UnsignedDataObjectProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType addNewUnsignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType)get_store().add_element_user(UNSIGNEDDATAOBJECTPROPERTIES$2);
            return target;
        }
    }
    
    /**
     * Unsets the "UnsignedDataObjectProperties" element
     */
    public void unsetUnsignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(UNSIGNEDDATAOBJECTPROPERTIES$2, 0);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$4);
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
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$4);
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
            return get_store().find_attribute_user(ID$4) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$4);
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
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$4);
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
            get_store().remove_attribute(ID$4);
        }
    }
}
