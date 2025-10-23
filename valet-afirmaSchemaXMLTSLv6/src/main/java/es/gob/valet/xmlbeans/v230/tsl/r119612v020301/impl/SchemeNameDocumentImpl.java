/*
 * An XML document type.
 * Localname: SchemeName
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.SchemeNameDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one SchemeName(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeNameDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.SchemeNameDocument
{
    
    public SchemeNameDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMENAME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeName");
    
    
    /**
     * Gets the "SchemeName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getSchemeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(SCHEMENAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeName" element
     */
    public void setSchemeName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType schemeName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(SCHEMENAME$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(SCHEMENAME$0);
            }
            target.set(schemeName);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewSchemeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(SCHEMENAME$0);
            return target;
        }
    }
}
