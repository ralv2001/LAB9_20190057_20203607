package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Deporte;
import com.example.clase9ws20232.repository.DeporteRepository;
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
@RequestMapping("/deporte")
public class DeporteController {

    final DeporteRepository deporteRepository;

    public DeporteController(DeporteRepository deporteRepository) {
        this.deporteRepository = deporteRepository;
    }

    @PostMapping(value = "/registro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> guardarDeporte(
            @RequestBody Deporte deporte,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();
        try {
            deporteRepository.save(deporte);
            if (fetchId) {
                responseMap.put("id", deporte.getIddeporte());
            }
            responseMap.put("estado", "creado");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        } catch (DataIntegrityViolationException e) {
            responseMap.put("estado", "error");
            if (e.getMessage().contains("nombreDeporte_UNIQUE")) {
                responseMap.put("msg", "El nombre del deporte ya existe en la base de datos.");
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
            responseMap.put("msg", "Debe enviar un deporte");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
