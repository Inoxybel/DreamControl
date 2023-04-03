package br.com.fiap.dreamcontrol.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginDTO(
    @NotNull 
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email só pode conter caracteres alfanuméricos e os especiais: _ . - @")
    String email, 

    @NotNull 
    @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "Nome deve conter no mínimo 3 caracteres e nenhum pode ser numérico")
    String senha
) {}
