package br.com.leonardocosta.gerenciadordetarefa.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.controller.TarefaController;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.TarefaService;
import br.com.leonardocosta.gerenciadordetarefa.request.TarefaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaControllerImpl implements TarefaController {

    private final TarefaService service;

    @Override
    public ResponseEntity<Void> adiconarTarefa(TarefaRecord tarefa) {
        service.salvar(tarefa);
        return ResponseEntity.status(CREATED.value()).build();
    }

    @Override
    public ResponseEntity<Tarefa> alocar(final Long tarefaId, final Long pessoaId) {
        try {
            service.alocar(tarefaId, pessoaId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Tarefa> buscar(final Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Override
    public ResponseEntity<Tarefa> finalizarTarefa(final Long tarefaId) {
        service.finalizarTarefa(tarefaId);
        return ResponseEntity.ok().build();
    }

}
