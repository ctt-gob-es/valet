/*
 * XML Type:  QualificationsType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.QualificationsType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.impl;
/**
 * An XML QualificationsType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class QualificationsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationsType
{
    
    public QualificationsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFICATIONELEMENT$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "QualificationElement");
    
    
    /**
     * Gets array of all "QualificationElement" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType[] getQualificationElementArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(QUALIFICATIONELEMENT$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "QualificationElement" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType getQualificationElementArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType)get_store().find_element_user(QUALIFICATIONELEMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "QualificationElement" element
     */
    public int sizeOfQualificationElementArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(QUALIFICATIONELEMENT$0);
        }
    }
    
    /**
     * Sets array of all "QualificationElement" element
     */
    public void setQualificationElementArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType[] qualificationElementArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(qualificationElementArray, QUALIFICATIONELEMENT$0);
        }
    }
    
    /**
     * Sets ith "QualificationElement" element
     */
    public void setQualificationElementArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType qualificationElement)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType)get_store().find_element_user(QUALIFICATIONELEMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(qualificationElement);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "QualificationElement" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType insertNewQualificationElement(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType)get_store().insert_element_user(QUALIFICATIONELEMENT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "QualificationElement" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType addNewQualificationElement()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualificationElementType)get_store().add_element_user(QUALIFICATIONELEMENT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "QualificationElement" element
     */
    public void removeQualificationElement(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(QUALIFICATIONELEMENT$0, i);
        }
    }
}
