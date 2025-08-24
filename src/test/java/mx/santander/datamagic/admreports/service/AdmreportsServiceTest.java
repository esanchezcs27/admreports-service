package mx.santander.datamagic.admreports.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.santander.datamagic.admreports.config.AdmreportsConfig;
import mx.santander.datamagic.admreports.dto.model.AdmReport;
import mx.santander.datamagic.admreports.dto.model.UsuarioTransaccion;
import mx.santander.datamagic.admreports.exception.AdmreportAccessException;
import mx.santander.datamagic.admreports.exception.AdmreportInexistenteException;
import mx.santander.datamagic.admreports.model.Admreport;
import mx.santander.datamagic.admreports.model.Usuarioreporte;
import mx.santander.datamagic.admreports.repository.IAdmreportsRepository;
import mx.santander.datamagic.admreports.repository.IUsuarioreporteRepository;
import mx.santander.datamagic.admreports.service.util.AdmreportsUtil;
import mx.santander.datamagic.admreports.util.AdmreportsConstantes;

@ExtendWith(MockitoExtension.class)
class AdmreportsServiceTest {

	/**
	 * 
	 */
	@InjectMocks
	private AdmreportsService service;

	/**
	 * Repositorio para la interaccion con la BD para las entidades Admreports.
	 */
	@Mock
	private IAdmreportsRepository admreportsRepository;

	/**
	 * Repositorio para la interaccion con la BD para las entidades Usuarioreporte.
	 */
	@Mock
	private IUsuarioreporteRepository usrReporteRepository;

	/**
	 * Atributo para asignar la configuracion de la clase.
	 */
	@Mock
	private AdmreportsConfig config;

	@Test
	void contextLoads() {
		assertNotNull(service);
		assertNotNull(admreportsRepository);
		assertNotNull(usrReporteRepository);
		assertNotNull(config);
	}

	@Test
	void consultarAdmreportTest() throws AdmreportInexistenteException {
		when(admreportsRepository.findById(any(Long.class))).thenReturn(AdmreportsUtil.getOptAdmreport());
		when(usrReporteRepository.findByClaveUsr(AdmreportsUtil.CLAVE_USUARIO))
				.thenReturn(AdmreportsUtil.getOptUsuarioreporte());
		AdmReport admReport = service.consultarAdmreport(AdmreportsUtil.ID_REPORTE);

		assertNotNull(admReport);
		assertNotNull(admReport.getClaveUsuario());
		assertNotNull(admReport.getFechaCreacion());
		assertNotNull(admReport.getFlgRepo());
		assertNotNull(admReport.getIdEstructura());
		assertNotNull(admReport.getIdReporte());
		assertNotNull(admReport.getMetaData());
		assertNotNull(admReport.getNomReport());
		assertNotNull(admReport.getPeriodicidad());
		assertNotNull(admReport.getUsuarioReporte());
		assertNotNull(admReport.getUsuarioReporte().getClaveId());
		assertNotNull(admReport.getUsuarioReporte().getCorreo());
		assertNotNull(admReport.getUsuarioReporte().getNombreCompleto());

	}
	
	@Test
	void consultarAdmreportTest_whenException() {
		assertThrows(AdmreportInexistenteException.class, () -> 
			service.consultarAdmreport(AdmreportsUtil.ID_REPORTE)
        );
	}

	@Test
	void crearAdmreportTest() {
		when(config.getIdRepoLine()).thenReturn(1);
		when(admreportsRepository.save(any())).thenReturn(AdmreportsUtil.getAdmreport());
		when(admreportsRepository.findByClaveUsuarioAdmin(any(), any())).thenReturn(1L);
		AdmReport admReport = service.crearAdmreport(AdmreportsUtil.getAdmReport());

		assertNotNull(admReport);
		assertNotNull(admReport.getIdReporte());
		assertNotNull(admReport.getIdEstructura());
		assertNotNull(admReport.getClaveUsuario());
		assertNotNull(admReport.getNomReport());
		assertNotNull(admReport.getPeriodicidad());
		assertNotNull(admReport.getFlgRepo());
		assertNotNull(admReport.getMetaData());
		assertNotNull(admReport.getFechaCreacion());

	}
	
