/*
 * XML Type:  QualificationsType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.impl;
/**
 * An XML QualificationsType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class QualificationsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType
{
    
    public QualificationsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFICATIONELEMENT$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "QualificationElement");
    
    
    /**
     * Gets array of all "QualificationElement" elements
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType[] getQualificationElementArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(QUALIFICATIONELEMENT$0, targetList);
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType[] result = new org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "QualificationElement" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType getQualificationElementArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType)get_store().find_element_user(QUALIFICATIONELEMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "QualificationElement" element
     */
    public int sizeOfQualificationElementArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(QUALIFICATIONELEMENT$0);
        }
    }
    
    /**
     * Sets array of all "QualificationElement" element
     */
    public void setQualificationElementArray(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType[] qualificationElementArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(qualificationElementArray, QUALIFICATIONELEMENT$0);
        }
    }
    
    /**
     * Sets ith "QualificationElement" element
     */
    public void setQualificationElementArray(int i, org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType qualificationElement)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType)get_store().find_element_user(QUALIFICATIONELEMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(qualificationElement);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "QualificationElement" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType insertNewQualificationElement(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType)get_store().insert_element_user(QUALIFICATIONELEMENT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "QualificationElement" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType addNewQualificationElement()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType)get_store().add_element_user(QUALIFICATIONELEMENT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "QualificationElement" element
     */
    public void removeQualificationElement(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(QUALIFICATIONELEMENT$0, i);
        }
    }
}
