/*
 * An XML document type.
 * Localname: PolicyOrLegalNotice
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.PolicyOrLegalNoticeDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one PolicyOrLegalNotice(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class PolicyOrLegalNoticeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.PolicyOrLegalNoticeDocument
{
    
    public PolicyOrLegalNoticeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYORLEGALNOTICE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PolicyOrLegalNotice");
    
    
    /**
     * Gets the "PolicyOrLegalNotice" element
     */
    public org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType getPolicyOrLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType target = null;
            target = (org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType)get_store().find_element_user(POLICYORLEGALNOTICE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PolicyOrLegalNotice" element
     */
    public void setPolicyOrLegalNotice(org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType policyOrLegalNotice)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType target = null;
            target = (org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType)get_store().find_element_user(POLICYORLEGALNOTICE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType)get_store().add_element_user(POLICYORLEGALNOTICE$0);
            }
            target.set(policyOrLegalNotice);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicyOrLegalNotice" element
     */
    public org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType addNewPolicyOrLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType target = null;
            target = (org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType)get_store().add_element_user(POLICYORLEGALNOTICE$0);
            return target;
        }
    }
}
