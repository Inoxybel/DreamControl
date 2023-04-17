package br.com.unit.services;
import br.com.fiap.dreamcontrol.dtos.LoginDTO;
import br.com.fiap.dreamcontrol.dtos.LoginResponseDTO;
import br.com.fiap.dreamcontrol.dtos.UsuarioResponseDTO;
import br.com.fiap.dreamcontrol.dtos.UsuarioUpdateDTO;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.repositories.UsuarioRepository;
import br.com.fiap.dreamcontrol.services.UsuarioService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTests {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void cadastrar_Deve_RetornarUsuarioCadastrado() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Fulano");
        usuario.setEmail("fulano@example.com");
        usuario.setSenha("senha123");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario resultado = usuarioService.cadastrar(usuario);

        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.getId());
        assertEquals(usuario.getNome(), resultado.getNome());
        assertEquals(usuario.getEmail(), resultado.getEmail());
        assertEquals(usuario.getSenha(), resultado.getSenha());
    }
    @Test
    public void atualizar_Deve_RetornarUsuarioAtualizado() {
        long id = 1L;
        UsuarioUpdateDTO usuarioDTO = new UsuarioUpdateDTO("Ciclano de Tal", "fulano@exemplo.com", "Senha1234");
        Usuario usuario = new Usuario("Fulano de Tal", "fulano@exemplo.com", "Senha1234");
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.saveAndFlush(usuario)).thenReturn(usuario);

        UsuarioResponseDTO resultado = usuarioService.atualizar(usuarioDTO, id);

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).saveAndFlush(usuario);
        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.id());
        assertEquals(usuarioDTO.nome(), resultado.nome());
        assertEquals(usuario.getEmail(), resultado.email());
        assertEquals(usuario.getSenha(), resultado.senha());
    }

    @Test
    public void testRecuperar() {
        long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Fulano");
        usuario.setEmail("fulano@example.com");
        usuario.setSenha("senha123");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.recuperar(id);

        verify(usuarioRepository, times(1)).findById(id);
        assertEquals(usuario, resultado);
    }

    @Test
    @DisplayName("Testa login de usuário")
    public void testLogar() {
        String email = "fulano@exemplo.com";
        String senha = "senhasecreta123";

        LoginDTO credenciais = new LoginDTO(email, senha);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Fulano");
        usuario.setEmail(email);
        usuario.setSenha(senha);

        when(usuarioRepository.buscarCredenciais(email, senha)).thenReturn(Optional.of(usuario));

        LoginResponseDTO resultado = usuarioService.logar(credenciais);

        verify(usuarioRepository, times(1)).buscarCredenciais(email, senha);
        assertNotNull(resultado);
        assertEquals(Optional.ofNullable(usuario.getId()), Optional.of(resultado.id()));
    }

}
