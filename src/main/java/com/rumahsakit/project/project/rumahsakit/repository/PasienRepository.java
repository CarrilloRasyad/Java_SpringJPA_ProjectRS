package com.rumahsakit.project.project.rumahsakit.repository;

import com.rumahsakit.project.project.rumahsakit.model.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienRepository extends JpaRepository<Pasien, Long> {
}
