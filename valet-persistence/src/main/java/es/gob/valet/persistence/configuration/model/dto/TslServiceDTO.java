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
 * <b>File:</b><p>es.gob.valet.dto.TSLServiceDTO.java.</p>
 * <b>Description:</b><p>Class that represents the information needed to generate the tree of the TSL Service DTO module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>04/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.1, 11/10/2022.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.persistence.configuration.model.entity.TslMapping;
import es.gob.valet.persistence.configuration.model.entity.TslService;

/**
 * <p>
 * Class that represents the information needed to generate the tree of the TSL
 * Service DTO module.
 * </p>
 * <b>Project:</b>
 * <p>
 * Platform for detection and validation of certificates recognized in European
 * TSL.
 * </p>
 * 
 * @version 1.1, 11/10/2022.
 */
public class TslServiceDTO implements Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = -6920967846457138974L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTslService;

	/**
	 * Attribute that represents the country.
	 */
	private String country;

	/**
	 * Attribute that represents the tsl version.
	 */
	private Long tslVersion;

	/**
	 * Attribute that represents the description of the tsp name.
	 */
	private String tspName;

	/**
	 * Attribute that represents the description of the tsp service name.
	 */
	private String tspServiceName;

	/**
	 * Attribute that represents the description of the digital identity id.
	 */
	private String digitalIdentityId;

	/**
	 * Attribute that represents the description of the digital identity
	 * caduced.
	 */
	private Date digitalIdentityCad;

	/**
	 * Attribute that represents the description of the certificate.
	 */
	@JsonSerialize(using = es.gob.valet.persistence.utils.ByteArraySerializer.class)
	private byte[] certificate;

	/**
	 * Attribute that represents the issuer of certificate.
	 */
	private String issuerDN;

	/**
	 * Attribute that represents the subject of certificate.
	 */
	private String subjectDN;

	/**
	 * Attribute that represents the start date of certificate.
	 */
	private String notBefore;

	/**
	 * Attribute that represents the end date of certificate.
	 */
	private String notAfter;

	/**
	 * Attribute that represents the logical fiels that have associate this tsl
	 * service.
	 */
	private List<MappingTslDTO> listMappingTslDTO;

	/**
	 * Constructor method for the class TslServiceDTO.java.
	 */
	public TslServiceDTO() {
	}

	/**
	 * Constructor method for the class TSLServiceDTO.java.
	 * 
	 * @param tslService parameter that contain tsl service obtain from BD.
	 * @throws CommonUtilsException If the method fails.
	 */
	public TslServiceDTO(TslService tslService) throws CommonUtilsException {
		if (null != tslService) {
			this.country = tslService.getCountry();
			this.digitalIdentityCad = tslService.getDigitalIdentityCad();
			this.digitalIdentityId = tslService.getDigitalIdentityId();
			this.idTslService = tslService.getIdTslService();
			this.tslVersion = tslService.getTslVersion();
			this.tspName = tslService.getTspName();
			this.tspServiceName = tslService.getTspServiceName();
			if (null != tslService.getCertificate()) {
				this.certificate = tslService.getCertificate();
				X509Certificate x509Certificate = UtilsCertificate.getX509Certificate(tslService.getCertificate());
				this.issuerDN = x509Certificate.getIssuerDN().toString();
				this.subjectDN = x509Certificate.getSubjectDN().toString();
				this.notBefore = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_STANDARD, x509Certificate.getNotBefore());
				this.notAfter = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_STANDARD, x509Certificate.getNotAfter());
			}
			List<TslMapping> listTslMapping = tslService.getTslMapping();
			if (null != listTslMapping && !listTslMapping.isEmpty()) {
				this.listMappingTslDTO = new ArrayList<MappingTslDTO>();
				for (TslMapping tslMapping : listTslMapping) {
					MappingTslDTO mappingTslDTO = new MappingTslDTO();
					mappingTslDTO.setIdTslMapping(tslMapping.getIdTslMapping());
					mappingTslDTO.setLogicalFieldId(tslMapping.getLogicalFieldId());
					mappingTslDTO.setLogicalFieldValue(tslMapping.getLogicalFieldValue());
					listMappingTslDTO.add(mappingTslDTO);
				}
			}
		}
	}

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
	 * Gets the value of the attribute {@link #issuerDN}.
	 * @return the value of the attribute {@link #issuerDN}.
	 */
	public String getIssuerDN() {
		return issuerDN;
	}

	/**
	 * Sets the value of the attribute {@link #issuerDN}.
	 * @param issuerDN The value for the attribute {@link #issuerDN}.
	 */
	public void setIssuerDN(String issuerDN) {
		this.issuerDN = issuerDN;
	}

	/**
	 * Gets the value of the attribute {@link #subjectDN}.
	 * @return the value of the attribute {@link #subjectDN}.
	 */
	public String getSubjectDN() {
		return subjectDN;
	}

	/**
	 * Sets the value of the attribute {@link #subjectDN}.
	 * @param subjectDN The value for the attribute {@link #subjectDN}.
	 */
	public void setSubjectDN(String subjectDN) {
		this.subjectDN = subjectDN;
	}

	/**
	 * Gets the value of the attribute {@link #notBefore}.
	 * @return the value of the attribute {@link #notBefore}.
	 */
	public String getNotBefore() {
		return notBefore;
	}

	/**
	 * Sets the value of the attribute {@link #notBefore}.
	 * @param notBefore The value for the attribute {@link #notBefore}.
	 */
	public void setNotBefore(String notBefore) {
		this.notBefore = notBefore;
	}

	/**
	 * Gets the value of the attribute {@link #notAfter}.
	 * @return the value of the attribute {@link #notAfter}.
	 */
	public String getNotAfter() {
		return notAfter;
	}

	/**
	 * Sets the value of the attribute {@link #notAfter}.
	 * @param notAfter The value for the attribute {@link #notAfter}.
	 */
	public void setNotAfter(String notAfter) {
		this.notAfter = notAfter;
	}
	
	/**
	 * Gets the value of the attribute {@link #listMappingTslDTO}.
	 * @return the value of the attribute {@link #listMappingTslDTO}.
	 */
	public List<MappingTslDTO> getListMappingTslDTO() {
		return listMappingTslDTO;
	}

	/**
	 * Sets the value of the attribute {@link #listMappingTslDTO}.
	 * @param listMappingTslDTO The value for the attribute {@link #listMappingTslDTO}.
	 */
	public void setListMappingTslDTO(List<MappingTslDTO> listMappingTslDTO) {
		this.listMappingTslDTO = listMappingTslDTO;
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
		result = prime * result + ((issuerDN == null) ? 0 : issuerDN.hashCode());
		result = prime * result + ((listMappingTslDTO == null) ? 0 : listMappingTslDTO.hashCode());
		result = prime * result + ((notAfter == null) ? 0 : notAfter.hashCode());
		result = prime * result + ((notBefore == null) ? 0 : notBefore.hashCode());
		result = prime * result + ((subjectDN == null) ? 0 : subjectDN.hashCode());
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
		TslServiceDTO other = (TslServiceDTO) obj;
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
		if (issuerDN == null) {
			if (other.issuerDN != null)
				return false;
		} else if (!issuerDN.equals(other.issuerDN))
			return false;
		if (listMappingTslDTO == null) {
			if (other.listMappingTslDTO != null)
				return false;
		} else if (!listMappingTslDTO.equals(other.listMappingTslDTO))
			return false;
		if (notAfter == null) {
			if (other.notAfter != null)
				return false;
		} else if (!notAfter.equals(other.notAfter))
			return false;
		if (notBefore == null) {
			if (other.notBefore != null)
				return false;
		} else if (!notBefore.equals(other.notBefore))
			return false;
		if (subjectDN == null) {
			if (other.subjectDN != null)
				return false;
		} else if (!subjectDN.equals(other.subjectDN))
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
