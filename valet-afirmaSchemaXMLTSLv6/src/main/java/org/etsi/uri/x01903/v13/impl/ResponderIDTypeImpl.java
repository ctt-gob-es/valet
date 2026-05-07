/*
 * XML Type:  ResponderIDType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.ResponderIDType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML ResponderIDType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class ResponderIDTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.ResponderIDType
{
    
    public ResponderIDTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName BYNAME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ByName");
    private static final javax.xml.namespace.QName BYKEY$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ByKey");
    
    
    /**
     * Gets the "ByName" element
     */
    public java.lang.String getByName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BYNAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ByName" element
     */
    public org.apache.xmlbeans.XmlString xgetByName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(BYNAME$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "ByName" element
     */
    public boolean isSetByName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BYNAME$0) != 0;
        }
    }
    
    /**
     * Sets the "ByName" element
     */
    public void setByName(java.lang.String byName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BYNAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BYNAME$0);
            }
            target.setStringValue(byName);
        }
    }
    
    /**
     * Sets (as xml) the "ByName" element
     */
    public void xsetByName(org.apache.xmlbeans.XmlString byName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(BYNAME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(BYNAME$0);
            }
            target.set(byName);
        }
    }
    
    /**
     * Unsets the "ByName" element
     */
    public void unsetByName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BYNAME$0, 0);
        }
    }
    
    /**
     * Gets the "ByKey" element
     */
    public byte[] getByKey()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BYKEY$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "ByKey" element
     */
    public org.apache.xmlbeans.XmlBase64Binary xgetByKey()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(BYKEY$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "ByKey" element
     */
    public boolean isSetByKey()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(BYKEY$2) != 0;
        }
    }
    
    /**
     * Sets the "ByKey" element
     */
    public void setByKey(byte[] byKey)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BYKEY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BYKEY$2);
            }
            target.setByteArrayValue(byKey);
        }
    }
    
    /**
     * Sets (as xml) the "ByKey" element
     */
    public void xsetByKey(org.apache.xmlbeans.XmlBase64Binary byKey)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(BYKEY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBase64Binary)get_store().add_element_user(BYKEY$2);
            }
            target.set(byKey);
        }
    }
    
    /**
     * Unsets the "ByKey" element
     */
    public void unsetByKey()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(BYKEY$2, 0);
        }
    }
}
