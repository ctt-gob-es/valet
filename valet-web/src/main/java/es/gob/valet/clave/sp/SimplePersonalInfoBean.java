package es.gob.valet.clave.sp;

public interface SimplePersonalInfoBean {

	public abstract String getDni();

	public abstract void setDni(String dni);

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

	public abstract String getApellidos();

	public abstract void setApellidos(String apellidos);

	public abstract String getInfoToken();

	public abstract void setInfoToken(String infoToken);
	
	public abstract String getErrorType();

	public abstract void setErrorType(String errorType);

	public abstract String getErrorMessage();

	public abstract void setErrorMessage(String errorMessage);

	public abstract String getServiceUrl();

	public abstract void setServiceUrl(String serviceUrl);
}
