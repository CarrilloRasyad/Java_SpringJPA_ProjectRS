package com.rumahsakit.project.project.rumahsakit.controller;

import com.rumahsakit.project.project.rumahsakit.model.Dokter;
import com.rumahsakit.project.project.rumahsakit.model.Obat;
import com.rumahsakit.project.project.rumahsakit.model.Pasien;
import com.rumahsakit.project.project.rumahsakit.service.ObatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/rs/obat")
public class ObatController {

    @Autowired
    private ObatService obatService;

    @GetMapping
    public HttpEntity<List<Obat>> getAllObat() {
        return new ResponseEntity<>(obatService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<Obat> getObatById(@PathVariable("id") String id) {
        Obat obat = obatService.get(id);
        return new ResponseEntity<>(obat, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Obat> addNewObat(@Valid @RequestBody Obat obat) {
        Obat newObat = obatService.save(obat);
        return new ResponseEntity<>(newObat, HttpStatus.CREATED);
    }
}

//@RestController
//@RequestMapping("/api/obat")
//public class ObatController {
//    @Autowired
//    private ObatService service;
//
//    @GetMapping
//    public List<Obat> listAllObat(){
//        return service.listAllObat();
//    }
//    @GetMapping("/{id}")
//    public HttpEntity<Obat> getObatById(@PathVariable("id") String id){
//        Obat obat = service.getObat(id);
//
//        return new ResponseEntity<>(obat, HttpStatus.OK);
//    }
//    @PostMapping
//    public HttpEntity<Obat> addNewObat(@Valid @RequestBody Obat obat) {
//        Obat addObat = service.saveObat(obat);
//        return new ResponseEntity<>(addObat, HttpStatus.CREATED);
//    }
//}
