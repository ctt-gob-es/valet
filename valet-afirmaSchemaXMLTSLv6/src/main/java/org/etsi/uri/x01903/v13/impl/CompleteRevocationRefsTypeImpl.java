/*
 * XML Type:  CompleteRevocationRefsType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CompleteRevocationRefsType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CompleteRevocationRefsType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CompleteRevocationRefsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CompleteRevocationRefsType
{
    
    public CompleteRevocationRefsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CRLREFS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CRLRefs");
    private static final javax.xml.namespace.QName OCSPREFS$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OCSPRefs");
    private static final javax.xml.namespace.QName OTHERREFS$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OtherRefs");
    private static final javax.xml.namespace.QName ID$6 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets the "CRLRefs" element
     */
    public org.etsi.uri.x01903.v13.CRLRefsType getCRLRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CRLRefsType)get_store().find_element_user(CRLREFS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "CRLRefs" element
     */
    public boolean isSetCRLRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CRLREFS$0) != 0;
        }
    }
    
    /**
     * Sets the "CRLRefs" element
     */
    public void setCRLRefs(org.etsi.uri.x01903.v13.CRLRefsType crlRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CRLRefsType)get_store().find_element_user(CRLREFS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CRLRefsType)get_store().add_element_user(CRLREFS$0);
            }
            target.set(crlRefs);
        }
    }
    
    /**
     * Appends and returns a new empty "CRLRefs" element
     */
    public org.etsi.uri.x01903.v13.CRLRefsType addNewCRLRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CRLRefsType)get_store().add_element_user(CRLREFS$0);
            return target;
        }
    }
    
    /**
     * Unsets the "CRLRefs" element
     */
    public void unsetCRLRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CRLREFS$0, 0);
        }
    }
    
    /**
     * Gets the "OCSPRefs" element
     */
    public org.etsi.uri.x01903.v13.OCSPRefsType getOCSPRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPRefsType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPRefsType)get_store().find_element_user(OCSPREFS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "OCSPRefs" element
     */
    public boolean isSetOCSPRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OCSPREFS$2) != 0;
        }
    }
    
    /**
     * Sets the "OCSPRefs" element
     */
    public void setOCSPRefs(org.etsi.uri.x01903.v13.OCSPRefsType ocspRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPRefsType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPRefsType)get_store().find_element_user(OCSPREFS$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.OCSPRefsType)get_store().add_element_user(OCSPREFS$2);
            }
            target.set(ocspRefs);
        }
    }
    
    /**
     * Appends and returns a new empty "OCSPRefs" element
     */
    public org.etsi.uri.x01903.v13.OCSPRefsType addNewOCSPRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPRefsType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPRefsType)get_store().add_element_user(OCSPREFS$2);
            return target;
        }
    }
    
    /**
     * Unsets the "OCSPRefs" element
     */
    public void unsetOCSPRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OCSPREFS$2, 0);
        }
    }
    
    /**
     * Gets the "OtherRefs" element
     */
    public org.etsi.uri.x01903.v13.OtherCertStatusRefsType getOtherRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherCertStatusRefsType target = null;
            target = (org.etsi.uri.x01903.v13.OtherCertStatusRefsType)get_store().find_element_user(OTHERREFS$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "OtherRefs" element
     */
    public boolean isSetOtherRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERREFS$4) != 0;
        }
    }
    
    /**
     * Sets the "OtherRefs" element
     */
    public void setOtherRefs(org.etsi.uri.x01903.v13.OtherCertStatusRefsType otherRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherCertStatusRefsType target = null;
            target = (org.etsi.uri.x01903.v13.OtherCertStatusRefsType)get_store().find_element_user(OTHERREFS$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.OtherCertStatusRefsType)get_store().add_element_user(OTHERREFS$4);
            }
            target.set(otherRefs);
        }
    }
    
    /**
     * Appends and returns a new empty "OtherRefs" element
     */
    public org.etsi.uri.x01903.v13.OtherCertStatusRefsType addNewOtherRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherCertStatusRefsType target = null;
            target = (org.etsi.uri.x01903.v13.OtherCertStatusRefsType)get_store().add_element_user(OTHERREFS$4);
            return target;
        }
    }
    
    /**
     * Unsets the "OtherRefs" element
     */
    public void unsetOtherRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERREFS$4, 0);
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
