package com.example.clase9ws20232.controller;


import com.example.clase9ws20232.entity.Partido;
import com.example.clase9ws20232.repository.PartidoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partido")
public class PartidoController {


    final PartidoRepository partidoRepository;

    public PartidoController(PartidoRepository partidoRepository){
        this.partidoRepository = partidoRepository;
    }

    @GetMapping(value = {"/getparticipantes"})
    public List<Partido> listapartidosparticipantes(
            @RequestParam(value = "idpartido") int idpartido,
            @RequestParam(value = "idequipo", required = false) int idequipo) {

        if (idequipo > 0){

        }else{

        }

        return partidoRepository.findAll();
    }










}
