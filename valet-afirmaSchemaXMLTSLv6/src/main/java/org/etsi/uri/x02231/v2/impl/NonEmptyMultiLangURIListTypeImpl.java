/*
 * XML Type:  NonEmptyMultiLangURIListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML NonEmptyMultiLangURIListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class NonEmptyMultiLangURIListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType
{
    
    public NonEmptyMultiLangURIListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName URI$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "URI");
    
    
    /**
     * Gets array of all "URI" elements
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType[] getURIArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(URI$0, targetList);
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType[] result = new org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "URI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType getURIArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().find_element_user(URI$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "URI" element
     */
    public int sizeOfURIArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(URI$0);
        }
    }
    
    /**
     * Sets array of all "URI" element
     */
    public void setURIArray(org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType[] uriArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(uriArray, URI$0);
        }
    }
    
    /**
     * Sets ith "URI" element
     */
    public void setURIArray(int i, org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().find_element_user(URI$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(uri);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "URI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType insertNewURI(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().insert_element_user(URI$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "URI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType addNewURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().add_element_user(URI$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "URI" element
     */
    public void removeURI(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(URI$0, i);
        }
    }
}
