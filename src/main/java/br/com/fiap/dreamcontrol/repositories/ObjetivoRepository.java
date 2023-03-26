package br.com.fiap.dreamcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dreamcontrol.models.Objetivo;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {

}
