package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.request.TarefaRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TarefaController {

    @PostMapping
    ResponseEntity<Void> adiconarTarefa(@RequestBody TarefaRecord tarefa);

    @PutMapping("/alocar/{tarefaId}")
    ResponseEntity<Tarefa> alocar(@PathVariable final Long tarefaId, @RequestParam final Long pessoaId);

    @GetMapping("/{id}")
    ResponseEntity<Tarefa> buscar(@PathVariable Long id);

    @PutMapping("/finalizar/{tarefaId}")
    public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Long tarefaId);

}