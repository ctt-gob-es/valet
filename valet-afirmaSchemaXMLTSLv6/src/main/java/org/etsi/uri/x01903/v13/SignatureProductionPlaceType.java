/*
 * XML Type:  SignatureProductionPlaceType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignatureProductionPlaceType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13;


/**
 * An XML SignatureProductionPlaceType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public interface SignatureProductionPlaceType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SignatureProductionPlaceType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("signatureproductionplacetype9684type");
    
    /**
     * Gets the "City" element
     */
    java.lang.String getCity();
    
    /**
     * Gets (as xml) the "City" element
     */
    org.apache.xmlbeans.XmlString xgetCity();
    
    /**
     * True if has "City" element
     */
    boolean isSetCity();
    
    /**
     * Sets the "City" element
     */
    void setCity(java.lang.String city);
    
    /**
     * Sets (as xml) the "City" element
     */
    void xsetCity(org.apache.xmlbeans.XmlString city);
    
    /**
     * Unsets the "City" element
     */
    void unsetCity();
    
    /**
     * Gets the "StateOrProvince" element
     */
    java.lang.String getStateOrProvince();
    
    /**
     * Gets (as xml) the "StateOrProvince" element
     */
    org.apache.xmlbeans.XmlString xgetStateOrProvince();
    
    /**
     * True if has "StateOrProvince" element
     */
    boolean isSetStateOrProvince();
    
    /**
     * Sets the "StateOrProvince" element
     */
    void setStateOrProvince(java.lang.String stateOrProvince);
    
    /**
     * Sets (as xml) the "StateOrProvince" element
     */
    void xsetStateOrProvince(org.apache.xmlbeans.XmlString stateOrProvince);
    
    /**
     * Unsets the "StateOrProvince" element
     */
    void unsetStateOrProvince();
    
    /**
     * Gets the "PostalCode" element
     */
    java.lang.String getPostalCode();
    
    /**
     * Gets (as xml) the "PostalCode" element
     */
    org.apache.xmlbeans.XmlString xgetPostalCode();
    
    /**
     * True if has "PostalCode" element
     */
    boolean isSetPostalCode();
    
    /**
     * Sets the "PostalCode" element
     */
    void setPostalCode(java.lang.String postalCode);
    
    /**
     * Sets (as xml) the "PostalCode" element
     */
    void xsetPostalCode(org.apache.xmlbeans.XmlString postalCode);
    
    /**
     * Unsets the "PostalCode" element
     */
    void unsetPostalCode();
    
    /**
     * Gets the "CountryName" element
     */
    java.lang.String getCountryName();
    
    /**
     * Gets (as xml) the "CountryName" element
     */
    org.apache.xmlbeans.XmlString xgetCountryName();
    
    /**
     * True if has "CountryName" element
     */
    boolean isSetCountryName();
    
    /**
     * Sets the "CountryName" element
     */
    void setCountryName(java.lang.String countryName);
    
    /**
     * Sets (as xml) the "CountryName" element
     */
    void xsetCountryName(org.apache.xmlbeans.XmlString countryName);
    
    /**
     * Unsets the "CountryName" element
     */
    void unsetCountryName();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType newInstance() {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x01903.v13.SignatureProductionPlaceType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x01903.v13.SignatureProductionPlaceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
