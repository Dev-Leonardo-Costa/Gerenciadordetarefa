package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TarefaController {

    @PostMapping
    ResponseEntity<Void> adiconarTarefa(@RequestBody final Tarefa tarefa);
}
