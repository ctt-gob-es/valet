/*
 * An XML document type.
 * Localname: SchemeInformationURI
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.SchemeInformationURIDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one SchemeInformationURI(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeInformationURIDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.SchemeInformationURIDocument
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
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType getSchemeInformationURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMEINFORMATIONURI$0, 0);
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
    public void setSchemeInformationURI(org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType schemeInformationURI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMEINFORMATIONURI$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMEINFORMATIONURI$0);
            }
            target.set(schemeInformationURI);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeInformationURI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType addNewSchemeInformationURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMEINFORMATIONURI$0);
            return target;
        }
    }
}
