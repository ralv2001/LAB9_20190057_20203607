package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Participante;
import com.example.clase9ws20232.repository.ParticipanteRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/participante")
public class ParticipanteController {

    final ParticipanteRepository participanteRepository;

    public ParticipanteController(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @PostMapping(value = "/registro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> guardarParticipante(
            @RequestBody Participante participante,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();
        try {
            participanteRepository.save(participante);
            if (fetchId) {
                responseMap.put("id", participante.getIdparticipante());
            }
            responseMap.put("estado", "creado");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        } catch (DataIntegrityViolationException e) {
            responseMap.put("estado", "error");

            if (e.getMessage().contains("equipo_UNIQUE")) {
                responseMap.put("msg", "El equipo ya existe en la base de datos.");
            } else if (e.getMessage().contains("carrera_UNIQUE")) {
                responseMap.put("msg", "La carrera ya existe en la base de datos.");
            } else if (e.getMessage().contains("codigo_UNIQUE")) {
                responseMap.put("msg", "El código del participante ya existe en la base de datos.");
            } else if (e.getMessage().contains("tipoParticipante_UNIQUE")) {
                responseMap.put("msg", "El tipo de participante ya existe en la base de datos.");
            } else {
                responseMap.put("msg", "Error de integridad en la base de datos.");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un participante");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
