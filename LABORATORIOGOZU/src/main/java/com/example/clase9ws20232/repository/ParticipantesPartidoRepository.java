package com.example.clase9ws20232.repository;


import com.example.clase9ws20232.entity.Equipo;
import com.example.clase9ws20232.entity.ParticipantesPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ParticipantesPartidoRepository extends JpaRepository<ParticipantesPartido,Integer> {
}
