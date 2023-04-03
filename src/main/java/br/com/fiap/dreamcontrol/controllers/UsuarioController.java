package br.com.fiap.dreamcontrol.controllers;

import br.com.fiap.dreamcontrol.dtos.UsuarioResponseDTO;
import br.com.fiap.dreamcontrol.exceptions.RestBadRequestException;
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
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody Usuario usuario)
    {
        var usuarioCadastrado = usuarioService.cadastrar(usuario);

        var responseDTO = new UsuarioResponseDTO(usuarioCadastrado.getId(), usuarioCadastrado.getNome(), usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable long id)
    {
        log.info("Atualizando cadastro de usuario pelo id: " + id);

        UsuarioResponseDTO responseService = usuarioService.atualizar(usuario, id);

        if(responseService == null)
            throw new RestBadRequestException("Atualização não efetuada. Tente novamente com dados diferentes.");

        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> logar(@Valid @RequestBody LoginDTO credenciais)
    {
        log.info("solicitando validação das credenciais informadas");

        LoginResponseDTO responseService = usuarioService.logar(credenciais);

        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }
}