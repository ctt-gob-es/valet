/*
 * An XML document type.
 * Localname: TSPServices
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPServicesDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one TSPServices(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TSPServicesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesDocument
{
    
    public TSPServicesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPSERVICES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPServices");
    
    
    /**
     * Gets the "TSPServices" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType getTSPServices()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType)get_store().find_element_user(TSPSERVICES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPServices" element
     */
    public void setTSPServices(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType tspServices)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType)get_store().find_element_user(TSPSERVICES$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType)get_store().add_element_user(TSPSERVICES$0);
            }
            target.set(tspServices);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPServices" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType addNewTSPServices()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType)get_store().add_element_user(TSPSERVICES$0);
            return target;
        }
    }
}
