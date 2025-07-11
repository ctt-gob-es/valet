package es.gob.valet.form;

import java.io.Serializable;

import es.gob.valet.persistence.configuration.model.entity.ValetServer;

public class ValetServerForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idValetServer;

	private String serverName;

	private String serverUrl;

	private String application;

	private String version;

	private Integer connectionTimeout;

	// Constructor por defecto
	public ValetServerForm() {
	}

	// Constructor que mapea desde la entidad JPA
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

	// Getters y setters

	public Long getIdValetServer() {
		return idValetServer;
	}

	public void setIdValetServer(Long idValetServer) {
		this.idValetServer = idValetServer;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
}