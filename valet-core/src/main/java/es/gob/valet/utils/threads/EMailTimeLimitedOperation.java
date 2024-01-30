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
 * <b>File:</b><p>es.gob.valet.utils.threads.EMailTimeLimitedOperation.java.</p>
 * <b>Description:</b><p>Class that represents an e-mail sending-operation. In this one all the information
 * is specified to define the e-mail and the necessary functionality is contributed to realize the sending
 * as an independent thread via SMTP server. This thread will be time limited.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.4, 22/06/2023.
 */
package es.gob.valet.utils.threads;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.sun.mail.util.MailConnectException;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsResources;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.commons.utils.threads.ATimeLimitedOperation;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.ConfServerMail;
import es.gob.valet.persistence.exceptions.CipherException;
import es.gob.valet.persistence.utils.UtilsAESCipher;

/**
 * <p>
 * Class that represents an e-mail sending-operation. In this one all the
 * information is specified to define the e-mail and the necessary functionality
 * is contributed to realize the sending as an independent thread via SMTP
 * server. This thread will be time limited.
 * </p>
 * <b>Project:</b>
 * <p>
 * Platform for detection and validation of certificates recognized in European
 * TSL.
 * </p>
 * 
 * @version 1.4, 22/06/2023.
 */
public class EMailTimeLimitedOperation extends ATimeLimitedOperation {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(EMailTimeLimitedOperation.class);

	/**
	 * Constant attribute that represents the email transport protocol 'smtp'.
	 */
	private static final String TRANSPORT_PROTOCOL_SMTP = "smtp";

	/**
	 * Constant attribute that represents a string separator.
	 */
	private static final String SEPARATOR = "------";

	/**
	 * Attribute that represents the list of addressees to the e-mail.
	 */
	private List<InternetAddress> mailAddresses = new ArrayList<InternetAddress>();

	/**
	 * Attribute that represents the subject of the e-mail.
	 */
	private String subject = null;

	/**
	 * Attribute that represents the message of the e-mail.
	 */
	private StringBuilder messageBuilder = null;

	/**
	 * Attribute that represents the Mail Server: Issuer.
	 */
	private String mailServerIssuer = null;

	/**
	 * Attribute that represents the Mail Server: Host.
	 */
	private String mailServerHost = null;

	/**
	 * Attribute that represents the Mail Server: Port.
	 */
	private int mailServerPort = NumberConstants.NUM25;

	/**
	 * Attribute that represents the Mail Server: Flag that indicates if it is
	 * necessary the authentication.
	 */
	private boolean mailServerAuthUseAuthentication = false;

	/**
	 * Attribute that represents the Mail Server: Authentication: User Name.
	 */
	private String mailServerAuthUserName = null;

	/**
	 * Attribute that represents the Mail Server: Authentication: Password.
	 */
	private String mailServerAuthPassword = null;

	/**
	 * Attribute that represents the maximun time allowed, in milliseconds, for
	 * establishing the SMTP connection.
	 */
	private Integer connectionTimeout;
	/**
	 * Attribute that represents the maximun time allowed, in milliseconds, for
	 * sending the mail messages.
	 */
	private Integer readingTimeout;
	/**
    * Attribute that indicates whether TLS encryption should be enabled or disabled.
    */
	private boolean tslEnabled = false;