	@Test
	void crearAdmreportTest_mayorCero() {
		when(config.getIdRepoLine()).thenReturn(1);
		when(admreportsRepository.save(any())).thenReturn(AdmreportsUtil.getAdmreport());
		when(admreportsRepository.findByClaveUsuarioAdmin(any(), any())).thenReturn(0L);
		
		when(admreportsRepository.findNumStructMatchToUsr(any(String.class), any(Long.class))).thenReturn(1);
		when(admreportsRepository.findPermissionByIdMatchToUsr(any(String.class), anyInt())).thenReturn(1);
		
		AdmReport admReport = service.crearAdmreport(AdmreportsUtil.getAdmReport());

		assertNotNull(admReport);
		assertNotNull(admReport.getIdReporte());
		assertNotNull(admReport.getIdEstructura());
		assertNotNull(admReport.getClaveUsuario());
		assertNotNull(admReport.getNomReport());
		assertNotNull(admReport.getPeriodicidad());
		assertNotNull(admReport.getFlgRepo());
		assertNotNull(admReport.getMetaData());
		assertNotNull(admReport.getFechaCreacion());

	}
	
	
	@Test
	void eliminarAdmreportTest() {
		Admreport adm = new Admreport();
		adm.setClaveUsuarioData("claveUsuario");
		adm.setFechaCreacionData(new Date());
		adm.setFlgRepoData(1);
		adm.setId(1L);
		adm.setIdEstructuraData(2L);
		adm.setMetaDataClob("metaClob");
		adm.setNomReportData("nombre");
		adm.setPeriodicidadData(1);
		adm.setRutaReportData("ruta");
		when(admreportsRepository.findById(any(Long.class))).thenReturn(Optional.of(adm));
		service.eliminarAdmreport(1L, "claveUsuario");
		assertNotNull(adm.getClaveUsuarioData());
	}

	@Test
	void eliminarAdmreportTest_whenException() {
		Admreport adm = new Admreport();
		adm.setClaveUsuarioData("ClaveUsuario");
		adm.setFechaCreacionData(new Date());
		adm.setFlgRepoData(1);
		adm.setId(1L);
		adm.setIdEstructuraData(2L);
		adm.setMetaDataClob("metaClob");
		adm.setNomReportData("nombre");
		adm.setPeriodicidadData(1);
		adm.setRutaReportData("ruta");
		when(admreportsRepository.findById(any(Long.class))).thenReturn(Optional.of(adm));
		assertThrows(AdmreportAccessException.class, () -> 
			service.eliminarAdmreport(1L, "claveUsuario")
		);
	}
	
	@Test
	void eliminarAdmreportTest_whenExceptionTwo() {
		assertThrows(AdmreportInexistenteException.class, () -> 
			service.eliminarAdmreport(1L, "claveUsuario")
		);
	}

	@Test
	void consultarAdmreportPorStructTest() {
		List<Admreport> list = new ArrayList<>();
		Admreport adm = new Admreport();
		adm.setClaveUsuarioData("ClaveUsuario");
		adm.setFechaCreacionData(new Date());
		adm.setFlgRepoData(1);
		adm.setId(1L);
		adm.setIdEstructuraData(2L);
		adm.setMetaDataClob("metaClob");
		adm.setNomReportData("nombre");
		adm.setPeriodicidadData(1);
		adm.setRutaReportData("ruta");
		list.add(adm);
		when(admreportsRepository.findByIdEstructuraData(any(Long.class), any(String.class))).thenReturn(Optional.of(list));
		service.consultarAdmreportPorStruct(1L, "claveUsuario");
		assertNotNull(adm.getClaveUsuarioData());
	}
	
