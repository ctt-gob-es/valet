/*
 * An XML document type.
 * Localname: AdditionalServiceInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.AdditionalServiceInformationDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one AdditionalServiceInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class AdditionalServiceInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationDocument
{
    
    public AdditionalServiceInformationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ADDITIONALSERVICEINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "AdditionalServiceInformation");
    
    
    /**
     * Gets the "AdditionalServiceInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType getAdditionalServiceInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType)get_store().find_element_user(ADDITIONALSERVICEINFORMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AdditionalServiceInformation" element
     */
    public void setAdditionalServiceInformation(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType additionalServiceInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType)get_store().find_element_user(ADDITIONALSERVICEINFORMATION$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType)get_store().add_element_user(ADDITIONALSERVICEINFORMATION$0);
            }
            target.set(additionalServiceInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "AdditionalServiceInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType addNewAdditionalServiceInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalServiceInformationType)get_store().add_element_user(ADDITIONALSERVICEINFORMATION$0);
            return target;
        }
    }
}
