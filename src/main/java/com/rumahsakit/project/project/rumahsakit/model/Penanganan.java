package com.rumahsakit.project.project.rumahsakit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "penanganan")
public class Penanganan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Pasien pasien;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Dokter dokter;

    @ManyToMany
    @JoinTable
    private List<Penyakit> penyakit;

    private float hargaPelayanan;
}
