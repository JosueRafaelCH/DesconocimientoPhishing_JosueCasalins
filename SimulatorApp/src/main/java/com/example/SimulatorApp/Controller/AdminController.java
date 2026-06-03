package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Entity.*;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final SimiladorService simuladorService;

    public AdminController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("totalUsuarios", simuladorService.buscarUsuariosTodos().size());
        model.addAttribute("totalPreguntas", simuladorService.buscarPreguntasTodas().size());
        model.addAttribute("totalEscenarios", simuladorService.buscarEscenariosTodos().size());
        model.addAttribute("totalTests", simuladorService.buscarTestsTodos().size());
        return "admin";
    }

    // --- Usuarios ---
    @GetMapping("/usuarios")
    public String listUsuarios(Model model) {
        model.addAttribute("usuarios", simuladorService.buscarUsuariosTodos());
        return "admin_usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuarioForm(Model model) {
        model.addAttribute("roles", simuladorService.buscarRolesTodos());
        model.addAttribute("estados", simuladorService.buscarEstadosUsuarioTodos());
        model.addAttribute("estratos", simuladorService.buscarEstratosTodos());
        model.addAttribute("docentes", simuladorService.buscarUsuariosPorRol(2));
        return "admin_usuario_form";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@RequestParam String nombres, @RequestParam String apellidos,
                                  @RequestParam String correo, @RequestParam String contrasena,
                                  @RequestParam Integer idRol, @RequestParam Integer idEstado,
                                  @RequestParam Integer idEstrato,
                                  @RequestParam(required = false) Integer idDocenteTutor) {
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setCorreoInstitucional(correo);
        usuario.setContrasenaHash(contrasena);
        usuario.setRol(simuladorService.buscarRolePorId(idRol));
        usuario.setEstado(simuladorService.buscarEstadoUsuarioPorId(idEstado));
        usuario.setEstrato(simuladorService.buscarEstratoPorId(idEstrato));
        if (idDocenteTutor != null) {
            usuario.setDocenteTutor(simuladorService.buscarUsuarioPorId(idDocenteTutor));
        }
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setFechaActualizacion(LocalDate.now());
        simuladorService.register(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuarioForm(@PathVariable Integer id, Model model) {
        model.addAttribute("usuario", simuladorService.buscarUsuarioPorId(id));
        model.addAttribute("roles", simuladorService.buscarRolesTodos());
        model.addAttribute("estados", simuladorService.buscarEstadosUsuarioTodos());
        model.addAttribute("estratos", simuladorService.buscarEstratosTodos());
        model.addAttribute("docentes", simuladorService.buscarUsuariosPorRol(2));
        return "admin_usuario_form";
    }

    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuario(@RequestParam Integer id, @RequestParam String nombres,
                                     @RequestParam String apellidos, @RequestParam String correo,
                                     @RequestParam Integer idRol, @RequestParam Integer idEstado,
                                     @RequestParam Integer idEstrato,
                                     @RequestParam(required = false) Integer idDocenteTutor) {
        Usuario usuario = simuladorService.buscarUsuarioPorId(id);
        if (usuario != null) {
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setCorreoInstitucional(correo);
            usuario.setRol(simuladorService.buscarRolePorId(idRol));
            usuario.setEstado(simuladorService.buscarEstadoUsuarioPorId(idEstado));
            usuario.setEstrato(simuladorService.buscarEstratoPorId(idEstrato));
            if (idDocenteTutor != null) {
                usuario.setDocenteTutor(simuladorService.buscarUsuarioPorId(idDocenteTutor));
            } else {
                usuario.setDocenteTutor(null);
            }
            usuario.setFechaActualizacion(LocalDate.now());
            simuladorService.guardarUsuario(usuario);
        }
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        simuladorService.eliminarUsuarioPorId(id);
        return "redirect:/admin/usuarios";
    }

    // --- Preguntas ---
    @GetMapping("/preguntas")
    public String listPreguntas(Model model) {
        model.addAttribute("preguntas", simuladorService.buscarPreguntasTodas());
        return "admin_preguntas";
    }

    @GetMapping("/preguntas/nueva")
    public String nuevaPreguntaForm(Model model) {
        model.addAttribute("niveles", simuladorService.buscarNivelesTodos());
        return "admin_pregunta_form";
    }

    @PostMapping("/preguntas/guardar")
    public String guardarPregunta(@RequestParam String enunciado, @RequestParam Integer idNivel,
                                   @RequestParam("opcionTexto") List<String> opcionTexto,
                                   @RequestParam("opcionCorrecta") Integer opcionCorrecta) {
        NivelDificultad nivel = simuladorService.buscarNivelPorId(idNivel);
        if (nivel == null) return "redirect:/admin/preguntas";

        PreguntaTest pregunta = new PreguntaTest();
        pregunta.setEnunciado(enunciado);
        pregunta.setNivel(nivel);
        pregunta.setFechaActualizacion(LocalDate.now());
        pregunta = simuladorService.guardarPregunta(pregunta);

        for (int i = 0; i < opcionTexto.size(); i++) {
            OpcionPregunta opcion = new OpcionPregunta();
            opcion.setTextoOpcion(opcionTexto.get(i));
            opcion.setEsCorrecta(i == opcionCorrecta);
            opcion.setPregunta(pregunta);
            simuladorService.guardarOpcion(opcion);
        }
        return "redirect:/admin/preguntas";
    }

    @PostMapping("/preguntas/eliminar/{id}")
    public String eliminarPregunta(@PathVariable Integer id) {
        simuladorService.eliminarPreguntaPorId(id);
        return "redirect:/admin/preguntas";
    }

    // --- Escenarios ---
    @GetMapping("/escenarios")
    public String listEscenarios(Model model) {
        model.addAttribute("escenarios", simuladorService.buscarEscenariosTodos());
        return "admin_escenarios";
    }

    @GetMapping("/escenarios/nuevo")
    public String nuevoEscenarioForm(Model model) {
        model.addAttribute("niveles", simuladorService.buscarNivelesTodos());
        return "admin_escenario_form";
    }

    @PostMapping("/escenarios/guardar")
    public String guardarEscenario(@RequestParam String titulo, @RequestParam String descripcion,
                                    @RequestParam Integer idNivel) {
        NivelDificultad nivel = simuladorService.buscarNivelPorId(idNivel);
        if (nivel == null) return "redirect:/admin/escenarios";

        EscenarioPhishing escenario = new EscenarioPhishing();
        escenario.setTitulo(titulo);
        escenario.setDescripcion(descripcion);
        escenario.setNivel(nivel);
        escenario.setFechaActualizacion(LocalDate.now());
        simuladorService.guardarEscenario(escenario);
        return "redirect:/admin/escenarios";
    }

    @PostMapping("/escenarios/eliminar/{id}")
    public String eliminarEscenario(@PathVariable Integer id) {
        simuladorService.eliminarEscenarioPorId(id);
        return "redirect:/admin/escenarios";
    }

    // --- Asignar Simulacion ---
    @GetMapping("/asignar")
    public String asignarSimulacionForm(Model model) {
        model.addAttribute("usuarios", simuladorService.buscarUsuariosTodos());
        model.addAttribute("escenarios", simuladorService.buscarEscenariosTodos());
        model.addAttribute("estadosEvento", simuladorService.buscarEstadosEventoTodos());
        model.addAttribute("tests", simuladorService.buscarTestsTodos());
        return "admin_asignar";
    }

    @PostMapping("/asignar/guardar")
    public String guardarAsignacion(@RequestParam Integer idUsuario, @RequestParam Integer idEscenario,
                                     @RequestParam Integer idEstadoEvento) {
        EventoSimulacion evento = new EventoSimulacion();
        evento.setUsuario(simuladorService.buscarUsuarioPorId(idUsuario));
        evento.setEscenario(simuladorService.buscarEscenarioPorId(idEscenario));
        evento.setEstadoEvento(simuladorService.buscarEstadoEventoPorId(idEstadoEvento));
        evento.setTest(simuladorService.buscarTestsTodos().isEmpty() ? null : simuladorService.buscarTestsTodos().get(0));
        evento.setFechaEnvio(LocalDate.now());
        evento.setFechaActualizacion(LocalDate.now());
        simuladorService.guardarEvento(evento);
        return "redirect:/admin/asignar";
    }
}
