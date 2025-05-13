package org.poo20241022.uf2405.r_examen.ln;

import java.util.List;
import java.util.Optional;

import org.poo20241022.uf2405.r_examen.entities.Region;
import org.poo20241022.uf2405.r_examen.exceptions.CodeError;
import org.poo20241022.uf2405.r_examen.exceptions.ServicioException;
import org.poo20241022.uf2405.r_examen.repositories.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscadorRegionesImpl implements BuscadorRegiones{
	
	Logger log = LoggerFactory.getLogger(BuscadorRegionesImpl.class);
	
	@Autowired
	RegionRepository repository;
	
	
	@Override
	public List<Region> listRegiones() throws ServicioException{
		log.info("[listRegiones]");
		
		List<Region> regiones;
		
		try {
			regiones= repository.findAll();
			
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return regiones;
		
	}
	
	
	@Override
	public Region conseguirRegion(Integer idRegion) throws ServicioException{
		log.info("[conseguirRegion]");
		log.debug("[idRegion: "+idRegion+"]");
		
		Region region;
		
		try {
			Optional<Region> regionOp= repository.findById(idRegion);
			if(!regionOp.isPresent()) throw new ServicioException(CodeError.REGION_NOT_FOUND);
			region= regionOp.get(); 
		}catch(ServicioException se) {
			log.error("ServicioException", se);
			throw se;
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return region;
		
	}
	
	@Override
	public Region grabarRegion(Region Region) throws ServicioException{
		log.info("[grabarRegion]");
		log.debug("[Region: "+Region.toString()+"]");
		Region region;
		
		try{
			region=repository.save(Region);
			
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return region;
		
	}
	
	
	@Override
	public void eliminarRegion(Integer idRegion) throws ServicioException{
		log.info("[eliminarRegion]");
		log.debug("[idRegion: "+idRegion+"]");
		
			try {
			repository.deleteById(idRegion);
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		
	}

	@Override
	public Region conseguirRegionByNombre(String nombre) throws ServicioException {
	    log.info("[conseguirRegionByNombre]");
	    log.debug("[nombre: " + nombre + "]");

	    Region region;

	    try {
	       
	        Optional<Region> regionOp = repository.findByNombre(nombre);
	        if (!regionOp.isPresent()) {
	            throw new ServicioException(CodeError.REGION_NOT_FOUND);
	        }
	        region = regionOp.get();
	    } catch (ServicioException se) {
	        log.error("ServicioException", se);
	        throw se;
	    } catch (Exception e) {
	        log.error("Exception", e);
	        throw new ServicioException(CodeError.ERROR_GENERAL, e);
	    }
	    return region;
	}



		
	@Override
	public Region conseguirRegionByCodigoPais(String id) throws ServicioException {
	    log.info("[conseguirRegionByCodigoPais]");
	    log.debug("[codigoPais: " + id + "]");

	    Region region;

	    try {
	      
	        Optional<Region> regionOp = repository.findByCodigoPais(id.toString());

	      
	        if (!regionOp.isPresent()) {
	            throw new ServicioException(CodeError.REGION_NOT_FOUND);
	        }

	  	        region = regionOp.get();

	    } catch (ServicioException se) {
	   
	        log.error("ServicioException", se);
	        throw se;
	    } catch (Exception e) {
	       
	        log.error("Exception", e);
	        throw new ServicioException(CodeError.ERROR_GENERAL, e);
	    }

	    return region;
	}

	
	
	}

	
	
	


