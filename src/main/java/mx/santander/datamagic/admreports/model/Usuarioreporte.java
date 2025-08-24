package mx.santander.datamagic.admreports.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Descripcion: Clase para el modelado del objeto de negocio de reportes a tratar
 * por el servicio en la aplicacion.
 * 
 * @author Santander Mexico
 */
@Entity
@Table(name = "AVTR_MX_MAE_ADMIN_USR_RP")
public class Usuarioreporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2590702238664464481L;
	
	/**
	 * Identificador de la clase.
	 */
	@Id
	@Column(name="id_usr_pk",updatable=false,nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_generator")
	@SequenceGenerator(name = "usuario_generator", sequenceName = "avtr_mx_mae_admin_usr_sec_rp", allocationSize = 1)
	private Long usuarioId;
	
	/**
	 * Identificador del usuario.
	 */
	@Column(name="usr_clave_usuar")
	private String claveUsr;
	
	/**
	 * Nombre completo del usuario.
	 */
	@Column(name="usr_nombre")
	private String nombre;
	
	/**
	 * Correo electronico del usuario.
	 */
	@Column(name="usr_correo")
	private String correoElectronico;
	
	/**
	 * Indicador que muestra si esta bloqueado o no el registro.
	 */
	@Column(name="flg_bloq")
	private Integer flgBloq;
	
	/**
	 * Fecha de bloqueo del usuario para realizar transacciones.
	 */
	@Basic(optional = true)
	@Column(name = "fch_fecha_bloq", insertable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fchFechaBloq;
	
	/**
	 * Fecha de desbloqueo del usuario para realizar transacciones.
	 */
	@Basic(optional = true)
	@Column(name = "fch_fecha_desbl", insertable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fchFechaDesbl;
	
	/**
	 * Constructor por default.
	 */
	public Usuarioreporte() {
		
	}
	
	/**
	 * Constructor que recibe los campos valores consultados de la BD. 
	 * @param usuarioId Identificador del usuario.
	 * @param claveUsr Clave del usuario.
	 * @param nombre Nombre completo del usuario.
	 * @param correoElectronico Correo electronico del usuario.
	 */
	public Usuarioreporte(Long usuarioId, String claveUsr, String nombre, String correoElectronico) {
		
		this.usuarioId = usuarioId;
		this.claveUsr = claveUsr;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		
	}

	/**
	 * @return the usuarioId
	 */
	public Long getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the claveUsr
	 */
	public String getClaveUsr() {
		return claveUsr;
	}

	/**
	 * @param claveUsr the claveUsr to set
	 */
	public void setClaveUsr(String claveUsr) {
		this.claveUsr = claveUsr;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * @return the flgBloq
	 */
	public Integer getFlgBloq() {
		return flgBloq;
	}

	/**
	 * @param flgBloq the flgBloq to set
	 */
	public void setFlgBloq(Integer flgBloq) {
		this.flgBloq = flgBloq;
	}

	/**
	 * @return the fchFechaBloq
	 */
	public Date getFchFechaBloq() {
		return obtenerClone(fchFechaBloq);
	}

	/**
	 * @param fchFechaBloq the fchFechaBloq to set
	 */
	public void setFchFechaBloq(Date fchFechaBloq) {
		this.fchFechaBloq = obtenerClone(fchFechaBloq);
	}

	/**
	 * @return the fchFechaDesbl
	 */
	public Date getFchFechaDesbl() {
		return obtenerClone(fchFechaDesbl);
	}

	/**
	 * @param fchFechaDesbl the fchFechaDesbl to set
	 */
	public void setFchFechaDesbl(Date fchFechaDesbl) {
		this.fchFechaDesbl = obtenerClone(fchFechaDesbl);
	}
	
	/**
	 * Metodo para hacer el cast del objeto de fecha y generar una copia
	 * que es la que regresara el metodo.
	 * 
	 * @param fechaProcesar Fecha de la que se desea hacer una copia.
	 * @return Date Nueva fecha generada.
	 */
	private Date obtenerClone(Date fechaProcesar) {
		
		if (fechaProcesar != null) {
			return (Date) fechaProcesar.clone();			
		} else {
			return null;			
		}
		
	}

}
