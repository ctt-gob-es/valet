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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TslLotlData.java.</p>
 * <b>Description:</b><p>Class that maps the <i>TSL_LOTL_DATA</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 26/06/2025.
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
 *  @version 1.0, 26/06/2025.
 */
@Entity
@Table(name = "TSL_LOTL_DATA")
public class TslLotlData implements Serializable {

	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = 8698168475704327400L;

	/**
	 * Unique identifier for the TSL LOTL Data entity.
	 * Auto-generated using a database sequence named "SQ_TSL_LOTL_DATA".
	 */
	@Id
	@Column(name = "ID_TSL_LOTL_DATA", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_tsl_lotl_data")
	@GenericGenerator(name = "sq_tsl_lotl_data", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_TSL_LOTL_DATA"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	private Long idTslLotlData;

	@Lob
	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "SIGNING_CERTIFICATE", nullable = false)
	private byte[ ] signingCertificate;
	
	/**
	 * Gets the value of the attribute {@link #idTslLotlData}.
	 * @return the value of the attribute {@link #idTslLotlData}.
	 */
	public Long getIdTslLotlData() {
		return idTslLotlData;
	}

	/**
	 * Sets the value of the attribute {@link #idTslLotlData}.
	 * @param idTslLotlData The value for the attribute {@link #idTslLotlData}.
	 */
	public void setIdTslLotlData(Long idTslLotlData) {
		this.idTslLotlData = idTslLotlData;
	}
	
	/**
	 * Gets the value of the attribute {@link #signingCertificate}.
	 * @return the value of the attribute {@link #signingCertificate}.
	 */
	public byte[ ] getSigningCertificate() {
		return signingCertificate;
	}
	
	/**
	 * Sets the value of the attribute {@link #signingCertificate}.
	 * @param signingCertificate The value for the attribute {@link #signingCertificate}.
	 */
	public void setSigningCertificate(byte[ ] signingCertificate) {
		this.signingCertificate = signingCertificate;
	}

}
