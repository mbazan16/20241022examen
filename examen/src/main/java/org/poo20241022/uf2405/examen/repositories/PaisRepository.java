package org.poo20241022.uf2405.examen.repositories;

import java.util.List;

import org.poo20241022.uf2405.examen.entities.Direccion;
import org.poo20241022.uf2405.examen.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PaisRepository extends JpaRepository<Pais,String> {

}
