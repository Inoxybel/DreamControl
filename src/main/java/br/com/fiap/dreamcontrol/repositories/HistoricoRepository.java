package br.com.fiap.dreamcontrol.repositories;

import br.com.fiap.dreamcontrol.models.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    @Query("SELECT h FROM Historico h JOIN h.registros r WHERE r.id = :registroId")
    Optional<Historico> findByRegistroId(@Param("registroId") Long registroId);
}

