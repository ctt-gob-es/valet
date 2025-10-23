/*
 * An XML document type.
 * Localname: TakenOverBy
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: org.etsi.uri.x02231.v2.additionaltypes.TakenOverByDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.additionaltypes.impl;
/**
 * A document containing one TakenOverBy(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class TakenOverByDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.additionaltypes.TakenOverByDocument
{
    
    public TakenOverByDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TAKENOVERBY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "TakenOverBy");
    
    
    /**
     * Gets the "TakenOverBy" element
     */
    public org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType getTakenOverBy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType)get_store().find_element_user(TAKENOVERBY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TakenOverBy" element
     */
    public void setTakenOverBy(org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType takenOverBy)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType)get_store().find_element_user(TAKENOVERBY$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType)get_store().add_element_user(TAKENOVERBY$0);
            }
            target.set(takenOverBy);
        }
    }
    
    /**
     * Appends and returns a new empty "TakenOverBy" element
     */
    public org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType addNewTakenOverBy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType)get_store().add_element_user(TAKENOVERBY$0);
            return target;
        }
    }
}
