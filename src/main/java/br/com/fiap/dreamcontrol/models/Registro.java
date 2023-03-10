package br.com.fiap.dreamcontrol.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Registro {
    
    private LocalDate data;
    private LocalTime tempo;

    public Registro(LocalDate data, LocalTime tempo) {
        this.data = data;
        this.tempo = tempo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getTempo() {
        return tempo;
    }

    public void setTempo(LocalTime tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "Data: " + data + "\nTempo: " + tempo;
    }
}
