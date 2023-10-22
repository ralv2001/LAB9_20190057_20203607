package com.example.clase9ws20232.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "carrera", nullable = false)
    private String carrera;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "tipoParticipante", nullable = false)
    private String tipoParticipante;
}
