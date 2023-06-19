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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.InfoSIResult.java.</p>
 * <b>Description:</b><p>Class representing information obtained in the procedure 4.3.Obtaining listed
 * services matching a certificate of ETSI TS 119 615 v.1.1.1.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>08/02/2023.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/06/2023.
 */
package es.gob.valet.tsl.certValidation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;

/**
 * <p>
 * Class representing information obtained in the procedure 4.3.Obtaining listed
 * services matching a certificate of ETSI TS 119 615 v.1.1.1.
 * </p>
 * <b>Project:</b>
 * <p>
 * Platform for detection and validation of certificates recognized in European
 * TSL.
 * </p>
 * 
 * @version 1.1, 19/06/2023.
 */
public class InfoSIResult implements Serializable {

	/**
	 * Attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 8336508998724924402L;
	/**
	 * Attribute that represents the 'TSPName' list of the TSPServices that the
	 * certificate identifies.
	 */
	private List<String> listTSPNames;
	/**
	 * Attribute that represents the 'TSPTradeName' list of the TSPServices that
	 * the certificate identifies.
	 */
	private List<String> listTSPTradeNames;
	
	/**
	 * Attribute that represents the 'TSPNames' list of the TSPServices of the country of the certificate.
	 */
	private List<String> listTSPNamesCountry;
	/**
	 * Attribute that represents the 'TSPTradeName' list of the TSPServices of the country of the certificate.
	 */
	private List<String> listTSPTradeNamesCountry;

	/**
	 * Attribute that represents the list of the ServiceHistoryInstance.
	 */
	private List<ServiceHistoryInstance> listSiAtDateTime;
	/**
	 * Attribute that stores information from the TSPService that has identified
	 * the timestamp certificate.
	 */
	private SIResult siResultTSA;
	
	
	/**
	 * Constructor method for the class InfoSIResult.java.
	 */
	public InfoSIResult() {
		listTSPNames = new ArrayList<String>();
		listTSPTradeNames = new ArrayList<String>();
		listSiAtDateTime = new ArrayList<ServiceHistoryInstance>();
		listTSPNamesCountry = new ArrayList<String>();
		listTSPTradeNamesCountry = new ArrayList<String>();
	}

	/**
	 * Gets the value of the attribute {@link #listTSPNames}.
	 * 
	 * @return the value of the attribute {@link #listTSPNames}.
	 */
	public List<String> getListTSPNames() {
		return listTSPNames;
	}

	/**
	 * Sets the value of the attribute {@link #listTSPNames}.
	 * 
	 * @param listTSPNames
	 *            The value for the attribute {@link #listTSPNames}.
	 */
	public void setListTSPNames(List<String> listTSPNames) {
		this.listTSPNames = listTSPNames;
	}

	/**
	 * Gets the value of the attribute {@link #listTSPTradeNames}.
	 * 
	 * @return the value of the attribute {@link #listTSPTradeNames}.
	 */
	public List<String> getListTSPTradeNames() {
		return listTSPTradeNames;
	}

	/**
	 * Sets the value of the attribute {@link #listTSPTradeNames}.
	 * 
	 * @param listTSPTradeNames
	 *            The value for the attribute {@link #listTSPTradeNames}.
	 */
	public void setListTSPTradeNames(List<String> listTSPTradeNames) {
		this.listTSPTradeNames = listTSPTradeNames;
	}

	/**
	 * Gets the value of the attribute {@link #listSiAtDateTime}.
	 * 
	 * @return the value of the attribute {@link #listSiAtDateTime}.
	 */
	public List<ServiceHistoryInstance> getListSiAtDateTime() {
		return listSiAtDateTime;
	}

	/**
	 * Sets the value of the attribute {@link #listSiAtDateTime}.
	 * 
	 * @param listSiAtDateTime
	 *            The value for the attribute {@link #listSiAtDateTime}.
	 */
	public void setListSiAtDateTime(List<ServiceHistoryInstance> listSiAtDateTime) {
		this.listSiAtDateTime = listSiAtDateTime;
	}

	/**
	 * Gets the value of the attribute {@link #siResultTSA}.
	 * 
	 * @return the value of the attribute {@link #siResultTSA}.
	 */
	public SIResult getSiResultTSA() {
		return siResultTSA;
	}

	/**
	 * Sets the value of the attribute {@link #siResultTSA}.
	 * 
	 * @param siResultTSA
	 *            The value for the attribute {@link #siResultTSA}.
	 */
	public void setSiResultTSA(SIResult siResultTSA) {
		this.siResultTSA = siResultTSA;
	}

	/**
	 * Gets the value of the attribute {@link #listTSPNamesCountry}.
	 * @return the value of the attribute {@link #listTSPNamesCountry}.
	 */
	public List<String> getListTSPNamesCountry() {
		return listTSPNamesCountry;
	}

	/**
	 * Sets the value of the attribute {@link #listTSPNamesCountry}.
	 * @param listTSPNamesCountry The value for the attribute {@link #listTSPNamesCountry}.
	 */
	public void setListTSPNamesCountry(List<String> listTSPNamesCountry) {
		this.listTSPNamesCountry = listTSPNamesCountry;
	}

	/**
	 * Gets the value of the attribute {@link #listTSPTradeNamesCountry}.
	 * @return the value of the attribute {@link #listTSPTradeNamesCountry}.
	 */
	public List<String> getListTSPTradeNamesCountry() {
		return listTSPTradeNamesCountry;
	}

	/**
	 * Sets the value of the attribute {@link #listTSPTradeNamesCountry}.
	 * @param listTSPTradeNamesCountry The value for the attribute {@link #listTSPTradeNamesCountry}.
	 */
	public void setListTSPTradeNamesCountry(List<String> listTSPTradeNamesCountry) {
		this.listTSPTradeNamesCountry = listTSPTradeNamesCountry;
	}



}
