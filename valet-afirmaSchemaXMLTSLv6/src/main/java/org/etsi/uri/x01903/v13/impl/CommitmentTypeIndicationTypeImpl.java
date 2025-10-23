/*
 * XML Type:  CommitmentTypeIndicationType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CommitmentTypeIndicationType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CommitmentTypeIndicationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
{
    
    public CommitmentTypeIndicationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMMITMENTTYPEID$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CommitmentTypeId");
    private static final javax.xml.namespace.QName OBJECTREFERENCE$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ObjectReference");
    private static final javax.xml.namespace.QName ALLSIGNEDDATAOBJECTS$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AllSignedDataObjects");
    private static final javax.xml.namespace.QName COMMITMENTTYPEQUALIFIERS$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CommitmentTypeQualifiers");
    
    
    /**
     * Gets the "CommitmentTypeId" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType getCommitmentTypeId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(COMMITMENTTYPEID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CommitmentTypeId" element
     */
    public void setCommitmentTypeId(org.etsi.uri.x01903.v13.ObjectIdentifierType commitmentTypeId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(COMMITMENTTYPEID$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(COMMITMENTTYPEID$0);
            }
            target.set(commitmentTypeId);
        }
    }
    
    /**
     * Appends and returns a new empty "CommitmentTypeId" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType addNewCommitmentTypeId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(COMMITMENTTYPEID$0);
            return target;
        }
    }
    
    /**
     * Gets array of all "ObjectReference" elements
     */
    public java.lang.String[] getObjectReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OBJECTREFERENCE$2, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "ObjectReference" element
     */
    public java.lang.String getObjectReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(OBJECTREFERENCE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "ObjectReference" elements
     */
    public org.apache.xmlbeans.XmlAnyURI[] xgetObjectReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OBJECTREFERENCE$2, targetList);
            org.apache.xmlbeans.XmlAnyURI[] result = new org.apache.xmlbeans.XmlAnyURI[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "ObjectReference" element
     */
    public org.apache.xmlbeans.XmlAnyURI xgetObjectReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_element_user(OBJECTREFERENCE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.apache.xmlbeans.XmlAnyURI)target;
        }
    }
    
    /**
     * Returns number of "ObjectReference" element
     */
    public int sizeOfObjectReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OBJECTREFERENCE$2);
        }
    }
    
    /**
     * Sets array of all "ObjectReference" element
     */
    public void setObjectReferenceArray(java.lang.String[] objectReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(objectReferenceArray, OBJECTREFERENCE$2);
        }
    }
    
    /**
     * Sets ith "ObjectReference" element
     */
    public void setObjectReferenceArray(int i, java.lang.String objectReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(OBJECTREFERENCE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(objectReference);
        }
    }
    
    /**
     * Sets (as xml) array of all "ObjectReference" element
     */
    public void xsetObjectReferenceArray(org.apache.xmlbeans.XmlAnyURI[]objectReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(objectReferenceArray, OBJECTREFERENCE$2);
        }
    }
    
    /**
     * Sets (as xml) ith "ObjectReference" element
     */
    public void xsetObjectReferenceArray(int i, org.apache.xmlbeans.XmlAnyURI objectReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_element_user(OBJECTREFERENCE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(objectReference);
        }
    }
    
    /**
     * Inserts the value as the ith "ObjectReference" element
     */
    public void insertObjectReference(int i, java.lang.String objectReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(OBJECTREFERENCE$2, i);
            target.setStringValue(objectReference);
        }
    }
    
    /**
     * Appends the value as the last "ObjectReference" element
     */
    public void addObjectReference(java.lang.String objectReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(OBJECTREFERENCE$2);
            target.setStringValue(objectReference);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ObjectReference" element
     */
    public org.apache.xmlbeans.XmlAnyURI insertNewObjectReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().insert_element_user(OBJECTREFERENCE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ObjectReference" element
     */
    public org.apache.xmlbeans.XmlAnyURI addNewObjectReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_element_user(OBJECTREFERENCE$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "ObjectReference" element
     */
    public void removeObjectReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OBJECTREFERENCE$2, i);
        }
    }
    
    /**
     * Gets the "AllSignedDataObjects" element
     */
    public org.apache.xmlbeans.XmlObject getAllSignedDataObjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(ALLSIGNEDDATAOBJECTS$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "AllSignedDataObjects" element
     */
    public boolean isSetAllSignedDataObjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ALLSIGNEDDATAOBJECTS$4) != 0;
        }
    }
    
    /**
     * Sets the "AllSignedDataObjects" element
     */
    public void setAllSignedDataObjects(org.apache.xmlbeans.XmlObject allSignedDataObjects)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().find_element_user(ALLSIGNEDDATAOBJECTS$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(ALLSIGNEDDATAOBJECTS$4);
            }
            target.set(allSignedDataObjects);
        }
    }
    
    /**
     * Appends and returns a new empty "AllSignedDataObjects" element
     */
    public org.apache.xmlbeans.XmlObject addNewAllSignedDataObjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlObject target = null;
            target = (org.apache.xmlbeans.XmlObject)get_store().add_element_user(ALLSIGNEDDATAOBJECTS$4);
            return target;
        }
    }
    
    /**
     * Unsets the "AllSignedDataObjects" element
     */
    public void unsetAllSignedDataObjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ALLSIGNEDDATAOBJECTS$4, 0);
        }
    }
    
    /**
     * Gets the "CommitmentTypeQualifiers" element
     */
    public org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType getCommitmentTypeQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType)get_store().find_element_user(COMMITMENTTYPEQUALIFIERS$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "CommitmentTypeQualifiers" element
     */
    public boolean isSetCommitmentTypeQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMMITMENTTYPEQUALIFIERS$6) != 0;
        }
    }
    
    /**
     * Sets the "CommitmentTypeQualifiers" element
     */
    public void setCommitmentTypeQualifiers(org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType commitmentTypeQualifiers)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType)get_store().find_element_user(COMMITMENTTYPEQUALIFIERS$6, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType)get_store().add_element_user(COMMITMENTTYPEQUALIFIERS$6);
            }
            target.set(commitmentTypeQualifiers);
        }
    }
    
    /**
     * Appends and returns a new empty "CommitmentTypeQualifiers" element
     */
    public org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType addNewCommitmentTypeQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType)get_store().add_element_user(COMMITMENTTYPEQUALIFIERS$6);
            return target;
        }
    }
    
    /**
     * Unsets the "CommitmentTypeQualifiers" element
     */
    public void unsetCommitmentTypeQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMMITMENTTYPEQUALIFIERS$6, 0);
        }
    }
}
