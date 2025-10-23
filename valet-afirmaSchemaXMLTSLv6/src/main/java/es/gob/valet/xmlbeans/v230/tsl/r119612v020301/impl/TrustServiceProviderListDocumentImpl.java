/*
 * An XML document type.
 * Localname: TrustServiceProviderList
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TrustServiceProviderListDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one TrustServiceProviderList(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TrustServiceProviderListDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListDocument
{
    
    public TrustServiceProviderListDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRUSTSERVICEPROVIDERLIST$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TrustServiceProviderList");
    
    
    /**
     * Gets the "TrustServiceProviderList" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType getTrustServiceProviderList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType)get_store().find_element_user(TRUSTSERVICEPROVIDERLIST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TrustServiceProviderList" element
     */
    public void setTrustServiceProviderList(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType trustServiceProviderList)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType)get_store().find_element_user(TRUSTSERVICEPROVIDERLIST$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType)get_store().add_element_user(TRUSTSERVICEPROVIDERLIST$0);
            }
            target.set(trustServiceProviderList);
        }
    }
    
    /**
     * Appends and returns a new empty "TrustServiceProviderList" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType addNewTrustServiceProviderList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TrustServiceProviderListType)get_store().add_element_user(TRUSTSERVICEPROVIDERLIST$0);
            return target;
        }
    }
}
