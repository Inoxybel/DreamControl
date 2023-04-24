package br.com.fiap.dreamcontrol.controllers;

import br.com.fiap.dreamcontrol.dtos.*;
import br.com.fiap.dreamcontrol.services.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Relatorio;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/sono")
public class SonoController {
	
	private ObjetivoService objetivoService;
	private RegistroService registroService;
    private RelatorioService relatorioService;
    private HistoricoService historicoService;
    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public SonoController(ObjetivoService objetivoService, RegistroService registroService, RelatorioService relatorioService, HistoricoService historicoService) {
        this.objetivoService = objetivoService;
        this.registroService = registroService;
        this.relatorioService = relatorioService;
        this.historicoService = historicoService;
    }

    @PostMapping("{userId}/objetivo")
    public ResponseEntity<EntityModel<Objetivo>> cadastrarObjetivo(@Valid @RequestBody Objetivo objetivo, @PathVariable long userId)
    {
        log.info("Cadastrando objetivo");
        Objetivo responseService = objetivoService.cadastrarObjetivo(objetivo, userId);

        var entityModel = EntityModel.of(
                responseService,
                linkTo(methodOn(SonoController.class).cadastrarObjetivo(objetivo, userId)).withSelfRel(),
                linkTo(methodOn(SonoController.class).recuperarObjetivo(userId)).withRel("recuperar")
        );

        return ResponseEntity.created(linkTo(methodOn(SonoController.class).cadastrarObjetivo(objetivo, userId)).toUri())
                .body(entityModel);
    }

    @GetMapping("{userId}/objetivo")
    public EntityModel<Objetivo> recuperarObjetivo(@PathVariable long userId)
    {
        log.info("Buscando objetivo");
        var objetivo = objetivoService.recuperarObjetivo(userId);

        return EntityModel.of(
                objetivo,
                linkTo(methodOn(SonoController.class).recuperarObjetivo(userId)).withSelfRel(),
                linkTo(methodOn(SonoController.class).cadastrarObjetivo(objetivo, userId)).withRel("cadastrar")
        );
    }

    @PostMapping("{userId}/registrar")
    public ResponseEntity<Registro> registrarSono(@Valid @RequestBody Registro registro, @PathVariable long userId)
    {
        log.info("Cadastrando registro de sono");
        var retornoService = registroService.registrarSono(registro, userId);
        
        if(retornoService.containsKey(true))
            return ResponseEntity.status(HttpStatus.OK).body(retornoService.get(true));

        return ResponseEntity.status(HttpStatus.CREATED).body(retornoService.get(false));
    }

    @DeleteMapping("{userId}/deletar/{registroId}")
    public ResponseEntity<Registro> deletarRegistro(@PathVariable long userId, @PathVariable long registroId)
    {
        log.info("Deletando registro de sono");
        
        registroService.deletarRegistro(userId, registroId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{userId}/historico")
    public PaginationResponseDTO recuperarHistorico(@PathVariable long userId, @PageableDefault(size = 3) Pageable pageable)
    {
        log.info("Buscando historico");

        var historicoEncontrado = historicoService.recuperarHistorico(userId, pageable);

        return historicoEncontrado;
    }


    @GetMapping("{userId}/relatorio")
    public ResponseEntity<Relatorio> recuperarRelatorio(@PathVariable long userId)
    {
        log.info("Buscando relatorio");

        var relatorioGerado = relatorioService.gerar(userId);

        if (relatorioGerado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relatorio não encontrado");
        }

        return ResponseEntity.ok(relatorioGerado);
    }
}
