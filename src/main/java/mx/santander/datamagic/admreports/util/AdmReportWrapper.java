package mx.santander.datamagic.admreports.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.santander.datamagic.admreports.dto.model.AdmReport;
import mx.santander.datamagic.admreports.dto.model.UsuarioReporte;
import mx.santander.datamagic.admreports.dto.model.UsuarioTransaccion;
import mx.santander.datamagic.admreports.model.Admreport;
import mx.santander.datamagic.admreports.model.Usuarioreporte;

/**
 * Clase de utileria para la generacion y transformacion de objetos.
 * 
 * @author Santander Mexico
 *
 */
public final class AdmReportWrapper {
	
	/**
	 * Formato de fecha por default que se le asignaran a las fechas.
	 */
	private static final SimpleDateFormat FORMATO_FECHA_DEFAULT = new SimpleDateFormat(AdmreportsConstantes.FORMATO_FECHA);
	
	/**
	 * Constructor por default de la clase.
	 */
	private AdmReportWrapper() {
		throw new IllegalStateException("Utility AdmReportWrapper class");
	}
	
	/**
	 * Metodo para la transformacion de objetos de vista a su respectivo
	 * objeto de modelo para base de datos.
	 * 
	 * @param admReport Objeto de vista que representa los reportes.
	 * @return Admreport Objeto de modelo que representa la informacion de los reportes.
	 */
	public static Admreport transform(AdmReport admReport) {
		
		var admrep = new Admreport();
		
		admrep.setId(admReport.getIdReporte());
		admrep.setIdEstructuraData(admReport.getIdEstructura());
		admrep.setClaveUsuarioData(admReport.getClaveUsuario());
		admrep.setNomReportData(admReport.getNomReport());
		admrep.setPeriodicidadData(admReport.getPeriodicidad());
		admrep.setFechaCreacionData(new Date());
		admrep.setRutaReportData(admReport.getRutaReport());
		admrep.setFlgRepoData(admReport.getFlgRepo());
		admrep.setMetaDataClob(admReport.getMetaData());
		
		return admrep;
		
	}
	
	/**
	 * Metodo para generar un objeto de modelo a partir de una entidad
	 * el objeto generado sera una nueva referencia.
	 * 
	 * @param admreport Objeto de entidad.
	 * @return AdmReport Objeto de modelo.
	 */
	public static AdmReport transformToAdmReport(Admreport admreport) {
		
		var admReport = new AdmReport();
		admReport.setIdReporte(admreport.getId());
		admReport.setIdEstructura(admreport.getIdEstructuraData());
		admReport.setClaveUsuario(admreport.getClaveUsuarioData());
		admReport.setNomReport(admreport.getNomReportData());
		admReport.setPeriodicidad(admreport.getPeriodicidadData());
		admReport.setRutaReport(admreport.getRutaReportData());
		admReport.setFlgRepo(admreport.getFlgRepoData());
		admReport.setMetaData(admreport.getMetaDataClob());
		admReport.setFechaCreacion(FORMATO_FECHA_DEFAULT.format(admreport.getFechaCreacionData()));
		
		return admReport;
		
	}
	
	/**
	 * Metodo para generar una lista de List<AdmReport> apartir de las entidades List<Admreport>
	 * 
	 * @param reportesBD Lista de entidades de reporte.
	 * @return List<AdmReport> Lista de DTOs de reportes.
	 */
	public static List<AdmReport> transform(List<Admreport> reportesBD) {
		
		List<AdmReport> reportes = new ArrayList<>();
		
		for (Admreport reporte : reportesBD) {
			reportes.add(transformToAdmReport(reporte));
		}
		
		return reportes;
		
	}
	
	/**
	 * Metodo que regresa un nuevo objeto del Modelo de AdmReport
	 * el objeto generado sera una nueva referencia.
	 * 
	 * @return AdmReport Objeto del modelo nuevo. 
	 */
	public static AdmReport transform() {
		
		return new AdmReport();
		
	}
	
	/**
	 * Metodo para generar un objeto de modelo a partir de una entidad
	 * el objeto generado sera una nueva referencia.
	 * @param usrReport Objeto de entidad.
	 * @return UsuarioReporte objeto del modelo.
	 */
	public static UsuarioReporte transformToUsuarioReporte(Usuarioreporte usrReport) {
		
		var usuarioReporte = new UsuarioReporte();
		usuarioReporte.setClaveId(usrReport.getClaveUsr());
		usuarioReporte.setCorreo(usrReport.getCorreoElectronico());
		usuarioReporte.setNombreCompleto(usrReport.getNombre());
		
		return usuarioReporte;
		
	}
	
	/**
	 * Metodo para generar un listado de objetos del tipo UsuarioReporte a partir de una lista de
	 * entidades consultadas de la base de datos.
	 * 
	 * @param usrsBD Lista de entidades de las cuales se obtendra la informacion para generar los DTOs
	 * @return List<UsuarioReporte> Listado de objetos que almacenan la informacion de los reportes.
	 */
	public static List<UsuarioReporte> tranformToListUsuarioReporte(List<Usuarioreporte> usrsBD) {
		
		List<UsuarioReporte> usuarios = new ArrayList<>();
		
		for (Usuarioreporte usrBD: usrsBD) {
			usuarios.add(transformToUsuarioReporte(usrBD));
		}
		
		return usuarios;
		
	}
	
	/**
	 * Metodo para generar un objeto de modelo del tipo UsuarioTransaccion
	 * a partir de una entidad proporcionada
	 * el objeto generado sera una nueva referencia.
	 * @param usrReport Objeto de entidad.
	 * @return UsuarioTransaccion objeto del modelo.
	 */
	public static UsuarioTransaccion transformToUsuarioTransaccion(Usuarioreporte usrReport) {
		
		var usrTransaccion = new UsuarioTransaccion();
		usrTransaccion.setClaveUsuario(usrReport.getClaveUsr());
		usrTransaccion.setFechaBloqueo(transFecha(usrReport.getFchFechaBloq()));		
		usrTransaccion.setFechaDesbloqueo(transFecha(usrReport.getFchFechaDesbl()));
		
		if (usrReport.getFlgBloq() != null) {
			usrTransaccion.setFlgBloqueo(usrReport.getFlgBloq());
		} else {
			usrTransaccion.setFlgBloqueo(AdmreportsConstantes.USR_DESBLOQUEADO);
		}
		
		usrTransaccion.setFlgExisteUsr(AdmreportsConstantes.USR_EXISTE);
		
		return usrTransaccion;
		
	}
	
	/**
	 * Metodo para generar el formato de una fecha en cadena dado una fecha en objeto Date.
	 * 
	 * @param fecha Fecha a formatear.
	 * @return String Equivalente en cadena de la representacion del Date.
	 */
	private static String transFecha(Date fecha) {
		
		if (fecha != null) {
			return FORMATO_FECHA_DEFAULT.format(fecha);
		} else {
			return "0";
		}
		
	}

}
