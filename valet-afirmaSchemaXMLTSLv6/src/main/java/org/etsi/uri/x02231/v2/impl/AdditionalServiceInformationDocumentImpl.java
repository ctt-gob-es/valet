/*
 * An XML document type.
 * Localname: AdditionalServiceInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.AdditionalServiceInformationDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one AdditionalServiceInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class AdditionalServiceInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.AdditionalServiceInformationDocument
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
    public org.etsi.uri.x02231.v2.AdditionalServiceInformationType getAdditionalServiceInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalServiceInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalServiceInformationType)get_store().find_element_user(ADDITIONALSERVICEINFORMATION$0, 0);
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
    public void setAdditionalServiceInformation(org.etsi.uri.x02231.v2.AdditionalServiceInformationType additionalServiceInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalServiceInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalServiceInformationType)get_store().find_element_user(ADDITIONALSERVICEINFORMATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.AdditionalServiceInformationType)get_store().add_element_user(ADDITIONALSERVICEINFORMATION$0);
            }
            target.set(additionalServiceInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "AdditionalServiceInformation" element
     */
    public org.etsi.uri.x02231.v2.AdditionalServiceInformationType addNewAdditionalServiceInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalServiceInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalServiceInformationType)get_store().add_element_user(ADDITIONALSERVICEINFORMATION$0);
            return target;
        }
    }
}
