package es.gob.valet.persistence.configuration.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;

public class ExternalAccessDTO implements Serializable {

	private List<String> listUrlDistributionPointCRLResult = new ArrayList<String>();

	private List<String> listUrlDistributionPointDPResult = new ArrayList<String>();

	private List<String> listUrlDistributionPointOCSPResult = new ArrayList<String>();

	private List<String> listUrlIssuerResult = new ArrayList<String>();

	private List<ExternalAccess> listExternalAccessResult = new ArrayList<ExternalAccess>();

	private Long idCountryRegion;

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

}
