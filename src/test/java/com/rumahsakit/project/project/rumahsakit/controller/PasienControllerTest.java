package com.rumahsakit.project.project.rumahsakit.controller;

import com.rumahsakit.project.project.rumahsakit.model.Pasien;
import com.rumahsakit.project.project.rumahsakit.service.PasienService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PasienControllerTest {

    @Mock
    private PasienService pasienService;

    @InjectMocks
    private PasienController pasienController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pasienController).build();
    }

    @Test
    public void testGetPasien() throws Exception {
        List<Pasien> pasienList =  List.of(   //Arrays.asList
                new Pasien(1L, "John Doe", 30, "123 Street", null),
                new Pasien(2L, "Jane Doe", 25, "456 Avenue", null)
        );

        lenient().when(pasienService.listAll()).thenReturn(pasienList);

        mockMvc.perform(get("/api/pasien"))
                .andExpect(status().isOk());

        HttpEntity<List<Pasien>> response = pasienController.getPasien();
        assertEquals(2, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getNamaPasien());
    }

    @Test
    public void testGetPasienById() throws Exception {
        Pasien pasien = new Pasien(1L, "John Doe", 30, "123 Street", null);

        lenient().when(pasienService.get(1L)).thenReturn(pasien);

        mockMvc.perform(get("/api/pasien/1"))
                .andExpect(status().isOk());

        HttpEntity<Pasien> response = pasienController.get(1L);
        assertEquals("John Doe", response.getBody().getNamaPasien());
    }

    @Test
    public void testAddNewPasien() throws Exception {
        Pasien pasien = new Pasien(1L, "John Doe", 30, "123 Street", null);

        lenient().when(pasienService.save(any(Pasien.class))).thenReturn(pasien);

        mockMvc.perform(post("/api/pasien")
                        .contentType("application/json")
                        .content("{\"namaPasien\":\"John Doe\", \"umurPasien\":30, \"alamatPasien\":\"123 Street\",\"penyakit\": {\n" +
                                "        \"id\":1\n" +
                                "    }}"))
                .andExpect(status().isCreated());

        HttpEntity<Pasien> response = pasienController.addNewPasien(pasien);
        assertEquals("John Doe", response.getBody().getNamaPasien());
    }
}
