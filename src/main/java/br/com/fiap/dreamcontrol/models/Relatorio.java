package br.com.fiap.dreamcontrol.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDate;


@Entity
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private LocalDate inicio;
    @NotNull
    @Column(nullable = false)
    private LocalDate fim;
    @NotNull
    @Column(nullable = false)
    private String tempoTotal;
    @NotNull
    @Column(nullable = false)
    private int objetivo;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Relatorio() {
    }

    public Relatorio(LocalDate inicio, LocalDate fim, String tempoTotal, int objetivo) {
        this.inicio = inicio;
        this.fim = fim;
        this.tempoTotal = tempoTotal;
        this.objetivo = objetivo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public String getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(String tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public String toString() {
        String formated = "Inicio: " + inicio + "\nFim: " + fim + "\nTempo Total: " + tempoTotal + "\nObjetivo: " + objetivo;
        return formated.replace("PT","");
    }
}
