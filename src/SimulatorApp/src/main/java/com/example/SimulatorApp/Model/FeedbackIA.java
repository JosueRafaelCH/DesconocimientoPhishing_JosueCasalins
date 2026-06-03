package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Feedback_IA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Feedback_IA")
    private Long idFeedbackIA;

    @ManyToOne
    @JoinColumn(name = "ID_Interaccion_Phishing", nullable = false)
    private InteraccionPhishing interaccionPhishing;

    @Column(name = "Mensaje_Feedback", columnDefinition = "LONGTEXT")
    private String mensajeFeedback;

    @Column(name = "Recomendaciones", columnDefinition = "LONGTEXT")
    private String recomendaciones;

    @Column(name = "Nivel_Confianza")
    private Float nivelConfianza;

    @Column(name = "Fecha_Generacion")
    private LocalDateTime fechaGeneracion;

    @PrePersist
    protected void onCreate() {
        fechaGeneracion = LocalDateTime.now();
    }
}
