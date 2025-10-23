/*
 * XML Type:  NonEmptyMultiLangURIType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.NonEmptyMultiLangURIType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML NonEmptyMultiLangURIType(@http://uri.etsi.org/02231/v2#).
 *
 * This is an atomic type that is a restriction of es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.NonEmptyMultiLangURIType.
 */
public class NonEmptyMultiLangURITypeImpl extends org.apache.xmlbeans.impl.values.JavaUriHolderEx implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType
{
    
    public NonEmptyMultiLangURITypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType, true);
    }
    
    protected NonEmptyMultiLangURITypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
    {
        super(sType, b);
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
}
