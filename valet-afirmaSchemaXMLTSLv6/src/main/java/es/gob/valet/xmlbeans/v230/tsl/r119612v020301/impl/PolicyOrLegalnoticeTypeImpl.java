/*
 * XML Type:  PolicyOrLegalnoticeType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.PolicyOrLegalnoticeType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML PolicyOrLegalnoticeType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class PolicyOrLegalnoticeTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType
{
    
    public PolicyOrLegalnoticeTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSLPOLICY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSLPolicy");
    private static final javax.xml.namespace.QName TSLLEGALNOTICE$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSLLegalNotice");
    
    
    /**
     * Gets array of all "TSLPolicy" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType[] getTSLPolicyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(TSLPOLICY$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "TSLPolicy" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType getTSLPolicyArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType)get_store().find_element_user(TSLPOLICY$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "TSLPolicy" element
     */
    public int sizeOfTSLPolicyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TSLPOLICY$0);
        }
    }
    
    /**
     * Sets array of all "TSLPolicy" element
     */
    public void setTSLPolicyArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType[] tslPolicyArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(tslPolicyArray, TSLPOLICY$0);
        }
    }
    
    /**
     * Sets ith "TSLPolicy" element
     */
    public void setTSLPolicyArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType tslPolicy)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType)get_store().find_element_user(TSLPOLICY$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(tslPolicy);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "TSLPolicy" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType insertNewTSLPolicy(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType)get_store().insert_element_user(TSLPOLICY$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TSLPolicy" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType addNewTSLPolicy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType)get_store().add_element_user(TSLPOLICY$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "TSLPolicy" element
     */
    public void removeTSLPolicy(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TSLPOLICY$0, i);
        }
    }
    
    /**
     * Gets array of all "TSLLegalNotice" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType[] getTSLLegalNoticeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(TSLLEGALNOTICE$2, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "TSLLegalNotice" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType getTSLLegalNoticeArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType)get_store().find_element_user(TSLLEGALNOTICE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "TSLLegalNotice" element
     */
    public int sizeOfTSLLegalNoticeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TSLLEGALNOTICE$2);
        }
    }
    
    /**
     * Sets array of all "TSLLegalNotice" element
     */
    public void setTSLLegalNoticeArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType[] tslLegalNoticeArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(tslLegalNoticeArray, TSLLEGALNOTICE$2);
        }
    }
    
    /**
     * Sets ith "TSLLegalNotice" element
     */
    public void setTSLLegalNoticeArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType tslLegalNotice)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType)get_store().find_element_user(TSLLEGALNOTICE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(tslLegalNotice);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "TSLLegalNotice" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType insertNewTSLLegalNotice(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType)get_store().insert_element_user(TSLLEGALNOTICE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TSLLegalNotice" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType addNewTSLLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType)get_store().add_element_user(TSLLEGALNOTICE$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "TSLLegalNotice" element
     */
    public void removeTSLLegalNotice(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TSLLEGALNOTICE$2, i);
        }
    }
}
