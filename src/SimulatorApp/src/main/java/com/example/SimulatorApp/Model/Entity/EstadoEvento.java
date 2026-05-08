package com.example.SimulatorApp.Model.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Estados_Evento")
public class EstadoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_evento")
    private Integer id;

    @Column(name = "nombre_estado", nullable = false, length = 50)
    private String nombreEstado;

    @OneToMany(mappedBy = "estadoEvento", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<EventoSimulacion> eventos = new ArrayList<>();

    public EstadoEvento() {

    }

    public EstadoEvento(Integer id, String nombreEstado, List<EventoSimulacion> eventos) {
        this.id = id;
        this.nombreEstado = nombreEstado;
        this.eventos = eventos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public List<EventoSimulacion> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoSimulacion> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "EstadoEvento [id=" + id + ", nombreEstado=" + nombreEstado + ", eventos=" + eventos + "]";
    }

    

    
}
