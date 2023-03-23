package br.com.fiap.dreamcontrol.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Objetivo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int duracao;
    private int objetivo;
    private LocalDate dataCriacao;


    public Objetivo(int duracao, int objetivo) {
        this.duracao = duracao;
        this.objetivo = objetivo;
        dataCriacao = LocalDate.now();
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }
    
    public LocalDate getDataCriacao() {
    	return dataCriacao;
    }

    @Override
    public String toString() {
        return "Duracao: " + duracao + "\nObjetivo: " + objetivo;
    }
}
