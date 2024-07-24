package com.rumahsakit.project.project.rumahsakit.repository;

import com.rumahsakit.project.project.rumahsakit.model.Penanganan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenangananRepository extends JpaRepository<Penanganan, Long> {
    List<Penanganan> findByPasienNamaPasien(String namaPasien);
}
