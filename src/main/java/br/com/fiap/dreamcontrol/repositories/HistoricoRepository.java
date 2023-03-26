package br.com.fiap.dreamcontrol.repositories;

import br.com.fiap.dreamcontrol.models.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}
