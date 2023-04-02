package br.com.fiap.dreamcontrol.controllers;

import br.com.fiap.dreamcontrol.dtos.UsuarioResponseDTO;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.services.UsuarioService;
import jakarta.validation.Valid;
import br.com.fiap.dreamcontrol.dtos.LoginDTO;
import br.com.fiap.dreamcontrol.dtos.LoginResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario)
    {
        usuarioService.cadastrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    @PutMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable long id)
    {
        log.info("atualizando cadastro de usuario pelo id: " + id);

        UsuarioResponseDTO responseService = usuarioService.atualizar(usuario, id);

        if(responseService == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> logar(@Valid @RequestBody LoginDTO credenciais)
    {
        log.info("solicitando validação das credenciais informadas");

        LoginResponseDTO responseService = usuarioService.logar(credenciais);

        if(responseService.id() == 0)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseService);

        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }
}