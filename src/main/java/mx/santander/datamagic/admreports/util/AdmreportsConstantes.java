package mx.santander.datamagic.admreports.util;

/**
 * Descripcion: Clase de constantes para el microservicio.
 * 
 * @author Santander Mexico
 */
public final class AdmreportsConstantes {
	
	/**
	 * Mensaje de error para las excepciones o logs.
	 */
	public static final String ERROR = "Error";

	/**
	 * Mensaje de Warning para las excepciones o logs.
	 */
	public static final String WARNING = "Warning";
	
	/**
	 * Constante para el valor de cero.
	 */
	public static final Long CERO_L = 0L;
	
	/**
	 * Bandera que indica que el usuario si existe en la BD.
	 */
	public static final Integer USR_EXISTE = 1;
	
	/**
	 * Bandera que indica que el usuario no existe en la BD.
	 */
	public static final Integer USR_NO_EXISTE = 0;
	
	/**
	 * Bandera que indica que el usuario esta bloqueado.
	 */
	public static final Integer USR_BLOQUEADO = 1;
	
	/**
	 * Bandera que indica que el usuario no esta bloqueado.
	 */
	public static final Integer USR_DESBLOQUEADO = 0;
	
	/**
	 * Formato de las fechas.
	 */
	public static final String FORMATO_FECHA = "dd-MM-yyyy HH:mm:ss";
	
	//Agregar nuevas constantes aqui
	/**
	 * Qry para la consulta de reportes asociados a un usuario.
	 */
	public static final String QRY_CONSULTA_REPORTES_BY_USUARIO_AND_ID_ESTRUCT = """
			SELECT
			repor.id_adm_repor_pk, repor.id_estru_fk, repor.id_perio_fk, repor.txt_ruta_repor,
			repor.txt_nom_repor, repor.usr_clave_usuar, repor.flg_repo, repor.fch_fecha_carga,
			repor.txt_meta_data
			FROM
			avtr_mx_mae_adm_repor_dro_rp repor JOIN avtr_mx_mae_estru_rep_rp estru
			ON repor.id_estru_fk = estru.id_estru_pk
			JOIN AVTR_MX_REL_AUT_USR_ESTRU_RP aut ON aut.id_estru_fk = estru.id_estru_pk
			JOIN AVTR_MX_MAE_ADMIN_USR_RP usr ON usr.id_usr_pk = aut.id_usr_fk
			WHERE usr.usr_clave_usuar = :claveUsuario
			""";
	
	/**
	 * Qry para la consulta de reportes sin considerar el usuario asociado, solo la estructura.
	 */
	public static final String QRY_CONSULTA_REPORTES_BY_ID_ESTRUCT_ADM = """
			SELECT
			repor.id_adm_repor_pk, repor.id_estru_fk, repor.id_perio_fk, repor.txt_ruta_repor,
			repor.txt_nom_repor, repor.usr_clave_usuar, repor.flg_repo, repor.fch_fecha_carga,
			repor.txt_meta_data
			FROM
			avtr_mx_mae_adm_repor_dro_rp repor JOIN avtr_mx_mae_estru_rep_rp estru
			ON repor.id_estru_fk = estru.id_estru_pk
			WHERE estru.id_estru_pk = :idEstructura order by repor.fch_fecha_carga desc
			""";
	
	/**
	 * Qry para la consulta de reportes, esta consulta es solo para perfil de adminsitrador, dado que no
	 * presenta restriccion del usuario.
	 */
	public static final String QRY_CONSULTA_REPORTES_BY_ID_ESTRUCT = """
			SELECT
			repor.id_adm_repor_pk, repor.id_estru_fk, repor.id_perio_fk, repor.txt_ruta_repor,
			repor.txt_nom_repor, repor.usr_clave_usuar, repor.flg_repo, repor.fch_fecha_carga,
			repor.txt_meta_data
			FROM
			avtr_mx_mae_adm_repor_dro_rp repor
			WHERE repor.txt_nom_repor like %:nombreReporte% collate binary_ci
			""";
	
	/**
	 * Segmento de qry con la condicion de identificador de estructura.
	 */
	public static final String AND_ESTRUCTURA = "AND estru.id_estru_pk = :idEstructura order by repor.fch_fecha_carga desc";
	
	/**
	 * Segmento de qry con la condicion de subcadena de busqueda por nombre de reporte.
	 */
	public static final String AND_NOM_REPORTE_LIKE = "AND repor.txt_nom_repor like %:nombreReporte% collate binary_ci";
	
