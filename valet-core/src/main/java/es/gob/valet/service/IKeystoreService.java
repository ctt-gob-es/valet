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
 * <b>File:</b><p>es.gob.valet.service.IKeystoreService.java.</p>
 * <b>Description:</b><p> Interface that provides communication with the operations of the persistence layer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/12/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 23/12/2022.
 */
package es.gob.valet.service;

import java.security.KeyStoreException;

import es.gob.valet.crypto.exception.CryptographyException;
import es.gob.valet.persistence.configuration.model.entity.Keystore;

/** 
 * <p>Interface that provides communication with the operations of the persistence layer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 23/12/2022.
 */
public interface IKeystoreService {
	/**
	 * Method that store and update keystore Java and Keystore Entity.
	 * 
	 * @param certificateInBytes parameter that contain a trusted certificate in bytes
	 * @param alias parameter that represents the alias of the certificate to obtain.
	 * @throws KeyStoreException If there is some error inserting the entry into the keystore.
	 */
	void saveCertificateKeystoreCA(byte[] certificateInBytes, String alias) throws KeyStoreException;
	
	/**
	 * Method that obtains the decoded password of the keystore represented by {@link #keystoreCacheObject}.
	 * @return the decoded password of the keystore represented by {@link #keystoreCacheObject}.
	 * @throws CryptographyException If the method fails.
	 */
	String getKeystoreDecodedPassword(Keystore keystore) throws CryptographyException;
}
