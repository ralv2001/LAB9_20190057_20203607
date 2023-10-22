package com.example.clase9ws20232.controller;


import com.example.clase9ws20232.entity.Equipo;
import com.example.clase9ws20232.entity.HistorialPartidos;
import com.example.clase9ws20232.entity.Partido;
import com.example.clase9ws20232.repository.EquipoRepository;
import com.example.clase9ws20232.repository.HistorialPartidosRepository;
import com.example.clase9ws20232.repository.ParticipantesPartidoRepository;
import com.example.clase9ws20232.repository.PartidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/partido")
public class PartidoController {


    final PartidoRepository partidoRepository;
    final EquipoRepository equipoRepository;
    final HistorialPartidosRepository historialPartidosRepository;
    final ParticipantesPartidoRepository participantesPartidoRepository;

    public PartidoController(PartidoRepository partidoRepository,
                             EquipoRepository equipoRepository,
                             HistorialPartidosRepository historialPartidosRepository,
                             ParticipantesPartidoRepository participantesPartidoRepository){
        this.partidoRepository = partidoRepository;
        this.equipoRepository = equipoRepository;
        this.historialPartidosRepository = historialPartidosRepository;
        this.participantesPartidoRepository = participantesPartidoRepository;
    }




    //PREGUNTA 2B
    @GetMapping("/getparticipantes")
    public ResponseEntity<HashMap<String, Object>> listapartidosparticipantes(
            @RequestParam("idpartido") String idpartido,
            @RequestParam(name = "idequipo", required = false) String idequipo) {

        int partidoId = Integer.parseInt(idpartido);
        //primero busco el partido en cuestion
        Optional<Partido> partidoOptional = partidoRepository.findById(partidoId);

        //si el partido existe hago esto
        if (partidoOptional.isPresent()) {
            Partido partido = partidoOptional.get();
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("result", "ok");
            respuesta.put("partido", partido);

            if (idequipo != null) {
                int equipoId = Integer.parseInt(idequipo);
                Optional<Equipo> equipoOptional = equipoRepository.findById(equipoId);

                if (equipoOptional.isPresent()) {
                    Equipo equipo = equipoOptional.get();

                    // Verifica si el equipo está involucrado en el partido
                    if (partido.getEquipoa().getIdequipo() == equipoId || partido.getEquipob().getIdequipo() == equipoId) {
                        respuesta.put("resultequipo", "ok");
                        respuesta.put("equipo", equipo);
                    } else {
                        respuesta.put("resultequipo", "El equipo no está involucrado en el partido");
                    }
                } else {
                    respuesta.put("resultequipo", "ID EQUIPO no encontrado");
                }
            } else {
                // No se proporcionó el parámetro "idequipo", mostrar a todos los participantes del partido
                respuesta.put("resultequipo", "No se especificó un equipo");
            }

            return ResponseEntity.ok(respuesta);
        } else {
            // El partido no existe
            return ResponseEntity.notFound().build();
        }
    }




    //PREGUNTA 2C
    /*
    @GetMapping("/gethistorialpartidos")
    public ResponseEntity<HashMap<String, Object>> LISTAHISTORIAL(
            @RequestParam(name = "idequipo", required = false) String idequipo) {



            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("result", "ok");


        if (idequipo != null) {
            int equipoId = Integer.parseInt(idequipo);
            Optional<Equipo> equipoOptional = equipoRepository.findById(equipoId);

            if (equipoOptional.isPresent()) {
                Equipo equipo = equipoOptional.get();

                // Filtra el historial de partidos por equipo
                //List<HistorialPartidos> historialEquipo = historialPartidosRepository.findBy

                respuesta.put("historial", historialEquipo);

                if (!historialEquipo.isEmpty()) {
                    respuesta.put("resultequipo", "ok");
                    respuesta.put("equipo", equipo);
                } else {
                    respuesta.put("resultequipo", "El equipo no tiene historial de partidos");
                }
            } else {
                respuesta.put("resultequipo", "ID EQUIPO no encontrado");
            }
        } else {
            // No se proporcionó el parámetro "idequipo", mostrar el historial de todos los partidos
            respuesta.put("historial", historialPartidosRepository.findAll());
            respuesta.put("resultequipo", "No se especificó un equipo");
        }


            return ResponseEntity.ok(respuesta);

    }

    
     */
















}
