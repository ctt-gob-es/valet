/*
 * XML Type:  ExtendedKeyUsageType
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.additionalTypes.ExtendedKeyUsageType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.impl;
/**
 * An XML ExtendedKeyUsageType(@http://uri.etsi.org/02231/v2/additionaltypes#).
 *
 * This is a complex type.
 */
public class ExtendedKeyUsageTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType
{
    
    public ExtendedKeyUsageTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KEYPURPOSEID$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "KeyPurposeId");
    
    
    /**
     * Gets array of all "KeyPurposeId" elements
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType[] getKeyPurposeIdArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(KEYPURPOSEID$0, targetList);
            org.etsi.uri.x01903.v13.ObjectIdentifierType[] result = new org.etsi.uri.x01903.v13.ObjectIdentifierType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "KeyPurposeId" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType getKeyPurposeIdArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(KEYPURPOSEID$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "KeyPurposeId" element
     */
    public int sizeOfKeyPurposeIdArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(KEYPURPOSEID$0);
        }
    }
    
    /**
     * Sets array of all "KeyPurposeId" element
     */
    public void setKeyPurposeIdArray(org.etsi.uri.x01903.v13.ObjectIdentifierType[] keyPurposeIdArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(keyPurposeIdArray, KEYPURPOSEID$0);
        }
    }
    
    /**
     * Sets ith "KeyPurposeId" element
     */
    public void setKeyPurposeIdArray(int i, org.etsi.uri.x01903.v13.ObjectIdentifierType keyPurposeId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(KEYPURPOSEID$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(keyPurposeId);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "KeyPurposeId" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType insertNewKeyPurposeId(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().insert_element_user(KEYPURPOSEID$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "KeyPurposeId" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType addNewKeyPurposeId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(KEYPURPOSEID$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "KeyPurposeId" element
     */
    public void removeKeyPurposeId(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(KEYPURPOSEID$0, i);
        }
    }
}
