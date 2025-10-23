/*
 * An XML document type.
 * Localname: SchemeInformationURI
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.SchemeInformationURIDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one SchemeInformationURI(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeInformationURIDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.SchemeInformationURIDocument
{
    
    public SchemeInformationURIDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMEINFORMATIONURI$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeInformationURI");
    
    
    /**
     * Gets the "SchemeInformationURI" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getSchemeInformationURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMEINFORMATIONURI$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeInformationURI" element
     */
    public void setSchemeInformationURI(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType schemeInformationURI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMEINFORMATIONURI$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMEINFORMATIONURI$0);
            }
            target.set(schemeInformationURI);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeInformationURI" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewSchemeInformationURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMEINFORMATIONURI$0);
            return target;
        }
    }
}
