package br.com.fiap.dreamcontrol.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.dreamcontrol.models.Historico;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Usuario;

public class HistoricoService {
    
    Logger log = LoggerFactory.getLogger(HistoricoService.class);
    UsuarioService usuarioService = new UsuarioService();
    RegistroService registroService = new RegistroService();

    public ResponseEntity<Historico> recuperarHistorico(int userId) {
        log.info("buscando historico de sono com id: " + userId);

        Usuario usuario = usuarioService.recuperar(userId);

        List<Registro> registros = new ArrayList<Registro>();


        if(!usuario.getEmail().isEmpty())
            registros = usuario.getRegistros();
        
        Historico historico = new Historico(registros);

        return ResponseEntity.status(HttpStatus.OK).body(historico);
    }
    
}
