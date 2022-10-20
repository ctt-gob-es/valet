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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.dto.MappingCertTslsDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Mapping Certificate TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 21/09/2022.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

/** 
 * <p>Class that represents an object that relates the code of a to the Mapping Certificate TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 21/09/2022.
 */
public class MappingCertTslsDTO implements Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = -6764381419139749795L;

	/**
	 * Attribute that represents the object ID.
	 */
	@JsonView(DataTablesOutput.View.class)
	private Long idMappingCertTsl;
	
	/**
	 * Attribute that represents the name of the mapping certificate tsls.
	 */
	@JsonView(DataTablesOutput.View.class)
	private String name;

	/**
	 * Gets the value of the attribute {@link #idMappingCertTsl}.
	 * @return the value of the attribute {@link #idMappingCertTsl}.
	 */
	public Long getIdMappingCertTsl() {
		return idMappingCertTsl;
	}

	/**
	 * Sets the value of the attribute {@link #idMappingCertTsl}.
	 * @param idMappingCertTsl The value for the attribute {@link #idMappingCertTsl}.
	 */
	public void setIdMappingCertTsl(Long idMappingCertTsl) {
		this.idMappingCertTsl = idMappingCertTsl;
	}

	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param name The value for the attribute {@link #name}.
	 */
	public void setName(String name) {
		this.name = name;
	}

}
