package com.example.SimulatorApp.Service;

import com.example.SimulatorApp.Model.InteraccionPhishing;
import com.example.SimulatorApp.Repository.InteraccionPhishingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteraccionPhishingService {

    @Autowired
    private InteraccionPhishingRepository interaccionPhishingRepository;

    public InteraccionPhishing saveInteraccion(InteraccionPhishing interaccion) {
        return interaccionPhishingRepository.save(interaccion);
    }

    public List<InteraccionPhishing> getInteraccionesByUsuario(Long idUsuario) {
        return interaccionPhishingRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public Optional<InteraccionPhishing> getInteraccionById(Long id) {
        return interaccionPhishingRepository.findById(id);
    }

    public Integer getCorrectasCount(Long idUsuario) {
        return interaccionPhishingRepository.countByUsuario_IdUsuarioAndEsCorrecta(idUsuario, true);
    }

    public Integer getIncorrectasCount(Long idUsuario) {
        return interaccionPhishingRepository.countByUsuario_IdUsuarioAndEsCorrecta(idUsuario, false);
    }

    public double getPromedioDesempenio(Long idUsuario) {
        List<InteraccionPhishing> interacciones = getInteraccionesByUsuario(idUsuario);
        if (interacciones.isEmpty()) {
            return 0;
        }
        return interacciones.stream()
                .mapToInt(InteraccionPhishing::getPuntuacionObtenida)
                .average()
                .orElse(0);
    }
}
