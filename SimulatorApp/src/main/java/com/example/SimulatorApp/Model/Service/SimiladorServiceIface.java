package com.example.SimulatorApp.Model.Service;

import java.util.List;
import com.example.SimulatorApp.Model.Entity.*;

public interface SimiladorServiceIface {
    
    // --- Custom Methods ---
    public Usuario register(Usuario usuario);
    public void saveTestResult(Integer usuarioId, List<Integer> opcionIds);

    // --- Roles ---
    public List<Role> buscarRolesTodos();
    public Role guardarRole(Role role);
    public Role buscarRolePorId(Integer id);
    public void eliminarRolePorId(Integer id);

    // --- Usuarios ---
    public List<Usuario> buscarUsuariosTodos();
    public Usuario guardarUsuario(Usuario usuario);
    public Usuario buscarUsuarioPorId(Integer id);
    public void eliminarUsuarioPorId(Integer id);
    public Usuario buscarUsuarioPorCorreo(String correo);
    public List<Usuario> buscarUsuariosPorRol(Integer idRol);
    public List<Usuario> buscarUsuariosPorEstado(Integer idEstado);
    public List<Usuario> buscarUsuariosPorEstrato(Integer idEstrato);
    public List<Usuario> buscarUsuariosPorDocenteTutor(Integer idDocente);

    // --- TestEvaluativo ---
    public List<TestEvaluativo> buscarTestsTodos();
    public TestEvaluativo guardarTest(TestEvaluativo test);
    public TestEvaluativo buscarTestPorId(Integer id);
    public void eliminarTestPorId(Integer id);
    public List<TestEvaluativo> buscarTestsPorUsuario(Integer idUsuario);

    // --- Preguntas/Opciones/Respuestas ---
    public List<PreguntaTest> buscarPreguntasTodas();
    public PreguntaTest guardarPregunta(PreguntaTest pregunta);
    public PreguntaTest buscarPreguntaPorId(Integer id);
    public void eliminarPreguntaPorId(Integer id);
    public List<PreguntaTest> buscarPreguntasPorNivel(Integer idNivel);

    public List<OpcionPregunta> buscarOpcionesTodas();
    public OpcionPregunta guardarOpcion(OpcionPregunta opcion);
    public OpcionPregunta buscarOpcionPorId(Integer id);
    public void eliminarOpcionPorId(Integer id);
    public List<OpcionPregunta> buscarOpcionesPorPregunta(Integer idPregunta);

    public List<RespuestaTest> buscarRespuestasTodas();
    public RespuestaTest guardarRespuesta(RespuestaTest respuesta);
    public RespuestaTest buscarRespuestaPorId(Integer id);
    public void eliminarRespuestaPorId(Integer id);
    public List<RespuestaTest> buscarRespuestasPorTest(Integer idTest);

    // --- Escenarios ---
    public List<EscenarioPhishing> buscarEscenariosTodos();
    public EscenarioPhishing guardarEscenario(EscenarioPhishing escenario);
    public EscenarioPhishing buscarEscenarioPorId(Integer id);
    public void eliminarEscenarioPorId(Integer id);
    public List<EscenarioPhishing> buscarEscenariosPorNivel(Integer idNivel);

    // --- Estados Evento ---
    public List<EstadoEvento> buscarEstadosEventoTodos();
    public EstadoEvento guardarEstadoEvento(EstadoEvento estado);
    public EstadoEvento buscarEstadoEventoPorId(Integer id);
    public void eliminarEstadoEventoPorId(Integer id);

    // --- EventoSimulacion ---
    public List<EventoSimulacion> buscarEventosTodos();
    public EventoSimulacion guardarEvento(EventoSimulacion evento);
    public EventoSimulacion crearAsignacion(Integer idUsuario, Integer idEscenario, Integer idEstadoEvento);
    public EventoSimulacion buscarEventoPorId(Integer id);
    public void eliminarEventoPorId(Integer id);
    public List<EventoSimulacion> buscarEventosPorUsuario(Integer idUsuario);
    public List<EventoSimulacion> buscarEventosPorEscenario(Integer idEscenario);
    public List<EventoSimulacion> buscarEventosPorEstado(Integer idEstadoEvento);
    public List<EventoSimulacion> buscarEventosPorTest(Integer idTest);

    // --- InteraccionPhishing ---
    public List<InteraccionPhishing> buscarInteraccionesTodas();
    public InteraccionPhishing guardarInteraccion(InteraccionPhishing interaccion);
    public InteraccionPhishing buscarInteraccionPorId(Integer id);
    public void eliminarInteraccionPorId(Integer id);
    public InteraccionPhishing buscarInteraccionPorEvento(Integer idEvento);

    // --- FeedbackIA ---
    public List<FeedbackIA> buscarFeedbacksTodos();
    public FeedbackIA guardarFeedback(FeedbackIA feedback);
    public FeedbackIA buscarFeedbackPorId(Integer id);
    public void eliminarFeedbackPorId(Integer id);
    public List<FeedbackIA> buscarFeedbacksPorInteraccion(Integer idInteraccion);

    // --- EstadosUsuario ---
    public List<EstadoUsuario> buscarEstadosUsuarioTodos();
    public EstadoUsuario guardarEstadoUsuario(EstadoUsuario estado);
    public EstadoUsuario buscarEstadoUsuarioPorId(Integer id);
    public void eliminarEstadoUsuarioPorId(Integer id);

    // --- Estrato ---
    public List<Estrato> buscarEstratosTodos();
    public Estrato guardarEstrato(Estrato estrato);
    public Estrato buscarEstratoPorId(Integer id);
    public void eliminarEstratoPorId(Integer id);

    // --- NivelDificultad ---
    public List<NivelDificultad> buscarNivelesTodos();
    public NivelDificultad guardarNivel(NivelDificultad nivel);
    public NivelDificultad buscarNivelPorId(Integer id);
    public void eliminarNivelPorId(Integer id);
}