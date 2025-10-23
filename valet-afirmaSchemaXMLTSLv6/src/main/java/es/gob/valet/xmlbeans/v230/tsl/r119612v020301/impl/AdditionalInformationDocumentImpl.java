/*
 * An XML document type.
 * Localname: AdditionalInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.AdditionalInformationDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one AdditionalInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class AdditionalInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationDocument
{
    
    public AdditionalInformationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ADDITIONALINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "AdditionalInformation");
    
    
    /**
     * Gets the "AdditionalInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType getAdditionalInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType)get_store().find_element_user(ADDITIONALINFORMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AdditionalInformation" element
     */
    public void setAdditionalInformation(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType additionalInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType)get_store().find_element_user(ADDITIONALINFORMATION$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType)get_store().add_element_user(ADDITIONALINFORMATION$0);
            }
            target.set(additionalInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "AdditionalInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType addNewAdditionalInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AdditionalInformationType)get_store().add_element_user(ADDITIONALINFORMATION$0);
            return target;
        }
    }
}
