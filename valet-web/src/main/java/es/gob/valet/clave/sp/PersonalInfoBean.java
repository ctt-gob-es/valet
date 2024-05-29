package es.gob.valet.clave.sp;

public class PersonalInfoBean implements SimplePersonalInfoBean {

	private String dni;
	
	private String nombre;
	
	private String apellidos;
	
	private String infoToken; 
	
	private String errorType;
	
	private String errorMessage;
	
	private String serviceUrl;

	@Override
	public String getDni() {
		return this.dni;
	}

	@Override
	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getApellidos() {
		return this.apellidos;
	}

	@Override
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public String getInfoToken() {
		return this.infoToken;
	}
	
	@Override
	public void setInfoToken(String infoToken) {
		this.infoToken = infoToken;
	}
	
	@Override
	public String getErrorType() {
		return errorType;
	}

	@Override
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getServiceUrl() {
		return this.serviceUrl;
	}
	
	@Override
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
}
