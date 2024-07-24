package com.rumahsakit.project.project.rumahsakit.repository;

import com.rumahsakit.project.project.rumahsakit.model.Dokter;
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
public class DokterRepositoryTest {
    @Mock
    public DokterRepository dokterRepository;

    @Test
    public void testFindAll() {
        Dokter dokter1 = new Dokter(1L, "Dokter A", "Spesialis A", 1000000);
        Dokter dokter2 = new Dokter(2L, "Dokter B", "Spesialis B", 2000000);

        List<Dokter> dokterList = new ArrayList<>();
        dokterList.add(dokter1);
        dokterList.add(dokter2);

        when(dokterRepository.findAll()).thenReturn(dokterList);

        List<Dokter> cariListDokter = dokterRepository.findAll();
        assertEquals(2, cariListDokter.size());
        assertEquals(dokter1.getNamaDokter(), cariListDokter.get(0).getNamaDokter());
        assertEquals(dokter2.getNamaDokter(), cariListDokter.get(1).getNamaDokter());
    }
    @Test
    public void testFindById() {
        Dokter dokter = new Dokter(1L,"Dokter A", "Spesialis A", 1000000);
        when(dokterRepository.findById(1L)).thenReturn(Optional.of(dokter));

        Optional<Dokter> cariDokter = dokterRepository.findById(1L);
        assertTrue(cariDokter.isPresent());
    }
    @Test
    public void testSavePasien() {
        Dokter saveDokter = new Dokter(1L, "Dokter A", "Spesialis A", 1000000);
        Dokter dokterToSave = new Dokter(null, "Dokter A", "Spesialis", 500000);

        when(dokterRepository.save(any(Dokter.class))).thenReturn(saveDokter);

        Dokter returnDokter = dokterRepository.save(dokterToSave);
        assertEquals(saveDokter.getId(), returnDokter.getId());
        assertEquals(saveDokter.getNamaDokter(), returnDokter.getNamaDokter());
    }
}
