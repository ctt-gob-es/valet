/*
 * XML Type:  PoliciesListType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.impl;
/**
 * An XML PoliciesListType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class PoliciesListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType
{
    
    public PoliciesListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYIDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "PolicyIdentifier");
    
    
    /**
     * Gets array of all "PolicyIdentifier" elements
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType[] getPolicyIdentifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POLICYIDENTIFIER$0, targetList);
            org.etsi.uri.x01903.v13.ObjectIdentifierType[] result = new org.etsi.uri.x01903.v13.ObjectIdentifierType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PolicyIdentifier" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType getPolicyIdentifierArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(POLICYIDENTIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PolicyIdentifier" element
     */
    public int sizeOfPolicyIdentifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYIDENTIFIER$0);
        }
    }
    
    /**
     * Sets array of all "PolicyIdentifier" element
     */
    public void setPolicyIdentifierArray(org.etsi.uri.x01903.v13.ObjectIdentifierType[] policyIdentifierArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(policyIdentifierArray, POLICYIDENTIFIER$0);
        }
    }
    
    /**
     * Sets ith "PolicyIdentifier" element
     */
    public void setPolicyIdentifierArray(int i, org.etsi.uri.x01903.v13.ObjectIdentifierType policyIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(POLICYIDENTIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(policyIdentifier);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicyIdentifier" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType insertNewPolicyIdentifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().insert_element_user(POLICYIDENTIFIER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicyIdentifier" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType addNewPolicyIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(POLICYIDENTIFIER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "PolicyIdentifier" element
     */
    public void removePolicyIdentifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYIDENTIFIER$0, i);
        }
    }
}
