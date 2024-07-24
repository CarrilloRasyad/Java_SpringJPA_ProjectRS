package com.rumahsakit.project.project.rumahsakit.service;

import com.rumahsakit.project.project.rumahsakit.model.Obat;

import java.util.List;

public interface ObatService {
    List<Obat> listAll();
    Obat get(String id);
    Obat save(Obat obat);
}
