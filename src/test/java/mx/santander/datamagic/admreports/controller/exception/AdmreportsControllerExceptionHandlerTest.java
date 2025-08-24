package mx.santander.datamagic.admreports.controller.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import mx.santander.datamagic.admreports.exception.AdmreportAccessException;
import mx.santander.datamagic.admreports.exception.AdmreportInexistenteException;
import mx.santander.datamagic.admreports.exception.AdmreportServiceException;
import mx.santander.datamagic.admreports.util.ErrorEnum;

@ExtendWith(MockitoExtension.class)
class AdmreportsControllerExceptionHandlerTest {
	
	@Mock
	MethodArgumentNotValidException argumentEx;
	
	@Mock
	MethodArgumentTypeMismatchException mismatchEx;

	@Test
	void getExceptionTest() {
		AdmreportsControllerExceptionHandler exceptionHandler = new AdmreportsControllerExceptionHandler();
		AdmreportAccessException accesEx = new AdmreportAccessException(ErrorEnum.EXC_DELETE_RESOURCE_FORBIDDEN);
		exceptionHandler.handleAdminprofileAccessException(accesEx);
		AdmreportInexistenteException inexistenteEx = new AdmreportInexistenteException(ErrorEnum.EXC_DUPLICADO);
		exceptionHandler.handleAdmreportInexistenteException(inexistenteEx);
		AdmreportServiceException serviceEx = new AdmreportServiceException(ErrorEnum.EXC_ERROR_PARAMS);
		exceptionHandler.handleAdmreportServiceException(serviceEx);
		Exception exception = new Exception();
		exceptionHandler.handleGenericException(exception);
		DuplicateKeyException duplicateEx = new DuplicateKeyException("message");
		exceptionHandler.handleGenericExceptionDuplicte(duplicateEx);
		exceptionHandler.handleValidationExceptionA(argumentEx);
		NumberFormatException numberEx = new NumberFormatException();
		exceptionHandler.handleValidationExceptionB(numberEx);
		exceptionHandler.handleValidationExceptionC(mismatchEx);
		MissingServletRequestParameterException reqParamterEx = new MissingServletRequestParameterException("name", "type");
		exceptionHandler.handleValidationExceptionD(reqParamterEx);
		
		SQLException sqlEx = new SQLException(ErrorEnum.EXC_ERR_BASE_DATOS.getCode());
		exceptionHandler.handleSQLException(sqlEx);
		
		
		assertNotNull(duplicateEx.getMessage());


	}
}
