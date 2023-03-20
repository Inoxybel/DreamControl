package br.com.fiap.dreamcontrol.controllers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.dreamcontrol.models.Historico;
import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.models.Relatorio;

@RestController
public class SonoController {

    // Vale ressaltar que os objetos criados aqui, foram criados puramente com o intuito de demonstrar que
    // os métodos estão funcionando como o esperado

    Logger log = LoggerFactory.getLogger(SonoController.class);

    @PostMapping("/api/sono/{userId}/objetivo")
    public ResponseEntity<Objetivo> cadastrarObjetivo(@RequestBody Objetivo objetivo, @PathVariable int userId)
    {
        log.info("cadastrando objetivo: " + objetivo);
        // Aqui vai ser chamado o serviço que realiza o cadastro do objetivo para o usuário com ID especificado
        // adicionaremos ao decorrer das aulas, quando fizermos a camada service
        return ResponseEntity.status(HttpStatus.CREATED).body(objetivo);
    }

    @GetMapping("/api/sono/{userId}/objetivo")
    public ResponseEntity<Objetivo> recuperarObjetivo(@PathVariable int userId)
    {
        log.info("buscando objetivo com id: " + userId);
        Objetivo objetivo = new Objetivo(15, 120);
        // Aqui vai ser chamado o serviço que busca o objetivo do usuário com ID especificado
        // adicionaremos ao decorrer das aulas, quando fizermos a camada service
        return ResponseEntity.status(HttpStatus.OK).body(objetivo);
    }

    @PostMapping("/api/sono/{userId}/registrar")
    public ResponseEntity<Registro> registrarSono(@RequestBody Registro registro, @PathVariable int userId)
    {
        log.info("registrando um periodo de sono: " + registro);
        // Aqui vai ser chamado o serviço que realiza o registro do período de sono do usuário com ID especificado
        // adicionaremos ao decorrer das aulas, quando fizermos a camada service
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @GetMapping("/api/sono/{userId}/historico")
    public ResponseEntity<Historico> recuperarHistorico(@PathVariable int userId) {
        log.info("buscando historico de sono com id: " + userId);
        // Aqui vai ser chamado o serviço que busca o histórico de sono do usuário com ID especificado
        // adicionaremos ao decorrer das aulas, quando fizermos a camada service
        Historico historico = new Historico(new ArrayList<Registro>(){
            {
                add(new Registro(LocalDate.of(2023, 03, 10), LocalTime.of(7,20,00)));
                add(new Registro(LocalDate.of(2023, 03, 9), LocalTime.of(8,20,00)));
                add(new Registro(LocalDate.of(2023, 03, 8), LocalTime.of(7,50,00)));
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(historico);
    }


    @GetMapping("/api/sono/{userId}/relatorio")
    public ResponseEntity<Relatorio> recuperarRelatorio(@PathVariable int userId)
    {
        log.info("buscando relatorio de sono com id: " + userId);

        // Aqui vai ser chamado o serviço que gera o relatório de sono do usuário com ID especificado
        // adicionaremos ao decorrer das aulas, quando fizermos a camada service
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

        Relatorio relatorio = new Relatorio(
                LocalDate.of(2023,03,01),
                LocalDate.of(2023,03,15),
                total,
                120
        );

        return ResponseEntity.ok(relatorio);
    }
}
