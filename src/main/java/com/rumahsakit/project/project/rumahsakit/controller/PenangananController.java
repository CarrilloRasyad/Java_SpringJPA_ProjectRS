package com.rumahsakit.project.project.rumahsakit.controller;

import com.rumahsakit.project.project.rumahsakit.model.Penanganan;
import com.rumahsakit.project.project.rumahsakit.service.PenangananService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rs/penanganan")
public class PenangananController {
    @Autowired
    private PenangananService penangananService;

    @GetMapping
    public HttpEntity<List<Penanganan>> getAllPenanganan() {
        return new ResponseEntity<>(penangananService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<Penanganan> getPenangananById(@PathVariable("id") Long id) {
        Penanganan penanganan = penangananService.get(id);
        return new ResponseEntity<>(penanganan, HttpStatus.OK);
    }

    @GetMapping("/search")
    public HttpEntity<List<Penanganan>> getPenangananByNamaPasien(@RequestParam("namaPasien") String namaPasien) {
        List<Penanganan> penangananList = penangananService.findByNamaPasien(namaPasien);
        return new ResponseEntity<>(penangananList, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<String> addNewPenanganan(@Valid @RequestBody Penanganan penanganan) {
        try {
            String nota = penangananService.addNewPenanganan(penanganan);
            return new ResponseEntity<>(nota, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
