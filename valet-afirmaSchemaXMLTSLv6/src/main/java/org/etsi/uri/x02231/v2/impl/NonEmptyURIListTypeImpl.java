/*
 * XML Type:  NonEmptyURIListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.NonEmptyURIListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML NonEmptyURIListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class NonEmptyURIListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.NonEmptyURIListType
{
    
    public NonEmptyURIListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName URI$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "URI");
    
    
    /**
     * Gets array of all "URI" elements
     */
    public java.lang.String[] getURIArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(URI$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "URI" element
     */
    public java.lang.String getURIArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(URI$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "URI" elements
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType[] xgetURIArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(URI$0, targetList);
            org.etsi.uri.x02231.v2.NonEmptyURIType[] result = new org.etsi.uri.x02231.v2.NonEmptyURIType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "URI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType xgetURIArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(URI$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.etsi.uri.x02231.v2.NonEmptyURIType)target;
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
    public void setURIArray(java.lang.String[] uriArray)
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
    public void setURIArray(int i, java.lang.String uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(URI$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(uri);
        }
    }
    
    /**
     * Sets (as xml) array of all "URI" element
     */
    public void xsetURIArray(org.etsi.uri.x02231.v2.NonEmptyURIType[]uriArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(uriArray, URI$0);
        }
    }
    
    /**
     * Sets (as xml) ith "URI" element
     */
    public void xsetURIArray(int i, org.etsi.uri.x02231.v2.NonEmptyURIType uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(URI$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(uri);
        }
    }
    
    /**
     * Inserts the value as the ith "URI" element
     */
    public void insertURI(int i, java.lang.String uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(URI$0, i);
            target.setStringValue(uri);
        }
    }
    
    /**
     * Appends the value as the last "URI" element
     */
    public void addURI(java.lang.String uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(URI$0);
            target.setStringValue(uri);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "URI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType insertNewURI(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().insert_element_user(URI$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "URI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType addNewURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().add_element_user(URI$0);
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
