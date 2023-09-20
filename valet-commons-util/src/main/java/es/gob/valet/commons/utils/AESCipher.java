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
 * <b>File:</b><p>es.gob.valet.afirma.utils.AESCipher.java.</p>
 * <p>Class to decode and encode password using AES algorithm.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/12/2022.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.commons.utils;

import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import es.gob.valet.constant.StaticConstants;
import es.gob.valet.exceptions.CipherException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CommonsUtilLogMessages;

/** 
 * <p>Class to decode and encode password using AES algorithm.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class AESCipher implements Serializable {
	/**
	 * Attribute that represents . 
	 */
	private static final long serialVersionUID = 1094502415533707370L;

	/**
	 * Attribute that represents the key for decode the passwords.
	 */
	private static Key key;

	/**
	 * Attribute that represents an instance of the class.
	 */
	private static AESCipher instance = null;
	
	/**
	 * Method that inits the class.
	 * @throws CipherException If the method fails.
	 */
	private AESCipher() throws CipherException {
			
		key = new SecretKeySpec(StaticValetConfig.getProperty(StaticConstants.AES_PASSWORD).getBytes(), StaticValetConfig.getProperty(StaticConstants.AES_ALGORITHM));
		
	}
	
	/**
	 * Method that obtains an instance of the class.
	 * @return an instance of the class.
	 * @throws CipherException If the method fails.
	 */
	public static synchronized AESCipher getInstance() throws CipherException {
		if (instance == null) {
			instance = new AESCipher();
		}
		return instance;
	}
	
	/**
	 * Method that decrypting a message.
	 * @param msg The message to decrypt.
	 * @return the message decrypted.
	 * @throws CipherException If the method fails.
	 */
	public byte[ ] decryptMessage(String msg) throws CipherException {
		try {
			Cipher cipher = Cipher.getInstance(StaticValetConfig.getProperty(StaticConstants.AES_NO_PADDING_ALG));
			IvParameterSpec ivspec = new IvParameterSpec(key.getEncoded());
			cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
			return cipher.doFinal(Base64.decodeBase64(msg));
		} catch (InvalidKeyException | IllegalBlockSizeException e) {
			throw new CipherException(Language.getResCommonsUtilsValet(CommonsUtilLogMessages.ERRORUTILS002));
		} catch (BadPaddingException e) {
			Cipher cipher;
			try {
				cipher = Cipher.getInstance(StaticValetConfig.getProperty(StaticConstants.AES_PADDING_ALG));
				cipher.init(Cipher.DECRYPT_MODE, key);
				return cipher.doFinal(Base64.decodeBase64(msg));
			} catch (InvalidKeyException | IllegalBlockSizeException
					| BadPaddingException e1) {
				throw new CipherException(Language.getResCommonsUtilsValet(CommonsUtilLogMessages.ERRORUTILS002));
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e1) {
				throw new CipherException(Language.getResCommonsUtilsValet(CommonsUtilLogMessages.ERRORUTILS002));
			}
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException e) {
			throw new CipherException(Language.getResCommonsUtilsValet(CommonsUtilLogMessages.ERRORUTILS002));
		}
	}
}
