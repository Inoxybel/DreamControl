package br.com.fiap.dreamcontrol.controllers;


import br.com.fiap.dreamcontrol.dtos.HistoricoDTO;
import br.com.fiap.dreamcontrol.exceptions.RestNotFoundException;
import br.com.fiap.dreamcontrol.services.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Relatorio;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sono")
public class SonoController {
	
	private ObjetivoService objetivoService;
	private RegistroService registroService;
    private RelatorioService relatorioService;
    private HistoricoService historicoService;

    Logger log = LoggerFactory.getLogger(UsuarioController.class);


    @Autowired
    public SonoController(ObjetivoService objetivoService, RegistroService registroService, RelatorioService relatorioService, HistoricoService historicoService) {
        this.objetivoService = objetivoService;
        this.registroService = registroService;
        this.relatorioService = relatorioService;
        this.historicoService = historicoService;
    }

    @PostMapping("{userId}/objetivo")
    public ResponseEntity<Objetivo> cadastrarObjetivo(@Valid @RequestBody Objetivo objetivo, @PathVariable long userId)
    {
        log.info("cadastrando objetivo");
        Boolean successful = objetivoService.cadastrarObjetivo(objetivo, userId);

        if (!successful) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(objetivo);
    }

    @GetMapping("{userId}/objetivo")
    public ResponseEntity<Objetivo> recuperarObjetivo(@PathVariable long userId)
    {
        log.info("buscando objetivo");
        var objetivoEncontrado = objetivoService.recuperarObjetivo(userId);

        if (objetivoEncontrado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objetivo não encontrado");
        }
        return ResponseEntity.ok(objetivoEncontrado);
    }

    @PostMapping("{userId}/registrar")
    public ResponseEntity<Registro> registrarSono(@Valid @RequestBody Registro registro, @PathVariable long userId)
    {
        log.info("cadastrando registro de sono");
        var retornoService = registroService.registrarSono(registro, userId);

        if (retornoService == null)
            return ResponseEntity.badRequest().build();
        
        if(retornoService.containsKey(true))
            return ResponseEntity.status(HttpStatus.OK).body(retornoService.get(true));

        return ResponseEntity.status(HttpStatus.CREATED).body(retornoService.get(false));
    }

    @DeleteMapping("{userId}/deletar/{registroId}")
    public ResponseEntity<Registro> deletarRegistro(@PathVariable long userId, @PathVariable long registroId)
    {
        log.info("deletando registro de sono");
        var successful = registroService.deletarRegistro(userId, registroId);

        if (!successful) {
            throw new RestNotFoundException("despesa não encontrada");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{userId}/historico")
    public ResponseEntity<HistoricoDTO> recuperarHistorico(@PathVariable long userId)
    {
        log.info("buscando historico");
        var historicoEncontrado = historicoService.recuperarHistorico(userId);
        if (historicoEncontrado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historico não encontrado");
        }
        return ResponseEntity.ok(historicoEncontrado);
    }


    @GetMapping("{userId}/relatorio")
    public ResponseEntity<Relatorio> recuperarRelatorio(@PathVariable long userId)
    {
        log.info("buscando relatorio");
        var relatorioGerado = relatorioService.gerar(userId);
        if (relatorioGerado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relatorio não encontrado");
        }
        return ResponseEntity.ok(relatorioGerado);
    }
}
