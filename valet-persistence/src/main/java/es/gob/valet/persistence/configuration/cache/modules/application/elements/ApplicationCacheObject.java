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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.application.elements.ApplicationCacheObject.java.</p>
 * <b>Description:</b><p>Class that represents an application in the clustered cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 15/09/2023.
 */
package es.gob.valet.persistence.configuration.cache.modules.application.elements;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.application.exceptions.ApplicationCacheException;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;

/**
 * <p>Class that represents an application in the clustered cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 15/09/2023.
 */
public class ApplicationCacheObject extends ConfigurationCacheObject {

	/**
	 * Attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -3645579138794227401L;

	/**
	 * Attribute that represents the application ID.
	 */
	private long applicationId = -1;

	/**
	 * Attribute that represents the identificator of the application in the system.
	 */
	private String identificator;

	/**
	 * Attribute that represents the name of the application.
	 */
	private String name = null;

	/**
	 * Attribute that represents the name of the responsible of the application.
	 */
	private String responsibleName = null;

	/**
	 * Attribute that represents the phone number of the responsible of the application.
	 */
	private String responsiblePhone = null;

	/**
	 * Attribute that represents the surnames of the responsible of the application.
	 */
	private String responsibleSurnames = null;

	/**
	 * Attribute that represents the email of the responsible of the application.
	 */
	private String responsibleMail = null;

	/**
	 *
	 * Constructor method for the class ApplicationCacheObject.java.
	 */
	private ApplicationCacheObject() {
		super();
	}

	/**
	 * Constructor method for the class ApplicationCacheObject.java.
	 * @param app ApplicationValet POJO from which build the Application Cache Object.
	 * @throws ApplicationCacheException In case of some error building the application.
	 */
	public ApplicationCacheObject(ApplicationValet app) throws ApplicationCacheException {
		this();
		// si el pojo recibido es nulo, se lanza una excepción ya que no se
		// puede inicializar el objeto
		if (app == null) {
			throw new ApplicationCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_APPLICATION_CACHE_LOG000));
		} else {
			setApplicationId(app.getIdApplication());
			setIdentificator(app.getIdentificator());
			setName(app.getName());
			setResponsibleName(app.getResponsibleName());
			setResponsibleSurnames(app.getResponsibleSurnames());
			setResponsibleMail(app.getResponsibleMail());
			setResponsiblePhone(app.getResponsiblePhone());
		}

	}

	/**
	 * Gets the value of the attribute {@link #applicationId}.
	 * @return the value of the attribute {@link #applicationId}.
	 */
	public long getApplicationId() {
		return applicationId;
	}

	/**
	 * Sets the value of the attribute {@link #applicationId}.
	 * @param applicationIdParam The value for the attribute {@link #applicationId}.
	 */
	public void setApplicationId(long applicationIdParam) {
		this.applicationId = applicationIdParam;
	}

	/**
	 * Gets the value of the attribute {@link #identificator}.
	 * @return the value of the attribute {@link #identificator}.
	 */
	public String getIdentificator() {
		return identificator;
	}

	/**
	 * Sets the value of the attribute {@link #identificator}.
	 * @param identificatorParam The value for the attribute {@link #identificator}.
	 */
	public void setIdentificator(String identificatorParam) {
		this.identificator = identificatorParam;
	}

	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param nameParam The value for the attribute {@link #name}.
	 */
	public void setName(String nameParam) {
		this.name = nameParam;
	}

	/**
	 * Gets the value of the attribute {@link #responsibleName}.
	 * @return the value of the attribute {@link #responsibleName}.
	 */
	public String getResponsibleName() {
		return responsibleName;
	}

	/**
	 * Sets the value of the attribute {@link #responsibleName}.
	 * @param responsibleNameParam The value for the attribute {@link #responsibleName}.
	 */
	public void setResponsibleName(String responsibleNameParam) {
		this.responsibleName = responsibleNameParam;
	}

	/**
	 * Gets the value of the attribute {@link #responsiblePhone}.
	 * @return the value of the attribute {@link #responsiblePhone}.
	 */
	public String getResponsiblePhone() {
		return responsiblePhone;
	}

	/**
	 * Sets the value of the attribute {@link #responsiblePhone}.
	 * @param responsiblePhoneParam The value for the attribute {@link #responsiblePhone}.
	 */
	public void setResponsiblePhone(String responsiblePhoneParam) {
		this.responsiblePhone = responsiblePhoneParam;
	}

	/**
	 * Gets the value of the attribute {@link #responsibleSurnames}.
	 * @return the value of the attribute {@link #responsibleSurnames}.
	 */
	public String getResponsibleSurnames() {
		return responsibleSurnames;
	}

	/**
	 * Sets the value of the attribute {@link #responsibleSurnames}.
	 * @param responsibleSurnamesParam The value for the attribute {@link #responsibleSurnames}.
	 */
	public void setResponsibleSurnames(String responsibleSurnamesParam) {
		this.responsibleSurnames = responsibleSurnamesParam;
	}

	/**
	 * Gets the value of the attribute {@link #responsibleMail}.
	 * @return the value of the attribute {@link #responsibleMail}.
	 */
	public String getResponsibleMail() {
		return responsibleMail;
	}

	/**
	 * Sets the value of the attribute {@link #responsibleMail}.
	 * @param responsibleMailParam The value for the attribute {@link #responsibleMail}.
	 */
	public void setResponsibleMail(String responsibleMailParam) {
		this.responsibleMail = responsibleMailParam;
	}

}
