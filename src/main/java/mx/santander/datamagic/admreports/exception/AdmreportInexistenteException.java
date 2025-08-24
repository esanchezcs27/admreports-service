package mx.santander.datamagic.admreports.exception;

import mx.santander.datamagic.admreports.util.ErrorEnum;

/**
 * Descripcion: Clase de excepcion de negocio cuando no existe Admreport.
 * 
 * @author Santander Mexico 
 */
public class AdmreportInexistenteException extends RuntimeException {

	/**
	 * Serial ID de la clase.
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
     * @param errorEnum Enum que almacena el detalle del error que sera presentando
     * al cliente.
     */
    public AdmreportInexistenteException(ErrorEnum errorEnum) {
    	super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
        this.errorEnum = errorEnum;
    }

    /**
     * Constructor
     * @param message Mensaje de error
     * @param code codigo de error
     */
    public AdmreportInexistenteException(String message, String code) {
        super(message);
        this.code = code;
        this.errorEnum = null;
    }
    

    /**
     * Constructor
     * @param message Mensaje de error
     * @param code codigo de error
     * @param causa Causa completa del error
     */
    public AdmreportInexistenteException(String message, String code, Throwable causa) {
        super(message, causa);
        this.code = code;
        this.errorEnum = null;
    }

    /**
     * Constructor que la interfaz del enumerador de error
     * @param code codigo de error
     * @param errorEnum Interfaz de enumerador de error
     */
    public AdmreportInexistenteException(String code, ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = code;
        this.errorEnum = errorEnum;
    }
    
    
    /**
     * Constructor que la interfaz del enumerador de error
     * @param code codigo de error
     * @param errorEnum Interfaz de enumerador de error
     * @param causa Causa completa del error
     */
    public AdmreportInexistenteException(String code, ErrorEnum errorEnum, Throwable causa) {
        super(errorEnum.getMessage(), causa);
        this.code = code;
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