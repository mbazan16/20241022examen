package org.poo20241022.uf2405.examen.repositories;

import java.util.List;
import java.util.Optional;

import org.poo20241022.uf2405.examen.entities.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DireccionRepository extends JpaRepository<Direccion,Integer> {
	
	@Query("select d.direccion from Departamento d where d.id=:id")
	Optional<Direccion> findByIdDepartamento(@Param("id") Integer idDepartamento);

	
	
	List<Direccion> findAllByPais_Region_Nombre(String string);


	// agregado uno
	
//	@Query("select d.direccion from Departamento d where d.direccion.pais.id = :codigoPais")
//	List<Direccion> findAllByCodigoPais(@Param("codigoPais") String codigoPais);



	

	@Query("SELECT d FROM Direccion d WHERE d.pais.id = :codigoPais")
	List<Direccion> findAllByCodigoPais(@Param("codigoPais") String codigoPais);


	//List<Direccion> findAllByPais_Id(String idPais);


	
	//List<Direccion> findAllByCodigoPais(String string);


	//List<Departamento> findByDireccion_Id(@Param("id")Integer idDireccion);	
}
