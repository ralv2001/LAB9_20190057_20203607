package com.example.clase9ws20232.repository;


import com.example.clase9ws20232.entity.Category;
import com.example.clase9ws20232.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido,Integer> {
}
