package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class TSLServiceDTO implements Serializable {

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
	private Long tspName;

	/**
	 * Attribute that represents the description of the tsp service name.
	 */
	private Long tspServiceName;

	/**
	 * Attribute that represents the description of the digital identity id.
	 */
	private Long digitalIdentityId;

	/**
	 * Attribute that represents the description of the digital identity caduced.
	 */
	private Date digitalIdentityCad;

	/**
	 * Attribute that represents the description of the certificate.
	 */
	private byte[] certificate;

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
	public Long getTspName() {
		return tspName;
	}

	/**
	 * Sets the value of the attribute {@link #tspName}.
	 * @param tspName The value for the attribute {@link #tspName}.
	 */
	public void setTspName(Long tspName) {
		this.tspName = tspName;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceName}.
	 * @return the value of the attribute {@link #tspServiceName}.
	 */
	public Long getTspServiceName() {
		return tspServiceName;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceName}.
	 * @param tspServiceName The value for the attribute {@link #tspServiceName}.
	 */
	public void setTspServiceName(Long tspServiceName) {
		this.tspServiceName = tspServiceName;
	}

	/**
	 * Gets the value of the attribute {@link #digitalIdentityId}.
	 * @return the value of the attribute {@link #digitalIdentityId}.
	 */
	public Long getDigitalIdentityId() {
		return digitalIdentityId;
	}

	/**
	 * Sets the value of the attribute {@link #digitalIdentityId}.
	 * @param digitalIdentityId The value for the attribute {@link #digitalIdentityId}.
	 */
	public void setDigitalIdentityId(Long digitalIdentityId) {
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
		TSLServiceDTO other = (TSLServiceDTO) obj;
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
