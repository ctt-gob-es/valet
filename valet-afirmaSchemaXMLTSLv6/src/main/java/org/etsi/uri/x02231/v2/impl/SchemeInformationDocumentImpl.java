/*
 * An XML document type.
 * Localname: SchemeInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.SchemeInformationDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one SchemeInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.SchemeInformationDocument
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
    public org.etsi.uri.x02231.v2.TSLSchemeInformationType getSchemeInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSLSchemeInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSLSchemeInformationType)get_store().find_element_user(SCHEMEINFORMATION$0, 0);
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
    public void setSchemeInformation(org.etsi.uri.x02231.v2.TSLSchemeInformationType schemeInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSLSchemeInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSLSchemeInformationType)get_store().find_element_user(SCHEMEINFORMATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TSLSchemeInformationType)get_store().add_element_user(SCHEMEINFORMATION$0);
            }
            target.set(schemeInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeInformation" element
     */
    public org.etsi.uri.x02231.v2.TSLSchemeInformationType addNewSchemeInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSLSchemeInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSLSchemeInformationType)get_store().add_element_user(SCHEMEINFORMATION$0);
            return target;
        }
    }
}
