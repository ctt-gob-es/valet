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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.Address.java.</p>
 * <b>Description:</b><p>Class that represents an Address with all its information
 * not dependent of the specification or TSL version.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.impl.common;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Class that represents an Address with all its information
 * not dependent of the specification or TSL version.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class Address implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -2819310565149041796L;

	/**
	 * Attribute that represents the postal addresses in all the presented languages.
	 */
	private transient Map<String, List<PostalAddress>> postalAddresses = null;

	/**
	 * Attribute that represents the electronic addresses in all the presented languages.
	 */
	private transient Map<String, List<URI>> electronicAddresses = null;

	/**
	 * Constructor method for the class Address.java.
	 */
	public Address() {
		super();
		postalAddresses = new HashMap<String, List<PostalAddress>>();
		electronicAddresses = new HashMap<String, List<URI>>();
	}

	/**
	 * Gets the value of the attribute {@link #postalAddresses}.
	 * @return the value of the attribute {@link #postalAddresses}.
	 */
	public final Map<String, List<PostalAddress>> getPostalAddresses() {
		return postalAddresses;
	}

	/**
	 * Adds a new Postal Addres for the specified language.
	 * @param language language to which add a new postal address.
	 * @param pa Postal Address to add.
	 */
	public final void addNewPostalAddress(String language, PostalAddress pa) {

		if (language != null && pa != null) {

			List<PostalAddress> paList = postalAddresses.get(language);
			if (paList == null) {
				paList = new ArrayList<PostalAddress>();
			}
			paList.add(pa);
			postalAddresses.put(language, paList);

		}

	}

	/**
	 * Sets the value of the attribute {@link #postalAddresses}.
	 * @param paList The value for the attribute {@link #postalAddresses}.
	 */
	public final void setPostalAddresses(Map<String, List<PostalAddress>> paList) {
		this.postalAddresses = paList;
	}

	/**
	 * Checks if there is some postal address added to this address.
	 * @return <code>true</code> if there is, otherwise <code>false</code>.
	 */
	public final boolean isThereSomePostalAddress() {
		return !postalAddresses.isEmpty();
	}

	/**
	 * Gets the value of the attribute {@link #electronicAddresses}.
	 * @return the value of the attribute {@link #electronicAddresses}.
	 */
	public final Map<String, List<URI>> getElectronicAddresses() {
		return electronicAddresses;
	}

	/**
	 * Adds a new Electronic Addres for the specified language.
	 * @param language language to which add a new electronic address.
	 * @param ea Electronic address to add.
	 */
	public final void addNewElectronicAddress(String language, URI ea) {

		if (language != null && ea != null) {

			List<URI> eaList = electronicAddresses.get(language);
			if (eaList == null) {
				eaList = new ArrayList<URI>();
			}
			eaList.add(ea);
			electronicAddresses.put(language, eaList);
		}

	}

	/**
	 * Sets the value of the attribute {@link #electronicAddresses}.
	 * @param eaList The value for the attribute {@link #electronicAddresses}.
	 */
	public final void setElectronicAddresses(Map<String, List<URI>> eaList) {
		this.electronicAddresses = eaList;
	}

	/**
	 * Checks if there is some electronic address added to this address.
	 * @return <code>true</code> if there is, otherwise <code>false</code>.
	 */
	public final boolean isThereSomeElectronicAddress() {
		return !electronicAddresses.isEmpty();
	}

}
