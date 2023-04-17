package br.com.unit.services;

import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.repositories.ObjetivoRepository;
import br.com.fiap.dreamcontrol.repositories.UsuarioRepository;
import br.com.fiap.dreamcontrol.services.ObjetivoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ObjetivoServiceTests {

    @Mock
    private ObjetivoRepository objetivoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ObjetivoService objetivoService;

    @Test
    public void testCadastrarObjetivo() {
        long userId = 12345L;
        Objetivo objetivo = new Objetivo(10, 100);
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));
        Mockito.when(objetivoRepository.save(objetivo)).thenReturn(objetivo);

        Objetivo resultado = objetivoService.cadastrarObjetivo(objetivo, userId);

        Assert.assertNotNull(resultado);
        Assert.assertEquals(usuario, resultado.getUsuario());
        Assert.assertNotNull(resultado.getDataCriacao());
    }

    @Test
    public void testRecuperarObjetivo() {
        long userId = 12345L;
        Objetivo objetivo = new Objetivo(10, 100);
        Usuario usuario = new Usuario();
        usuario.setObjetivo(objetivo);
        Mockito.when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));

        Objetivo resultado = objetivoService.recuperarObjetivo(userId);

        Assert.assertNotNull(resultado);
        Assert.assertEquals(objetivo, resultado);
    }
}
