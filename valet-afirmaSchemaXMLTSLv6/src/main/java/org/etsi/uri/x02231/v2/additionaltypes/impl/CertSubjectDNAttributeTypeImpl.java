/*
 * XML Type:  CertSubjectDNAttributeType
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.additionaltypes.impl;
/**
 * An XML CertSubjectDNAttributeType(@http://uri.etsi.org/02231/v2/additionaltypes#).
 *
 * This is a complex type.
 */
public class CertSubjectDNAttributeTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType
{
    
    public CertSubjectDNAttributeTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEOID$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "AttributeOID");
    
    
    /**
     * Gets array of all "AttributeOID" elements
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType[] getAttributeOIDArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTEOID$0, targetList);
            org.etsi.uri.x01903.v13.ObjectIdentifierType[] result = new org.etsi.uri.x01903.v13.ObjectIdentifierType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "AttributeOID" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType getAttributeOIDArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(ATTRIBUTEOID$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "AttributeOID" element
     */
    public int sizeOfAttributeOIDArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTEOID$0);
        }
    }
    
    /**
     * Sets array of all "AttributeOID" element
     */
    public void setAttributeOIDArray(org.etsi.uri.x01903.v13.ObjectIdentifierType[] attributeOIDArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attributeOIDArray, ATTRIBUTEOID$0);
        }
    }
    
    /**
     * Sets ith "AttributeOID" element
     */
    public void setAttributeOIDArray(int i, org.etsi.uri.x01903.v13.ObjectIdentifierType attributeOID)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(ATTRIBUTEOID$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attributeOID);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AttributeOID" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType insertNewAttributeOID(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().insert_element_user(ATTRIBUTEOID$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AttributeOID" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType addNewAttributeOID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(ATTRIBUTEOID$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "AttributeOID" element
     */
    public void removeAttributeOID(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTEOID$0, i);
        }
    }
}
