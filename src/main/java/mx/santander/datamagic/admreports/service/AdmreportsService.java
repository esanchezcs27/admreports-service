package mx.santander.datamagic.admreports.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.santander.datamagic.admreports.exception.AdmreportAccessException;
import mx.santander.datamagic.admreports.exception.AdmreportInexistenteException;
import mx.santander.datamagic.admreports.config.AdmreportsConfig;
import mx.santander.datamagic.admreports.dto.model.AdmReport;
import mx.santander.datamagic.admreports.dto.model.UsuarioReporte;
import mx.santander.datamagic.admreports.dto.model.UsuarioTransaccion;
import mx.santander.datamagic.admreports.repository.IAdmreportsRepository;
import mx.santander.datamagic.admreports.repository.IUsuarioreporteRepository;
import mx.santander.datamagic.admreports.util.AdmReportWrapper;
import mx.santander.datamagic.admreports.util.AdmreportsConstantes;
import mx.santander.datamagic.admreports.util.ErrorEnum;
import mx.santander.datamagic.admreports.util.PerfilEnum;
import mx.santander.datamagic.admreports.util.PermisoEnum;
import mx.santander.datamagic.admreports.model.Admreport;
import mx.santander.datamagic.admreports.model.Usuarioreporte;

/**
 * Descripcion: Esta es la clase principal de este microservicio, encargada ser orquestador de la
 * logica de negocio relacionada solamente a admreports y sus datos (persistencia)
 * 
 * En esta clase puede haber invocacion a otros componentes con sufijo *Service,
 * los cuales a su vez pudieran invocar a otros microservicios o APIs, pero sin contener logica de negocio.
 * 
 * @author Santander Mexico
 */
@Service
public class AdmreportsService implements IAdmreportsService {


	/**
	 * La Constante LOGGER. Obtiene el Logger de la clase para que sea mandado a
	 * consola o a cualquier destino que pueda utilizarse para el control de
	 * auditoria y/o manejo de las trazas generadas durante el proceso.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdmreportsService.class);
	
    /**
     * Repositorio para la interaccion con la BD para las entidades Admreports
     * para poder realizar las operaciones CRUD que se implementan en esta clase
     * service.
     */
	@Autowired
	private IAdmreportsRepository admreportsRepository;
	
	/**
	 * Repositorio para la interaccion con la BD para las entidades Usuarioreporte
	 * para poder realizar las operaciones CRUD que se implementan en esta clase
	 * service.
	 */
	@Autowired
	private IUsuarioreporteRepository usrReporteRepository;

