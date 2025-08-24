package mx.santander.datamagic.admreports.util;

/**
 * Descripcion: Esta clase permite la enumeracion de diferentes valores que
 * pueden tomar los permisos que se pueden asociar en cada modulo.
 * 
 * The Enum PermisoEnum.
 * 
 * @author Santander Mexico
 */
public enum PermisoEnum {
	
	CREAR_ARCHIVOS(1, "Crear archivos");
	
	/**
	 * Indica el codigo del permiso.
	 * 
	 */
    private final int id;

    /**
     * Almacena el detalle del permiso.
     * 
     */
	private final String description;
	
	/**
	 * 
	 * @param id
	 * @param description
	 */
	PermisoEnum(final int id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
