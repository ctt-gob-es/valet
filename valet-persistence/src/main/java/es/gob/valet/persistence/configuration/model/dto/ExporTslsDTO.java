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
 * <b>File:</b><p>es.gob.valet.dto.ExporTslsDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Export TSLs DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/03/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

/** 
 * <p>Class that represents an object that relates the code of a to the Export TSLs DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/03/2025.
 */
public class ExporTslsDTO {

	/**
	 * Attribute that represents the version select for user
	 */
	private ValetVersionDTO valetVersionDTO;
	
	/**
	 * Gets the value of the attribute {@link #valetVersionDTO}.
	 * @return the value of the attribute {@link #valetVersionDTO}.
	 */
	public ValetVersionDTO getValetVersionDTO() {
		return valetVersionDTO;
	}

	
	/**
	 * Sets the value of the attribute {@link #valetVersionDTO}.
	 * @param valetVersionDTO The value for the attribute {@link #valetVersionDTO}.
	 */
	public void setValetVersionDTO(ValetVersionDTO valetVersionDTO) {
		this.valetVersionDTO = valetVersionDTO;
	}
	
	
}
