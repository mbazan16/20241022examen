package org.poo20241022.uf2405.examen.repositories;

import java.util.List;
import java.util.Optional;

import org.poo20241022.uf2405.examen.entities.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DireccionRepository extends JpaRepository<Direccion,Integer> {
	

	List<Direccion> findAllByPais_Region_Nombre(String idRegion);

	@Query("select d from Direccion d where d.pais.id=:id")
	List<Direccion> findAllByCodigoPais(@Param("id") String codPais);

	@Query("select d.direccion from Departamento d where d.id=:id")
	Optional<Direccion> findByIdDepartamento(@Param("id") Integer idDepartamento);
	
}
