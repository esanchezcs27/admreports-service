package mx.santander.datamagic.admreports.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.santander.datamagic.admreports.model.Admreport;
import mx.santander.datamagic.admreports.util.AdmreportsConstantes;

/**
 * Descripcion: Interface que extiende las operaciones CRUD del patron repository utilizado por Spring Data
 * para acceder a las entidades Admreport.
 * 
 * @author Santander Mexico
 */
@Repository
public interface IAdmreportsRepository extends CrudRepository<Admreport, Long> {
	
	/**
	 * Metodo para consultar reportes por id y clave de usuario.
	 * @param id Identificador del reporte.
	 * @param claveUsuario Clave del usuario asociado al reporte.
	 * @return Optional<Admreport> Reporte encontrado
	 */
	Optional<Admreport> findByIdAndClaveUsuarioData(Long id, String claveUsuario);
	
	/**
	 * Metodo para consultar los reportes que estan asociados a una estructura para cierto usuario.
	 * @param idEstructura Identificador de la estructura a la que pertenecen los reportes. 
	 * @param claveUsuario Usuario asociado a la estructura para acceder a los reportes.
	 * @return List<Admreport> Listado de reportes encontrados.
	 */
	@Query(value = AdmreportsConstantes.QRY_CONSULTA_REPORTES_BY_USUARIO_AND_ID_ESTRUCT+AdmreportsConstantes.AND_ESTRUCTURA,
			nativeQuery = true)
	 Optional<List<Admreport>> findByIdEstructuraData(@Param("idEstructura") Long idEstructura,
			 @Param("claveUsuario") String claveUsuario);
	
	/**
	 * Metodo para consultar los reportes que estan asociados a una estructura para un Administrador.
	 * @param idEstructura Identificador de la estructura a la que pertenecen los reportes. 
	 * @return List<Admreport> Listado de reportes encontrados.
	 */
	@Query(value = AdmreportsConstantes.QRY_CONSULTA_REPORTES_BY_ID_ESTRUCT_ADM,
			nativeQuery = true)
	 Optional<List<Admreport>> findByIdEstructuraData(@Param("idEstructura") Long idEstructura);
	 
	 /**
	  * Metodo para consultar un reporte por criterio de nombre.
	  * @param nomReporte Nombre del reporte a buscar.
	  * @param claveUsuario Usuario asociado a la estructura para acceder a los reportes.
	  * @return List<Admreport> Listado de reportes encontrados.
	  */
	@Query(value = AdmreportsConstantes.QRY_CONSULTA_REPORTES_BY_USUARIO_AND_ID_ESTRUCT+AdmreportsConstantes.AND_NOM_REPORTE_LIKE,
			nativeQuery = true)
	 Optional<List<Admreport>> findByNomReportDataContainingIgnoreCase(@Param("nombreReporte") String nomReporte,
			 @Param("claveUsuario") String claveUsuario);
	
	 /**
	  * Metodo para consultar un reporte por criterio de nombre.
	  * @param nomReporte Nombre del reporte a buscar.
	  * @return List<Admreport> Listado de reportes encontrados.
	  */
	@Query(value = AdmreportsConstantes.QRY_CONSULTA_REPORTES_BY_ID_ESTRUCT,
			nativeQuery = true)
	 Optional<List<Admreport>> findByNomReportDataContainingIgnoreCase(@Param("nombreReporte") String nomReporte);
	
	/**
	 * Metodo que consulta si un usuario tiene asociado el perfil dado en el criterio de busqueda.
	 * 
	 * @param idPerfil Identificador del perfil.
	 * @param claveUsuario Clave del usuario.
	 * @return Long Indicador si el usuario tiene asociado el perfil. 1=Lo tiene asociado.
	 */
	@Query(value = AdmreportsConstantes.QRY_CONSULTA_ADMIN, nativeQuery = true)
	Long findByClaveUsuarioAdmin(Long idPerfil, String claveUsuario);
	
	/**
	 * Metodo que consulta si un usuario tiene asociada una estructura.
	 * 
	 * @param claveUsuario Clave del usuario.
	 * @param idEstructura Identificador de la estructura.
	 * @return int Indicador si el usuario tiene asociada una estructura. Mayor a cero = si esta asociada.
	 */
	@Query(value = AdmreportsConstantes.QRY_VALIDA_STRUCT_USR, nativeQuery = true)
	int findNumStructMatchToUsr(String claveUsuario, Long idEstructura);
	
	/**
	 * Metodo que consulta si un usuario tiene asociada una estructura.
	 * 
	 * @param claveUsuario Clave del usuario.
	 * @param idPermiso Identificador del permiso.
	 * @return int Indicador si el usuario tiene asociada un permiso. Mayor a cero = si esta asociada.
	 */
	@Query(value = AdmreportsConstantes.QRY_VALIDA_PERMISO, nativeQuery = true)
	int findPermissionByIdMatchToUsr(String claveUsuario, int idPermiso);

}
