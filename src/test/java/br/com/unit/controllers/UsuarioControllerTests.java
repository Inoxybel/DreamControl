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
import org.springframework.hateoas.EntityModel;
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

        ResponseEntity<EntityModel<UsuarioResponseDTO>> response = controller.cadastrar(usuario);

        UsuarioResponseDTO responseBody = response.getBody().getContent();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(usuarioCadastrado.getId(), responseBody.id());
        assertEquals(usuarioCadastrado.getNome(), responseBody.nome());
        assertEquals(usuarioCadastrado.getEmail(), responseBody.email());
        assertEquals(usuarioCadastrado.getSenha(), responseBody.senha());
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

        EntityModel<UsuarioResponseDTO> response = controller.atualizar(usuario, id);
        UsuarioResponseDTO responseBody = response.getContent();

        assertNotNull(responseBody);
        assertEquals(usuarioAtualizado.getId(), responseBody.id());
        assertEquals(usuarioAtualizado.getNome(), responseBody.nome());
        assertEquals(usuarioAtualizado.getEmail(), responseBody.email());
        assertEquals(usuarioAtualizado.getSenha(), responseBody.senha());
    }

    @Test
    public void logar_Deve_RetornarLoginResponseDTO() {
        LoginDTO credenciais = new LoginDTO("pedro@teste.com", "Senha1234");

        long id = 1L;

        LoginResponseDTO loginResponse = new LoginResponseDTO(id);

        when(service.logar(credenciais)).thenReturn(loginResponse);

        EntityModel<LoginResponseDTO> response = controller.logar(credenciais);

        assertNotNull(response.getContent());
        assertEquals(loginResponse.id(), response.getContent().id());
    }
}
