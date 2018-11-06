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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.ifaces.ITSLOtherConstants.java.</p>
 * <b>Description:</b><p>Interface that defines all the constants related with TSL that are not defined in other classes.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/11/2018.
 */
package es.gob.valet.tsl.parsing.ifaces;

/**
 * <p>Interface that defines all the constants related with TSL that are not defined in other classes.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/11/2018.
 */
public interface ITSLOtherConstants {

	/**
	 * Constant attribute that represents the token 'VAT'.
	 */
	String TOKEN_TSP_INF_TRADE_NAME_VAT = "VAT";

	/**
	 * Constant attribute that represents the token 'NTR'.
	 */
	String TOKEN_TSP_INF_TRADE_NAME_NTR = "NTR";

	/**
	 * Constant attribute that represents the token 'PAS'.
	 */
	String TOKEN_TSP_INF_TRADE_NAME_PAS = "PAS";

	/**
	 * Constant attribute that represents the token 'IDC'.
	 */
	String TOKEN_TSP_INF_TRADE_NAME_IDC = "IDC";

	/**
	 * Constant attribute that represents the token 'PNO'.
	 */
	String TOKEN_TSP_INF_TRADE_NAME_PNO = "PNO";

	/**
	 * Constant attribute that represents the token 'TIN'.
	 */
	String TOKEN_TSP_INF_TRADE_NAME_TIN = "TIN";

	/**
	 * Constant attribute that represents the HTTP Head type for TSL.
	 */
	String TSL_APPLICATION_TYPE = "application/vnd.etsi.tsl+xml";

}
