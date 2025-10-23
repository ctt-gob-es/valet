/*
 * XML Type:  DigitalIdentityListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.DigitalIdentityListType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML DigitalIdentityListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class DigitalIdentityListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType
{
    
    public DigitalIdentityListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DIGITALID$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "DigitalId");
    
    
    /**
     * Gets array of all "DigitalId" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType[] getDigitalIdArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(DIGITALID$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "DigitalId" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType getDigitalIdArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType)get_store().find_element_user(DIGITALID$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "DigitalId" element
     */
    public int sizeOfDigitalIdArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DIGITALID$0);
        }
    }
    
    /**
     * Sets array of all "DigitalId" element
     */
    public void setDigitalIdArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType[] digitalIdArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(digitalIdArray, DIGITALID$0);
        }
    }
    
    /**
     * Sets ith "DigitalId" element
     */
    public void setDigitalIdArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType digitalId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType)get_store().find_element_user(DIGITALID$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(digitalId);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "DigitalId" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType insertNewDigitalId(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType)get_store().insert_element_user(DIGITALID$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "DigitalId" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType addNewDigitalId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityType)get_store().add_element_user(DIGITALID$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "DigitalId" element
     */
    public void removeDigitalId(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DIGITALID$0, i);
        }
    }
}
