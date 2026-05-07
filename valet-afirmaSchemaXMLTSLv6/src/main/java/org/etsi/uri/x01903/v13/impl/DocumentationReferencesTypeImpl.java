/*
 * XML Type:  DocumentationReferencesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.DocumentationReferencesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML DocumentationReferencesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class DocumentationReferencesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.DocumentationReferencesType
{
    
    public DocumentationReferencesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DOCUMENTATIONREFERENCE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "DocumentationReference");
    
    
    /**
     * Gets array of all "DocumentationReference" elements
     */
    public java.lang.String[] getDocumentationReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(DOCUMENTATIONREFERENCE$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "DocumentationReference" element
     */
    public java.lang.String getDocumentationReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DOCUMENTATIONREFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "DocumentationReference" elements
     */
    public org.apache.xmlbeans.XmlAnyURI[] xgetDocumentationReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(DOCUMENTATIONREFERENCE$0, targetList);
            org.apache.xmlbeans.XmlAnyURI[] result = new org.apache.xmlbeans.XmlAnyURI[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "DocumentationReference" element
     */
    public org.apache.xmlbeans.XmlAnyURI xgetDocumentationReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_element_user(DOCUMENTATIONREFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.apache.xmlbeans.XmlAnyURI)target;
        }
    }
    
    /**
     * Returns number of "DocumentationReference" element
     */
    public int sizeOfDocumentationReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DOCUMENTATIONREFERENCE$0);
        }
    }
    
    /**
     * Sets array of all "DocumentationReference" element
     */
    public void setDocumentationReferenceArray(java.lang.String[] documentationReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(documentationReferenceArray, DOCUMENTATIONREFERENCE$0);
        }
    }
    
    /**
     * Sets ith "DocumentationReference" element
     */
    public void setDocumentationReferenceArray(int i, java.lang.String documentationReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DOCUMENTATIONREFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(documentationReference);
        }
    }
    
    /**
     * Sets (as xml) array of all "DocumentationReference" element
     */
    public void xsetDocumentationReferenceArray(org.apache.xmlbeans.XmlAnyURI[]documentationReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(documentationReferenceArray, DOCUMENTATIONREFERENCE$0);
        }
    }
    
    /**
     * Sets (as xml) ith "DocumentationReference" element
     */
    public void xsetDocumentationReferenceArray(int i, org.apache.xmlbeans.XmlAnyURI documentationReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_element_user(DOCUMENTATIONREFERENCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(documentationReference);
        }
    }
    
    /**
     * Inserts the value as the ith "DocumentationReference" element
     */
    public void insertDocumentationReference(int i, java.lang.String documentationReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(DOCUMENTATIONREFERENCE$0, i);
            target.setStringValue(documentationReference);
        }
    }
    
    /**
     * Appends the value as the last "DocumentationReference" element
     */
    public void addDocumentationReference(java.lang.String documentationReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DOCUMENTATIONREFERENCE$0);
            target.setStringValue(documentationReference);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "DocumentationReference" element
     */
    public org.apache.xmlbeans.XmlAnyURI insertNewDocumentationReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().insert_element_user(DOCUMENTATIONREFERENCE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "DocumentationReference" element
     */
    public org.apache.xmlbeans.XmlAnyURI addNewDocumentationReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_element_user(DOCUMENTATIONREFERENCE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "DocumentationReference" element
     */
    public void removeDocumentationReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DOCUMENTATIONREFERENCE$0, i);
        }
    }
}
