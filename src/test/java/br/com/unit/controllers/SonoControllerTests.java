package br.com.unit.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.crosscutting.builders.PaginationResponseDTOBuilder;
import br.com.fiap.dreamcontrol.controllers.SonoController;
import br.com.fiap.dreamcontrol.services.HistoricoService;
import br.com.fiap.dreamcontrol.services.ObjetivoService;
import br.com.fiap.dreamcontrol.services.RegistroService;
import br.com.fiap.dreamcontrol.services.RelatorioService;

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
}
