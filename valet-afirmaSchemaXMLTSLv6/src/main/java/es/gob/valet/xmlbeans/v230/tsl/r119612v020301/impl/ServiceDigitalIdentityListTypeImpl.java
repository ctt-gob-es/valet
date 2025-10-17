/*
 * XML Type:  ServiceDigitalIdentityListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceDigitalIdentityListType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML ServiceDigitalIdentityListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class ServiceDigitalIdentityListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType
{
    
    public ServiceDigitalIdentityListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEDIGITALIDENTITY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceDigitalIdentity");
    
    
    /**
     * Gets array of all "ServiceDigitalIdentity" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType[] getServiceDigitalIdentityArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICEDIGITALIDENTITY$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceDigitalIdentity" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType getServiceDigitalIdentityArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITY$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ServiceDigitalIdentity" element
     */
    public int sizeOfServiceDigitalIdentityArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEDIGITALIDENTITY$0);
        }
    }
    
    /**
     * Sets array of all "ServiceDigitalIdentity" element
     */
    public void setServiceDigitalIdentityArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType[] serviceDigitalIdentityArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceDigitalIdentityArray, SERVICEDIGITALIDENTITY$0);
        }
    }
    
    /**
     * Sets ith "ServiceDigitalIdentity" element
     */
    public void setServiceDigitalIdentityArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType serviceDigitalIdentity)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITY$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceDigitalIdentity);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceDigitalIdentity" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType insertNewServiceDigitalIdentity(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType)get_store().insert_element_user(SERVICEDIGITALIDENTITY$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceDigitalIdentity" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType addNewServiceDigitalIdentity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITY$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceDigitalIdentity" element
     */
    public void removeServiceDigitalIdentity(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEDIGITALIDENTITY$0, i);
        }
    }
}
