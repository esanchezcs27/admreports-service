package mx.santander.datamagic.admreports.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class AdmreportsConfigTest {
	
	@Test
	void getConfigTest() {
		AdmreportsConfig config = new AdmreportsConfig();
		config.setIdRepoLine(1);
		assertNotNull(config.getIdRepoLine());
	}
}