	/**
	 * Qry para la consulta del usuario asociado a una clave.
	 */
	public static final String QRY_CONSULTA_USUARIO_BY_CLAVE = """
			SELECT usr.id_usr_pk, usr.usr_clave_usuar, usr.usr_nombre, usr.usr_correo
			FROM AVTR_MX_MAE_ADMIN_USR_RP usr
			WHERE usr.usr_clave_usuar = :claveUsuario			
			""";
	
	/**
	 * Qry para la consulta de la informacion de bloqueo del usuario asociado a una clave.
	 */
	public static final String QRY_CONSULTA_USUARIO_BLOQ_BY_CLAVE = """
			SELECT usr.id_usr_pk, usr.usr_clave_usuar, usr.flg_bloq, usr.fch_fecha_bloq, usr.fch_fecha_desbl,
			usr.usr_correo, usr.usr_nombre
			FROM AVTR_MX_MAE_ADMIN_USR_RP usr
			WHERE usr.usr_clave_usuar = :claveUsuario			
			""";
	
	/**
	 * Qry para la consulta de la lista de usuarios asociados a las claves proporcionadas.
	 */
	public static final String QRY_CONSULTA_LIST_USUARIO_BY_CLAVE = """
			SELECT usr.id_usr_pk, usr.usr_clave_usuar, usr.usr_nombre, usr.usr_correo
			FROM AVTR_MX_MAE_ADMIN_USR_RP usr
			WHERE usr.usr_clave_usuar IN (:clavesUsuarios)			
			""";
	
	/**
	 * Qry para obtener el numero de usuarios con una clave dada y perfil asociado.
	 */
	public static final String QRY_CONSULTA_ADMIN = """
			select count(*) from AVTR_MX_MAE_ADMIN_USR_RP admUsr
			JOIN AVTR_MX_REL_ADMIN_PERFI_USR admPerUsr ON admUsr.ID_USR_PK = admPerUsr.ID_USR_FK
			JOIN AVTR_MX_ADMIN_PERFI admPer ON admPerUsr.ID_PERFI_FK = admPer.ID_PERFI_PK
			AND admPerUsr.ID_MODUL_FK = admPer.ID_MODUL_FK
			WHERE admPer.ID_PERFI_PK = :idPerfil AND admUsr.usr_clave_usuar = :claveUsuario
			""";
	
	/**
	 * Qry para validar si un usuario tiene relacionada una estructura.
	 */
	public static final String QRY_VALIDA_STRUCT_USR = """
			select count(*) as asignado from AVTR_MX_MAE_ADMIN_USR_RP admUsr
			JOIN AVTR_MX_REL_AUT_USR_ESTRU_RP relUsrStru ON admUsr.ID_USR_PK = relUsrStru.ID_USR_FK
			WHERE admUsr.usr_clave_usuar = :claveUsuario and relUsrStru.ID_ESTRU_FK = :idEstructura
			""";
	
	/**
	 * Qry para consultar si un usuario tiene asociado un permiso y en caso de que si con
	 * que valor 1=Permiso otorgado, 0=Permiso no otorgado.
	 */
	public static final String QRY_VALIDA_PERMISO = """
			select CASE WHEN perUsr.flg_aut is null THEN permPerf.flg_aut ELSE perUsr.flg_aut END
			AS flgPermiso
			FROM AVTR_MX_MAE_ADMIN_USR_RP admUsr 
			JOIN AVTR_MX_REL_ADMIN_PERFI_USR relAdmPerUs ON admUsr.ID_USR_PK = relAdmPerUs.ID_USR_FK 
			JOIN AVTR_MX_ADMIN_PERFI admPerf ON admPerf.id_perfi_pk = relAdmPerUs.id_perfi_fk
			AND admPerf.id_modul_fk = relAdmPerUs.id_modul_fk
			JOIN AVTR_MX_REL_PERMI_PERFI_RP permPerf ON  permPerf.ID_PERFI_FK = admPerf.id_perfi_pk
			AND permPerf.ID_MODUL_FK = admPerf.id_modul_fk
			LEFT JOIN AVTR_MX_REL_PERMI_USR_RP perUsr ON perUsr.id_usr_fk = relAdmPerUs.ID_USR_FK
			AND perUsr.id_admin_permi_fk = permPerf.id_admin_permi_fk AND perUsr.id_perfi_fk = permPerf.ID_PERFI_FK
			where admUsr.usr_clave_usuar = :claveUsuario AND permPerf.ID_ADMIN_PERMI_FK = :idPermiso
			""";
	

	private AdmreportsConstantes() {} // previene instanciacion
	
}