package com.example.clase9ws20232.controller;


import com.example.clase9ws20232.entity.*;
import com.example.clase9ws20232.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/partido")
public class PartidoController {

    final PartidoRepository partidoRepository;
    final ParticipanteRepository participanteRepository;
    final EquipoRepository equipoRepository;
    final HistorialPartidosRepository historialPartidosRepository;
    final ParticipantesPartidoRepository participantesPartidoRepository;

    public PartidoController(PartidoRepository partidoRepository,
                             EquipoRepository equipoRepository,
                             HistorialPartidosRepository historialPartidosRepository,
                             ParticipantesPartidoRepository participantesPartidoRepository,
                             ParticipanteRepository participanteRepository){
        this.partidoRepository = partidoRepository;
        this.equipoRepository = equipoRepository;
        this.historialPartidosRepository = historialPartidosRepository;
        this.participantesPartidoRepository = participantesPartidoRepository;
        this.participanteRepository = participanteRepository;
    }



    //PREGUNTA 2A
    // CREAR /
    @PostMapping(value = { "/registro"})
    public ResponseEntity<HashMap<String, Object>> guardarPartido(
            @RequestBody Partido partido) {

        HashMap<String, Object> responseJson = new HashMap<>();

        System.out.println(partido.getEquipoa() + "   gagagaga   " + partido.getEquipob());

        partidoRepository.save(partido);


        HistorialPartidos historialPartidos = new HistorialPartidos();
        historialPartidos.setPartido(partido);

        Deporte deporte = new Deporte();
        deporte.setIddeporte(1);
        historialPartidos.setDeporte(deporte);

        historialPartidos.setHoraFecha(new Date());

        historialPartidosRepository.save(historialPartidos);

        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
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


            //si se manda idequipo listare todos los aprticipantes de dicho partido en ese equipo
            //si se manda equipo
            if (idequipo != null) {
                int equipoId = Integer.parseInt(idequipo);
                Optional<Equipo> equipoOptional = equipoRepository.findById(equipoId);


                //se analiza si el id equipo existe
                if (equipoOptional.isPresent()) {
                    Equipo equipo = equipoOptional.get();


                    // Verifica si el equipo está involucrado en el partido
                    if (partido.getEquipoa().getIdequipo() == equipoId || partido.getEquipob().getIdequipo() == equipoId) {
                        respuesta.put("resultequipo", "ok");

                        //en equipo estara guardado los participantes del equipo que estuvieron en ese partido
                        respuesta.put("equipo", participanteRepository.buscarParticipanteEnPartidoEnEquipo(equipoId,partidoId));


                    } else {
                        respuesta.put("resultequipo", "El equipo no está involucrado en el partido");
                    }


                } else {
                    respuesta.put("resultequipo", "ID EQUIPO no encontrado");
                }





            //se mando solo el iddelpartido, toncs listare todos los participantes d ese partido nms
            //no se mandoidequipo
            } else {
                // No se proporcionó el parámetro "idequipo", mostrar a todos los participantes del partido
                respuesta.put("resultequipo", "No se especificó un equipo");

                //en participantes estaran TODOS los jugadores del partido
                respuesta.put("participantes",participanteRepository.buscarParticipanteEnPartido(partidoId));


            }

            return ResponseEntity.ok(respuesta);

        } else {
            // El partido no existe
            return ResponseEntity.notFound().build();
        }
    }








    //PREGUNTA 2C

    @GetMapping("/gethistorialpartidos")
    public ResponseEntity<HashMap<String, Object>> LISTAHISTORIAL(
            @RequestParam(name = "idequipo", required = false) String idequipo) {



        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("result", "ok");



        //si se ingresa un equipo
        if (idequipo != null) {
            int equipoId = Integer.parseInt(idequipo);
            Optional<Equipo> equipoOptional = equipoRepository.findById(equipoId);

            //si el idequipo ingresado existe
            if (equipoOptional.isPresent()) {
                Equipo equipo = equipoOptional.get();

                // Filtra el historial de partidos por equipo

                respuesta.put("historial", historialPartidosRepository.buscarHistorialPorEquipo(equipoId));


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

}
