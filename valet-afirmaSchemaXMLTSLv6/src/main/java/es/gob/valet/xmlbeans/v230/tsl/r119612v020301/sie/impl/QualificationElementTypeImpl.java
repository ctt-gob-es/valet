/*
 * XML Type:  QualificationElementType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.QualificationElementType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.impl;
/**
 * An XML QualificationElementType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class QualificationElementTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType
{
    
    public QualificationElementTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFIERS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "Qualifiers");
    private static final javax.xml.namespace.QName CRITERIALIST$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "CriteriaList");
    
    
    /**
     * Gets the "Qualifiers" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType getQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType)get_store().find_element_user(QUALIFIERS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Qualifiers" element
     */
    public void setQualifiers(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType qualifiers)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType)get_store().find_element_user(QUALIFIERS$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType)get_store().add_element_user(QUALIFIERS$0);
            }
            target.set(qualifiers);
        }
    }
    
    /**
     * Appends and returns a new empty "Qualifiers" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType addNewQualifiers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType)get_store().add_element_user(QUALIFIERS$0);
            return target;
        }
    }
    
    /**
     * Gets the "CriteriaList" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType getCriteriaList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType)get_store().find_element_user(CRITERIALIST$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CriteriaList" element
     */
    public void setCriteriaList(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType criteriaList)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType)get_store().find_element_user(CRITERIALIST$2, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType)get_store().add_element_user(CRITERIALIST$2);
            }
            target.set(criteriaList);
        }
    }
    
    /**
     * Appends and returns a new empty "CriteriaList" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType addNewCriteriaList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType)get_store().add_element_user(CRITERIALIST$2);
            return target;
        }
    }
}
