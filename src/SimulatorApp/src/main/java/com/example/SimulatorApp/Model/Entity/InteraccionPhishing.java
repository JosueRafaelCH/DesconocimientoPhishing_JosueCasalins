package com.example.SimulatorApp.Model.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Interacciones_Phishing")
public class InteraccionPhishing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interaccion")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false, unique = true)
    @JsonManagedReference
    private EventoSimulacion evento;

    @Column(name = "fecha_clic")
    private LocalDate fechaClic;

    @Column(name = "fecha_datos_ingresados")
    private LocalDate fechaDatosIngresados;

    @OneToMany(mappedBy = "interaccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<FeedbackIA> feedbacks = new ArrayList<>();

    public InteraccionPhishing() {

    }

    

    public InteraccionPhishing(Integer id, EventoSimulacion evento, LocalDate fechaClic, LocalDate fechaDatosIngresados,
            List<FeedbackIA> feedbacks) {
        this.id = id;
        this.evento = evento;
        this.fechaClic = fechaClic;
        this.fechaDatosIngresados = fechaDatosIngresados;
        this.feedbacks = feedbacks;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventoSimulacion getEvento() {
        return evento;
    }

    public void setEvento(EventoSimulacion evento) {
        this.evento = evento;
    }

    public LocalDate getFechaClic() {
        return fechaClic;
    }

    public void setFechaClic(LocalDate fechaClic) {
        this.fechaClic = fechaClic;
    }

    public LocalDate getFechaDatosIngresados() {
        return fechaDatosIngresados;
    }

    public void setFechaDatosIngresados(LocalDate fechaDatosIngresados) {
        this.fechaDatosIngresados = fechaDatosIngresados;
    }

    public List<FeedbackIA> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackIA> feedbacks) {
        this.feedbacks = feedbacks;
    }



    @Override
    public String toString() {
        return "InteraccionPhishing [id=" + id + ", evento=" + evento + ", fechaClic=" + fechaClic
                + ", fechaDatosIngresados=" + fechaDatosIngresados + ", feedbacks=" + feedbacks + "]";
    }

    

    

    
}