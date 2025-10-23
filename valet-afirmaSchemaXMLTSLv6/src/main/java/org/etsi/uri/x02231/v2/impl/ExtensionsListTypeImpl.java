/*
 * XML Type:  ExtensionsListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ExtensionsListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML ExtensionsListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class ExtensionsListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ExtensionsListType
{
    
    public ExtensionsListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXTENSION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "Extension");
    
    
    /**
     * Gets array of all "Extension" elements
     */
    public org.etsi.uri.x02231.v2.ExtensionType[] getExtensionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(EXTENSION$0, targetList);
            org.etsi.uri.x02231.v2.ExtensionType[] result = new org.etsi.uri.x02231.v2.ExtensionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Extension" element
     */
    public org.etsi.uri.x02231.v2.ExtensionType getExtensionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionType)get_store().find_element_user(EXTENSION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Extension" element
     */
    public int sizeOfExtensionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(EXTENSION$0);
        }
    }
    
    /**
     * Sets array of all "Extension" element
     */
    public void setExtensionArray(org.etsi.uri.x02231.v2.ExtensionType[] extensionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(extensionArray, EXTENSION$0);
        }
    }
    
    /**
     * Sets ith "Extension" element
     */
    public void setExtensionArray(int i, org.etsi.uri.x02231.v2.ExtensionType extension)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionType)get_store().find_element_user(EXTENSION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(extension);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Extension" element
     */
    public org.etsi.uri.x02231.v2.ExtensionType insertNewExtension(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionType)get_store().insert_element_user(EXTENSION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Extension" element
     */
    public org.etsi.uri.x02231.v2.ExtensionType addNewExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionType)get_store().add_element_user(EXTENSION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Extension" element
     */
    public void removeExtension(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(EXTENSION$0, i);
        }
    }
}
