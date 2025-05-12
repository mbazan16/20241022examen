package org.poo20241022.uf2405.examen.ln;

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
import org.poo20241022.uf2405.examen.entities.Direccion;
import org.poo20241022.uf2405.examen.exceptions.CodeError;
import org.poo20241022.uf2405.examen.exceptions.ServicioException;
import org.poo20241022.uf2405.examen.repositories.DireccionRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuscadorDireccionesImplTest {

	@InjectMocks
	private BuscadorDireccionesImpl servicio;

	@Mock
	private DireccionRepository repository;

	private Direccion direccion;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		direccion = new Direccion();
		direccion.setId(1);
		direccion.setCalle("Alcala");
		direccion.setCiudad("Madrid");
		direccion.setProvincia("Madrid");
	}

	@Test
	void testListDireccionesSuccess() throws ServicioException {
		when(repository.findAll()).thenReturn(List.of(direccion));

		List<Direccion> result = servicio.listDirecciones();

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(repository).findAll();
	}

	@Test
	void testListDireccionesException() {
		when(repository.findAll()).thenThrow(new RuntimeException("DB Error"));

		assertThrows(ServicioException.class, () -> servicio.listDirecciones());
	}

	@Test
	void testConseguirDireccionSuccess() throws ServicioException {
		when(repository.findById(1)).thenReturn(Optional.of(direccion));

		Direccion result = servicio.conseguirDireccion(1);

		assertNotNull(result);
		assertEquals("Madrid", result.getCiudad());
	}

	@Test
	void testConseguirDireccionNotFound() {
		when(repository.findById(1)).thenReturn(Optional.empty());

		ServicioException ex = assertThrows(ServicioException.class, () -> servicio.conseguirDireccion(1));
		assertEquals(CodeError.DIRECCION_NOT_FOUND, ex.getCodigo());
	}

	@Test
	void testGrabarDireccionSuccess() throws ServicioException {
		when(repository.save(any(Direccion.class))).thenReturn(direccion);

		Direccion result = servicio.grabarDireccion(direccion);

		assertNotNull(result);
		assertEquals("Madrid", result.getCiudad());
	}

	@Test
	void testGrabarDireccionException() {
		when(repository.save(any(Direccion.class))).thenThrow(new RuntimeException("DB Error"));

		assertThrows(ServicioException.class, () -> servicio.grabarDireccion(direccion));
	}

	@Test
	void testEliminarDireccionSuccess() {
		assertDoesNotThrow(() -> servicio.eliminarDireccion(1));
		verify(repository).deleteById(1);
	}

	@Test
	void testEliminarDireccionException() {
		doThrow(new RuntimeException("DB Error")).when(repository).deleteById(1);

		assertThrows(ServicioException.class, () -> servicio.eliminarDireccion(1));
	}
	
	@Test
	void testListDireccionesByNombreRegionSuccess() throws ServicioException {
		when(repository.findAllByPais_Region_Nombre("Europe")).thenReturn(List.of(direccion));

		List<Direccion> result = servicio.listDireccionesByNombreRegion("Europe");

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(repository).findAllByPais_Region_Nombre("Europe");
	}

	@Test
	void testListDireccionesByNombreRegionException() {
		when(repository.findAllByPais_Region_Nombre(null)).thenThrow(new RuntimeException("DB Error"));

		assertThrows(ServicioException.class, () -> servicio.listDireccionesByNombreRegion(null));
	}
	
	@Test
	void testListDireccionesByCodigoPaisSuccess() throws ServicioException {
		when(repository.findAllByCodigoPais("ES")).thenReturn(List.of(direccion));

		List<Direccion> result = servicio.listDireccionesByCodigoPais("ES");

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(repository).findAllByCodigoPais("ES");
	}

	@Test
	void testListDireccionesByCodigoPaisException() {
		when(repository.findAllByCodigoPais(null)).thenThrow(new RuntimeException("DB Error"));

		assertThrows(ServicioException.class, () -> servicio.listDireccionesByCodigoPais(null));
	}
	
	@Test
	void testConseguirDireccionByDepartamentoIdSuccess() throws ServicioException {
		when(repository.findByIdDepartamento(70)).thenReturn(Optional.of(direccion));

		Direccion result = servicio.conseguirDireccionByDepartamentoId(70);

		assertNotNull(result);
		assertEquals("Madrid", result.getCiudad());
	}

	@Test
	void testConseguirDireccionByDepartamentoIdNotFound() {
		when(repository.findByIdDepartamento(70)).thenReturn(Optional.empty());

		ServicioException ex = assertThrows(ServicioException.class, () -> servicio.conseguirDireccionByDepartamentoId(70));
		assertEquals(CodeError.DIRECCION_NOT_FOUND, ex.getCodigo());
	}
	
	

}
