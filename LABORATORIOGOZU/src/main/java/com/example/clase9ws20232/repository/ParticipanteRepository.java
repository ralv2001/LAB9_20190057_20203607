package com.example.clase9ws20232.repository;

import com.example.clase9ws20232.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante,Integer> {

    @Query(value = "SELECT p.idparticipante, p.equipo, p.carrera, p.codigo, p.tipoParticipante \n" +
            "from participantespartido pp\n" +
            "inner join participante p on (p.idparticipante = pp.participante_idparticipante)\n" +
            "inner join partido part on (pp.partido_idpartido = part.idpartido)\n" +
            "where p.equipo = ?1 and part.idpartido = ?2",
    nativeQuery = true)
    List<Participante> buscarParticipanteEnPartidoEnEquipo(int equipoID, int partidoID);



    @Query(value = "SELECT p.idparticipante, p.equipo, p.carrera, p.codigo, p.tipoParticipante \n" +
            "from participantespartido pp\n" +
            "inner join participante p on (p.idparticipante = pp.participante_idparticipante)\n" +
            "inner join partido part on (pp.partido_idpartido = part.idpartido)\n" +
            "where part.idpartido = ?1",
            nativeQuery = true)
    List<Participante> buscarParticipanteEnPartido(int partidoID);

}
