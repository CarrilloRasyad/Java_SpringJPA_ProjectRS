package com.rumahsakit.project.project.rumahsakit.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "obat")
public class Obat {
    @Id
    private String namaObat;
    private float hargaObat;
}


