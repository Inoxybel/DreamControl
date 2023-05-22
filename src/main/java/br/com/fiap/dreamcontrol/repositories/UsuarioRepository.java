package br.com.fiap.dreamcontrol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dreamcontrol.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = ?2")
    Optional<Usuario> buscarCredenciais(String email, String senha);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Optional<Usuario> buscarEmail(String email);
}
