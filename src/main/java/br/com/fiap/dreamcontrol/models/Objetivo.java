package br.com.fiap.dreamcontrol.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Objetivo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private int duracao;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private int objetivo;
    
    @Column(nullable = false)
    private LocalDate dataCriacao;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    public Objetivo(int duracao, int objetivo) {
        this.duracao = duracao;
        this.objetivo = objetivo;
    }

    public void setDataCriacao(){
        dataCriacao = LocalDate.now();
    }
}
