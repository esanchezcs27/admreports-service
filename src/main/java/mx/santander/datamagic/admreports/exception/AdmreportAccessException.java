package mx.santander.datamagic.admreports.exception;

import mx.santander.datamagic.admreports.util.ErrorEnum;

/**
 * Descripcion: Clase de excepcion de negocio cuando no existen privilegios para
 * realizar una accion sobre un recurso de Admreport.
 * 
 * @author Santander Mexico
 *  
 */
public class AdmreportAccessException extends RuntimeException {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 6604602191663245983L;
	
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
    public AdmreportAccessException(ErrorEnum errorEnum) {
    	super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
        this.errorEnum = errorEnum;
    }

    /**
     * Constructor
     * @param message Mensaje de error
     * @param code Codigo de error
     */
    public AdmreportAccessException(String message, String code) {
        super(message);
        this.code = code;
        this.errorEnum = null;
    }
    

    /**
     * Constructor
     * @param message Mensaje de error
     * @param code Codigo de error
     * @param causa Causa completa del error
     */
    public AdmreportAccessException(String message, String code, Throwable causa) {
        super(message, causa);
        this.code = code;
        this.errorEnum = null;
    }
    


    /**
     * Constructor que la interfaz del enumerador de error
     * @param code Codigo de error
     * @param errorEnum Interfaz de enumerador de error
     */
    public AdmreportAccessException(String code, ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = code;
        this.errorEnum = errorEnum;
    }
    
    
    /**
     * Constructor que la interfaz del enumerador de error
     * @param code Codigo de error
     * @param errorEnum Interfaz de enumerador de error
     * @param causa Causa completa del error
     */
    public AdmreportAccessException(String code, ErrorEnum errorEnum, Throwable causa) {
        super(errorEnum.getMessage(), causa);
        this.code = code;
        this.errorEnum = null;
    }
    
    /**
     * 
     * @return
     */
	public String getCode() {
		return code;
	}


	/**
	 * @return the errorEnum
	 */
	public ErrorEnum getErrorEnum() {
		return errorEnum;
	}

}
