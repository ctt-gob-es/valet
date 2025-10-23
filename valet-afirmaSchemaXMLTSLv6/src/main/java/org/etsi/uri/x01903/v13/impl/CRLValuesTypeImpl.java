/*
 * XML Type:  CRLValuesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CRLValuesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CRLValuesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CRLValuesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CRLValuesType
{
    
    public CRLValuesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENCAPSULATEDCRLVALUE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedCRLValue");
    
    
    /**
     * Gets array of all "EncapsulatedCRLValue" elements
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] getEncapsulatedCRLValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ENCAPSULATEDCRLVALUE$0, targetList);
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] result = new org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "EncapsulatedCRLValue" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType getEncapsulatedCRLValueArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDCRLVALUE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "EncapsulatedCRLValue" element
     */
    public int sizeOfEncapsulatedCRLValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ENCAPSULATEDCRLVALUE$0);
        }
    }
    
    /**
     * Sets array of all "EncapsulatedCRLValue" element
     */
    public void setEncapsulatedCRLValueArray(org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] encapsulatedCRLValueArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(encapsulatedCRLValueArray, ENCAPSULATEDCRLVALUE$0);
        }
    }
    
    /**
     * Sets ith "EncapsulatedCRLValue" element
     */
    public void setEncapsulatedCRLValueArray(int i, org.etsi.uri.x01903.v13.EncapsulatedPKIDataType encapsulatedCRLValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDCRLVALUE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(encapsulatedCRLValue);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "EncapsulatedCRLValue" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType insertNewEncapsulatedCRLValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().insert_element_user(ENCAPSULATEDCRLVALUE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "EncapsulatedCRLValue" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType addNewEncapsulatedCRLValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().add_element_user(ENCAPSULATEDCRLVALUE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "EncapsulatedCRLValue" element
     */
    public void removeEncapsulatedCRLValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ENCAPSULATEDCRLVALUE$0, i);
        }
    }
}
