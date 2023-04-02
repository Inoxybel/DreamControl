package br.com.fiap.dreamcontrol.errors;

public record RestConstraintViolationError(
        int code,
        Object field,
        String message
) {}
