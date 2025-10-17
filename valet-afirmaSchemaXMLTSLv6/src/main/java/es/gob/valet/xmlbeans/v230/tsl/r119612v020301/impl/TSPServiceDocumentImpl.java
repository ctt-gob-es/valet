/*
 * An XML document type.
 * Localname: TSPService
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPServiceDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one TSPService(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TSPServiceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceDocument
{
    
    public TSPServiceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPSERVICE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPService");
    
    
    /**
     * Gets the "TSPService" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType getTSPService()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType)get_store().find_element_user(TSPSERVICE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPService" element
     */
    public void setTSPService(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType tspService)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType)get_store().find_element_user(TSPSERVICE$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType)get_store().add_element_user(TSPSERVICE$0);
            }
            target.set(tspService);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPService" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType addNewTSPService()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType)get_store().add_element_user(TSPSERVICE$0);
            return target;
        }
    }
}
