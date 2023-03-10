package br.com.fiap.dreamcontrol.models;

public class Objetivo {

    private int duracao;
    private int objetivo;

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

    @Override
    public String toString() {
        return "Duracao: " + duracao + "\nObjetivo: " + objetivo;
    }
}
