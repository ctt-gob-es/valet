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
 * <b>File:</b><p>es.gob.valet.dto.CTslImplDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Catalog TSLs DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.1, 06/05/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

import es.gob.valet.persistence.configuration.model.entity.CTslImpl;

/** 
 * <p>Class that represents an object that relates the code of a to the Catalog TSLs DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 06/05/2025.
 */
public class CTslImplDTO {

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTSLImpl;

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
	 * Default constructor for the CTslImplDTO class.
	 * This constructor initializes a new instance of the CTslImplDTO with default values.
	 */
	public CTslImplDTO(){}

	/**
	 * Constructor that initializes a CTslImplDTO instance using the provided CTslImpl object.
	 * This constructor maps the properties of the given CTslImpl object to the corresponding fields in the CTslImplDTO.
	 *
	 * @param cTslImpl the CTslImpl object to be used for initializing the DTO
	 */
	public CTslImplDTO(CTslImpl cTslImpl) {
	    this.idTSLImpl = cTslImpl.getIdTSLImpl();
	    this.namespace = cTslImpl.getNamespace();
	    this.specification = cTslImpl.getSpecification();
	    this.version = cTslImpl.getVersion();
	}

	/**
	 * Gets the value of the attribute {@link #idTSLImpl}.
	 * @return the value of the attribute {@link #idTSLImpl}.
	 */
	public Long getIdTSLImpl() {
		return idTSLImpl;
	}

	
	/**
	 * Sets the value of the attribute {@link #idTSLImpl}.
	 * @param idTSLImpl The value for the attribute {@link #idTSLImpl}.
	 */
	public void setIdTSLImpl(Long idTSLImpl) {
		this.idTSLImpl = idTSLImpl;
	}

	
	/**
	 * Gets the value of the attribute {@link #specification}.
	 * @return the value of the attribute {@link #specification}.
	 */
	public String getSpecification() {
		return specification;
	}

	
	/**
	 * Sets the value of the attribute {@link #specification}.
	 * @param specification The value for the attribute {@link #specification}.
	 */
	public void setSpecification(String specification) {
		this.specification = specification;
	}

	
	/**
	 * Gets the value of the attribute {@link #version}.
	 * @return the value of the attribute {@link #version}.
	 */
	public String getVersion() {
		return version;
	}

	
	/**
	 * Sets the value of the attribute {@link #version}.
	 * @param version The value for the attribute {@link #version}.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	
	/**
	 * Gets the value of the attribute {@link #namespace}.
	 * @return the value of the attribute {@link #namespace}.
	 */
	public String getNamespace() {
		return namespace;
	}

	
	/**
	 * Sets the value of the attribute {@link #namespace}.
	 * @param namespace The value for the attribute {@link #namespace}.
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	
}
