package com.rumahsakit.project.project.rumahsakit.controller;

import com.rumahsakit.project.project.rumahsakit.model.Obat;
import com.rumahsakit.project.project.rumahsakit.model.Penyakit;
import com.rumahsakit.project.project.rumahsakit.service.PenyakitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PenyakitControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PenyakitService penyakitService;

    @InjectMocks
    private PenyakitController penyakitController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(penyakitController).build();
    }

    @Test
    public void testGetAllPenyakit() throws Exception {
        Obat obat = new Obat("Obat A", 50000.0f);
        Penyakit penyakit1 = new Penyakit(1L, "Flu", obat);
        Penyakit penyakit2 = new Penyakit(2L, "Demam", obat);

        List<Penyakit> penyakitList = Arrays.asList(penyakit1, penyakit2);

        when(penyakitService.listAll()).thenReturn(penyakitList);

        mockMvc.perform(get("/api/penyakit")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<List<Penyakit>> responseEntity = penyakitController.getAllPenyakit();
        assertEquals(penyakitList, responseEntity.getBody());
    }

    @Test
    public void testGetPenyakitById() throws Exception {
        Obat obat = new Obat("Obat A", 50000.0f);
        Penyakit penyakit = new Penyakit(1L, "Flu", obat);

        when(penyakitService.get(1L)).thenReturn(penyakit);

        mockMvc.perform(get("/api/penyakit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<Penyakit> responseEntity = penyakitController.getPenyakitById(1L);
        assertEquals(penyakit, responseEntity.getBody());
    }

    @Test
    public void testAddNewPenyakit() throws Exception {
        Obat obat = new Obat("Obat A", 50000.0f);
        Penyakit penyakit = new Penyakit(1L, "Flu", obat);

        when(penyakitService.save(any(Penyakit.class))).thenReturn(penyakit);

        mockMvc.perform(post("/api/penyakit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"namaPenyakit\": \"Flu\", \"obat\": {\"namaObat\": \"Obat A\", \"hargaObat\": 50000.0}}"))
                .andExpect(status().isCreated());

        ResponseEntity<Penyakit> responseEntity = penyakitController.addNewPenyakit(penyakit);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(penyakit, responseEntity.getBody());
    }
}
