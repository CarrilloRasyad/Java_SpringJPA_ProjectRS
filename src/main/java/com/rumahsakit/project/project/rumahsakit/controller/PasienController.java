package com.rumahsakit.project.project.rumahsakit.controller;


import com.rumahsakit.project.project.rumahsakit.model.Pasien;
import com.rumahsakit.project.project.rumahsakit.service.PasienService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rs/pasien")
public class PasienController {

    Logger logger = LoggerFactory.getLogger(PasienController.class);

    @Autowired
    private PasienService pasienService;

    @GetMapping
    public HttpEntity<List<Pasien>> getPasien() {
        logger.info("Mengambil semua data pasien");
        List<Pasien> pasienList = pasienService.listAll();
        logger.info("Berhasil mengambil {} data pasien", pasienList.size());
        return new ResponseEntity<>(pasienList, HttpStatus.OK);
//        return new ResponseEntity<>(pasienService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<Pasien> get(@PathVariable("id") Long id) {
        Pasien pasien = pasienService.get(id);
        return new ResponseEntity<>(pasien, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Pasien> addNewPasien(@Valid @RequestBody Pasien pasien) {
        Pasien addPasien = pasienService.save(pasien);
        return new ResponseEntity<>(addPasien, HttpStatus.CREATED);
    }
}

//@RestController
//@RequestMapping("/api/pasien")
//public class PasienController {
//    Logger logger = LoggerFactory.getLogger(PasienController.class);
//    @Autowired
//    private PasienService pasienService;
//
//    @GetMapping
//    public HttpEntity<List<Pasien>>  getPasien(){
//        return new ResponseEntity<>(pasienService.listAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public HttpEntity<Pasien> getPasienById(@PathVariable("id") Long id){
//        Pasien pasien = pasienService.get(id);
//
//        return new ResponseEntity<>(pasien, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public HttpEntity<Pasien>addNewPasien(@Valid @RequestBody Pasien pasien) {
//        Pasien addPasien = pasienService.save(pasien);
//        return new ResponseEntity<>(addPasien, HttpStatus.CREATED);
//    }
//}
