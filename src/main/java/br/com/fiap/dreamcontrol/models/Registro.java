package br.com.fiap.dreamcontrol.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Registro {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDate data;

    @NotNull
    @Column(nullable = false)
    private LocalTime tempo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Registro() {
    }

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
