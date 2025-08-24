package mx.santander.datamagic.admreports.util;

/**
 * Descripcion: Esta clase permite la enumeracion de diferentes valores que
 * pueden tomar los perfiles dentro de cada modulo.
 * 
 * The Enum PerfilEnum.
 * 
 * @author Santander Mexico
 */
public enum PerfilEnum {
	
	ADMIN_REPOSITORIO(1L, "Administrador");
	
	/**
	 * Indica el codigo del perfil.
	 * 
	 */
    private final Long id;

    /**
     * Almacena el detalle del perfil.
     * 
     */
	private final String description;
	
	/**
	 * 
	 * @param id
	 * @param description
	 */
	PerfilEnum(final Long id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
