package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Dao.UsuarioDAOIface;
import com.example.SimulatorApp.Model.Entity.RespuestaTest;
import com.example.SimulatorApp.Model.Entity.TestEvaluativo;
import com.example.SimulatorApp.Model.Entity.Usuario;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    private final SimiladorService simuladorService;

    public TestController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/test")
    public String showTestForm(Model model) {
        model.addAttribute("preguntas", simuladorService.buscarPreguntasTodas());
        return "test";
    }

    @PostMapping("/test/submit")
    public String submitTest(HttpServletRequest request, Authentication auth) {
        String correo = auth.getName();
        Usuario usuario = simuladorService.buscarUsuarioPorCorreo(correo);
        if (usuario == null) return "redirect:/login?error";

        List<Integer> opcionIds = new ArrayList<>();
        for (int i = 0; ; i++) {
            String val = request.getParameter("respuestas[" + i + "]");
            if (val == null) break;
            opcionIds.add(Integer.parseInt(val));
        }

        simuladorService.saveTestResult(usuario.getId(), opcionIds);
        return "redirect:/test/historial";
    }

    @GetMapping("/test/historial")
    public String showTestHistory(Authentication auth, Model model) {
        String correo = auth.getName();
        Usuario usuario = simuladorService.buscarUsuarioPorCorreo(correo);
        List<TestEvaluativo> tests = simuladorService.buscarTestsPorUsuario(usuario.getId());
        model.addAttribute("tests", tests);
        return "test_historial";
    }

    @GetMapping("/test/resultados/{id}")
    public String showTestResults(@PathVariable Integer id, Model model) {
        TestEvaluativo test = simuladorService.buscarTestPorId(id);
        List<RespuestaTest> respuestas = simuladorService.buscarRespuestasPorTest(id);

        int correctas = 0;
        for (RespuestaTest r : respuestas) {
            if (r.getOpcion().getEsCorrecta()) {
                correctas++;
            }
        }

        model.addAttribute("test", test);
        model.addAttribute("respuestas", respuestas);
        model.addAttribute("total", respuestas.size());
        model.addAttribute("correctas", correctas);
        model.addAttribute("porcentaje", respuestas.size() > 0 ? (correctas * 100 / respuestas.size()) : 0);
        return "test_resultados";
    }
}