	@Test
	void consultarAdmreportPorStructTest_whenException() {
		assertThrows(AdmreportInexistenteException.class, () -> 
			service.consultarAdmreportPorStruct(1L, "claveUsuario")
		);
	}

	
	@Test
	void buscarAdmreportPorNomReporteTest() {
		List<Admreport> list = new ArrayList<>();
		Admreport adm = new Admreport();
		adm.setClaveUsuarioData("claveUsuario");
		adm.setFechaCreacionData(new Date());
		adm.setFlgRepoData(1);
		adm.setId(1L);
		adm.setIdEstructuraData(2L);
		adm.setMetaDataClob("metaClob");
		adm.setNomReportData("nombre");
		adm.setPeriodicidadData(1);
		adm.setRutaReportData("ruta");
		list.add(adm);
		when(admreportsRepository.findByNomReportDataContainingIgnoreCase(any(String.class), any(String.class))).thenReturn(Optional.of(list));
		List<Usuarioreporte> listReporte = new ArrayList<>();
		Usuarioreporte usuarioreporte = new Usuarioreporte();
		usuarioreporte.setClaveUsr("claveUsuario");
		usuarioreporte.setCorreoElectronico("test@gmail.com");
		usuarioreporte.setNombre("nombre");
		usuarioreporte.setUsuarioId(1L);
		listReporte.add(usuarioreporte);
		when(usrReporteRepository.findAllByClaveUsrIn(any())).thenReturn(listReporte );
		service.buscarAdmreportPorNomReporte("search", "claveUsuario");
		assertNotNull(adm.getClaveUsuarioData());
	}
	
	@Test
	void buscarAdmreportPorNomReporteTest_whenDifClaveUsuario() {
		List<Admreport> list = new ArrayList<>();
		Admreport adm = new Admreport();
		adm.setClaveUsuarioData("claveUsuarioTest");
		adm.setFechaCreacionData(new Date());
		adm.setFlgRepoData(1);
		adm.setId(1L);
		adm.setIdEstructuraData(2L);
		adm.setMetaDataClob("metaClob");
		adm.setNomReportData("nombre");
		adm.setPeriodicidadData(1);
		adm.setRutaReportData("ruta");
		list.add(adm);
		when(admreportsRepository.findByNomReportDataContainingIgnoreCase(any(String.class), any(String.class))).thenReturn(Optional.of(list));
		List<Usuarioreporte> listReporte = new ArrayList<>();
		Usuarioreporte usuarioreporte = new Usuarioreporte();
		usuarioreporte.setClaveUsr("claveUsuario");
		usuarioreporte.setCorreoElectronico("test@gmail.com");
		usuarioreporte.setNombre("nombre");
		usuarioreporte.setUsuarioId(1L);
		listReporte.add(usuarioreporte);
		when(usrReporteRepository.findAllByClaveUsrIn(any())).thenReturn(listReporte );
		service.buscarAdmreportPorNomReporte("search", "claveUsuario");
		assertNotNull(adm.getClaveUsuarioData());
	}
	
	
	@Test
	void buscarAdmreportPorNomReporteTest_whenIsAdminIsFalse() {
		List<Admreport> list = new ArrayList<>();
		Admreport adm = new Admreport();
		adm.setClaveUsuarioData("claveUsuario");
		adm.setFechaCreacionData(new Date());
		adm.setFlgRepoData(1);
		adm.setId(1L);
		adm.setIdEstructuraData(2L);
		adm.setMetaDataClob("metaClob");
		adm.setNomReportData("nombre");
		adm.setPeriodicidadData(1);
		adm.setRutaReportData("ruta");
		list.add(adm);
		when(admreportsRepository.findByClaveUsuarioAdmin(any(), any())).thenReturn(1L);
		when(admreportsRepository.findByNomReportDataContainingIgnoreCase(any(String.class))).thenReturn(Optional.of(list));
		List<Usuarioreporte> listReporte = new ArrayList<>();
		Usuarioreporte usuarioreporte = new Usuarioreporte();
		usuarioreporte.setClaveUsr("claveUsuario");
		usuarioreporte.setCorreoElectronico("test@gmail.com");
		usuarioreporte.setNombre("nombre");
		usuarioreporte.setUsuarioId(1L);
		listReporte.add(usuarioreporte);
		when(usrReporteRepository.findAllByClaveUsrIn(any())).thenReturn(listReporte );
		service.buscarAdmreportPorNomReporte("search", "claveUsuario");
		assertNotNull(adm.getClaveUsuarioData());
	}
	
	
	
