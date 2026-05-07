/*
 * An XML document type.
 * Localname: Qualifications
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.impl;
/**
 * A document containing one Qualifications(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#) element.
 *
 * This is a complex type.
 */
public class QualificationsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument
{
    
    public QualificationsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFICATIONS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "Qualifications");
    
    
    /**
     * Gets the "Qualifications" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType getQualifications()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType)get_store().find_element_user(QUALIFICATIONS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Qualifications" element
     */
    public void setQualifications(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType qualifications)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType)get_store().find_element_user(QUALIFICATIONS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType)get_store().add_element_user(QUALIFICATIONS$0);
            }
            target.set(qualifications);
        }
    }
    
    /**
     * Appends and returns a new empty "Qualifications" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType addNewQualifications()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType)get_store().add_element_user(QUALIFICATIONS$0);
            return target;
        }
    }
}
