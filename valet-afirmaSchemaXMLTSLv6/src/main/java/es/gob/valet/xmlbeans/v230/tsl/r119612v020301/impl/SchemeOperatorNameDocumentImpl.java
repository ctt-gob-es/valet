/*
 * An XML document type.
 * Localname: SchemeOperatorName
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.SchemeOperatorNameDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one SchemeOperatorName(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeOperatorNameDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.SchemeOperatorNameDocument
{
    
    public SchemeOperatorNameDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMEOPERATORNAME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeOperatorName");
    
    
    /**
     * Gets the "SchemeOperatorName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getSchemeOperatorName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(SCHEMEOPERATORNAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeOperatorName" element
     */
    public void setSchemeOperatorName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType schemeOperatorName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(SCHEMEOPERATORNAME$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(SCHEMEOPERATORNAME$0);
            }
            target.set(schemeOperatorName);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeOperatorName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewSchemeOperatorName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(SCHEMEOPERATORNAME$0);
            return target;
        }
    }
}
