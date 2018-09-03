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
 * <b>File:</b><p>es.gob.valet.form.MappingForm.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 12 ago. 2018.
 */
package es.gob.valet.form;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.form.TslForm.View;

/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 12 ago. 2018.
 */
public class MappingTslForm {
	private Long idTslCountryRegion;
	private Long idTslCountryRegionMapping;
	private String nameCountryRegion;
	private String mappingIdentificator;
	private String mappingValue;
	private String rowIndexMapping;
	/**
	 * Gets the value of the attribute {@link #idTslCountryRegion}.
	 * @return the value of the attribute {@link #idTslCountryRegion}.
	 */
	@JsonView(View.class)
	public Long getIdTslCountryRegion() {
		return idTslCountryRegion;
	}
	
	/**
	 * Sets the value of the attribute {@link #idTslCountryRegion}.
	 * @param idTslCountryRegion The value for the attribute {@link #idTslCountryRegion}.
	 */
	public void setIdTslCountryRegion(Long idTslCountryRegion) {
		this.idTslCountryRegion = idTslCountryRegion;
	}
	
	/**
	 * Gets the value of the attribute {@link #idTslCountryRegionMapping}.
	 * @return the value of the attribute {@link #idTslCountryRegionMapping}.
	 */
	@JsonView(View.class)
	public Long getIdTslCountryRegionMapping() {
		return idTslCountryRegionMapping;
	}
	
	/**
	 * Sets the value of the attribute {@link #idTslCountryRegionMapping}.
	 * @param idTslCountryRegionMapping The value for the attribute {@link #idTslCountryRegionMapping}.
	 */
	public void setIdTslCountryRegionMapping(Long idTslCountryRegionMapping) {
		this.idTslCountryRegionMapping = idTslCountryRegionMapping;
	}
	
	/**
	 * Gets the value of the attribute {@link #nameCountryRegion}.
	 * @return the value of the attribute {@link #nameCountryRegion}.
	 */
	@JsonView(View.class)
	public String getNameCountryRegion() {
		return nameCountryRegion;
	}
	
	/**
	 * Sets the value of the attribute {@link #nameCountryRegion}.
	 * @param nameCountryRegion The value for the attribute {@link #nameCountryRegion}.
	 */
	public void setNameCountryRegion(String nameCountryRegion) {
		this.nameCountryRegion = nameCountryRegion;
	}
	
	/**
	 * Gets the value of the attribute {@link #mappingIdentificator}.
	 * @return the value of the attribute {@link #mappingIdentificator}.
	 */
	@JsonView(View.class)
	public String getMappingIdentificator() {
		return mappingIdentificator;
	}
	
	/**
	 * Sets the value of the attribute {@link #mappingIdentificator}.
	 * @param mappingIdentificator The value for the attribute {@link #mappingIdentificator}.
	 */
	public void setMappingIdentificator(String mappingIdentificator) {
		this.mappingIdentificator = mappingIdentificator;
	}
	
	/**
	 * Gets the value of the attribute {@link #mappingValue}.
	 * @return the value of the attribute {@link #mappingValue}.
	 */
	@JsonView(View.class)
	public String getMappingValue() {
		return mappingValue;
	}
	
	
	
	/**
	 * Sets the value of the attribute {@link #mappingValue}.
	 * @param mappingValue The value for the attribute {@link #mappingValue}.
	 */
	public void setMappingValue(String mappingValue) {
		this.mappingValue = mappingValue;
	}
	
	
	/**
	 * Gets the value of the attribute {@link #rowIndexMapping}.
	 * @return the value of the attribute {@link #rowIndexMapping}.
	 */
	@JsonView(View.class)
	public String getRowIndexMapping() {
		return rowIndexMapping;
	}

	
	/**
	 * Sets the value of the attribute {@link #rowIndexMapping}.
	 * @param rowIndexMapping The value for the attribute {@link #rowIndexMapping}.
	 */
	public void setRowIndexMapping(String rowIndexMapping) {
		this.rowIndexMapping = rowIndexMapping;
	}

	public interface View {
	}	


}
