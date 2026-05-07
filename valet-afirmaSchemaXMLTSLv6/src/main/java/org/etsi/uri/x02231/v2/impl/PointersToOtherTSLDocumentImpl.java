/*
 * An XML document type.
 * Localname: PointersToOtherTSL
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.PointersToOtherTSLDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one PointersToOtherTSL(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class PointersToOtherTSLDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.PointersToOtherTSLDocument
{
    
    public PointersToOtherTSLDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POINTERSTOOTHERTSL$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PointersToOtherTSL");
    
    
    /**
     * Gets the "PointersToOtherTSL" element
     */
    public org.etsi.uri.x02231.v2.OtherTSLPointersType getPointersToOtherTSL()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointersType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointersType)get_store().find_element_user(POINTERSTOOTHERTSL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PointersToOtherTSL" element
     */
    public void setPointersToOtherTSL(org.etsi.uri.x02231.v2.OtherTSLPointersType pointersToOtherTSL)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointersType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointersType)get_store().find_element_user(POINTERSTOOTHERTSL$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.OtherTSLPointersType)get_store().add_element_user(POINTERSTOOTHERTSL$0);
            }
            target.set(pointersToOtherTSL);
        }
    }
    
    /**
     * Appends and returns a new empty "PointersToOtherTSL" element
     */
    public org.etsi.uri.x02231.v2.OtherTSLPointersType addNewPointersToOtherTSL()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointersType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointersType)get_store().add_element_user(POINTERSTOOTHERTSL$0);
            return target;
        }
    }
}
