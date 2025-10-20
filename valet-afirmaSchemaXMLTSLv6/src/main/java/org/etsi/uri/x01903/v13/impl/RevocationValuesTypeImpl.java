/*
 * XML Type:  RevocationValuesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.RevocationValuesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML RevocationValuesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class RevocationValuesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.RevocationValuesType
{
    
    public RevocationValuesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CRLVALUES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CRLValues");
    private static final javax.xml.namespace.QName OCSPVALUES$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OCSPValues");
    private static final javax.xml.namespace.QName OTHERVALUES$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OtherValues");
    private static final javax.xml.namespace.QName ID$6 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets the "CRLValues" element
     */
    public org.etsi.uri.x01903.v13.CRLValuesType getCRLValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CRLValuesType)get_store().find_element_user(CRLVALUES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "CRLValues" element
     */
    public boolean isSetCRLValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CRLVALUES$0) != 0;
        }
    }
    
    /**
     * Sets the "CRLValues" element
     */
    public void setCRLValues(org.etsi.uri.x01903.v13.CRLValuesType crlValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CRLValuesType)get_store().find_element_user(CRLVALUES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CRLValuesType)get_store().add_element_user(CRLVALUES$0);
            }
            target.set(crlValues);
        }
    }
    
    /**
     * Appends and returns a new empty "CRLValues" element
     */
    public org.etsi.uri.x01903.v13.CRLValuesType addNewCRLValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CRLValuesType)get_store().add_element_user(CRLVALUES$0);
            return target;
        }
    }
    
    /**
     * Unsets the "CRLValues" element
     */
    public void unsetCRLValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CRLVALUES$0, 0);
        }
    }
    
    /**
     * Gets the "OCSPValues" element
     */
    public org.etsi.uri.x01903.v13.OCSPValuesType getOCSPValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPValuesType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPValuesType)get_store().find_element_user(OCSPVALUES$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "OCSPValues" element
     */
    public boolean isSetOCSPValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OCSPVALUES$2) != 0;
        }
    }
    
    /**
     * Sets the "OCSPValues" element
     */
    public void setOCSPValues(org.etsi.uri.x01903.v13.OCSPValuesType ocspValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPValuesType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPValuesType)get_store().find_element_user(OCSPVALUES$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.OCSPValuesType)get_store().add_element_user(OCSPVALUES$2);
            }
            target.set(ocspValues);
        }
    }
    
    /**
     * Appends and returns a new empty "OCSPValues" element
     */
    public org.etsi.uri.x01903.v13.OCSPValuesType addNewOCSPValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPValuesType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPValuesType)get_store().add_element_user(OCSPVALUES$2);
            return target;
        }
    }
    
    /**
     * Unsets the "OCSPValues" element
     */
    public void unsetOCSPValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OCSPVALUES$2, 0);
        }
    }
    
    /**
     * Gets the "OtherValues" element
     */
    public org.etsi.uri.x01903.v13.OtherCertStatusValuesType getOtherValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherCertStatusValuesType target = null;
            target = (org.etsi.uri.x01903.v13.OtherCertStatusValuesType)get_store().find_element_user(OTHERVALUES$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "OtherValues" element
     */
    public boolean isSetOtherValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERVALUES$4) != 0;
        }
    }
    
    /**
     * Sets the "OtherValues" element
     */
    public void setOtherValues(org.etsi.uri.x01903.v13.OtherCertStatusValuesType otherValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherCertStatusValuesType target = null;
            target = (org.etsi.uri.x01903.v13.OtherCertStatusValuesType)get_store().find_element_user(OTHERVALUES$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.OtherCertStatusValuesType)get_store().add_element_user(OTHERVALUES$4);
            }
            target.set(otherValues);
        }
    }
    
    /**
     * Appends and returns a new empty "OtherValues" element
     */
    public org.etsi.uri.x01903.v13.OtherCertStatusValuesType addNewOtherValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherCertStatusValuesType target = null;
            target = (org.etsi.uri.x01903.v13.OtherCertStatusValuesType)get_store().add_element_user(OTHERVALUES$4);
            return target;
        }
    }
    
    /**
     * Unsets the "OtherValues" element
     */
    public void unsetOtherValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERVALUES$4, 0);
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
