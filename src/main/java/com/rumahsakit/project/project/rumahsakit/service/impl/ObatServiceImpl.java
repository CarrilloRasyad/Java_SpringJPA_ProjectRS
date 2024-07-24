package com.rumahsakit.project.project.rumahsakit.service.impl;

import com.rumahsakit.project.project.rumahsakit.model.Obat;
import com.rumahsakit.project.project.rumahsakit.model.Pasien;
import com.rumahsakit.project.project.rumahsakit.repository.ObatRepository;
import com.rumahsakit.project.project.rumahsakit.service.ObatService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {

    @Autowired
    private ObatRepository obatRepository;

    @Override
    public List<Obat> listAll() {
        return obatRepository.findAll();
    }

    @Override
    public Obat get(String id) {
        return obatRepository.findById(id).orElse(null);
    }

    @Override
    public Obat save(Obat obat) {
        return obatRepository.save(obat);
    }
}

//@Service
//public class ObatServiceImpl implements ObatService {
//    Logger logger = LoggerFactory.getLogger(ObatServiceImpl.class);
//
//    @Autowired
//    private ObatRepository obtrepo;
//
//    @Override
//    public List<Obat> listAllObat() {
//
//        return obtrepo.findAll();
//    }
//
//    public Obat getObat(String id) {
//        return obtrepo.findById(id).orElse(null);
//    }
//
//
//    @Override
//    public Obat saveObat(Obat obat) {
//        return obtrepo.save(obat);
//    }
//}
 //   @Override
//    public Obat getObat(String id) {
//        try{
//            return obtrepo.findById(id).get();
//        }catch (NoSuchElementException e) {
//            throw new NoSuchElementException("Tidak ada obat");
//        }
//    }

