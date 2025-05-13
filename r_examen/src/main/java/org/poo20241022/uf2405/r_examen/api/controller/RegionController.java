package org.poo20241022.uf2405.r_examen.api.controller;

import java.util.List;

import org.poo20241022.uf2405.r_examen.entities.Region;
import org.poo20241022.uf2405.r_examen.exceptions.ServicioException;
import org.poo20241022.uf2405.r_examen.ln.BuscadorRegiones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/regiones")
public class RegionController {
	
	@Autowired
	BuscadorRegiones servicio;
	
	
	@GetMapping
	public List<Region> list() throws ServicioException{
		
		return servicio.listRegiones();
		
	}	
	
	@GetMapping(value="/{id}")
	public Region find(@PathVariable Integer id) throws ServicioException {
		
		return servicio.conseguirRegion(id);
		
	}
	
	
	@PostMapping
	public Region create(@RequestBody Region region) throws ServicioException {
		
		
		return servicio.grabarRegion(region);
	}
	
	
	@PutMapping
	public Region save(@RequestBody Region region) throws ServicioException {
		
		return servicio.grabarRegion(region);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>  delete(@PathVariable Integer id) throws ServicioException {
		
		servicio.eliminarRegion(id);
		
		return ResponseEntity.ok("Se ha eliminado");
		
	}
	
	
	@GetMapping("/n/{nombre}")
	public Region listByRegion(@PathVariable("nombre")String nombreRegion) throws ServicioException{
		return servicio.conseguirRegionByNombre(nombreRegion);
		
	}
	
	@GetMapping("/pais/{id}")
	public Region listByPais(@PathVariable("id") String codPais) throws ServicioException{
		return servicio.conseguirRegionByCodigoPais(codPais);
		
	}
	
	
	
	
}
