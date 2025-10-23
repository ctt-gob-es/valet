/*
 * XML Type:  ServiceHistoryType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceHistoryType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML ServiceHistoryType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class ServiceHistoryTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType
{
    
    public ServiceHistoryTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEHISTORYINSTANCE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceHistoryInstance");
    
    
    /**
     * Gets array of all "ServiceHistoryInstance" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType[] getServiceHistoryInstanceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICEHISTORYINSTANCE$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceHistoryInstance" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType getServiceHistoryInstanceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType)get_store().find_element_user(SERVICEHISTORYINSTANCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ServiceHistoryInstance" element
     */
    public int sizeOfServiceHistoryInstanceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEHISTORYINSTANCE$0);
        }
    }
    
    /**
     * Sets array of all "ServiceHistoryInstance" element
     */
    public void setServiceHistoryInstanceArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType[] serviceHistoryInstanceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceHistoryInstanceArray, SERVICEHISTORYINSTANCE$0);
        }
    }
    
    /**
     * Sets ith "ServiceHistoryInstance" element
     */
    public void setServiceHistoryInstanceArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType serviceHistoryInstance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType)get_store().find_element_user(SERVICEHISTORYINSTANCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceHistoryInstance);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceHistoryInstance" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType insertNewServiceHistoryInstance(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType)get_store().insert_element_user(SERVICEHISTORYINSTANCE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceHistoryInstance" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType addNewServiceHistoryInstance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType)get_store().add_element_user(SERVICEHISTORYINSTANCE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceHistoryInstance" element
     */
    public void removeServiceHistoryInstance(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEHISTORYINSTANCE$0, i);
        }
    }
}
