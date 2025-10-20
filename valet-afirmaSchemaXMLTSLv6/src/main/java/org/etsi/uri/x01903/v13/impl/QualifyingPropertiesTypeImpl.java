/*
 * XML Type:  QualifyingPropertiesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.QualifyingPropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML QualifyingPropertiesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class QualifyingPropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.QualifyingPropertiesType
{
    
    public QualifyingPropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNEDPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignedProperties");
    private static final javax.xml.namespace.QName UNSIGNEDPROPERTIES$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedProperties");
    private static final javax.xml.namespace.QName TARGET$4 = 
        new javax.xml.namespace.QName("", "Target");
    private static final javax.xml.namespace.QName ID$6 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets the "SignedProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedPropertiesType getSignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedPropertiesType)get_store().find_element_user(SIGNEDPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SignedProperties" element
     */
    public boolean isSetSignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNEDPROPERTIES$0) != 0;
        }
    }
    
    /**
     * Sets the "SignedProperties" element
     */
    public void setSignedProperties(org.etsi.uri.x01903.v13.SignedPropertiesType signedProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedPropertiesType)get_store().find_element_user(SIGNEDPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignedPropertiesType)get_store().add_element_user(SIGNEDPROPERTIES$0);
            }
            target.set(signedProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "SignedProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedPropertiesType addNewSignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedPropertiesType)get_store().add_element_user(SIGNEDPROPERTIES$0);
            return target;
        }
    }
    
    /**
     * Unsets the "SignedProperties" element
     */
    public void unsetSignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNEDPROPERTIES$0, 0);
        }
    }
    
    /**
     * Gets the "UnsignedProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedPropertiesType getUnsignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedPropertiesType)get_store().find_element_user(UNSIGNEDPROPERTIES$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "UnsignedProperties" element
     */
    public boolean isSetUnsignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(UNSIGNEDPROPERTIES$2) != 0;
        }
    }
    
    /**
     * Sets the "UnsignedProperties" element
     */
    public void setUnsignedProperties(org.etsi.uri.x01903.v13.UnsignedPropertiesType unsignedProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedPropertiesType)get_store().find_element_user(UNSIGNEDPROPERTIES$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.UnsignedPropertiesType)get_store().add_element_user(UNSIGNEDPROPERTIES$2);
            }
            target.set(unsignedProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "UnsignedProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedPropertiesType addNewUnsignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedPropertiesType)get_store().add_element_user(UNSIGNEDPROPERTIES$2);
            return target;
        }
    }
    
    /**
     * Unsets the "UnsignedProperties" element
     */
    public void unsetUnsignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(UNSIGNEDPROPERTIES$2, 0);
        }
    }
    
    /**
     * Gets the "Target" attribute
     */
    public java.lang.String getTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TARGET$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Target" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(TARGET$4);
            return target;
        }
    }
    
    /**
     * Sets the "Target" attribute
     */
    public void setTarget(java.lang.String targetValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TARGET$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TARGET$4);
            }
            target.setStringValue(targetValue);
        }
    }
    
    /**
     * Sets (as xml) the "Target" attribute
     */
    public void xsetTarget(org.apache.xmlbeans.XmlAnyURI targetValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(TARGET$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(TARGET$4);
            }
            target.set(targetValue);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$6);
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
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$6);
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
            return get_store().find_attribute_user(ID$6) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$6);
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
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$6);
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
            get_store().remove_attribute(ID$6);
        }
    }
}
