package com.rumahsakit.project.project.rumahsakit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "penyakit")
public class Penyakit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namaPenyakit;

    @OneToOne
    @JoinColumn(referencedColumnName = "namaObat")
    private Obat obat;
}
