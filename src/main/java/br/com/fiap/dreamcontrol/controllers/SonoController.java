package br.com.fiap.dreamcontrol.controllers;

import br.com.fiap.dreamcontrol.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.dreamcontrol.models.Historico;
import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Relatorio;

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
    public ResponseEntity<Objetivo> cadastrarObjetivo(@RequestBody Objetivo objetivo, @PathVariable long userId)
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(objetivoEncontrado);
    }

    @PostMapping("{userId}/registrar")
    public ResponseEntity<Registro> registrarSono(@RequestBody Registro registro, @PathVariable long userId)
    {
        log.info("cadastrando registro de sono");
        var successful = registroService.registrarSono(registro, userId);

        if (!successful) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @DeleteMapping("{userId}/deletar/{registroId}")
    public ResponseEntity<Registro> deletarRegistro(@PathVariable long userId, @PathVariable long registroId)
    {
        log.info("deletando registro de sono");
        var successful = registroService.deletarRegistro(userId, registroId);

        if (!successful) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{userId}/historico")
    public ResponseEntity<Historico> recuperarHistorico(@PathVariable long userId)
    {
        log.info("buscando historico");
        var historicoEncontrado = historicoService.recuperarHistorico(userId);
        if (historicoEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historicoEncontrado);
    }


    @GetMapping("{userId}/relatorio")
    public ResponseEntity<Relatorio> recuperarRelatorio(@PathVariable long userId)
    {
        log.info("buscando relatorio");
        var relatorioGerado = relatorioService.gerar(userId);
        if (relatorioGerado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relatorioGerado);
    }
}
