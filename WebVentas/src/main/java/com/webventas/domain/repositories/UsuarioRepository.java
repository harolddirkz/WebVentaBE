package com.webventas.domain.repositories;

import com.webventas.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query(value = """
        SELECT *
        FROM usuarios u
        WHERE u.usuario = :usuario
    """, nativeQuery = true)
    Optional<Usuario> findByUsuario(String usuario);

    List<Usuario> findByHabilitado(Boolean habilitado);
}
