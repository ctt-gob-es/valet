/*
 * XML Type:  InternationalNamesType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.InternationalNamesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML InternationalNamesType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class InternationalNamesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.InternationalNamesType
{
    
    public InternationalNamesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NAME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "Name");
    
    
    /**
     * Gets array of all "Name" elements
     */
    public org.etsi.uri.x02231.v2.MultiLangNormStringType[] getNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NAME$0, targetList);
            org.etsi.uri.x02231.v2.MultiLangNormStringType[] result = new org.etsi.uri.x02231.v2.MultiLangNormStringType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Name" element
     */
    public org.etsi.uri.x02231.v2.MultiLangNormStringType getNameArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangNormStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangNormStringType)get_store().find_element_user(NAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Name" element
     */
    public int sizeOfNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NAME$0);
        }
    }
    
    /**
     * Sets array of all "Name" element
     */
    public void setNameArray(org.etsi.uri.x02231.v2.MultiLangNormStringType[] nameArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(nameArray, NAME$0);
        }
    }
    
    /**
     * Sets ith "Name" element
     */
    public void setNameArray(int i, org.etsi.uri.x02231.v2.MultiLangNormStringType name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangNormStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangNormStringType)get_store().find_element_user(NAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(name);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Name" element
     */
    public org.etsi.uri.x02231.v2.MultiLangNormStringType insertNewName(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangNormStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangNormStringType)get_store().insert_element_user(NAME$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Name" element
     */
    public org.etsi.uri.x02231.v2.MultiLangNormStringType addNewName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangNormStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangNormStringType)get_store().add_element_user(NAME$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Name" element
     */
    public void removeName(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NAME$0, i);
        }
    }
}
