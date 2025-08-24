package mx.santander.datamagic.admreports.exception;

import mx.santander.datamagic.admreports.util.ErrorEnum;

/**
 * Descripcion: Clase de exception de negocio de los metodos de Admreport.
 * 
 * @author Santander Mexico
 */
public class AdmreportServiceException extends RuntimeException {

	/**
	 * Serial ID.
	 */
    private static final long serialVersionUID = 1303454450535514738L;
    
    /**
     * Codigo de error.
     */
    private final String code;
    
    /**
     * Enum que almacena el detalle del error que se mostrara en el cliente.
     */
    private final ErrorEnum errorEnum;

    /**
     * Constructor
     * @param code Codigo de error 
     * @param message Mensaje de error
     */
    public AdmreportServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.errorEnum = null;
    }


    /**
     * Constructor
     * @param message Mensaje de error
     */
    public AdmreportServiceException(String message) {
        super(message);
        this.code = null;
        this.errorEnum = null;
    }
    

    /**
     * Constructor
     * @param message Mensaje de error
     * @param causa Causa completa del error
     */
    public AdmreportServiceException(String message, Throwable causa) {
        super(message, causa);
        this.code = null;
        this.errorEnum = null;
    }
    


    /**
     * Constructor que la interfaz del enumerador de error
     * @param errorEnum Interfaz de enumerador de error
     */
    public AdmreportServiceException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = null;
        this.errorEnum = errorEnum;
    }
    
    
    /**
     * Constructor que la interfaz del enumerador de error
     * @param errorEnum Interfaz de enumerador de error
     * @param causa Causa completa del error
     */
    public AdmreportServiceException(ErrorEnum errorEnum, Throwable causa) {
        super(errorEnum.getMessage(), causa);
        this.code = null;
        this.errorEnum = errorEnum;
    }
    
    /**
     * Metodo que regresa el codigo de error.
     * @return Code codigo de error.
     */
	public String getCode() {
		return code;
	}


	/**
	 * Metodo que regresa el Enum que almacena el detalle del codigo del error.
	 * @return the errorEnum Enum con el detalle del codigo del error.
	 */
	public ErrorEnum getErrorEnum() {
		return errorEnum;
	}
    
}