package br.com.fiap.dreamcontrol.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.dreamcontrol.models.Cadastro;
import br.com.fiap.dreamcontrol.models.Login;

@RestController
public class UsuarioController {
    
    @PostMapping("/api/usuario/cadastrar")
    public String cadastrar(@RequestBody Cadastro cadastro)
    {
        return "" + cadastro; 
    }

    @PutMapping("/api/usuario/{id}")
    public String atualizar(@RequestBody Cadastro cadastro, @PathVariable int id)
    {
        return id + " :\n" + cadastro;
    }

    @PostMapping("/api/usuario/login")
    public String logar(@RequestBody Login credenciais)
    {
        return "guid";
    }
}
