/*
 * An XML document type.
 * Localname: OtherTSLPointer
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.OtherTSLPointerDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one OtherTSLPointer(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class OtherTSLPointerDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerDocument
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
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType getOtherTSLPointer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType)get_store().find_element_user(OTHERTSLPOINTER$0, 0);
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
    public void setOtherTSLPointer(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType otherTSLPointer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType)get_store().find_element_user(OTHERTSLPOINTER$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType)get_store().add_element_user(OTHERTSLPOINTER$0);
            }
            target.set(otherTSLPointer);
        }
    }
    
    /**
     * Appends and returns a new empty "OtherTSLPointer" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType addNewOtherTSLPointer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointerType)get_store().add_element_user(OTHERTSLPOINTER$0);
            return target;
        }
    }
}
