package org.poo20241022.uf2405.r_examen.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.poo20241022.uf2405.r_examen.entities.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RegionRepositoryTest {

	Logger log = LoggerFactory.getLogger(RegionRepositoryTest.class);

	@Autowired
	RegionRepository repositorio;

	@Test
	@DisplayName("findByNombreRegionSuccess")
	void findByNombreRegionSuccess() {
		log.info("[findByNombreRegionSuccess]");

		Optional<Region> region = repositorio.findByNombre("Europe");
		

		assertEquals(true, region.isPresent());
		log.debug("Region:"+region);
		assertEquals(1, region.get().getId());

	}

	@Test
	@DisplayName("findByNombreRegionFail")
	void findByNombreRegionFail() {
		log.info("[findByNombreRegionFail]");

		Optional<Region> region = repositorio.findByNombre(null);		

		assertEquals(false, region.isPresent());
		log.debug("Region:"+region);

	}
	
	@Test
	@DisplayName("findByCodigoPaisSuccess")
	void findByCodigoPaisSuccess() {
		log.info("[findByCodigoPaisSuccess]");

		Optional<Region> region = repositorio.findByCodigoPais("IT");
		

		assertEquals(true, region.isPresent());
		log.debug("Region:"+region);
		assertEquals(1, region.get().getId());

	}

	@Test
	@DisplayName("findByCodigoPaisFail")
	void findByCodigoPaisFail() {
		log.info("[findByCodigoPaisFail]");

		Optional<Region> region = repositorio.findByCodigoPais(null);		

		assertEquals(false, region.isPresent());
		log.debug("Region:"+region);


	}

	

	
}
