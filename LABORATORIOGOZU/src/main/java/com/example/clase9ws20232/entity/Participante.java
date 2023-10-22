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
    @JoinColumn(name = "partido_idpartido")
    private Partido partido;

    @ManyToOne
    @JoinColumn(name = "deporte_iddeporte")
    private Deporte deporte;

    @Column(name = "horaFecha")
    private Date horaFecha;

}
