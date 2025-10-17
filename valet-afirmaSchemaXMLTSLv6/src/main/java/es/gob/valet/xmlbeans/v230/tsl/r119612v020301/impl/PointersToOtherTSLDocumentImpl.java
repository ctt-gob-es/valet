/*
 * An XML document type.
 * Localname: PointersToOtherTSL
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.PointersToOtherTSLDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one PointersToOtherTSL(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class PointersToOtherTSLDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PointersToOtherTSLDocument
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
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType getPointersToOtherTSL()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType)get_store().find_element_user(POINTERSTOOTHERTSL$0, 0);
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
    public void setPointersToOtherTSL(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType pointersToOtherTSL)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType)get_store().find_element_user(POINTERSTOOTHERTSL$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType)get_store().add_element_user(POINTERSTOOTHERTSL$0);
            }
            target.set(pointersToOtherTSL);
        }
    }
    
    /**
     * Appends and returns a new empty "PointersToOtherTSL" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType addNewPointersToOtherTSL()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType)get_store().add_element_user(POINTERSTOOTHERTSL$0);
            return target;
        }
    }
}
