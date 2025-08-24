package mx.santander.datamagic.admreports.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import mx.santander.datamagic.admreports.dto.model.AdmReport;
import mx.santander.datamagic.admreports.dto.model.UsuarioTransaccion;
import mx.santander.datamagic.admreports.exception.AdmreportInexistenteException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.santander.datamagic.admreports.model.Admreport;
import mx.santander.datamagic.admreports.service.IAdmreportsService;

import java.util.List;

import org.owasp.encoder.Encode;


/**
 * Descripcion: Esta clase se encarga de exponer los endpoints de acceso basado principios REST
 * Existen ciertas consultas, bajas, altas y actualizaciones a una coleccion de recursos de admreports.
 * 
 * @author Santander Mexico
 */
@RestController
@RequestMapping("/admreports")
public class AdmreportsController {
	 
	/** La Constante LOGGER. Obtiene el Logger de la clase */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdmreportsController.class);
	
	@Autowired
	private IAdmreportsService admreportsService;
	
    /**
     * Este es un ejemplo de un metodo HTTP GET consultando por id del recurso de tipo Admreport
	 * y en la implementacion de la interfaz de negocio admreportsService 
	 * puede realizar ciertas transformaciones/agregaciones a la consulta para enriquecer la presentacion.
	 * 
	 * Este metodo es idempotente, y sus procesos derivados NUNCA deben modificar el estado de algun recurso en el servidor. 
	 * TODOS los procesos desencadenados deben ser solo de consulta.
	 * 
     * @param id Id de admreport a dar de alta
     * @return Codigo de la operacion y objeto JSON obtenido
     * @throws AdmreportInexistenteException Excepcion de recurso inexistente
     */
	@Operation(description = "Listar los Admreports disponibles", summary = "Lista de Admreports")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
		@GetMapping(value = "/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AdmReport> consultarAdmreportPorId(@PathVariable("id") Long id) 
							throws AdmreportInexistenteException{
								
        LOGGER.info("Entra a controller para consulta de la entidad");
        AdmReport admreport;
        
		admreport = admreportsService.consultarAdmreport(id);
        return new ResponseEntity<>(admreport, HttpStatus.OK);
		
    }
	
    /**
     * Este es un ejemplo de un metodo HTTP GET consultando por id de la estructura para recuperar recursos de tipo Admreport
	 * que tiene asociados y en la implementacion de la interfaz de negocio admreportsService 
	 * puede realizar ciertas transformaciones/agregaciones a la consulta para enriquecer la presentacion.
	 * 
	 * Este metodo es idempotente, y sus procesos derivados NUNCA deben modificar el estado de algun recurso en el servidor. 
	 * TODOS los procesos desencadenados deben ser solo de consulta.
	 * 
     * @param id Identificador de la estructura a la que estan asociados los reportes.
     * @param claveUsuario Valor que se envia en el header corresponde al identificador del usuario.
     * @return List<AdmReport> Listado de reportes obtenidos.
     * @throws AdmreportInexistenteException Excepcion de recurso inexistente
     */
	@Operation(description = "Listar los Reportes asociados a una estructura y a un usuario", summary = "Lista de reportes asociados a una estructura")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
		@GetMapping(value = "/structure/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<AdmReport>> consultarAdmreportPorStruct(@PathVariable("id") Long id, 
    		@RequestHeader("Identificador-Usuario") @Valid @NotBlank String claveUsuario) 
							throws AdmreportInexistenteException{
								
        LOGGER.info("Entra a controller para consulta de la lista de reportes asociados a la estructura");
        List<AdmReport> admreport;
        
		admreport = admreportsService.consultarAdmreportPorStruct(id, claveUsuario);
        return new ResponseEntity<>(admreport, HttpStatus.OK);
		
    }
	
    /**
     * Este es un ejemplo de un metodo HTTP GET consultando por criterio de busqueda de nombre del reporte
     * para recuperar recursos de tipo Admreport que coinciden con la cadena del criterio de busqueda
	 * y en la implementacion de la interfaz de negocio admreportsService 
	 * puede realizar ciertas transformaciones/agregaciones a la consulta para enriquecer la presentacion.
	 * 
	 * Este metodo es idempotente, y sus procesos derivados NUNCA deben modificar el estado de algun recurso en el servidor. 
	 * TODOS los procesos desencadenados deben ser solo de consulta.
	 * 
     * @param search Texto que sera asociado al criterio de busqueda de nombre para el reporte a consultar.
     * @param claveUsuario Valor que se envia en el header corresponde al identificador del usuario.
     * @return List<AdmReport> Listado de reportes obtenidos.
     * @throws AdmreportInexistenteException Excepcion de recurso inexistente
     */
	@Operation(description = "Listar los Reportes asociados a un usuario y a un criterio de busqueda",
			summary = "Lista de reportes asociados a un criterio de busqueda")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
		@GetMapping(value = "/search/{search}",
				produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<AdmReport>> buscarAdmreportPorNomReporte(@PathVariable("search") String search,
    		@RequestHeader("Identificador-Usuario") @Valid @NotBlank String claveUsuario) 
							throws AdmreportInexistenteException{
								
        LOGGER.info("Entra a controller para consulta de la lista de reportes asociados a la estructura");
        List<AdmReport> admreport;
        
		admreport = admreportsService.buscarAdmreportPorNomReporte(search, claveUsuario);
        return new ResponseEntity<>(admreport, HttpStatus.OK);
		
    }

    

    /**
     * Este es un ejemplo de un metodo HTTP POST, el cual en la implementacion de la interfaz de negocio admreportsService 
	 * crea un nuevo recurso bajo la coleccion /admreports y puede realizar cierta logica de negocio adicional
	 * 
 	 * Este metodo es non-safe y NO es idempotente, por lo cual sus procesos derivados SIEMPRE afectaran el estado de un recurso en el servidor.
	 * En conjunto con un mecanismo de control de transaccionalidad en repositorios con estado, 
	 * puede es usado por ejemplo para orquestar transacciones financieras o de otro tipo
	 * 
     * @param admReport AdmReport a dar de alta
     * @param ucBuilder Generador de URIs
     * @return Codigo de la operacion, y header con la URI de la ubicacion de nuevo recurso
     */
	@Operation(description = "Alta de Admreports En el header Location devuelve el recurso que fue registrado", summary = "Alta de Admreports")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
		@PostMapping(value = "",
						consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Long> crearAdmreport(@Valid @RequestBody AdmReport admReport, 
								UriComponentsBuilder ucBuilder) {

    	LOGGER.info("Entra a controller, creacion : {}", Encode.forJava(admReport.toString()));
    	AdmReport admreportGen;
    
    	admreportGen = admreportsService.crearAdmreport(admReport);

		var location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(admreportGen.getIdReporte()).toUri();
		
		return ResponseEntity.created(location).build();
		
    }


    /**
     * Este es un ejemplo de un metodo HTTP DELETE, el cual en la implementacion de la interfaz de negocio admreportsService 
	 * se encarga de eliminar un recurso en particular de la coleccion /admreports
	 * 
	 * Este metodo es idempotente, y sus procesos derivados son idoneos para eliminar algun recurso existente en el servidor
	 * 
     * @param id Id de admreport
     * @param claveUsuario Clave del usuario que intenta borrar el reporte
     * @return Codigo http indicando si la eliminacion fue exitosa
     * @throws AdmreportInexistenteException Excepcion de recurso inexistente
     */
	@Operation(description = "Eliminacion de Admreports", summary = "Eliminacion de Admreports")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Admreport> eliminarAdmreport(@PathVariable("id") Long id,
				@RequestHeader("Identificador-Usuario") @Valid @NotBlank String claveUsuario)
    						throws AdmreportInexistenteException{
					
    	LOGGER.info("Entra a controller, eliminacion de admreport");
        
		admreportsService.eliminarAdmreport(id, claveUsuario);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
    }
	
    /**
     * Este es un ejemplo de un metodo HTTP GET consultando por el id del usuario de tipo Admreport
	 * y en la implementacion de la interfaz de negocio admreportsService 
	 * puede realizar ciertas transformaciones/agregaciones a la consulta para enriquecer la presentacion.
	 * 
	 * Este metodo es idempotente, y sus procesos derivados NUNCA deben modificar el estado de algun recurso en el servidor. 
	 * TODOS los procesos desencadenados deben ser solo de consulta.
	 * 
     * @param claveUsuario Identificador del usuario.
     * @return Codigo de la operacion y objeto JSON obtenido
     * @throws AdmreportInexistenteException Excepcion de recurso inexistente
     */
	@Operation(description = "Regresa el estatus de bloqueo de un usuario al realizar una operacion", summary = "Regresa el estatus de bloqueo de un usuario")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
		@GetMapping(value = "/operacion/{claveUsuario}",
				produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<UsuarioTransaccion> consultarOperacionActiva(@PathVariable("claveUsuario") String claveUsuario) 
							throws AdmreportInexistenteException{
								
        LOGGER.info("Entra a controller para consulta si hay una operacion activa ");
        UsuarioTransaccion usrTransaccion;
        
        usrTransaccion = admreportsService.buscarTransaccionActiva(claveUsuario);
        return new ResponseEntity<>(usrTransaccion, HttpStatus.OK);
		
    }
	
    /**
     * Este es un ejemplo de un metodo HTTP PUT, el cual en la implementacion de la interfaz de negocio para la modificacion
     * de un objeto del tipo usuario para que se encargue de actualizar el estado de un recurso usuario en particular
     * el estatus y fecha de bloqueo.
	 * 
	 * Este metodo es idempotente y sus procesos derivados son idoneos para actualizar el estado de algun recurso EXISTENTE en el servidor
     * 
     * @param claveUsuario Clave del usuario que se va a procesar para el bloqueo o desbloqueo.
     * @param idOperacion Id de la operacion
     * @return Codigo http indicando si la actualizacion fue exitosa
     * @throws AdmreportInexistenteException Excepcion de recurso inexistente
     */
	@Operation(description = "Actualizacion de la fecha y el estatus de una transaccion", summary = "Actualizacion de la fecha y el estatus de una transaccion")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
		@PutMapping(value = "/modificar-operacion/{idOperacion}/{claveUsuario}")
    public ResponseEntity<UsuarioTransaccion> actualizarTransaccion(@PathVariable("claveUsuario") String claveUsuario,
    		@PathVariable("idOperacion") Integer idOperacion) throws AdmreportInexistenteException{
		
    	LOGGER.info("Entra a controller, actualizacion bloqueo ");
    	
    	admreportsService.actualizarTransActiva(claveUsuario, idOperacion);        

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
    }


}