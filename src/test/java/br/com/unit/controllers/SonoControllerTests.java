package br.com.unit.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.dreamcontrol.exceptions.RestNotFoundException;
import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Relatorio;
import br.com.fiap.dreamcontrol.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.crosscutting.builders.PaginationResponseDTOBuilder;
import br.com.fiap.dreamcontrol.controllers.SonoController;
import br.com.fiap.dreamcontrol.services.HistoricoService;
import br.com.fiap.dreamcontrol.services.ObjetivoService;
import br.com.fiap.dreamcontrol.services.RegistroService;
import br.com.fiap.dreamcontrol.services.RelatorioService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

public class SonoControllerTests {
    
    private ObjetivoService objetivoService;
	private RegistroService registroService;
    private RelatorioService relatorioService;
    private HistoricoService historicoService;
    private SonoController sonoController;
    
    @BeforeEach
    public void setUp()
    {
        objetivoService = mock(ObjetivoService.class);
        registroService = mock(RegistroService.class);
        relatorioService = mock(RelatorioService.class);
        historicoService = mock(HistoricoService.class);

        sonoController = new SonoController(objetivoService, registroService, relatorioService, historicoService);
    }

    @Test
    public void cadastrarObjetivo_Deve_RetornarObjetivoCadastrado()
    {
        Objetivo objetivo = new Objetivo(10, 8);
        long userId = 1L;
        Objetivo objetivoCadastrado = new Objetivo(10, 8);
        objetivoCadastrado.setId(1L);
        objetivoCadastrado.setDataCriacao(LocalDate.now());
        objetivoCadastrado.setUsuario(new Usuario());
        when(objetivoService.cadastrarObjetivo(objetivo, userId)).thenReturn(objetivoCadastrado);

        ResponseEntity<Objetivo> response = sonoController.cadastrarObjetivo(objetivo, userId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(objetivoCadastrado, response.getBody());
    }

    @Test
    public void cadastrarObjetivo_Deve_Lancar_RestNotFoundException_QuandoUsuarioNaoForEncontrado()
    {
        long userId = 1L;
        Objetivo objetivo = new Objetivo(10, 8);

        when(objetivoService.cadastrarObjetivo(objetivo, userId))
                .thenThrow(new RestNotFoundException("Usuario não encontrado"));

        assertThrows(RestNotFoundException.class, () -> sonoController.cadastrarObjetivo(objetivo, userId));
    }

    @Test
    public void recuperarObjetivo_Deve_RetornarObjetivoDoUsuario()
    {
        long userId = 1L;
        Objetivo objetivo = new Objetivo(10, 8);
        objetivo.setId(1L);
        objetivo.setDataCriacao(LocalDate.now());
        objetivo.setUsuario(new Usuario());
        when(objetivoService.recuperarObjetivo(userId)).thenReturn(objetivo);

        EntityModel<Objetivo> response = sonoController.recuperarObjetivo(userId);

        //assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(objetivo, response);
    }

    @Test
    public void recuperarObjetivo_Deve_Lancar_RestNotFoundException_QuandoUsuarioNaoForEncontrado()
    {
        long userId = 1L;

        when(objetivoService.recuperarObjetivo(userId))
                .thenThrow(new RestNotFoundException("Usuário não encontrado"));

        assertThrows(RestNotFoundException.class, () -> sonoController.recuperarObjetivo(userId));
    }

    @Test
    public void registrarSono_Deve_RetornarSonoCadastrado()
    {
        Registro registro = new Registro(LocalDate.now(), LocalTime.of(07, 25, 30));
        long userId = 1L;

        when(registroService.registrarSono(registro, userId)).thenReturn(Collections.singletonMap(false, registro));

        ResponseEntity<Registro> response = sonoController.registrarSono(registro, userId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(registro, response.getBody());
    }

    @Test
    public void deletarRegistro_Deve_RetornarNoContent_QuandoRegistroExistirEUsuarioForValido()
    {
        long userId = 1L;
        long registroId = 1L;
        Registro registro = new Registro(LocalDate.now(), LocalTime.now());
        registro.setUsuario(new Usuario());
        doNothing().when(registroService).deletarRegistro(userId, registroId);

        ResponseEntity<Registro> response = sonoController.deletarRegistro(userId, registroId);

        verify(registroService, times(1)).deletarRegistro(userId, registroId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    public void recuperarHistorico_Deve_RetornarHistorico()
    {
        var retornoService = new PaginationResponseDTOBuilder().build();
        when(historicoService.recuperarHistorico(1, null)).thenReturn(retornoService);

        var retornoController = sonoController.recuperarHistorico(1, null);

        assertEquals(retornoController.content(), retornoService.content());
        assertEquals(retornoController.number(), retornoService.number());
        assertEquals(retornoController.totalElements(), retornoService.totalElements());
        assertEquals(retornoController.totalPages(), retornoService.totalPages());
        assertEquals(retornoController.first(), retornoService.first());
        assertEquals(retornoController.last(), retornoService.last());
    }

    @Test
    public void recuperarRelatorio_Deve_RetornarRelatorio_QuandoRelatorioExistir()
    {
        long userId = 1L;
        Relatorio relatorio = new Relatorio();
        when(relatorioService.gerar(userId)).thenReturn(relatorio);

        ResponseEntity<Relatorio> response = sonoController.recuperarRelatorio(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(relatorio, response.getBody());
    }

    @Test
    public void recuperarRelatorio_Deve_LancarRestNotFoundException_QuandoRelatorioNaoExistir()
    {
        long userId = 1L;
        when(relatorioService.gerar(userId)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> sonoController.recuperarRelatorio(userId));
    }
}
