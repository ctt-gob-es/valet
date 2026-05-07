/*
 * XML Type:  OCSPIdentifierType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.OCSPIdentifierType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML OCSPIdentifierType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class OCSPIdentifierTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.OCSPIdentifierType
{
    
    public OCSPIdentifierTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESPONDERID$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ResponderID");
    private static final javax.xml.namespace.QName PRODUCEDAT$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ProducedAt");
    private static final javax.xml.namespace.QName URI$4 = 
        new javax.xml.namespace.QName("", "URI");
    
    
    /**
     * Gets the "ResponderID" element
     */
    public org.etsi.uri.x01903.v13.ResponderIDType getResponderID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ResponderIDType target = null;
            target = (org.etsi.uri.x01903.v13.ResponderIDType)get_store().find_element_user(RESPONDERID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ResponderID" element
     */
    public void setResponderID(org.etsi.uri.x01903.v13.ResponderIDType responderID)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ResponderIDType target = null;
            target = (org.etsi.uri.x01903.v13.ResponderIDType)get_store().find_element_user(RESPONDERID$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.ResponderIDType)get_store().add_element_user(RESPONDERID$0);
            }
            target.set(responderID);
        }
    }
    
    /**
     * Appends and returns a new empty "ResponderID" element
     */
    public org.etsi.uri.x01903.v13.ResponderIDType addNewResponderID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ResponderIDType target = null;
            target = (org.etsi.uri.x01903.v13.ResponderIDType)get_store().add_element_user(RESPONDERID$0);
            return target;
        }
    }
    
    /**
     * Gets the "ProducedAt" element
     */
    public java.util.Calendar getProducedAt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PRODUCEDAT$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "ProducedAt" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetProducedAt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(PRODUCEDAT$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ProducedAt" element
     */
    public void setProducedAt(java.util.Calendar producedAt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PRODUCEDAT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PRODUCEDAT$2);
            }
            target.setCalendarValue(producedAt);
        }
    }
    
    /**
     * Sets (as xml) the "ProducedAt" element
     */
    public void xsetProducedAt(org.apache.xmlbeans.XmlDateTime producedAt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(PRODUCEDAT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(PRODUCEDAT$2);
            }
            target.set(producedAt);
        }
    }
    
    /**
     * Gets the "URI" attribute
     */
    public java.lang.String getURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(URI$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "URI" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(URI$4);
            return target;
        }
    }
    
    /**
     * True if has "URI" attribute
     */
    public boolean isSetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(URI$4) != null;
        }
    }
    
    /**
     * Sets the "URI" attribute
     */
    public void setURI(java.lang.String uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(URI$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(URI$4);
            }
            target.setStringValue(uri);
        }
    }
    
    /**
     * Sets (as xml) the "URI" attribute
     */
    public void xsetURI(org.apache.xmlbeans.XmlAnyURI uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(URI$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(URI$4);
            }
            target.set(uri);
        }
    }
    
    /**
     * Unsets the "URI" attribute
     */
    public void unsetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(URI$4);
        }
    }
}
