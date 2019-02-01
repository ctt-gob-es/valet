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
 * @version 1.3, 01/02/2019.
 */
package es.gob.valet.rest.client;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.exceptions.ValetRestException;
import es.gob.valet.rest.elements.DetectCertInTslInfoAndValidationResponse;
import es.gob.valet.rest.elements.TslInformationResponse;
import es.gob.valet.rest.services.ITslRestService;

/**
 * <p>Class that implements a client for Valet rest services.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 01/02/2019.
 */
public class ValetClient {

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
		// TODO IMPORTANTE: Hay que establecer una creación del cliente en
		// función de si
		// el destino es HTTP o HTTPS, así como dar la posibilidad de usar
		// proxy.
		// Incluso se podría configurar almacenes de confianza e identificación
		// para conexiones segura.
		// También es posible especificar listado de SNI válidos.
		// Del mismo modo se debería permitir configurar los algoritmos y
		// protocolos restringidos
		// para el cifrado de la conexión segura.
		ResteasyClient client = new ResteasyClientBuilder().readTimeout(timeout, TimeUnit.SECONDS).disableTrustManager().build();
		ResteasyWebTarget target = client.target(UriBuilder.fromPath(urlValetRest));
		restService = target.proxy(ITslRestService.class);
	}

	/**
	 * Method that returns the information and revocation status of the input certificate extracted from a TSL.
	 * @param application Application identifier.
	 * @param delegatedApp Delegated application identifier.
	 * @param tslLocation TSL location to use. It could be <code>null</code>.
	 * @param certificate Certificate to detect (byte[] in Base64 encoded).
	 * @param detectionDate Date to use to detect and validate the input certificate.
	 * @param getInfo Flag that indicates if it is necessary to get the certificate information in response.
	 * @param checkRevStatus Flag that indicates if it is necessary to check the revocation status of the input certificate.
	 * @param returnRevocationEvidence Flag that indicates if it is necessary to return the revocation evidence (only if {@code checkRevocationStatus} is <code>true</code>).
	 * @return Structure with detected certificate in TSL and revocation status.
	 * @throws ValetRestException If some error is produced in the execution of the service.
	 */
	public DetectCertInTslInfoAndValidationResponse detectCertInTslInfoAndValidation(final String application, final String delegatedApp, final String tslLocation, final String certificate, final Date detectionDate, final Boolean getInfo, final Boolean checkRevStatus, final Boolean returnRevocationEvidence) throws ValetRestException {
		LOGGER.info("Starting call to \'detectCertInTslInfoAndValidation\' method at Valet rest service.");

		DetectCertInTslInfoAndValidationResponse response = null;
		if (restService != null) {

			String detectionDateString = null;
			if (detectionDate != null) {
				detectionDateString = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_JSON, detectionDate);
			}

			try {
				response = restService.detectCertInTslInfoAndValidation(application, delegatedApp, tslLocation, certificate, detectionDateString, getInfo, checkRevStatus, returnRevocationEvidence);
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
	 * Method that returns the TSL information.
	 * @param application Application identifier.
	 * @param delegatedApp Delegated application identifier.
	 * @param countryRegionCode Country/Region code that represents the TSL. It could be <code>null</code>.
	 * @param tslLocation TSL location to use. It could be <code>null</code>.
	 * @param getTslXmlData Flag that indicates if it is necessary to return the XML data that represents the TSL.
	 * @return Structure of TSL information.
	 * @throws ValetRestException If some error is produced in the execution of the service.
	 */
	public TslInformationResponse getTslInformation(final String application, final String delegatedApp, final String countryRegionCode, final String tslLocation, final Boolean getTslXmlData) throws ValetRestException {
		LOGGER.info("Starting call to \'getTslInformation\' method at Valet rest service.");

		TslInformationResponse response = null;
		if (restService != null) {

			try {
				if (restService != null) {
					response = restService.getTslInformation(application, delegatedApp, countryRegionCode, tslLocation, getTslXmlData);
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
