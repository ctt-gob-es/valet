/*
 * An XML document type.
 * Localname: ServiceHistoryInstance
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceHistoryInstanceDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one ServiceHistoryInstance(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceHistoryInstanceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceDocument
{
    
    public ServiceHistoryInstanceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEHISTORYINSTANCE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceHistoryInstance");
    
    
    /**
     * Gets the "ServiceHistoryInstance" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType getServiceHistoryInstance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType)get_store().find_element_user(SERVICEHISTORYINSTANCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceHistoryInstance" element
     */
    public void setServiceHistoryInstance(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType serviceHistoryInstance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType)get_store().find_element_user(SERVICEHISTORYINSTANCE$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceHistoryInstanceType)get_store().add_element_user(SERVICEHISTORYINSTANCE$0);
            }
            target.set(serviceHistoryInstance);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceHistoryInstance" element
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
}
