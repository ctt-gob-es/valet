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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.CStatusCertificate.java.</p>
 * <b>Description:</b><p>Class that maps the <i>C_STATUS_CERTIFICATE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that maps the <i>C_STATUS_CERTIFICATE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/09/2018.
 */
@Entity
@Table(name = "C_STATUS_CERTIFICATES")
public class CStatusCertificate implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 1587072570533127062L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idStatusCertificate;

	/**
	 * Attribute that represents the name of the token with the description stored in properties file for internationalization.
	 */
	private String tokenName;

	/**
	 * Gets the value of the attribute {@link #idStatusCertificate}.
	 * @return the value of the attribute {@link #idStatusCertificate}.
	 */
	@Id
	@Column(name = "ID_STATUS_CERTIFICATE", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@JsonView(DataTablesOutput.View.class)
	public Long getIdStatusCertificate() {
		return idStatusCertificate;
	}

	/**
	 * Sets the value of the attribute {@link #idStatusCertificate}.
	 * @param idStatusCertificateParam The value for the attribute {@link #idStatusCertificate}.
	 */
	public void setIdStatusCertificate(Long idStatusCertificateParam) {
		this.idStatusCertificate = idStatusCertificateParam;
	}

	/**
	 * Gets the value of the attribute {@link #tokenName}.
	 * @return the value of the attribute {@link #tokenName}.
	 */
	@Column(name = "TOKEN_NAME", nullable = false, length = NumberConstants.NUM30)
	@JsonView(DataTablesOutput.View.class)
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * Sets the value of the attribute {@link #tokenName}.
	 * @param tokenNameParam The value for the attribute {@link #tokenName}.
	 */
	public void setTokenName(String tokenNameParam) {
		this.tokenName = tokenNameParam;
	}

}
