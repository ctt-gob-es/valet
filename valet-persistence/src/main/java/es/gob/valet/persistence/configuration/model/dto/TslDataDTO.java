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
 * <b>File:</b><p>es.gob.valet.dto.TslDataDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the TSL Data DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.2, 21/05/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.util.Base64;

import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.persistence.configuration.model.entity.TslData;

/** 
 * <p>Class that represents an object that relates the code of a to the TSL Data DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 21/05/2025.
 */
public class TslDataDTO {

	/**
	 * Attribute that represents the identifier of TSL.
	 */
	private Long idTslData;

	/**
	 * Attribute that represents the sequence number of this TSL.
	 */
	private Integer sequenceNumber;

	/**
	 * Attribute that represents a TSL responsible.
	 */
	private String responsible;

	/**
	 * Attribute that represents the issue date of this TSL.
	 */
	private String issueDate;

	/**
	 * Attribute that represents the expiration Date for this TSL.
	 */
	private String expirationDate;

	/**
	 * Attribute that represents the URI where this TSL is officially located.
	 */
	private String uriTslLocation;

	/**
	 * Attribute that represents the XML document of this TSL.
	 */
	private String xmlDocument;

	/**
	 * Attribute that represents the legible document of this TSL.
	 */
	private String legibleDocument;

	/**
	 * Attribute that represents the ETSI TS specification and version of this TSL.
	 */
	private CTslImplDTO cTslImplDTO;

	/**
	 * Attribute that represents the country/region for this TSL.
	 */
	private TslCountryRegionDTO tslCountryRegionDTO;

	/**
	 * Attribute that represents if a new TSL are available.
	 */
	private String newTSLAvailable;

	/**
	 * Attribute that represents the last new TSL available are find.
	 */
	private String lastNewTSLAvailableFind;

	/**
	 * Default constructor for {@code TslDataDTO}.
	 * <p>
	 * Initializes an empty instance of TslDataDTO.
	 * Useful for frameworks that require a no-argument constructor, such as serialization libraries or dependency injection tools.
	 */
	public TslDataDTO() {}
	
