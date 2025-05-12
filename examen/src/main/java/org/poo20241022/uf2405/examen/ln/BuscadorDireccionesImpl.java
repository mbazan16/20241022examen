package org.poo20241022.uf2405.examen.ln;

import java.util.List;
import java.util.Optional;

import org.poo20241022.uf2405.examen.entities.Departamento;
import org.poo20241022.uf2405.examen.entities.Direccion;
import org.poo20241022.uf2405.examen.exceptions.CodeError;
import org.poo20241022.uf2405.examen.exceptions.ServicioException;
import org.poo20241022.uf2405.examen.repositories.DireccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscadorDireccionesImpl implements BuscadorDirecciones{
	
	Logger log = LoggerFactory.getLogger(BuscadorDireccionesImpl.class);
	
	@Autowired
	DireccionRepository repository;
	
	
	@Override
	public List<Direccion> listDirecciones() throws ServicioException{
		log.info("[listDirecciones]");
		
		List<Direccion> direcciones;
		
		try {
			direcciones= repository.findAll();
			
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return direcciones;
		
	}
	
	
	@Override
	public Direccion conseguirDireccion(Integer idDireccion) throws ServicioException{
		log.info("[conseguirDireccion]");
		log.debug("[idDireccion: "+idDireccion+"]");
		
		Direccion direccion;
		
		try {
			Optional<Direccion> direccionOp= repository.findById(idDireccion);
			if(!direccionOp.isPresent()) throw new ServicioException(CodeError.DIRECCION_NOT_FOUND);
			direccion= direccionOp.get(); 
		}catch(ServicioException se) {
			log.error("ServicioException", se);
			throw se;
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return direccion;
		
	}
	
	@Override
	public Direccion grabarDireccion(Direccion Direccion) throws ServicioException{
		log.info("[grabarDireccion]");
		log.debug("[Direccion: "+Direccion.toString()+"]");
		Direccion direccion;
		
		try{
			direccion=repository.save(Direccion);
			
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return direccion;
		
	}
	
	
	@Override
	public void eliminarDireccion(Integer idDireccion) throws ServicioException{
		log.info("[eliminarDireccion]");
		log.debug("[idDireccion: "+idDireccion+"]");
		
			try {
			repository.deleteById(idDireccion);
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		
	}
	
	

	@Override
	public List<Direccion> listDireccionesByNombreRegion(String nombreRegion) throws ServicioException {
		log.info("[listDireccionesByNombreRegion]");
		log.debug("[nombreRegion: "+nombreRegion+"]");
		
			try {
			return repository.findAllByPais_Region_Nombre(nombreRegion);
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
	}

	
	// Codigo Agregado por raam -------------------------------------------------------------------------------
	
	// buscar uno solo 
	public Direccion conseguirDireccionByDepartamentoId(Integer idDepartamento) throws ServicioException {
	    log.info("Buscando dirección para el departamento con ID: {}", idDepartamento);
	   // log.debug("[nombreRegion: "+nombreRegion+"]");
	    try {
	        return repository.findByIdDepartamento(idDepartamento)
	                .orElseThrow(() -> new ServicioException(CodeError.DIRECCION_NOT_FOUND));
	    } catch (ServicioException e) {
	        throw e; 
	    } catch (Exception e) {
	        log.error("Error inesperado", e);
	        throw new ServicioException(CodeError.ERROR_GENERAL, e);
	    }
	}

	// Quiero buscar un listado de direcciones por el codigo de pais 
//	public List<Departamento> listDireccionesByCodigoPais(Object object) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//		
	
	
	
	public List<Direccion> listDireccionesByCodigoPais(String codigoPais) throws ServicioException {
	    log.info("Buscando direcciones por código de país: {}", codigoPais);
	   // log.debug("[nombreRegion: "+nombreRegion+"]");
	    try {
	        return repository.findAllByCodigoPais(codigoPais);
	    } catch (Exception e) {
	        log.error("Error al buscar direcciones por código de país", e);
	        throw new ServicioException(CodeError.ERROR_GENERAL, e);
	    }
	}

	
//	public List<Direccion> listDireccionesByCodigoPais(String codigoPais) throws ServicioException {
//	    log.info("Buscando direcciones por código de país: {}", codigoPais);
//
//	    try {
//	        return repository.findAllByPais_Id(codigoPais);
//	    } catch (Exception e) {
//	        log.error("Error al buscar direcciones por código de país", e);
//	        throw new ServicioException(CodeError.ERROR_GENERAL, e);
//	    }
//	}


	

//	public Direccion conseguirDireccionByDepartamentoId(Integer idDepartamento) {
//	    log.info("Buscando dirección para el departamento con ID: {}", idDepartamento);
//
//	    return repository.findByIdDepartamento(idDepartamento)
//	            .orElseThrow(() -> new ServicioException("No se encontró la dirección para el departamento con ID: " + idDepartamento));
//	}
		
	
//	public Direccion conseguirDireccionByDepartamentoId(Integer idDepartamento) throws ServicioException {
//	    log.info("Buscando dirección para el departamento con ID: {}", idDepartamento);
//
//	    try {
//	        return repository.findByIdDepartamento(idDepartamento)
//	                .orElseThrow(() -> new ServicioException());
//	    } catch (Exception e) {
//	        log.error("Error en conseguirDireccionByDepartamentoId", e);
//	        throw new ServicioException(CodeError.ERROR_GENERAL, e);
//	    }
//	}


		
	


	

	
	
	

}
