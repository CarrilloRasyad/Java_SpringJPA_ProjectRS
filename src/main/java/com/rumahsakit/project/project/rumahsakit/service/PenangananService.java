package com.rumahsakit.project.project.rumahsakit.service;

import com.rumahsakit.project.project.rumahsakit.model.Penanganan;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PenangananService {

    List<Penanganan> listAll();
    Penanganan get(Long id);
    Penanganan save(Penanganan penanganan);
    List<Penanganan>findByNamaPasien(String namaPasien);
    String addNewPenanganan(Penanganan penanganan) throws Exception;
}
