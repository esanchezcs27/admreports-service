package mx.santander.datamagic.admreports.service.util;

import java.util.Date;
import java.util.Optional;

import mx.santander.datamagic.admreports.dto.model.AdmReport;
import mx.santander.datamagic.admreports.dto.model.UsuarioTransaccion;
import mx.santander.datamagic.admreports.model.Admreport;
import mx.santander.datamagic.admreports.model.Usuarioreporte;
import mx.santander.datamagic.admreports.util.AdmreportsConstantes;

public class AdmreportsUtil {
	
	public static final String CLAVE_USUARIO = "9889aeea-0c2b-4b1d-a01e-de6374061c55";
	public static final Long ID_REPORTE = 1l;
	public static final Long ID_ESTRUCTURA = 1l;
	public static final String METADATOS = "{\"name\":\"1726134892726.pdf\",\"fileSize\":5031151,\"contentType\":\"application/pdf\"}";
	public static final String NOMBRE_REPORTE = "reporte_prueba.pdf";
	public static final String EMAIL = "uramirez@santander.com.mx";
	public static final String NOMBRE_USUARIO = "Alejandro Ramirez";
	public static final Long ID_USUARIO = 1L;
	public static final Integer PERIODICIDAD = 1;
	
	public static Optional<Admreport> getOptAdmreport() {
		
		return Optional.of(getAdmreport());		
	}
	
	public static Optional<Usuarioreporte> getOptUsuarioreporte() {
		
		Usuarioreporte usrReporte = new Usuarioreporte();
		usrReporte.setClaveUsr(CLAVE_USUARIO);
		usrReporte.setCorreoElectronico(EMAIL);
		usrReporte.setNombre(NOMBRE_USUARIO);
		usrReporte.setUsuarioId(ID_USUARIO);
		
		return Optional.of(usrReporte);
		
	}
	
	public static Admreport getAdmreport() {
		
		Admreport admreport = new Admreport();
		admreport.setClaveUsuarioData(CLAVE_USUARIO);
		admreport.setFechaCreacionData(new Date());
		admreport.setFlgRepoData(1);
		admreport.setId(ID_REPORTE);
		admreport.setIdEstructuraData(ID_ESTRUCTURA);
		admreport.setMetaDataClob(METADATOS);
		admreport.setNomReportData(NOMBRE_REPORTE);
		admreport.setPeriodicidadData(PERIODICIDAD);
		admreport.setRutaReportData(null);
		
		return admreport;
		
	}
	
	public static AdmReport getAdmReport() {
		
		AdmReport admReport = new AdmReport();
		admReport.setIdEstructura(ID_ESTRUCTURA);
		admReport.setClaveUsuario(CLAVE_USUARIO);
		admReport.setNomReport(NOMBRE_REPORTE);
		admReport.setPeriodicidad(PERIODICIDAD);
		admReport.setMetaData(METADATOS);
		
		return admReport;
		
	}
	
	public static UsuarioTransaccion getUsuarioTransaccion() {
		
		UsuarioTransaccion usrTransaccion = new UsuarioTransaccion();
		usrTransaccion.setClaveUsuario(AdmreportsUtil.CLAVE_USUARIO);
		usrTransaccion.setFechaBloqueo("");
		usrTransaccion.setFechaDesbloqueo("");
		usrTransaccion.setFlgBloqueo(AdmreportsConstantes.USR_DESBLOQUEADO);
		usrTransaccion.setFlgExisteUsr(1);
		
		return usrTransaccion;
	}

}
