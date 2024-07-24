package com.rumahsakit.project.project.rumahsakit.service;

import com.rumahsakit.project.project.rumahsakit.model.Obat;
import com.rumahsakit.project.project.rumahsakit.model.Pasien;
import com.rumahsakit.project.project.rumahsakit.model.Penyakit;
import com.rumahsakit.project.project.rumahsakit.repository.PasienRepository;
import com.rumahsakit.project.project.rumahsakit.repository.PenyakitRepository;
import com.rumahsakit.project.project.rumahsakit.service.impl.PasienServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PasienServiceTest {

    @Mock
    private PasienRepository pasienRepository;

    @Mock
    private PenyakitRepository penyakitRepository;

    @InjectMocks
    private PasienServiceImpl pasienService;

    private Pasien pasien;
    private Penyakit penyakit;
    private Obat obat;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        obat = new Obat("Obat A",50000.0f);
        penyakit = new Penyakit(1L, "Flu",obat);
        pasien = new Pasien(1L, "John Doe", 30, "123 Main St", penyakit);
    }

    @Test
    public void testListAll() {
        List<Pasien> pasienList = Arrays.asList(pasien);
        lenient().when(pasienRepository.findAll()).thenReturn(pasienList);

        List<Pasien> result = pasienService.listAll();
        assertEquals(1, result.size());
        assertEquals(pasien.getNamaPasien(), result.get(0).getNamaPasien());
    }

    @Test
    public void testGet() {
        lenient().when(pasienRepository.findById(1L)).thenReturn(Optional.of(pasien));

        Pasien result = pasienService.get(1L);
        assertNotNull(result);
        assertEquals(pasien.getNamaPasien(), result.getNamaPasien());
    }

    @Test
    public void testSave() {
        lenient().when(penyakitRepository.findById(1L)).thenReturn(Optional.of(penyakit));
        lenient().when(pasienRepository.save(any(Pasien.class))).thenReturn(pasien);

        Pasien result = pasienService.save(pasien);
        assertNotNull(result);
        assertEquals(pasien.getNamaPasien(), result.getNamaPasien());
    }
}
