package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Equipo;
import com.example.clase9ws20232.repository.EquipoRepository;
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
@RequestMapping("/equipo")
public class EquipoController {

    final EquipoRepository equipoRepository;

    public EquipoController(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @PostMapping(value = "/registro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> guardarEquipo(
            @RequestBody Equipo equipo,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();
        try {
            equipoRepository.save(equipo);
            if (fetchId) {
                responseMap.put("id", equipo.getIdequipo());
            }
            responseMap.put("estado", "creado");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        } catch (DataIntegrityViolationException e) {
            responseMap.put("estado", "error");

            if (e.getMessage().contains("nombre_UNIQUE")) {
                responseMap.put("msg", "El nombre del equipo ya existe en la base de datos.");
            } else if (e.getMessage().contains("color_UNIQUE")) {
                responseMap.put("msg", "El color del equipo ya existe en la base de datos.");
            } else if (e.getMessage().contains("mascota_UNIQUE")) {
                responseMap.put("msg", "La mascota del equipo ya existe en la base de datos.");
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
            responseMap.put("msg", "Debe enviar un equipo");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
