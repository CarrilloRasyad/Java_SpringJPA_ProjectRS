package com.rumahsakit.project.project.rumahsakit.service;

import com.rumahsakit.project.project.rumahsakit.model.Pasien;


import java.util.List;


public interface PasienService {
    List<Pasien> listAll();

    Pasien get(Long id);

    Pasien save(Pasien pasien);
}
