/*
 * An XML document type.
 * Localname: SchemeOperatorName
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.SchemeOperatorNameDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one SchemeOperatorName(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class SchemeOperatorNameDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.SchemeOperatorNameDocument
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
    public org.etsi.uri.x02231.v2.InternationalNamesType getSchemeOperatorName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(SCHEMEOPERATORNAME$0, 0);
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
    public void setSchemeOperatorName(org.etsi.uri.x02231.v2.InternationalNamesType schemeOperatorName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(SCHEMEOPERATORNAME$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(SCHEMEOPERATORNAME$0);
            }
            target.set(schemeOperatorName);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeOperatorName" element
     */
    public org.etsi.uri.x02231.v2.InternationalNamesType addNewSchemeOperatorName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(SCHEMEOPERATORNAME$0);
            return target;
        }
    }
}
