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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel")
    @JsonManagedReference
    private NivelDificultad nivel;

    @Column(name = "fecha_realizacion", nullable = false)
    private LocalDate fechaRealizacion;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    @Column(name = "cantidad_preguntas")
    private Integer cantidadPreguntas;

    @Column(name = "cant_aciertos")
    private Integer cantAciertos;

    @Column(name = "calificacion")
    private Double calificacion;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RespuestaTest> respuestas = new ArrayList<>();

    public TestEvaluativo() {
        respuestas = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public NivelDificultad getNivel() { return nivel; }
    public void setNivel(NivelDificultad nivel) { this.nivel = nivel; }
    public LocalDate getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(LocalDate fechaRealizacion) { this.fechaRealizacion = fechaRealizacion; }
    public LocalDate getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDate fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
    public Integer getCantidadPreguntas() { return cantidadPreguntas; }
    public void setCantidadPreguntas(Integer cantidadPreguntas) { this.cantidadPreguntas = cantidadPreguntas; }
    public Integer getCantAciertos() { return cantAciertos; }
    public void setCantAciertos(Integer cantAciertos) { this.cantAciertos = cantAciertos; }
    public Double getCalificacion() { return calificacion; }
    public void setCalificacion(Double calificacion) { this.calificacion = calificacion; }
    public List<RespuestaTest> getRespuestas() { return respuestas; }
    public void setRespuestas(List<RespuestaTest> respuestas) { this.respuestas = respuestas; }
}
