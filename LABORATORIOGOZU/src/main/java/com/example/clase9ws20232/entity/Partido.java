package com.example.clase9ws20232.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "equipoa")
    private Equipo equipoa;

    @ManyToOne
    @JoinColumn(name = "equipob")
    private Equipo equipob;

    @Column(name = "scorea", nullable = false)
    private String scorea;

    @Column(name = "scoreb", nullable = false)
    private String scoreb;



}
