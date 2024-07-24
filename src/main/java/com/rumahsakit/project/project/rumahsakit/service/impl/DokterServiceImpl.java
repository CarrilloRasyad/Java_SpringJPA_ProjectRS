package com.rumahsakit.project.project.rumahsakit.service.impl;

import com.rumahsakit.project.project.rumahsakit.model.Dokter;
import com.rumahsakit.project.project.rumahsakit.repository.DokterRepository;
import com.rumahsakit.project.project.rumahsakit.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DokterServiceImpl implements DokterService {
    @Autowired
    private DokterRepository dokterRepository;

    @Override
    public List<Dokter> listAll() {
        return dokterRepository.findAll();
    }

    @Override
    public Dokter get(Long id) {
        return dokterRepository.findById(id).orElse(null);
    }

    @Override
    public Dokter save(Dokter dokter) {
        return dokterRepository.save(dokter);
    }
}