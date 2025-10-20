/*
 * XML Type:  OCSPRefType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.OCSPRefType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML OCSPRefType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class OCSPRefTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.OCSPRefType
{
    
    public OCSPRefTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OCSPIDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OCSPIdentifier");
    private static final javax.xml.namespace.QName DIGESTALGANDVALUE$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "DigestAlgAndValue");
    
    
    /**
     * Gets the "OCSPIdentifier" element
     */
    public org.etsi.uri.x01903.v13.OCSPIdentifierType getOCSPIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPIdentifierType)get_store().find_element_user(OCSPIDENTIFIER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "OCSPIdentifier" element
     */
    public void setOCSPIdentifier(org.etsi.uri.x01903.v13.OCSPIdentifierType ocspIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPIdentifierType)get_store().find_element_user(OCSPIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.OCSPIdentifierType)get_store().add_element_user(OCSPIDENTIFIER$0);
            }
            target.set(ocspIdentifier);
        }
    }
    
    /**
     * Appends and returns a new empty "OCSPIdentifier" element
     */
    public org.etsi.uri.x01903.v13.OCSPIdentifierType addNewOCSPIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPIdentifierType)get_store().add_element_user(OCSPIDENTIFIER$0);
            return target;
        }
    }
    
    /**
     * Gets the "DigestAlgAndValue" element
     */
    public org.etsi.uri.x01903.v13.DigestAlgAndValueType getDigestAlgAndValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().find_element_user(DIGESTALGANDVALUE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "DigestAlgAndValue" element
     */
    public boolean isSetDigestAlgAndValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DIGESTALGANDVALUE$2) != 0;
        }
    }
    
    /**
     * Sets the "DigestAlgAndValue" element
     */
    public void setDigestAlgAndValue(org.etsi.uri.x01903.v13.DigestAlgAndValueType digestAlgAndValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().find_element_user(DIGESTALGANDVALUE$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().add_element_user(DIGESTALGANDVALUE$2);
            }
            target.set(digestAlgAndValue);
        }
    }
    
    /**
     * Appends and returns a new empty "DigestAlgAndValue" element
     */
    public org.etsi.uri.x01903.v13.DigestAlgAndValueType addNewDigestAlgAndValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().add_element_user(DIGESTALGANDVALUE$2);
            return target;
        }
    }
    
    /**
     * Unsets the "DigestAlgAndValue" element
     */
    public void unsetDigestAlgAndValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DIGESTALGANDVALUE$2, 0);
        }
    }
}
