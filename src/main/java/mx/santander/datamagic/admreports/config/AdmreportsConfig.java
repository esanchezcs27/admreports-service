package mx.santander.datamagic.admreports.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Descripcion: Esta es la clase que permite por medio de sus anotaciones
 * inyectar las configuraciones personalizadas externalizadas
 * (ya sea por medio del archivo bootstrap.yml
 * O bien por el servicio de configuracion referenciado por el mismo).
 * 
 * @author Santander Mexico
 */
@Configuration
@ConfigurationProperties(prefix="admreports-service")
public class AdmreportsConfig {
	
	/**
	 * Identificador para los reportes de la linea.
	 */
    @Value("${configuraciones.repositorios.linea.id}")
    private Integer idRepoLine;

	/**
	 * @return El identificador del reporte de la linea.
	 */
	public Integer getIdRepoLine() {
		return idRepoLine;
	}

	/**
	 * @param idRepoLine El identificador del reporte de la linea.
	 */
	public void setIdRepoLine(Integer idRepoLine) {
		this.idRepoLine = idRepoLine;
	}

}