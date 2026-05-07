/*
 * XML Type:  PolicyOrLegalnoticeType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML PolicyOrLegalnoticeType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class PolicyOrLegalnoticeTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.PolicyOrLegalnoticeType
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
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType[] getTSLPolicyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(TSLPOLICY$0, targetList);
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType[] result = new org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "TSLPolicy" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType getTSLPolicyArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().find_element_user(TSLPOLICY$0, i);
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
    public void setTSLPolicyArray(org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType[] tslPolicyArray)
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
    public void setTSLPolicyArray(int i, org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType tslPolicy)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().find_element_user(TSLPOLICY$0, i);
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
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType insertNewTSLPolicy(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().insert_element_user(TSLPOLICY$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TSLPolicy" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType addNewTSLPolicy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().add_element_user(TSLPOLICY$0);
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
    public org.etsi.uri.x02231.v2.MultiLangStringType[] getTSLLegalNoticeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(TSLLEGALNOTICE$2, targetList);
            org.etsi.uri.x02231.v2.MultiLangStringType[] result = new org.etsi.uri.x02231.v2.MultiLangStringType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "TSLLegalNotice" element
     */
    public org.etsi.uri.x02231.v2.MultiLangStringType getTSLLegalNoticeArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangStringType)get_store().find_element_user(TSLLEGALNOTICE$2, i);
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
    public void setTSLLegalNoticeArray(org.etsi.uri.x02231.v2.MultiLangStringType[] tslLegalNoticeArray)
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
    public void setTSLLegalNoticeArray(int i, org.etsi.uri.x02231.v2.MultiLangStringType tslLegalNotice)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangStringType)get_store().find_element_user(TSLLEGALNOTICE$2, i);
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
    public org.etsi.uri.x02231.v2.MultiLangStringType insertNewTSLLegalNotice(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangStringType)get_store().insert_element_user(TSLLEGALNOTICE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TSLLegalNotice" element
     */
    public org.etsi.uri.x02231.v2.MultiLangStringType addNewTSLLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangStringType)get_store().add_element_user(TSLLEGALNOTICE$2);
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
