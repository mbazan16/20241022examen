package org.poo20241022.uf2405.examen.ln;

import java.util.List;

import org.poo20241022.uf2405.examen.entities.Departamento;
import org.poo20241022.uf2405.examen.entities.Direccion;
import org.poo20241022.uf2405.examen.exceptions.ServicioException;



public interface BuscadorDirecciones {

	List<Direccion> listDirecciones() throws ServicioException;

	Direccion conseguirDireccion(Integer idDireccion) throws ServicioException;

	Direccion grabarDireccion(Direccion Direccion) throws ServicioException;

	void eliminarDireccion(Integer idDireccion) throws ServicioException;
	
	List<Direccion> listDireccionesByNombreRegion(String nombreRegion) throws ServicioException;

	List<Direccion> listDireccionesByCodigoPais(String string) throws ServicioException;

	
	
	//List<Departamento> listDepartamentosByIdRegion(Integer idRegion) throws ServicioException;

}