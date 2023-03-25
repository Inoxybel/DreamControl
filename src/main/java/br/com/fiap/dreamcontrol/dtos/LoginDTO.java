package br.com.fiap.dreamcontrol.dtos;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(
    @NotNull String email, 
    @NotNull String senha
) {}
