package mx.santander.datamagic.admreports.dto.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Descripcion: Clase para el modelado del objeto de negocio para el front a tratar por el servicio.
 * 
 * @author Santander Mexico
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "clave", "nombre", "correo" })
public class UsuarioReporte implements Serializable {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 4025306562611172052L;
	
	/**
	 * Identificador del reporte.
	 */
	@JsonProperty("clave")
	private String claveId;
	
	/**
	 * Identificador del reporte.
	 */
	@JsonProperty("nombre")
	private String nombreCompleto;
	
	/**
	 * Identificador del reporte.
	 */
	@JsonProperty("correo")
	private String correo;

	/**
	 * @return the claveId
	 */
	public String getClaveId() {
		return claveId;
	}

	/**
	 * @param claveId the claveId to set
	 */
	public void setClaveId(String claveId) {
		this.claveId = claveId;
	}

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
