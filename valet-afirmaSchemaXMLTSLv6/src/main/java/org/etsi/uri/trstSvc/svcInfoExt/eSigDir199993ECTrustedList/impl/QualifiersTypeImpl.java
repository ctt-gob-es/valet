/*
 * XML Type:  QualifiersType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifiersType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.impl;
/**
 * An XML QualifiersType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class QualifiersTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifiersType
{
    
    public QualifiersTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "Qualifier");
    
    
    /**
     * Gets array of all "Qualifier" elements
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType[] getQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(QUALIFIER$0, targetList);
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType[] result = new org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Qualifier" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType getQualifierArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType)get_store().find_element_user(QUALIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Qualifier" element
     */
    public int sizeOfQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(QUALIFIER$0);
        }
    }
    
    /**
     * Sets array of all "Qualifier" element
     */
    public void setQualifierArray(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType[] qualifierArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(qualifierArray, QUALIFIER$0);
        }
    }
    
    /**
     * Sets ith "Qualifier" element
     */
    public void setQualifierArray(int i, org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType qualifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType)get_store().find_element_user(QUALIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(qualifier);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Qualifier" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType insertNewQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType)get_store().insert_element_user(QUALIFIER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Qualifier" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType addNewQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualifierType)get_store().add_element_user(QUALIFIER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Qualifier" element
     */
    public void removeQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(QUALIFIER$0, i);
        }
    }
}
