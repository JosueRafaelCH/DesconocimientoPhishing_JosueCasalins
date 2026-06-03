package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.SimulatorApp.Model.Entity.Usuario;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDAOIface extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.rol JOIN FETCH u.estado JOIN FETCH u.estrato WHERE u.correoInstitucional = :correo")
    Optional<Usuario> findByCorreoInstitucional(String correo);

    List<Usuario> findByRolId(Integer idRol);

    List<Usuario> findByEstadoId(Integer idEstado);

    List<Usuario> findByEstratoId(Integer idEstrato);

    List<Usuario> findByDocenteTutorId(Integer idDocente);
}
