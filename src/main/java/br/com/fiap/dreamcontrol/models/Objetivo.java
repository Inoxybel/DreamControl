package br.com.fiap.dreamcontrol.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;


@Entity
public class Objetivo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private int duracao;

    @NotNull
    @Column(nullable = false)
    private int objetivo;
    
    @Column(nullable = false)
    private LocalDate dataCriacao;

    @OneToOne(mappedBy="objetivo")
    private Usuario usuario;


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
