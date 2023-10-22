package com.example.clase9ws20232.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "deporte")
@Getter
@Setter
public class Deporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddeporte", nullable = false)
    private Integer iddeporte;

    @Column(name = "nombreDeporte", nullable = false, length = 45)
    private String nombreDeporte;

    @Column(name = "pesoDeporte", nullable = false)
    private Integer pesoDeporte;
}
