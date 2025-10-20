/*
 * XML Type:  CertIDType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CertIDType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CertIDType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CertIDTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CertIDType
{
    
    public CertIDTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CERTDIGEST$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CertDigest");
    private static final javax.xml.namespace.QName ISSUERSERIAL$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "IssuerSerial");
    private static final javax.xml.namespace.QName URI$4 = 
        new javax.xml.namespace.QName("", "URI");
    
    
    /**
     * Gets the "CertDigest" element
     */
    public org.etsi.uri.x01903.v13.DigestAlgAndValueType getCertDigest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().find_element_user(CERTDIGEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CertDigest" element
     */
    public void setCertDigest(org.etsi.uri.x01903.v13.DigestAlgAndValueType certDigest)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().find_element_user(CERTDIGEST$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().add_element_user(CERTDIGEST$0);
            }
            target.set(certDigest);
        }
    }
    
    /**
     * Appends and returns a new empty "CertDigest" element
     */
    public org.etsi.uri.x01903.v13.DigestAlgAndValueType addNewCertDigest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().add_element_user(CERTDIGEST$0);
            return target;
        }
    }
    
    /**
     * Gets the "IssuerSerial" element
     */
    public org.w3.x2000.x09.xmldsig.X509IssuerSerialType getIssuerSerial()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509IssuerSerialType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509IssuerSerialType)get_store().find_element_user(ISSUERSERIAL$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "IssuerSerial" element
     */
    public void setIssuerSerial(org.w3.x2000.x09.xmldsig.X509IssuerSerialType issuerSerial)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509IssuerSerialType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509IssuerSerialType)get_store().find_element_user(ISSUERSERIAL$2, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.X509IssuerSerialType)get_store().add_element_user(ISSUERSERIAL$2);
            }
            target.set(issuerSerial);
        }
    }
    
    /**
     * Appends and returns a new empty "IssuerSerial" element
     */
    public org.w3.x2000.x09.xmldsig.X509IssuerSerialType addNewIssuerSerial()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509IssuerSerialType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509IssuerSerialType)get_store().add_element_user(ISSUERSERIAL$2);
            return target;
        }
    }
    
    /**
     * Gets the "URI" attribute
     */
    public java.lang.String getURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(URI$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "URI" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(URI$4);
            return target;
        }
    }
    
    /**
     * True if has "URI" attribute
     */
    public boolean isSetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(URI$4) != null;
        }
    }
    
    /**
     * Sets the "URI" attribute
     */
    public void setURI(java.lang.String uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(URI$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(URI$4);
            }
            target.setStringValue(uri);
        }
    }
    
    /**
     * Sets (as xml) the "URI" attribute
     */
    public void xsetURI(org.apache.xmlbeans.XmlAnyURI uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(URI$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(URI$4);
            }
            target.set(uri);
        }
    }
    
    /**
     * Unsets the "URI" attribute
     */
    public void unsetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(URI$4);
        }
    }
}
