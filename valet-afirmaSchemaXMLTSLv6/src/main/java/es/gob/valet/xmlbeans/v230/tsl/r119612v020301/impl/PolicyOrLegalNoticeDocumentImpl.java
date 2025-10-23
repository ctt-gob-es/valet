/*
 * An XML document type.
 * Localname: PolicyOrLegalNotice
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.PolicyOrLegalNoticeDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one PolicyOrLegalNotice(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class PolicyOrLegalNoticeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalNoticeDocument
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
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType getPolicyOrLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType)get_store().find_element_user(POLICYORLEGALNOTICE$0, 0);
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
    public void setPolicyOrLegalNotice(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType policyOrLegalNotice)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType)get_store().find_element_user(POLICYORLEGALNOTICE$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType)get_store().add_element_user(POLICYORLEGALNOTICE$0);
            }
            target.set(policyOrLegalNotice);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicyOrLegalNotice" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType addNewPolicyOrLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType)get_store().add_element_user(POLICYORLEGALNOTICE$0);
            return target;
        }
    }
}
