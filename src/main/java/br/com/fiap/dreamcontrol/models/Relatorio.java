package br.com.fiap.dreamcontrol.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@Entity
public class Relatorio {

    @JsonIgnore
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
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Relatorio(LocalDate inicio, LocalDate fim, String tempoTotal, int objetivo) {
        this.inicio = inicio;
        this.fim = fim;
        this.tempoTotal = tempoTotal;
        this.objetivo = objetivo;
    }
}
