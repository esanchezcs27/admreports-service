package mx.santander.datamagic.admreports.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import mx.santander.datamagic.admreports.dto.model.AdmReport;
import mx.santander.datamagic.admreports.dto.model.UsuarioReporte;
import mx.santander.datamagic.admreports.dto.model.UsuarioTransaccion;
import mx.santander.datamagic.admreports.model.Admreport;
import mx.santander.datamagic.admreports.service.IAdmreportsService;
import mx.santander.datamagic.admreports.service.util.AdmreportsUtil;
import mx.santander.datamagic.admreports.util.AdmreportsConstantes;

@ExtendWith(MockitoExtension.class)
class AdmreportsControllerTest {

	@InjectMocks
	private AdmreportsController controller;
	
	@Mock
	IAdmreportsService admreportsService;
	
	@Mock
	private UriComponentsBuilder ucBuilder;
	
	@Test
	void consultarAdmreportPorId() {
		AdmReport admReport = new AdmReport();
		admReport.setClaveUsuario("clave");
		admReport.setFechaCreacion("10/01/24");
		admReport.setFlgRepo(1);
		admReport.setIdEstructura(1L);
		admReport.setIdReporte(1L);
		admReport.setMetaData("metadata");
		admReport.setNomReport("nombre");
		admReport.setPeriodicidad(1);
		admReport.setRutaReport("ruta");
		UsuarioReporte usuario = new UsuarioReporte();
		usuario.setClaveId("clave");
		usuario.setCorreo("test@gmail.com");
		usuario.setNombreCompleto("nombre");
		admReport.setUsuarioReporte(usuario);
		when(admreportsService.consultarAdmreport(any(Long.class))).thenReturn(admReport);
		ResponseEntity<AdmReport> resp = controller.consultarAdmreportPorId(1L);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void consultarAdmreportPorStructTest() {
		List<AdmReport> list = new ArrayList<>();
		AdmReport admReport = new AdmReport();
		admReport.setClaveUsuario("clave");
		admReport.setFechaCreacion("10/01/24");
		admReport.setFlgRepo(1);
		admReport.setIdEstructura(1L);
		admReport.setIdReporte(1L);
		admReport.setMetaData("metadata");
		admReport.setNomReport("nombre");
		admReport.setPeriodicidad(1);
		admReport.setRutaReport("ruta");
		UsuarioReporte usuario = new UsuarioReporte();
		usuario.setClaveId("clave");
		usuario.setCorreo("test@gmail.com");
		usuario.setNombreCompleto("nombre");
		admReport.setUsuarioReporte(usuario);
		list.add(admReport);
		when(admreportsService.consultarAdmreportPorStruct(any(Long.class), any(String.class))).thenReturn(list);
		ResponseEntity<List<AdmReport>> resp = controller.consultarAdmreportPorStruct(1L, "claveUsuario");
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	void buscarAdmreportPorNomReporteTest(){
		List<AdmReport> list = new ArrayList<>();
		AdmReport admReport = new AdmReport();
		admReport.setClaveUsuario("clave");
		admReport.setFechaCreacion("10/01/24");
		admReport.setFlgRepo(1);
		admReport.setIdEstructura(1L);
		admReport.setIdReporte(1L);
		admReport.setMetaData("metadata");
		admReport.setNomReport("nombre");
		admReport.setPeriodicidad(1);
		admReport.setRutaReport("ruta");
		UsuarioReporte usuario = new UsuarioReporte();
		usuario.setClaveId("clave");
		usuario.setCorreo("test@gmail.com");
		usuario.setNombreCompleto("nombre");
		admReport.setUsuarioReporte(usuario);
		list.add(admReport);
		when(admreportsService.buscarAdmreportPorNomReporte(any(String.class), any(String.class))).thenReturn(list);
		ResponseEntity<List<AdmReport>> resp = controller.buscarAdmreportPorNomReporte("search", "claveUsuario");
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void crearAdmreportTest() {
		MockHttpServletRequest req = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(req));
		AdmReport admReport = new AdmReport();
		admReport.setClaveUsuario("clave");
		admReport.setFechaCreacion("10/01/24");
		admReport.setFlgRepo(1);
		admReport.setIdEstructura(1L);
		admReport.setIdReporte(1L);
		admReport.setMetaData("metadata");
		admReport.setNomReport("nombre");
		admReport.setPeriodicidad(1);
		admReport.setRutaReport("ruta");
		UsuarioReporte usuario = new UsuarioReporte();
		usuario.setClaveId("clave");
		usuario.setCorreo("test@gmail.com");
		usuario.setNombreCompleto("nombre");
		admReport.setUsuarioReporte(usuario);
		when(admreportsService.crearAdmreport(any())).thenReturn(admReport);
		ResponseEntity<Long> resp = controller.crearAdmreport(admReport, ucBuilder);
		assertThat(resp.getHeaders().getLocation().getPath()).isEqualTo("/1");
	}
	
	@Test
	void eliminarAdmreportTest() {
		doNothing().when(admreportsService).eliminarAdmreport(any(long.class), any(String.class));
		ResponseEntity<Admreport> resp = controller.eliminarAdmreport(1L, "claveUsuario");
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void consultarOperacionActivaTest() {
		
		when(admreportsService.buscarTransaccionActiva(any())).thenReturn(AdmreportsUtil.getUsuarioTransaccion());
		ResponseEntity<UsuarioTransaccion> resp = controller.consultarOperacionActiva(AdmreportsUtil.CLAVE_USUARIO);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void actualizarTransaccionTest() {
		
		doNothing().when(admreportsService).actualizarTransActiva(AdmreportsUtil.CLAVE_USUARIO, 1);
		ResponseEntity<UsuarioTransaccion> resp = controller.actualizarTransaccion(AdmreportsUtil.CLAVE_USUARIO, 1);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
}