	/**
	 * Constructor method for the class EMailTimeLimitedOperation.java.
	 * 
	 * @throws EMailException
	 *             In case of some error building the configuration for send the
	 *             email.
	 */
	private EMailTimeLimitedOperation() throws EMailException {
		super();
		ConfServerMail csm = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices()
				.getConfServerMailService().getAllConfServerMail();
		if (csm == null) {
			throw new EMailException(IValetException.COD_201,
					Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_015));
		} else {
			initializeServerMailConfiguration(csm);
		}
	}

	/**
	 * This constructor method sets an email to be sended through internal mail
	 * server.
	 * 
	 * @param listAddresseesParam
	 *            Parameter that represents the list of addressees to the
	 *            e-mail.
	 * @param subjectParam
	 *            Parameter that represents the subject of the e-mail.
	 * @param message
	 *            Parameter that represents the message of the e-mail.
	 * @throws EMailException
	 *             If some of the input parameters is null.
	 */
	public EMailTimeLimitedOperation(List<String> listAddresseesParam, String subjectParam, String message)
			throws EMailException {

		this();

		// Comprobamos que haya destinatarios a los que enviar el e-mail
		if (listAddresseesParam == null || listAddresseesParam.isEmpty()) {
			throw new EMailException(IValetException.COD_201,
					Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_005));
		} else {
			for (String mailAddress : listAddresseesParam) {
				if (checkEmailAdress(mailAddress)) {
					try {
						mailAddresses.add(new InternetAddress(mailAddress));
					} catch (AddressException e) {
						continue;
					}
				} else {
					LOGGER.warn(Language.getFormatResCoreGeneral(ICoreGeneralMessages.EMAIL_008,
							new Object[] { mailAddress }));
				}
			}
			if (mailAddresses.isEmpty()) {
				throw new EMailException(IValetException.COD_201,
						Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_005));
			}
		}
		// Comprobamos que se haya indicado un asunto
		if (subjectParam == null) {
			throw new EMailException(IValetException.COD_201,
					Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_006));
		}
		subject = subjectParam;
		// Comprobamos que se haya indicado un mensaje
		if (message == null) {
			throw new EMailException(IValetException.COD_201,
					Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_007));
		}
		messageBuilder = new StringBuilder(message);
		
	
		//setMaxTimeForRunningThread(NumberConstants.NUM10000);
		
		

	}

	/**
	 * Auxiliar method that sets all the properties needed for the mail server
	 * from a data base instance.
	 * 
	 * @param csm
	 *            Object that represents an instance of a mail server in the
	 *            data base.
	 * @throws EMailException
	 *             In case of some error setting the configuration.
	 */
	private void initializeServerMailConfiguration(ConfServerMail csm) throws EMailException {

		mailServerIssuer = csm.getIssuerMail();
		if (!checkEmailAdress(mailServerIssuer)) {
			LOGGER.warn(Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_000));
		}
		mailServerHost = csm.getHostMail();
		if (UtilsStringChar.isNullOrEmptyTrim(mailServerHost)) {
			throw new EMailException(IValetException.COD_201,
					Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_001));
		}
		boolean validPort = true;
		Long portLong = csm.getPortMail();
		if (portLong != null) {
			mailServerPort = portLong.intValue();
			validPort = mailServerPort >= 0;
		} else {
			validPort = false;
		}
		if (!validPort) {
			LOGGER.warn(Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_002));
			mailServerPort = NumberConstants.NUM25;
		}
		
		connectionTimeout = csm.getConnectionTimeout();
		readingTimeout = csm.getReadingTimeout();
        tslEnabled = csm.getTlsEnabled();
		initializeServerMailConfigurationAuth(csm);

	}

	/**
	 * Auxiliar method that sets the properties for authentication needed for
	 * the mail server from a data base instance.
	 * 
	 * @param csm
	 *            Object that represents an instance of a mail server in the
	 *            data base.
	 */
	private void initializeServerMailConfigurationAuth(ConfServerMail csm) {

		mailServerAuthUseAuthentication = csm.getUseAuthenticationMail() == null ? false
				: csm.getUseAuthenticationMail().booleanValue();
		if (mailServerAuthUseAuthentication) {
			mailServerAuthUserName = csm.getUserMail();
			mailServerAuthPassword = csm.getPasswordMail();
			if (mailServerAuthPassword != null) {
				try {
					mailServerAuthPassword = new String(
							UtilsAESCipher.getInstance().decryptMessage(mailServerAuthPassword));
				} catch (CipherException e) {
					LOGGER.warn(Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_003));
					mailServerAuthPassword = null;
				}
			}
			if (mailServerAuthUserName == null || mailServerAuthPassword == null) {
				LOGGER.warn(Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_004));
				mailServerAuthUseAuthentication = false;
				mailServerAuthUserName = null;
				mailServerAuthPassword = null;
			}
		}

	}

	/**
	 * Checks if the input email address is valid.
	 * 
	 * @param emailAddress
	 *            Email address to validate.
	 * @return <code>true</code> if the input email address is valid, otherwise
	 *         <code>false</code>.
	 */
	private boolean checkEmailAdress(String emailAddress) {

		boolean result = true;
		try {
			InternetAddress ia = new InternetAddress(emailAddress);
			ia.validate();
		} catch (AddressException e) {
			result = false;
		} catch (Exception e2) {
			LOGGER.error(e2.getMessage(), e2);
		}
		return result;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see es.gob.valet.commons.utils.threads.ATimeLimitedOperation#doOperationThread()
	 */
	@Override
	protected void doOperationThread() throws Exception {

		useInternalMailServer();

	}

	/**
	 * Private method that implements the logic to send an email through the
	 * internal mail server.
	 * @throws EMailException 
	 */
	private void useInternalMailServer() throws EMailException {

		// Se inicializan las propiedades junto con una sesión por
		// defecto.
		Properties props = new Properties();
		props.put("mail.smtp.host", mailServerHost);
        props.put("mail.smtp.starttls.enable", tslEnabled);
		props.put("mail.smtp.port", Integer.toString(mailServerPort));
		props.put("mail.smtp.auth", Boolean.toString(mailServerAuthUseAuthentication));
		props.put("mail.smtp.connectiontimeout", connectionTimeout);// tiempo d conexión
		props.put("mail.smtp.timeout", readingTimeout);// tiempo de mandar el mensaje

		Session session = Session.getInstance(props);

		try {

			// Se crea el mensaje.
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(mailServerIssuer));

			if (!mailAddresses.isEmpty()) {
				msg.setRecipients(Message.RecipientType.TO,
						mailAddresses.toArray(new InternetAddress[mailAddresses.size()]));
				msg.setSubject(subject);

				msg.setSentDate(Calendar.getInstance().getTime());
				msg.setText(messageBuilder.toString());

				// Se intenta la conexión con el servidor de correo.
				Transport transport = session.getTransport(TRANSPORT_PROTOCOL_SMTP);
				transport.connect(mailServerHost, mailServerAuthUserName, mailServerAuthPassword);

				// Se efectúa el envío el mensaje.
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();
			}

		} catch (MessagingException e) {
			sendException(e);
		}

	}

	/**
	 * Method that manages the send of the produced exception.
	 * 
	 * @param mex
	 *            Parameter that represents the exception to send.
	 */
	private void sendException(Exception mex) {
		LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.EMAIL_009, new Object[] {subject}));
		

		Exception ex = mex;
		do {
			if (ex instanceof SendFailedException) {
				SendFailedException sfex = (SendFailedException) ex;
				Address[] invalid = sfex.getInvalidAddresses();
				sendExceptionAux(ICoreGeneralMessages.EMAIL_010, invalid);

				Address[] validUnsent = sfex.getValidUnsentAddresses();
				sendExceptionAux(ICoreGeneralMessages.EMAIL_011, validUnsent);

				Address[] validSent = sfex.getValidSentAddresses();
				sendExceptionAux(ICoreGeneralMessages.EMAIL_012, validSent);
			}
			else if(ex instanceof SocketTimeoutException || ex instanceof MailConnectException){
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.EMAIL_016, ex.getMessage()));
				ex = null;
			}
			else {
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.EMAIL_017, ex.getMessage()));
				ex = null;
			}
			
		} while (ex != null);
		LOGGER.error(stack2string(mex));

	}

	/**
	 * Method that shows in the log the result of the e-mail send for each
	 * addressee.
	 * 
	 * @param errorMsg
	 *            Parameter that represents the message for the error.
	 * @param arrayAddress
	 *            Parameter that represents the list of addressees.
	 */
	private void sendExceptionAux(String errorMsg, Address[] arrayAddress) {
		LOGGER.error(Language.getResCoreGeneral(errorMsg));
		if (arrayAddress != null) {
			for (int i = 0; i < arrayAddress.length; i++) {
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.EMAIL_013,
						new Object[] { arrayAddress[i] }));
			}
		}
	}

	/**
	 * Method that obtains the text from the stack trace of an exception.
	 * 
	 * @param e
	 *            Parameter that represents the exception to process.
	 * @return the text from the stack trace of the exception.
	 */
	private String stack2string(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			e.printStackTrace(pw);
			return SEPARATOR + UtilsStringChar.SPECIAL_SYSTEM_LINE_SEPARATOR_STRING + sw.toString() + SEPARATOR
					+ UtilsStringChar.SPECIAL_SYSTEM_LINE_SEPARATOR_STRING;
		} catch (Exception e2) {
			return Language.getResCoreGeneral(ICoreGeneralMessages.EMAIL_014);
		} finally {
			// Cerramos recursos
			UtilsResources.safeCloseWriter(pw);
			UtilsResources.safeCloseWriter(sw);
		}
	}

	/**
	 * Method that appends a text at the end of the body of the e-mail message.
	 * 
	 * @param text
	 *            Parameter that represents the text to append.
	 */
	public final void appendToBodyMessage(String text) {
		messageBuilder.append(text);
	}

	/**
	 * Method that appends a text and a new line at the end of the body of the
	 * e-mail message.
	 * 
	 * @param text
	 *            String with text to append.
	 */
	public final void appendToBodyMessageWithNewLine(String text) {
		appendToBodyMessage(text);
		messageBuilder.append(UtilsStringChar.SPECIAL_LINE_BREAK_STRING);
	}

}
