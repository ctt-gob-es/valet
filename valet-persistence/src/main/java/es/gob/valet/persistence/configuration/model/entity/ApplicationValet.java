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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.Application.java.</p>
 * <b>Description:</b><p>Class that maps the <i>APPLICATION</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>10/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 10/12/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;


/**
 * <p>Class that maps the <i>APPLICATION</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 10/12/2018.
 */
@Entity
@Table (name = "APPLICATION")
public class ApplicationValet implements Serializable {

	/**
	 * Attribute that represents the class serial version.
	 */
	private static final long serialVersionUID = 3584845740448521252L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idApplication;

	/**
	 * Attribute that represents the identificator of the application in the system.
	 */
	private String identificator;

	/**
	 * Attribute that represents the name of the application.
	 */
	private String name;
	/**
	 * Attribute that represents the name of the responsible of the application.
	 */
	private String responsibleName;

	/**
	 * Attribute that represents the phone number of the responsible of the application.
	 */
	private String responsiblePhone;

	/**
	 * Attribute that represents the surnames of the responsible of the application.
	 */
	private String responsibleSurnames;

	/**
	 * Attribute that represents the email of the responsible of the application.
	 */
	private String responsibleMail;


	/**
	 * Gets the value of the attribute {@link #idApplication}.
	 * @return the value of the attribute {@link #idApplication}.
	 */
	@Id
	@Column(name = "ID_APPLICATION", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_application")
	@GenericGenerator(name = "sq_application", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_APPLICATION"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdApplication() {
		return idApplication;
	}


	/**
	 * Sets the value of the attribute {@link #idApplication}.
	 * @param idApplicationParam The value for the attribute {@link #idApplication}.
	 */
	public void setIdApplication(Long idApplicationParam) {
		this.idApplication = idApplicationParam;
	}


	/**
	 * Gets the value of the attribute {@link #identificator}.
	 * @return the value of the attribute {@link #identificator}.
	 */
	@Column(name = "IDENTIFICATOR", length = NumberConstants.NUM2000, nullable = false, unique = true)
	@JsonView(DataTablesOutput.View.class)
	public String getIdentificator() {
		return identificator;
	}


	/**
	 * Sets the value of the attribute {@link #identificator}.
	 * @param identificatorParam The value for the attribute {@link #identificator}.
	 */
	public void setIdentificator(String identificatorParam) {
		this.identificator = identificatorParam;
	}



	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	@Column(name = "NAME", nullable = false, length = NumberConstants.NUM255)
	@JsonView(DataTablesOutput.View.class)
	public String getName() {
		return name;
	}



	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param nameParam The value for the attribute {@link #name}.
	 */
	public void setName(String nameParam) {
		this.name = nameParam;
	}


	/**
	 * Gets the value of the attribute {@link #responsibleName}.
	 * @return the value of the attribute {@link #responsibleName}.
	 */
	@Column(name = "RESPONSIBLE_NAME", length = NumberConstants.NUM255)
	@JsonView(DataTablesOutput.View.class)
	public String getResponsibleName() {
		return responsibleName;
	}


	/**
	 * Sets the value of the attribute {@link #responsibleName}.
	 * @param responsibleNameParam The value for the attribute {@link #responsibleName}.
	 */
	public void setResponsibleName(String responsibleNameParam) {
		this.responsibleName = responsibleNameParam;
	}


	/**
	 * Gets the value of the attribute {@link #responsiblePhone}.
	 * @return the value of the attribute {@link #responsiblePhone}.
	 */
	@Column(name = "RESPONSIBLE_PHONE", length = NumberConstants.NUM255)
	public String getResponsiblePhone() {
		return responsiblePhone;
	}


	/**
	 * Sets the value of the attribute {@link #responsiblePhone}.
	 * @param responsiblePhoneParam The value for the attribute {@link #responsiblePhone}.
	 */
	public void setResponsiblePhone(String responsiblePhoneParam) {
		this.responsiblePhone = responsiblePhoneParam;
	}


	/**
	 * Gets the value of the attribute {@link #responsibleSurnames}.
	 * @return the value of the attribute {@link #responsibleSurnames}.
	 */
	@Column(name = "RESPONSIBLE_SURNAMES", length = NumberConstants.NUM255)
	@JsonView(DataTablesOutput.View.class)
	public String getResponsibleSurnames() {
		return responsibleSurnames;
	}


	/**
	 * Sets the value of the attribute {@link #responsibleSurnames}.
	 * @param responsibleSurnamesParam The value for the attribute {@link #responsibleSurnames}.
	 */
	public void setResponsibleSurnames(String responsibleSurnamesParam) {
		this.responsibleSurnames = responsibleSurnamesParam;
	}


	/**
	 * Gets the value of the attribute {@link #responsibleMail}.
	 * @return the value of the attribute {@link #responsibleMail}.
	 */
	@Column(name = "RESPONSIBLE_MAIL", length = NumberConstants.NUM255)
	public String getResponsibleMail() {
		return responsibleMail;
	}


	/**
	 * Sets the value of the attribute {@link #responsibleMail}.
	 * @param responsibleMailParam The value for the attribute {@link #responsibleMail}.
	 */
	public void setResponsibleMail(String responsibleMailParam) {
		this.responsibleMail = responsibleMailParam;
	}

}
