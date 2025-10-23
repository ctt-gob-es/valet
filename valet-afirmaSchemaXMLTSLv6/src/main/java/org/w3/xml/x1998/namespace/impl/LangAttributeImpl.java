/*
 * An XML attribute type.
 * Localname: lang
 * Namespace: http://www.w3.org/XML/1998/namespace
 * Java type: org.w3.xml.x1998.namespace.LangAttribute
 *
 * Automatically generated - do not modify.
 */
package org.w3.xml.x1998.namespace.impl;
/**
 * A document containing one lang(@http://www.w3.org/XML/1998/namespace) attribute.
 *
 * This is a complex type.
 */
public class LangAttributeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.xml.x1998.namespace.LangAttribute
{
    
    public LangAttributeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName LANG$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/XML/1998/namespace", "lang");
    
    
    /**
     * Gets the "lang" attribute
     */
    public java.lang.String getLang()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LANG$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "lang" attribute
     */
    public org.w3.xml.x1998.namespace.LangAttribute.Lang xgetLang()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.xml.x1998.namespace.LangAttribute.Lang target = null;
            target = (org.w3.xml.x1998.namespace.LangAttribute.Lang)get_store().find_attribute_user(LANG$0);
            return target;
        }
    }
    
    /**
     * True if has "lang" attribute
     */
    public boolean isSetLang()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(LANG$0) != null;
        }
    }
    
    /**
     * Sets the "lang" attribute
     */
    public void setLang(java.lang.String lang)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LANG$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(LANG$0);
            }
            target.setStringValue(lang);
        }
    }
    
    /**
     * Sets (as xml) the "lang" attribute
     */
    public void xsetLang(org.w3.xml.x1998.namespace.LangAttribute.Lang lang)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.xml.x1998.namespace.LangAttribute.Lang target = null;
            target = (org.w3.xml.x1998.namespace.LangAttribute.Lang)get_store().find_attribute_user(LANG$0);
            if (target == null)
            {
                target = (org.w3.xml.x1998.namespace.LangAttribute.Lang)get_store().add_attribute_user(LANG$0);
            }
            target.set(lang);
        }
    }
    
    /**
     * Unsets the "lang" attribute
     */
    public void unsetLang()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(LANG$0);
        }
    }
    /**
     * An XML lang(@http://www.w3.org/XML/1998/namespace).
     *
     * This is a union type. Instances are of one of the following types:
     *     org.apache.xmlbeans.XmlLanguage
     *     org.w3.xml.x1998.namespace.LangAttribute$Lang$Member
     */
    public static class LangImpl extends org.apache.xmlbeans.impl.values.XmlUnionImpl implements org.w3.xml.x1998.namespace.LangAttribute.Lang, org.apache.xmlbeans.XmlLanguage, org.w3.xml.x1998.namespace.LangAttribute.Lang.Member
    {
        
        public LangImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected LangImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
        /**
         * An anonymous inner XML type.
         *
         * This is an atomic type that is a restriction of org.w3.xml.x1998.namespace.LangAttribute$Lang$Member.
         */
        public static class MemberImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements org.w3.xml.x1998.namespace.LangAttribute.Lang.Member
        {
            
            public MemberImpl(org.apache.xmlbeans.SchemaType sType)
            {
                super(sType, false);
            }
            
            protected MemberImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
            {
                super(sType, b);
            }
        }
    }
}
