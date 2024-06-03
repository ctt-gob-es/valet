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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.UserValet.java.</p>
 * <b>Description:</b><p> Class that maps the <i>USER_VALET</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>13/06/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 25/10/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>USER_VALET</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 25/10/2018.
 */
@Entity
@Table(name = "USER_VALET")
public class UserValet implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -4344979641003186960L;

	/**
	 * Constant attribute that represents the string <i>"yes_no"</i>.
	 */
	private static final String CONS_YES_NO = "yes_no";

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idUserValet;

	/**
	 * Attribute that indicates whether the user has audit role (true) or not (false).
	 */
	private Boolean isBlocked;

	/**

	 * Attribute that represents the email of the user.
	 */
	private String email;

	/**
	 * Attribute that represents the user name.
	 */
	private String name;

	/**
	 * Attribute that represents the surnames of the user.
	 */
	private String surnames;

	/**
	 * Attribute that represents the accumulated number of failed attempts accessing the platform since the last time which the
	 * user acceded correctly.
	 */
	private Integer attemptsNumber;

	/**
	 * Attribute that represents the date with the last correctly access to the platform by the user.
	 */
	private Date lastAccess;

	/**
	 * Attribute that represents the IP address used by the user to access to the platform.
	 */
	private String lastIpAccess;
	
	/**
	 * Attribute that represents the user NIF.
	 */
	private String nif;

	/**
	 * Gets the value of the attribute {@link #idUserValet}.
	 * @return the value of the attribute {@link #idUserValet}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Id
	@Column(name = "ID_USER_VALET", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_user_valet")
	@GenericGenerator(name = "sq_user_valet", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_USER_VALET"), @Parameter(name = "initial_value", value = "2"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdUserValet() {
		// CHECKSTYLE:ON
		return idUserValet;
	}

	/**
	 * Sets the value of the attribute {@link #idUserValet}.
	 * @param idUserValetParam The value for the attribute {@link #idUserValet}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setIdUserValet(Long idUserValetParam) {
		// CHECKSTYLE:ON
		this.idUserValet = idUserValetParam;
	}

	/**
	 * Gets the value of the attribute {@link #isBlocked}.
	 * @return the value of the attribute {@link #isBlocked}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Column(name = "IS_BLOCKED", nullable = false, precision = 1)
	@Type(type = CONS_YES_NO)
	public Boolean getIsBlocked() {
		// CHECKSTYLE:ON
		return isBlocked;
	}

	/**
	 * Sets the value of the attribute {@link #isBlocked}.
	 * @param isBlockedParam The value for the attribute {@link #isBlocked}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setIsBlocked(Boolean isBlockedParam) {
		// CHECKSTYLE:ON
		this.isBlocked = isBlockedParam;
	}

	/**
	 * Gets the value of the attribute {@link #email}.
	 * @return the value of the attribute {@link #email}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Column(name = "EMAIL", nullable = false, length = NumberConstants.NUM150)
	@JsonView(DataTablesOutput.View.class)
	public String getEmail() {
		// CHECKSTYLE:ON
		return email;
	}

	/**
	 * Sets the value of the attribute {@link #email}.
	 * @param mailParam The value for the attribute {@link #email}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setEmail(String mailParam) {
		// CHECKSTYLE:ON
		this.email = mailParam;
	}

	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Column(name = "NAME", nullable = false, length = NumberConstants.NUM100)
	@JsonView(DataTablesOutput.View.class)
	public String getName() {
		// CHECKSTYLE:ON
		return name;
	}

	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param nameParam The value for the attribute {@link #name}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setName(String nameParam) {
		// CHECKSTYLE:ON
		this.name = nameParam;
	}

	/**
	 * Gets the value of the attribute {@link #surnames}.
	 * @return the value of the attribute {@link #surnames}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Column(name = "SURNAMES", nullable = false, length = NumberConstants.NUM150)
	@JsonView(DataTablesOutput.View.class)
	public String getSurnames() {
		// CHECKSTYLE:ON
		return surnames;
	}

	/**
	 * Sets the value of the attribute {@link #surnames}.
	 * @param surnamesParam The value for the attribute {@link #surnames}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setSurnames(String surnamesParam) {
		// CHECKSTYLE:ON
		this.surnames = surnamesParam;
	}

	/**
	 * Gets the value of the attribute {@link #attemptsNumber}.
	 * @return the value of the attribute {@link #attemptsNumber}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Column(name = "ATTEMPTS_NUMBER", nullable = false)
	public Integer getAttemptsNumber() {
		// CHECKSTYLE:ON
		return attemptsNumber;
	}

	/**
	 * Sets the value of the attribute {@link #attemptsNumber}.
	 * @param attemptsNumber The value for the attribute {@link #attemptsNumber}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setAttemptsNumber(Integer attemptsNumber) {
		// CHECKSTYLE:ON
		this.attemptsNumber = attemptsNumber;
	}

	/**
	 * Gets the value of the attribute {@link #lastAccess}.
	 * @return the value of the attribute {@link #lastAccess}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Column(name = "LAST_ACCESS")
	public Date getLastAccess() {
		// CHECKSTYLE:ON
		return lastAccess;
	}

	/**
	 * Sets the value of the attribute {@link #lastAccess}.
	 * @param lastAccess The value for the attribute {@link #lastAccess}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setLastAccess(Date lastAccess) {
		// CHECKSTYLE:ON
		this.lastAccess = lastAccess;
	}

	/**
	 * Gets the value of the attribute {@link #lastIpAccess}.
	 * @return the value of the attribute {@link #lastIpAccess}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Column(name = "IP_LAST_ACCESS", length = NumberConstants.NUM15)
	public String getLastIpAccess() {
		// CHECKSTYLE:ON
		return lastIpAccess;
	}

	/**
	 * Sets the value of the attribute {@link #lastIpAccess}.
	 * @param lastIpAccess The value for the attribute {@link #lastIpAccess}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setLastIpAccess(String lastIpAccess) {
		// CHECKSTYLE:ON
		this.lastIpAccess = lastIpAccess;
	}
	
	/**
	 * Gets the value of the attribute {@link #nif}.
	 * @return the value of the attribute {@link #nif}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	@Column(name = "NIF", nullable = false, length = NumberConstants.NUM100)
	@JsonView(DataTablesOutput.View.class)
	public String getNif() {
		// CHECKSTYLE:ON
		return nif;
	}

	/**
	 * Sets the value of the attribute {@link #nif}.
	 * @param nameParam The value for the attribute {@link #nif}.
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Hibernate JPA needs not final access methods.
	public void setNif(String nifParam) {
		// CHECKSTYLE:ON
		this.nif = nifParam;
	}

}
