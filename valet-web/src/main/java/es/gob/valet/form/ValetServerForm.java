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
 * <b>File:</b><p>es.gob.valet.form.ValetServerForm.java.</p>
 * <b>Description:</b><p>Class that represents the backing form for adding/editing a ValetServer entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.form;

import java.io.Serializable;

import es.gob.valet.persistence.configuration.model.entity.ValetServer;

/**
 * Class that represents the backing form for adding/editing a ValetServer entity.
 * Project: Platform for detection and validation of certificates recognized in European TSL.
 * @version 1.0, 31/07/2025.
 */
public class ValetServerForm implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Attribute that represents the value of the primary key as a hidden input in the form.
	 */
	private Long idValetServer;

	/**
	 * Attribute that represents the value of the input server name in the form.
	 */
	private String serverName;

	/**
	 * Attribute that represents the value of the input server URL in the form.
	 */
	private String serverUrl;

	/**
	 * Attribute that represents the value of the application identifier in the form.
	 */
	private String application;

	/**
	 * Attribute that represents the version string selected in the form.
	 */
	private String version;

	/**
	 * Attribute that represents the value of the connection timeout in milliseconds.
	 */
	private Integer connectionTimeout;

	/**
	 * Default constructor.
	 */
	public ValetServerForm() {
	}

	/**
	 * Constructor that maps the fields from a {@link ValetServer} entity.
	 * @param entity The ValetServer entity to map.
	 */
	public ValetServerForm(ValetServer entity) {
		if (entity != null) {
			this.idValetServer = entity.getIdValetServer();
			this.serverName = entity.getServerName();
			this.serverUrl = entity.getServerUrl();
			this.application = entity.getApplication();

			if (entity.getVersion() != null) {
				switch (entity.getVersion()) {
					case 1:
						this.version = "1.1.0";
						break;
					case 2:
						this.version = "1.2.0";
						break;
					default:
						this.version = String.valueOf(entity.getVersion());
						break;
				}
			}

			this.connectionTimeout = entity.getConnectionTimeout();
		}
	}

	/**
	 * Converts this form to a {@link ValetServer} entity.
	 * @return The mapped ValetServer entity.
	 */
	public ValetServer toEntity() {
		ValetServer entity = new ValetServer();
		entity.setIdValetServer(this.idValetServer);
		entity.setServerName(this.serverName);
		entity.setServerUrl(this.serverUrl);
		entity.setApplication(this.application);

		if ("1.1.0".equals(this.version)) {
			entity.setVersion(1);
		} else if ("1.2.0".equals(this.version)) {
			entity.setVersion(2);
		} else {
			try {
				entity.setVersion(Integer.parseInt(this.version));
			} catch (NumberFormatException e) {
				entity.setVersion(null);
			}
		}

		entity.setConnectionTimeout(this.connectionTimeout);

		return entity;
	}

	/**
	 * Gets the value of the attribute {@link #idValetServer}.
	 * @return the value of the attribute {@link #idValetServer}.
	 */
	public Long getIdValetServer() {
		return idValetServer;
	}

	/**
	 * Sets the value of the attribute {@link #idValetServer}.
	 * @param idValetServer The value for the attribute {@link #idValetServer}.
	 */
	public void setIdValetServer(Long idValetServer) {
		this.idValetServer = idValetServer;
	}

	/**
	 * Gets the value of the attribute {@link #serverName}.
	 * @return the value of the attribute {@link #serverName}.
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * Sets the value of the attribute {@link #serverName}.
	 * @param serverName The value for the attribute {@link #serverName}.
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * Gets the value of the attribute {@link #serverUrl}.
	 * @return the value of the attribute {@link #serverUrl}.
	 */
	public String getServerUrl() {
		return serverUrl;
	}

	/**
	 * Sets the value of the attribute {@link #serverUrl}.
	 * @param serverUrl The value for the attribute {@link #serverUrl}.
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * Gets the value of the attribute {@link #application}.
	 * @return the value of the attribute {@link #application}.
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * Sets the value of the attribute {@link #application}.
	 * @param application The value for the attribute {@link #application}.
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * Gets the value of the attribute {@link #version}.
	 * @return the value of the attribute {@link #version}.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the value of the attribute {@link #version}.
	 * @param version The value for the attribute {@link #version}.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the value of the attribute {@link #connectionTimeout}.
	 * @return the value of the attribute {@link #connectionTimeout}.
	 */
	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * Sets the value of the attribute {@link #connectionTimeout}.
	 * @param connectionTimeout The value for the attribute {@link #connectionTimeout}.
	 */
	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
}