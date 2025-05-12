package org.poo20241022.uf2405.examen.repositories;

import java.util.Optional;

import org.poo20241022.uf2405.examen.entities.Departamento;
import org.poo20241022.uf2405.examen.entities.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface DepartamentoRepository  extends JpaRepository<Departamento, Integer> {
	
	
   	



}
