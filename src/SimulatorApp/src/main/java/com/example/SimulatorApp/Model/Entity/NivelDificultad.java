package com.example.SimulatorApp.Model.Entity;

import java.math.BigDecimal;
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
@Table(name = "Niveles_Dificultad")
public class NivelDificultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel")
    private Integer id;

    @Column(name = "nombre_nivel", nullable = false, length = 50)
    private String nombreNivel;

    @Column(name = "puntaje_minimo", nullable = false)
    private BigDecimal puntajeMinimo;

    @Column(name = "puntaje_maximo", nullable = false)
    private BigDecimal puntajeMaximo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "nivel", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PreguntaTest> preguntas = new ArrayList<>();

    @OneToMany(mappedBy = "nivel", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<EscenarioPhishing> escenarios = new ArrayList<>();

    public NivelDificultad() {

    }

    

    public NivelDificultad(Integer id, String nombreNivel, BigDecimal puntajeMinimo, BigDecimal puntajeMaximo,
            String descripcion, List<PreguntaTest> preguntas, List<EscenarioPhishing> escenarios) {
        this.id = id;
        this.nombreNivel = nombreNivel;
        this.puntajeMinimo = puntajeMinimo;
        this.puntajeMaximo = puntajeMaximo;
        this.descripcion = descripcion;
        this.preguntas = preguntas;
        this.escenarios = escenarios;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public BigDecimal getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public void setPuntajeMinimo(BigDecimal puntajeMinimo) {
        this.puntajeMinimo = puntajeMinimo;
    }

    public BigDecimal getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(BigDecimal puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PreguntaTest> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaTest> preguntas) {
        this.preguntas = preguntas;
    }

    public List<EscenarioPhishing> getEscenarios() {
        return escenarios;
    }

    public void setEscenarios(List<EscenarioPhishing> escenarios) {
        this.escenarios = escenarios;
    }



    @Override
    public String toString() {
        return "NivelDificultad [id=" + id + ", nombreNivel=" + nombreNivel + ", puntajeMinimo=" + puntajeMinimo
                + ", puntajeMaximo=" + puntajeMaximo + ", descripcion=" + descripcion + ", preguntas=" + preguntas
                + ", escenarios=" + escenarios + "]";
    }

    

    
}