	/**
	 * Atributo para asignar la configuracion de la clase y obtener las
	 * variables de entorno del sistema desde el archivo de configuracion
	 * para que dichas variables puedan parametrizarse entre cada ambiente
	 * en donde se despliegue la aplicacion.
	 */
	@Autowired
	private AdmreportsConfig config;


	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.santander.datamagic.admreports.service.IAdmreportsService#
	 * consultarAdmreport(Long)
	 */
	@Override
	public AdmReport consultarAdmreport(Long id) 
								throws AdmreportInexistenteException{


		LOGGER.info("Se ejecuta metodo consultarAdmreport del servicio AdmreportsService");
		
		Optional<Admreport> admReportOpt = admreportsRepository.findById(id);
		var admreport = admReportOpt.orElseThrow(() -> new AdmreportInexistenteException(ErrorEnum.EXC_REPORT_BY_ID_NOT_FOUND));
		
		var reporteView = AdmReportWrapper.transformToAdmReport(admreport);
		
		Optional<Usuarioreporte> usrReport = usrReporteRepository.findByClaveUsr(admreport.getClaveUsuarioData());
		
		if (usrReport.isPresent()) {		
			reporteView.setUsuarioReporte(AdmReportWrapper.transformToUsuarioReporte(usrReport.get()));
		}
									
		return reporteView;
		
	}							


	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.santander.datamagic.admreports.service.IAdmreportsService#
	 * crearAdmreport(mx.santander.datamagic.admreports.model.Admreport)
	 */
	@Override
	public AdmReport crearAdmreport(AdmReport admreport) {

		LOGGER.info("Se ejecuta metodo crearAdmreport del servicio AdmreportsService");
		
		admreport.setFlgRepo(config.getIdRepoLine());
		admreport.setRutaReport("Ruta");
		
		if(isAdmin(admreport.getClaveUsuario()) || 
				(admreportsRepository.findNumStructMatchToUsr(admreport.getClaveUsuario(), admreport.getIdEstructura()) > 0
						&& admreportsRepository.findPermissionByIdMatchToUsr(admreport.getClaveUsuario(),
								PermisoEnum.CREAR_ARCHIVOS.getId()) > 0)) {
			
			var admreportNew = admreportsRepository.save(AdmReportWrapper.transform(admreport));
			
			return AdmReportWrapper.transformToAdmReport(admreportNew);
			
		} else {
			throw new AdmreportAccessException(ErrorEnum.EXC_RESOURCE_FORBIDDEN);
		}
		

	}		

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.santander.datamagic.admreports.service.IAdmreportsService#
	 * eliminarAdmreport(Long, String)
	 */
	@Override
	public void eliminarAdmreport(Long id, String claveUsuario) 
							throws AdmreportInexistenteException{

		LOGGER.info("Se ejecuta metodo eliminarAdmreport del servicio AdmreportsService");
		
		Optional<Admreport> admReportOpt = admreportsRepository.findById(id);
		var admreport = admReportOpt.orElseThrow(() -> new AdmreportInexistenteException(
				ErrorEnum.EXC_DELETE_REPORT_BY_ID_NOT_FOUND));
		
		if (isAdmin(claveUsuario) || admreport.getClaveUsuarioData().equals(claveUsuario)) {
			admreportsRepository.deleteById(id);
		} else {
			throw new AdmreportAccessException(ErrorEnum.EXC_DELETE_RESOURCE_FORBIDDEN);
		}

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.santander.datamagic.admreports.service.IAdmreportsService#
	 * consultarAdmreportPorStruct(Long, String)
	 */
	@Override
	public List<AdmReport> consultarAdmreportPorStruct(Long id, String claveUsuario) throws AdmreportInexistenteException {
		
		List<AdmReport> reportesView = null;
		Optional<List<Admreport>> reportesOpt = Optional.empty();
		//Agregar una validacion de perfil de administrador, para que en ese escenario se haga una consulta sin restriccion
		if (isAdmin(claveUsuario)) {
			reportesOpt = admreportsRepository.findByIdEstructuraData(id);			
		} else {
			reportesOpt = admreportsRepository.findByIdEstructuraData(id, claveUsuario);			
		}
		
		List<Admreport> reportes = reportesOpt.orElseThrow(() -> new AdmreportInexistenteException(
				ErrorEnum.EXC_REPORT_BY_STRUCT_NOT_FOUND));
		
		reportesView = AdmReportWrapper.transform(reportes);
		
		matchUsersToReport(reportesView);
		
		
		return reportesView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.santander.datamagic.admreports.service.IAdmreportsService#
	 * buscarAdmreportPorNomReporte(Long, String)
	 */
	@Override
	public List<AdmReport> buscarAdmreportPorNomReporte(String search, String claveUsuario) throws AdmreportInexistenteException {
		
		List<AdmReport> reportesView = new ArrayList<>();
		Optional<List<Admreport>> reportesOpt =  Optional.empty();
		List<Admreport> reportes = null;
		//Agregar una validacion de perfil de administrador, para que en ese escenario se haga una consulta sin restriccion
		if (isAdmin(claveUsuario)) {
			reportesOpt = admreportsRepository.findByNomReportDataContainingIgnoreCase(search);
		} else {
			reportesOpt = admreportsRepository.findByNomReportDataContainingIgnoreCase(search, claveUsuario);
		}
		
		reportes = reportesOpt.orElseThrow(() -> new AdmreportInexistenteException(
				ErrorEnum.EXC_REPORT_BY_NAME_NOT_FOUND));
		
		reportesView = AdmReportWrapper.transform(reportes);
		matchUsersToReport(reportesView);
		
		
		return reportesView;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.santander.datamagic.admreports.service.IAdmreportsService#
	 * buscarTransaccionActiva(String)
	 */
	@Override
	public UsuarioTransaccion buscarTransaccionActiva(String claveUsuario) throws AdmreportInexistenteException {
		
		UsuarioTransaccion usrTransaccion;
		Optional<Usuarioreporte> usrReport = usrReporteRepository.findByClaveUsuario(claveUsuario);
		
		if (usrReport.isPresent()) {
			usrTransaccion = AdmReportWrapper.transformToUsuarioTransaccion(usrReport.get());
		} else {
			usrTransaccion = new UsuarioTransaccion();
			usrTransaccion.setFlgExisteUsr(AdmreportsConstantes.USR_NO_EXISTE);
		}
		
		return usrTransaccion;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.santander.datamagic.admreports.service.IAdmreportsService#
	 * actualizarTransActiva(String, Integer)
	 */
	@Override
	public void actualizarTransActiva(String claveUsuario, Integer indBloqueo) throws AdmreportInexistenteException {
		
		Optional<Usuarioreporte> usrSesion = usrReporteRepository.findByClaveUsuario(claveUsuario);
		Usuarioreporte usuarioSesion;
		
		if (usrSesion.isPresent()) {
			
			usuarioSesion = usrSesion.get();
			
			if (AdmreportsConstantes.USR_BLOQUEADO.equals(indBloqueo)) {
				
				usuarioSesion.setFlgBloq(AdmreportsConstantes.USR_BLOQUEADO);
				usuarioSesion.setFchFechaBloq(new Date());
				
			} else if (AdmreportsConstantes.USR_DESBLOQUEADO.equals(indBloqueo)) {
				
				usuarioSesion.setFlgBloq(AdmreportsConstantes.USR_DESBLOQUEADO);
				usuarioSesion.setFchFechaDesbl(new Date());
				
			}
			
			usrReporteRepository.save(usuarioSesion);
			
		}	

		
	}
	
	/**
	 * Metodo para consultar y validar si un usuario tiene perfil de administrador
	 * en la aplicacion, dicho metodo recibe como parametro la clave del usuario la cual
	 * es la llave para realizar la busqueda e indentificar al usuario en el sistema.
	 * 
	 * @param claveUsuario Clave del usuario que se quiere validar si su perfil es de administrador
	 * en el modulo de repositorio.
	 * @return boolean Indica si el usuario tiene perfil de administrador. true = es administrador,
	 * false = No es administrador.
	 */
	public boolean isAdmin(String claveUsuario) {
		
		Long numRegistros = admreportsRepository.findByClaveUsuarioAdmin(PerfilEnum.ADMIN_REPOSITORIO.getId(), claveUsuario);
		
		return AdmreportsConstantes.CERO_L.compareTo(numRegistros) < 0;
		
	}
	
	/**
	 * Metodo para indicar la relacion que tienen los usuarios en los reportes
	 * en los que pueda estar asociado de acuerdo a la consulta donde se
	 * obtiene el usuario que genero un reporte.
	 * 
	 * @param reportesView Lista de DTOs que almacenan la informacion del reporte
	 * y del usuario que lo genero.
	 */
	private void matchUsersToReport(List<AdmReport> reportesView) {
		
		List<String> clavesUsr = new ArrayList<>();
		Map<String, String> clavesUsrMap = new HashMap<>();
		
		for (AdmReport admReport : reportesView) {			
			clavesUsrMap.put(admReport.getClaveUsuario(), admReport.getClaveUsuario());			
		}
		
		clavesUsrMap.forEach((k,v) -> clavesUsr.add(v));
		
		List<UsuarioReporte> usrsView = AdmReportWrapper.tranformToListUsuarioReporte(
				usrReporteRepository.findAllByClaveUsrIn(clavesUsr));
		
		for (AdmReport admReport : reportesView) {
			
			for (UsuarioReporte usuarioReporte: usrsView) {
				
				if (admReport.getClaveUsuario().equals(usuarioReporte.getClaveId())) {
					admReport.setUsuarioReporte(usuarioReporte);
				}
				
			}
			
		}
		
	}		

}
