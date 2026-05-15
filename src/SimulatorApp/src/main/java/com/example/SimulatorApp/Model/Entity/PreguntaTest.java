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
@Table(name = "Preguntas_Test")
public class PreguntaTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String enunciado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel", nullable = false)
    @JsonManagedReference
    private NivelDificultad nivel;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<OpcionPregunta> opciones = new ArrayList<>();

    public PreguntaTest() {
        opciones = new ArrayList<>();
    }

    public PreguntaTest(Integer id, String enunciado, NivelDificultad nivel, LocalDate fechaActualizacion,
            List<OpcionPregunta> opciones) {
        this.id = id;
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.fechaActualizacion = fechaActualizacion;
        this.opciones = opciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
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

    public List<OpcionPregunta> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionPregunta> opciones) {
        this.opciones = opciones;
    }

    @Override
    public String toString() {
        return "PreguntaTest [id=" + id + ", enunciado=" + enunciado + ", nivel=" + nivel + ", fechaActualizacion="
                + fechaActualizacion + ", opciones=" + opciones + "]";
    }

    
}
