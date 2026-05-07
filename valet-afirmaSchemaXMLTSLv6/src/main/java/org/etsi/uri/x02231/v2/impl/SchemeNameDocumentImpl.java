/*
 * An XML document type.
 * Localname: SchemeName
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.SchemeNameDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one SchemeName(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeNameDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.SchemeNameDocument
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
    public org.etsi.uri.x02231.v2.InternationalNamesType getSchemeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(SCHEMENAME$0, 0);
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
    public void setSchemeName(org.etsi.uri.x02231.v2.InternationalNamesType schemeName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(SCHEMENAME$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(SCHEMENAME$0);
            }
            target.set(schemeName);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeName" element
     */
    public org.etsi.uri.x02231.v2.InternationalNamesType addNewSchemeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(SCHEMENAME$0);
            return target;
        }
    }
}
