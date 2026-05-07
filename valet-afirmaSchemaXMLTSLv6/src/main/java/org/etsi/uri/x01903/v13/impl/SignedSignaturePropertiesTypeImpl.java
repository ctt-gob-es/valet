/*
 * XML Type:  SignedSignaturePropertiesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;

import org.apache.xmlbeans.XmlObject;

/**
 * An XML SignedSignaturePropertiesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SignedSignaturePropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
{
    
    public SignedSignaturePropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNINGTIME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigningTime");
    private static final javax.xml.namespace.QName SIGNINGCERTIFICATE$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigningCertificate");
    private static final javax.xml.namespace.QName SIGNINGCERTIFICATEV2$2 = 
    		new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigningCertificateV2");
    private static final javax.xml.namespace.QName SIGNATUREPOLICYIDENTIFIER$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignaturePolicyIdentifier");
    private static final javax.xml.namespace.QName SIGNATUREPRODUCTIONPLACE$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignatureProductionPlace");
    private static final javax.xml.namespace.QName SIGNERROLE$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignerRole");
    private static final javax.xml.namespace.QName ID$10 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets the "SigningTime" element
     */
    public java.util.Calendar getSigningTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SIGNINGTIME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "SigningTime" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetSigningTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(SIGNINGTIME$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "SigningTime" element
     */
    public boolean isSetSigningTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNINGTIME$0) != 0;
        }
    }
    
    /**
     * Sets the "SigningTime" element
     */
    public void setSigningTime(java.util.Calendar signingTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SIGNINGTIME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SIGNINGTIME$0);
            }
            target.setCalendarValue(signingTime);
        }
    }
    
    /**
     * Sets (as xml) the "SigningTime" element
     */
    public void xsetSigningTime(org.apache.xmlbeans.XmlDateTime signingTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(SIGNINGTIME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(SIGNINGTIME$0);
            }
            target.set(signingTime);
        }
    }
    
    /**
     * Unsets the "SigningTime" element
     */
    public void unsetSigningTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNINGTIME$0, 0);
        }
    }
    
    /**
     * Gets the "SigningCertificate" element
     */
    public org.etsi.uri.x01903.v13.CertIDListType getSigningCertificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDListType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDListType)get_store().find_element_user(SIGNINGCERTIFICATE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }

    /**
     * Gets the "SigningCertificateV2" element
     */
    public org.etsi.uri.x01903.v13.CertIDListType getSigningCertificateV2() {
        synchronized (monitor()) {
            check_orphaned();
            Object obj = get_store().find_element_user(SIGNINGCERTIFICATEV2$2, 0);
            if (obj == null) {
                return null;
            }

            org.etsi.uri.x01903.v13.CertIDListType certList = org.etsi.uri.x01903.v13.CertIDListType.Factory.newInstance();

            try {
                if (obj instanceof org.apache.xmlbeans.impl.values.XmlAnyTypeImpl) {
                    org.apache.xmlbeans.impl.values.XmlAnyTypeImpl any = (org.apache.xmlbeans.impl.values.XmlAnyTypeImpl) obj;

                    org.w3c.dom.NodeList certNodes = any.getDomNode().getChildNodes();
                    for (int i = 0; i < certNodes.getLength(); i++) {
                        org.w3c.dom.Node certNode = certNodes.item(i);

                        if (certNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE &&
                            "Cert".equals(certNode.getLocalName()) &&
                            "http://uri.etsi.org/01903/v1.3.2#".equals(certNode.getNamespaceURI())) {

                            // Crear un nuevo CertIDType en el CertIDListType
                            org.etsi.uri.x01903.v13.CertIDType certId = certList.addNewCert();

                            // Copiar el contenido del nodo <Cert> sin desconectar XMLBeans
                            certId.set(org.apache.xmlbeans.XmlObject.Factory.parse(certNode));
                        }
                    }

                    // Si no había ningún <Cert>, devolvemos null
                    if (certList.sizeOfCertArray() == 0) {
                        return null;
                    }

                    return certList;
                }

                // Si ya es del tipo esperado
                if (obj instanceof org.etsi.uri.x01903.v13.CertIDListType) {
                    return (org.etsi.uri.x01903.v13.CertIDListType) obj;
                }

            } catch (Exception e) {
                // En caso de fallo, devolvemos null
                return null;
            }

            return null;
        }
    }
    
    /**
     * True if has "SigningCertificate" element
     */
    public boolean isSetSigningCertificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNINGCERTIFICATE$2) != 0;
        }
    }
    
    /**
     * Sets the "SigningCertificate" element
     */
    public void setSigningCertificate(org.etsi.uri.x01903.v13.CertIDListType signingCertificate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDListType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDListType)get_store().find_element_user(SIGNINGCERTIFICATE$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CertIDListType)get_store().add_element_user(SIGNINGCERTIFICATE$2);
            }
            target.set(signingCertificate);
        }
    }
    
    /**
     * Appends and returns a new empty "SigningCertificate" element
     */
    public org.etsi.uri.x01903.v13.CertIDListType addNewSigningCertificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDListType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDListType)get_store().add_element_user(SIGNINGCERTIFICATE$2);
            return target;
        }
    }
    
    /**
     * Unsets the "SigningCertificate" element
     */
    public void unsetSigningCertificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNINGCERTIFICATE$2, 0);
        }
    }
    
    /**
     * Gets the "SignaturePolicyIdentifier" element
     */
    public org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType getSignaturePolicyIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType)get_store().find_element_user(SIGNATUREPOLICYIDENTIFIER$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SignaturePolicyIdentifier" element
     */
    public boolean isSetSignaturePolicyIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNATUREPOLICYIDENTIFIER$4) != 0;
        }
    }
    
    /**
     * Sets the "SignaturePolicyIdentifier" element
     */
    public void setSignaturePolicyIdentifier(org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType signaturePolicyIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType)get_store().find_element_user(SIGNATUREPOLICYIDENTIFIER$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType)get_store().add_element_user(SIGNATUREPOLICYIDENTIFIER$4);
            }
            target.set(signaturePolicyIdentifier);
        }
    }
    
    /**
     * Appends and returns a new empty "SignaturePolicyIdentifier" element
     */
    public org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType addNewSignaturePolicyIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType)get_store().add_element_user(SIGNATUREPOLICYIDENTIFIER$4);
            return target;
        }
    }
    
    /**
     * Unsets the "SignaturePolicyIdentifier" element
     */
    public void unsetSignaturePolicyIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNATUREPOLICYIDENTIFIER$4, 0);
        }
    }
    
    /**
     * Gets the "SignatureProductionPlace" element
     */
    public org.etsi.uri.x01903.v13.SignatureProductionPlaceType getSignatureProductionPlace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignatureProductionPlaceType target = null;
            target = (org.etsi.uri.x01903.v13.SignatureProductionPlaceType)get_store().find_element_user(SIGNATUREPRODUCTIONPLACE$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SignatureProductionPlace" element
     */
    public boolean isSetSignatureProductionPlace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNATUREPRODUCTIONPLACE$6) != 0;
        }
    }
    
    /**
     * Sets the "SignatureProductionPlace" element
     */
    public void setSignatureProductionPlace(org.etsi.uri.x01903.v13.SignatureProductionPlaceType signatureProductionPlace)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignatureProductionPlaceType target = null;
            target = (org.etsi.uri.x01903.v13.SignatureProductionPlaceType)get_store().find_element_user(SIGNATUREPRODUCTIONPLACE$6, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignatureProductionPlaceType)get_store().add_element_user(SIGNATUREPRODUCTIONPLACE$6);
            }
            target.set(signatureProductionPlace);
        }
    }
    
    /**
     * Appends and returns a new empty "SignatureProductionPlace" element
     */
    public org.etsi.uri.x01903.v13.SignatureProductionPlaceType addNewSignatureProductionPlace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignatureProductionPlaceType target = null;
            target = (org.etsi.uri.x01903.v13.SignatureProductionPlaceType)get_store().add_element_user(SIGNATUREPRODUCTIONPLACE$6);
            return target;
        }
    }
    
    /**
     * Unsets the "SignatureProductionPlace" element
     */
    public void unsetSignatureProductionPlace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNATUREPRODUCTIONPLACE$6, 0);
        }
    }
    
    /**
     * Gets the "SignerRole" element
     */
    public org.etsi.uri.x01903.v13.SignerRoleType getSignerRole()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignerRoleType target = null;
            target = (org.etsi.uri.x01903.v13.SignerRoleType)get_store().find_element_user(SIGNERROLE$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SignerRole" element
     */
    public boolean isSetSignerRole()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNERROLE$8) != 0;
        }
    }
    
    /**
     * Sets the "SignerRole" element
     */
    public void setSignerRole(org.etsi.uri.x01903.v13.SignerRoleType signerRole)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignerRoleType target = null;
            target = (org.etsi.uri.x01903.v13.SignerRoleType)get_store().find_element_user(SIGNERROLE$8, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignerRoleType)get_store().add_element_user(SIGNERROLE$8);
            }
            target.set(signerRole);
        }
    }
    
    /**
     * Appends and returns a new empty "SignerRole" element
     */
    public org.etsi.uri.x01903.v13.SignerRoleType addNewSignerRole()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignerRoleType target = null;
            target = (org.etsi.uri.x01903.v13.SignerRoleType)get_store().add_element_user(SIGNERROLE$8);
            return target;
        }
    }
    
    /**
     * Unsets the "SignerRole" element
     */
    public void unsetSignerRole()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNERROLE$8, 0);
        }
    }
    
    /**
     * Gets the "Id" attribute
     */
    public java.lang.String getId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$10);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Id" attribute
     */
    public org.apache.xmlbeans.XmlID xgetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$10);
            return target;
        }
    }
    
    /**
     * True if has "Id" attribute
     */
    public boolean isSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ID$10) != null;
        }
    }
    
    /**
     * Sets the "Id" attribute
     */
    public void setId(java.lang.String id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$10);
            }
            target.setStringValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "Id" attribute
     */
    public void xsetId(org.apache.xmlbeans.XmlID id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$10);
            }
            target.set(id);
        }
    }
    
    /**
     * Unsets the "Id" attribute
     */
    public void unsetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ID$10);
        }
    }
}
