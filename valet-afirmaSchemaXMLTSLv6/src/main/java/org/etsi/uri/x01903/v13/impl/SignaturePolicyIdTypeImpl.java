/*
 * XML Type:  SignaturePolicyIdType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignaturePolicyIdType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML SignaturePolicyIdType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SignaturePolicyIdTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignaturePolicyIdType
{
    
    public SignaturePolicyIdTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGPOLICYID$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigPolicyId");
    private static final javax.xml.namespace.QName TRANSFORMS$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Transforms");
    private static final javax.xml.namespace.QName SIGPOLICYHASH$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigPolicyHash");
    private static final javax.xml.namespace.QName SIGPOLICYQUALIFIERS$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigPolicyQualifiers");
    
    
    /**
     * Gets the "SigPolicyId" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType getSigPolicyId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(SIGPOLICYID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SigPolicyId" element
     */
    public void setSigPolicyId(org.etsi.uri.x01903.v13.ObjectIdentifierType sigPolicyId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(SIGPOLICYID$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(SIGPOLICYID$0);
            }
            target.set(sigPolicyId);
        }
    }
    
    /**
     * Appends and returns a new empty "SigPolicyId" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType addNewSigPolicyId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(SIGPOLICYID$0);
            return target;
        }
    }
    
    /**
     * Gets the "Transforms" element
     */
    public org.w3.x2000.x09.xmldsig.TransformsType getTransforms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.TransformsType target = null;
            target = (org.w3.x2000.x09.xmldsig.TransformsType)get_store().find_element_user(TRANSFORMS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Transforms" element
     */
    public boolean isSetTransforms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TRANSFORMS$2) != 0;
        }
    }
    
    /**
     * Sets the "Transforms" element
     */
    public void setTransforms(org.w3.x2000.x09.xmldsig.TransformsType transforms)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.TransformsType target = null;
            target = (org.w3.x2000.x09.xmldsig.TransformsType)get_store().find_element_user(TRANSFORMS$2, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.TransformsType)get_store().add_element_user(TRANSFORMS$2);
            }
            target.set(transforms);
        }
    }
    
    /**
     * Appends and returns a new empty "Transforms" element
     */
    public org.w3.x2000.x09.xmldsig.TransformsType addNewTransforms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.TransformsType target = null;
            target = (org.w3.x2000.x09.xmldsig.TransformsType)get_store().add_element_user(TRANSFORMS$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Transforms" element
     */
    public void unsetTransforms()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TRANSFORMS$2, 0);
        }
    }
    
    /**
     * Gets the "SigPolicyHash" element
     */
    public org.etsi.uri.x01903.v13.DigestAlgAndValueType getSigPolicyHash()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().find_element_user(SIGPOLICYHASH$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SigPolicyHash" element
     */
    public void setSigPolicyHash(org.etsi.uri.x01903.v13.DigestAlgAndValueType sigPolicyHash)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().find_element_user(SIGPOLICYHASH$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().add_element_user(SIGPOLICYHASH$4);
            }
            target.set(sigPolicyHash);
        }
    }
    
    /**
     * Appends and returns a new empty "SigPolicyHash" element
     */
    public org.etsi.uri.x01903.v13.DigestAlgAndValueType addNewSigPolicyHash()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DigestAlgAndValueType target = null;
            target = (org.etsi.uri.x01903.v13.DigestAlgAndValueType)get_store().add_element_user(SIGPOLICYHASH$4);
            return target;
        }
    }
    
    /**
     * Gets the "SigPolicyQualifiers" element
     */
    public org.etsi.uri.x01903.v13.SigPolicyQualifiersListType getSigPolicyQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SigPolicyQualifiersListType target = null;
            target = (org.etsi.uri.x01903.v13.SigPolicyQualifiersListType)get_store().find_element_user(SIGPOLICYQUALIFIERS$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SigPolicyQualifiers" element
     */
    public boolean isSetSigPolicyQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGPOLICYQUALIFIERS$6) != 0;
        }
    }
    
    /**
     * Sets the "SigPolicyQualifiers" element
     */
    public void setSigPolicyQualifiers(org.etsi.uri.x01903.v13.SigPolicyQualifiersListType sigPolicyQualifiers)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SigPolicyQualifiersListType target = null;
            target = (org.etsi.uri.x01903.v13.SigPolicyQualifiersListType)get_store().find_element_user(SIGPOLICYQUALIFIERS$6, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SigPolicyQualifiersListType)get_store().add_element_user(SIGPOLICYQUALIFIERS$6);
            }
            target.set(sigPolicyQualifiers);
        }
    }
    
    /**
     * Appends and returns a new empty "SigPolicyQualifiers" element
     */
    public org.etsi.uri.x01903.v13.SigPolicyQualifiersListType addNewSigPolicyQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SigPolicyQualifiersListType target = null;
            target = (org.etsi.uri.x01903.v13.SigPolicyQualifiersListType)get_store().add_element_user(SIGPOLICYQUALIFIERS$6);
            return target;
        }
    }
    
    /**
     * Unsets the "SigPolicyQualifiers" element
     */
    public void unsetSigPolicyQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGPOLICYQUALIFIERS$6, 0);
        }
    }
}
