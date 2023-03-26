package br.com.fiap.dreamcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dreamcontrol.models.Registro;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
