package br.com.fiap.dreamcontrol.dtos;

import br.com.fiap.dreamcontrol.models.Registro;

import java.util.List;

public record HistoricoDTO(List<Registro> registros) {}

