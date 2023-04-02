package br.com.fiap.dreamcontrol.errors;

public record RestValidationError(
        Integer code,
        String field,
        String message
) {}
