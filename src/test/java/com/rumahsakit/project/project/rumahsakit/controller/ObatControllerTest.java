package com.rumahsakit.project.project.rumahsakit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rumahsakit.project.project.rumahsakit.model.Obat;
import com.rumahsakit.project.project.rumahsakit.service.ObatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ObatControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ObatService obatService;

    @InjectMocks
    private ObatController obatController;

    private ObjectMapper objectMapper = new ObjectMapper ();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(obatController).build();
    }

    @Test
    public void testGetAllObat() throws Exception {
        Obat obat1 = new Obat("ObatA", 5000.0f);
        Obat obat2 = new Obat("ObatB", 7000.0f);
        List<Obat> obatList = Arrays.asList(obat1, obat2);

        lenient().when(obatService.listAll()).thenReturn(obatList);

        mockMvc.perform(get("/api/obat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetObatById() throws Exception {
        Obat obat = new Obat("ObatA", 5000.0f);
        lenient().when(obatService.get("ObatA")).thenReturn(obat);

        mockMvc.perform(get("/api/obat/ObatA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddNewObat() throws Exception {
        Obat obat = new Obat("ObatA", 5000.0f);
        lenient().when(obatService.save(any(Obat.class))).thenReturn(obat);

        mockMvc.perform(post("/api/obat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(obat)))
                .andExpect(status().isCreated());

        HttpEntity<Obat> response = obatController.addNewObat(obat);
        assertEquals("ObatA", response.getBody().getNamaObat());
    }
}
