package com.rumahsakit.project.project.rumahsakit.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pasien")
public class Pasien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaPasien;
    private int umurPasien;
    private String alamatPasien;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Penyakit penyakit;

}
