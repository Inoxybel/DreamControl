package br.com.fiap.dreamcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dreamcontrol.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
