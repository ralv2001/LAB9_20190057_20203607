package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Participante;
import com.example.clase9ws20232.repository.ParticipanteRepository;
import jakarta.servlet.http.HttpServletRequest;
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

        participanteRepository.save(participante);
        if (fetchId) {
            responseMap.put("id", participante.getIdparticipante());
        }
        responseMap.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
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
