/*
 * XML Type:  TSPServiceType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPServiceType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML TSPServiceType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TSPServiceTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType
{
    
    public TSPServiceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceInformation");
    private static final javax.xml.namespace.QName SERVICEHISTORY$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceHistory");
    
    
    /**
     * Gets the "ServiceInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType getServiceInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType)get_store().find_element_user(SERVICEINFORMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceInformation" element
     */
    public void setServiceInformation(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType serviceInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType)get_store().find_element_user(SERVICEINFORMATION$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType)get_store().add_element_user(SERVICEINFORMATION$0);
            }
            target.set(serviceInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType addNewServiceInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType)get_store().add_element_user(SERVICEINFORMATION$0);
            return target;
        }
    }
    
    /**
     * Gets the "ServiceHistory" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType getServiceHistory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType)get_store().find_element_user(SERVICEHISTORY$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ServiceHistory" element
     */
    public boolean isSetServiceHistory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEHISTORY$2) != 0;
        }
    }
    
    /**
     * Sets the "ServiceHistory" element
     */
    public void setServiceHistory(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType serviceHistory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType)get_store().find_element_user(SERVICEHISTORY$2, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType)get_store().add_element_user(SERVICEHISTORY$2);
            }
            target.set(serviceHistory);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceHistory" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType addNewServiceHistory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryType)get_store().add_element_user(SERVICEHISTORY$2);
            return target;
        }
    }
    
    /**
     * Unsets the "ServiceHistory" element
     */
    public void unsetServiceHistory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEHISTORY$2, 0);
        }
    }
}
