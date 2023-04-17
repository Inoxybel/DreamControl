package br.com.unit.services;

import br.com.fiap.dreamcontrol.dtos.PaginationResponseDTO;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.repositories.RegistroRepository;
import br.com.fiap.dreamcontrol.services.HistoricoService;
import org.junit.Assert;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class HistoricoServiceTests {

    @Mock
    private RegistroRepository registroRepository;

    @InjectMocks
    private HistoricoService historicoService;

    @Test
    public void recuperarHistorico_Deve_RetornarHistorico() {
        long userId = 12345L;
        Pageable pageable = PageRequest.of(0, 10);
        List<Registro> registros = Arrays.asList(new Registro(), new Registro(), new Registro());
        Page<Registro> registrosPage = new PageImpl<>(registros, pageable, registros.size());
        Mockito.when(registroRepository.getAllRegisters(userId, pageable)).thenReturn(registrosPage);

        PaginationResponseDTO response = historicoService.recuperarHistorico(userId, pageable);

        Assert.assertNotNull(response);
        Assert.assertEquals(registros.size(), response.content().size());
    }
}
