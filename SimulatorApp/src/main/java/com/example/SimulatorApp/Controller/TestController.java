package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Entity.*;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

/**
 * TestController
 * RF04 - Ejecucion de test de phishing
 * RF07 - Registro de desempeno (fecha, nivel, puntaje)
 * CU-02 - Realizar Test Diagnostico
 * RNF03 - Interfaz clara con instrucciones y resultados
 * RNF07 - Compatibilidad con navegadores modernos (via Bootstrap en vistas)
 */
@Controller
public class TestController {

    private final SimiladorService simuladorService;

    public TestController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/test")
    public String showTestForm(Model model) {
        model.addAttribute("niveles", simuladorService.buscarNivelesTodos());
        return "estudiante/test";
    }

    @GetMapping("/test/iniciar/{nivelId}")
    public String iniciarTest(@PathVariable Integer nivelId, Authentication auth, Model model) {
        String correo = auth.getName();
        Usuario usuario = simuladorService.buscarUsuarioPorCorreo(correo);
        if (usuario == null) return "redirect:/login?error";

        NivelDificultad nivel = simuladorService.buscarNivelPorId(nivelId);
        if (nivel == null) return "redirect:/test";

        List<PreguntaTest> preguntas = simuladorService.buscarPreguntasPorNivel(nivelId);
        if (preguntas.isEmpty()) {
            model.addAttribute("mensaje", "No hay preguntas disponibles para este nivel.");
            model.addAttribute("niveles", simuladorService.buscarNivelesTodos());
            return "estudiante/test";
        }

        TestEvaluativo test = new TestEvaluativo();
        test.setUsuario(usuario);
        test.setNivel(nivel);
        test.setFechaRealizacion(LocalDate.now());
        test.setCantidadPreguntas(preguntas.size());
        test = simuladorService.guardarTest(test);

        model.addAttribute("test", test);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("nivelNombre", nivel.getNombreNivel());
        return "estudiante/preguntas_test";
    }

    @PostMapping("/test/enviar/{testId}")
    public String enviarTest(@PathVariable Integer testId, HttpServletRequest request) {
        TestEvaluativo test = simuladorService.buscarTestPorId(testId);
        if (test == null) return "redirect:/test";

        int aciertos = 0;
        int total = 0;

        for (int i = 1; ; i++) {
            String preguntaParam = request.getParameter("preguntaId_" + i);
            if (preguntaParam == null) break;

            Integer preguntaId = Integer.parseInt(preguntaParam);
            PreguntaTest pregunta = simuladorService.buscarPreguntaPorId(preguntaId);
            if (pregunta == null) continue;

            String opcionParam = request.getParameter("pregunta_" + preguntaId);
            if (opcionParam == null) continue;

            Integer opcionId = Integer.parseInt(opcionParam);
            OpcionPregunta opcion = simuladorService.buscarOpcionPorId(opcionId);
            if (opcion == null) continue;

            RespuestaTest respuesta = new RespuestaTest();
            respuesta.setTest(test);
            respuesta.setPregunta(pregunta);
            respuesta.setOpcion(opcion);
            respuesta.setEsCorrecta(opcion.getEsCorrecta());
            simuladorService.guardarRespuesta(respuesta);

            total++;
            if (opcion.getEsCorrecta()) aciertos++;
        }

        test.setCantAciertos(aciertos);
        test.setCalificacion(total > 0 ? (double) aciertos / total * 5.0 : 0.0);
        simuladorService.guardarTest(test);

        return "redirect:/test/resultados/" + testId;
    }

    @GetMapping("/test/historial")
    public String showTestHistory(Authentication auth, Model model) {
        String correo = auth.getName();
        Usuario usuario = simuladorService.buscarUsuarioPorCorreo(correo);
        List<TestEvaluativo> tests = simuladorService.buscarTestsPorUsuario(usuario.getId());
        model.addAttribute("tests", tests);
        return "estudiante/test_historial";
    }

    @GetMapping("/test/resultados/{id}")
    public String showTestResults(@PathVariable Integer id, Model model) {
        TestEvaluativo test = simuladorService.buscarTestPorId(id);
        if (test == null) return "redirect:/test/historial";

        List<RespuestaTest> respuestas = simuladorService.buscarRespuestasPorTest(id);
        model.addAttribute("test", test);
        model.addAttribute("respuestas", respuestas);
        return "estudiante/test_resultados";
    }
}
