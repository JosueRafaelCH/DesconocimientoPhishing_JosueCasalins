package com.example.SimulatorApp.Model.Service;

import com.example.SimulatorApp.Model.Dao.*;
import com.example.SimulatorApp.Model.Entity.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SimiladorService implements SimiladorServiceIface {

    private final RoleDAOIface roleDAO;
    private final UsuarioDAOIface usuarioDAO;
    private final TestEvaluativoDAOIface testDAO;
    private final PreguntaTestDAOIface preguntaDAO;
    private final OpcionPreguntaDAOIface opcionDAO;
    private final RespuestaTestDAOIface respuestaDAO;
    private final EscenarioPhishingDAOIface escenarioDAO;
    private final EstadoEventoDAOIface estadoEventoDAO;
    private final EventoSimulacionDAOIface eventoDAO;
    private final InteraccionPhishingDAOIface interaccionDAO;
    private final FeedbackIADAOIface feedbackDAO;
    private final EstadoUsuarioDAOIface estadoUsuarioDAO;
    private final EstratoDAOIface estratoDAO;
    private final NivelDificultadDAOIface nivelDAO;
    private final PasswordEncoder passwordEncoder;

    public SimiladorService(RoleDAOIface roleDAO, UsuarioDAOIface usuarioDAO, TestEvaluativoDAOIface testDAO, 
                            PreguntaTestDAOIface preguntaDAO, OpcionPreguntaDAOIface opcionDAO, 
                            RespuestaTestDAOIface respuestaDAO, EscenarioPhishingDAOIface escenarioDAO, 
                            EstadoEventoDAOIface estadoEventoDAO, EventoSimulacionDAOIface eventoDAO, 
                            InteraccionPhishingDAOIface interaccionDAO, FeedbackIADAOIface feedbackDAO, 
                            EstadoUsuarioDAOIface estadoUsuarioDAO, EstratoDAOIface estratoDAO, 
                            NivelDificultadDAOIface nivelDAO, PasswordEncoder passwordEncoder) {
        this.roleDAO = roleDAO;
        this.usuarioDAO = usuarioDAO;
        this.testDAO = testDAO;
        this.preguntaDAO = preguntaDAO;
        this.opcionDAO = opcionDAO;
        this.respuestaDAO = respuestaDAO;
        this.escenarioDAO = escenarioDAO;
        this.estadoEventoDAO = estadoEventoDAO;
        this.eventoDAO = eventoDAO;
        this.interaccionDAO = interaccionDAO;
        this.feedbackDAO = feedbackDAO;
        this.estadoUsuarioDAO = estadoUsuarioDAO;
        this.estratoDAO = estratoDAO;
        this.nivelDAO = nivelDAO;
        this.passwordEncoder = passwordEncoder;
    }

    // --- Custom Methods ---
    @Override 
    @Transactional 
    public Usuario register(Usuario usuario) {
        usuario.setContrasenaHash(passwordEncoder.encode(usuario.getContrasenaHash()));
        
        if (usuario.getRol() == null) {
            usuario.setRol(roleDAO.findByNombreRol("Estudiante"));
        }
        if (usuario.getEstado() == null) {
            usuario.setEstado(estadoUsuarioDAO.findAll().stream().findFirst().orElse(null));
        }
        if (usuario.getEstrato() == null) {
            usuario.setEstrato(estratoDAO.findAll().stream().findFirst().orElse(null));
        }
        
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setFechaActualizacion(LocalDate.now());
        return usuarioDAO.save(usuario);
    }

    @Override 
    @Transactional 
    public void saveTestResult(Integer usuarioId, List<Integer> opcionIds) {
        Usuario usuario = usuarioDAO.findById(usuarioId).orElseThrow();
        TestEvaluativo test = new TestEvaluativo();
        test.setUsuario(usuario);
        test.setFechaRealizacion(LocalDate.now());
        test.setFechaActualizacion(LocalDate.now());
        test = testDAO.save(test);

        for (Integer opcionId : opcionIds) {
            OpcionPregunta opcion = opcionDAO.findById(opcionId).orElseThrow();
            RespuestaTest respuesta = new RespuestaTest();
            respuesta.setTest(test);
            respuesta.setPregunta(opcion.getPregunta());
            respuesta.setOpcion(opcion);
            respuestaDAO.save(respuesta);
        }
    }

    // --- Roles ---
    @Override 
    @Transactional(readOnly = true) 
    public List<Role> buscarRolesTodos() { 
        return roleDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public Role guardarRole(Role role) { 
        return roleDAO.save(role); 
    }

    @Override 
    @Transactional(readOnly = true) public Role buscarRolePorId(Integer id) { 
        return roleDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarRolePorId(Integer id) { 
        roleDAO.deleteById(id); 
    }

    // --- Usuarios ---
    @Override 
    @Transactional(readOnly = true) 
    public List<Usuario> buscarUsuariosTodos() {
        return usuarioDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public Usuario guardarUsuario(Usuario usuario) { 
        return usuarioDAO.save(usuario);
    }

    @Override @Transactional(readOnly = true) 
    public Usuario buscarUsuarioPorId(Integer id) { 
        return usuarioDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarUsuarioPorId(Integer id) { 
        usuarioDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public Usuario buscarUsuarioPorCorreo(String correo) { 
        return usuarioDAO.findByCorreoInstitucional(correo).orElse(null); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<Usuario> buscarUsuariosPorRol(Integer idRol) { 
        return usuarioDAO.findByRolId(idRol); 
    }

    @Override @Transactional(readOnly = true) 
    public List<Usuario> buscarUsuariosPorEstado(Integer idEstado) { 
        return usuarioDAO.findByEstadoId(idEstado); 
    }
    @Override 
    @Transactional(readOnly = true) 
    public List<Usuario> buscarUsuariosPorEstrato(Integer idEstrato) { 
        return usuarioDAO.findByEstratoId(idEstrato); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<Usuario> buscarUsuariosPorDocenteTutor(Integer idDocente) { 
        return usuarioDAO.findByDocenteTutorId(idDocente); 
    }

    // --- TestEvaluativo ---
    @Override 
    @Transactional(readOnly = true) 
    public List<TestEvaluativo> buscarTestsTodos() { 
        return testDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public TestEvaluativo guardarTest(TestEvaluativo test) { 
        return testDAO.save(test); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public TestEvaluativo buscarTestPorId(Integer id) { 
        return testDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarTestPorId(Integer id) { 
        testDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<TestEvaluativo> buscarTestsPorUsuario(Integer idUsuario) { 
        return testDAO.findByUsuarioIdOrderByFechaRealizacionDesc(idUsuario); 
    }

    // --- Preguntas/Opciones/Respuestas ---
    @Override 
    @Transactional(readOnly = true) 
    public List<PreguntaTest> buscarPreguntasTodas() { 
        return preguntaDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public PreguntaTest guardarPregunta(PreguntaTest pregunta) { 
        return preguntaDAO.save(pregunta); 
    }

    @Override @Transactional(readOnly = true) 
    public PreguntaTest buscarPreguntaPorId(Integer id) { 
        return preguntaDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarPreguntaPorId(Integer id) { 
        preguntaDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<PreguntaTest> buscarPreguntasPorNivel(Integer idNivel) { 
        return preguntaDAO.findByNivelId(idNivel); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<OpcionPregunta> buscarOpcionesTodas() { 
        return opcionDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public OpcionPregunta guardarOpcion(OpcionPregunta opcion) { 
        return opcionDAO.save(opcion); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public OpcionPregunta buscarOpcionPorId(Integer id) { 
        return opcionDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarOpcionPorId(Integer id) { 
        opcionDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<OpcionPregunta> buscarOpcionesPorPregunta(Integer idPregunta) { 
        return opcionDAO.findByPreguntaId(idPregunta); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<RespuestaTest> buscarRespuestasTodas() { 
        return respuestaDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public RespuestaTest guardarRespuesta(RespuestaTest respuesta) { 
        return respuestaDAO.save(respuesta); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public RespuestaTest buscarRespuestaPorId(Integer id) { 
        return respuestaDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarRespuestaPorId(Integer id) { 
        respuestaDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<RespuestaTest> buscarRespuestasPorTest(Integer idTest) { 
        return respuestaDAO.findByTestId(idTest); 
    }


    // --- Escenarios ---
    @Override 
    @Transactional(readOnly = true) 
    public List<EscenarioPhishing> buscarEscenariosTodos() { 
        return escenarioDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public EscenarioPhishing guardarEscenario(EscenarioPhishing escenario) { 
        return escenarioDAO.save(escenario); 
    }

    @Override
    @Transactional(readOnly = true) 
    public EscenarioPhishing buscarEscenarioPorId(Integer id) { 
        return escenarioDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarEscenarioPorId(Integer id) { 
        escenarioDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<EscenarioPhishing> buscarEscenariosPorNivel(Integer idNivel) { 
        return escenarioDAO.findByNivelId(idNivel); 
    }

    // --- Estados Evento ---
    @Override 
    @Transactional(readOnly = true) 
    public List<EstadoEvento> buscarEstadosEventoTodos() { 
        return estadoEventoDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public EstadoEvento guardarEstadoEvento(EstadoEvento estado) { 
        return estadoEventoDAO.save(estado); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public EstadoEvento buscarEstadoEventoPorId(Integer id) { 
        return estadoEventoDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarEstadoEventoPorId(Integer id) { 
        estadoEventoDAO.deleteById(id); 
    }


    // --- EventoSimulacion ---
    @Override 
    @Transactional(readOnly = true) 
    public List<EventoSimulacion> buscarEventosTodos() { 
        return eventoDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public EventoSimulacion guardarEvento(EventoSimulacion evento) { 
        return eventoDAO.save(evento); 
    }

    @Override
    @Transactional
    public EventoSimulacion crearAsignacion(Integer idUsuario, Integer idEscenario, Integer idEstadoEvento) {
        Usuario usuario = usuarioDAO.findById(idUsuario).orElse(null);
        EscenarioPhishing escenario = escenarioDAO.findById(idEscenario).orElse(null);
        EstadoEvento estadoEvento = estadoEventoDAO.findById(idEstadoEvento).orElse(null);
        if (usuario == null || escenario == null || estadoEvento == null) return null;
        EventoSimulacion evento = new EventoSimulacion();
        evento.setUsuario(usuario);
        evento.setEscenario(escenario);
        evento.setEstadoEvento(estadoEvento);
        evento.setFechaEnvio(LocalDate.now());
        evento.setFechaActualizacion(LocalDate.now());
        return eventoDAO.save(evento);
    }

    @Override 
    @Transactional(readOnly = true) 
    public EventoSimulacion buscarEventoPorId(Integer id) { 
        return eventoDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarEventoPorId(Integer id) { 
        eventoDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<EventoSimulacion> buscarEventosPorUsuario(Integer idUsuario) { 
        return eventoDAO.findByUsuarioId(idUsuario); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<EventoSimulacion> buscarEventosPorEscenario(Integer idEscenario) { 
        return eventoDAO.findByEscenarioId(idEscenario); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<EventoSimulacion> buscarEventosPorEstado(Integer idEstadoEvento) { 
        return eventoDAO.findByEstadoEventoId(idEstadoEvento); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<EventoSimulacion> buscarEventosPorTest(Integer idTest) { 
        return eventoDAO.findByTestId(idTest); 
    }


    // --- InteraccionPhishing ---
    @Override 
    @Transactional(readOnly = true) 
    public List<InteraccionPhishing> buscarInteraccionesTodas() { 
        return interaccionDAO.findAll(); 
    }

    @Override @Transactional 
    public InteraccionPhishing guardarInteraccion(InteraccionPhishing interaccion) { 
        return interaccionDAO.save(interaccion); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public InteraccionPhishing buscarInteraccionPorId(Integer id) { 
        return interaccionDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarInteraccionPorId(Integer id) { 
        interaccionDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public InteraccionPhishing buscarInteraccionPorEvento(Integer idEvento) { 
        return interaccionDAO.findByEventoId(idEvento).orElse(null); 
    }

    // --- FeedbackIA ---
    @Override 
    @Transactional(readOnly = true) 
    public List<FeedbackIA> buscarFeedbacksTodos() { 
        return feedbackDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public FeedbackIA guardarFeedback(FeedbackIA feedback) { 
        return feedbackDAO.save(feedback); 
    }

    @Override @Transactional(readOnly = true) 
    public FeedbackIA buscarFeedbackPorId(Integer id) { 
        return feedbackDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarFeedbackPorId(Integer id) { 
        feedbackDAO.deleteById(id); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public List<FeedbackIA> buscarFeedbacksPorInteraccion(Integer idInteraccion) { 
        return feedbackDAO.findByInteraccionId(idInteraccion); 
    }


    // --- EstadosUsuario ---
    @Override 
    @Transactional(readOnly = true) 
    public List<EstadoUsuario> buscarEstadosUsuarioTodos() { 
        return estadoUsuarioDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public EstadoUsuario guardarEstadoUsuario(EstadoUsuario estado) { 
        return estadoUsuarioDAO.save(estado); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public EstadoUsuario buscarEstadoUsuarioPorId(Integer id) { 
        return estadoUsuarioDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarEstadoUsuarioPorId(Integer id) { 
        estadoUsuarioDAO.deleteById(id); 
    }

    // --- Estrato ---
    @Override 
    @Transactional(readOnly = true) 
    public List<Estrato> buscarEstratosTodos() { 
        return estratoDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public Estrato guardarEstrato(Estrato estrato) { 
        return estratoDAO.save(estrato); 
    }

    @Override 
    @Transactional(readOnly = true) 
    public Estrato buscarEstratoPorId(Integer id) { 
        return estratoDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarEstratoPorId(Integer id) { 
        estratoDAO.deleteById(id); 
    }

    // --- NivelDificultad ---
    @Override 
    @Transactional(readOnly = true) 
    public List<NivelDificultad> buscarNivelesTodos() { 
        return nivelDAO.findAll(); 
    }

    @Override 
    @Transactional 
    public NivelDificultad guardarNivel(NivelDificultad nivel) { 
        return nivelDAO.save(nivel); 
    }


    @Override 
    @Transactional(readOnly = true) 
    public NivelDificultad buscarNivelPorId(Integer id) { 
        return nivelDAO.findById(id).orElse(null); 
    }

    @Override 
    @Transactional 
    public void eliminarNivelPorId(Integer id) { 
        nivelDAO.deleteById(id); 
    }
    
}