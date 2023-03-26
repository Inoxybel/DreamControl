package br.com.fiap.dreamcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dreamcontrol.models.Registro;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    boolean existsByIdAndUsuarioId(long registroId, long usuarioId);
}
