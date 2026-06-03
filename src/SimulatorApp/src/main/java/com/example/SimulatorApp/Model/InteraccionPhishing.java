package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Interacciones_Phishing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteraccionPhishing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Interaccion_Phishing")
    private Long idInteraccionPhishing;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Escenario_Phishing", nullable = false)
    private EscenarioPhishing escenarioPhishing;

    @Column(name = "Tipo_Interaccion")
    private String tipoInteraccion;

    @Column(name = "Respuesta_Usuario", columnDefinition = "TEXT")
    private String respuestaUsuario;

    @Column(name = "Es_Correcta")
    private Boolean esCorrecta;

    @Column(name = "Puntuacion_Obtenida")
    private Integer puntuacionObtenida;

    @Column(name = "Fecha_Interaccion")
    private LocalDateTime fechaInteraccion;

    @PrePersist
    protected void onCreate() {
        fechaInteraccion = LocalDateTime.now();
    }
}
