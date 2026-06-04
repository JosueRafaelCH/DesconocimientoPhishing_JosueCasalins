package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Entity.*;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * AdminController
 * RF04 - Gestion de escenarios de phishing (CRUD)
 * RF10 - Asignacion de simulaciones a estudiantes
 * CU-03 - Gestionar Escenarios de Phishing
 * CU-04 - Desplegar Simulacion de Phishing (asignacion)
 * RNF03 - Interfaz de administracion con formularios claros
 * RNF07 - Compatibilidad con navegadores modernos
 * RNF09 - Arquitectura MVC con capas DAO/Service/Controller
 */
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
        return "admin/panel";
    }

    // --- Usuarios ---
    @GetMapping("/usuarios")
    public String listUsuarios(Model model) {
        model.addAttribute("usuarios", simuladorService.buscarUsuariosTodos());
        return "admin/usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuarioForm(Model model) {
        model.addAttribute("roles", simuladorService.buscarRolesTodos());
        model.addAttribute("estados", simuladorService.buscarEstadosUsuarioTodos());
        model.addAttribute("estratos", simuladorService.buscarEstratosTodos());
        model.addAttribute("docentes", simuladorService.buscarUsuariosPorRol(2));
        model.addAttribute("usuario", new Usuario());
        return "admin/usuario_form";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@RequestParam String nombres, @RequestParam String apellidos,
                                  @RequestParam String correo, @RequestParam String contrasena,
                                  @RequestParam Integer idRol, @RequestParam(required = false) Integer idEstado,
                                  @RequestParam(required = false) Integer idEstrato,
                                  @RequestParam(required = false) Integer idDocenteTutor) {
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setCorreoInstitucional(correo);
        usuario.setContrasenaHash(contrasena);
        usuario.setRol(simuladorService.buscarRolePorId(idRol));
        usuario.setEstado(idEstado != null ? simuladorService.buscarEstadoUsuarioPorId(idEstado) : null);
        usuario.setEstrato(idEstrato != null && idEstrato > 0 ? simuladorService.buscarEstratoPorId(idEstrato) : null);
        if (idDocenteTutor != null && idDocenteTutor > 0) {
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
        return "admin/usuario_form";
    }

    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuario(@RequestParam Integer id, @RequestParam String nombres,
                                     @RequestParam String apellidos, @RequestParam String correo,
                                     @RequestParam Integer idRol, @RequestParam(required = false) Integer idEstado,
                                     @RequestParam(required = false) Integer idEstrato,
                                     @RequestParam(required = false) Integer idDocenteTutor) {
        Usuario usuario = simuladorService.buscarUsuarioPorId(id);
        if (usuario != null) {
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setCorreoInstitucional(correo);
            usuario.setRol(simuladorService.buscarRolePorId(idRol));
            usuario.setEstado(idEstado != null ? simuladorService.buscarEstadoUsuarioPorId(idEstado) : null);
            usuario.setEstrato(idEstrato != null && idEstrato > 0 ? simuladorService.buscarEstratoPorId(idEstrato) : null);
            if (idDocenteTutor != null && idDocenteTutor > 0) {
                usuario.setDocenteTutor(simuladorService.buscarUsuarioPorId(idDocenteTutor));
            } else {
                usuario.setDocenteTutor(null);
            }
            usuario.setFechaActualizacion(LocalDate.now());
            simuladorService.guardarUsuario(usuario);
        }
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        simuladorService.eliminarUsuarioPorId(id);
        return "redirect:/admin/usuarios";
    }

    // --- Preguntas ---
    @GetMapping("/preguntas")
    public String listPreguntas(Model model) {
        model.addAttribute("preguntas", simuladorService.buscarPreguntasTodas());
        return "admin/preguntas";
    }

    @GetMapping("/preguntas/nueva")
    public String nuevaPreguntaForm(Model model) {
        model.addAttribute("niveles", simuladorService.buscarNivelesTodos());
        model.addAttribute("pregunta", new PreguntaTest());
        return "admin/pregunta_form";
    }

    @PostMapping("/preguntas/guardar")
    public String guardarPregunta(@RequestParam String enunciado, @RequestParam Integer idNivel,
                                   @RequestParam("opcion_1") String op1,
                                   @RequestParam("opcion_2") String op2,
                                   @RequestParam("opcion_3") String op3,
                                   @RequestParam("opcion_4") String op4,
                                   @RequestParam Integer correcta) {
        NivelDificultad nivel = simuladorService.buscarNivelPorId(idNivel);
        if (nivel == null) return "redirect:/admin/preguntas";

        PreguntaTest pregunta = new PreguntaTest();
        pregunta.setEnunciado(enunciado);
        pregunta.setNivel(nivel);
        pregunta.setFechaActualizacion(LocalDate.now());
        pregunta = simuladorService.guardarPregunta(pregunta);

        String[] textos = {op1, op2, op3, op4};
        for (int i = 0; i < textos.length; i++) {
            if (textos[i] == null || textos[i].trim().isEmpty()) continue;
            OpcionPregunta opcion = new OpcionPregunta();
            opcion.setTextoOpcion(textos[i]);
            opcion.setEsCorrecta((i + 1) == correcta);
            opcion.setPregunta(pregunta);
            simuladorService.guardarOpcion(opcion);
        }
        return "redirect:/admin/preguntas";
    }

    @GetMapping("/preguntas/editar/{id}")
    public String editarPreguntaForm(@PathVariable Integer id, Model model) {
        model.addAttribute("pregunta", simuladorService.buscarPreguntaPorId(id));
        model.addAttribute("niveles", simuladorService.buscarNivelesTodos());
        return "admin/pregunta_form";
    }

    @PostMapping("/preguntas/actualizar")
    public String actualizarPregunta(@RequestParam Integer id, @RequestParam String enunciado,
                                      @RequestParam Integer idNivel,
                                      @RequestParam("opcion_1") String op1,
                                      @RequestParam("opcion_2") String op2,
                                      @RequestParam("opcion_3") String op3,
                                      @RequestParam("opcion_4") String op4,
                                      @RequestParam Integer correcta) {
        PreguntaTest pregunta = simuladorService.buscarPreguntaPorId(id);
        if (pregunta == null) return "redirect:/admin/preguntas";

        pregunta.setEnunciado(enunciado);
        pregunta.setNivel(simuladorService.buscarNivelPorId(idNivel));
        pregunta.setFechaActualizacion(LocalDate.now());
        simuladorService.guardarPregunta(pregunta);

        for (OpcionPregunta oldOp : pregunta.getOpciones()) {
            simuladorService.eliminarOpcionPorId(oldOp.getId());
        }

        String[] textos = {op1, op2, op3, op4};
        for (int i = 0; i < textos.length; i++) {
            if (textos[i] == null || textos[i].trim().isEmpty()) continue;
            OpcionPregunta opcion = new OpcionPregunta();
            opcion.setTextoOpcion(textos[i]);
            opcion.setEsCorrecta((i + 1) == correcta);
            opcion.setPregunta(pregunta);
            simuladorService.guardarOpcion(opcion);
        }
        return "redirect:/admin/preguntas";
    }

    @GetMapping("/preguntas/eliminar/{id}")
    public String eliminarPregunta(@PathVariable Integer id) {
        simuladorService.eliminarPreguntaPorId(id);
        return "redirect:/admin/preguntas";
    }

    // --- Escenarios ---
    @GetMapping("/escenarios")
    public String listEscenarios(Model model) {
        model.addAttribute("escenarios", simuladorService.buscarEscenariosTodos());
        return "admin/escenarios";
    }

    @GetMapping("/escenarios/nuevo")
    public String nuevoEscenarioForm(Model model) {
        model.addAttribute("niveles", simuladorService.buscarNivelesTodos());
        model.addAttribute("escenario", new EscenarioPhishing());
        return "admin/escenario_form";
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

    @GetMapping("/escenarios/editar/{id}")
    public String editarEscenarioForm(@PathVariable Integer id, Model model) {
        model.addAttribute("escenario", simuladorService.buscarEscenarioPorId(id));
        model.addAttribute("niveles", simuladorService.buscarNivelesTodos());
        return "admin/escenario_form";
    }

    @PostMapping("/escenarios/actualizar")
    public String actualizarEscenario(@RequestParam Integer id, @RequestParam String titulo,
                                       @RequestParam String descripcion, @RequestParam Integer idNivel) {
        EscenarioPhishing escenario = simuladorService.buscarEscenarioPorId(id);
        if (escenario != null) {
            escenario.setTitulo(titulo);
            escenario.setDescripcion(descripcion);
            escenario.setNivel(simuladorService.buscarNivelPorId(idNivel));
            escenario.setFechaActualizacion(LocalDate.now());
            simuladorService.guardarEscenario(escenario);
        }
        return "redirect:/admin/escenarios";
    }

    @GetMapping("/escenarios/eliminar/{id}")
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
        model.addAttribute("eventos", simuladorService.buscarEventosTodos());
        return "admin/asignar";
    }

    @PostMapping("/asignar/guardar")
    public String guardarAsignacion(@RequestParam Integer idUsuario, @RequestParam Integer idEscenario,
                                     @RequestParam Integer idEstadoEvento) {
        EventoSimulacion evento = new EventoSimulacion();
        evento.setUsuario(simuladorService.buscarUsuarioPorId(idUsuario));
        evento.setEscenario(simuladorService.buscarEscenarioPorId(idEscenario));
        evento.setEstadoEvento(simuladorService.buscarEstadoEventoPorId(idEstadoEvento));
        evento.setFechaEnvio(LocalDate.now());
        evento.setFechaActualizacion(LocalDate.now());
        simuladorService.guardarEvento(evento);
        return "redirect:/admin/asignar";
    }
}
