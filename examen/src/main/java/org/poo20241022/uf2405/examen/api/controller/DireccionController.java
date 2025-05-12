package org.poo20241022.uf2405.examen.api.controller;

import java.util.List;

import org.poo20241022.uf2405.examen.entities.Direccion;
import org.poo20241022.uf2405.examen.exceptions.ServicioException;
import org.poo20241022.uf2405.examen.ln.BuscadorDirecciones;
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
@RequestMapping("/api/direcciones")
public class DireccionController {
	
	@Autowired
	BuscadorDirecciones servicio;
	
	
	@GetMapping
	public List<Direccion> list() throws ServicioException{
		
		return servicio.listDirecciones();
		
	}	
	
	@GetMapping(value="/{id}")
	public Direccion find(@PathVariable Integer id) throws ServicioException {
		
		return servicio.conseguirDireccion(id);
		
	}
	
	
	@PostMapping
	public Direccion create(@RequestBody Direccion direccion) throws ServicioException {
		
		
		return servicio.grabarDireccion(direccion);
	}
	
	
	@PutMapping
	public Direccion save(@RequestBody Direccion direccion) throws ServicioException {
		
		return servicio.grabarDireccion(direccion);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>  delete(@PathVariable Integer id) throws ServicioException {
		
		servicio.eliminarDireccion(id);
		
		return ResponseEntity.ok("Se ha eliminado");
		
	}
	
	@GetMapping("/region/{nombre}")
	public List<Direccion> listByRegion(@PathVariable("nombre")String nombreRegion) throws ServicioException{
		return servicio.listDireccionesByNombreRegion(nombreRegion);
		
	}
	
	@GetMapping("/pais/{id}")
	public List<Direccion> listByDireccion(@PathVariable("id") String codPais) throws ServicioException{
		return servicio.listDireccionesByCodigoPais(codPais);
		
	}
	@GetMapping("/departamento/{id}")
	public Direccion conseguirDireccionByDepartamentoId(@PathVariable("id")Integer departamentoID) throws ServicioException {
		return servicio.conseguirDireccionByDepartamentoId(departamentoID);
	}
	
	
	
}
