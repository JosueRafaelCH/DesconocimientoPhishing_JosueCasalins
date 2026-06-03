package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Eventos_Simulacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoSimulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Evento_Simulacion")
    private Long idEventoSimulacion;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Escenario_Phishing", nullable = false)
    private EscenarioPhishing escenarioPhishing;

    @Column(name = "Tipo_Evento")
    private String tipoEvento;

    @Column(name = "Fecha_Evento")
    private LocalDateTime fechaEvento;

    @Column(name = "Detalles", columnDefinition = "TEXT")
    private String detalles;

    @PrePersist
    protected void onCreate() {
        fechaEvento = LocalDateTime.now();
    }
}
