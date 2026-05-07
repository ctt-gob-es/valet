/*
 * XML Type:  PostalAddressType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.PostalAddressType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2;


/**
 * An XML PostalAddressType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public interface PostalAddressType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(PostalAddressType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("postaladdresstypeaec0type");
    
    /**
     * Gets the "StreetAddress" element
     */
    java.lang.String getStreetAddress();
    
    /**
     * Gets (as xml) the "StreetAddress" element
     */
    org.etsi.uri.x02231.v2.NonEmptyString xgetStreetAddress();
    
    /**
     * Sets the "StreetAddress" element
     */
    void setStreetAddress(java.lang.String streetAddress);
    
    /**
     * Sets (as xml) the "StreetAddress" element
     */
    void xsetStreetAddress(org.etsi.uri.x02231.v2.NonEmptyString streetAddress);
    
    /**
     * Gets the "Locality" element
     */
    java.lang.String getLocality();
    
    /**
     * Gets (as xml) the "Locality" element
     */
    org.etsi.uri.x02231.v2.NonEmptyString xgetLocality();
    
    /**
     * Sets the "Locality" element
     */
    void setLocality(java.lang.String locality);
    
    /**
     * Sets (as xml) the "Locality" element
     */
    void xsetLocality(org.etsi.uri.x02231.v2.NonEmptyString locality);
    
    /**
     * Gets the "StateOrProvince" element
     */
    java.lang.String getStateOrProvince();
    
    /**
     * Gets (as xml) the "StateOrProvince" element
     */
    org.etsi.uri.x02231.v2.NonEmptyString xgetStateOrProvince();
    
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
    void xsetStateOrProvince(org.etsi.uri.x02231.v2.NonEmptyString stateOrProvince);
    
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
    org.etsi.uri.x02231.v2.NonEmptyString xgetPostalCode();
    
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
    void xsetPostalCode(org.etsi.uri.x02231.v2.NonEmptyString postalCode);
    
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
    org.etsi.uri.x02231.v2.NonEmptyString xgetCountryName();
    
    /**
     * Sets the "CountryName" element
     */
    void setCountryName(java.lang.String countryName);
    
    /**
     * Sets (as xml) the "CountryName" element
     */
    void xsetCountryName(org.etsi.uri.x02231.v2.NonEmptyString countryName);
    
    /**
     * Gets the "lang" attribute
     */
    java.lang.String getLang();
    
    /**
     * Gets (as xml) the "lang" attribute
     */
    org.apache.xmlbeans.XmlLanguage xgetLang();
    
    /**
     * Sets the "lang" attribute
     */
    void setLang(java.lang.String lang);
    
    /**
     * Sets (as xml) the "lang" attribute
     */
    void xsetLang(org.apache.xmlbeans.XmlLanguage lang);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.x02231.v2.PostalAddressType newInstance() {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.PostalAddressType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.PostalAddressType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
