package br.com.unit.services;

import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.repositories.RegistroRepository;
import br.com.fiap.dreamcontrol.repositories.UsuarioRepository;
import br.com.fiap.dreamcontrol.services.RegistroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistroServiceTests {
    @Mock
    private RegistroRepository registroRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private RegistroService registroService;

    @Test
    public void registrarSono_Deve_RetornarRegistro()
    {
        Registro registro = new Registro(LocalDate.now(), LocalTime.of(8, 30));
        long userId = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(userId);
        usuario.setRegistro(new ArrayList<>());
        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));
        when(registroRepository.save(any(Registro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Map<Boolean, Registro> retornoMetodo = registroService.registrarSono(registro, userId);

        assertEquals(1, usuario.getRegistro().size());
        assertTrue(retornoMetodo.containsKey(false));
        assertFalse(retornoMetodo.containsKey(true));
        assertEquals(registro, retornoMetodo.get(false));
    }

    @Test
    public void deletarRegistro()
    {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Registro registro = new Registro(LocalDate.now(), LocalTime.of(8, 30));
        registro.setId(1L);
        registro.setUsuario(usuario);

        List<Registro> registros = new ArrayList<>();
        registros.add(registro);
        usuario.setRegistro(registros);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(registroRepository.findById(1L)).thenReturn(Optional.of(registro));

        registroService.deletarRegistro(1L, 1L);

        assertEquals(0, usuario.getRegistro().size());
        verify(usuarioRepository).save(usuario);
        verify(registroRepository).delete(registro);
    }


}

