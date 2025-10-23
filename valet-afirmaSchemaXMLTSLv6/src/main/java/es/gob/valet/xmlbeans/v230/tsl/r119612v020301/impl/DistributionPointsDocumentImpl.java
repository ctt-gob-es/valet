/*
 * An XML document type.
 * Localname: DistributionPoints
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.DistributionPointsDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one DistributionPoints(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class DistributionPointsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DistributionPointsDocument
{
    
    public DistributionPointsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DISTRIBUTIONPOINTS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "DistributionPoints");
    
    
    /**
     * Gets the "DistributionPoints" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType getDistributionPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType)get_store().find_element_user(DISTRIBUTIONPOINTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "DistributionPoints" element
     */
    public void setDistributionPoints(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType distributionPoints)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType)get_store().find_element_user(DISTRIBUTIONPOINTS$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType)get_store().add_element_user(DISTRIBUTIONPOINTS$0);
            }
            target.set(distributionPoints);
        }
    }
    
    /**
     * Appends and returns a new empty "DistributionPoints" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType addNewDistributionPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType)get_store().add_element_user(DISTRIBUTIONPOINTS$0);
            return target;
        }
    }
}
