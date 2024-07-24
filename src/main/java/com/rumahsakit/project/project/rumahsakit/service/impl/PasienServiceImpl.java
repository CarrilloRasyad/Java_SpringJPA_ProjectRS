package com.rumahsakit.project.project.rumahsakit.service.impl;

import com.rumahsakit.project.project.rumahsakit.model.Pasien;
import com.rumahsakit.project.project.rumahsakit.model.Penyakit;
import com.rumahsakit.project.project.rumahsakit.repository.PasienRepository;
import com.rumahsakit.project.project.rumahsakit.repository.PenyakitRepository;
import com.rumahsakit.project.project.rumahsakit.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienServiceImpl implements PasienService {
    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private PenyakitRepository penyakitRepository;

    @Override
    public List<Pasien> listAll() {
        return pasienRepository.findAll();
    }

    @Override
    public Pasien get(Long id) {
        return pasienRepository.findById(id).get();
    }

    @Override
    public Pasien save(Pasien pasien) {
        Penyakit penyakit1 = penyakitRepository.findById(pasien.getPenyakit().getId()).get();
        pasien.setPenyakit(penyakit1);
        return pasienRepository.save(pasien);
    }
}
