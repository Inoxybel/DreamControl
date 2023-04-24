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
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<EntityModel<Registro>> registrarSono(@Valid @RequestBody Registro registro, @PathVariable long userId) {
        log.info("Cadastrando registro de sono");
        var retornoService = registroService.registrarSono(registro, userId);
        Registro retorno;
        HttpStatus status;

        if (retornoService.containsKey(true)) {
            retorno = retornoService.get(true);
            status = HttpStatus.CREATED;
        } else {
            retorno = retornoService.get(false);
            status = HttpStatus.OK;
        }

        var entityModel = EntityModel.of(
                retorno,
                linkTo(methodOn(SonoController.class).registrarSono(registro, userId)).withSelfRel(),
                linkTo(methodOn(SonoController.class).deletarRegistro(userId, retorno.getId())).withRel("deletar")
        );

        return ResponseEntity.status(status).body(entityModel);
    }


    @DeleteMapping("{userId}/deletar/{registroId}")
    public ResponseEntity<EntityModel<Object>> deletarRegistro(@PathVariable long userId, @PathVariable long registroId) {
        log.info("Deletando registro de sono");

        registroService.deletarRegistro(userId, registroId);

        var entityModel = EntityModel.of(
                null,
                linkTo(methodOn(SonoController.class).registrarSono(null, userId)).withRel("registrar")
        );

        return ResponseEntity.status(HttpStatus.OK).body(entityModel);
    }


    @GetMapping("{userId}/historico")
    public ResponseEntity<EntityModel<PaginationResponseDTO>> recuperarHistorico(@PathVariable long userId, @PageableDefault(size = 3) Pageable pageable) {
        log.info("Buscando historico");

        var historicoEncontrado = historicoService.recuperarHistorico(userId, pageable);

        var entityModel = EntityModel.of(
                historicoEncontrado,
                linkTo(methodOn(SonoController.class).recuperarHistorico(userId, pageable)).withSelfRel(),
                linkTo(methodOn(SonoController.class).recuperarRelatorio(userId)).withRel("relatorio")
        );

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("{userId}/relatorio")
    public ResponseEntity<EntityModel<Relatorio>> recuperarRelatorio(@PathVariable long userId) {
        log.info("Buscando relatorio");

        var relatorioGerado = relatorioService.gerar(userId);

        if (relatorioGerado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relatorio não encontrado");
        }

        var entityModel = EntityModel.of(
                relatorioGerado,
                linkTo(methodOn(SonoController.class).recuperarRelatorio(userId)).withSelfRel(),
                linkTo(methodOn(SonoController.class).recuperarHistorico(userId, Pageable.unpaged())).withRel("historico")
        );

        return ResponseEntity.ok(entityModel);
    }

}
