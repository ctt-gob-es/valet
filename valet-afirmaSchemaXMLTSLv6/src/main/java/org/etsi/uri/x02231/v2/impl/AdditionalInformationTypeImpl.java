/*
 * XML Type:  AdditionalInformationType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.AdditionalInformationType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML AdditionalInformationType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class AdditionalInformationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.AdditionalInformationType
{
    
    public AdditionalInformationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TEXTUALINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TextualInformation");
    private static final javax.xml.namespace.QName OTHERINFORMATION$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "OtherInformation");
    
    
    /**
     * Gets array of all "TextualInformation" elements
     */
    public org.etsi.uri.x02231.v2.MultiLangStringType[] getTextualInformationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(TEXTUALINFORMATION$0, targetList);
            org.etsi.uri.x02231.v2.MultiLangStringType[] result = new org.etsi.uri.x02231.v2.MultiLangStringType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "TextualInformation" element
     */
    public org.etsi.uri.x02231.v2.MultiLangStringType getTextualInformationArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangStringType)get_store().find_element_user(TEXTUALINFORMATION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "TextualInformation" element
     */
    public int sizeOfTextualInformationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TEXTUALINFORMATION$0);
        }
    }
    
    /**
     * Sets array of all "TextualInformation" element
     */
    public void setTextualInformationArray(org.etsi.uri.x02231.v2.MultiLangStringType[] textualInformationArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(textualInformationArray, TEXTUALINFORMATION$0);
        }
    }
    
    /**
     * Sets ith "TextualInformation" element
     */
    public void setTextualInformationArray(int i, org.etsi.uri.x02231.v2.MultiLangStringType textualInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangStringType)get_store().find_element_user(TEXTUALINFORMATION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(textualInformation);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "TextualInformation" element
     */
    public org.etsi.uri.x02231.v2.MultiLangStringType insertNewTextualInformation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangStringType)get_store().insert_element_user(TEXTUALINFORMATION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TextualInformation" element
     */
    public org.etsi.uri.x02231.v2.MultiLangStringType addNewTextualInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.MultiLangStringType target = null;
            target = (org.etsi.uri.x02231.v2.MultiLangStringType)get_store().add_element_user(TEXTUALINFORMATION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "TextualInformation" element
     */
    public void removeTextualInformation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TEXTUALINFORMATION$0, i);
        }
    }
    
    /**
     * Gets array of all "OtherInformation" elements
     */
    public org.etsi.uri.x02231.v2.AnyType[] getOtherInformationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OTHERINFORMATION$2, targetList);
            org.etsi.uri.x02231.v2.AnyType[] result = new org.etsi.uri.x02231.v2.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "OtherInformation" element
     */
    public org.etsi.uri.x02231.v2.AnyType getOtherInformationArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().find_element_user(OTHERINFORMATION$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "OtherInformation" element
     */
    public int sizeOfOtherInformationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERINFORMATION$2);
        }
    }
    
    /**
     * Sets array of all "OtherInformation" element
     */
    public void setOtherInformationArray(org.etsi.uri.x02231.v2.AnyType[] otherInformationArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(otherInformationArray, OTHERINFORMATION$2);
        }
    }
    
    /**
     * Sets ith "OtherInformation" element
     */
    public void setOtherInformationArray(int i, org.etsi.uri.x02231.v2.AnyType otherInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().find_element_user(OTHERINFORMATION$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(otherInformation);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OtherInformation" element
     */
    public org.etsi.uri.x02231.v2.AnyType insertNewOtherInformation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().insert_element_user(OTHERINFORMATION$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OtherInformation" element
     */
    public org.etsi.uri.x02231.v2.AnyType addNewOtherInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().add_element_user(OTHERINFORMATION$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "OtherInformation" element
     */
    public void removeOtherInformation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERINFORMATION$2, i);
        }
    }
}
