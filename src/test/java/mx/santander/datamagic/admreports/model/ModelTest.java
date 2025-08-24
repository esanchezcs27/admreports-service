package mx.santander.datamagic.admreports.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ModelTest {
	
	@Test
	void shouldReturn_getModelTest() {
		Admreport admreport = new Admreport(1L);
		admreport = new Admreport(admreport);
		admreport = new Admreport(1L, 1L, 1, "ruta", "nombreReporteData", "claveUsuario", 1, new Date(), "metaDataClob");
		admreport.toString();
		
		Usuarioreporte usuarioreporte = new Usuarioreporte();
		usuarioreporte = new Usuarioreporte(1L, "claveUsuario", "nombre", "test@tets.com");
		
		assertNotNull(admreport.getClaveUsuarioData());
		assertNotNull(usuarioreporte.getUsuarioId());
	}
}
