/*
 * An XML document type.
 * Localname: SchemeTypeCommunityRules
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.SchemeTypeCommunityRulesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one SchemeTypeCommunityRules(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeTypeCommunityRulesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.SchemeTypeCommunityRulesDocument
{
    
    public SchemeTypeCommunityRulesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMETYPECOMMUNITYRULES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeTypeCommunityRules");
    
    
    /**
     * Gets the "SchemeTypeCommunityRules" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType getSchemeTypeCommunityRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMETYPECOMMUNITYRULES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeTypeCommunityRules" element
     */
    public void setSchemeTypeCommunityRules(org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType schemeTypeCommunityRules)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMETYPECOMMUNITYRULES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMETYPECOMMUNITYRULES$0);
            }
            target.set(schemeTypeCommunityRules);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeTypeCommunityRules" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType addNewSchemeTypeCommunityRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMETYPECOMMUNITYRULES$0);
            return target;
        }
    }
}
