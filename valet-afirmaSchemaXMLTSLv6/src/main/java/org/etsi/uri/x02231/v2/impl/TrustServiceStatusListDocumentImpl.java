/*
 * An XML document type.
 * Localname: TrustServiceStatusList
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TrustServiceStatusListDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one TrustServiceStatusList(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TrustServiceStatusListDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TrustServiceStatusListDocument
{
    
    public TrustServiceStatusListDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRUSTSERVICESTATUSLIST$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TrustServiceStatusList");
    
    
    /**
     * Gets the "TrustServiceStatusList" element
     */
    public org.etsi.uri.x02231.v2.TrustStatusListType getTrustServiceStatusList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TrustStatusListType target = null;
            target = (org.etsi.uri.x02231.v2.TrustStatusListType)get_store().find_element_user(TRUSTSERVICESTATUSLIST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TrustServiceStatusList" element
     */
    public void setTrustServiceStatusList(org.etsi.uri.x02231.v2.TrustStatusListType trustServiceStatusList)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TrustStatusListType target = null;
            target = (org.etsi.uri.x02231.v2.TrustStatusListType)get_store().find_element_user(TRUSTSERVICESTATUSLIST$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TrustStatusListType)get_store().add_element_user(TRUSTSERVICESTATUSLIST$0);
            }
            target.set(trustServiceStatusList);
        }
    }
    
    /**
     * Appends and returns a new empty "TrustServiceStatusList" element
     */
    public org.etsi.uri.x02231.v2.TrustStatusListType addNewTrustServiceStatusList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TrustStatusListType target = null;
            target = (org.etsi.uri.x02231.v2.TrustStatusListType)get_store().add_element_user(TRUSTSERVICESTATUSLIST$0);
            return target;
        }
    }
}
