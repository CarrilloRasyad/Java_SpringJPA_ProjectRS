package com.rumahsakit.project.project.rumahsakit.service;

import com.rumahsakit.project.project.rumahsakit.model.Dokter;
import java.util.List;

public interface DokterService {
    List<Dokter> listAll();
    Dokter get(Long id);
    Dokter save(Dokter dokter);
}
