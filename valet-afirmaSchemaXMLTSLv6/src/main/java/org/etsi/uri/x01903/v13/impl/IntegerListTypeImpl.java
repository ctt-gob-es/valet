/*
 * XML Type:  IntegerListType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.IntegerListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML IntegerListType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class IntegerListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.IntegerListType
{
    
    public IntegerListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INT$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "int");
    
    
    /**
     * Gets array of all "int" elements
     */
    public java.math.BigInteger[] getIntArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(INT$0, targetList);
            java.math.BigInteger[] result = new java.math.BigInteger[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getBigIntegerValue();
            return result;
        }
    }
    
    /**
     * Gets ith "int" element
     */
    public java.math.BigInteger getIntArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "int" elements
     */
    public org.apache.xmlbeans.XmlInteger[] xgetIntArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(INT$0, targetList);
            org.apache.xmlbeans.XmlInteger[] result = new org.apache.xmlbeans.XmlInteger[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "int" element
     */
    public org.apache.xmlbeans.XmlInteger xgetIntArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(INT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.apache.xmlbeans.XmlInteger)target;
        }
    }
    
    /**
     * Returns number of "int" element
     */
    public int sizeOfIntArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(INT$0);
        }
    }
    
    /**
     * Sets array of all "int" element
     */
    public void setIntArray(java.math.BigInteger[] xintArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(xintArray, INT$0);
        }
    }
    
    /**
     * Sets ith "int" element
     */
    public void setIntArray(int i, java.math.BigInteger xint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(xint);
        }
    }
    
    /**
     * Sets (as xml) array of all "int" element
     */
    public void xsetIntArray(org.apache.xmlbeans.XmlInteger[]xintArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(xintArray, INT$0);
        }
    }
    
    /**
     * Sets (as xml) ith "int" element
     */
    public void xsetIntArray(int i, org.apache.xmlbeans.XmlInteger xint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(INT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(xint);
        }
    }
    
    /**
     * Inserts the value as the ith "int" element
     */
    public void insertInt(int i, java.math.BigInteger xint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(INT$0, i);
            target.setBigIntegerValue(xint);
        }
    }
    
    /**
     * Appends the value as the last "int" element
     */
    public void addInt(java.math.BigInteger xint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(INT$0);
            target.setBigIntegerValue(xint);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "int" element
     */
    public org.apache.xmlbeans.XmlInteger insertNewInt(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().insert_element_user(INT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "int" element
     */
    public org.apache.xmlbeans.XmlInteger addNewInt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(INT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "int" element
     */
    public void removeInt(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(INT$0, i);
        }
    }
}
