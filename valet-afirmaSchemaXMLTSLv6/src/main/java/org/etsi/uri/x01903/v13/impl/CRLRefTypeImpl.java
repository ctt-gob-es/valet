/*
 * XML Type:  CRLRefType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CRLRefType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CRLRefType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CRLRefTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CRLRefType
{
    
    public CRLRefTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DIGESTALGANDVALUE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "DigestAlgAndValue");
    private static final javax.xml.namespace.QName CRLIDENTIFIER$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CRLIdentifier");
    
    
    /**
     * Gets the "DigestAlgAndValue" element
     */
    public org.etsi.uri.x01903.v13.DigestAlgAndValueType getDigestAlgAndValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().find_element_user(DIGESTALGANDVALUE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
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
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().find_element_user(DIGESTALGANDVALUE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().add_element_user(DIGESTALGANDVALUE$0);
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
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().add_element_user(DIGESTALGANDVALUE$0);
            return target;
        }
    }
    
    /**
     * Gets the "CRLIdentifier" element
     */
    public org.etsi.uri.x01903.v13.CRLIdentifierType getCRLIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.CRLIdentifierType)get_store().find_element_user(CRLIDENTIFIER$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "CRLIdentifier" element
     */
    public boolean isSetCRLIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CRLIDENTIFIER$2) != 0;
        }
    }
    
    /**
     * Sets the "CRLIdentifier" element
     */
    public void setCRLIdentifier(org.etsi.uri.x01903.v13.CRLIdentifierType crlIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.CRLIdentifierType)get_store().find_element_user(CRLIDENTIFIER$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CRLIdentifierType)get_store().add_element_user(CRLIDENTIFIER$2);
            }
            target.set(crlIdentifier);
        }
    }
    
    /**
     * Appends and returns a new empty "CRLIdentifier" element
     */
    public org.etsi.uri.x01903.v13.CRLIdentifierType addNewCRLIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.CRLIdentifierType)get_store().add_element_user(CRLIDENTIFIER$2);
            return target;
        }
    }
    
    /**
     * Unsets the "CRLIdentifier" element
     */
    public void unsetCRLIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CRLIDENTIFIER$2, 0);
        }
    }
}
