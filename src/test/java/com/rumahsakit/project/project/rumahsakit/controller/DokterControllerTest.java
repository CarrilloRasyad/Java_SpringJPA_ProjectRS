package com.rumahsakit.project.project.rumahsakit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rumahsakit.project.project.rumahsakit.model.Dokter;
import com.rumahsakit.project.project.rumahsakit.service.DokterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DokterControllerTest {
    @Mock
    private DokterService dokterService;

    @InjectMocks
    private DokterController dokterController;

    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dokterController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllDokter() throws Exception {
        List<Dokter> dokterList = List.of(
                new Dokter(1L, "Dokter A", "Spesialis A", 1500000),
                new Dokter(2L, "Dokter B", "Spesialis B", 2000000),
                new Dokter(3L, "Dokter C", "Spesialis C", 3000000)
        );
        lenient().when(dokterService.listAll()).thenReturn(dokterList);
        mockMvc.perform(get("/api/dokter"))
                .andExpect(status().isOk());
        HttpEntity<List<Dokter>> response = dokterController.getAllDokter();
        assertEquals(3, response.getBody().size());
        assertEquals("Dokter A", response.getBody().get(0).getNamaDokter());
    }

    @Test
    public void testGetDokterById() throws Exception {
        Dokter dokter = new Dokter(1L, "Dokter A", "Spesialis A",  1500000);
        lenient().when(dokterService.get(1L)).thenReturn(dokter);
        mockMvc.perform(get("/api/dokter/1"))
                .andExpect(status().isOk());
        HttpEntity<Dokter> response = dokterController.getDokterById(1L);
        assertEquals("Dokter A", response.getBody().getNamaDokter());
    }

    @Test
    public void testAddNewDokter() throws Exception {
        Dokter dokter = new Dokter(1L, "Dokter A", "Spesialis A", 1500000);
        lenient().when(dokterService.save(any(Dokter.class))).thenReturn(dokter);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/dokter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dokter))) // convert object to json as string format string
                .andExpect(MockMvcResultMatchers.status().isCreated());

        HttpEntity<Dokter> response = dokterController.addNewDokter(dokter);
        assertEquals("Dokter A", response.getBody().getNamaDokter());

    }

}
