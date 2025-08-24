package mx.santander.datamagic.admreports.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DuplicateKeyException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import mx.santander.datamagic.admreports.exception.AdmreportAccessException;
import mx.santander.datamagic.admreports.exception.AdmreportInexistenteException;
import mx.santander.datamagic.admreports.exception.AdmreportServiceException;
import mx.santander.datamagic.admreports.controller.exception.model.DefaultError;
import mx.santander.datamagic.admreports.controller.exception.model.DefaultErrorList;
import mx.santander.datamagic.admreports.util.ErrorEnum;

import java.sql.SQLException;

import org.owasp.encoder.Encode;

/**
 * Descripcion: Esta clase se encarga de servir como apoyo al controller, manejando de manera desacoplada
 * las excepciones esperadas en la aplicacion, y manejando el catalogo de errores con ayuda de un enumerador personalizado.
 * Tambien tiene un manejo de errores genericos.
 * 
 * @author Santander Mexico
 */
@RestControllerAdvice
public class AdmreportsControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdmreportsControllerExceptionHandler.class);
    
    /**
     * Manejo de excepciones de validacion de argumentos de entrada
     * @param pe Excepcion de tipo MethodArgumentNotValidException
     * @return La entidad de respuesta que maneja el error como objeto
     */
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<DefaultErrorList> handleValidationExceptionA(MethodArgumentNotValidException pe) {
    	LOGGER.warn("Argmentos invalidos", pe);
    	return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ErrorEnum.EXC_ERROR_PARAMS, 
				pe.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
	}

	/**
     * Manejo de excepciones de validacion de formatos de numeros de entrada
     * @param pe Excepcion de tipo NumberFormatException
     * @return La entidad de respuesta que maneja el error como objeto
     */
	@ExceptionHandler(value = {NumberFormatException.class})
	public ResponseEntity<DefaultErrorList> handleValidationExceptionB(NumberFormatException pe) {
    	LOGGER.warn("Excepcion de formatos de numeros de entrada", pe);
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ErrorEnum.EXC_ERROR_PARAMS, pe.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
	}

	/**
     * Manejo de excepciones de validacion de tipo de datos de entrada
     * @param pe Excepcion de tipo MethodArgumentTypeMismatchException
     * @return La entidad de respuesta que maneja el error como objeto
     */
	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	public ResponseEntity<DefaultErrorList> handleValidationExceptionC(MethodArgumentTypeMismatchException pe) {
    	LOGGER.warn("Excepcion de tipo de datos de entrada", pe);
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ErrorEnum.EXC_ERROR_PARAMS, pe.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
	}


	/**
     * Manejo de excepciones de validacion de cantidad de parametros de entrada enviados
     * @param pe Excepcion de tipo MissingServletRequestParameterException
     * @return La entidad de respuesta que maneja el error como objeto
     */
	@ExceptionHandler(value = {MissingServletRequestParameterException.class})
	public ResponseEntity<DefaultErrorList> handleValidationExceptionD(MissingServletRequestParameterException pe) {
    	LOGGER.warn("Excepcion de cantidad de parametros de entrada enviados", pe);
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ErrorEnum.EXC_ERROR_PARAMS, pe.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Manejo de excepcion de llave duplicada
	 * @param ex Excepcion de tipo DuplicateKeyException
	 * @return La entidad de respuesta que maneja el error como objeto
	 */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<DefaultErrorList> handleGenericExceptionDuplicte(DuplicateKeyException ex) {
		LOGGER.warn("Entidad duplicada", ex);
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ErrorEnum.EXC_DUPLICADO)), HttpStatus.BAD_REQUEST);
    }


	/**
     * Manejo de excepcion de registro inexistente
     * @param ex Excepcion de registro inexsistente AdmreportInexistenteException
     * @return La entidad de respuesta que maneja el error como objeto
     */
    @ExceptionHandler(AdmreportInexistenteException.class)
    public ResponseEntity<DefaultErrorList> handleAdmreportInexistenteException(AdmreportInexistenteException ex) {
    	LOGGER.warn("Entidad inexistente {}", Encode.forJava(ex.getMessage()));
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ex.getErrorEnum())), HttpStatus.NOT_FOUND);
    }
    
	/**
     * Manejo de excepcion de registro sin privilegios de acceso
     * @param ex Excepcion de registro inexsistente AdminprofileAccessException
     * @return La entidad de respuesta que maneja el error como objeto
     */
    @ExceptionHandler(AdmreportAccessException.class)
    public ResponseEntity<DefaultErrorList> handleAdminprofileAccessException(AdmreportAccessException ex) {
    	LOGGER.warn("No se tiene permisos para acceder al recurso {}", Encode.forJava(ex.getMessage()));
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ex.getErrorEnum())), HttpStatus.FORBIDDEN);
    }

    /**
     * Manejo de excepcion de servicio no exitosa
     * @param ex Excepcion de servicio no exitosa
     * @return La entidad de respuesta que maneja el error como objeto
     */
    @ExceptionHandler(AdmreportServiceException.class)
    public ResponseEntity<DefaultErrorList> handleAdmreportServiceException(AdmreportServiceException ex) {
    	LOGGER.error("Error en la ejecucion del servicio Admreport", ex);
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ex.getErrorEnum())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Manejo de excepciones cuando ocurre un problema en la capa de base de datos.
     * @param ex Excepcion de base de datos.
     * @return La entidad de respuesta que maneja el error como objeto
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<DefaultErrorList> handleSQLException(SQLException ex) {
    	LOGGER.error("Error en la ejecucion del servicio Admreport en el accedo a la BD", ex);
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ErrorEnum.EXC_ERR_BASE_DATOS)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
	/**
	 * Manejo de excepcion generica
	 * @param ex Excepcion generica de tipo Exception
	 * @return La entidad de respuesta que maneja el error como objeto
	 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultErrorList> handleGenericException(Exception ex) {
		LOGGER.error("Error en la ejecucion", ex);
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(ErrorEnum.EXC_GENERICO)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	
}