	/**
	 * Constructs a TslDataDTO from a TslData entity.
	 * 
	 * @param tslData the TslData entity to create the DTO from
	 */
	public TslDataDTO(TslData tslData) {
		this.expirationDate = (tslData.getExpirationDate() != null) ? UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_STANDARD, tslData.getExpirationDate()) : "";
		this.idTslData = tslData.getIdTslData();
		this.issueDate = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_STANDARD, tslData.getIssueDate());
		this.lastNewTSLAvailableFind = tslData.getLastNewTSLAvailableFind() != null ? UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_STANDARD, tslData.getLastNewTSLAvailableFind()) : "";
		this.legibleDocument = tslData.getLegibleDocument() != null ? Base64.getEncoder().encodeToString(tslData.getLegibleDocument()) : "";
		this.newTSLAvailable = tslData.getNewTSLAvailable();
		this.responsible = tslData.getResponsible() != null ? tslData.getResponsible() : "";
		this.sequenceNumber = tslData.getSequenceNumber();
		this.tslCountryRegionDTO = new TslCountryRegionDTO(tslData.getTslCountryRegion(), false, null);
		this.cTslImplDTO = new CTslImplDTO(tslData.getTslImpl());
		this.uriTslLocation = tslData.getUriTslLocation();
		this.xmlDocument = Base64.getEncoder().encodeToString(tslData.getXmlDocument());
	}

	/**
	 * Gets the value of the attribute {@link #idTslData}.
	 * @return the value of the attribute {@link #idTslData}.
	 */
	public Long getIdTslData() {
		return idTslData;
	}

	/**
	 * Sets the value of the attribute {@link #idTslData}.
	 * @param idTslData The value for the attribute {@link #idTslData}.
	 */
	public void setIdTslData(Long idTslData) {
		this.idTslData = idTslData;
	}

	/**
	 * Gets the value of the attribute {@link #sequenceNumber}.
	 * @return the value of the attribute {@link #sequenceNumber}.
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Sets the value of the attribute {@link #sequenceNumber}.
	 * @param sequenceNumber The value for the attribute {@link #sequenceNumber}.
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * Gets the value of the attribute {@link #responsible}.
	 * @return the value of the attribute {@link #responsible}.
	 */
	public String getResponsible() {
		return responsible;
	}

	/**
	 * Sets the value of the attribute {@link #responsible}.
	 * @param responsible The value for the attribute {@link #responsible}.
	 */
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	/**
	 * Gets the value of the attribute {@link #issueDate}.
	 * @return the value of the attribute {@link #issueDate}.
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * Sets the value of the attribute {@link #issueDate}.
	 * @param issueDate The value for the attribute {@link #issueDate}.
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * Gets the value of the attribute {@link #expirationDate}.
	 * @return the value of the attribute {@link #expirationDate}.
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the value of the attribute {@link #expirationDate}.
	 * @param expirationDate The value for the attribute {@link #expirationDate}.
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets the value of the attribute {@link #uriTslLocation}.
	 * @return the value of the attribute {@link #uriTslLocation}.
	 */
	public String getUriTslLocation() {
		return uriTslLocation;
	}

	/**
	 * Sets the value of the attribute {@link #uriTslLocation}.
	 * @param uriTslLocation The value for the attribute {@link #uriTslLocation}.
	 */
	public void setUriTslLocation(String uriTslLocation) {
		this.uriTslLocation = uriTslLocation;
	}

	/**
	 * Gets the value of the attribute {@link #xmlDocument}.
	 * @return the value of the attribute {@link #xmlDocument}.
	 */
	public String getXmlDocument() {
		return xmlDocument;
	}

	/**
	 * Sets the value of the attribute {@link #xmlDocument}.
	 * @param xmlDocument The value for the attribute {@link #xmlDocument}.
	 */
	public void setXmlDocument(String xmlDocument) {
		this.xmlDocument = xmlDocument;
	}

	/**
	 * Gets the value of the attribute {@link #legibleDocument}.
	 * @return the value of the attribute {@link #legibleDocument}.
	 */
	public String getLegibleDocument() {
		return legibleDocument;
	}

	/**
	 * Sets the value of the attribute {@link #legibleDocument}.
	 * @param legibleDocument The value for the attribute {@link #legibleDocument}.
	 */
	public void setLegibleDocument(String legibleDocument) {
		this.legibleDocument = legibleDocument;
	}

	/**
	 * Gets the value of the attribute {@link #cTslImplDTO}.
	 * @return the value of the attribute {@link #cTslImplDTO}.
	 */
	public CTslImplDTO getcTslImplDTO() {
		return cTslImplDTO;
	}

	/**
	 * Sets the value of the attribute {@link #cTslImplDTO}.
	 * @param cTslImplDTO The value for the attribute {@link #cTslImplDTO}.
	 */
	public void setcTslImplDTO(CTslImplDTO cTslImplDTO) {
		this.cTslImplDTO = cTslImplDTO;
	}

	/**
	 * Gets the value of the attribute {@link #tslCountryRegionDTO}.
	 * @return the value of the attribute {@link #tslCountryRegionDTO}.
	 */
	public TslCountryRegionDTO getTslCountryRegionDTO() {
		return tslCountryRegionDTO;
	}

	/**
	 * Sets the value of the attribute {@link #tslCountryRegionDTO}.
	 * @param tslCountryRegionDTO The value for the attribute {@link #tslCountryRegionDTO}.
	 */
	public void setTslCountryRegionDTO(TslCountryRegionDTO tslCountryRegionDTO) {
		this.tslCountryRegionDTO = tslCountryRegionDTO;
	}

	/**
	 * Gets the value of the attribute {@link #newTSLAvailable}.
	 * @return the value of the attribute {@link #newTSLAvailable}.
	 */
	public String getNewTSLAvailable() {
		return newTSLAvailable;
	}

	/**
	 * Sets the value of the attribute {@link #newTSLAvailable}.
	 * @param newTSLAvailable The value for the attribute {@link #newTSLAvailable}.
	 */
	public void setNewTSLAvailable(String newTSLAvailable) {
		this.newTSLAvailable = newTSLAvailable;
	}

	/**
	 * Gets the value of the attribute {@link #lastNewTSLAvailableFind}.
	 * @return the value of the attribute {@link #lastNewTSLAvailableFind}.
	 */
	public String getLastNewTSLAvailableFind() {
		return lastNewTSLAvailableFind;
	}

	/**
	 * Sets the value of the attribute {@link #lastNewTSLAvailableFind}.
	 * @param lastNewTSLAvailableFind The value for the attribute {@link #lastNewTSLAvailableFind}.
	 */
	public void setLastNewTSLAvailableFind(String lastNewTSLAvailableFind) {
		this.lastNewTSLAvailableFind = lastNewTSLAvailableFind;
	}

}
