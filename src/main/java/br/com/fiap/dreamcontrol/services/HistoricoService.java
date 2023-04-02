package br.com.fiap.dreamcontrol.services;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dreamcontrol.dtos.HistoricoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Usuario;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    Logger log = LoggerFactory.getLogger(HistoricoService.class);
    private UsuarioService usuarioService;

    @Autowired
    public HistoricoService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public HistoricoDTO recuperarHistorico(long userId) {
        log.info("buscando historico de sono com id: " + userId);

        Usuario usuario = usuarioService.recuperar(userId);

        if (usuario == null || usuario.getEmail().isEmpty()) {
            return null;
        }

        List<Registro> registros = usuario.getRegistros();
        if (registros == null) {
            registros = new ArrayList<>();
        }

        return new HistoricoDTO(registros);
    }
}
