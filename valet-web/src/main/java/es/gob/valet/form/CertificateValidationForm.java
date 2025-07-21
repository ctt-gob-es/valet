package es.gob.valet.form;

import org.springframework.web.multipart.MultipartFile;

public class CertificateValidationForm {

	private Long valetServerId;
	private MultipartFile certFile;
	private boolean fetchMappings;

	// Campos avanzados
	private String tslLocation;
	private String detectionDate;
	private boolean checkRevocationStatus;
	private boolean returnRevocationEvidence;
	private boolean returnCertificateChain;

	// Getters y setters

	public Long getValetServerId() {
		return valetServerId;
	}

	public void setValetServerId(Long valetServerId) {
		this.valetServerId = valetServerId;
	}

	public MultipartFile getCertFile() {
		return certFile;
	}

	public void setCertFile(MultipartFile certFile) {
		this.certFile = certFile;
	}

	public boolean isFetchMappings() {
		return fetchMappings;
	}

	public void setFetchMappings(boolean fetchMappings) {
		this.fetchMappings = fetchMappings;
	}

	public String getTslLocation() {
		return tslLocation;
	}

	public void setTslLocation(String tslLocation) {
		this.tslLocation = tslLocation;
	}

	public String getDetectionDate() {
		return detectionDate;
	}

	public void setDetectionDate(String detectionDate) {
		this.detectionDate = detectionDate;
	}

	public boolean isCheckRevocationStatus() {
		return checkRevocationStatus;
	}

	public void setCheckRevocationStatus(boolean checkRevocationStatus) {
		this.checkRevocationStatus = checkRevocationStatus;
	}

	public boolean isReturnRevocationEvidence() {
		return returnRevocationEvidence;
	}

	public void setReturnRevocationEvidence(boolean returnRevocationEvidence) {
		this.returnRevocationEvidence = returnRevocationEvidence;
	}

	public boolean isReturnCertificateChain() {
		return returnCertificateChain;
	}

	public void setReturnCertificateChain(boolean returnCertificateChain) {
		this.returnCertificateChain = returnCertificateChain;
	}
}