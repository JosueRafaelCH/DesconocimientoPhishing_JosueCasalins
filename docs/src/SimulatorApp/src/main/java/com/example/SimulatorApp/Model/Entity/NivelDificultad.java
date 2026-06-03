package com.example.SimulatorApp.Model.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Niveles_Dificultad")
public class NivelDificultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel")
    private Integer id;

    @Column(name = "nombre_nivel", nullable = false, length = 50)
    private String nombreNivel;

    @Column(name = "puntaje_minimo", nullable = false)
    private Double puntajeMinimo;

    @Column(name = "puntaje_maximo", nullable = false)
    private Double puntajeMaximo;

    public NivelDificultad() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombreNivel() { return nombreNivel; }
    public void setNombreNivel(String nombreNivel) { this.nombreNivel = nombreNivel; }
}