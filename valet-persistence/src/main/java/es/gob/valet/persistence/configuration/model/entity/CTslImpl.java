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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.CTslImpl.java.</p>
 * <b>Description:</b><p>Class that maps the <i>C_TSL_IMPL</i> database table as a Plain Old Java Object.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17 jul. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 17 jul. 2018.
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
 * <p>Class that maps the <i>C_TSL_IMPL</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17 jul. 2018.
 */
@Entity
@Table(name = "C_TSL_IMPL")
public class CTslImpl implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -2075777713836623804L;
	/**
	 * Attribute that represents the object ID.
	 */
	private Long idCTSLImpl;

	/**
	 * Attribute that represents the ETSI TS number specification for TSL.
	 */
	private String specification;

	/**
	 * Attribute that represents the ETSI TS specification version. 
	 */
	private String version;

	/**
	 * Attribute that represents the namespace used in this specification and version for the TSL. 
	 */
	private String namespace;

	/**
	 * Gets the value of the attribute {@link #specification}.
	 * @return the value of the attribute {@link #specification}.
	 */
	@Column(name = "SPECIFICATION", nullable = false, length = NumberConstants.NUM6)
	public String getSpecification() {
		return specification;
	}

	/**
	 * Sets the value of the attribute {@link #specification}.
	 * @param specificationParam The value for the attribute {@link #specification}.
	 */
	public void setSpecification(String specificationParam) {
		this.specification = specificationParam;
	}

	/**
	 * Gets the value of the attribute {@link #version}.
	 * @return the value of the attribute {@link #version}.
	 */
	@Column(name = "VERSION", nullable = false, length = NumberConstants.NUM5)
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the value of the attribute {@link #version}.
	 * @param versionParam The value for the attribute {@link #version}.
	 */
	public void setVersion(String versionParam) {
		this.version = versionParam;
	}

	/**
	 * Gets the value of the attribute {@link #namespace}.
	 * @return the value of the attribute {@link #namespace}.
	 */
	@Column(name = "NAMESPACE", nullable = false, length = NumberConstants.NUM256)
	public String getNamespace() {
		return namespace;
	}

	/**
	 * Sets the value of the attribute {@link #namespace}.
	 * @param namespaceParam The value for the attribute {@link #namespace}.
	 */
	public void setNamespace(String namespaceParam) {
		this.namespace = namespaceParam;
	}

	/**
	 * Gets the value of the attribute {@link #idCTSLImpl}.
	 * @return the value of the attribute {@link #idCTSLImpl}.
	 */
	@Id
	@Column(name = "ID_TSL_IMPL", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(generator = "sq_c_tsl_impl")
	@GenericGenerator(name = "sq_c_tsl_impl", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SQ_C_TSL_IMPL"), @Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@JsonView(DataTablesOutput.View.class)
	public Long getIdCTSLImpl() {
		return idCTSLImpl;
	}

	/**
	 * Sets the value of the attribute {@link #idCTSLImpl}.
	 * @param idCTSLImplParam The value for the attribute {@link #idCTSLImpl}.
	 */
	public void setIdCTSLImpl(Long idCTSLImplParam) {
		this.idCTSLImpl = idCTSLImplParam;
	}

}
