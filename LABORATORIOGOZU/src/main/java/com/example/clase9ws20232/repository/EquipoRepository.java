package com.example.clase9ws20232.repository;


import com.example.clase9ws20232.entity.Equipo;
import com.example.clase9ws20232.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo,Integer> {
}
