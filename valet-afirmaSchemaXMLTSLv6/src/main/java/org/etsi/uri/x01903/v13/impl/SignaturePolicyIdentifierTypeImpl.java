/*
 * XML Type:  SignaturePolicyIdentifierType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML SignaturePolicyIdentifierType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SignaturePolicyIdentifierTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
{
    
    public SignaturePolicyIdentifierTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNATUREPOLICYID$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignaturePolicyId");
    private static final javax.xml.namespace.QName SIGNATUREPOLICYIMPLIED$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignaturePolicyImplied");
    
    
    /**
     * Gets the "SignaturePolicyId" element
     */
    public org.etsi.uri.x01903.v13.SignaturePolicyIdType getSignaturePolicyId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdType)get_store().find_element_user(SIGNATUREPOLICYID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SignaturePolicyId" element
     */
    public boolean isSetSignaturePolicyId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNATUREPOLICYID$0) != 0;
        }
    }
    
    /**
     * Sets the "SignaturePolicyId" element
     */
    public void setSignaturePolicyId(org.etsi.uri.x01903.v13.SignaturePolicyIdType signaturePolicyId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdType)get_store().find_element_user(SIGNATUREPOLICYID$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignaturePolicyIdType)get_store().add_element_user(SIGNATUREPOLICYID$0);
            }
            target.set(signaturePolicyId);
        }
    }
    
    /**
     * Appends and returns a new empty "SignaturePolicyId" element
     */
    public org.etsi.uri.x01903.v13.SignaturePolicyIdType addNewSignaturePolicyId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdType)get_store().add_element_user(SIGNATUREPOLICYID$0);
            return target;
        }
    }
    
    /**
     * Unsets the "SignaturePolicyId" element
     */
    public void unsetSignaturePolicyId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNATUREPOLICYID$0, 0);
        }
    }
    
    /**
     * Gets the "SignaturePolicyImplied" element
     */
    public org.apache.xmlbeans.XmlObject getSignaturePolicyImplied()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(SIGNATUREPOLICYIMPLIED$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SignaturePolicyImplied" element
     */
    public boolean isSetSignaturePolicyImplied()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNATUREPOLICYIMPLIED$2) != 0;
        }
    }
    
    /**
     * Sets the "SignaturePolicyImplied" element
     */
    public void setSignaturePolicyImplied(org.apache.xmlbeans.XmlObject signaturePolicyImplied)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(SIGNATUREPOLICYIMPLIED$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(SIGNATUREPOLICYIMPLIED$2);
            }
            target.set(signaturePolicyImplied);
        }
    }
    
    /**
     * Appends and returns a new empty "SignaturePolicyImplied" element
     */
    public org.apache.xmlbeans.XmlObject addNewSignaturePolicyImplied()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(SIGNATUREPOLICYIMPLIED$2);
            return target;
        }
    }
    
    /**
     * Unsets the "SignaturePolicyImplied" element
     */
    public void unsetSignaturePolicyImplied()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNATUREPOLICYIMPLIED$2, 0);
        }
    }
}
