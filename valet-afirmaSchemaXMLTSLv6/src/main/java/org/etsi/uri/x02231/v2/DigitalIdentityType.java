/*
 * XML Type:  DigitalIdentityType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.DigitalIdentityType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2;


/**
 * An XML DigitalIdentityType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public interface DigitalIdentityType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(DigitalIdentityType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("digitalidentitytype800dtype");
    
    /**
     * Gets the "X509Certificate" element
     */
    byte[] getX509Certificate();
    
    /**
     * Gets (as xml) the "X509Certificate" element
     */
    org.apache.xmlbeans.XmlBase64Binary xgetX509Certificate();
    
    /**
     * True if has "X509Certificate" element
     */
    boolean isSetX509Certificate();
    
    /**
     * Sets the "X509Certificate" element
     */
    void setX509Certificate(byte[] x509Certificate);
    
    /**
     * Sets (as xml) the "X509Certificate" element
     */
    void xsetX509Certificate(org.apache.xmlbeans.XmlBase64Binary x509Certificate);
    
    /**
     * Unsets the "X509Certificate" element
     */
    void unsetX509Certificate();
    
    /**
     * Gets the "X509SubjectName" element
     */
    java.lang.String getX509SubjectName();
    
    /**
     * Gets (as xml) the "X509SubjectName" element
     */
    org.apache.xmlbeans.XmlString xgetX509SubjectName();
    
    /**
     * True if has "X509SubjectName" element
     */
    boolean isSetX509SubjectName();
    
    /**
     * Sets the "X509SubjectName" element
     */
    void setX509SubjectName(java.lang.String x509SubjectName);
    
    /**
     * Sets (as xml) the "X509SubjectName" element
     */
    void xsetX509SubjectName(org.apache.xmlbeans.XmlString x509SubjectName);
    
    /**
     * Unsets the "X509SubjectName" element
     */
    void unsetX509SubjectName();
    
    /**
     * Gets the "KeyValue" element
     */
    org.w3.x2000.x09.xmldsig.KeyValueType getKeyValue();
    
    /**
     * True if has "KeyValue" element
     */
    boolean isSetKeyValue();
    
    /**
     * Sets the "KeyValue" element
     */
    void setKeyValue(org.w3.x2000.x09.xmldsig.KeyValueType keyValue);
    
    /**
     * Appends and returns a new empty "KeyValue" element
     */
    org.w3.x2000.x09.xmldsig.KeyValueType addNewKeyValue();
    
    /**
     * Unsets the "KeyValue" element
     */
    void unsetKeyValue();
    
    /**
     * Gets the "X509SKI" element
     */
    byte[] getX509SKI();
    
    /**
     * Gets (as xml) the "X509SKI" element
     */
    org.apache.xmlbeans.XmlBase64Binary xgetX509SKI();
    
    /**
     * True if has "X509SKI" element
     */
    boolean isSetX509SKI();
    
    /**
     * Sets the "X509SKI" element
     */
    void setX509SKI(byte[] x509SKI);
    
    /**
     * Sets (as xml) the "X509SKI" element
     */
    void xsetX509SKI(org.apache.xmlbeans.XmlBase64Binary x509SKI);
    
    /**
     * Unsets the "X509SKI" element
     */
    void unsetX509SKI();
    
    /**
     * Gets the "Other" element
     */
    org.etsi.uri.x02231.v2.AnyType getOther();
    
    /**
     * True if has "Other" element
     */
    boolean isSetOther();
    
    /**
     * Sets the "Other" element
     */
    void setOther(org.etsi.uri.x02231.v2.AnyType other);
    
    /**
     * Appends and returns a new empty "Other" element
     */
    org.etsi.uri.x02231.v2.AnyType addNewOther();
    
    /**
     * Unsets the "Other" element
     */
    void unsetOther();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.x02231.v2.DigitalIdentityType newInstance() {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.DigitalIdentityType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.DigitalIdentityType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
