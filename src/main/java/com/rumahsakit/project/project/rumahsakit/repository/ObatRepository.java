package com.rumahsakit.project.project.rumahsakit.repository;

import com.rumahsakit.project.project.rumahsakit.model.Obat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObatRepository extends JpaRepository<Obat, String> {
}
