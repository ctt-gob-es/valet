/*
 * An XML document type.
 * Localname: X509CertificateLocation
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: org.etsi.uri.x02231.v2.additionaltypes.X509CertificateLocationDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.additionaltypes.impl;
/**
 * A document containing one X509CertificateLocation(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class X509CertificateLocationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.additionaltypes.X509CertificateLocationDocument
{
    
    public X509CertificateLocationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName X509CERTIFICATELOCATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "X509CertificateLocation");
    
    
    /**
     * Gets the "X509CertificateLocation" element
     */
    public java.lang.String getX509CertificateLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(X509CERTIFICATELOCATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "X509CertificateLocation" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType xgetX509CertificateLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(X509CERTIFICATELOCATION$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "X509CertificateLocation" element
     */
    public void setX509CertificateLocation(java.lang.String x509CertificateLocation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(X509CERTIFICATELOCATION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(X509CERTIFICATELOCATION$0);
            }
            target.setStringValue(x509CertificateLocation);
        }
    }
    
    /**
     * Sets (as xml) the "X509CertificateLocation" element
     */
    public void xsetX509CertificateLocation(org.etsi.uri.x02231.v2.NonEmptyURIType x509CertificateLocation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(X509CERTIFICATELOCATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().add_element_user(X509CERTIFICATELOCATION$0);
            }
            target.set(x509CertificateLocation);
        }
    }
}
