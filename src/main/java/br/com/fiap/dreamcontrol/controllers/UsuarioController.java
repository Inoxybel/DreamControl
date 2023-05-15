package br.com.fiap.dreamcontrol.controllers;

import br.com.fiap.dreamcontrol.dtos.UsuarioResponseDTO;
import br.com.fiap.dreamcontrol.dtos.UsuarioUpdateDTO;
import br.com.fiap.dreamcontrol.exceptions.RestBadRequestException;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.services.TokenService;
import br.com.fiap.dreamcontrol.services.UsuarioService;
import jakarta.validation.Valid;
import br.com.fiap.dreamcontrol.dtos.LoginDTO;
import br.com.fiap.dreamcontrol.dtos.LoginResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;


    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("cadastrar")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> cadastrar(@Valid @RequestBody Usuario usuario)
    {
        var usuarioCadastrado = usuarioService.cadastrar(usuario);

        var responseDTO = new UsuarioResponseDTO(usuarioCadastrado.getId(), usuarioCadastrado.getNome(), usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha());

        var entityModel = EntityModel.of(
                responseDTO,
                linkTo(methodOn(UsuarioController.class).cadastrar(usuario)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).atualizar(new UsuarioUpdateDTO(usuarioCadastrado.getNome(), usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha()), usuarioCadastrado.getId())).withRel("atualizar"),
                linkTo(methodOn(UsuarioController.class).logar(new LoginDTO(usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha()))).withRel("logar")
        );

        return ResponseEntity.created(linkTo(methodOn(UsuarioController.class).cadastrar(usuario)).toUri())
                .body(entityModel);
    }

    @PutMapping("{id}")
    public EntityModel<UsuarioResponseDTO> atualizar(@RequestBody UsuarioUpdateDTO usuario, @PathVariable long id)
    {
        log.info("Atualizando cadastro de usuario pelo id: " + id);

        UsuarioResponseDTO responseService = usuarioService.atualizar(usuario, id);

        if(responseService == null)
            throw new RestBadRequestException("Atualização não efetuada. Tente novamente com dados diferentes.");

        return EntityModel.of(
                responseService,
                linkTo(methodOn(UsuarioController.class).atualizar(usuario, id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).cadastrar(new Usuario(usuario.nome(), usuario.email(), usuario.senha()))).withRel("cadastrar"),
                linkTo(methodOn(UsuarioController.class).logar(new LoginDTO(usuario.email(), usuario.senha()))).withRel("logar")
        );
    }

    @PostMapping("login")
    public EntityModel<LoginResponseDTO> logar(@Valid @RequestBody LoginDTO credenciais)
    {
        log.info("solicitando validação das credenciais informadas");

        LoginResponseDTO responseService = usuarioService.logar(credenciais);

        return EntityModel.of(
                responseService,
                linkTo(methodOn(UsuarioController.class).logar(credenciais)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).cadastrar(new Usuario())).withRel("cadastrar")
        );
    }
}