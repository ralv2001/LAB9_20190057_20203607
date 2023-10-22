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
    private Equipo equipoa;

    @ManyToOne
    @JoinColumn(name = "equipoB")
    private Equipo equipob;

    @Column(name = "scoreA")
    private Integer scorea;

    @Column(name = "scoreB")
    private Integer scoreb;
}

