package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.gob.valet.commons.utils.NumberConstants;

@Entity
@Table(name = "VALET_CACHE_VERSION")
public class ValetCacheVersion implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -999355507852868226L;

	@Id
	@Column(name = "ID_VALET_CACHE_VERSION", unique = true, nullable = false, precision = NumberConstants.NUM19)
	private Long idValetCacheVersion;

	@Column(name = "VERSION_NUMBER", nullable = false)
	private Integer versionNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED", nullable = false)
	private Date lastUpdate;

	/**
	 * Gets the value of the attribute {@link #idValetCacheVersion}.
	 * @return the value of the attribute {@link #idValetCacheVersion}.
	 */
	public Long getIdValetCacheVersion() {
		return idValetCacheVersion;
	}

	/**
	 * Sets the value of the attribute {@link #idValetCacheVersion}.
	 * @param idValetCacheVersion The value for the attribute {@link #idValetCacheVersion}.
	 */
	public void setIdValetCacheVersion(Long idValetCacheVersion) {
		this.idValetCacheVersion = idValetCacheVersion;
	}

	/**
	 * Gets the value of the attribute {@link #versionNumber}.
	 * @return the value of the attribute {@link #versionNumber}.
	 */
	public Integer getVersionNumber() {
		return versionNumber;
	}

	/**
	 * Sets the value of the attribute {@link #versionNumber}.
	 * @param versionNumber The value for the attribute {@link #versionNumber}.
	 */
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}

	/**
	 * Gets the value of the attribute {@link #lastUpdate}.
	 * @return the value of the attribute {@link #lastUpdate}.
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * Sets the value of the attribute {@link #lastUpdate}.
	 * @param lastUpdate The value for the attribute {@link #lastUpdate}.
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
