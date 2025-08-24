package mx.santander.datamagic.admreports.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "AVTR_MX_MAE_ADM_REPOR_DRO_RP")
public class Admreport implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 105656052184755891L;
	
	/**
	 * Identificador de la clase.
	 */
	@Id
	@JsonIgnore
	@Column(name="id_adm_repor_pk",updatable=false,nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admreports_generator")
	@SequenceGenerator(name = "admreports_generator", sequenceName = "avtr_mx_mae_adm_rep_dro_sec_rp", allocationSize = 1)
	private Long id;
	
	/**
	 * Identificador de la estructura asociada al reporte.
	 */
	@Column(name="id_estru_fk")
	private Long idEstructuraData;
	
	/**
	 * Identificador de la periodicidad.
	 */
	@Column(name="id_perio_fk")
	private Integer periodicidadData;
	
	/**
	 * Ruta donde se encuentra el reporte en el repositorio documental.
	 */
	@Column(name="txt_ruta_repor")
	private String rutaReportData;
	
	/**
	 * Nombre del reporte que se almaceno.
	 */
	@Column(name="txt_nom_repor")
	private String nomReportData;
	
	/**
	 * Clave del usuario que guardo el reporte.
	 */
	@Column(name="usr_clave_usuar")
	private String claveUsuarioData;
	
	/**
	 * Indicador del repositorio en donde esta almacenado el reporte.
	 */
	@Column(name="flg_repo")
	private Integer flgRepoData;
	
	/**
	 * Fecha de creacion del reporte.
	 */
	@Basic(optional = false)
	@Column(name = "fch_fecha_carga", insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacionData;
	
	/**
	 * Metadatos.
	 */
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name="txt_meta_data")
	private String metaDataClob;
	
	
	/**
	 * Constructor por default.
	 */
	public Admreport(){
	}

	/**
	 * Constructor que recibe el objeto
	 * @param admreport - El objeto de entidad
	 */
	public Admreport(Admreport admreport){
		this.id = admreport.getId();
	}
	
	/**
	 * Constructor que recibe los valores por separados
	 * @param id - El id de la entidad
	 */
	public Admreport(Long id){
		this.id = id;
	}
	
	/**
	 * Constructor que recibe los campos valores consultados de la BD.
	 * @param id El id de la entidad.
	 * @param idEstructuraData Identificador de la estructura asociada al reporte..
	 * @param periodicidadData Identificador de la periodicidad.
	 * @param rutaReportData Ruta donde se encuentra el reporte en el repositorio documental.
	 * @param nomReportData Nombre del reporte que se almaceno.
	 * @param claveUsuarioData Clave del usuario que guardo el reporte.
	 * @param flgRepoData Indicador del repositorio en donde esta almacenado el reporte.
	 * @param fechaCreacionData Fecha de creacion del reporte.
	 * @param metaDataClob Metadatos.
	 */
	public Admreport(Long id, Long idEstructuraData, Integer periodicidadData, String rutaReportData,
			String nomReportData, String claveUsuarioData, Integer flgRepoData, Date fechaCreacionData,
			String metaDataClob) {
		this.id = id;
		this.idEstructuraData = idEstructuraData;
		this.periodicidadData = periodicidadData;
		this.rutaReportData = rutaReportData;
		this.nomReportData = nomReportData;
		this.claveUsuarioData = claveUsuarioData;
		this.flgRepoData = flgRepoData;
		this.fechaCreacionData = (Date) fechaCreacionData.clone();
		this.metaDataClob = metaDataClob;
	}
	
	/**
	 * Metodo que obtiene el identificador de la clase.
	 * @return Id Identificador de la clase.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo que asigna valor al identificador de la clase.
	 * @param id Identificador de la clase.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Metodo que recupera el valor del indetificador de la estructura asociado.
	 * @return the idEstructuraData
	 */
	public Long getIdEstructuraData() {
		return idEstructuraData;
	}

	/**
	 * Metodo que asigna el valor del indetificador de la estructura asociado.
	 * @param idEstructuraData the idEstructuraData to set
	 */
	public void setIdEstructuraData(Long idEstructuraData) {
		this.idEstructuraData = idEstructuraData;
	}

	/**
	 * @return the periodicidadData
	 */
	public Integer getPeriodicidadData() {
		return periodicidadData;
	}

	/**
	 * @param periodicidadData the periodicidadData to set
	 */
	public void setPeriodicidadData(Integer periodicidadData) {
		this.periodicidadData = periodicidadData;
	}

	/**
	 * @return the fechaCreacionData
	 */
	public Date getFechaCreacionData() {
		return (Date) fechaCreacionData.clone();
	}

	/**
	 * @param fechaCreacionData the fechaCreacionData to set
	 */
	public void setFechaCreacionData(Date fechaCreacionData) {
		this.fechaCreacionData = (Date) fechaCreacionData.clone();
	}

	/**
	 * @return the nomReportData
	 */
	public String getNomReportData() {
		return nomReportData;
	}

	/**
	 * @param nomReportData the nomReportData to set
	 */
	public void setNomReportData(String nomReportData) {
		this.nomReportData = nomReportData;
	}

	/**
	 * @return the rutaReportData
	 */
	public String getRutaReportData() {
		return rutaReportData;
	}

	/**
	 * @param rutaReportData the rutaReportData to set
	 */
	public void setRutaReportData(String rutaReportData) {
		this.rutaReportData = rutaReportData;
	}

	/**
	 * @return the claveUsuarioData
	 */
	public String getClaveUsuarioData() {
		return claveUsuarioData;
	}

	/**
	 * @param claveUsuarioData the claveUsuarioData to set
	 */
	public void setClaveUsuarioData(String claveUsuarioData) {
		this.claveUsuarioData = claveUsuarioData;
	}

	/**
	 * @return the flgRepoData
	 */
	public Integer getFlgRepoData() {
		return flgRepoData;
	}

	/**
	 * @param flgRepoData the flgRepoData to set
	 */
	public void setFlgRepoData(Integer flgRepoData) {
		this.flgRepoData = flgRepoData;
	}

	/**
	 * @return the metaDataClob
	 */
	public String getMetaDataClob() {
		return metaDataClob;
	}

	/**
	 * @param metaDataClob the metaDataClob to set
	 */
	public void setMetaDataClob(String metaDataClob) {
		this.metaDataClob = metaDataClob;
	}

	@Override
	public String toString() {
		return "Admreport [id=" + id + "]";
	}

}
