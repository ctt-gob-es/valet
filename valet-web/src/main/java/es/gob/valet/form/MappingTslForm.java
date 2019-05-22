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
 * <b>Description:</b><p>Class that represents the backing form for adding/editing the mapping values for a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/08/2018.</p>
 * @author Gobierno de España.
 *@version 1.4, 22/05/2019.
 */
package es.gob.valet.form;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * <p>Class that represents the backing form for adding/editing the mapping values for a TSL</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4, 22/05/2019.
 */
public class MappingTslForm {

	/**
	 * Attribute that represents the ID of the country/region for this TSL.
	 */
	private Long idTslCountryRegion;

	/**
	 * Attribute that represents the ID of the TslCountryRegionMapping.
	 */
	private Long idTslCountryRegionMapping;

	/**
	 * Attribute that represents the name of the country or region.
	 */
	private String nameCountryRegion;

	/**
	 * Attribute that represents the code of the country or region.
	 */
	private String codeCountryRegion;

	/**
	 * Attribute that represents the identificator for the logical mapping.
	 */
	private String mappingIdentificator;
	
	/**
	 * Attribute that represents the value for the mapping.
	 */
	private String mappingFreeValue;
	
	/**
	 * Attribute that represents the value for the mapping.
	 */
	private String mappingSimpleValue;

	/**
	 * Attribute that represents the association type identifier.
	 */
	private Long idMappingType;

	/**
	 * Attribute that represents index of the row of the selected mapping.
	 */
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
	 * @param idTslCountryRegionParam The value for the attribute {@link #idTslCountryRegion}.
	 */
	public void setIdTslCountryRegion(Long idTslCountryRegionParam) {
		this.idTslCountryRegion = idTslCountryRegionParam;
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
	 * @param idTslCountryRegionMappingParam The value for the attribute {@link #idTslCountryRegionMapping}.
	 */
	public void setIdTslCountryRegionMapping(Long idTslCountryRegionMappingParam) {
		this.idTslCountryRegionMapping = idTslCountryRegionMappingParam;
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
	 * @param nameCountryRegionParam The value for the attribute {@link #nameCountryRegion}.
	 */
	public void setNameCountryRegion(String nameCountryRegionParam) {
		this.nameCountryRegion = nameCountryRegionParam;
	}

	/**
	 * Gets the value of the attribute {@link #codeCountryRegion}.
	 * @return the value of the attribute {@link #codeCountryRegion}.
	 */
	public String getCodeCountryRegion() {
		return codeCountryRegion;
	}

	/**
	 * Sets the value of the attribute {@link #codeCountryRegion}.
	 * @param codeCountryRegionParam The value for the attribute {@link #codeCountryRegion}.
	 */
	public void setCodeCountryRegion(String codeCountryRegionParam) {
		this.codeCountryRegion = codeCountryRegionParam;
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
	 * @param mappingIdentificatorParam The value for the attribute {@link #mappingIdentificator}.
	 */
	public void setMappingIdentificator(String mappingIdentificatorParam) {
		this.mappingIdentificator = mappingIdentificatorParam;
	}
	
	/**
	 * Gets the value of the attribute {@link #mappingFreeValue}.
	 * @return the value of the attribute {@link #mappingFreeValue}.
	 */
	@JsonView(View.class)
	public String getMappingFreeValue() {
		return mappingFreeValue;
	}

	/**
	 * Sets the value of the attribute {@link #mappingFreeValue}.
	 * @param mappingFreeValueParam The value for the attribute {@link #mappingFreeValue}.
	 */
	public void setMappingFreeValue(String mappingFreeValueParam) {
		this.mappingFreeValue = mappingFreeValueParam;
	}
	
	/**
	 * Gets the value of the attribute {@link #mappingSimpleValue}.
	 * @return the value of the attribute {@link #mappingSimpleValue}.
	 */
	@JsonView(View.class)
	public String getMappingSimpleValue() {
		return mappingSimpleValue;
	}

	/**
	 * Sets the value of the attribute {@link #mappingSimpleValue}.
	 * @param mappingSimpleValueParam The value for the attribute {@link #mappingSimpleValue}.
	 */
	public void setMappingSimpleValue(String mappingSimpleValueParam) {
		this.mappingSimpleValue = mappingSimpleValueParam;
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
	 * @param rowIndexMappingParam The value for the attribute {@link #rowIndexMapping}.
	 */
	public void setRowIndexMapping(String rowIndexMappingParam) {
		this.rowIndexMapping = rowIndexMappingParam;
	}

	/**
	 * Gets the value of the attribute {@link #idMappingType}.
	 * @return the value of the attribute {@link #idMappingType}.
	 */
	public Long getIdMappingType() {
		return idMappingType;
	}

	/**
	 * Sets the value of the attribute {@link #idMappingType}.
	 * @param idMappingTypeParam The value for the attribute {@link #idMappingType}.
	 */
	public void setIdMappingType(Long idMappingTypeParam) {
		this.idMappingType = idMappingTypeParam;
	}

	/**
	 *
	 * <p>Class.</p>
	 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
	 * @version 1.0, 12 sept. 2018.
	 */
	public interface View {
	}

}
