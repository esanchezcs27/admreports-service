package mx.santander.datamagic.admreports.util;

/**
 * Descripcion: The Enum ErrorEnum.
 * 
 * Esta clase permite la enumeracion de diferentes mensajes de excepcion
 * utilizados en los cuerpos de respuesta HTTP arrojados por el servicio
 * 
 * Es posible agregar nuevos mensajes personalizados
 * para permitir que el servicio sea mas explicito,
 * recordando siempre que es importante evitar arrojar informacion sensible.
 * 
 * @author Santander Mexico
 */
public enum ErrorEnum {

	EXC_GENERICO("REPO.001", "Error general", "Ocurrio un error en el servidor", AdmreportsConstantes.ERROR, ""),
	
	EXC_INEXISTENTE("EXC.101", "Admreport inexistente", "Admreport no existe, intente con otro valor", AdmreportsConstantes.WARNING, ""),
	
	EXC_OPER_NO_EXITOSA("EXC.102", "Operacion no exitosa", "Operacion no exitosa", AdmreportsConstantes.ERROR, ""),

	EXC_OPER_CON_ERRORES("EXC.103", "Operacion con errores", "Operacion con errores", AdmreportsConstantes.ERROR, ""),
	
	EXC_REPORT_BY_ID_NOT_FOUND("REPO.002", "Reporte no encontrado asociado al identificador proporcionado", "No existe un reporte asociado al identificador proporcionado", AdmreportsConstantes.WARNING, ""),
	
	EXC_FORBIDDEN("REPO.003", "No se cuenta con permisos", "No se tiene permisos para realizar la accion solicitada", AdmreportsConstantes.WARNING, ""),
	
	EXC_RESOURCE_FORBIDDEN("REPO.004", "No se cuenta con permisos de guardado", "El usuario proporcionado no tiene permisos para guardar en el recurso solicitado", AdmreportsConstantes.WARNING, ""),
	
	EXC_DELETE_RESOURCE_FORBIDDEN("REPO.005", "No se cuenta con permisos para borrar el reporte", "El usuario proporcionado no tiene privilegios para borrar el reporte", AdmreportsConstantes.WARNING, ""),
	
	EXC_REPORT_BY_STRUCT_NOT_FOUND("REPO.006", "Reporte no encontrado en la estructura", "No existen reportes asociados a la estructura proporcionada", AdmreportsConstantes.WARNING, ""),
	
	EXC_REPORT_BY_NAME_NOT_FOUND("REPO.007", "Reporte no encontrado con el criterio de busqueda", "No se encontraron reportes asociados al nombre en el criterio de busqueda proporcionado", AdmreportsConstantes.WARNING, ""),
	
	EXC_ERR_BASE_DATOS("REPO.008", "Error en base de datos", "Ocurrio un error en la comunicacion con la base de datos",
			AdmreportsConstantes.ERROR, ""),
	
	EXC_DELETE_REPORT_BY_ID_NOT_FOUND("REPO.009", "Reporte para borrar no encontrado", "No se pude borrar el reporte, no existe un reporte asociado al identificador proporcionado",
			AdmreportsConstantes.WARNING, ""),
	
	EXC_ERROR_PARAMS("REPO.010", "Parametros invalidos", "Parametros invalidos de consumo, revisar la peticion", AdmreportsConstantes.WARNING, ""),
	
	EXC_DUPLICADO("REPO.011", "Registro para guardar duplicado", "El registro que se intenta guardar ya existe, no puede ser sobrescrito", AdmreportsConstantes.WARNING, "");
	
	/**
	 * Indica el codigo de error.
	 * 
	 */
    private final String code;
    
    /**
     * Almacena una breve descripcion del error.
     * 
     */
    private final String message;

    /**
     * Almacena el detalle del error.
     * 
     */
	private final String description;

	/**
	 * Nivel de error generado.
	 * 
	 */
    private final String level;

    /**
     * Algun dato adicional para complementar la descripcion del error.
     * 
     */
    private final String moreInfo;

    
    ErrorEnum(final String code, final String message, 
    		final String description, final String level, final String moreInfo ) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.level = level;
        this.moreInfo = moreInfo;
    }

    /**
     * Regresa el indicador del codigo de error.
     * @return code.
     */
	public String getCode() {
		return code;
	}

	/**
	 * Regresa una breve descripcion del error.
	 * @return message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Regresa el detalle del error.
	 * @return description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Regresa el nivel de error generado.
	 * @return level.
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * Regresa algun dato adicional para complementar la descripcion del error.
	 * @return moreInfo.
	 */
	public String getMoreInfo() {
		return moreInfo;
	}



}
