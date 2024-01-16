package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.request.TarefaCreateRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TarefaController {

    @PostMapping
    ResponseEntity<Void> adiconarTarefa(@RequestBody TarefaCreateRecord tarefa);

    @PutMapping("/alocar/{tarefaId}")
    ResponseEntity<Tarefa> alocar(@PathVariable final Long tarefaId, @RequestParam final Long pessoaId);

    @GetMapping("/{id}")
    ResponseEntity<Tarefa> buscar(@PathVariable Long id);

    @PutMapping("/finalizar/{tarefaId}")
    ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Long tarefaId);

    @GetMapping("/pendentes-meu")
    ResponseEntity<List<Tarefa>> listarTarefasPendentesMaisAntigas(@RequestParam(defaultValue = "3") int limit);

    @GetMapping("/pendentes")
    ResponseEntity<List<Tarefa>> listarTarefasPendentesMaisAntigas();

}
