/*
 * An XML document type.
 * Localname: ServiceInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceInformationDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one ServiceInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceInformationDocument
{
    
    public ServiceInformationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceInformation");
    
    
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
}
