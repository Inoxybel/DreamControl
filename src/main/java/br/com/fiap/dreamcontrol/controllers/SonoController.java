package br.com.fiap.dreamcontrol.controllers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.management.relation.RelationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.dreamcontrol.models.Historico;
import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Relatorio;

@RestController
public class SonoController {

    Logger log = LoggerFactory.getLogger(SonoController.class);
    
    @PostMapping("/api/sono/{userId}/objetivo")
    public String cadastrarObjetivo(@RequestBody Objetivo objetivo, @PathVariable int userId)
    {
        log.info("cadastrando objetivo: " + objetivo);
        return "" + objetivo;
    }

    @GetMapping("/api/sono/{userId}/objetivo")
    public String recuperarObjetivo(@PathVariable int userId)
    {
        log.info("buscando objetivo com id: " + userId);
        return userId + "\nduracao: " + new Objetivo(15, 120).getObjetivo(); 
    }

    @PostMapping("/api/sono/{userId}/registrar")
    public String registrarSono(@RequestBody Registro registro, @PathVariable int userId)
    {
        log.info("registrando um periodo de sono: " + registro);
        return userId + "\n" + registro;
    }

    @GetMapping("/api/sono/{userId}/historico")
    public String recuperarHistorico(@PathVariable int userId)
    {
        log.info("buscando historico de sono com id: " + userId);
        return userId + "\n" + new Historico(new ArrayList<Registro>(){
            {
                add(new Registro(LocalDate.of(2023, 03, 10), LocalTime.of(7,20,00)));
                add(new Registro(LocalDate.of(2023, 03, 9), LocalTime.of(8,20,00)));
                add(new Registro(LocalDate.of(2023, 03, 8), LocalTime.of(7,50,00)));
            }
        });
    }

    @GetMapping("/api/sono/{userId}/relatorio")
    public String recuperarRelatorio(@PathVariable int userId)
    {
        log.info("buscando relatorio de sono com id: " + userId);
        var historico = new Historico(new ArrayList<Registro>(){
            {
                add(new Registro(LocalDate.of(2023, 03, 10), LocalTime.of(7,23,23)));
                add(new Registro(LocalDate.of(2023, 03, 9), LocalTime.of(8,20,00)));
                add(new Registro(LocalDate.of(2023, 03, 8), LocalTime.of(7,51,10)));
                add(new Registro(LocalDate.of(2023, 03, 8), LocalTime.of(6,30,00)));
                add(new Registro(LocalDate.of(2023, 03, 8), LocalTime.of(8,50,20)));
                add(new Registro(LocalDate.of(2023, 03, 8), LocalTime.of(7,13,00)));
            }
        });

        Duration total = Duration.ZERO;

        for (Registro r : historico.getRegistros()) {
            total = total.plusHours(r.getTempo().getHour())
                         .plusMinutes(r.getTempo().getMinute())
                         .plusSeconds(r.getTempo().getSecond());
        };

        return userId + "\n" + new Relatorio(
            LocalDate.of(2023,03,01), 
            LocalDate.of(2023,03,15), 
            total,
            120
        );
    }
}
