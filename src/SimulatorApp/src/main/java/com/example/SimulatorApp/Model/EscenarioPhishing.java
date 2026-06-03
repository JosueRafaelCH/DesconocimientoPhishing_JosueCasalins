package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Escenarios_Phishing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscenarioPhishing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Escenario_Phishing")
    private Long idEscenarioPhishing;

    @Column(name = "Titulo", nullable = false)
    private String titulo;

    @Column(name = "Descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ID_Nivel_Dificultad")
    private NivelDificultad nivelDificultad;

    @Column(name = "Contenido_Escenario", columnDefinition = "LONGTEXT")
    private String contenidoEscenario;

    @Column(name = "Respuesta_Correcta", columnDefinition = "LONGTEXT")
    private String respuestaCorrecta;

    @Column(name = "Fecha_Creacion")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
