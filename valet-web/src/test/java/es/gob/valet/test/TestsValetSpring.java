package es.gob.valet.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = { "es.gob.valet" })
public class TestsValetSpring {

	public static void main(String[ ] args) {
		System.setProperty("weblogic.transaction.allowOverrideSetRollbackReason", "true");
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		System.setProperty("UseSunHttpHandler", "true");
		System.setProperty("valet.config.path", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\conf\\");
		System.setProperty("logging.config", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\conf\\valet-log4j.xml");
		System.setProperty("weblogic.config.path.logs.dir", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\logs");
		System.setProperty("spring.config.location", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\conf\\application.properties");
		System.setProperty("weblogic.server.temp.dir", "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\VALET-ADM\\tmp\\");
		System.setProperty("file.encoding", "UTF-8");
		System.setProperty("com.sun.management.jmxremote.authenticate", "false");
		System.setProperty("com.sun.management.jmxremote.ssl", "false");

		// Configuración de la memoria
		System.setProperty("XX:MetaspaceSize", "1024M");
		System.setProperty("XX:MaxMetaspaceSize", "4096M");

		// Levanta el contexto de Spring Boot
		ConfigurableApplicationContext context = SpringApplication.run(TestsValetSpring.class, args);
	}

}
