package br.com.fiap.dreamcontrol.models;

import java.time.Duration;
import java.time.LocalDate;


/* Essa classe também deveria ser tratada como um objeto de transferência como uma DTO por exemplo */
public class Relatorio {
    
    private LocalDate inicio;
    private LocalDate fim;
    private Duration tempoTotal;
    private int objetivo;

    public Relatorio() {
    }

    public Relatorio(LocalDate inicio, LocalDate fim, Duration total, int objetivo) {
        this.inicio = inicio;
        this.fim = fim;
        this.tempoTotal = total;
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

    public Duration getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(Duration tempoTotal) {
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
