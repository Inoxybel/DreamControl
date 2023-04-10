package br.com.fiap.dreamcontrol.services;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Relatorio;
import br.com.fiap.dreamcontrol.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    private UsuarioService usuarioService;

    Logger log = LoggerFactory.getLogger(RelatorioService.class);

    @Autowired
    public RelatorioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Relatorio gerar(long userId)
    {
        log.info("Iniciando criação do relatório do usuário: " + userId);

        Usuario usuario = usuarioService.recuperar(userId);

        Objetivo objetivo = usuario.getObjetivo();
        List<Registro> registros = usuario.getRegistro();

        log.info("Filtrando e organizando os registros recuperados");
        List<Registro> registrosValidos = getRegistrosValidos(registros, objetivo);

        log.info("Criando relatório baseado nos dados coletados");
        Relatorio relatorio = criarRelatorio(objetivo, registrosValidos);

        return relatorio;
    }

    private List<Registro> getRegistrosValidos(List<Registro> registros, Objetivo objetivo) 
    {
        /* Retorna apenas registros com data superior a data de criação do objetivo 
           até a data atual da requisição */
        return registros.stream()
                .filter(registro -> registro.getData().compareTo(objetivo.getDataCriacao()) >= 0)
                .sorted((p1, p2) -> p1.getData().compareTo(p2.getData()))
                .collect(Collectors.toList());
    }

    private Relatorio criarRelatorio(Objetivo objetivo, List<Registro> registros)
    {
        return new Relatorio(
                objetivo.getDataCriacao(),
                objetivo.getDataCriacao().plusDays(objetivo.getDuracao()),
                calcularTempo(registros),
                objetivo.getObjetivo()
        );
    }

    private String calcularTempo(List<Registro> registros)
    {
        Duration total = Duration.ZERO;

        for (Registro r : registros) {
            total = total.plusHours(r.getTempo().getHour())
                    .plusMinutes(r.getTempo().getMinute())
                    .plusSeconds(r.getTempo().getSecond());
        };

        return total.toString().replace("PT", "");
    }
}
