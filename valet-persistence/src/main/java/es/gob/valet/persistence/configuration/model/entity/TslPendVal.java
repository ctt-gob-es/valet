package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import es.gob.valet.commons.utils.NumberConstants;

@Entity
@Table(name = "TSL_PEND_VAL")
public class TslPendVal implements Serializable {

	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = 3489998856050834737L;
	
	@Id
	@Column(name = "ID_TSL_PEND_VAL", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_tsl_pend_val")
	@GenericGenerator(name = "sq_tsl_pend_val", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_TSL_PEND_VAL"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	private Long idTslPendVal;
	
	@Lob
	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "XML_DOCUMENT", nullable = false)
	private byte[ ] xmlDocument;

	
	/**
	 * Gets the value of the attribute {@link #idTslPendVal}.
	 * @return the value of the attribute {@link #idTslPendVal}.
	 */
	public Long getIdTslPendVal() {
		return idTslPendVal;
	}

	
	/**
	 * Sets the value of the attribute {@link #idTslPendVal}.
	 * @param idTslPendVal The value for the attribute {@link #idTslPendVal}.
	 */
	public void setIdTslPendVal(Long idTslPendVal) {
		this.idTslPendVal = idTslPendVal;
	}

	/**
	 * Gets the value of the attribute {@link #xmlDocument}.
	 * @return the value of the attribute {@link #xmlDocument}.
	 */
	public byte[ ] getXmlDocument() {
		return xmlDocument;
	}

	
	/**
	 * Sets the value of the attribute {@link #xmlDocument}.
	 * @param xmlDocument The value for the attribute {@link #xmlDocument}.
	 */
	public void setXmlDocument(byte[ ] xmlDocument) {
		this.xmlDocument = xmlDocument;
	}
	
	
}
