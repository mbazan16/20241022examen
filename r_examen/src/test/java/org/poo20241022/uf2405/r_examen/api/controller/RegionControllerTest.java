package org.poo20241022.uf2405.r_examen.api.controller;

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
import org.poo20241022.uf2405.r_examen.entities.Region;
import org.poo20241022.uf2405.r_examen.ln.BuscadorRegiones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class RegionControllerTest {
	
	Logger log = LoggerFactory.getLogger(RegionControllerTest.class);


    private MockMvc mockMvc;

	@Mock
    private BuscadorRegiones servicio;
	
	@InjectMocks
    private RegionController controller;

    
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    private Region getSampleRegion() {
        Region region = new Region();
		region.setId(1);
		region.setNombre("Mundo");
		return region;
    }

    @Test
    void testListRegiones() throws Exception {
    	log.info("[testListRegiones]");
    	log.info("SERVICIO"+servicio);
        when(servicio.listRegiones()).thenReturn(List.of(getSampleRegion()));

        mockMvc.perform(get("/api/regiones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Mundo"));
    }

    @Test
    void testFindRegion() throws Exception {
        when(servicio.conseguirRegion(1)).thenReturn(getSampleRegion());

        mockMvc.perform(get("/api/regiones/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mundo"));
    }

    @Test
    void testCreateRegion() throws Exception {
        Region d = getSampleRegion();
        when(servicio.grabarRegion(any(Region.class))).thenReturn(d);

        mockMvc.perform(post("/api/regiones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mundo"));
    }

    @Test
    void testUpdateRegion() throws Exception {
        Region d = getSampleRegion();
        when(servicio.grabarRegion(any(Region.class))).thenReturn(d);

        mockMvc.perform(put("/api/regiones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mundo"));
    }

    @Test
    void testListRegionesByRegion() throws Exception {
        when(servicio.conseguirRegionByNombre("Europe")).thenReturn(getSampleRegion());

        mockMvc.perform(get("/api/regiones/n/{nombre}","Europe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Mundo"));
    }
    
    @Test
    void testListRegionesByPais() throws Exception {
        when(servicio.conseguirRegionByCodigoPais("ES")).thenReturn(getSampleRegion());

        mockMvc.perform(get("/api/regiones/pais/{id}","ES"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Mundo"));
    }
    
   

}
