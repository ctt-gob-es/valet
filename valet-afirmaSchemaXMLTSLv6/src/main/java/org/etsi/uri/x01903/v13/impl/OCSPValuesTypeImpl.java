/*
 * XML Type:  OCSPValuesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.OCSPValuesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML OCSPValuesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class OCSPValuesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.OCSPValuesType
{
    
    public OCSPValuesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENCAPSULATEDOCSPVALUE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedOCSPValue");
    
    
    /**
     * Gets array of all "EncapsulatedOCSPValue" elements
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] getEncapsulatedOCSPValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ENCAPSULATEDOCSPVALUE$0, targetList);
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] result = new org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "EncapsulatedOCSPValue" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType getEncapsulatedOCSPValueArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDOCSPVALUE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "EncapsulatedOCSPValue" element
     */
    public int sizeOfEncapsulatedOCSPValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ENCAPSULATEDOCSPVALUE$0);
        }
    }
    
    /**
     * Sets array of all "EncapsulatedOCSPValue" element
     */
    public void setEncapsulatedOCSPValueArray(org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] encapsulatedOCSPValueArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(encapsulatedOCSPValueArray, ENCAPSULATEDOCSPVALUE$0);
        }
    }
    
    /**
     * Sets ith "EncapsulatedOCSPValue" element
     */
    public void setEncapsulatedOCSPValueArray(int i, org.etsi.uri.x01903.v13.EncapsulatedPKIDataType encapsulatedOCSPValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDOCSPVALUE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(encapsulatedOCSPValue);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "EncapsulatedOCSPValue" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType insertNewEncapsulatedOCSPValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().insert_element_user(ENCAPSULATEDOCSPVALUE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "EncapsulatedOCSPValue" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType addNewEncapsulatedOCSPValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().add_element_user(ENCAPSULATEDOCSPVALUE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "EncapsulatedOCSPValue" element
     */
    public void removeEncapsulatedOCSPValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ENCAPSULATEDOCSPVALUE$0, i);
        }
    }
}
