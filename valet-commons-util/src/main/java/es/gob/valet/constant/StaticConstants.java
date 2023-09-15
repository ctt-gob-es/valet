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
 * <b>File:</b><p>es.gob.valet.constant.StaticConstants.java.</p>
 * <b>Description:</b><p> Interface that contains the static constants for the static configuration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/12/2022.</p>
 * @author Gobierno de España.
 * @version 1.1, 15/09/2023.
 */
package es.gob.valet.constant;


/** 
 * <p>Interface that contains the static constants for the static configuration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 15/09/2023.
 */
public class StaticConstants {
	/**
	 * Attribute that represents the password for the system keystores.
	 */
	@SuppressWarnings("squid:S2068")
	public static final String AES_PASSWORD = "aes.password";
	
	/**
	 * Attribute that represents the AES algorithm name.
	 */
	public static final String AES_ALGORITHM = "aes.algorithm";
	
	/**
	 * Attribute that represents the Padding algorithm for the AES cipher.
	 */
	public static final String AES_PADDING_ALG = "aes.padding.alg";
	
	/**
	 * Attribute that represents the key for the property that indicates the Padding algorithm for the AES cipher.
	 */
	public static final String AES_NO_PADDING_ALG = "aes.nopadding.alg";
}
