package br.com.fiap.dreamcontrol.controllers;

import br.com.fiap.dreamcontrol.models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.dreamcontrol.models.Login;

@RestController
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/api/usuario/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario)
    {
        log.info("cadastrando usuario: " + usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    @PutMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario, @PathVariable int id) {
        log.info("atualizando cadastro de usuario: " + usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/api/usuario/login")
    public ResponseEntity<String> logar(@RequestBody Login credenciais) {
        log.info("validando login: " + credenciais);
        return ResponseEntity.ok("guid");
    }
}
