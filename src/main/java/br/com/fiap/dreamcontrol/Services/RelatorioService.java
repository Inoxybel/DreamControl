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

public class RelatorioService {
    
    Logger log = LoggerFactory.getLogger(RelatorioService.class);
    UsuarioService usuarioService = new UsuarioService();

    public Relatorio gerar(long userId)
    {
        log.info("iniciando criação do relatório do usuário de id: " + userId);
        
        Usuario usuario = usuarioService.recuperar(userId);

        if(usuario.getEmail().isEmpty())
            return new Relatorio(null, null, null, 0);

        Objetivo objetivo = usuario.getObjetivo();
        List<Registro> registros = usuario.getRegistros();

        log.info("filtrando e organizando os registros recuperados");
        List<Registro> registrosValidos = registros.stream()
        .filter(registro -> registro.getData().compareTo(objetivo.getDataCriacao()) >= 0)
        .sorted((p1, p2) -> p1.getData().compareTo(p2.getData()))
        .collect(Collectors.toList());

        log.info("criando relatório baseado nos dados coletados");
        Relatorio relatorio = new Relatorio(
            objetivo.getDataCriacao(), 
            objetivo.getDataCriacao().plusDays(objetivo.getDuracao()), 
            calcularTempo(registrosValidos), 
            objetivo.getObjetivo()
        );

        return relatorio;
    }

    private Duration calcularTempo(List<Registro> registros)
    {
        Duration total = Duration.ZERO;

        for (Registro r : registros) {
            total = total.plusHours(r.getTempo().getHour())
                        .plusMinutes(r.getTempo().getMinute())
                        .plusSeconds(r.getTempo().getSecond());
        };

        return total;
    }
     
}
