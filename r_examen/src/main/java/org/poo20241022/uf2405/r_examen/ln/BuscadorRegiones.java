package org.poo20241022.uf2405.r_examen.ln;

import java.util.List;

import org.poo20241022.uf2405.r_examen.entities.Region;
import org.poo20241022.uf2405.r_examen.exceptions.ServicioException;



public interface BuscadorRegiones {

	List<Region> listRegiones() throws ServicioException;

	Region conseguirRegion(Integer idRegion) throws ServicioException;

	Region grabarRegion(Region Region) throws ServicioException;

	void eliminarRegion(Integer idRegion) throws ServicioException;
	
	Region conseguirRegionByNombre(String nombre) throws ServicioException;

	Region conseguirRegionByCodigoPais(String codigo) throws ServicioException;

}