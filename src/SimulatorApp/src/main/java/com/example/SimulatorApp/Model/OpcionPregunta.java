package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Opciones_Pregunta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpcionPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Opcion_Pregunta")
    private Long idOpcionPregunta;

    @ManyToOne
    @JoinColumn(name = "ID_Pregunta_Test", nullable = false)
    private PreguntaTest preguntaTest;

    @Column(name = "Texto_Opcion", columnDefinition = "TEXT", nullable = false)
    private String textoOpcion;

    @Column(name = "Es_Correcta", nullable = false)
    private Boolean esCorrecta;
}
