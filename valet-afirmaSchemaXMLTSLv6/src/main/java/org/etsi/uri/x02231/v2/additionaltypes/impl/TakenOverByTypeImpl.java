/*
 * XML Type:  TakenOverByType
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.additionaltypes.impl;
/**
 * An XML TakenOverByType(@http://uri.etsi.org/02231/v2/additionaltypes#).
 *
 * This is a complex type.
 */
public class TakenOverByTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType
{
    
    public TakenOverByTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName URI$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "URI");
    private static final javax.xml.namespace.QName TSPNAME$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "TSPName");
    private static final javax.xml.namespace.QName SCHEMEOPERATORNAME$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeOperatorName");
    private static final javax.xml.namespace.QName SCHEMETERRITORY$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeTerritory");
    private static final javax.xml.namespace.QName OTHERQUALIFIER$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "OtherQualifier");
    
    
    /**
     * Gets the "URI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType getURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().find_element_user(URI$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "URI" element
     */
    public void setURI(org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().find_element_user(URI$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().add_element_user(URI$0);
            }
            target.set(uri);
        }
    }
    
    /**
     * Appends and returns a new empty "URI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType addNewURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType)get_store().add_element_user(URI$0);
            return target;
        }
    }
    
    /**
     * Gets the "TSPName" element
     */
    public org.etsi.uri.x02231.v2.InternationalNamesType getTSPName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(TSPNAME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPName" element
     */
    public void setTSPName(org.etsi.uri.x02231.v2.InternationalNamesType tspName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(TSPNAME$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(TSPNAME$2);
            }
            target.set(tspName);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPName" element
     */
    public org.etsi.uri.x02231.v2.InternationalNamesType addNewTSPName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(TSPNAME$2);
            return target;
        }
    }
    
    /**
     * Gets the "SchemeOperatorName" element
     */
    public org.etsi.uri.x02231.v2.InternationalNamesType getSchemeOperatorName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(SCHEMEOPERATORNAME$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeOperatorName" element
     */
    public void setSchemeOperatorName(org.etsi.uri.x02231.v2.InternationalNamesType schemeOperatorName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(SCHEMEOPERATORNAME$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(SCHEMEOPERATORNAME$4);
            }
            target.set(schemeOperatorName);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeOperatorName" element
     */
    public org.etsi.uri.x02231.v2.InternationalNamesType addNewSchemeOperatorName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(SCHEMEOPERATORNAME$4);
            return target;
        }
    }
    
    /**
     * Gets the "SchemeTerritory" element
     */
    public java.lang.String getSchemeTerritory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SCHEMETERRITORY$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "SchemeTerritory" element
     */
    public org.apache.xmlbeans.XmlString xgetSchemeTerritory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SCHEMETERRITORY$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "SchemeTerritory" element
     */
    public void setSchemeTerritory(java.lang.String schemeTerritory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SCHEMETERRITORY$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SCHEMETERRITORY$6);
            }
            target.setStringValue(schemeTerritory);
        }
    }
    
    /**
     * Sets (as xml) the "SchemeTerritory" element
     */
    public void xsetSchemeTerritory(org.apache.xmlbeans.XmlString schemeTerritory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SCHEMETERRITORY$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SCHEMETERRITORY$6);
            }
            target.set(schemeTerritory);
        }
    }
    
    /**
     * Gets array of all "OtherQualifier" elements
     */
    public org.etsi.uri.x02231.v2.AnyType[] getOtherQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OTHERQUALIFIER$8, targetList);
            org.etsi.uri.x02231.v2.AnyType[] result = new org.etsi.uri.x02231.v2.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "OtherQualifier" element
     */
    public org.etsi.uri.x02231.v2.AnyType getOtherQualifierArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().find_element_user(OTHERQUALIFIER$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "OtherQualifier" element
     */
    public int sizeOfOtherQualifierArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERQUALIFIER$8);
        }
    }
    
    /**
     * Sets array of all "OtherQualifier" element
     */
    public void setOtherQualifierArray(org.etsi.uri.x02231.v2.AnyType[] otherQualifierArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(otherQualifierArray, OTHERQUALIFIER$8);
        }
    }
    
    /**
     * Sets ith "OtherQualifier" element
     */
    public void setOtherQualifierArray(int i, org.etsi.uri.x02231.v2.AnyType otherQualifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().find_element_user(OTHERQUALIFIER$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(otherQualifier);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OtherQualifier" element
     */
    public org.etsi.uri.x02231.v2.AnyType insertNewOtherQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().insert_element_user(OTHERQUALIFIER$8, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OtherQualifier" element
     */
    public org.etsi.uri.x02231.v2.AnyType addNewOtherQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().add_element_user(OTHERQUALIFIER$8);
            return target;
        }
    }
    
    /**
     * Removes the ith "OtherQualifier" element
     */
    public void removeOtherQualifier(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERQUALIFIER$8, i);
        }
    }
}
