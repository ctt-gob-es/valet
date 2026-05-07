/*
 * XML Type:  SignedPropertiesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignedPropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML SignedPropertiesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SignedPropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignedPropertiesType
{
    
    public SignedPropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNEDSIGNATUREPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignedSignatureProperties");
    private static final javax.xml.namespace.QName SIGNEDDATAOBJECTPROPERTIES$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignedDataObjectProperties");
    private static final javax.xml.namespace.QName ID$4 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets the "SignedSignatureProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedSignaturePropertiesType getSignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType)get_store().find_element_user(SIGNEDSIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SignedSignatureProperties" element
     */
    public boolean isSetSignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNEDSIGNATUREPROPERTIES$0) != 0;
        }
    }
    
    /**
     * Sets the "SignedSignatureProperties" element
     */
    public void setSignedSignatureProperties(org.etsi.uri.x01903.v13.SignedSignaturePropertiesType signedSignatureProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType)get_store().find_element_user(SIGNEDSIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType)get_store().add_element_user(SIGNEDSIGNATUREPROPERTIES$0);
            }
            target.set(signedSignatureProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "SignedSignatureProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedSignaturePropertiesType addNewSignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType)get_store().add_element_user(SIGNEDSIGNATUREPROPERTIES$0);
            return target;
        }
    }
    
    /**
     * Unsets the "SignedSignatureProperties" element
     */
    public void unsetSignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNEDSIGNATUREPROPERTIES$0, 0);
        }
    }
    
    /**
     * Gets the "SignedDataObjectProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType getSignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType)get_store().find_element_user(SIGNEDDATAOBJECTPROPERTIES$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SignedDataObjectProperties" element
     */
    public boolean isSetSignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNEDDATAOBJECTPROPERTIES$2) != 0;
        }
    }
    
    /**
     * Sets the "SignedDataObjectProperties" element
     */
    public void setSignedDataObjectProperties(org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType signedDataObjectProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType)get_store().find_element_user(SIGNEDDATAOBJECTPROPERTIES$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType)get_store().add_element_user(SIGNEDDATAOBJECTPROPERTIES$2);
            }
            target.set(signedDataObjectProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "SignedDataObjectProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType addNewSignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType)get_store().add_element_user(SIGNEDDATAOBJECTPROPERTIES$2);
            return target;
        }
    }
    
    /**
     * Unsets the "SignedDataObjectProperties" element
     */
    public void unsetSignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNEDDATAOBJECTPROPERTIES$2, 0);
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
