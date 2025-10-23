/*
 * XML Type:  CommitmentTypeQualifiersListType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CommitmentTypeQualifiersListType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CommitmentTypeQualifiersListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType
{
    
    public CommitmentTypeQualifiersListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMMITMENTTYPEQUALIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CommitmentTypeQualifier");
    
    
    /**
     * Gets array of all "CommitmentTypeQualifier" elements
     */
    public org.etsi.uri.x01903.v13.AnyType[] getCommitmentTypeQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COMMITMENTTYPEQUALIFIER$0, targetList);
            org.etsi.uri.x01903.v13.AnyType[] result = new org.etsi.uri.x01903.v13.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CommitmentTypeQualifier" element
     */
    public org.etsi.uri.x01903.v13.AnyType getCommitmentTypeQualifierArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(COMMITMENTTYPEQUALIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CommitmentTypeQualifier" element
     */
    public int sizeOfCommitmentTypeQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMMITMENTTYPEQUALIFIER$0);
        }
    }
    
    /**
     * Sets array of all "CommitmentTypeQualifier" element
     */
    public void setCommitmentTypeQualifierArray(org.etsi.uri.x01903.v13.AnyType[] commitmentTypeQualifierArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(commitmentTypeQualifierArray, COMMITMENTTYPEQUALIFIER$0);
        }
    }
    
    /**
     * Sets ith "CommitmentTypeQualifier" element
     */
    public void setCommitmentTypeQualifierArray(int i, org.etsi.uri.x01903.v13.AnyType commitmentTypeQualifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(COMMITMENTTYPEQUALIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(commitmentTypeQualifier);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CommitmentTypeQualifier" element
     */
    public org.etsi.uri.x01903.v13.AnyType insertNewCommitmentTypeQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().insert_element_user(COMMITMENTTYPEQUALIFIER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CommitmentTypeQualifier" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewCommitmentTypeQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(COMMITMENTTYPEQUALIFIER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "CommitmentTypeQualifier" element
     */
    public void removeCommitmentTypeQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMMITMENTTYPEQUALIFIER$0, i);
        }
    }
}
