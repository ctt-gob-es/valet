/*
 * XML Type:  ClaimedRolesListType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.ClaimedRolesListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML ClaimedRolesListType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class ClaimedRolesListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.ClaimedRolesListType
{
    
    public ClaimedRolesListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CLAIMEDROLE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ClaimedRole");
    
    
    /**
     * Gets array of all "ClaimedRole" elements
     */
    public org.etsi.uri.x01903.v13.AnyType[] getClaimedRoleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CLAIMEDROLE$0, targetList);
            org.etsi.uri.x01903.v13.AnyType[] result = new org.etsi.uri.x01903.v13.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ClaimedRole" element
     */
    public org.etsi.uri.x01903.v13.AnyType getClaimedRoleArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(CLAIMEDROLE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ClaimedRole" element
     */
    public int sizeOfClaimedRoleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CLAIMEDROLE$0);
        }
    }
    
    /**
     * Sets array of all "ClaimedRole" element
     */
    public void setClaimedRoleArray(org.etsi.uri.x01903.v13.AnyType[] claimedRoleArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(claimedRoleArray, CLAIMEDROLE$0);
        }
    }
    
    /**
     * Sets ith "ClaimedRole" element
     */
    public void setClaimedRoleArray(int i, org.etsi.uri.x01903.v13.AnyType claimedRole)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(CLAIMEDROLE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(claimedRole);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ClaimedRole" element
     */
    public org.etsi.uri.x01903.v13.AnyType insertNewClaimedRole(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().insert_element_user(CLAIMEDROLE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ClaimedRole" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewClaimedRole()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(CLAIMEDROLE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ClaimedRole" element
     */
    public void removeClaimedRole(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CLAIMEDROLE$0, i);
        }
    }
}
