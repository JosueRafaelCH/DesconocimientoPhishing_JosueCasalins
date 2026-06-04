package com.example.SimulatorApp.Model.Service;

import com.example.SimulatorApp.Model.Dao.*;
import com.example.SimulatorApp.Model.Entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimiladorServiceTest {

    @Mock private RoleDAOIface roleDAO;
    @Mock private UsuarioDAOIface usuarioDAO;
    @Mock private TestEvaluativoDAOIface testDAO;
    @Mock private PreguntaTestDAOIface preguntaDAO;
    @Mock private OpcionPreguntaDAOIface opcionDAO;
    @Mock private RespuestaTestDAOIface respuestaDAO;
    @Mock private EscenarioPhishingDAOIface escenarioDAO;
    @Mock private EstadoEventoDAOIface estadoEventoDAO;
    @Mock private EventoSimulacionDAOIface eventoDAO;
    @Mock private InteraccionPhishingDAOIface interaccionDAO;
    @Mock private FeedbackIADAOIface feedbackDAO;
    @Mock private EstadoUsuarioDAOIface estadoUsuarioDAO;
    @Mock private EstratoDAOIface estratoDAO;
    @Mock private NivelDificultadDAOIface nivelDAO;

    private SimiladorService service;

    @BeforeEach
    void setUp() {
        service = new SimiladorService(
            roleDAO, usuarioDAO, testDAO, preguntaDAO, opcionDAO,
            respuestaDAO, escenarioDAO, estadoEventoDAO, eventoDAO,
            interaccionDAO, feedbackDAO, estadoUsuarioDAO, estratoDAO,
            nivelDAO, new BCryptPasswordEncoder()
        );
    }

    @Test
    void register_encodesPasswordAndSaves() {
        Role rolEstudiante = new Role();
        rolEstudiante.setId(1);
        rolEstudiante.setNombreRol("Estudiante");

        EstadoUsuario estadoActivo = new EstadoUsuario();
        estadoActivo.setId(1);
        estadoActivo.setNombreEstado("Activo");

        Estrato estrato = new Estrato();
        estrato.setId(1);
        estrato.setDescripcion("Estrato 1");

        when(roleDAO.findByNombreRol("Estudiante")).thenReturn(rolEstudiante);
        when(estadoUsuarioDAO.findAll()).thenReturn(List.of(estadoActivo));
        when(estratoDAO.findAll()).thenReturn(List.of(estrato));

        Usuario input = new Usuario();
        input.setNombres("Juan");
        input.setApellidos("Perez");
        input.setCorreoInstitucional("juan@uniremington.edu.co");
        input.setContrasenaHash("Pass1234!");

        when(usuarioDAO.save(any(Usuario.class))).thenAnswer(inv -> {
            Usuario saved = inv.getArgument(0);
            saved.setId(99);
            return saved;
        });

        Usuario result = service.register(input);

        assertNotNull(result.getId());
        assertEquals(99, result.getId());
        assertNotEquals("Pass1234!", result.getContrasenaHash());
        assertTrue(result.getContrasenaHash().startsWith("$2a$"));
        assertEquals("Estudiante", result.getRol().getNombreRol());
        assertEquals("Activo", result.getEstado().getNombreEstado());
        assertNotNull(result.getFechaRegistro());
        assertNotNull(result.getFechaActualizacion());

        verify(usuarioDAO, times(1)).save(input);
    }

    @Test
    void buscarUsuarioPorCorreo_delegatesToDAO() {
        Usuario esperado = new Usuario();
        esperado.setId(1);
        esperado.setCorreoInstitucional("juan@uniremington.edu.co");

        when(usuarioDAO.findByCorreoInstitucional("juan@uniremington.edu.co"))
            .thenReturn(Optional.of(esperado));

        Usuario result = service.buscarUsuarioPorCorreo("juan@uniremington.edu.co");
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("juan@uniremington.edu.co", result.getCorreoInstitucional());
    }

    @Test
    void buscarUsuarioPorCorreo_returnsNullWhenNotFound() {
        when(usuarioDAO.findByCorreoInstitucional("noexiste@test.com"))
            .thenReturn(Optional.empty());

        assertNull(service.buscarUsuarioPorCorreo("noexiste@test.com"));
    }

    @Test
    void buscarNivelesTodos_delegatesToDAO() {
        NivelDificultad facil = new NivelDificultad();
        facil.setId(1);
        facil.setNombreNivel("Fácil");

        when(nivelDAO.findAll()).thenReturn(List.of(facil));

        List<NivelDificultad> result = service.buscarNivelesTodos();
        assertEquals(1, result.size());
        assertEquals("Fácil", result.get(0).getNombreNivel());
    }

    @Test
    void buscarPreguntasPorNivel_delegatesToDAO() {
        PreguntaTest pregunta = new PreguntaTest();
        pregunta.setId(10);
        pregunta.setEnunciado("¿Qué es phishing?");

        when(preguntaDAO.findByNivelId(1)).thenReturn(List.of(pregunta));

        List<PreguntaTest> result = service.buscarPreguntasPorNivel(1);
        assertEquals(1, result.size());
        assertEquals("¿Qué es phishing?", result.get(0).getEnunciado());
    }

    @Test
    void guardarTest_returnsSavedTestWithId() {
        TestEvaluativo test = new TestEvaluativo();
        test.setFechaRealizacion(LocalDate.now());

        when(testDAO.save(any(TestEvaluativo.class))).thenAnswer(inv -> {
            TestEvaluativo saved = inv.getArgument(0);
            saved.setId(5);
            return saved;
        });

        TestEvaluativo result = service.guardarTest(test);
        assertNotNull(result.getId());
        assertEquals(5, result.getId());
    }
}
