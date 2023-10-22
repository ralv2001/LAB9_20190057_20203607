package com.example.clase9ws20232.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "participante")
@Getter
@Setter
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idparticipante", nullable = false)
    private Integer idparticipante;

    @ManyToOne
    @JoinColumn(name = "equipo")
    private Equipo equipo;

    @Column(name = "carrera", nullable = false, length = 45)
    private String carrera;

    @Column(name = "codigo")
    private Double codigo;

    @Column(name = "tipoParticipante", nullable = false, length = 45)
    private String tipoParticipante;


}