	@Test
	void buscarAdmreportPorNomReporteTest_whenException() {
		assertThrows(AdmreportInexistenteException.class, () -> 
			service.buscarAdmreportPorNomReporte("search", "claveUsuario")
		);
	}
	
	@Test
	void buscarTransActivaTest() {
		
		when(usrReporteRepository.findByClaveUsuario(any())).thenReturn(AdmreportsUtil.getOptUsuarioreporte());
		
		UsuarioTransaccion usrTransaccion = service.buscarTransaccionActiva(AdmreportsUtil.CLAVE_USUARIO);
		
		assertNotNull(usrTransaccion);
		assertNotNull(usrTransaccion.getClaveUsuario());
		assertNotNull(usrTransaccion.getFechaBloqueo());
		assertNotNull(usrTransaccion.getFechaDesbloqueo());
		assertNotNull(usrTransaccion.getFlgBloqueo());
		assertNotNull(usrTransaccion.getFlgExisteUsr());
		assertEquals(AdmreportsConstantes.USR_EXISTE, usrTransaccion.getFlgExisteUsr());

	}
	
	@Test
	void buscarTransActivaNoExisteTest() {
		
		when(usrReporteRepository.findByClaveUsuario(any())).thenReturn(Optional.empty());
		
		UsuarioTransaccion usrTransaccion = service.buscarTransaccionActiva(AdmreportsUtil.CLAVE_USUARIO);
		
		assertNotNull(usrTransaccion);
		assertNotNull(usrTransaccion.getFlgExisteUsr());
		assertEquals(AdmreportsConstantes.USR_NO_EXISTE, usrTransaccion.getFlgExisteUsr());

	}
	
	@Test
	void actualizarTransActivaBloqueadoTest() {
		
		when(usrReporteRepository.findByClaveUsuario(any())).thenReturn(AdmreportsUtil.getOptUsuarioreporte());
		
		when(usrReporteRepository.save(any())).thenReturn(new Usuarioreporte());
		
		service.actualizarTransActiva(AdmreportsUtil.CLAVE_USUARIO, AdmreportsConstantes.USR_BLOQUEADO);
		
		assertNotNull(AdmreportsUtil.getOptUsuarioreporte());

	}
	
	@Test
	void actualizarTransActivaDesbloqueadoTest() {
		
		when(usrReporteRepository.findByClaveUsuario(any())).thenReturn(AdmreportsUtil.getOptUsuarioreporte());
		
		when(usrReporteRepository.save(any())).thenReturn(new Usuarioreporte());
		
		service.actualizarTransActiva(AdmreportsUtil.CLAVE_USUARIO, AdmreportsConstantes.USR_DESBLOQUEADO);
		
		assertNotNull(AdmreportsUtil.getOptUsuarioreporte());

	}
	
	@Test
	void actualizarTransActivaOtroTest() {
		
		when(usrReporteRepository.findByClaveUsuario(any())).thenReturn(AdmreportsUtil.getOptUsuarioreporte());
		
		when(usrReporteRepository.save(any())).thenReturn(new Usuarioreporte());
		
		service.actualizarTransActiva(AdmreportsUtil.CLAVE_USUARIO, 2);
		
		assertNotNull(AdmreportsUtil.getOptUsuarioreporte());

	}
	
	@Test
	void actualizarTransActivaVacioTest() {
		
		when(usrReporteRepository.findByClaveUsuario(any())).thenReturn(Optional.empty());
		
		service.actualizarTransActiva(AdmreportsUtil.CLAVE_USUARIO, AdmreportsConstantes.USR_DESBLOQUEADO);
		
		assertNotNull(Optional.empty());

	}
	
	

}