package br.com.unit.controllers;

import br.com.fiap.dreamcontrol.controllers.UsuarioController;
import br.com.fiap.dreamcontrol.dtos.LoginDTO;
import br.com.fiap.dreamcontrol.dtos.LoginResponseDTO;
import br.com.fiap.dreamcontrol.dtos.UsuarioResponseDTO;
import br.com.fiap.dreamcontrol.dtos.UsuarioUpdateDTO;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.services.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTests {
    @InjectMocks
    private UsuarioController controller;
    @Mock
    private UsuarioService service;

    @Test
    public void cadastrar_Deve_RetornarUsuarioResponseDTO() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setEmail("fulano@example.com");
        usuario.setSenha("senha123");

        Usuario usuarioCadastrado = new Usuario();
        usuarioCadastrado.setId(1L);
        usuarioCadastrado.setNome("Fulano");
        usuarioCadastrado.setEmail("fulano@example.com");
        usuarioCadastrado.setSenha("senha123");

        when(service.cadastrar(usuario)).thenReturn(usuarioCadastrado);

        ResponseEntity<UsuarioResponseDTO> response = controller.cadastrar(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(usuarioCadastrado.getId(), response.getBody().id());
        assertEquals(usuarioCadastrado.getNome(), response.getBody().nome());
        assertEquals(usuarioCadastrado.getEmail(), response.getBody().email());
        assertEquals(usuarioCadastrado.getSenha(), response.getBody().senha());
    }

    @Test
    public void atualizar_Deve_RetornarUsuarioAtualizado() {
        UsuarioUpdateDTO usuario = new UsuarioUpdateDTO("Pedro Augusto", "pedro@teste.com", "Senha1234");

        long id = 1L;

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setId(id);
        usuarioAtualizado.setNome("Pedro Augusto");
        usuarioAtualizado.setEmail("pedro@teste.com");
        usuarioAtualizado.setSenha("Senha1234");

        when(service.atualizar(usuario, id)).thenReturn(new UsuarioResponseDTO(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getEmail(),
                usuarioAtualizado.getSenha()
        ));

        ResponseEntity<UsuarioResponseDTO> response = controller.atualizar(usuario, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(usuarioAtualizado.getId(), response.getBody().id());
        assertEquals(usuarioAtualizado.getNome(), response.getBody().nome());
        assertEquals(usuarioAtualizado.getEmail(), response.getBody().email());
        assertEquals(usuarioAtualizado.getSenha(), response.getBody().senha());
    }

    @Test
    public void logar_Deve_RetornarLoginResponseDTO() {
        LoginDTO credenciais = new LoginDTO("pedro@teste.com", "Senha1234");

        long id = 1L;

        LoginResponseDTO loginResponse = new LoginResponseDTO(id);

        when(service.logar(credenciais)).thenReturn(loginResponse);

        ResponseEntity<LoginResponseDTO> response = controller.logar(credenciais);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(loginResponse.id(), response.getBody().id());
    }
}
