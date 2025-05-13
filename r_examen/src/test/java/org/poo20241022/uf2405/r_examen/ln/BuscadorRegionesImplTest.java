package org.poo20241022.uf2405.r_examen.ln;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.poo20241022.uf2405.r_examen.entities.Region;
import org.poo20241022.uf2405.r_examen.exceptions.CodeError;
import org.poo20241022.uf2405.r_examen.exceptions.ServicioException;
import org.poo20241022.uf2405.r_examen.repositories.RegionRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuscadorRegionesImplTest {

	@InjectMocks
	private BuscadorRegionesImpl servicio;

	@Mock
	private RegionRepository repository;

	private Region region;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		region = new Region();
		region.setId(1);
		region.setNombre("Mundo");
	}

	@Test
	void testListRegionesSuccess() throws ServicioException {
		when(repository.findAll()).thenReturn(List.of(region));

		List<Region> result = servicio.listRegiones();

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(repository).findAll();
	}

	@Test
	void testListRegionesException() {
		when(repository.findAll()).thenThrow(new RuntimeException("DB Error"));

		assertThrows(ServicioException.class, () -> servicio.listRegiones());
	}

	@Test
	void testConseguirRegionSuccess() throws ServicioException {
		when(repository.findById(1)).thenReturn(Optional.of(region));

		Region result = servicio.conseguirRegion(1);

		assertNotNull(result);
		assertEquals("Mundo", result.getNombre());
	}

	@Test
	void testConseguirRegionNotFound() {
		when(repository.findById(1)).thenReturn(Optional.empty());

		ServicioException ex = assertThrows(ServicioException.class, () -> servicio.conseguirRegion(1));
		assertEquals(CodeError.REGION_NOT_FOUND, ex.getCodigo());
	}

	@Test
	void testGrabarRegionSuccess() throws ServicioException {
		when(repository.save(any(Region.class))).thenReturn(region);

		Region result = servicio.grabarRegion(region);

		assertNotNull(result);
		assertEquals("Mundo", result.getNombre());
	}

	@Test
	void testGrabarRegionException() {
		when(repository.save(any(Region.class))).thenThrow(new RuntimeException("DB Error"));

		assertThrows(ServicioException.class, () -> servicio.grabarRegion(region));
	}

	@Test
	void testEliminarRegionSuccess() {
		assertDoesNotThrow(() -> servicio.eliminarRegion(1));
		verify(repository).deleteById(1);
	}

	@Test
	void testEliminarRegionException() {
		doThrow(new RuntimeException("DB Error")).when(repository).deleteById(1);

		assertThrows(ServicioException.class, () -> servicio.eliminarRegion(1));
	}
	
	@Test
	void testRegionByNombreRegionSuccess() throws ServicioException {
		when(repository.findByNombre("Mundo")).thenReturn(Optional.of(region));

		Region result = servicio.conseguirRegionByNombre("Mundo");

		assertNotNull(result);
		assertEquals("Mundo", result.getNombre());
	}

	
	@Test
	void testRegionByNombreRegionException() {
		when(repository.findByNombre("Mundo")).thenReturn(Optional.empty());

		ServicioException ex = assertThrows(ServicioException.class, () -> servicio.conseguirRegionByNombre(null));
		assertEquals(CodeError.REGION_NOT_FOUND, ex.getCodigo());
	}
	
	@Test
	void testRegionByCodigoPaisSuccess() throws ServicioException {
		when(repository.findByCodigoPais("ES")).thenReturn(Optional.of(region));

		Region result = servicio.conseguirRegionByCodigoPais("ES");

		assertNotNull(result);
		assertEquals("Mundo", result.getNombre());
	}

	@Test
	void testRegionByCodigoPaisException() {
		when(repository.findByCodigoPais("ES")).thenReturn(Optional.empty());

		ServicioException ex = assertThrows(ServicioException.class, () -> servicio.conseguirRegionByCodigoPais(null));
		assertEquals(CodeError.REGION_NOT_FOUND, ex.getCodigo());
	}
	
	

}