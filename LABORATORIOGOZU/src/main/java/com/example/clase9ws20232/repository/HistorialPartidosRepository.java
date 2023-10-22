package com.example.clase9ws20232.repository;

import com.example.clase9ws20232.entity.Equipo;
import com.example.clase9ws20232.entity.HistorialPartidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistorialPartidosRepository extends JpaRepository<HistorialPartidos,Integer> {


    @Query(value = "SELECT hp.idhistorialPartidos, hp.partido_idpartido, hp.deporte_iddeporte, hp.horaFecha\n" +
            "from historialpartidos hp\n" +
            "inner join partido p on (p.idpartido = hp.partido_idpartido)\n" +
            "inner join equipo e on ((e.idequipo = p.equipoA) or (e.idequipo = p.equipoB))\n" +
            "where e.idequipo = ?1",
            nativeQuery = true)
    List<HistorialPartidos> buscarHistorialPorEquipo(int equipoID);

}
