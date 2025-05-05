package es.gob.valet.importsls;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncImport {

	@Bean(name = "executorServiceImport")
	public ExecutorService taskExecutor() {
		return Executors.newFixedThreadPool(10);
	}
}
