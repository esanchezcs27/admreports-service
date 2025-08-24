package mx.santander.datamagic.admreports.service;

import java.util.List;

import mx.santander.datamagic.admreports.dto.model.AdmReport;
import mx.santander.datamagic.admreports.dto.model.UsuarioTransaccion;
import mx.santander.datamagic.admreports.exception.AdmreportInexistenteException;

/**
 * Descripcion: Interfaz publica para las operaciones de negocio de la entidad Admreport.
 * 
 * @author Santander Mexico
 */
public interface IAdmreportsService {


	/**
	 * Consulta de entidad de negocio
	 * 
	 * @param id El id de la entidad a consultar
	 * @return Objeto de la entidad consultada
	 * @throws AdmreportInexistenteException Excepcion de entidad inexistente
	 */
	AdmReport consultarAdmreport(Long id) throws AdmreportInexistenteException;
	
	/**
	 * Metodo para la consulta de reportes asociados a una estructura.
	 * 
	 * @param id Identificador de la estructura.
	 * @param claveUsuario Clave del usuario asociado a la estructura.
	 * @return List<AdmReport> Listado de reportes encontrados.
	 * @throws AdmreportInexistenteException Excepcion de entidad inexistente
	 */
	List<AdmReport> consultarAdmreportPorStruct(Long id, String claveUsuario) throws AdmreportInexistenteException;	
	
	/**
	 * Metodo para la consulta de reportes por criterio de busqueda del nombre.
	 * 
	 * @param search Criterio de busqueda.
	 * @param claveUsuario Clave del usuario asociado a la estructura.
	 * @return List<AdmReport> Listado de reportes encontrados.
	 * @throws AdmreportInexistenteException Excepcion de entidad inexistente.
	 */
	List<AdmReport> buscarAdmreportPorNomReporte(String search, String claveUsuario) throws AdmreportInexistenteException;	

	/**
	 * Creacion de entidad de negocio
	 * @param admreport El objeto de la entidad a crear
	 * @return Entidad creada
	 */
	AdmReport crearAdmreport(AdmReport admreport);

	/**
	 * Eliminacion de entidad de negocio
	 * 
	 * @param id El id de la entidad a eliminar
	 * @param claveUsuario El identificador del usuario que quiere eliminar el reporte.
	 * @throws AdmreportInexistenteException Excepcion de entidad inexistente
	 */
	void eliminarAdmreport(Long id, String claveUsuario) throws AdmreportInexistenteException;
	
	/**
	 * Metodo para realizar la validacion de si un usuario se encuentra en una transaccion
	 * de guardado de documentos, esto para evitar las operaciones concurrentes con el 
	 * mismo usuario.
	 * 
	 * @param claveUsuario Identificador del usuario
	 * @return UsuarioTransaccion. Regresa la informacion de la transaccion del usuario.
	 * tenga alguna transaccion en proceso.
	 * @throws AdmreportInexistenteException Excepcion de negocio.
	 */
	UsuarioTransaccion buscarTransaccionActiva(String claveUsuario) throws AdmreportInexistenteException;
	
	/**
	 * Actualizacion de una entidad de negocio del usuario para generar o liberar la bandera de
	 * bloqueo de transaccion segun lo definido en el valor de la bandera indBloqueo.
	 * 
	 * @param claveUsuario El identificador del usuario que quiere eliminar el reporte.
	 * @param indBloqueo Bandera para indicar si se va a llevar a cabo un bloqueo=1, desbloqueo=0
	 * @throws AdmreportInexistenteException Excepcion de entidad inexistente
	 */
	void actualizarTransActiva(String claveUsuario, Integer indBloqueo) throws AdmreportInexistenteException;

}
