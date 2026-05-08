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
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(name = "correo_institucional", nullable = false, length = 150, unique = true)
    private String correoInstitucional;

    @Column(name = "contrasena_hash", nullable = false, length = 255)
    private String contrasenaHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    @JsonManagedReference
    private Role rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    @JsonManagedReference
    private EstadoUsuario estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estrato", nullable = false)
    @JsonManagedReference
    private Estrato estrato;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<TestEvaluativo> tests = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<EventoSimulacion> eventos = new ArrayList<>();

    public Usuario() {

    }

    public Usuario(Integer id, String nombres, String apellidos, String correoInstitucional, String contrasenaHash,
            Role rol, EstadoUsuario estado, Estrato estrato, LocalDate fechaRegistro, LocalDate fechaActualizacion,
            List<TestEvaluativo> tests, List<EventoSimulacion> eventos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoInstitucional = correoInstitucional;
        this.contrasenaHash = contrasenaHash;
        this.rol = rol;
        this.estado = estado;
        this.estrato = estrato;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.tests = tests;
        this.eventos = eventos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public Estrato getEstrato() {
        return estrato;
    }

    public void setEstrato(Estrato estrato) {
        this.estrato = estrato;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<TestEvaluativo> getTests() {
        return tests;
    }

    public void setTests(List<TestEvaluativo> tests) {
        this.tests = tests;
    }

    public List<EventoSimulacion> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoSimulacion> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correoInstitucional="
                + correoInstitucional + ", contrasenaHash=" + contrasenaHash + ", rol=" + rol + ", estado=" + estado
                + ", estrato=" + estrato + ", fechaRegistro=" + fechaRegistro + ", fechaActualizacion="
                + fechaActualizacion + ", tests=" + tests + ", eventos=" + eventos + "]";
    }

    

    
}