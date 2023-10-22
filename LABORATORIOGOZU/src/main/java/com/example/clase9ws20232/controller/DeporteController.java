package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Deporte;
import com.example.clase9ws20232.entity.Equipo;
import com.example.clase9ws20232.repository.DeporteRepository;
import com.example.clase9ws20232.repository.EquipoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
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

    @PostMapping(value = "/registro")
    public ResponseEntity<HashMap<String, Object>> guardarDeporte(
            @RequestBody Deporte deporte,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();

        deporteRepository.save(deporte);
        if (fetchId) {
            responseMap.put("id", deporte.getIddeporte());
        }
        responseMap.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un equipo");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
    //produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8"

}
