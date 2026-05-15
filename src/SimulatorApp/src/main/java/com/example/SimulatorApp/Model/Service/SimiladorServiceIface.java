package com.example.SimulatorApp.Model.Service;

import java.util.List;

import com.example.SimulatorApp.Model.Entity.EscenarioPhishing;
import com.example.SimulatorApp.Model.Entity.EstadoEvento;
import com.example.SimulatorApp.Model.Entity.EstadoUsuario;
import com.example.SimulatorApp.Model.Entity.Estrato;
import com.example.SimulatorApp.Model.Entity.EventoSimulacion;
import com.example.SimulatorApp.Model.Entity.FeedbackIA;
import com.example.SimulatorApp.Model.Entity.InteraccionPhishing;
import com.example.SimulatorApp.Model.Entity.OpcionPregunta;
import com.example.SimulatorApp.Model.Entity.PreguntaTest;
import com.example.SimulatorApp.Model.Entity.RespuestaTest;
import com.example.SimulatorApp.Model.Entity.Role;
import com.example.SimulatorApp.Model.Entity.TestEvaluativo;
import com.example.SimulatorApp.Model.Entity.Usuario;

public interface SimiladorServiceIface {

    // Metodos para Roles
    public List<Role> buscarRolesTodos();
    public Role guardarRole(Role role);
    public Role buscarRolePorId(Integer id);
    public void eliminarRolePorId(Integer id);

    // Metodos Usuarios
    public List<Usuario> buscarUsuariosTodos();
    public Usuario guardarUsuario(Usuario usuario);
    public Usuario buscarUsuarioPorId(Integer id);
    public void eliminarUsuarioPorId(Integer id);
    public Usuario buscarUsuarioPorCorreo(String correo);
    public List<Usuario> buscarUsuariosPorRol(Integer idRol);
    public List<Usuario> buscarUsuariosPorEstado(Integer idEstado);
    public List<Usuario> buscarUsuariosPorEstrato(Integer idEstrato);

    //Metodos TestEvaluativo
    public List<TestEvaluativo> buscarTestsTodos();
    public TestEvaluativo guardarTest(TestEvaluativo test);
    public TestEvaluativo buscarTestPorId(Integer id);
    public void eliminarTestPorId(Integer id);
    public List<TestEvaluativo> buscarTestsPorUsuario(Integer idUsuario);

    //Metodos PreguntaTest
    public List<PreguntaTest> buscarPreguntasTodas();
    public PreguntaTest guardarPregunta(PreguntaTest pregunta);
    public PreguntaTest buscarPreguntaPorId(Integer id);
    public void eliminarPreguntaPorId(Integer id);
    public List<PreguntaTest> buscarPreguntasPorNivel(Integer idNivel);

    //Metodos OpcionPregunta
    public List<OpcionPregunta> buscarOpcionesTodas();
    public OpcionPregunta guardarOpcion(OpcionPregunta opcion);
    public OpcionPregunta buscarOpcionPorId(Integer id);
    public void eliminarOpcionPorId(Integer id);
    public List<OpcionPregunta> buscarOpcionesPorPregunta(Integer idPregunta);

    //Metodos RespuestaTest
    public List<RespuestaTest> buscarRespuestasTodas();
    public RespuestaTest guardarRespuesta(RespuestaTest respuesta);
    public RespuestaTest buscarRespuestaPorId(Integer id);
    public void eliminarRespuestaPorId(Integer id);
    public List<RespuestaTest> buscarRespuestasPorTest(Integer idTest);

    //Nivel de dificultad
    public List<EscenarioPhishing> buscarEscenariosTodos();
    public EscenarioPhishing guardarEscenario(EscenarioPhishing escenario);
    public EscenarioPhishing buscarEscenarioPorId(Integer id);
    public void eliminarEscenarioPorId(Integer id);
    public List<EscenarioPhishing> buscarEscenariosPorNivel(Integer idNivel);

    //Metodos Estado Evento
    public List<EstadoEvento> buscarEstadosEventoTodos();
    public EstadoEvento guardarEstadoEvento(EstadoEvento estado);
    public EstadoEvento buscarEstadoEventoPorId(Integer id);
    public void eliminarEstadoEventoPorId(Integer id);

    //Metodos de Evento de Simulacion
    public List<EventoSimulacion> buscarEventosTodos();
    public EventoSimulacion guardarEvento(EventoSimulacion evento);
    public EventoSimulacion buscarEventoPorId(Integer id);
    public void eliminarEventoPorId(Integer id);
    public List<EventoSimulacion> buscarEventosPorUsuario(Integer idUsuario);
    public List<EventoSimulacion> buscarEventosPorEscenario(Integer idEscenario);
    public List<EventoSimulacion> buscarEventosPorEstado(Integer idEstadoEvento);
    public List<EventoSimulacion> buscarEventosPorTest(Integer idTest);


    //Metodos InteraccionPhishing
    public List<InteraccionPhishing> buscarInteraccionesTodas();
    public InteraccionPhishing guardarInteraccion(InteraccionPhishing interaccion);
    public InteraccionPhishing buscarInteraccionPorId(Integer id);
    public void eliminarInteraccionPorId(Integer id);
    public InteraccionPhishing buscarInteraccionPorEvento(Integer idEvento);


    //Metodos  FeedbackIA
    public List<FeedbackIA> buscarFeedbacksTodos();
    public FeedbackIA guardarFeedback(FeedbackIA feedback);
    public FeedbackIA buscarFeedbackPorId(Integer id);
    public void eliminarFeedbackPorId(Integer id);
    public List<FeedbackIA> buscarFeedbacksPorInteraccion(Integer idInteraccion);

    //Metodos EstadoUsuario

    public List<EstadoUsuario> buscarEstadosUsuarioTodos();
    public EstadoUsuario guardarEstadoUsuario(EstadoUsuario estado);
    public EstadoUsuario buscarEstadoUsuarioPorId(Integer id);
    public void eliminarEstadoUsuarioPorId(Integer id);

    //Metodos Estrato.

    public List<Estrato> buscarEstratosTodos();
    public Estrato guardarEstrato(Estrato estrato);
    public Estrato buscarEstratoPorId(Integer id);
    public void eliminarEstratoPorId(Integer id);

}
    

