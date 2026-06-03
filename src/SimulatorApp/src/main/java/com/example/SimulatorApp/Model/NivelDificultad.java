package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Niveles_Dificultad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NivelDificultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Nivel_Dificultad")
    private Long idNivelDificultad;

    @Column(name = "Nombre_Nivel", unique = true, nullable = false)
    private String nombreNivel;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Puntuacion_Minima")
    private Integer puntuacionMinima;

    @Column(name = "Puntuacion_Maxima")
    private Integer puntuacionMaxima;
}
