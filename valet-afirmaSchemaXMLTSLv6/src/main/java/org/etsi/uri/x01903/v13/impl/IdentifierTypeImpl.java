/*
 * XML Type:  IdentifierType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.IdentifierType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML IdentifierType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is an atomic type that is a restriction of org.etsi.uri.x01903.v13.IdentifierType.
 */
public class IdentifierTypeImpl extends org.apache.xmlbeans.impl.values.JavaUriHolderEx implements org.etsi.uri.x01903.v13.IdentifierType
{
    
    public IdentifierTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType, true);
    }
    
    protected IdentifierTypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
    {
        super(sType, b);
    }
    
    private static final javax.xml.namespace.QName QUALIFIER$0 = 
        new javax.xml.namespace.QName("", "Qualifier");
    
    
    /**
     * Gets the "Qualifier" attribute
     */
    public org.etsi.uri.x01903.v13.QualifierType.Enum getQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(QUALIFIER$0);
            if (target == null)
            {
                return null;
            }
            return (org.etsi.uri.x01903.v13.QualifierType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Qualifier" attribute
     */
    public org.etsi.uri.x01903.v13.QualifierType xgetQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.QualifierType target = null;
            target = (org.etsi.uri.x01903.v13.QualifierType)get_store().find_attribute_user(QUALIFIER$0);
            return target;
        }
    }
    
    /**
     * True if has "Qualifier" attribute
     */
    public boolean isSetQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(QUALIFIER$0) != null;
        }
    }
    
    /**
     * Sets the "Qualifier" attribute
     */
    public void setQualifier(org.etsi.uri.x01903.v13.QualifierType.Enum qualifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(QUALIFIER$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(QUALIFIER$0);
            }
            target.setEnumValue(qualifier);
        }
    }
    
    /**
     * Sets (as xml) the "Qualifier" attribute
     */
    public void xsetQualifier(org.etsi.uri.x01903.v13.QualifierType qualifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.QualifierType target = null;
            target = (org.etsi.uri.x01903.v13.QualifierType)get_store().find_attribute_user(QUALIFIER$0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.QualifierType)get_store().add_attribute_user(QUALIFIER$0);
            }
            target.set(qualifier);
        }
    }
    
    /**
     * Unsets the "Qualifier" attribute
     */
    public void unsetQualifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(QUALIFIER$0);
        }
    }
}
