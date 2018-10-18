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
 * <b>File:</b><p>es.gob.valet.rest.client.ValetClient.java.</p>
 * <b>Description:</b><p> Class that implements a client for Valet rest services.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 21/09/2018.
 */
package es.gob.valet.rest.client;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.exceptions.ValetRestException;
import es.gob.valet.rest.elements.DetectCertInTslInfoAndValidationResponse;
import es.gob.valet.rest.elements.GetTslInformationResponse;
import es.gob.valet.rest.services.ITslRestService;

/** 
 * <p>Class that implements a client for Valet rest services.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 21/09/2018.
 */
public class ValetClient implements ITslRestService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ValetClient.class);

	/**
	 * Attribute that represents the object that manages the communication with Valet rest services.
	 */
	private ITslRestService restService;

	/**
	 * Constructor method for the class ValetClient.java.
	 * @param urlValetRest Endpoint for Valet rest services.
	 * @param timeout Limit in seconds the network timeout for connect to Valet rest services.
	 */
	public ValetClient(final String urlValetRest, final Integer timeout) {
		ResteasyClient client = new ResteasyClientBuilder().socketTimeout(timeout, TimeUnit.SECONDS).build();
		ResteasyWebTarget target = client.target(UriBuilder.fromPath(urlValetRest));
		restService = target.proxy(ITslRestService.class);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.rest.services.ITslRestService#detectCertInTslInfoAndValidation(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean)
	 */
	@Override
	public DetectCertInTslInfoAndValidationResponse detectCertInTslInfoAndValidation(final String application, final String delegatedApp, final String tslLocation, final String certificate, final String detectionDate, final Boolean getInfo, final Boolean checkRevStatus, final Boolean returnRevoEvid) throws ValetRestException {
		LOGGER.info("Starting call to \'detectCertInTslInfoAndValidation\' method at Valet rest service.");

		DetectCertInTslInfoAndValidationResponse response = null;
		if (restService != null) {

			try {
				if (restService != null) {
					response = restService.detectCertInTslInfoAndValidation(application, delegatedApp, tslLocation, certificate, detectionDate, getInfo, checkRevStatus, returnRevoEvid);
				}
			} catch (ProcessingException e) {
				if (e.getCause().getClass().equals(UnknownHostException.class)) {
					throw new ValetRestUnknownHostException(IValetException.COD_193, "Error trying to connect to Valet rest services. Unknown host. The address of the host could not be determined.");
				} else if (e.getCause().getClass().equals(SocketTimeoutException.class)) {
					throw new ValetRestTimeoutException(IValetException.COD_194, "Error trying to connect to Valet rest services. Network connection timeout. The service didn't response after seconds configured as 'timeout'.");
				} else if (e.getCause().getClass().equals(ConnectException.class)) {
					throw new ValetRestConnectionRefusedException(IValetException.COD_195, "Error trying to connect to Valet rest services. Connection refused. Error occurred while attempting to connect a socket to a remote address and port.");
				} else {
					// If child exception of ProcessingException is unknown
					throw new ValetRestException(IValetException.COD_196, "Error trying to connect to Valet rest services. Connection no available. There are internal processing errors on the server.");
				}
			} catch (NotFoundException e) {
				throw new ValetRestHostNotFoundException(IValetException.COD_197, "Error trying to connect to Valet rest services. Not found. The resource requested by client was not found on the server.");
			} catch (Exception e) {
				throw new ValetRestException(IValetException.COD_198, "Error trying to connect to Valet rest services. Connection no available.", e);
			}
		} else {
			System.out.println("Conexión no válida");
		}

		return response;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.rest.services.ITslRestService#getTslInformation(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public GetTslInformationResponse getTslInformation(final String application, final String delegatedApp, final String countryRegion, final String tslLocation, final Boolean getTslXmlData) throws ValetRestException {
		LOGGER.info("Starting call to \'getTslInformation\' method at Valet rest service.");

		GetTslInformationResponse response = null;
		if (restService != null) {

			try {
				if (restService != null) {
					response = restService.getTslInformation(application, delegatedApp, countryRegion, tslLocation, getTslXmlData);
				}
			} catch (ProcessingException e) {
				if (e.getCause().getClass().equals(UnknownHostException.class)) {
					throw new ValetRestUnknownHostException(IValetException.COD_193, "Error trying to connect to Valet rest services. Unknown host. The address of the host could not be determined.");
				} else if (e.getCause().getClass().equals(SocketTimeoutException.class)) {
					throw new ValetRestTimeoutException(IValetException.COD_194, "Error trying to connect to Valet rest services. Network connection timeout. The service didn't response after seconds configured as 'timeout'.");
				} else if (e.getCause().getClass().equals(ConnectException.class)) {
					throw new ValetRestConnectionRefusedException(IValetException.COD_195, "Error trying to connect to Valet rest services. Connection refused. Error occurred while attempting to connect a socket to a remote address and port.");
				} else {
					// If child exception of ProcessingException is unknown
					throw new ValetRestException(IValetException.COD_196, "Error trying to connect to Valet rest services. Connection no available. There are internal processing errors on the server.");
				}
			} catch (NotFoundException e) {
				throw new ValetRestHostNotFoundException(IValetException.COD_197, "Error trying to connect to Valet rest services. Not found. The resource requested by client was not found on the server.");
			} catch (Exception e) {
				throw new ValetRestException(IValetException.COD_198, "Error trying to connect to Valet rest services. Connection no available.", e);
			}
		} else {
			System.out.println("Conexión no válida");
		}

		return response;
	}

}
