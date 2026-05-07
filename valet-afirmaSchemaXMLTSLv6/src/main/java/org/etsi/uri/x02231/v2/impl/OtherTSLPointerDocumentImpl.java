/*
 * An XML document type.
 * Localname: OtherTSLPointer
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.OtherTSLPointerDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one OtherTSLPointer(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class OtherTSLPointerDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.OtherTSLPointerDocument
{
    
    public OtherTSLPointerDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OTHERTSLPOINTER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "OtherTSLPointer");
    
    
    /**
     * Gets the "OtherTSLPointer" element
     */
    public org.etsi.uri.x02231.v2.OtherTSLPointerType getOtherTSLPointer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointerType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointerType)get_store().find_element_user(OTHERTSLPOINTER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "OtherTSLPointer" element
     */
    public void setOtherTSLPointer(org.etsi.uri.x02231.v2.OtherTSLPointerType otherTSLPointer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointerType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointerType)get_store().find_element_user(OTHERTSLPOINTER$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.OtherTSLPointerType)get_store().add_element_user(OTHERTSLPOINTER$0);
            }
            target.set(otherTSLPointer);
        }
    }
    
    /**
     * Appends and returns a new empty "OtherTSLPointer" element
     */
    public org.etsi.uri.x02231.v2.OtherTSLPointerType addNewOtherTSLPointer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.OtherTSLPointerType target = null;
            target = (org.etsi.uri.x02231.v2.OtherTSLPointerType)get_store().add_element_user(OTHERTSLPOINTER$0);
            return target;
        }
    }
}
