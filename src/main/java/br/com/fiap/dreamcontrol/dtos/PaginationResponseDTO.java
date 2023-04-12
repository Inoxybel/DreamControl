package br.com.fiap.dreamcontrol.dtos;

import java.util.List;

import br.com.fiap.dreamcontrol.models.Registro;

public record PaginationResponseDTO(
    List<Registro> content,
    int number,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last
) {}
