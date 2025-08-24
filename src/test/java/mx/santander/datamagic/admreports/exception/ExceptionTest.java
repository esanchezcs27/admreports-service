package mx.santander.datamagic.admreports.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.santander.datamagic.admreports.util.ErrorEnum;


@ExtendWith(MockitoExtension.class)
class ExceptionTest {

	@Test
	void exceptionTest() {
		AdmreportInexistenteException inexistenteEx = new AdmreportInexistenteException(ErrorEnum.EXC_ERROR_PARAMS);
		inexistenteEx = new AdmreportInexistenteException("PERF.003", ErrorEnum.EXC_FORBIDDEN);
		inexistenteEx = new AdmreportInexistenteException("Usuario no encontrado", ErrorEnum.EXC_OPER_CON_ERRORES);
		inexistenteEx = new AdmreportInexistenteException("message", "code", inexistenteEx);
		
		AdmreportServiceException serviceEx = new AdmreportServiceException(ErrorEnum.EXC_INEXISTENTE);
		serviceEx = new AdmreportServiceException(ErrorEnum.EXC_OPER_CON_ERRORES, serviceEx);
		serviceEx = new AdmreportServiceException("code", "message");
		serviceEx = new AdmreportServiceException("message");
		serviceEx = new AdmreportServiceException("message", serviceEx);
		serviceEx.getCode();
		serviceEx.getErrorEnum();
		AdmreportAccessException accessEx = new AdmreportAccessException(ErrorEnum.EXC_OPER_NO_EXITOSA);
		accessEx = new AdmreportAccessException("code", ErrorEnum.EXC_REPORT_BY_ID_NOT_FOUND);
		accessEx = new AdmreportAccessException("message", "code");
		accessEx = new AdmreportAccessException("code", ErrorEnum.EXC_FORBIDDEN, accessEx);
		accessEx = new AdmreportAccessException("message", "code", accessEx);
		
		
		accessEx.getCode();
		accessEx.getErrorEnum();
		
		assertNotNull(accessEx.getCode());
	}
}
