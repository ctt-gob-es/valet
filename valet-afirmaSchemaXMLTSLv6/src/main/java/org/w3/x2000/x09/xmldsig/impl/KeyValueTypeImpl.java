/*
 * XML Type:  KeyValueType
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.KeyValueType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * An XML KeyValueType(@http://www.w3.org/2000/09/xmldsig#).
 *
 * This is a complex type.
 */
public class KeyValueTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.KeyValueType
{
    
    public KeyValueTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DSAKEYVALUE$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "DSAKeyValue");
    private static final javax.xml.namespace.QName RSAKEYVALUE$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "RSAKeyValue");
    
    
    /**
     * Gets the "DSAKeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.DSAKeyValueType getDSAKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.DSAKeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.DSAKeyValueType)get_store().find_element_user(DSAKEYVALUE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "DSAKeyValue" element
     */
    public boolean isSetDSAKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DSAKEYVALUE$0) != 0;
        }
    }
    
    /**
     * Sets the "DSAKeyValue" element
     */
    public void setDSAKeyValue(org.w3.x2000.x09.xmldsig.DSAKeyValueType dsaKeyValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.DSAKeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.DSAKeyValueType)get_store().find_element_user(DSAKEYVALUE$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.DSAKeyValueType)get_store().add_element_user(DSAKEYVALUE$0);
            }
            target.set(dsaKeyValue);
        }
    }
    
    /**
     * Appends and returns a new empty "DSAKeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.DSAKeyValueType addNewDSAKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.DSAKeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.DSAKeyValueType)get_store().add_element_user(DSAKEYVALUE$0);
            return target;
        }
    }
    
    /**
     * Unsets the "DSAKeyValue" element
     */
    public void unsetDSAKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DSAKEYVALUE$0, 0);
        }
    }
    
    /**
     * Gets the "RSAKeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.RSAKeyValueType getRSAKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RSAKeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.RSAKeyValueType)get_store().find_element_user(RSAKEYVALUE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "RSAKeyValue" element
     */
    public boolean isSetRSAKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RSAKEYVALUE$2) != 0;
        }
    }
    
    /**
     * Sets the "RSAKeyValue" element
     */
    public void setRSAKeyValue(org.w3.x2000.x09.xmldsig.RSAKeyValueType rsaKeyValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RSAKeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.RSAKeyValueType)get_store().find_element_user(RSAKEYVALUE$2, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.RSAKeyValueType)get_store().add_element_user(RSAKEYVALUE$2);
            }
            target.set(rsaKeyValue);
        }
    }
    
    /**
     * Appends and returns a new empty "RSAKeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.RSAKeyValueType addNewRSAKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RSAKeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.RSAKeyValueType)get_store().add_element_user(RSAKEYVALUE$2);
            return target;
        }
    }
    
    /**
     * Unsets the "RSAKeyValue" element
     */
    public void unsetRSAKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RSAKEYVALUE$2, 0);
        }
    }
}
