package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Respuestas_Test")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Respuesta_Test")
    private Long idRespuestaTest;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Pregunta_Test", nullable = false)
    private PreguntaTest preguntaTest;

    @ManyToOne
    @JoinColumn(name = "ID_Opcion_Seleccionada")
    private OpcionPregunta opcionSeleccionada;

    @Column(name = "Fecha_Respuesta")
    private LocalDateTime fechaRespuesta;

    @Column(name = "Es_Correcta")
    private Boolean esCorrecta;

    @PrePersist
    protected void onCreate() {
        fechaRespuesta = LocalDateTime.now();
    }
}
