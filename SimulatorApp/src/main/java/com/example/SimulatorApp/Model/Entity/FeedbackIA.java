package com.example.SimulatorApp.Model.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Feedback_IA")
public class FeedbackIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_interaccion", nullable = false)
    @JsonManagedReference
    private InteraccionPhishing interaccion;

    @Column(name = "contenido_feedback", columnDefinition = "TEXT", nullable = false)
    private String contenidoFeedback;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @Column(name = "modelo_ia", length = 100)
    private String modeloIa;

    public FeedbackIA() {

    }

    public FeedbackIA(Integer id, InteraccionPhishing interaccion, String contenidoFeedback, LocalDate fechaGeneracion,
            String modeloIa) {
        this.id = id;
        this.interaccion = interaccion;
        this.contenidoFeedback = contenidoFeedback;
        this.fechaGeneracion = fechaGeneracion;
        this.modeloIa = modeloIa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InteraccionPhishing getInteraccion() {
        return interaccion;
    }

    public void setInteraccion(InteraccionPhishing interaccion) {
        this.interaccion = interaccion;
    }

    public String getContenidoFeedback() {
        return contenidoFeedback;
    }

    public void setContenidoFeedback(String contenidoFeedback) {
        this.contenidoFeedback = contenidoFeedback;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getModeloIa() {
        return modeloIa;
    }

    public void setModeloIa(String modeloIa) {
        this.modeloIa = modeloIa;
    }

    @Override
    public String toString() {
        return "FeedbackIA [id=" + id + ", interaccion=" + interaccion + ", contenidoFeedback=" + contenidoFeedback
                + ", fechaGeneracion=" + fechaGeneracion + ", modeloIa=" + modeloIa + "]";
    }

    



    
}
