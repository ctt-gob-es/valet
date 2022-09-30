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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TSLService.java.</p>
 * <b>Description:</b><p>Class that maps the <i>TSL_SERVICE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 28/09/2022.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>TSL_SERVICE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 *  @version 1.0, 28/09/2022.
 */
@Entity
@Table(name = "TSL_SERVICE")
public class TSLService implements Serializable {

	/**
	 * Attribute that represents the class serial version.
	 */
	private static final long serialVersionUID = 6301263874433042243L;

	/**
	 * Attribute that represents the object ID.
	 */
	@Id
	@Column(name = "ID_SERVICE", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_tsl_service")
	@GenericGenerator(name = "sq_tsl_service", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SQ_TSL_SERVICE"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	private Long idTslService;

	/**
	 * Attribute that represents the country.
	 */
	@Column(name = "COUNTRY", nullable = false, length = NumberConstants.NUM2)
	private String country;

	/**
	 * Attribute that represents the tsl version.
	 */
	@Column(name = "TSL_VERSION", nullable = false, length = NumberConstants.NUM8)
	private Long tslVersion;

	/**
	 * Attribute that represents the tsp name.
	 */
	@Column(name = "TSP_NAME", nullable = false, length = NumberConstants.NUM256)
	private String tspName;

	/**
	 * Attribute that represents the tsp service name.
	 */
	@Column(name = "TSP_SERVICE_NAME", nullable = false, length = NumberConstants.NUM256)
	private String tspServiceName;

	/**
	 * Attribute that represents the digital identity id.
	 */
	@Column(name = "DIGITAL_IDENTITY_ID", nullable = false, length = NumberConstants.NUM44)
	private String digitalIdentityId;

	/**
	 * Attribute that represents the digital identity caduced.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DIGITAL_IDENTITY_CAD", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date digitalIdentityCad;

	/**
	 * Attribute that represents the certificate.
	 */
	@Column(name = "CERTIFICATE", nullable = true)
	private byte[] certificate;

	/**
	 * Attribute that represents the tsl mappings associated.
	 */
	@OneToMany(mappedBy = "tslService", cascade = { CascadeType.REMOVE, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private List<TSLMapping> tslMapping;

	/**
	 * Gets the value of the attribute {@link #idTslService}.
	 * @return the value of the attribute {@link #idTslService}.
	 */
	public Long getIdTslService() {
		return idTslService;
	}

	/**
	 * Sets the value of the attribute {@link #idTslService}.
	 * @param idTslService The value for the attribute {@link #idTslService}.
	 */
	public void setIdTslService(Long idTslService) {
		this.idTslService = idTslService;
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

	/**
	 * Gets the value of the attribute {@link #tslVersion}.
	 * @return the value of the attribute {@link #tslVersion}.
	 */
	public Long getTslVersion() {
		return tslVersion;
	}

	/**
	 * Sets the value of the attribute {@link #tslVersion}.
	 * @param tslVersion The value for the attribute {@link #tslVersion}.
	 */
	public void setTslVersion(Long tslVersion) {
		this.tslVersion = tslVersion;
	}

	/**
	 * Gets the value of the attribute {@link #tspName}.
	 * @return the value of the attribute {@link #tspName}.
	 */
	public String getTspName() {
		return tspName;
	}

	/**
	 * Sets the value of the attribute {@link #tspName}.
	 * @param tspName The value for the attribute {@link #tspName}.
	 */
	public void setTspName(String tspName) {
		this.tspName = tspName;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceName}.
	 * @return the value of the attribute {@link #tspServiceName}.
	 */
	public String getTspServiceName() {
		return tspServiceName;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceName}.
	 * @param tspServiceName The value for the attribute {@link #tspServiceName}.
	 */
	public void setTspServiceName(String tspServiceName) {
		this.tspServiceName = tspServiceName;
	}

	/**
	 * Gets the value of the attribute {@link #digitalIdentityId}.
	 * @return the value of the attribute {@link #digitalIdentityId}.
	 */
	public String getDigitalIdentityId() {
		return digitalIdentityId;
	}

	/**
	 * Sets the value of the attribute {@link #digitalIdentityId}.
	 * @param digitalIdentityId The value for the attribute {@link #digitalIdentityId}.
	 */
	public void setDigitalIdentityId(String digitalIdentityId) {
		this.digitalIdentityId = digitalIdentityId;
	}

	/**
	 * Gets the value of the attribute {@link #digitalIdentityCad}.
	 * @return the value of the attribute {@link #digitalIdentityCad}.
	 */
	public Date getDigitalIdentityCad() {
		return digitalIdentityCad;
	}

	/**
	 * Sets the value of the attribute {@link #digitalIdentityCad}.
	 * @param digitalIdentityCad The value for the attribute {@link #digitalIdentityCad}.
	 */
	public void setDigitalIdentityCad(Date digitalIdentityCad) {
		this.digitalIdentityCad = digitalIdentityCad;
	}

	/**
	 * Gets the value of the attribute {@link #certificate}.
	 * @return the value of the attribute {@link #certificate}.
	 */
	public byte[] getCertificate() {
		return certificate;
	}

	/**
	 * Sets the value of the attribute {@link #certificate}.
	 * @param certificate The value for the attribute {@link #certificate}.
	 */
	public void setCertificate(byte[] certificate) {
		this.certificate = certificate;
	}

	/**
	 * Gets the value of the attribute {@link #tslMapping}.
	 * @return the value of the attribute {@link #tslMapping}.
	 */
	public List<TSLMapping> getTslMapping() {
		return tslMapping;
	}

	/**
	 * Sets the value of the attribute {@link #tslMapping}.
	 * @param tslMapping The value for the attribute {@link #tslMapping}.
	 */
	public void setTslMapping(List<TSLMapping> tslMapping) {
		this.tslMapping = tslMapping;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(certificate);
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((digitalIdentityCad == null) ? 0 : digitalIdentityCad.hashCode());
		result = prime * result + ((digitalIdentityId == null) ? 0 : digitalIdentityId.hashCode());
		result = prime * result + ((idTslService == null) ? 0 : idTslService.hashCode());
		result = prime * result + ((tslMapping == null) ? 0 : tslMapping.hashCode());
		result = prime * result + ((tslVersion == null) ? 0 : tslVersion.hashCode());
		result = prime * result + ((tspName == null) ? 0 : tspName.hashCode());
		result = prime * result + ((tspServiceName == null) ? 0 : tspServiceName.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TSLService other = (TSLService) obj;
		if (!Arrays.equals(certificate, other.certificate))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (digitalIdentityCad == null) {
			if (other.digitalIdentityCad != null)
				return false;
		} else if (!digitalIdentityCad.equals(other.digitalIdentityCad))
			return false;
		if (digitalIdentityId == null) {
			if (other.digitalIdentityId != null)
				return false;
		} else if (!digitalIdentityId.equals(other.digitalIdentityId))
			return false;
		if (idTslService == null) {
			if (other.idTslService != null)
				return false;
		} else if (!idTslService.equals(other.idTslService))
			return false;
		if (tslMapping == null) {
			if (other.tslMapping != null)
				return false;
		} else if (!tslMapping.equals(other.tslMapping))
			return false;
		if (tslVersion == null) {
			if (other.tslVersion != null)
				return false;
		} else if (!tslVersion.equals(other.tslVersion))
			return false;
		if (tspName == null) {
			if (other.tspName != null)
				return false;
		} else if (!tspName.equals(other.tspName))
			return false;
		if (tspServiceName == null) {
			if (other.tspServiceName != null)
				return false;
		} else if (!tspServiceName.equals(other.tspServiceName))
			return false;
		return true;
	}

}
