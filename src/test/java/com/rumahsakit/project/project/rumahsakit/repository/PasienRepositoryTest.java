package com.rumahsakit.project.project.rumahsakit.repository;

import com.rumahsakit.project.project.rumahsakit.model.Obat;
import com.rumahsakit.project.project.rumahsakit.model.Pasien;
import com.rumahsakit.project.project.rumahsakit.model.Penyakit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PasienRepositoryTest {

    @Mock
    private PasienRepository pasienRepository;

    @Test
    public void testFindById() {
        // Mock data
        Penyakit penyakit = new Penyakit(1L, "Flu", new Obat("Obat A", 50000.0f));
        Pasien pasien = new Pasien(1L, "John Doe", 30, "123 Main St", penyakit);

        // Mock behavior
        when(pasienRepository.findById(1L)).thenReturn(Optional.of(pasien));

        // Test
        Optional<Pasien> foundPasien = pasienRepository.findById(1L);
        assertTrue(foundPasien.isPresent());
        assertEquals(1L, foundPasien.get().getId());
        assertEquals("John Doe", foundPasien.get().getNamaPasien());
    }

    @Test
    public void testFindAll() {
        // Mock data
        Penyakit penyakit1 = new Penyakit(1L, "Flu", new Obat("Obat A", 50000.0f));
        Penyakit penyakit2 = new Penyakit(2L, "Demam", new Obat("Obat B", 75000.0f));
        Pasien pasien1 = new Pasien(1L, "John Doe", 30, "123 Main St", penyakit1);
        Pasien pasien2 = new Pasien(2L, "Jane Smith", 25, "456 Elm St", penyakit2);
        List<Pasien> pasienList = new ArrayList<>();
        pasienList.add(pasien1);
        pasienList.add(pasien2);

        // Mock behavior
        when(pasienRepository.findAll()).thenReturn(pasienList);

        // Test
        List<Pasien> foundPasienList = pasienRepository.findAll();
        assertEquals(2, foundPasienList.size());
        assertEquals(pasien1.getNamaPasien(), foundPasienList.get(0).getNamaPasien());
        assertEquals(pasien2.getNamaPasien(), foundPasienList.get(1).getNamaPasien());
    }
    @Test
    public void testSavePasien() {
        // Mock data
        Penyakit penyakit = new Penyakit(1L, "Flu", new Obat("Obat A", 50000.0f));
        Pasien pasienToSave = new Pasien(null, "John Doe", 30, "123 Main St", penyakit);
        Pasien savedPasien = new Pasien(1L, "John Doe", 30, "123 Main St", penyakit);

        // Mock behavior
        when(pasienRepository.save(any(Pasien.class))).thenReturn(savedPasien);

        // Test
        Pasien returnedPasien = pasienRepository.save(pasienToSave);
        assertEquals(savedPasien.getId(), returnedPasien.getId());
        assertEquals(savedPasien.getNamaPasien(), returnedPasien.getNamaPasien());
    }
}