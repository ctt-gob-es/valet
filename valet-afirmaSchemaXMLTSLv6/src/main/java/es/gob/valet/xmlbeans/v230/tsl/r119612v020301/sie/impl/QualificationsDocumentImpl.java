/*
 * An XML document type.
 * Localname: Qualifications
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.QualificationsDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.impl;
/**
 * A document containing one Qualifications(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#) element.
 *
 * This is a complex type.
 */
public class QualificationsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsDocument
{
    
    public QualificationsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFICATIONS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "Qualifications");
    
    
    /**
     * Gets the "Qualifications" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType getQualifications()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType)get_store().find_element_user(QUALIFICATIONS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Qualifications" element
     */
    public void setQualifications(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType qualifications)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType)get_store().find_element_user(QUALIFICATIONS$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType)get_store().add_element_user(QUALIFICATIONS$0);
            }
            target.set(qualifications);
        }
    }
    
    /**
     * Appends and returns a new empty "Qualifications" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType addNewQualifications()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType)get_store().add_element_user(QUALIFICATIONS$0);
            return target;
        }
    }
}
