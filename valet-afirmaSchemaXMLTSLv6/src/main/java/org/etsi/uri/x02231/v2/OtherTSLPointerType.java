/*
 * XML Type:  OtherTSLPointerType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.OtherTSLPointerType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2;


/**
 * An XML OtherTSLPointerType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public interface OtherTSLPointerType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(OtherTSLPointerType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("othertslpointertype0977type");
    
    /**
     * Gets the "ServiceDigitalIdentities" element
     */
    org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType getServiceDigitalIdentities();
    
    /**
     * True if has "ServiceDigitalIdentities" element
     */
    boolean isSetServiceDigitalIdentities();
    
    /**
     * Sets the "ServiceDigitalIdentities" element
     */
    void setServiceDigitalIdentities(org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType serviceDigitalIdentities);
    
    /**
     * Appends and returns a new empty "ServiceDigitalIdentities" element
     */
    org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType addNewServiceDigitalIdentities();
    
    /**
     * Unsets the "ServiceDigitalIdentities" element
     */
    void unsetServiceDigitalIdentities();
    
    /**
     * Gets the "TSLLocation" element
     */
    java.lang.String getTSLLocation();
    
    /**
     * Gets (as xml) the "TSLLocation" element
     */
    org.etsi.uri.x02231.v2.NonEmptyURIType xgetTSLLocation();
    
    /**
     * Sets the "TSLLocation" element
     */
    void setTSLLocation(java.lang.String tslLocation);
    
    /**
     * Sets (as xml) the "TSLLocation" element
     */
    void xsetTSLLocation(org.etsi.uri.x02231.v2.NonEmptyURIType tslLocation);
    
    /**
     * Gets the "AdditionalInformation" element
     */
    org.etsi.uri.x02231.v2.AdditionalInformationType getAdditionalInformation();
    
    /**
     * True if has "AdditionalInformation" element
     */
    boolean isSetAdditionalInformation();
    
    /**
     * Sets the "AdditionalInformation" element
     */
    void setAdditionalInformation(org.etsi.uri.x02231.v2.AdditionalInformationType additionalInformation);
    
    /**
     * Appends and returns a new empty "AdditionalInformation" element
     */
    org.etsi.uri.x02231.v2.AdditionalInformationType addNewAdditionalInformation();
    
    /**
     * Unsets the "AdditionalInformation" element
     */
    void unsetAdditionalInformation();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType newInstance() {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.OtherTSLPointerType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.OtherTSLPointerType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
