package br.com.fiap.dreamcontrol.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.dreamcontrol.models.Cadastro;
import br.com.fiap.dreamcontrol.models.Login;

@RestController
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(UsuarioController.class);
    
    @PostMapping("/api/usuario/cadastrar")
    public String cadastrar(@RequestBody Cadastro cadastro)
    {
        log.info("cadastrando usuario: " + cadastro);
        return "" + cadastro; 
    }

    @PutMapping("/api/usuario/{id}")
    public String atualizar(@RequestBody Cadastro cadastro, @PathVariable int id)
    {
        log.info("atualizando cadastro de usuario: " + cadastro);
        return id + " :\n" + cadastro;
    }

    @PostMapping("/api/usuario/login")
    public String logar(@RequestBody Login credenciais)
    {
        log.info("validando login: " + credenciais);
        return "guid";
    }
}
