package es.gob.valet.persistence.configuration.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VALET_SERVER")
public class ValetServer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_VALET_SERVER", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valetServerSeq")
    @SequenceGenerator(name = "valetServerSeq", sequenceName = "SQ_VALET_SERVER", allocationSize = 1)
    private Long idValetServer;

    @Column(name = "SERVER_NAME", nullable = false, length = 255)
    private String serverName;

    @Column(name = "SERVER_URL", nullable = false, length = 4000)
    private String serverUrl;

    @Column(name = "APPLICATION", nullable = false, length = 100)
    private String application;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "CONNECTION_TIMEOUT")
    private Integer connectionTimeout;

    // Getters and Setters

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}