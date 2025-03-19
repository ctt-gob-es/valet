package es.gob.valet.test;


import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TomcatJNDIConfToTest {
	
	@Bean 
	public TomcatServletWebServerFactory tomcatFactory() {
	    return new TomcatServletWebServerFactory() {
//	        protected void postProcessContext(Context context) {
//	            ContextResource resource = new ContextResource();
//	            resource.setName("jndi/valetConfigOwner");
//	            resource.setType(DataSource.class.getName());
//	            resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
//	            resource.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
//	            resource.setProperty("url", "jdbc:oracle:thin:@localhost:1521:XE");
//	            resource.setProperty("username", "VALET_CONFIGOWNER");
//	            resource.setProperty("password", "123456");
//	            resource.setProperty("maxTotal", "20");
//	            resource.setProperty("maxIdle", "10");
//	            resource.setProperty("minIdle", "5");
//	            context.getNamingResources().addResource(resource);
//	        }
	    };
	}
}
