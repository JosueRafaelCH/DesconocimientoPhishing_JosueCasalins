package com.example.SimulatorApp.Model.Entity;

import java.time.LocalDate;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Eventos_Simulacion")
public class EventoSimulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonManagedReference
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escenario", nullable = false)
    @JsonManagedReference
    private EscenarioPhishing escenario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test", nullable = false)
    @JsonManagedReference
    private TestEvaluativo test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_evento", nullable = false)
    @JsonManagedReference
    private EstadoEvento estadoEvento;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    @OneToOne(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private InteraccionPhishing interaccion;

    public EventoSimulacion() {

    }

    public EventoSimulacion(Integer id, Usuario usuario, EscenarioPhishing escenario, TestEvaluativo test,
            EstadoEvento estadoEvento, LocalDate fechaEnvio, LocalDate fechaActualizacion,
            InteraccionPhishing interaccion) {
        this.id = id;
        this.usuario = usuario;
        this.escenario = escenario;
        this.test = test;
        this.estadoEvento = estadoEvento;
        this.fechaEnvio = fechaEnvio;
        this.fechaActualizacion = fechaActualizacion;
        this.interaccion = interaccion;
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

    public EscenarioPhishing getEscenario() {
        return escenario;
    }

    public void setEscenario(EscenarioPhishing escenario) {
        this.escenario = escenario;
    }

    public TestEvaluativo getTest() {
        return test;
    }

    public void setTest(TestEvaluativo test) {
        this.test = test;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public InteraccionPhishing getInteraccion() {
        return interaccion;
    }

    public void setInteraccion(InteraccionPhishing interaccion) {
        this.interaccion = interaccion;
    }

    @Override
    public String toString() {
        return "EventoSimulacion [id=" + id + ", usuario=" + usuario + ", escenario=" + escenario + ", test=" + test
                + ", estadoEvento=" + estadoEvento + ", fechaEnvio=" + fechaEnvio + ", fechaActualizacion="
                + fechaActualizacion + ", interaccion=" + interaccion + "]";
    }

    

    
}