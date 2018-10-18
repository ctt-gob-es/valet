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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TslCountryRegion.java.</p>
 * <b>Description:</b><p>Class the maps the <i>TSL_COUNTRY_REGION</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>11/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 11/09/2018.
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
 * <p>Class the maps the <i>TSL_COUNTRY_REGION</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 11/09/2018.
 */
@Entity
@Table(name = "TSL_COUNTRY_REGION")
public class TslCountryRegion implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 8054326752005320182L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTslCountryRegion;

	/**
	 * Attribute that represents the country/region code for a TSL (ISO 3166).
	 */
	private String countryRegionCode;

	/**
	 * Attribute that represents the country/region name.
	 */
	private String countryRegionName;

	/**
	 * Gets the value of the attribute {@link #idTslCountryRegion}.
	 * @return the value of the attribute {@link #idTslCountryRegion}.
	 */
	@Id
	@Column(name = "ID_COUNTRY_REGION", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@JsonView(DataTablesOutput.View.class)
	public Long getIdTslCountryRegion() {
		return idTslCountryRegion;
	}

	/**
	 * Sets the value of the attribute {@link #idTslCountryRegion}.
	 * @param idTslCountryRegionParam The value for the attribute {@link #idTslCountryRegion}.
	 */
	public void setIdTslCountryRegion(Long idTslCountryRegionParam) {
		this.idTslCountryRegion = idTslCountryRegionParam;
	}

	/**
	 * Gets the value of the attribute {@link #countryRegionCode}.
	 * @return the value of the attribute {@link #countryRegionCode}.
	 */
	@Column(name = "COUNTRY_REGION_CODE", nullable = false, length = NumberConstants.NUM16)
	@JsonView(DataTablesOutput.View.class)
	public String getCountryRegionCode() {
		return countryRegionCode;
	}

	/**
	 * Sets the value of the attribute {@link #countryRegionCode}.
	 * @param countryRegionCodeParam The value for the attribute {@link #countryRegionCode}.
	 */

	public void setCountryRegionCode(String countryRegionCodeParam) {
		this.countryRegionCode = countryRegionCodeParam;
	}

	/**
	 * Gets the value of the attribute {@link #countryRegionName}.
	 * @return the value of the attribute {@link #countryRegionName}.
	 */
	@Column(name = "COUNTRY_REGION_NAME", nullable = false, length = NumberConstants.NUM128)
	@JsonView(DataTablesOutput.View.class)
	public String getCountryRegionName() {
		return countryRegionName;
	}

	/**
	 * Sets the value of the attribute {@link #countryRegionName}.
	 * @param countryRegionNameParam The value for the attribute {@link #countryRegionName}.
	 */
	public void setCountryRegionName(String countryRegionNameParam) {
		this.countryRegionName = countryRegionNameParam;
	}

}
