package com.rumahsakit.project.project.rumahsakit.service;

import com.rumahsakit.project.project.rumahsakit.model.Penyakit;

import java.util.List;

public interface PenyakitService {
    List<Penyakit> listAll();
    Penyakit get(Long id);
    Penyakit save(Penyakit penyakit) throws Exception;
}
