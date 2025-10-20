/*
 * An XML document type.
 * Localname: AdditionalInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.AdditionalInformationDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one AdditionalInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class AdditionalInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.AdditionalInformationDocument
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
    public org.etsi.uri.x02231.v2.AdditionalInformationType getAdditionalInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalInformationType)get_store().find_element_user(ADDITIONALINFORMATION$0, 0);
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
    public void setAdditionalInformation(org.etsi.uri.x02231.v2.AdditionalInformationType additionalInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalInformationType)get_store().find_element_user(ADDITIONALINFORMATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.AdditionalInformationType)get_store().add_element_user(ADDITIONALINFORMATION$0);
            }
            target.set(additionalInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "AdditionalInformation" element
     */
    public org.etsi.uri.x02231.v2.AdditionalInformationType addNewAdditionalInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalInformationType)get_store().add_element_user(ADDITIONALINFORMATION$0);
            return target;
        }
    }
}
