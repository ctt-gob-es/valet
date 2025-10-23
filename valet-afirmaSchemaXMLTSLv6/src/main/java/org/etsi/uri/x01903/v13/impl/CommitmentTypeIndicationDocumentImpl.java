/*
 * An XML document type.
 * Localname: CommitmentTypeIndication
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CommitmentTypeIndicationDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one CommitmentTypeIndication(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class CommitmentTypeIndicationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CommitmentTypeIndicationDocument
{
    
    public CommitmentTypeIndicationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMMITMENTTYPEINDICATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CommitmentTypeIndication");
    
    
    /**
     * Gets the "CommitmentTypeIndication" element
     */
    public org.etsi.uri.x01903.v13.CommitmentTypeIndicationType getCommitmentTypeIndication()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeIndicationType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType)get_store().find_element_user(COMMITMENTTYPEINDICATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CommitmentTypeIndication" element
     */
    public void setCommitmentTypeIndication(org.etsi.uri.x01903.v13.CommitmentTypeIndicationType commitmentTypeIndication)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeIndicationType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType)get_store().find_element_user(COMMITMENTTYPEINDICATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType)get_store().add_element_user(COMMITMENTTYPEINDICATION$0);
            }
            target.set(commitmentTypeIndication);
        }
    }
    
    /**
     * Appends and returns a new empty "CommitmentTypeIndication" element
     */
    public org.etsi.uri.x01903.v13.CommitmentTypeIndicationType addNewCommitmentTypeIndication()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeIndicationType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType)get_store().add_element_user(COMMITMENTTYPEINDICATION$0);
            return target;
        }
    }
}
