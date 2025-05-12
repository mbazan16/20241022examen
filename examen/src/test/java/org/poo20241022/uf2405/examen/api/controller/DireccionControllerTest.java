package org.poo20241022.uf2405.examen.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poo20241022.uf2405.examen.entities.Direccion;
import org.poo20241022.uf2405.examen.ln.BuscadorDirecciones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class DireccionControllerTest {

	Logger log = LoggerFactory.getLogger(DireccionControllerTest.class);

	private MockMvc mockMvc;

	@Mock
	private BuscadorDirecciones servicio;

	@InjectMocks
	private DireccionController controller;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		objectMapper = new ObjectMapper();
	}

	private Direccion getSampleDireccion() {
		Direccion direccion = new Direccion();
		direccion = new Direccion();
		direccion.setId(1);
		direccion.setCalle("Alcala");
		direccion.setCiudad("Madrid");
		direccion.setProvincia("Madrid");
		return direccion;
	}

	@Test
	void testListDirecciones() throws Exception {
		log.info("[testListDirecciones]");
		log.info("SERVICIO" + servicio);
		when(servicio.listDirecciones()).thenReturn(List.of(getSampleDireccion()));

		mockMvc.perform(get("/api/direcciones")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].ciudad").value("Madrid"));
	}

	@Test
	void testFindDireccion() throws Exception {
		when(servicio.conseguirDireccion(1)).thenReturn(getSampleDireccion());

		mockMvc.perform(get("/api/direcciones/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.ciudad").value("Madrid"));
	}

	@Test
	void testCreateDireccion() throws Exception {
		Direccion d = getSampleDireccion();
		when(servicio.grabarDireccion(any(Direccion.class))).thenReturn(d);

		mockMvc.perform(post("/api/direcciones").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(d))).andExpect(status().isOk())
				.andExpect(jsonPath("$.ciudad").value("Madrid"));
	}

	@Test
	void testUpdateDireccion() throws Exception {
		Direccion d = getSampleDireccion();
		when(servicio.grabarDireccion(any(Direccion.class))).thenReturn(d);

		mockMvc.perform(put("/api/direcciones").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(d))).andExpect(status().isOk())
				.andExpect(jsonPath("$.ciudad").value("Madrid"));
	}

	@Test
	void testListDireccionesByRegion() throws Exception {
		when(servicio.listDireccionesByNombreRegion("Europe")).thenReturn(List.of(getSampleDireccion()));

		mockMvc.perform(get("/api/direcciones/region/{nombre}", "Europe")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].ciudad").value("Madrid"));
	}

	@Test
	void testListDireccionesByPais() throws Exception {
		when(servicio.listDireccionesByCodigoPais("ES")).thenReturn(List.of(getSampleDireccion()));

		mockMvc.perform(get("/api/direcciones/pais/{id}", "ES")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].ciudad").value("Madrid"));
	}

	@Test
	void testFindDireccionByDepartamento() throws Exception {
		when(servicio.conseguirDireccionByDepartamentoId(1)).thenReturn(getSampleDireccion());

		mockMvc.perform(get("/api/direcciones/departamento/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.ciudad").value("Madrid"));
	}

}
