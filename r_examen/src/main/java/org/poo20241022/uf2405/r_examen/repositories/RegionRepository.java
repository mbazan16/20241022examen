package org.poo20241022.uf2405.r_examen.repositories;


import java.util.Optional;

import org.poo20241022.uf2405.r_examen.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegionRepository extends JpaRepository<Region,Integer> {
	

}
