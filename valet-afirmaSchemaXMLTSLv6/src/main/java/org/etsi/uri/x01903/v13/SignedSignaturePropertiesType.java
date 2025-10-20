/*
 * XML Type:  SignedSignaturePropertiesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13;


/**
 * An XML SignedSignaturePropertiesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public interface SignedSignaturePropertiesType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SignedSignaturePropertiesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("signedsignaturepropertiestype06abtype");
    
    /**
     * Gets the "SigningTime" element
     */
    java.util.Calendar getSigningTime();
    
    /**
     * Gets (as xml) the "SigningTime" element
     */
    org.apache.xmlbeans.XmlDateTime xgetSigningTime();
    
    /**
     * True if has "SigningTime" element
     */
    boolean isSetSigningTime();
    
    /**
     * Sets the "SigningTime" element
     */
    void setSigningTime(java.util.Calendar signingTime);
    
    /**
     * Sets (as xml) the "SigningTime" element
     */
    void xsetSigningTime(org.apache.xmlbeans.XmlDateTime signingTime);
    
    /**
     * Unsets the "SigningTime" element
     */
    void unsetSigningTime();
    
    /**
     * Gets the "SigningCertificate" element
     */
    org.etsi.uri.x01903.v13.CertIDListType getSigningCertificate();
    
    /**
     * True if has "SigningCertificate" element
     */
    boolean isSetSigningCertificate();
    
    /**
     * Sets the "SigningCertificate" element
     */
    void setSigningCertificate(org.etsi.uri.x01903.v13.CertIDListType signingCertificate);
    
    /**
     * Appends and returns a new empty "SigningCertificate" element
     */
    org.etsi.uri.x01903.v13.CertIDListType addNewSigningCertificate();
    
    /**
     * Unsets the "SigningCertificate" element
     */
    void unsetSigningCertificate();
    
    /**
     * Gets the "SignaturePolicyIdentifier" element
     */
    org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType getSignaturePolicyIdentifier();
    
    /**
     * True if has "SignaturePolicyIdentifier" element
     */
    boolean isSetSignaturePolicyIdentifier();
    
    /**
     * Sets the "SignaturePolicyIdentifier" element
     */
    void setSignaturePolicyIdentifier(org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType signaturePolicyIdentifier);
    
    /**
     * Appends and returns a new empty "SignaturePolicyIdentifier" element
     */
    org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType addNewSignaturePolicyIdentifier();
    
    /**
     * Unsets the "SignaturePolicyIdentifier" element
     */
    void unsetSignaturePolicyIdentifier();
    
    /**
     * Gets the "SignatureProductionPlace" element
     */
    org.etsi.uri.x01903.v13.SignatureProductionPlaceType getSignatureProductionPlace();
    
    /**
     * True if has "SignatureProductionPlace" element
     */
    boolean isSetSignatureProductionPlace();
    
    /**
     * Sets the "SignatureProductionPlace" element
     */
    void setSignatureProductionPlace(org.etsi.uri.x01903.v13.SignatureProductionPlaceType signatureProductionPlace);
    
    /**
     * Appends and returns a new empty "SignatureProductionPlace" element
     */
    org.etsi.uri.x01903.v13.SignatureProductionPlaceType addNewSignatureProductionPlace();
    
    /**
     * Unsets the "SignatureProductionPlace" element
     */
    void unsetSignatureProductionPlace();
    
    /**
     * Gets the "SignerRole" element
     */
    org.etsi.uri.x01903.v13.SignerRoleType getSignerRole();
    
    /**
     * True if has "SignerRole" element
     */
    boolean isSetSignerRole();
    
    /**
     * Sets the "SignerRole" element
     */
    void setSignerRole(org.etsi.uri.x01903.v13.SignerRoleType signerRole);
    
    /**
     * Appends and returns a new empty "SignerRole" element
     */
    org.etsi.uri.x01903.v13.SignerRoleType addNewSignerRole();
    
    /**
     * Unsets the "SignerRole" element
     */
    void unsetSignerRole();
    
    /**
     * Gets the "Id" attribute
     */
    java.lang.String getId();
    
    /**
     * Gets (as xml) the "Id" attribute
     */
    org.apache.xmlbeans.XmlID xgetId();
    
    /**
     * True if has "Id" attribute
     */
    boolean isSetId();
    
    /**
     * Sets the "Id" attribute
     */
    void setId(java.lang.String id);
    
    /**
     * Sets (as xml) the "Id" attribute
     */
    void xsetId(org.apache.xmlbeans.XmlID id);
    
    /**
     * Unsets the "Id" attribute
     */
    void unsetId();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType newInstance() {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x01903.v13.SignedSignaturePropertiesType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
