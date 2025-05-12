package org.poo20241022.uf2405.examen.repositories;

import java.util.List;
import java.util.Optional;

import org.poo20241022.uf2405.examen.entities.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

    // Consulta por nombre de región (relación: Direccion -> Pais -> Region)
    @Query("SELECT d FROM Direccion d WHERE d.pais.region.nombre = :nombreRegion")
    List<Direccion> findAllByPais_Region_Nombre(@Param("nombreRegion") String nombreRegion);

    // Método para obtener las direcciones por el código del país (relación Direccion -> Pais)
    @Query("SELECT d FROM Direccion d WHERE d.pais.id = :codigoPais")
    List<Direccion> findAllByCodigoPais(@Param("codigoPais") String codigoPais);

    // Método para obtener la dirección por ID de departamento (relación Direccion -> Departamento)
    @Query("SELECT d.direccion FROM Departamento d WHERE d.id = :idDepartamento")
    Optional<Direccion> findByIdDepartamento(@Param("idDepartamento") Integer idDepartamento);
}
