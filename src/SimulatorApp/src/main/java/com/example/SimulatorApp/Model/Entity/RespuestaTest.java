package com.example.SimulatorApp.Model.Entity;

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
@Table(name = "Respuestas_Test")
public class RespuestaTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test", nullable = false)
    @JsonManagedReference
    private TestEvaluativo test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta", nullable = false)
    @JsonManagedReference
    private PreguntaTest pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_opcion", nullable = false)
    @JsonManagedReference
    private OpcionPregunta opcion;

    public RespuestaTest() {

    }

    public RespuestaTest(Integer id, TestEvaluativo test, PreguntaTest pregunta, OpcionPregunta opcion) {
        this.id = id;
        this.test = test;
        this.pregunta = pregunta;
        this.opcion = opcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TestEvaluativo getTest() {
        return test;
    }

    public void setTest(TestEvaluativo test) {
        this.test = test;
    }

    public PreguntaTest getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaTest pregunta) {
        this.pregunta = pregunta;
    }

    public OpcionPregunta getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionPregunta opcion) {
        this.opcion = opcion;
    }

    @Override
    public String toString() {
        return "RespuestaTest [id=" + id + ", test=" + test + ", pregunta=" + pregunta + ", opcion=" + opcion + "]";
    }

    

    
}