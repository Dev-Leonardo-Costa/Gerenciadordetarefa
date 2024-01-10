package br.com.leonardocosta.gerenciadordetarefa.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.controller.TarefaController;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/tarefas")
public class TarefaControllerImpl implements TarefaController {

    private final TarefaService service;

    @Autowired
    public TarefaControllerImpl(TarefaService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> adiconarTarefa(final Tarefa tarefa) {
        service.salvar(tarefa);
        return ResponseEntity.status(CREATED.value()).build();
    }
}
