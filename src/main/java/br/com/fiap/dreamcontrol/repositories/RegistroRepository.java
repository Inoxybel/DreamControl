package br.com.fiap.dreamcontrol.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.dreamcontrol.models.Registro;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    boolean existsByIdAndUsuarioId(long registroId, long usuarioId);

    @Query("SELECT r FROM Registro r WHERE r.usuario.id = :usuarioId")
    Page<Registro> getAllRegisters(long usuarioId , Pageable pageable);
}
