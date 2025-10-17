/*
 * An XML attribute type.
 * Localname: space
 * Namespace: http://www.w3.org/XML/1998/namespace
 * Java type: org.w3.xml.x1998.namespace.SpaceAttribute
 *
 * Automatically generated - do not modify.
 */
package org.w3.xml.x1998.namespace.impl;
/**
 * A document containing one space(@http://www.w3.org/XML/1998/namespace) attribute.
 *
 * This is a complex type.
 */
public class SpaceAttributeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.xml.x1998.namespace.SpaceAttribute
{
    
    public SpaceAttributeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SPACE$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/XML/1998/namespace", "space");
    
    
    /**
     * Gets the "space" attribute
     */
    public org.w3.xml.x1998.namespace.SpaceAttribute.Space.Enum getSpace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SPACE$0);
            if (target == null)
            {
                return null;
            }
            return (org.w3.xml.x1998.namespace.SpaceAttribute.Space.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "space" attribute
     */
    public org.w3.xml.x1998.namespace.SpaceAttribute.Space xgetSpace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.xml.x1998.namespace.SpaceAttribute.Space target = null;
            target = (org.w3.xml.x1998.namespace.SpaceAttribute.Space)get_store().find_attribute_user(SPACE$0);
            return target;
        }
    }
    
    /**
     * True if has "space" attribute
     */
    public boolean isSetSpace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(SPACE$0) != null;
        }
    }
    
    /**
     * Sets the "space" attribute
     */
    public void setSpace(org.w3.xml.x1998.namespace.SpaceAttribute.Space.Enum space)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SPACE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SPACE$0);
            }
            target.setEnumValue(space);
        }
    }
    
    /**
     * Sets (as xml) the "space" attribute
     */
    public void xsetSpace(org.w3.xml.x1998.namespace.SpaceAttribute.Space space)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.xml.x1998.namespace.SpaceAttribute.Space target = null;
            target = (org.w3.xml.x1998.namespace.SpaceAttribute.Space)get_store().find_attribute_user(SPACE$0);
            if (target == null)
            {
                target = (org.w3.xml.x1998.namespace.SpaceAttribute.Space)get_store().add_attribute_user(SPACE$0);
            }
            target.set(space);
        }
    }
    
    /**
     * Unsets the "space" attribute
     */
    public void unsetSpace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(SPACE$0);
        }
    }
    /**
     * An XML space(@http://www.w3.org/XML/1998/namespace).
     *
     * This is an atomic type that is a restriction of org.w3.xml.x1998.namespace.SpaceAttribute$Space.
     */
    public static class SpaceImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements org.w3.xml.x1998.namespace.SpaceAttribute.Space
    {
        
        public SpaceImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected SpaceImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
}
