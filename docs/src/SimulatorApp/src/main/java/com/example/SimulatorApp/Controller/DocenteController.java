package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Entity.RespuestaTest;
import com.example.SimulatorApp.Model.Entity.TestEvaluativo;
import com.example.SimulatorApp.Model.Entity.Usuario;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/docente")
public class DocenteController {

    private final SimiladorService simuladorService;

    public DocenteController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/panel")
    public String panel(Authentication auth, Model model) {
        String correo = auth.getName();
        Usuario docente = simuladorService.buscarUsuarioPorCorreo(correo);
        if (docente == null) return "redirect:/login?error";

        List<Usuario> estudiantes = simuladorService.buscarUsuariosPorDocenteTutor(docente.getId());

        int totalTests = 0;
        int totalCorrectas = 0;
        int totalPreguntas = 0;

        for (Usuario est : estudiantes) {
            List<TestEvaluativo> tests = simuladorService.buscarTestsPorUsuario(est.getId());
            totalTests += tests.size();
            for (TestEvaluativo t : tests) {
                List<RespuestaTest> respuestas = simuladorService.buscarRespuestasPorTest(t.getId());
                totalPreguntas += respuestas.size();
                for (RespuestaTest r : respuestas) {
                    if (r.getOpcion().getEsCorrecta()) totalCorrectas++;
                }
            }
        }

        double promedio = totalPreguntas > 0 ? (double) totalCorrectas / totalPreguntas * 100 : 0;

        model.addAttribute("docente", docente);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("totalEstudiantes", estudiantes.size());
        model.addAttribute("totalTests", totalTests);
        model.addAttribute("promedioGlobal", String.format("%.1f", promedio));
        return "dashboard_docente";
    }

    @GetMapping("/estudiantes")
    public String listEstudiantes(Authentication auth, Model model) {
        String correo = auth.getName();
        Usuario docente = simuladorService.buscarUsuarioPorCorreo(correo);
        if (docente == null) return "redirect:/login?error";

        List<Usuario> estudiantes = simuladorService.buscarUsuariosPorDocenteTutor(docente.getId());

        List<Map<String, Object>> stats = new ArrayList<>();
        for (Usuario est : estudiantes) {
            Map<String, Object> s = new HashMap<>();
            s.put("estudiante", est);
            List<TestEvaluativo> tests = simuladorService.buscarTestsPorUsuario(est.getId());
            int total = 0, correctas = 0, preguntas = 0;
            for (TestEvaluativo t : tests) {
                total++;
                List<RespuestaTest> respuestas = simuladorService.buscarRespuestasPorTest(t.getId());
                preguntas += respuestas.size();
                for (RespuestaTest r : respuestas) {
                    if (r.getOpcion().getEsCorrecta()) correctas++;
                }
            }
            s.put("totalTests", total);
            s.put("promedio", preguntas > 0 ? String.format("%.1f", (double) correctas / preguntas * 100) : "N/A");
            stats.add(s);
        }

        model.addAttribute("stats", stats);
        return "docente_estudiantes";
    }

    @GetMapping("/estudiante/{id}")
    public String verEstudiante(@PathVariable Integer id, Model model) {
        Usuario estudiante = simuladorService.buscarUsuarioPorId(id);
        if (estudiante == null) return "redirect:/docente/estudiantes";

        List<TestEvaluativo> tests = simuladorService.buscarTestsPorUsuario(id);
        List<Map<String, Object>> resultados = new ArrayList<>();
        for (TestEvaluativo t : tests) {
            Map<String, Object> r = new HashMap<>();
            r.put("test", t);
            List<RespuestaTest> respuestas = simuladorService.buscarRespuestasPorTest(t.getId());
            int correctas = 0;
            for (RespuestaTest rt : respuestas) {
                if (rt.getOpcion().getEsCorrecta()) correctas++;
            }
            r.put("total", respuestas.size());
            r.put("correctas", correctas);
            r.put("porcentaje", respuestas.size() > 0 ? (correctas * 100 / respuestas.size()) : 0);
            resultados.add(r);
        }

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("resultados", resultados);
        return "docente_estudiante_detalle";
    }
}
