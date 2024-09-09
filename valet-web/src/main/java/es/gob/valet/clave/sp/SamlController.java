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
 * <b>File:</b><p>es.gob.valet.clave.sp.SamlController.java.</p>
 * <b>Description:</b><p> .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/06/2024.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/06/2024.
 */
package es.gob.valet.clave.sp;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.dto.PersonalInfoBean;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.services.impl.UserValetService;
import es.gob.valet.rest.controller.ProxyRestController;
import es.gob.valet.utils.AuthenticationService;
import es.gob.valet.utils.SecureRandomXmlIdGenerator;
import es.gob.valet.utils.SessionHolder;
import eu.eidas.auth.commons.EidasStringUtil;
import eu.eidas.auth.commons.attribute.AttributeDefinition;
import eu.eidas.auth.commons.attribute.AttributeValue;
import eu.eidas.auth.commons.attribute.ImmutableAttributeMap;
import eu.eidas.auth.commons.attribute.PersonType;
import eu.eidas.auth.commons.attribute.impl.StringAttributeValueMarshaller;
import eu.eidas.auth.commons.protocol.IAuthenticationResponseNoMetadata;
import eu.eidas.auth.commons.protocol.IRequestMessageNoMetadata;
import eu.eidas.auth.commons.protocol.eidas.LevelOfAssuranceComparison;
import eu.eidas.auth.commons.protocol.eidas.impl.EidasAuthenticationRequestNoMetadata;
import eu.eidas.auth.commons.protocol.impl.EidasSamlBinding;
import eu.eidas.auth.engine.ProtocolEngineNoMetadataI;
import eu.eidas.auth.engine.xml.opensaml.SAMLEngineUtils;
import eu.eidas.engine.exceptions.EIDASSAMLEngineException;
/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/06/2024.
 */
@Controller
public class SamlController {

	/**
	 * Attribute that represents the class ProtocolEngineNoMetadataI. 
	 */
	private static ProtocolEngineNoMetadataI protocolEngine = null;
	/**
	 * Attribute that represents the class AuthenticationService. 
	 */
	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * Attribute that represents the class userValetService. 
	 */
	@Autowired
	UserValetService userValetService;

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(SamlController.class);
	
	/**
	 * Method where we build the SAML request that we will send to Cl@ve through a jsp file.
	 * @param request request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param model Model
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value="/accessClave", method = RequestMethod.POST)
	public void samlLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException {

	    try {
	    	
	    	LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_SAML_ATTRIBUTES));
	    	String claveServiceUrl = StaticValetConfig.getProperty(StaticValetConfig.CLAVE_SERVICE_URL);//URL de Cl@ve
			String providerName = StaticValetConfig.getProperty(StaticValetConfig.CLAVE_PROVIDER_NAME);//Nombre del proveedor
			String eidasLevelOfAssurance = StaticValetConfig.getProperty(StaticValetConfig.CLAVE_EIDAS_LEVEL_OF_ASSURANCE);//Nivel de garantía sobre las credenciales
			String spApplication = StaticValetConfig.getProperty(StaticValetConfig.CLAVE_SP_APPLICATION);//Nombre de la aplicación
           
            String relayState = SecureRandomXmlIdGenerator.INSTANCE.generateIdentifier(8);
            String baseUrl = getBaseUrl(request);
            String returnUrl = baseUrl + "/responseAccessClave";//URL de la página o servicio retorno 

            LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_SAML_REQUEST_BUILD));
            ImmutableAttributeMap.Builder reqAttrMapBuilder = new ImmutableAttributeMap.Builder();
    	    reqAttrMapBuilder.putPrimaryValues(new AttributeDefinition.Builder<String>()
    	      .nameUri("http://es.minhafp.clave/RelayState")
    	      .friendlyName("RelayState")
    	      .personType(PersonType.NATURAL_PERSON)
    	      .required(false)
    	      .uniqueIdentifier(true)
    	      .xmlType("http://eidas.europa.eu/attributes/naturalperson", "PersonIdentifierType", "eidas-natural")
    	      .attributeValueMarshaller(new StringAttributeValueMarshaller())
    	      .build(), SecureRandomXmlIdGenerator.INSTANCE.generateIdentifier(8));

            EidasAuthenticationRequestNoMetadata.Builder reqBuilder = new EidasAuthenticationRequestNoMetadata.Builder()
                .destination(claveServiceUrl)
                .providerName(providerName)
                .requestedAttributes(reqAttrMapBuilder.build())
                .levelOfAssurance(eidasLevelOfAssurance)
                .levelOfAssuranceComparison(LevelOfAssuranceComparison.MINIMUM)
                .nameIdFormat("urn:oasis:names:tc:SAML:2.0:nameid-format:persistent")
                .binding(EidasSamlBinding.EMPTY.getName())
                .assertionConsumerServiceURL(returnUrl)
                .forceAuth(true)
                .spApplication(spApplication);

            String requestId = SAMLEngineUtils.generateNCName();
            reqBuilder.id(requestId);
            IRequestMessageNoMetadata binaryRequestMessage = getProtocolEngine().generateRequestMessage(reqBuilder.build(), true);
            String samlRequest = EidasStringUtil.encodeToBase64(binaryRequestMessage.getMessageBytes());

            if(!SessionHolder.sessionsSAML.isEmpty()) {
            	SessionHolder.sessionsSAML.clear();
            }
            // Almacenar relayState e InResponseToId en la sesión
            SessionHolder.sessionsSAML.put(requestId, relayState);
            
            request.setAttribute("RelayState", relayState); //Identificador de la sesión
            request.setAttribute("peticionSAML", samlRequest); //Petición SAML en Base 64
            request.setAttribute("urlRedireccion", claveServiceUrl);
            
            LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_SAML_REQUEST_REDIRECT));
            //Redirigimos al formulario de carga
            request.getRequestDispatcher("/WEB-INF/jsp/redirectForm.jsp").forward(request, response);
            
        } catch (EIDASSAMLEngineException e) {
            throw new RuntimeException("Error generando SAML request", e);
        }
    }


    
	/**
	 * Method where we receive and handle the response of the SAML request.
	 * @param request request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param model Model
	 * @param redirectAttributes RedirectAttributes
	 * @throws IOException
	 * @throws ServletException
	 */
    @RequestMapping(value="/responseAccessClave", method = RequestMethod.POST)
    public String processSamlResponse(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws Exception {
    	
    	LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_SAML_RESPONSE));
    	// Obtenemos los datos del resultado
    	String samlResponse = request.getParameter("SAMLResponse");
    	String relayState = request.getParameter("RelayState");
    	String remoteHost = request.getRemoteHost();

