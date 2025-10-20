/*
 * XML Type:  SigPolicyQualifiersListType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML SigPolicyQualifiersListType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SigPolicyQualifiersListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
{
    
    public SigPolicyQualifiersListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGPOLICYQUALIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigPolicyQualifier");
    
    
    /**
     * Gets array of all "SigPolicyQualifier" elements
     */
    public org.etsi.uri.x01903.v13.AnyType[] getSigPolicyQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SIGPOLICYQUALIFIER$0, targetList);
            org.etsi.uri.x01903.v13.AnyType[] result = new org.etsi.uri.x01903.v13.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "SigPolicyQualifier" element
     */
    public org.etsi.uri.x01903.v13.AnyType getSigPolicyQualifierArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(SIGPOLICYQUALIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "SigPolicyQualifier" element
     */
    public int sizeOfSigPolicyQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGPOLICYQUALIFIER$0);
        }
    }
    
    /**
     * Sets array of all "SigPolicyQualifier" element
     */
    public void setSigPolicyQualifierArray(org.etsi.uri.x01903.v13.AnyType[] sigPolicyQualifierArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(sigPolicyQualifierArray, SIGPOLICYQUALIFIER$0);
        }
    }
    
    /**
     * Sets ith "SigPolicyQualifier" element
     */
    public void setSigPolicyQualifierArray(int i, org.etsi.uri.x01903.v13.AnyType sigPolicyQualifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(SIGPOLICYQUALIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(sigPolicyQualifier);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "SigPolicyQualifier" element
     */
    public org.etsi.uri.x01903.v13.AnyType insertNewSigPolicyQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().insert_element_user(SIGPOLICYQUALIFIER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "SigPolicyQualifier" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewSigPolicyQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(SIGPOLICYQUALIFIER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "SigPolicyQualifier" element
     */
    public void removeSigPolicyQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGPOLICYQUALIFIER$0, i);
        }
    }
}
