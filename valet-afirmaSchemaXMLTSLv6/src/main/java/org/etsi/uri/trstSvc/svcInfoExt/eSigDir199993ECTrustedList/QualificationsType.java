/*
 * XML Type:  QualificationsType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList;


/**
 * An XML QualificationsType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public interface QualificationsType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(QualificationsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("qualificationstype3809type");
    
    /**
     * Gets array of all "QualificationElement" elements
     */
    org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType[] getQualificationElementArray();
    
    /**
     * Gets ith "QualificationElement" element
     */
    org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType getQualificationElementArray(int i);
    
    /**
     * Returns number of "QualificationElement" element
     */
    int sizeOfQualificationElementArray();
    
    /**
     * Sets array of all "QualificationElement" element
     */
    void setQualificationElementArray(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType[] qualificationElementArray);
    
    /**
     * Sets ith "QualificationElement" element
     */
    void setQualificationElementArray(int i, org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType qualificationElement);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "QualificationElement" element
     */
    org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType insertNewQualificationElement(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "QualificationElement" element
     */
    org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationElementType addNewQualificationElement();
    
    /**
     * Removes the ith "QualificationElement" element
     */
    void removeQualificationElement(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType newInstance() {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
