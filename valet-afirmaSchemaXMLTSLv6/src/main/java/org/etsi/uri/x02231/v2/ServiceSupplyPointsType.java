/*
 * XML Type:  ServiceSupplyPointsType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ServiceSupplyPointsType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2;


/**
 * An XML ServiceSupplyPointsType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public interface ServiceSupplyPointsType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ServiceSupplyPointsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("servicesupplypointstype235etype");
    
    /**
     * Gets array of all "ServiceSupplyPoint" elements
     */
    java.lang.String[] getServiceSupplyPointArray();
    
    /**
     * Gets ith "ServiceSupplyPoint" element
     */
    java.lang.String getServiceSupplyPointArray(int i);
    
    /**
     * Gets (as xml) array of all "ServiceSupplyPoint" elements
     */
    org.etsi.uri.x02231.v2.NonEmptyURIType[] xgetServiceSupplyPointArray();
    
    /**
     * Gets (as xml) ith "ServiceSupplyPoint" element
     */
    org.etsi.uri.x02231.v2.NonEmptyURIType xgetServiceSupplyPointArray(int i);
    
    /**
     * Returns number of "ServiceSupplyPoint" element
     */
    int sizeOfServiceSupplyPointArray();
    
    /**
     * Sets array of all "ServiceSupplyPoint" element
     */
    void setServiceSupplyPointArray(java.lang.String[] serviceSupplyPointArray);
    
    /**
     * Sets ith "ServiceSupplyPoint" element
     */
    void setServiceSupplyPointArray(int i, java.lang.String serviceSupplyPoint);
    
    /**
     * Sets (as xml) array of all "ServiceSupplyPoint" element
     */
    void xsetServiceSupplyPointArray(org.etsi.uri.x02231.v2.NonEmptyURIType[] serviceSupplyPointArray);
    
    /**
     * Sets (as xml) ith "ServiceSupplyPoint" element
     */
    void xsetServiceSupplyPointArray(int i, org.etsi.uri.x02231.v2.NonEmptyURIType serviceSupplyPoint);
    
    /**
     * Inserts the value as the ith "ServiceSupplyPoint" element
     */
    void insertServiceSupplyPoint(int i, java.lang.String serviceSupplyPoint);
    
    /**
     * Appends the value as the last "ServiceSupplyPoint" element
     */
    void addServiceSupplyPoint(java.lang.String serviceSupplyPoint);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceSupplyPoint" element
     */
    org.etsi.uri.x02231.v2.NonEmptyURIType insertNewServiceSupplyPoint(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceSupplyPoint" element
     */
    org.etsi.uri.x02231.v2.NonEmptyURIType addNewServiceSupplyPoint();
    
    /**
     * Removes the ith "ServiceSupplyPoint" element
     */
    void removeServiceSupplyPoint(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType newInstance() {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.ServiceSupplyPointsType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.ServiceSupplyPointsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
