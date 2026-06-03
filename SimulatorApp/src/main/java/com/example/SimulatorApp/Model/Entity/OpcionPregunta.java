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
@Table(name = "Opciones_Pregunta")
public class OpcionPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opcion")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta", nullable = false)
    @JsonManagedReference
    private PreguntaTest pregunta;

    @Column(name = "texto_opcion", nullable = false, length = 500)
    private String textoOpcion;

    @Column(name = "es_correcta", nullable = false)
    private Boolean esCorrecta;

    public OpcionPregunta() {
    }

    public OpcionPregunta(Integer id, PreguntaTest pregunta, String textoOpcion, Boolean esCorrecta) {
        this.id = id;
        this.pregunta = pregunta;
        this.textoOpcion = textoOpcion;
        this.esCorrecta = esCorrecta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PreguntaTest getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaTest pregunta) {
        this.pregunta = pregunta;
    }

    public String getTextoOpcion() {
        return textoOpcion;
    }

    public void setTextoOpcion(String textoOpcion) {
        this.textoOpcion = textoOpcion;
    }

    public Boolean getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(Boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    @Override
    public String toString() {
        return "OpcionPregunta [id=" + id + ", pregunta=" + pregunta + ", textoOpcion=" + textoOpcion + ", esCorrecta="
                + esCorrecta + "]";
    }

    
}
