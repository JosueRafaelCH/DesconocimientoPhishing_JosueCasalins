package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Test_Evaluativo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestEvaluativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Test_Evaluativo")
    private Long idTestEvaluativo;

    @Column(name = "Titulo", nullable = false)
    private String titulo;

    @Column(name = "Descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ID_Nivel_Dificultad")
    private NivelDificultad nivelDificultad;

    @Column(name = "Fecha_Creacion")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
