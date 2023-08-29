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
 * <b>File:</b><p>es.gob.valet.dto.CAssociationTypeDTO.java.</p>
 * <b>Description:</b><p>Class that represents an object that relates the code of a to the Constants DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>09/08/2023.</p>
 * @author Gobierno de España.
 * @version 1.1, 10/08/2023.
 */
package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;


/** 
 * <p>Class that represents an object that relates the code of a to the Constants DTO administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 10/08/2023.
 */
public class ExternalAccessDTO implements Serializable {

	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = -4578902273285866831L;

	/**
	 * Attribute that represents list of url DP to CRL.
	 */
	private List<String> listUrlDistributionPointCRLResult = new ArrayList<String>();

	/**
	 * Attribute that represents list of url DP.
	 */
	private List<String> listUrlDistributionPointDPResult = new ArrayList<String>();

	/**
	 * Attribute that represents list of url DP to OCSP.
	 */
	private List<String> listUrlDistributionPointOCSPResult = new ArrayList<String>();

	/**
	 * Attribute that represents list of url DP to Issuer.
	 */
	private List<String> listUrlIssuerResult = new ArrayList<String>();

	/**
	 * Attribute that represents list of url DP to switch operation.
	 */
	private List<ExternalAccess> listExternalAccessResult = new ArrayList<ExternalAccess>();

	/**
	 * Attribute that represents id country region.
	 */
	private Long idCountryRegion;
	
	/**
	 * Attribute that represents the object ID.
	 */
	private Long idUrl;

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
	 * Gets the value of the attribute {@link #listUrlDistributionPointCRLResult}.
	 * @return the value of the attribute {@link #listUrlDistributionPointCRLResult}.
	 */
	public List<String> getListUrlDistributionPointCRLResult() {
		return listUrlDistributionPointCRLResult;
	}

	/**
	 * Sets the value of the attribute {@link #listUrlDistributionPointCRLResult}.
	 * @param listUrlDistributionPointCRLResult The value for the attribute {@link #listUrlDistributionPointCRLResult}.
	 */
	public void setListUrlDistributionPointCRLResult(List<String> listUrlDistributionPointCRLResult) {
		this.listUrlDistributionPointCRLResult = listUrlDistributionPointCRLResult;
	}

	/**
	 * Gets the value of the attribute {@link #listUrlDistributionPointDPResult}.
	 * @return the value of the attribute {@link #listUrlDistributionPointDPResult}.
	 */
	public List<String> getListUrlDistributionPointDPResult() {
		return listUrlDistributionPointDPResult;
	}

	/**
	 * Sets the value of the attribute {@link #listUrlDistributionPointDPResult}.
	 * @param listUrlDistributionPointDPResult The value for the attribute {@link #listUrlDistributionPointDPResult}.
	 */
	public void setListUrlDistributionPointDPResult(List<String> listUrlDistributionPointDPResult) {
		this.listUrlDistributionPointDPResult = listUrlDistributionPointDPResult;
	}

	/**
	 * Gets the value of the attribute {@link #listUrlDistributionPointOCSPResult}.
	 * @return the value of the attribute {@link #listUrlDistributionPointOCSPResult}.
	 */
	public List<String> getListUrlDistributionPointOCSPResult() {
		return listUrlDistributionPointOCSPResult;
	}

	/**
	 * Sets the value of the attribute {@link #listUrlDistributionPointOCSPResult}.
	 * @param listUrlDistributionPointOCSPResult The value for the attribute {@link #listUrlDistributionPointOCSPResult}.
	 */
	public void setListUrlDistributionPointOCSPResult(List<String> listUrlDistributionPointOCSPResult) {
		this.listUrlDistributionPointOCSPResult = listUrlDistributionPointOCSPResult;
	}

	/**
	 * Gets the value of the attribute {@link #listUrlIssuerResult}.
	 * @return the value of the attribute {@link #listUrlIssuerResult}.
	 */
	public List<String> getListUrlIssuerResult() {
		return listUrlIssuerResult;
	}

	/**
	 * Sets the value of the attribute {@link #listUrlIssuerResult}.
	 * @param listUrlIssuerResult The value for the attribute {@link #listUrlIssuerResult}.
	 */
	public void setListUrlIssuerResult(List<String> listUrlIssuerResult) {
		this.listUrlIssuerResult = listUrlIssuerResult;
	}

	/**
	 * Gets the value of the attribute {@link #idCountryRegion}.
	 * @return the value of the attribute {@link #idCountryRegion}.
	 */
	public Long getIdCountryRegion() {
		return idCountryRegion;
	}

	/**
	 * Sets the value of the attribute {@link #idCountryRegion}.
	 * @param idCountryRegion The value for the attribute {@link #idCountryRegion}.
	 */
	public void setIdCountryRegion(Long idCountryRegion) {
		this.idCountryRegion = idCountryRegion;
	}

	/**
	 * Gets the value of the attribute {@link #listExternalAccessResult}.
	 * @return the value of the attribute {@link #listExternalAccessResult}.
	 */
	public List<ExternalAccess> getListExternalAccessResult() {
		return listExternalAccessResult;
	}

	/**
	 * Sets the value of the attribute {@link #listExternalAccessResult}.
	 * @param listExternalAccessResult The value for the attribute {@link #listExternalAccessResult}.
	 */
	public void setListExternalAccessResult(List<ExternalAccess> listExternalAccessResult) {
		this.listExternalAccessResult = listExternalAccessResult;
	}

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

}
