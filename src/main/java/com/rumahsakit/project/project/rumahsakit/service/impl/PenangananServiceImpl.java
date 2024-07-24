package com.rumahsakit.project.project.rumahsakit.service.impl;

import com.rumahsakit.project.project.rumahsakit.controller.PasienController;
import com.rumahsakit.project.project.rumahsakit.model.*;
import com.rumahsakit.project.project.rumahsakit.repository.*;
import com.rumahsakit.project.project.rumahsakit.service.PenangananService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PenangananServiceImpl implements PenangananService {
    Logger logger = LoggerFactory.getLogger(PasienController.class);

    @Autowired
    private PenangananRepository penangananRepository;

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private DokterRepository dokterRepository;

    @Autowired
    private ObatRepository obatRepository;

    @Autowired
    private PenyakitRepository penyakitRepository;

    @Override
    public List<Penanganan> listAll() {
        return penangananRepository.findAll();
    }

    @Override
    public Penanganan get(Long id) {
        return penangananRepository.findById(id).orElse(null);
    }

    @Override
    public Penanganan save(Penanganan penanganan) {
        return penangananRepository.save(penanganan);
    }

    @Override
    public List<Penanganan> findByNamaPasien(String namaPasien) {
        return penangananRepository.findByPasienNamaPasien(namaPasien);
    }

    @Override
    public String addNewPenanganan(Penanganan penanganan) throws Exception {
        Optional<Pasien> pasien1 = pasienRepository.findById(penanganan.getPasien().getId());
        Optional<Dokter> dokter1 = dokterRepository.findById(penanganan.getDokter().getId());
        List<Penyakit> penyakitkit = new ArrayList<>();
        for(Penyakit p : penanganan.getPenyakit()) {
            penyakitkit.add(penyakitRepository.findById(p.getId()).get());
        }
//        Penyakit penyakitt = penyakitRepository.findById(penanganan.getPasien().getId()).get();
        if(!pasien1.isPresent()) {
            throw new Exception("Pasien ilang");
        }

        if (dokter1.isEmpty()) {
            throw new Exception("Dokter tidak tersedia");
        }
        logger.info("dokter tersedia " + dokter1);
        logger.info("info penanganan " + penanganan);

        for (Obat obat : penanganan.getPenyakit().stream().map(p -> p.getObat()).toList()) {
            Optional<Obat> obatOpt = obatRepository.findById(obat.getNamaObat());
            if (!obatOpt.isPresent()) {
                throw new Exception("Obat " + obat.getNamaObat() + " tidak tersedia");
            }
        }


        penanganan.setDokter(dokter1.get());
        penanganan.setPasien(pasien1.get());
        penanganan.setPenyakit(penyakitkit);
        penanganan = penangananRepository.save(penanganan);
//        List<Penanganan> test = penangananRepository.findAll();
        logger.info("masuk sini" + penanganan);
        return generateNota(penanganan);
    }

    private String generateNota(Penanganan penanganan) {
        StringBuilder nota = new StringBuilder();
        nota.append("Nota Penanganan Pelayanan\n");
        nota.append("=========================\n");
        nota.append("Pasien: ").append(penanganan.getPasien().getNamaPasien()).append("\n");
        nota.append("Dokter: ").append(penanganan.getDokter().getNamaDokter()).append("\n");
        nota.append("Penyakit dan Obat:\n");

        penanganan.getPenyakit().forEach(p -> {
            nota.append("- ").append(p.getNamaPenyakit()).append("& ")
                    .append(p.getObat().getNamaObat()).append(" (")
                    .append(p.getObat().getHargaObat()).append(")\n");
        });

        nota.append("Harga Pelayanan: ").append(penanganan.getHargaPelayanan()).append("\n");
        nota.append("=========================\n");
        return nota.toString();
    }
}

//@Service
//public class PenangananServiceImpl implements PenangananService {
//    @Autowired
//    private PenangananRepository penangananRepository;
//
//    @Autowired
//    private PasienRepository pasienRepository;
//
//    @Autowired
//    private DokterRepository dokterRepository;
//
//    @Autowired
//    private ObatRepository obtrepo;
//
//    @Override
//    public List<Penanganan> listAllPenanganan() {
//        return penangananRepository.findAll();
//    }
//    @Override
//    public Penanganan get(Long id) {
//        return penangananRepository.findById(id).orElse(null);
//    }
//    @Override
//    public Penanganan save(Penanganan penanganan) {
//        return penangananRepository.save(penanganan);
//    }
//    @Override
//    public List<Penanganan> findByNamaPasien(String namaPasien) {
//        return penangananRepository.findByNamaPasien(namaPasien);
//    }
//    @Override
//    public String addNewPenanganan(Penanganan penanganan) throws Exception {
//        // validasi apakah dokternya ada atau tidak
//        Optional<Dokter> dokterOpt = dokterRepository.findById(penanganan.getDokter().getId());
//        if (!dokterOpt.isPresent()) {
//            throw new Exception("Dokter tidak tersedia");
//        }
//
//        // validasi apakah obatnya ada atau tidak
//        for (Obat obat : penanganan.getPenyakit().stream().map(p -> p.getObat()).toList()) {
//            Optional<Obat> obatOpt = obtrepo.findById(obat.getNamaObat());
//            if (!obatOpt.isPresent()) {
//                throw new Exception("Obat " + obat.getNamaObat() + " tidak tersedia");
//            }
//        }
//
//        // Save the treatment (Penanganan)
//        Penanganan savedPenanganan = penangananRepository.save(penanganan);
//
//        // Generate and return the receipt (nota)
//        return generateNota(savedPenanganan);
//    }
//    private String generateNota(Penanganan penanganan) {
//        StringBuilder nota = new StringBuilder();
//        nota.append("Nota Penanganan Pelayanan\n");
//        nota.append("=========================\n");
//        nota.append("Pasien: ").append(penanganan.getPasien().getNamaPasien()).append("\n");
//        nota.append("Dokter: ").append(penanganan.getDokter().getNamaDokter()).append("\n");
//        nota.append("Penyakit dan Obat:\n");
//
//        penanganan.getPenyakit().forEach(p -> {
//            nota.append("- ").append(p.getNamaPenyakit()).append("& ")
//                    .append(p.getObat().getNamaObat()).append(" (")
//                    .append(p.getObat().getHargaObat()).append(")\n");
//        });
//
//        nota.append("Harga Pelayanan: ").append(penanganan.getHargaPelayanan()).append("\n");
//        nota.append("=========================\n");
//        return nota.toString();
//    }
//}
