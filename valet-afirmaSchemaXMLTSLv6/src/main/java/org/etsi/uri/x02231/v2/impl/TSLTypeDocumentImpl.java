/*
 * An XML document type.
 * Localname: TSLType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TSLTypeDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one TSLType(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TSLTypeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TSLTypeDocument
{
    
    public TSLTypeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSLTYPE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSLType");
    
    
    /**
     * Gets the "TSLType" element
     */
    public java.lang.String getTSLType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLTYPE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TSLType" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType xgetTSLType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(TSLTYPE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "TSLType" element
     */
    public void setTSLType(java.lang.String tslType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLTYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TSLTYPE$0);
            }
            target.setStringValue(tslType);
        }
    }
    
    /**
     * Sets (as xml) the "TSLType" element
     */
    public void xsetTSLType(org.etsi.uri.x02231.v2.NonEmptyURIType tslType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(TSLTYPE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().add_element_user(TSLTYPE$0);
            }
            target.set(tslType);
        }
    }
}
