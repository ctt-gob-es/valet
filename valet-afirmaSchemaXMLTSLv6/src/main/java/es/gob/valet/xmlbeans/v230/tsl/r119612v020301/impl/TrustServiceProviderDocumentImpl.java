/*
 * An XML document type.
 * Localname: TrustServiceProvider
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TrustServiceProviderDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one TrustServiceProvider(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TrustServiceProviderDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderDocument
{
    
    public TrustServiceProviderDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRUSTSERVICEPROVIDER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TrustServiceProvider");
    
    
    /**
     * Gets the "TrustServiceProvider" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType getTrustServiceProvider()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType)get_store().find_element_user(TRUSTSERVICEPROVIDER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TrustServiceProvider" element
     */
    public void setTrustServiceProvider(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType trustServiceProvider)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType)get_store().find_element_user(TRUSTSERVICEPROVIDER$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType)get_store().add_element_user(TRUSTSERVICEPROVIDER$0);
            }
            target.set(trustServiceProvider);
        }
    }
    
    /**
     * Appends and returns a new empty "TrustServiceProvider" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType addNewTrustServiceProvider()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType)get_store().add_element_user(TRUSTSERVICEPROVIDER$0);
            return target;
        }
    }
}
