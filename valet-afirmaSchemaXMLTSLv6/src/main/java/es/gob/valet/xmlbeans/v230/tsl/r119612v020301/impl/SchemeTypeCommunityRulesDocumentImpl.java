/*
 * An XML document type.
 * Localname: SchemeTypeCommunityRules
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.SchemeTypeCommunityRulesDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one SchemeTypeCommunityRules(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeTypeCommunityRulesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.SchemeTypeCommunityRulesDocument
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
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getSchemeTypeCommunityRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMETYPECOMMUNITYRULES$0, 0);
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
    public void setSchemeTypeCommunityRules(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType schemeTypeCommunityRules)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMETYPECOMMUNITYRULES$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMETYPECOMMUNITYRULES$0);
            }
            target.set(schemeTypeCommunityRules);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeTypeCommunityRules" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewSchemeTypeCommunityRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMETYPECOMMUNITYRULES$0);
            return target;
        }
    }
}
