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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.dto.TslDataSummary.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the TSL data for summary DTO.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.1, 06/05/2025.
 */
package es.gob.valet.persistence.configuration.model.dto;

/** 
 * <p>Class that represents an object that relates the code of a to the TSL Data DTO administration.</p>
 * <b>Project:</b><p>Class that represents an object that relates the code of a to the TSL data for summary DTO.</p>
 * @version 1.1, 06/05/2025.
 */
public class TslDataSummary {
	
	/**
	 * Country code or name associated with the TSL (Trusted Service List) data.
	 */
	private String country;

	/**
	 * Sequence number identifying the TSL version.
	 */
	private int numberSequence;

	/**
	 * Entity responsible for the issuance of the TSL.
	 */
	private String responsible;

	/**
	 * Date on which the TSL was issued.
	 */
	private String issueDate;

	/**
	 * Date on which the TSL expires.
	 */
	private String expireDate;

	/**
	 * URL or location from which the TSL can be downloaded or accessed.
	 */
	private String distributionPoint;

	/**
	 * Constructs a new {@code TslDataSummary} with the specified parameters.
	 *
	 * @param country the country code or name.
	 * @param numberSequence the sequence number of the TSL.
	 * @param responsible the name of the entity responsible for the TSL.
	 * @param issueDate the issue date of the TSL.
	 * @param expireDate the expiration date of the TSL.
	 * @param distributionPoint the distribution point URL or location.
	 */
	public TslDataSummary(String country, int numberSequence, String responsible, String issueDate, String expireDate, String distributionPoint) {
	    this.country = country;
	    this.numberSequence = numberSequence;
	    this.responsible = responsible;
	    this.issueDate = issueDate;
	    this.expireDate = expireDate;
	    this.distributionPoint = distributionPoint;
	}
	
	/**
	 * Gets the value of the attribute {@link #country}.
	 * @return the value of the attribute {@link #country}.
	 */
	public String getCountry() {
		return country;
	}

	
	/**
	 * Sets the value of the attribute {@link #country}.
	 * @param country The value for the attribute {@link #country}.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	
	/**
	 * Gets the value of the attribute {@link #numberSequence}.
	 * @return the value of the attribute {@link #numberSequence}.
	 */
	public int getNumberSequence() {
		return numberSequence;
	}

	
	/**
	 * Sets the value of the attribute {@link #numberSequence}.
	 * @param numberSequence The value for the attribute {@link #numberSequence}.
	 */
	public void setNumberSequence(int numberSequence) {
		this.numberSequence = numberSequence;
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
	 * Gets the value of the attribute {@link #expireDate}.
	 * @return the value of the attribute {@link #expireDate}.
	 */
	public String getExpireDate() {
		return expireDate;
	}

	
	/**
	 * Sets the value of the attribute {@link #expireDate}.
	 * @param expireDate The value for the attribute {@link #expireDate}.
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	
	/**
	 * Gets the value of the attribute {@link #distributionPoint}.
	 * @return the value of the attribute {@link #distributionPoint}.
	 */
	public String getDistributionPoint() {
		return distributionPoint;
	}

	
	/**
	 * Sets the value of the attribute {@link #distributionPoint}.
	 * @param distributionPoint The value for the attribute {@link #distributionPoint}.
	 */
	public void setDistributionPoint(String distributionPoint) {
		this.distributionPoint = distributionPoint;
	}
	
	
	
}
