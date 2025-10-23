/*
 * An XML document type.
 * Localname: SchemeInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.SchemeInformationDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one SchemeInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.SchemeInformationDocument
{
    
    public SchemeInformationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMEINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeInformation");
    
    
    /**
     * Gets the "SchemeInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType getSchemeInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType)get_store().find_element_user(SCHEMEINFORMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeInformation" element
     */
    public void setSchemeInformation(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType schemeInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType)get_store().find_element_user(SCHEMEINFORMATION$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType)get_store().add_element_user(SCHEMEINFORMATION$0);
            }
            target.set(schemeInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType addNewSchemeInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType)get_store().add_element_user(SCHEMEINFORMATION$0);
            return target;
        }
    }
}
