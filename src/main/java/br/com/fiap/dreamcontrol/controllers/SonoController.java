package br.com.fiap.dreamcontrol.controllers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import br.com.fiap.dreamcontrol.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<Objetivo> cadastrarObjetivo(@RequestBody Objetivo objetivo, @PathVariable int userId)
    {
        log.info("cadastrando objetivo");
        objetivoService.cadastrarObjetivo(objetivo, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(objetivo);
    }

    @GetMapping("{userId}/objetivo")
    public ResponseEntity<Objetivo> recuperarObjetivo(@PathVariable int userId)
    {
        log.info("buscando objetivo");
        var objetivoEncontrado = objetivoService.recuperarObjetivo(userId);

        if (objetivoEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(objetivoEncontrado);
    }

    @PostMapping("{userId}/registrar")
    public ResponseEntity<Registro> registrarSono(@RequestBody Registro registro, @PathVariable int userId)
    {
        log.info("cadastrando registro de sono");
        registroService.registrarSono(registro, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @DeleteMapping("{userId}/deletar")
    public ResponseEntity<Registro> deletarRegistro (@PathVariable int userId)
    {
        log.info("deletando registro de sono");
        registroService.deletarRegistro(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{userId}/historico")
    public ResponseEntity<Historico> recuperarHistorico(@PathVariable int userId)
    {
        log.info("buscando historico");
        var historicoEncontrado = historicoService.recuperarHistorico(userId);
        if (historicoEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historicoEncontrado);
    }


    @GetMapping("{userId}/relatorio")
    public ResponseEntity<Relatorio> recuperarRelatorio(@PathVariable int userId)
    {
        log.info("buscando relatorio");
        var relatorioGerado = relatorioService.gerar(userId);
        if (relatorioGerado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relatorioGerado);
    }
}
