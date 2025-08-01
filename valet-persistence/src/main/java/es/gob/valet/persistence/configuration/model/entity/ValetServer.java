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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.ValetServer.java</p>
 * <b>Description:</b><p>Class that maps the <i>VALET_SERVER</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>31/07/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 31/07/2025.
 */
package es.gob.valet.persistence.configuration.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>Class that maps the <i>VALET_SERVER</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 31/07/2025.
 */
@Entity
@Table(name = "VALET_SERVER")
public class ValetServer implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attribute that represents the unique identifier for the ValetServer entity.
	 */
	@Id
	@Column(name = "ID_VALET_SERVER", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valetServerSeq")
	@SequenceGenerator(name = "valetServerSeq", sequenceName = "SQ_VALET_SERVER", allocationSize = 1)
	private Long idValetServer;

	/**
	 * Attribute that represents the name of the ValetServer.
	 */
	@Column(name = "SERVER_NAME", nullable = false, length = 255)
	private String serverName;

	/**
	 * Attribute that represents the base URL of the ValetServer.
	 */
	@Column(name = "SERVER_URL", nullable = false, length = 4000)
	private String serverUrl;

	/**
	 * Attribute that represents the name of the application associated with this ValetServer.
	 */
	@Column(name = "APPLICATION", nullable = false, length = 100)
	private String application;

	/**
	 * Attribute that represents the internal version of the ValetServer.
	 */
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	/**
	 * Attribute that represents the timeout in milliseconds for connecting to the ValetServer.
	 */
	@Column(name = "CONNECTION_TIMEOUT")
	private Integer connectionTimeout;

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
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets the value of the attribute {@link #version}.
	 * @param version The value for the attribute {@link #version}.
	 */
	public void setVersion(Integer version) {
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
