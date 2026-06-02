package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Dao.PreguntaTestDAOIface;
import com.example.SimulatorApp.Model.Dao.UsuarioDAOIface;
import com.example.SimulatorApp.Model.Entity.PreguntaTest;
import com.example.SimulatorApp.Model.Entity.Usuario;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private PreguntaTestDAOIface preguntaRepository;
    @Autowired
    private SimiladorService simuladorService;
    @Autowired
    private UsuarioDAOIface usuarioRepository;

    @GetMapping("/test")
    public String showTestForm(Model model) {
        List<PreguntaTest> preguntas = preguntaRepository.findAll();
        model.addAttribute("preguntas", preguntas);
        return "test";
    }

    @PostMapping("/test/submit")
    public String submitTest(@RequestParam("respuestas") List<Integer> respuestas, Authentication auth) {
        String correo = auth.getName();
        Usuario usuario = usuarioRepository.findByCorreoInstitucional(correo).orElseThrow();
        simuladorService.saveTestResult(usuario.getId(), respuestas);
        return "redirect:/dashboard";
    }
}