/*
 * An XML document type.
 * Localname: Qualifications
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList;


/**
 * A document containing one Qualifications(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#) element.
 *
 * This is a complex type.
 */
public interface QualificationsDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(QualificationsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s873B7E6C0380BA89DCCDC166F3A3B371").resolveHandle("qualifications6f3fdoctype");
    
    /**
     * Gets the "Qualifications" element
     */
    org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType getQualifications();
    
    /**
     * Sets the "Qualifications" element
     */
    void setQualifications(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType qualifications);
    
    /**
     * Appends and returns a new empty "Qualifications" element
     */
    org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsType addNewQualifications();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument newInstance() {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.QualificationsDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
