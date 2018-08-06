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
 * <b>File:</b><p>es.gob.valet.form.TslForm.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>28 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 28 jun. 2018.
 */
package es.gob.valet.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import es.gob.valet.rest.exception.CheckItFirst;

/** 
 * <p>Class that represents the baking form for adding/editing a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 28 jun. 2018.
 */
public class TslForm {

	/**
	 * Attribute that represents the value of the primary key as a hidden input in the form.
	 */
	private Long idTslValet = null;

	/**
	 * Attribute that represents the name of the selected specification. 
	 */
	@NotNull(message = "form.valid.tsl.specification.notempty")
	private String specification;

	/**
	 * Attribute that represents the name of the selected version.
	 */
	@NotNull(message = "form.valid.tsl.version.notempty")
	private String version;

	/**
	 * Attribute that represents the TSL location URI.
	 */
	private String urlTsl;
	
	/**
	 * Attribute that represents the country of TSL.
	 */
	private Long country;
	/**
	 * Attribute that represents the sequence number of the TSL. 
	 */
	private int sequenceNumber = -1;
	
	/**
	 * Attribute that represents a TSL name.
	 */
	private String tslName;
	
	/**
	 * Attribute that represents a TSL responsible.
	 */
	private String tslResponsible;
	

	/**
	 * Attribute that represents the issue date of the TSL. 
	 */
	private String issueDate = null;

	/**
	 * Attribute that represents the next update date of the TSL. 
	 */
	private String expirationDate = null;
	
	/**
	 * Attribute that represents the name of the country.
	 */
	private String countryName;
	
	/**
	 * Attribute that indicates if 
	 */
	private Boolean isLegible;
	
	

	/**
	 * Attribute that represents the uploaded file.
	 */
	@NotNull(groups = CheckItFirst.class, message = "{form.valid.tsl.file.notempty}")
	private MultipartFile file;
	
	
	/**
	 * Attribute that represents uploaded file corresponding to the readable TSL document.
	 */
	private MultipartFile fileDocument;

	/**
	 * Gets the value of the attribute {@link #idTslValet}.
	 * @return the value of the attribute {@link #idTslValet}.
	 */
	public Long getIdTslValet() {
		return idTslValet;
	}

	/**
	 * Sets the value of the attribute {@link #idTslValet}.
	 * @param idTslValet The value for the attribute {@link #idTslValet}.
	 */
	public void setIdTslValet(Long idTslValet) {
		this.idTslValet = idTslValet;
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
	 * Gets the value of the attribute {@link #urlTsl}.
	 * @return the value of the attribute {@link #urlTsl}.
	 */
	public String getUrlTsl() {
		return urlTsl;
	}

	/**
	 * Sets the value of the attribute {@link #urlTsl}.
	 * @param urlTsl The value for the attribute {@link #urlTsl}.
	 */
	public void setUrlTsl(String urlTsl) {
		this.urlTsl = urlTsl;
	}

	/**
	 * Gets the value of the attribute {@link #file}.
	 * @return the value of the attribute {@link #file}.
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * Sets the value of the attribute {@link #file}.
	 * @param file The value for the attribute {@link #file}.
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
	/**
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
	public Long getCountry() {
		return country;
	}

	
	/**
	 * Sets the value of the attribute {@link #country}.
	 * @param country The value for the attribute {@link #country}.
	 */
	public void setCountry(Long country) {
		this.country = country;
	}

	
	/**
	 * Gets the value of the attribute {@link #sequenceNumber}.
	 * @return the value of the attribute {@link #sequenceNumber}.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	
	/**
	 * Sets the value of the attribute {@link #sequenceNumber}.
	 * @param sequenceNumber The value for the attribute {@link #sequenceNumber}.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	
	/**
	 * Gets the value of the attribute {@link #tslName}.
	 * @return the value of the attribute {@link #tslName}.
	 */
	public String getTslName() {
		return tslName;
	}

	
	/**
	 * Sets the value of the attribute {@link #tslName}.
	 * @param tslName The value for the attribute {@link #tslName}.
	 */
	public void setTslName(String tslName) {
		this.tslName = tslName;
	}

	
	/**
	 * Gets the value of the attribute {@link #tslResponsible}.
	 * @return the value of the attribute {@link #tslResponsible}.
	 */
	public String getTslResponsible() {
		return tslResponsible;
	}

	
	/**
	 * Sets the value of the attribute {@link #tslResponsible}.
	 * @param tslResponsible The value for the attribute {@link #tslResponsible}.
	 */
	public void setTslResponsible(String tslResponsible) {
		this.tslResponsible = tslResponsible;
	}

	
	
	
	/**
	 * Gets the value of the attribute {@link #countryName}.
	 * @return the value of the attribute {@link #countryName}.
	 */
	public String getCountryName() {
		return countryName;
	}

	
	/**
	 * Sets the value of the attribute {@link #countryName}.
	 * @param countryName The value for the attribute {@link #countryName}.
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
	 * Gets the value of the attribute {@link #isLegible}.
	 * @return the value of the attribute {@link #isLegible}.
	 */
	public Boolean getIsLegible() {
		return isLegible;
	}

	/**
	 * Sets the value of the attribute {@link #isLegible}.
	 * @param isLegible The value for the attribute {@link #isLegible}.
	 */
	public void setIsLegible(Boolean isLegible) {
		this.isLegible = isLegible;
	}

	/**
	 * Gets the value of the attribute {@link #fileDocument}.
	 * @return the value of the attribute {@link #fileDocument}.
	 */
	public MultipartFile getFileDocument() {
		return fileDocument;
	}

	/**
	 * Sets the value of the attribute {@link #fileDocument}.
	 * @param fileDocument The value for the attribute {@link #fileDocument}.
	 */
	public void setFileDocument(MultipartFile fileDocument) {
		this.fileDocument = fileDocument;
	}

	
	

}
