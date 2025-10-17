/*
 * An XML document type.
 * Localname: SchemeTerritory
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.SchemeTerritoryDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one SchemeTerritory(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeTerritoryDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.SchemeTerritoryDocument
{
    
    public SchemeTerritoryDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMETERRITORY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeTerritory");
    
    
    /**
     * Gets the "SchemeTerritory" element
     */
    public java.lang.String getSchemeTerritory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SCHEMETERRITORY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "SchemeTerritory" element
     */
    public org.apache.xmlbeans.XmlString xgetSchemeTerritory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SCHEMETERRITORY$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "SchemeTerritory" element
     */
    public void setSchemeTerritory(java.lang.String schemeTerritory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SCHEMETERRITORY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SCHEMETERRITORY$0);
            }
            target.setStringValue(schemeTerritory);
        }
    }
    
    /**
     * Sets (as xml) the "SchemeTerritory" element
     */
    public void xsetSchemeTerritory(org.apache.xmlbeans.XmlString schemeTerritory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SCHEMETERRITORY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SCHEMETERRITORY$0);
            }
            target.set(schemeTerritory);
        }
    }
}
