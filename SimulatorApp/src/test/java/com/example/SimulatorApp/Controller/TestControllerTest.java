package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Entity.*;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestControllerTest {

    @Mock private SimiladorService simuladorService;
    @Mock private Authentication auth;
    @Mock private Model model;

    @InjectMocks
    private TestController controller;

    @Test
    void showTestForm_addsLevelsToModel() {
        NivelDificultad facil = new NivelDificultad();
        facil.setId(1);
        facil.setNombreNivel("Fácil");

        when(simuladorService.buscarNivelesTodos()).thenReturn(List.of(facil));

        String view = controller.showTestForm(model);

        assertEquals("estudiante/test", view);
        verify(model).addAttribute("niveles", List.of(facil));
    }

    @Test
    void showTestForm_returnsCorrectView() {
        when(simuladorService.buscarNivelesTodos()).thenReturn(List.of());

        String view = controller.showTestForm(model);

        assertEquals("estudiante/test", view);
    }

    @Test
    void iniciarTest_redirectsToLoginWhenUserNotFound() {
        when(auth.getName()).thenReturn("user@test.com");
        when(simuladorService.buscarUsuarioPorCorreo("user@test.com")).thenReturn(null);

        String view = controller.iniciarTest(1, auth, model);

        assertEquals("redirect:/login?error", view);
    }

    @Test
    void iniciarTest_redirectsToTestWhenLevelNotFound() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(auth.getName()).thenReturn("user@test.com");
        when(simuladorService.buscarUsuarioPorCorreo("user@test.com")).thenReturn(usuario);
        when(simuladorService.buscarNivelPorId(999)).thenReturn(null);

        String view = controller.iniciarTest(999, auth, model);

        assertEquals("redirect:/test", view);
    }

    @Test
    void iniciarTest_returnsTestWithMessageWhenNoQuestions() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        NivelDificultad nivel = new NivelDificultad();
        nivel.setId(1);

        when(auth.getName()).thenReturn("user@test.com");
        when(simuladorService.buscarUsuarioPorCorreo("user@test.com")).thenReturn(usuario);
        when(simuladorService.buscarNivelPorId(1)).thenReturn(nivel);
        when(simuladorService.buscarPreguntasPorNivel(1)).thenReturn(List.of());
        when(simuladorService.buscarNivelesTodos()).thenReturn(List.of());

        String view = controller.iniciarTest(1, auth, model);

        assertEquals("estudiante/test", view);
        verify(model).addAttribute(eq("mensaje"), anyString());
    }

    @Test
    void showTestHistory_returnsHistoryView() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(auth.getName()).thenReturn("user@test.com");
        when(simuladorService.buscarUsuarioPorCorreo("user@test.com")).thenReturn(usuario);
        when(simuladorService.buscarTestsPorUsuario(1)).thenReturn(List.of());

        String view = controller.showTestHistory(auth, model);

        assertEquals("estudiante/test_historial", view);
    }
}
