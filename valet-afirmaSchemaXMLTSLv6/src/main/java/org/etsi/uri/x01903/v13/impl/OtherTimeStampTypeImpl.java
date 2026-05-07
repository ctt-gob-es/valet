/*
 * XML Type:  OtherTimeStampType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.OtherTimeStampType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML OtherTimeStampType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class OtherTimeStampTypeImpl extends org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl implements org.etsi.uri.x01903.v13.OtherTimeStampType
{
    
    public OtherTimeStampTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REFERENCEINFO$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ReferenceInfo");
    private static final javax.xml.namespace.QName ENCAPSULATEDTIMESTAMP$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedTimeStamp");
    private static final javax.xml.namespace.QName XMLTIMESTAMP$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "XMLTimeStamp");
    
    
    /**
     * Gets array of all "ReferenceInfo" elements
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType[] getReferenceInfoArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(REFERENCEINFO$0, targetList);
            org.etsi.uri.x01903.v13.ReferenceInfoType[] result = new org.etsi.uri.x01903.v13.ReferenceInfoType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ReferenceInfo" element
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType getReferenceInfoArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().find_element_user(REFERENCEINFO$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ReferenceInfo" element
     */
    public int sizeOfReferenceInfoArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REFERENCEINFO$0);
        }
    }
    
    /**
     * Sets array of all "ReferenceInfo" element
     */
    public void setReferenceInfoArray(org.etsi.uri.x01903.v13.ReferenceInfoType[] referenceInfoArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(referenceInfoArray, REFERENCEINFO$0);
        }
    }
    
    /**
     * Sets ith "ReferenceInfo" element
     */
    public void setReferenceInfoArray(int i, org.etsi.uri.x01903.v13.ReferenceInfoType referenceInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().find_element_user(REFERENCEINFO$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(referenceInfo);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ReferenceInfo" element
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType insertNewReferenceInfo(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().insert_element_user(REFERENCEINFO$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ReferenceInfo" element
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType addNewReferenceInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().add_element_user(REFERENCEINFO$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ReferenceInfo" element
     */
    public void removeReferenceInfo(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REFERENCEINFO$0, i);
        }
    }
    
    /**
     * Gets array of all "EncapsulatedTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] getEncapsulatedTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ENCAPSULATEDTIMESTAMP$2, targetList);
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] result = new org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "EncapsulatedTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType getEncapsulatedTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDTIMESTAMP$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "EncapsulatedTimeStamp" element
     */
    public int sizeOfEncapsulatedTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ENCAPSULATEDTIMESTAMP$2);
        }
    }
    
    /**
     * Sets array of all "EncapsulatedTimeStamp" element
     */
    public void setEncapsulatedTimeStampArray(org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] encapsulatedTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(encapsulatedTimeStampArray, ENCAPSULATEDTIMESTAMP$2);
        }
    }
    
    /**
     * Sets ith "EncapsulatedTimeStamp" element
     */
    public void setEncapsulatedTimeStampArray(int i, org.etsi.uri.x01903.v13.EncapsulatedPKIDataType encapsulatedTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDTIMESTAMP$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(encapsulatedTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "EncapsulatedTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType insertNewEncapsulatedTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().insert_element_user(ENCAPSULATEDTIMESTAMP$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "EncapsulatedTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType addNewEncapsulatedTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().add_element_user(ENCAPSULATEDTIMESTAMP$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "EncapsulatedTimeStamp" element
     */
    public void removeEncapsulatedTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ENCAPSULATEDTIMESTAMP$2, i);
        }
    }
    
    /**
     * Gets array of all "XMLTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.AnyType[] getXMLTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(XMLTIMESTAMP$4, targetList);
            org.etsi.uri.x01903.v13.AnyType[] result = new org.etsi.uri.x01903.v13.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "XMLTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.AnyType getXMLTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(XMLTIMESTAMP$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "XMLTimeStamp" element
     */
    public int sizeOfXMLTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(XMLTIMESTAMP$4);
        }
    }
    
    /**
     * Sets array of all "XMLTimeStamp" element
     */
    public void setXMLTimeStampArray(org.etsi.uri.x01903.v13.AnyType[] xmlTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(xmlTimeStampArray, XMLTIMESTAMP$4);
        }
    }
    
    /**
     * Sets ith "XMLTimeStamp" element
     */
    public void setXMLTimeStampArray(int i, org.etsi.uri.x01903.v13.AnyType xmlTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(XMLTIMESTAMP$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(xmlTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "XMLTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.AnyType insertNewXMLTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().insert_element_user(XMLTIMESTAMP$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "XMLTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewXMLTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(XMLTIMESTAMP$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "XMLTimeStamp" element
     */
    public void removeXMLTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(XMLTIMESTAMP$4, i);
        }
    }
}
