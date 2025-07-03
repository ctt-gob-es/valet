package es.gob.valet.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = { "es.gob.valet" })
public class TestsValetSpringInWeblogic {

	public static void main(String[ ] args) {
		System.setProperty("weblogic.transaction.allowOverrideSetRollbackReason", "true");
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		System.setProperty("UseSunHttpHandler", "true");
		
		
		System.setProperty("valet.config.path", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\conf");
		System.setProperty("logging.config", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\conf\\valet-log4j.xml");
		System.setProperty("weblogic.config.path.logs.dir", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\logs");
		System.setProperty("spring.config.location", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\conf\\application.properties");
		System.setProperty("file.encoding", "UTF-8");
		System.setProperty("com.sun.management.jmxremote", "");
		System.setProperty("com.sun.management.jmxremote.port", "8123");
		System.setProperty("com.sun.management.jmxremote.authenticate", "false");
		System.setProperty("com.sun.management.jmxremote.ssl", "false");
		
		System.setProperty("clave.path", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\conf\\clave");
		System.setProperty("integra.config", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\conf\\clave\\integra");
		System.setProperty("weblogic.server.temp.dir", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\tmp");

		// Levanta el contexto de Spring Boot
		ConfigurableApplicationContext context = SpringApplication.run(TestsValetSpringInWeblogic.class, args);
	}

}
