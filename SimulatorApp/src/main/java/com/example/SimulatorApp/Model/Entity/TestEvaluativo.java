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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Test_Evaluativo")
public class TestEvaluativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_test")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonManagedReference
    private Usuario usuario;

    @Column(name = "fecha_realizacion", nullable = false)
    private LocalDate fechaRealizacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RespuestaTest> respuestas = new ArrayList<>();

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<EventoSimulacion> eventos = new ArrayList<>();

    public TestEvaluativo() {
        respuestas = new ArrayList<>();
        eventos = new ArrayList<>();
    }

    

    public TestEvaluativo(Integer id, Usuario usuario, LocalDate fechaRealizacion, LocalDate fechaActualizacion,
            List<RespuestaTest> respuestas, List<EventoSimulacion> eventos) {
        this.id = id;
        this.usuario = usuario;
        this.fechaRealizacion = fechaRealizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.respuestas = respuestas;
        this.eventos = eventos;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<RespuestaTest> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaTest> respuestas) {
        this.respuestas = respuestas;
    }

    public List<EventoSimulacion> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoSimulacion> eventos) {
        this.eventos = eventos;
    }



    @Override
    public String toString() {
        return "TestEvaluativo [id=" + id + ", usuario=" + usuario + ", fechaRealizacion=" + fechaRealizacion
                + ", fechaActualizacion=" + fechaActualizacion + ", respuestas=" + respuestas + ", eventos=" + eventos
                + "]";
    }

    

    


    
}
