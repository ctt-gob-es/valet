/*
 * An XML document type.
 * Localname: TakenOverBy
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.additionalTypes.TakenOverByDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.impl;
/**
 * A document containing one TakenOverBy(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class TakenOverByDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByDocument
{
    
    public TakenOverByDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TAKENOVERBY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "TakenOverBy");
    
    
    /**
     * Gets the "TakenOverBy" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType getTakenOverBy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType)get_store().find_element_user(TAKENOVERBY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TakenOverBy" element
     */
    public void setTakenOverBy(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType takenOverBy)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType)get_store().find_element_user(TAKENOVERBY$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType)get_store().add_element_user(TAKENOVERBY$0);
            }
            target.set(takenOverBy);
        }
    }
    
    /**
     * Appends and returns a new empty "TakenOverBy" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType addNewTakenOverBy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType)get_store().add_element_user(TAKENOVERBY$0);
            return target;
        }
    }
}
