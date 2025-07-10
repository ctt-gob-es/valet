/*
/*******************************************************************************
 * Copyright (C) 2018 MINHAFP, Gobierno de España
 * This program is licensed and may be used, modified and redistributed under the  terms
 * of the European Public License (EUPL), either version 1.1 or (at your option)
 * any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and
 * more details.
 * You should have received a copy of the EUPL1.1 license
 * along with this program; if not, you may find it at
 * http:joinup.ec.europa.eu/software/page/eupl/licence-eupl
 ******************************************************************************/

/**
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TslPendVal.java.</p>
 * <b>Description:</b><p>Class that maps the <i>TSL_MAPPING</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.1, 10/07/2025.
 */
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

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>TSL_PEND_VAL</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 *  @version 1.1, 10/07/2025.
 */
@Entity
@Table(name = "TSL_PEND_VAL")
public class TslPendVal implements Serializable {

	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = 3489998856050834737L;
	
	/**
	 * Unique identifier for the pending TSL validation entry.
	 * Generated using the sequence SQ_TSL_PEND_VAL.
	 */
	@Id
	@Column(name = "ID_TSL_PEND_VAL", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_tsl_pend_val")
	@GenericGenerator(
	    name = "sq_tsl_pend_val",
	    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	    parameters = {
	        @Parameter(name = "sequence_name", value = "SQ_TSL_PEND_VAL"),
	        @Parameter(name = "initial_value", value = "1"),
	        @Parameter(name = "increment_size", value = "1")
	    }
	)
	private Long idTslPendVal;

	/**
	 * XML binary content of the TSL document.
	 * Stored as a LOB and lazily fetched from the database.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "XML_DOCUMENT", nullable = false)
	private byte[] xmlDocument;
	
	/**
	 * Country name associated with the TSL (e.g., "Spain" ).
	 * This field is mandatory.
	 */
	@Column(name = "COUNTRY", nullable = false)
	private String country;

	
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
	
	/**
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Sets the value of the attribute {@link #country}.
	 * @param country The value for the attribute {@link #country}.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
}
