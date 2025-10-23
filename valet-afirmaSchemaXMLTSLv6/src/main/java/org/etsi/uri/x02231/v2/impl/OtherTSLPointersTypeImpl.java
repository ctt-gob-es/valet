/*
 * XML Type:  OtherTSLPointersType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.OtherTSLPointersType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML OtherTSLPointersType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class OtherTSLPointersTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.OtherTSLPointersType
{
    
    public OtherTSLPointersTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OTHERTSLPOINTER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "OtherTSLPointer");
    
    
    /**
     * Gets array of all "OtherTSLPointer" elements
     */
    public org.etsi.uri.x02231.v2.OtherTSLPointerType[] getOtherTSLPointerArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OTHERTSLPOINTER$0, targetList);
            org.etsi.uri.x02231.v2.OtherTSLPointerType[] result = new org.etsi.uri.x02231.v2.OtherTSLPointerType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "OtherTSLPointer" element
     */
    public org.etsi.uri.x02231.v2.OtherTSLPointerType getOtherTSLPointerArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointerType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointerType)get_store().find_element_user(OTHERTSLPOINTER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "OtherTSLPointer" element
     */
    public int sizeOfOtherTSLPointerArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERTSLPOINTER$0);
        }
    }
    
    /**
     * Sets array of all "OtherTSLPointer" element
     */
    public void setOtherTSLPointerArray(org.etsi.uri.x02231.v2.OtherTSLPointerType[] otherTSLPointerArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(otherTSLPointerArray, OTHERTSLPOINTER$0);
        }
    }
    
    /**
     * Sets ith "OtherTSLPointer" element
     */
    public void setOtherTSLPointerArray(int i, org.etsi.uri.x02231.v2.OtherTSLPointerType otherTSLPointer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointerType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointerType)get_store().find_element_user(OTHERTSLPOINTER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(otherTSLPointer);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OtherTSLPointer" element
     */
    public org.etsi.uri.x02231.v2.OtherTSLPointerType insertNewOtherTSLPointer(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointerType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointerType)get_store().insert_element_user(OTHERTSLPOINTER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OtherTSLPointer" element
     */
    public org.etsi.uri.x02231.v2.OtherTSLPointerType addNewOtherTSLPointer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointerType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointerType)get_store().add_element_user(OTHERTSLPOINTER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "OtherTSLPointer" element
     */
    public void removeOtherTSLPointer(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERTSLPOINTER$0, i);
        }
    }
}
