package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Preguntas_Test")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Pregunta_Test")
    private Long idPreguntaTest;

    @ManyToOne
    @JoinColumn(name = "ID_Test_Evaluativo", nullable = false)
    private TestEvaluativo testEvaluativo;

    @Column(name = "Enunciado", columnDefinition = "TEXT", nullable = false)
    private String enunciado;

    @Column(name = "Tipo_Pregunta", length = 50)
    private String tipoPregunta;
}
