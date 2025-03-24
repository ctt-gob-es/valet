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
		
		
		System.setProperty("valet.config.path", "C:\\Tomcat\\apache-tomcat-8.5.24-valET-1.0.0\\conf\\");
		System.setProperty("logging.config", "C:\\Tomcat\\apache-tomcat-8.5.24-valET-1.0.0\\conf\\valet-log4j.xml");
		System.setProperty("weblogic.config.path.logs.dir", "C:\\Tomcat\\apache-tomcat-8.5.24-valET-1.0.0\\logs");
		System.setProperty("spring.config.location", "C:\\Tomcat\\apache-tomcat-8.5.24-valET-1.0.0\\conf\\application.properties");
		System.setProperty("file.encoding", "UTF-8");
		System.setProperty("com.sun.management.jmxremote", "");
		System.setProperty("com.sun.management.jmxremote.port", "8123");
		System.setProperty("com.sun.management.jmxremote.authenticate", "false");
		System.setProperty("com.sun.management.jmxremote.ssl", "false");
		
		System.setProperty("XX:MetaspaceSize", "1024M");
		System.setProperty("XX:MaxMetaspaceSize", "4096M");
		System.setProperty("XX:MinMetaspaceFreeRatio", "20");
		System.setProperty("XX:MaxMetaspaceFreeRatio", "50");
		System.setProperty("XX:+UseG1GC", "");
		System.setProperty("XX:MaxNewSize", "3584m");
		System.setProperty("XX:NewSize", "3584m");
		System.setProperty("XX:SurvivorRatio", "6");
		
		System.setProperty("clave.path", "C:\\Tomcat\\apache-tomcat-8.5.24-valET-1.0.0\\conf\\clave\\");
		System.setProperty("integra.config", "C:\\Tomcat\\apache-tomcat-8.5.24-valET-1.0.0\\conf\\clave\\integra\\");
		System.setProperty("weblogic.server.temp.dir", "C:\\Tomcat\\apache-tomcat-8.5.24-valET-1.0.0\\tmp\\");

		// Levanta el contexto de Spring Boot
		ConfigurableApplicationContext context = SpringApplication.run(TestsValetSpring.class, args);
	}

}