    	LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_SAML_RESPONSE_USER_INFO));
    	// Extramos la información del usuario de la respuesta
    	PersonalInfoBean personalInfo;
    	try {
    	    String claveReturnUrl = getBaseUrl(request);
			personalInfo = obtenerDatosUsuario(
    	        claveReturnUrl, samlResponse, relayState, remoteHost);
    	} catch (Exception e) {
    	    throw new SecurityException("No se obtuvieron los datos del usuario", e);
    	}

    	// Recuperamos los datos
    	String dni = personalInfo.getDni(); 
    	
    	LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_SAML_RESPONSE_LOGIN));
    	LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_SAML_RESPONSE_REDIRECT));
    	Iterable<UserValet> usuariosValet = userValetService.getAllUserValet();
    	for (UserValet usuario : usuariosValet) {
    	   if(usuario.getNif().equals(dni)) { 
    		   redirectAttributes.addFlashAttribute("nifUser", usuario.getNif());
    		   authenticationService.authenticateUser(usuario.getNif());
    		   return "redirect:/inicio";
    	   }
    	}

		return "login.html";
    }
    
    /**
     * Method to get base URL
     * @param request HttpServletRequest
     * @return String with the url obtained
     */
    public String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }
    
    // Obtenemos el motor de validacion
    /**
     * Method to obtain the validation engine through the configuration files located in CLAVE_CONFIG_PATH
     * @return protocolEngine variable of class ProtocolEngineNoMetadataI
     */
    private static ProtocolEngineNoMetadataI getProtocolEngine() {
        if (protocolEngine == null) {
            protocolEngine = SpProtocolEngineFactory.getSpProtocolEngine(
                "SPNoMetadata", StaticValetConfig.getProperty(StaticValetConfig.CLAVE_CONFIG_PATH)); // Ruta de los ficheros de configuracion
        }
        return protocolEngine;
    }


    // Método auxiliar para extraer atributos del mapa
    /**
     * Helper method to extract attributes from the map.
     * @param friendlyName String
     * @param attrMap ImmutableMap<AttributeDefinition<?>, ImmutableSet<? extends AttributeValue<?>>>
     * @return null
     */
    private static String extractFromAttrMap(String friendlyName, ImmutableMap<AttributeDefinition<?>, ImmutableSet<? extends AttributeValue<?>>> attrMap) {
        Iterator<AttributeDefinition<?>> it = attrMap.keySet().iterator();
        while (it.hasNext()) {
            AttributeDefinition<?> k = it.next();
            if (friendlyName.equals(k.getFriendlyName())) {
                ImmutableSet<? extends AttributeValue<?>> valuesSet = attrMap.get(k);
                return !valuesSet.isEmpty() ? (String) valuesSet.iterator().next().getValue() : null;
            }
        }
        return null;
    }
    
    /**
     * Method to obtain user data.
     * @param claveReturnUrl String
     * @param samlResponse String
     * @param relayState String
     * @param remoteHost String
     * @return personalInfo of class PersonalInfoBean
     * @throws EIDASSAMLEngineException
     */
    private PersonalInfoBean obtenerDatosUsuario(String claveReturnUrl, String samlResponse, String relayState, String remoteHost) throws EIDASSAMLEngineException {
    	byte[] decSamlToken = EidasStringUtil.decodeBytesFromBase64(samlResponse);

    	// Validamos token SAML
    	IAuthenticationResponseNoMetadata authnResponse = getProtocolEngine()
    	    .unmarshallResponseAndValidate(decSamlToken, remoteHost, 0, 0, claveReturnUrl+"/responseAccessClave");
    	// Comprobamos que el relayState de peticion y respuesta sea el mismo
    	String prevRState = SessionHolder.sessionsSAML.get(authnResponse.getInResponseToId());
    	if (prevRState == null || ! prevRState.equals(relayState)) {
    	    throw new EIDASSAMLEngineException("La respuesta no valida");
    	}

    	// Eliminamos el valor la sesion y que la respuesta sea valida
    	SessionHolder.sessionsSAML.remove(authnResponse.getInResponseToId());
    	if (authnResponse.isFailure()) {
    	    throw new SecurityException("La respuesta no devolvio los datos del usuario");
    	}

    	ImmutableMap<AttributeDefinition<?>, ImmutableSet<? extends AttributeValue<?>>>
    	    attrMap = authnResponse.getAttributes().getAttributeMap();
    	PersonalInfoBean personalInfo = new PersonalInfoBean();
    	personalInfo.setNombre(extractFromAttrMap("FirstName", attrMap));
    	personalInfo.setApellidos(extractFromAttrMap("FamilyName", attrMap));
    	personalInfo.setDni(extractFromAttrMap("PersonIdentifier", attrMap));

    	return personalInfo;
    }
}
