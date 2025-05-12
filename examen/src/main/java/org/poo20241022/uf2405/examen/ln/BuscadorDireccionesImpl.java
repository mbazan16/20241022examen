package org.poo20241022.uf2405.examen.ln;


import java.util.List;
import java.util.Optional;

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


	@Override
	public List<Direccion> listDireccionesByCodigoPais(String codigoPais) throws ServicioException {
		log.info("[listDireccionesByCodigoPais]");
		log.debug("[codigPais: " + codigoPais + "]");
		List<Direccion> direcciones;
		try {
			direcciones = repository.findAllByCodigoPais(codigoPais); 
	        if (direcciones == null || direcciones.isEmpty()) {
	            throw new ServicioException(CodeError.DIRECCION_NOT_FOUND);
	        }
		} catch (Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL, e);
		}	return direcciones;
		
	}

	@Override
	public Direccion conseguirDireccionByDepartamentoId(Integer Id) throws ServicioException {
		log.info("[listDireccionesByDepartamentoId]");
		log.debug("[deptoId: " + Id + "]");
		Direccion direccion;
		try {
			Optional<Direccion> direccionOp = repository.findByIdDepartamento(Id);
			if (!direccionOp.isPresent()) {
				throw new ServicioException(CodeError.DIRECCION_NOT_FOUND);
			}
			direccion = direccionOp.get();
		} catch (ServicioException se) {
			log.error("ServicioException", se);
			throw se;
		} catch (Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL, e);
		}
		return direccion;

	}

	@Override
	public List<Direccion> listDirecciones() throws ServicioException {
		// TODO Auto-generated method stub
		return null;
	}
}
//	@Override
//	public List<Direccion> listDirecciones() throws ServicioException {
//			log.info("[listDirecciones]");
//			log.debug("[id: " + id + "]");
//			List<Direccion> direcciones;
//			try {
//				direcciones = repository.findAll(); 
//		        if (direcciones == null || direcciones.isEmpty()) {
//		            throw new ServicioException(CodeError.DIRECCION_NOT_FOUND);
//		        }
//			} catch (Exception e) {
//				log.error("Exception", e);
//				throw new ServicioException(CodeError.ERROR_GENERAL, e);
//			}	return direcciones;
//			
//	}
//	}
	
	
	

	

	

