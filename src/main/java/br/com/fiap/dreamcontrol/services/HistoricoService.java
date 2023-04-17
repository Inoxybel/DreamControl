package br.com.fiap.dreamcontrol.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import br.com.fiap.dreamcontrol.dtos.PaginationResponseDTO;
import br.com.fiap.dreamcontrol.repositories.RegistroRepository;

import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    Logger log = LoggerFactory.getLogger(HistoricoService.class);
    private RegistroRepository registroRepository;

    @Autowired
    public HistoricoService(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    public PaginationResponseDTO recuperarHistorico(long userId, Pageable pageable) {
        log.info("Buscando historico de sono do usuário: " + userId);

        var registros = registroRepository.getAllRegisters(userId, pageable);

        var responsePersonalizado = new PaginationResponseDTO(
            registros.getContent(),
            registros.getNumber(),
            registros.getTotalElements(),
            registros.getTotalPages(),
            registros.isFirst(),
            registros.isLast()
        );

        return responsePersonalizado;
    }
}
