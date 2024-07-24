package com.rumahsakit.project.project.rumahsakit.controller;

import com.rumahsakit.project.project.rumahsakit.model.Dokter;
import com.rumahsakit.project.project.rumahsakit.service.DokterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rs/dokter")
public class DokterController {

    @Autowired
    private DokterService dokterService;

    @GetMapping
    public HttpEntity<List<Dokter>> getAllDokter() {
        List<Dokter> dokterList = dokterService.listAll();
        return new ResponseEntity<>(dokterList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<Dokter> getDokterById(@PathVariable("id") Long id) {
        Dokter dokter = dokterService.get(id);
        return new ResponseEntity<>(dokter, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public HttpEntity<Dokter> addNewDokter(@Valid @RequestBody Dokter dokter) {
        Dokter newDokter = dokterService.save(dokter);
        return new ResponseEntity<>(newDokter, HttpStatus.CREATED);
    }
}







//@RestController
//@RequestMapping("/profile/dokter")
//public class DokterController {
//    @Autowired
//    public DokterService dokterService;
//
//    @GetMapping
//    public HttpEntity<List<Dokter>> get(){
//        return new ResponseEntity<>(dokterService.listAllDokter(), HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public HttpEntity<Dokter> getDokter(@PathVariable("id") Long id) {
//        Dokter dokter = dokterService.getDokter(id);
//
//        return new ResponseEntity<>(dokter, HttpStatus.OK);
//    }
//    @PostMapping
//    public HttpEntity<Dokter> addNewDokter(@Valid @RequestBody Dokter dokter) {
//        Dokter addDokter = dokterService.saveDokter(dokter);
//        return new ResponseEntity<>(addDokter, HttpStatus.CREATED);
//    }
//
//}
