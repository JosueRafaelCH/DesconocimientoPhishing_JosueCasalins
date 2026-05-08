package com.example.SimulatorApp.Model.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "Escenarios_Phishing")
public class EscenarioPhishing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escenario")
    private Integer id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "imagen_escenario", length = 300)
    private String imagenEscenario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel", nullable = false)
    @JsonManagedReference
    private NivelDificultad nivel;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    @OneToMany(mappedBy = "escenario", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<EventoSimulacion> eventos = new ArrayList<>();

    public EscenarioPhishing() {

    }

    public EscenarioPhishing(Integer id, String titulo, String descripcion, String imagenEscenario,
            NivelDificultad nivel, LocalDate fechaActualizacion, List<EventoSimulacion> eventos) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenEscenario = imagenEscenario;
        this.nivel = nivel;
        this.fechaActualizacion = fechaActualizacion;
        this.eventos = eventos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenEscenario() {
        return imagenEscenario;
    }

    public void setImagenEscenario(String imagenEscenario) {
        this.imagenEscenario = imagenEscenario;
    }

    public NivelDificultad getNivel() {
        return nivel;
    }

    public void setNivel(NivelDificultad nivel) {
        this.nivel = nivel;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<EventoSimulacion> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoSimulacion> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "EscenarioPhishing [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion
                + ", imagenEscenario=" + imagenEscenario + ", nivel=" + nivel + ", fechaActualizacion="
                + fechaActualizacion + ", eventos=" + eventos + "]";
    }

    

    
}
