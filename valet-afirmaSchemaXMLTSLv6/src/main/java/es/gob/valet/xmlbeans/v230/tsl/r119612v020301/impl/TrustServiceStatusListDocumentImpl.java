/*
 * An XML document type.
 * Localname: TrustServiceStatusList
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TrustServiceStatusListDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one TrustServiceStatusList(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TrustServiceStatusListDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceStatusListDocument
{
    
    public TrustServiceStatusListDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRUSTSERVICESTATUSLIST$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TrustServiceStatusList");
    
    
    /**
     * Gets the "TrustServiceStatusList" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType getTrustServiceStatusList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType)get_store().find_element_user(TRUSTSERVICESTATUSLIST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TrustServiceStatusList" element
     */
    public void setTrustServiceStatusList(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType trustServiceStatusList)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType)get_store().find_element_user(TRUSTSERVICESTATUSLIST$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType)get_store().add_element_user(TRUSTSERVICESTATUSLIST$0);
            }
            target.set(trustServiceStatusList);
        }
    }
    
    /**
     * Appends and returns a new empty "TrustServiceStatusList" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType addNewTrustServiceStatusList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustStatusListType)get_store().add_element_user(TRUSTSERVICESTATUSLIST$0);
            return target;
        }
    }
}
