package br.com.unit.services;
import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Relatorio;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.services.RelatorioService;
import br.com.fiap.dreamcontrol.services.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RelatorioServiceTests {
    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private RelatorioService relatorioService;

    @Test
    public void testGerar()
    {
        Usuario usuario = new Usuario();
        usuario.setId(123L);

        Objetivo objetivo = new Objetivo();
        objetivo.setDataCriacao(LocalDate.now());
        objetivo.setDuracao(120);
        objetivo.setObjetivo(15);
        usuario.setObjetivo(objetivo);

        List<Registro> registros = new ArrayList<>();
        registros.add(new Registro(LocalDate.now(), LocalTime.of(8, 00)));
        registros.add(new Registro(LocalDate.now(), LocalTime.of(9, 00)));
        registros.add(new Registro(LocalDate.now(), LocalTime.of(7, 00)));
        usuario.setRegistro(registros);

        Mockito.when(usuarioService.recuperar(Mockito.anyLong())).thenReturn(usuario);

        Relatorio relatorio = relatorioService.gerar(123L);

        assertNotNull(relatorio);
        assertEquals(objetivo.getDataCriacao(), relatorio.getInicio());
        assertEquals(objetivo.getDataCriacao().plusDays(objetivo.getDuracao()), relatorio.getFim());
        assertEquals("24H", relatorio.getTempoTotal());
        assertEquals(objetivo.getObjetivo(), relatorio.getObjetivo());
    }
}
