package org.poo20241022.uf2405.examen.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.poo20241022.uf2405.examen.entities.Direccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class DireccionRepositoryTest {

	Logger log = LoggerFactory.getLogger(DireccionRepositoryTest.class);

	@Autowired
	DireccionRepository repositorio;

	@Test
	@DisplayName("findByIdDepartamentoSuccess")
	void findByIdDepartamentoSuccess() {
		log.info("[findByIdDepartamentoSuccess]");

		Optional<Direccion> direccion = repositorio.findByIdDepartamento(70);
		

		assertEquals(true, direccion.isPresent());
		log.debug("Direccion:"+direccion);
		assertEquals(1700, direccion.get().getId());

	}

	@Test
	@DisplayName("findByIdDepartamentoFail")
	void findByIdDepartamentoFail() {
		log.info("[findByIdDepartamentoFail]");

		Optional<Direccion> direccion = repositorio.findByIdDepartamento(null);		

		assertEquals(false, direccion.isPresent());
		log.debug("direccion:"+direccion);

	}
	
	@Test
	@DisplayName("findAllByNombreRegionSuccess")
	void findAllByNombreRegionSuccess() {
		log.info("[findAllByNombreRegionSuccess]");

		List<Direccion> direcciones = repositorio.findAllByPais_Region_Nombre("Europe");
		log.debug("direcciones:"+direcciones);

		assertEquals(false, direcciones.isEmpty());
		assertEquals(3, direcciones.size());

	}

	@Test
	@DisplayName("findAllByNombreRegionFail")
	void findAllByNombreRegionFail() {
		log.info("[findAllByNombreRegionFail]");

		List<Direccion> direcciones = repositorio.findAllByPais_Region_Nombre(null);
		log.debug("direcciones:"+direcciones);

		assertEquals(true, direcciones.isEmpty());

	}
	
	@Test
	@DisplayName("findAllByCodigoPaisSuccess")
	void findAllByCodigoPaisSuccess() {
		log.info("[findAllByCodigoPaisSuccess]");

		List<Direccion> direcciones = repositorio.findAllByCodigoPais("IT");
		log.debug("direcciones:"+direcciones);

		assertEquals(false, direcciones.isEmpty());
		assertEquals(1, direcciones.size());

	}

	@Test
	@DisplayName("findAllByCodigoPaisFail")
	void findAllByCodigoPaisFail() {
		log.info("[findAllByCodigoPaisFail]");

		List<Direccion> direcciones = repositorio.findAllByCodigoPais(null);
		log.debug("direcciones:"+direcciones);

		assertEquals(true, direcciones.isEmpty());

	}

	

	
}
