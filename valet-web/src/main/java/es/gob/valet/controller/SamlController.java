package es.gob.valet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import es.gob.valet.clave.sp.SpProtocolEngineFactory;
import es.gob.valet.dto.PersonalInfoBean;
import es.gob.valet.i18n.Language;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.Task;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService;
import es.gob.valet.persistence.configuration.services.ifaces.ITaskService;
import es.gob.valet.persistence.configuration.services.impl.UserValetService;
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

import javax.xml.namespace.QName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@Controller
public class SamlController {

	private static ProtocolEngineNoMetadataI protocolEngine = null;

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	UserValetService userValetService;
	
	/**
	 * Attribute that represents the service object for accessing the repository. 
	 */
	@Autowired
	private IKeystoreService keystoreService;
	
	/**
	 * Attribute that represents the service object for accessing the repository. 
	 */
	@Autowired
	private ITaskService taskService;
	 
	@RequestMapping(value="/accessClave", method = RequestMethod.POST)
	public void samlLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException {

	    try {
            String claveServiceUrl = "https://se-pasarela.clave.gob.es/Proxy2/ServiceProvider";
            String providerName = "S2833002E_E04975701";
            String eidasLevelOfAssurance = "http://eidas.europa.eu/LoA/substantial";
            String spApplication = "Valet";
            String relayState = SecureRandomXmlIdGenerator.INSTANCE.generateIdentifier(8);
            String baseUrl = getBaseUrl(request);
            String returnUrl = baseUrl + "/responseAccessClave";

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
            
            request.setAttribute("RelayState", relayState);
            request.setAttribute("peticionSAML", samlRequest);
            request.setAttribute("urlRedireccion", claveServiceUrl);
            
            request.getRequestDispatcher("/WEB-INF/jsp/redirectForm.jsp").forward(request, response);
            
        } catch (EIDASSAMLEngineException e) {
            throw new RuntimeException("Error generando SAML request", e);
        }
    }


    // Método para manejar la respuesta SAML
    @RequestMapping(value="/responseAccessClave", method = RequestMethod.POST)
    public String processSamlResponse(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    	
    	// Obtenemos los datos del resultado
    	String samlResponse = request.getParameter("SAMLResponse");
    	String relayState = request.getParameter("RelayState");
    	String remoteHost = request.getRemoteHost();

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
    	String nombre = personalInfo.getNombre();
    	
    	Iterable<UserValet> usuariosValet = userValetService.getAllUserValet();
    	for (UserValet usuario : usuariosValet) {
    	   if(usuario.getNif().equals(dni)) {
    		   List<Keystore> listKeystores = keystoreService.getAllKeystore();
    			List<Task> listTask = taskService.getAllTask();
    			for(Task task: listTask){
    				task.setTokenName(Language.getResPersistenceConstants(task.getTokenName()));
    			}
    			model.addAttribute("listtask", listTask);
    			model.addAttribute("listkeystore", listKeystores);
    			authenticationService.authenticateUser(nombre);
    			return "inicio.html";
    	   }
    	}

		return "login.html";
    }
    
    public String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }
    
    // Obtenemos el motor de validacion
    public ProtocolEngineNoMetadataI getProtocolEngine() {
        if (this.protocolEngine == null) {
            this.protocolEngine = SpProtocolEngineFactory.getSpProtocolEngine(
                "SPNoMetadata", System.getProperty("clave.path")); // Ruta de los ficheros de configuracion
        }
        return this.protocolEngine;
    }


    // Método auxiliar para extraer atributos del mapa
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
