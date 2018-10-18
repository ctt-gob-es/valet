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
 * @version 1.0, 02/10/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/** 
 * <p>Class that maps the <i>KEYSTORE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 02/10/2018.
 */
@Entity
@Table(name = "MAIL")
public class Mail implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = 707464190994966811L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idMail;

	/**
	 * Attribute that represents a valid email address.
	 */
	private String emailAddress;

	/**
	 * Attribute that represents the alarms using this mail.
	 */
	private Set<Alarm> alarm;

	/**
	 * Gets the value of the attribute {@link #idMail}.
	 * @return the value of the attribute {@link #idMail}.
	 */
	@Id
	@Column(name = "ID_MAIL", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_mail")
	@GenericGenerator(name = "sq_mail", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_MAIL"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdMail() {
		return idMail;
	}

	/**
	 * Sets the value of the attribute {@link #idMail}.
	 * @param idMailParam The value for the attribute {@link #idMail}.
	 */
	public void setIdMail(Long idMailParam) {
		this.idMail = idMailParam;
	}

	/**
	 * Gets the value of the attribute {@link #emailAddress}.
	 * @return the value of the attribute {@link #emailAddress}.
	 */
	@Column(name = "EMAIL_ADDRESS", unique = true, nullable = false, precision = NumberConstants.NUM200)
	@JsonView(DataTablesOutput.View.class)
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the value of the attribute {@link #emailAddress}.
	 * @param emailAddressParam The value for the attribute {@link #emailAddress}.
	 */
	public void setEmailAddress(String emailAddressParam) {
		this.emailAddress = emailAddressParam;
	}

	/**
	 * Gets the value of the attribute {@link #alarm}.
	 * @return the value of the attribute {@link #alarm}.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "R_ALARM_MAIL", joinColumns = @JoinColumn(name = "ID_MAIL", referencedColumnName = "ID_MAIL", nullable = false), inverseJoinColumns = @JoinColumn(name = "ID_ALARM", referencedColumnName = "ID_ALARM", nullable = false))
	public Set<Alarm> getAlarm() {
		return alarm;
	}

	/**
	 * Sets the value of the attribute {@link #alarm}.
	 * @param alarmParam The value for the attribute {@link #alarm}.
	 */
	public void setAlarm(Set<Alarm> alarmParam) {
		this.alarm = alarmParam;
	}

}
