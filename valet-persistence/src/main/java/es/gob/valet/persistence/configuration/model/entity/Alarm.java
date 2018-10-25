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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.Mail.java.</p>
 * <b>Description:</b><p>Class that maps the <i>MAIL</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 25/10/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>KEYSTORE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 25/10/2018.
 */
@Entity
@Table(name = "ALARM")
public class Alarm implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -6922902321185485093L;

	/**
	 * Attribute that represents the object ID.
	 */
	private String idAlarm;

	/**
	 * Attribute that represents the description for the alarm.
	 */
	private String description;

	/**
	 * Attribute that represents a time of block.
	 */
	private Long timeBlock;

	/**
	 * Attribute that represents if an alarm is active or not.
	 */
	private Boolean active;

	/**
	 * Attribute that represents a list of person where alarm is sended.
	 */
	private List<Mail> mails;

	/**
	 * Gets the value of the attribute {@link #idAlarm}.
	 * @return the value of the attribute {@link #idAlarm}.
	 */
	@Id
	@Column(name = "ID_ALARM", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@JsonView(DataTablesOutput.View.class)
	public String getIdAlarm() {
		return idAlarm;
	}

	/**
	 * Sets the value of the attribute {@link #idAlarm}.
	 * @param idAlarmParam The value for the attribute {@link #idAlarm}.
	 */
	public void setIdAlarm(String idAlarmParam) {
		this.idAlarm = idAlarmParam;
	}

	/**
	 * Gets the value of the attribute {@link #description}.
	 * @return the value of the attribute {@link #description}.
	 */
	@Column(name = "DESCRIPTION", nullable = true, precision = NumberConstants.NUM200)
	@JsonView(DataTablesOutput.View.class)
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the attribute {@link #description}.
	 * @param descriptionParam The value for the attribute {@link #description}.
	 */
	public void setDescription(String descriptionParam) {
		this.description = descriptionParam;
	}

	/**
	 * Gets the value of the attribute {@link #timeBlock}.
	 * @return the value of the attribute {@link #timeBlock}.
	 */
	@Column(name = "TIME_BLOCK", nullable = true, precision = NumberConstants.NUM19)
	@JsonView(DataTablesOutput.View.class)
	public Long getTimeBlock() {
		return timeBlock;
	}

	/**
	 * Sets the value of the attribute {@link #timeBlocked}.
	 * @param timeBlockParam The value for the attribute {@link #timeBlock}.
	 */
	public void setTimeBlock(Long timeBlockParam) {
		this.timeBlock = timeBlockParam;
	}

	/**
	 * Gets the value of the attribute {@link #active}.
	 * @return the value of the attribute {@link #active}.
	 */
	@Column(name = "ACTIVE", nullable = true, precision = NumberConstants.NUM1)
	@Type(type = "yes_no")
	@JsonView(DataTablesOutput.View.class)
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets the value of the attribute {@link #active}.
	 * @param activeParam The value for the attribute {@link #active}.
	 */
	public void setActive(Boolean activeParam) {
		this.active = activeParam;
	}

	/**
	 * Gets the value of the attribute {@link #mails}.
	 * @return the value of the attribute {@link #mails}.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "R_ALARM_MAIL", joinColumns = @JoinColumn(name = "ID_ALARM", referencedColumnName = "ID_ALARM", nullable = false), inverseJoinColumns = @JoinColumn(name = "ID_MAIL", referencedColumnName = "ID_MAIL", nullable = false))
	@JsonView(DataTablesOutput.View.class)
	public List<Mail> getMails() {
		return mails;
	}

	/**
	 * Sets the value of the attribute {@link #mails}.
	 * @param mailsParam The value for the attribute {@link #mails}.
	 */
	public void setMails(List<Mail> mailsParam) {
		this.mails = mailsParam;
	}

}
