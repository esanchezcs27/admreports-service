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
@JsonPropertyOrder({ "idReporte", "idEstructura", "periodicidad", "rutaReporte", 
	"nombreReporte", "claveUsuario", "flgRepo", "metaData", "fechaCreacion", "usuarioCreacion" })
public class AdmReport implements Serializable {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 693079710185587317L;
	
	/**
	 * Identificador del reporte.
	 */
	@JsonProperty("idReporte")
	private Long idReporte;
	
	/**
	 * Identificador de la estructura a la que pertenece el reporte.
	 */
	@JsonProperty("idEstructura")
	private Long idEstructura;
	
	/**
	 * Identificador de la periodicidad.
	 */
	@JsonProperty("periodicidad")
	private Integer periodicidad;
	
	/**
	 * Ruta en donde se almacena el reporte.
	 */
	@JsonProperty("rutaReporte")
	private String rutaReport;//Evaluar si se quita este atributo al parecer no se va a usar (Para el MS de agregacion)
	
	/**
	 * Nombre del reporte.
	 */
	@JsonProperty("nombreReporte")
	private String nomReport;
	
	/**
	 * Clave del usuario que registro el reporte.
	 */
	@JsonProperty("claveUsuario")
	private String claveUsuario;
	
	/**
	 * Identificador del repositorio en donde se almacena el reporte.
	 */
	@JsonProperty("flgRepo")
	private Integer flgRepo;//Evaluar si se quita este atributo al parecer no se va a usar (Para el MS de agregacion)
	
	/**
	 * Metadatos.
	 */
	@JsonProperty("metadata")
	private String metaData;//Evaluar si se quita este atributo al parecer no se va a usar (Para el MS de agregacion)
	
	/**
	 * Fecha de creacion del reporte
	 */
	@JsonProperty("fechaCreacion")
	private String fechaCreacion;
	
	/**
	 * Datos del usuario que genero el reporte.
	 */
	@JsonProperty("usuarioCreacion")
	private UsuarioReporte usuarioReporte;

	/**
	 * Metodo que regresa el identificador del reporte.
	 * @return the idReporte
	 */
	public Long getIdReporte() {
		return idReporte;
	}

	/**
	 * Metodo que asigna el identificador del reporte.
	 * @param idReporte the idReporte to set
	 */
	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	/**
	 * Metodo que regresa el identificador de la estructura.
	 * @return the idEstructura
	 */
	public Long getIdEstructura() {
		return idEstructura;
	}

	/**
	 * Metodo que asigna el identificador de la estructura.
	 * @param idEstructura the idEstructura to set
	 */
	public void setIdEstructura(Long idEstructura) {
		this.idEstructura = idEstructura;
	}

	/**
	 * Metodo que regresa el identificador de la periodicidad.
	 * @return the periodicidad
	 */
	public Integer getPeriodicidad() {
		return periodicidad;
	}

	/**
	 * Metodo que asigna el identificador de la periodicidad.
	 * @param periodicidad the periodicidad to set
	 */
	public void setPeriodicidad(Integer periodicidad) {
		this.periodicidad = periodicidad;
	}

	/**
	 * Metodo que regresa la ruta en donde esta almacenado el reporte.
	 * @return the rutaReport
	 */
	public String getRutaReport() {
		return rutaReport;
	}

	/**
	 * Metodo que asigna la ruta en donde esta almacenado el reporte.
	 * @param rutaReport the rutaReport to set
	 */
	public void setRutaReport(String rutaReport) {
		this.rutaReport = rutaReport;
	}

	/**
	 * Metodo que regresa el nombre del reporte.
	 * @return the nomReport
	 */
	public String getNomReport() {
		return nomReport;
	}

	/**
	 * Metodo que asigna el nombre del reporte.
	 * @param nomReport the nomReport to set
	 */
	public void setNomReport(String nomReport) {
		this.nomReport = nomReport;
	}

	/**
	 * Metodo que regresa la clave del usuario que dio de alta el reporte.
	 * @return the claveUsuario
	 */
	public String getClaveUsuario() {
		return claveUsuario;
	}

	/**
	 * Metodo que asigna la clave del usuario que dio de alta el reporte.
	 * @param claveUsuario the claveUsuario to set
	 */
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	/**
	 * Metodo que regresa el identificador del repositorio en donde se almacena el reporte.
	 * @return the flgRepo
	 */
	public Integer getFlgRepo() {
		return flgRepo;
	}

	/**
	 * Metodo que asigna el identificador del repositorio en donde se almacena el reporte.
	 * @param flgRepo the flgRepo to set
	 */
	public void setFlgRepo(Integer flgRepo) {
		this.flgRepo = flgRepo;
	}

	/**
	 * Metodo que regresa los metadatos.
	 * @return the metaData
	 */
	public String getMetaData() {
		return metaData;
	}

	/**
	 * Metodo que asigna los metadatos.
	 * @param metaData the metaData to set
	 */
	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	/**
	 * @return the fechaCreacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the usuarioReporte
	 */
	public UsuarioReporte getUsuarioReporte() {
		return usuarioReporte;
	}

	/**
	 * @param usuarioReporte the usuarioReporte to set
	 */
	public void setUsuarioReporte(UsuarioReporte usuarioReporte) {
		this.usuarioReporte = usuarioReporte;
	}

}
