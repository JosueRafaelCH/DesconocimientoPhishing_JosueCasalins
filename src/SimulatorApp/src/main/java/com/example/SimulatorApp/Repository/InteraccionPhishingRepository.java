package com.example.SimulatorApp.Repository;

import com.example.SimulatorApp.Model.InteraccionPhishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteraccionPhishingRepository extends JpaRepository<InteraccionPhishing, Long> {
    List<InteraccionPhishing> findByUsuario_IdUsuario(Long idUsuario);
    Integer countByUsuario_IdUsuarioAndEsCorrecta(Long idUsuario, Boolean esCorrecta);
}
