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
 * <b>File:</b><p>es.gob.valet.form.ApplicationForm.java.</p>
 * <b>Description:</b><p>Class that represents the backing form fot adding/edditing an application.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 12/12/2018.
 */
package es.gob.valet.form;



import java.util.Date;


/**
 * <p>Class that represents the backing form fot adding/edditing an external access.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 12/12/2018.
 */
public class ExternalAccessForm {

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idUrl ;

	/**
	 * Attribute that represents the url.
	 */
	private String url;

	/**
	 * Attribute that represents the url.
	 */
	private String originUrl;
	
	/**
	 * Attribute that indicates if the connection is OK (true) or not (false).
	 */
	private Boolean stateConn;

	/**
	 * Attribute that represents the last url connection.
	 */
	private Date lastConn;

	/**
	 * Attribute that represents the date of from connection.
	 */
	private String dateFrom;
	
	/**
	 * Attribute that represents the date of to connection.
	 */
	private String dateTo;

	/**
	 * Attribute that indicates if the connection is OK (true) or not (false).
	 */
	private String stateConnInput;
	
	/**
	 * @return the idUrl
	 */
	public Long getIdUrl() {
		return idUrl;
	}

	/**
	 * @param idUrl the idUrl to set
	 */
	public void setIdUrl(Long idUrl) {
		this.idUrl = idUrl;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the originUrl
	 */
	public String getOriginUrl() {
		return originUrl;
	}

	/**
	 * @param originUrl the originUrl to set
	 */
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	/**
	 * @return the stateConn
	 */
	public Boolean getStateConn() {
		return stateConn;
	}

	/**
	 * @param stateConn the stateConn to set
	 */
	public void setStateConn(Boolean stateConn) {
		this.stateConn = stateConn;
	}

	/**
	 * @return the lastConn
	 */
	public Date getLastConn() {
		return lastConn;
	}

	/**
	 * @param lastConn the lastConn to set
	 */
	public void setLastConn(Date lastConn) {
		this.lastConn = lastConn;
	}

	/**
	 * @return the dateFrom
	 */
	public String getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public String getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return the stateConnInput
	 */
	public String getStateConnInput() {
		return stateConnInput;
	}

	/**
	 * @param stateConnInput the stateConnInput to set
	 */
	public void setStateConnInput(String stateConnInput) {
		this.stateConnInput = stateConnInput;
	}
	

}
