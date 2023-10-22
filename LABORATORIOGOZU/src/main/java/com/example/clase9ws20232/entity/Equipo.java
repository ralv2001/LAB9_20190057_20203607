package com.example.clase9ws20232.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "equipo")
@Getter
@Setter
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idequipo", nullable = false)
    private Integer idequipo;

    @Column(name = "nombreEquipo", nullable = false, length = 45)
    private String nombreEquipo;

    @Column(name = "colorEquipo", nullable = false, length = 45)
    private String colorEquipo;

    @Column(name = "mascota", nullable = false, length = 45)
    private String mascota;

}
