/*
 * XML Type:  QualifiersType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.QualifiersType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.impl;
/**
 * An XML QualifiersType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class QualifiersTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifiersType
{
    
    public QualifiersTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "Qualifier");
    
    
    /**
     * Gets array of all "Qualifier" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType[] getQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(QUALIFIER$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Qualifier" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType getQualifierArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType)get_store().find_element_user(QUALIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Qualifier" element
     */
    public int sizeOfQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(QUALIFIER$0);
        }
    }
    
    /**
     * Sets array of all "Qualifier" element
     */
    public void setQualifierArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType[] qualifierArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(qualifierArray, QUALIFIER$0);
        }
    }
    
    /**
     * Sets ith "Qualifier" element
     */
    public void setQualifierArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType qualifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType)get_store().find_element_user(QUALIFIER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(qualifier);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Qualifier" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType insertNewQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType)get_store().insert_element_user(QUALIFIER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Qualifier" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType addNewQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.QualifierType)get_store().add_element_user(QUALIFIER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Qualifier" element
     */
    public void removeQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(QUALIFIER$0, i);
        }
    }
}
