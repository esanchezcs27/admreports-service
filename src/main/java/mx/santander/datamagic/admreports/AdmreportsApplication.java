package mx.santander.datamagic.admreports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Descripcion: Esta es la clase principal que inicia la aplicacion de Spring Boot.
 * En ella se puede agregar el escaneo de distintos paquetes para que sean cargados en el contexto de Spring.
 * 
 * @author Santander Mexico
 */
@SpringBootApplication
@ComponentScan({"mx.santander.datamagic"})// , "com.santander", "mx.santander.commons"
public class AdmreportsApplication {

	/**
	 * Metodo main para inicializar la aplicacion Spring Boot
	 * @param args Argumentos opcionales de envio al programa
	 */
    public static void main(String[] args) {
		SpringApplication.run(AdmreportsApplication.class, args);
	}

	
	/**
	 * Metodo para permitir que el bean RestTemplate este disponible en el contexto de Spring
	 * @return El bean de RestTemplate disponible en contexto
	 */
	@Bean
	public RestTemplate restTemplate() {
		//Inicializa un RestTemplate por default
		return new RestTemplate();
	}	

}