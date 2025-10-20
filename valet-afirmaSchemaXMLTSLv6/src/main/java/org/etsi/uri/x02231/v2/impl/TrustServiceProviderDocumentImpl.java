/*
 * An XML document type.
 * Localname: TrustServiceProvider
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TrustServiceProviderDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one TrustServiceProvider(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TrustServiceProviderDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TrustServiceProviderDocument
{
    
    public TrustServiceProviderDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRUSTSERVICEPROVIDER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TrustServiceProvider");
    
    
    /**
     * Gets the "TrustServiceProvider" element
     */
    public org.etsi.uri.x02231.v2.TSPType getTrustServiceProvider()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPType target = null;
            target = (org.etsi.uri.x02231.v2.TSPType)get_store().find_element_user(TRUSTSERVICEPROVIDER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TrustServiceProvider" element
     */
    public void setTrustServiceProvider(org.etsi.uri.x02231.v2.TSPType trustServiceProvider)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPType target = null;
            target = (org.etsi.uri.x02231.v2.TSPType)get_store().find_element_user(TRUSTSERVICEPROVIDER$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TSPType)get_store().add_element_user(TRUSTSERVICEPROVIDER$0);
            }
            target.set(trustServiceProvider);
        }
    }
    
    /**
     * Appends and returns a new empty "TrustServiceProvider" element
     */
    public org.etsi.uri.x02231.v2.TSPType addNewTrustServiceProvider()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPType target = null;
            target = (org.etsi.uri.x02231.v2.TSPType)get_store().add_element_user(TRUSTSERVICEPROVIDER$0);
            return target;
        }
    }
}
