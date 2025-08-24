package mx.santander.datamagic.admreports.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.santander.datamagic.admreports.model.Usuarioreporte;
import mx.santander.datamagic.admreports.util.AdmreportsConstantes;

/**
 * Descripcion: Interface que extiende las operaciones CRUD del patron repository utilizado por Spring Data.
 * 
 * @author Santander Mexico
 */
@Repository
public interface IUsuarioreporteRepository extends CrudRepository<Usuarioreporte, Long> {
	
	/**
	 * Metodo para consultar la informacion del usuario asociado a un reportes por clave de usuario.
	 * @param claveUsuario Clave del usuario asociado al reporte.
	 * @return Optional<Usuarioreporte> Usuario encontrado
	 */
	@Query(value = AdmreportsConstantes.QRY_CONSULTA_USUARIO_BLOQ_BY_CLAVE,
			nativeQuery = true)
	Optional<Usuarioreporte> findByClaveUsr(@Param("claveUsuario") String claveUsuario);
	
	/**
	 * Metodo para consultar el listado de reportes que fueron creados por los usuarios
	 * proporcionados en el contrato.
	 * 
	 * @param claveUsr Listado de claves de usuario.
	 * @return List<Usuarioreporte> Listado de reportes asociados a las claves de usuario proporcionadas
	 * en el contrato del metodo.
	 */
	List<Usuarioreporte> findAllByClaveUsrIn(Iterable<String> claveUsr);
	
	/**
	 * Metodo para consultar la informacion del usuario asociado a un reportes por clave de usuario,
	 * el cual obtendra el estatus de bloqueo del usuario.
	 * @param claveUsuario Clave del usuario asociado al reporte.
	 * @return Optional<Usuarioreporte> Usuario encontrado
	 */
	@Query(value = AdmreportsConstantes.QRY_CONSULTA_USUARIO_BLOQ_BY_CLAVE,
			nativeQuery = true)
	Optional<Usuarioreporte> findByClaveUsuario(@Param("claveUsuario") String claveUsuario);

}
