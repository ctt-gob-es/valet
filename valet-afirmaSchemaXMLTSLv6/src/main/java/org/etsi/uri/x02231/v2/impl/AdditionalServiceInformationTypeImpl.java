/*
 * XML Type:  AdditionalServiceInformationType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.AdditionalServiceInformationType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML AdditionalServiceInformationType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class AdditionalServiceInformationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.AdditionalServiceInformationType
{
    
    public AdditionalServiceInformationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName URI$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "URI");
    private static final javax.xml.namespace.QName INFORMATIONVALUE$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "InformationValue");
    private static final javax.xml.namespace.QName OTHERINFORMATION$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "OtherInformation");
    
    
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
     * Gets the "InformationValue" element
     */
    public java.lang.String getInformationValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INFORMATIONVALUE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "InformationValue" element
     */
    public org.apache.xmlbeans.XmlString xgetInformationValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(INFORMATIONVALUE$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "InformationValue" element
     */
    public boolean isSetInformationValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(INFORMATIONVALUE$2) != 0;
        }
    }
    
    /**
     * Sets the "InformationValue" element
     */
    public void setInformationValue(java.lang.String informationValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INFORMATIONVALUE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(INFORMATIONVALUE$2);
            }
            target.setStringValue(informationValue);
        }
    }
    
    /**
     * Sets (as xml) the "InformationValue" element
     */
    public void xsetInformationValue(org.apache.xmlbeans.XmlString informationValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(INFORMATIONVALUE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(INFORMATIONVALUE$2);
            }
            target.set(informationValue);
        }
    }
    
    /**
     * Unsets the "InformationValue" element
     */
    public void unsetInformationValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(INFORMATIONVALUE$2, 0);
        }
    }
    
    /**
     * Gets the "OtherInformation" element
     */
    public org.etsi.uri.x02231.v2.AnyType getOtherInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().find_element_user(OTHERINFORMATION$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "OtherInformation" element
     */
    public boolean isSetOtherInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERINFORMATION$4) != 0;
        }
    }
    
    /**
     * Sets the "OtherInformation" element
     */
    public void setOtherInformation(org.etsi.uri.x02231.v2.AnyType otherInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().find_element_user(OTHERINFORMATION$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.AnyType)get_store().add_element_user(OTHERINFORMATION$4);
            }
            target.set(otherInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "OtherInformation" element
     */
    public org.etsi.uri.x02231.v2.AnyType addNewOtherInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().add_element_user(OTHERINFORMATION$4);
            return target;
        }
    }
    
    /**
     * Unsets the "OtherInformation" element
     */
    public void unsetOtherInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERINFORMATION$4, 0);
        }
    }
}
