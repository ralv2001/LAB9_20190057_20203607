package com.example.clase9ws20232.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "partido")
@Getter
@Setter
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpartido", nullable = false)
    private Integer idpartido;

    @ManyToOne
    @JoinColumn(name = "equipoA")
    private Equipo equipoA;

    @ManyToOne
    @JoinColumn(name = "equipoB")
    private Equipo equipoB;

    @Column(name = "scoreA")
    private Integer scoreA;

    @Column(name = "scoreB")
    private Integer scoreB;
}
