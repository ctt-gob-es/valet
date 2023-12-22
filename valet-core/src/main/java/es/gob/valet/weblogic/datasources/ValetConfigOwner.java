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
 * <b>File:</b><p>es.gob.valet.weblogic.datasources.ValetConfigOwner.java.</p>
 * <b>Description:</b><p>Spring configuration class that sets the configuration of Spring components, entities and repositories.</p>
 * <b>Project:</b><p>Spring configuration class that sets the configuration of Spring components, entities and repositories.</p>
 * <b>Date:</b><p>21/12/2023.</p>
 * @author Gobierno de España.
 * @version 1.0, 21/12/2023.
 */
package es.gob.valet.weblogic.datasources;

import java.util.HashMap;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * <p>Spring configuration class that sets the configuration of Spring components, entities and repositories.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 21/12/2023.
 */
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "es.gob.valet.persistence.configuration.model.repository", entityManagerFactoryRef = "entityManagerValetConfigOwner", transactionManagerRef = "transManagerValetConfigOwner")
public class ValetConfigOwner {

	/**
	 * Attribute that represents the logger of this class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ValetConfigOwner.class);
	
	/**
	 * Attribute that represents the HSQL JNDI name.
	 */
	@Value("${valetConfigOwner-jndi-name}")
	private String oracleJndiDataSource;

	/**
	 * Attribute that represents the HSQL dialect.
	 */
	@Value("${valetConfigOwner-dialect}")
	private String oracleDialect;

	/**
	 * Attribute that represents if format SQL.
	 */
	@Value("${valetConfigOwner-format-sql}")
	private Boolean formatSql;

	/**
	 * Attribute that represents the DDL auto.
	 */
	@Value("${valetConfigOwner-ddl-auto}")
	private String ddlAuto;

	/**
	 * Attribute that represents if show SQL.
	 */
	@Value("${valetConfigOwner-show-sql}")
	private Boolean showSQL;

	/**
	 * Attribute that represents if use new id generator mappings.
	 */
	@Value("${valetConfigOwner-use-new-id-generator-mappings}")
	private Boolean useNewIdGeneratorMappings;
	
	/**
	 * Bean of spring with transaction manager of ValetConfigOwner scheme.
	 * 
	 * @return transaction manager object.
	 */
	@Bean
	public PlatformTransactionManager transManagerValetConfigOwner() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerValetConfigOwner().getObject());
		return transactionManager;
	}

	/**
	 * 
	 * Bean of spring with local container entity manager factory bean of scheme ValetConfigOwner scheme.
	 * 
	 * @return local container entity manager factory bean.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerValetConfigOwner() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setDataSource(this.hsqlDataSource());
		em.setPackagesToScan(new String[] { "es.gob.valet.persistence.configuration.model.entity" });
		em.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", oracleDialect);
		properties.put("hibernate.format_sql", String.valueOf(formatSql));
		properties.put("hibernate.hbm2ddl.auto", ddlAuto);
		properties.put("hibernate.show_sql", String.valueOf(showSQL));
		properties.put("hibernate.id.new_generator_mappings", String.valueOf(useNewIdGeneratorMappings));
		
		em.setJpaPropertyMap(properties);

		return em;
	}

	/**
	 * Bean of spring with data source of scheme ValetConfigOwner.
	 * 
	 * @return data source.
	 */
	@Bean
	public DataSource hsqlDataSource() {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName(oracleJndiDataSource);
		bean.setProxyInterface(DataSource.class);
		try {
			bean.afterPropertiesSet();
		} catch (IllegalArgumentException | NamingException e) {
			LOGGER.error(e);
		}
		return (DataSource) bean.getObject();
	}
}
