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
@JsonPropertyOrder({ "flgBloqueo", "fechaBloqueo", "fechaDesbloqueo" })
public class UsuarioTransaccion implements Serializable {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -5742119595480326475L;
	
	/**
	 * Clave del usuario.
	 */
	@JsonProperty("claveUsuario")
	private String claveUsuario;
	
	/**
	 * Identificador de bloqueo.
	 */
	@JsonProperty("flgBloqueo")
	private Integer flgBloqueo;
	
	/**
	 * Fecha en que se realizo el bloqueo.
	 */
	@JsonProperty("fechaBloqueo")
	private String fechaBloqueo;
	
	/**
	 * Fecha en que se realizo el desbloqueo.
	 */
	@JsonProperty("fechaDesbloqueo")
	private String fechaDesbloqueo;
	
	/**
	 * Identificador para saber si el usuario que se esta buscando existe.
	 */
	@JsonProperty("flgExisteUsr")
	private Integer flgExisteUsr;

	/**
	 * @return the claveUsuario
	 */
	public String getClaveUsuario() {
		return claveUsuario;
	}

	/**
	 * @param claveUsuario the claveUsuario to set
	 */
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	/**
	 * @return the flgBloqueo
	 */
	public Integer getFlgBloqueo() {
		return flgBloqueo;
	}

	/**
	 * @param flgBloqueo the flgBloqueo to set
	 */
	public void setFlgBloqueo(Integer flgBloqueo) {
		this.flgBloqueo = flgBloqueo;
	}

	/**
	 * @return the fechaBloqueo
	 */
	public String getFechaBloqueo() {
		return fechaBloqueo;
	}

	/**
	 * @param fechaBloqueo the fechaBloqueo to set
	 */
	public void setFechaBloqueo(String fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

	/**
	 * @return the fechaDesbloqueo
	 */
	public String getFechaDesbloqueo() {
		return fechaDesbloqueo;
	}

	/**
	 * @param fechaDesbloqueo the fechaDesbloqueo to set
	 */
	public void setFechaDesbloqueo(String fechaDesbloqueo) {
		this.fechaDesbloqueo = fechaDesbloqueo;
	}

	/**
	 * @return the flgExisteUsr
	 */
	public Integer getFlgExisteUsr() {
		return flgExisteUsr;
	}

	/**
	 * @param flgExisteUsr the flgExisteUsr to set
	 */
	public void setFlgExisteUsr(Integer flgExisteUsr) {
		this.flgExisteUsr = flgExisteUsr;
	}

}
