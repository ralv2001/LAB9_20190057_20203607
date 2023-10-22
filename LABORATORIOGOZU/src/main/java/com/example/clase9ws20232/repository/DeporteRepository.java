package com.example.clase9ws20232.repository;

import com.example.clase9ws20232.entity.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeporteRepository extends JpaRepository<Deporte,Integer> {
}
