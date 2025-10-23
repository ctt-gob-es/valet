/*
 * XML Type:  NextUpdateType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.NextUpdateType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML NextUpdateType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class NextUpdateTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType
{
    
    public NextUpdateTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DATETIME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "dateTime");
    
    
    /**
     * Gets the "dateTime" element
     */
    public java.util.Calendar getDateTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATETIME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "dateTime" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetDateTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(DATETIME$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "dateTime" element
     */
    public boolean isSetDateTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DATETIME$0) != 0;
        }
    }
    
    /**
     * Sets the "dateTime" element
     */
    public void setDateTime(java.util.Calendar dateTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATETIME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DATETIME$0);
            }
            target.setCalendarValue(dateTime);
        }
    }
    
    /**
     * Sets (as xml) the "dateTime" element
     */
    public void xsetDateTime(org.apache.xmlbeans.XmlDateTime dateTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(DATETIME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(DATETIME$0);
            }
            target.set(dateTime);
        }
    }
    
    /**
     * Unsets the "dateTime" element
     */
    public void unsetDateTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DATETIME$0, 0);
        }
    }
}
