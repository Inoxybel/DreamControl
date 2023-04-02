package br.com.fiap.dreamcontrol.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    public Objetivo() {
    }

    public Objetivo(int duracao, int objetivo) {
        this.duracao = duracao;
        this.objetivo = objetivo;
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


    public void setDataCriacao(){
        dataCriacao = LocalDate.now();
    }

    public LocalDate getDataCriacao() {
    	return dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Duracao: " + duracao + "\nObjetivo: " + objetivo;
    }
}
