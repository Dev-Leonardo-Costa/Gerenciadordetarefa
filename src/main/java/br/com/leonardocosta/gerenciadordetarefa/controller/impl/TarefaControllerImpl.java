package br.com.leonardocosta.gerenciadordetarefa.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.controller.TarefaController;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.TarefaService;
import br.com.leonardocosta.gerenciadordetarefa.request.TarefaCreateRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaControllerImpl implements TarefaController {

    private final TarefaService service;

    @Override
    public ResponseEntity<Void> adiconarTarefa(TarefaCreateRecord tarefa) {
        service.salvar(tarefa);
        return ResponseEntity.status(CREATED.value()).build();
    }

    @Override
    public ResponseEntity<Tarefa> alocar(final Long tarefaId, final Long pessoaId) {
        service.alocar(tarefaId, pessoaId);
        return ResponseEntity.noContent().build();
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

    @Override
    public ResponseEntity<List<Tarefa>> listarTarefasPendentesMaisAntigas(int limit) {
        List<Tarefa> tarefas = service.listarTarefasPendentesMaisAntigas(limit);
        return ResponseEntity.ok(tarefas);
    }

    @Override
    public ResponseEntity<List<Tarefa>> listarTarefasPendentesMaisAntigas() {
        List<Tarefa> tarefas = service.listarTarefasPendentesMaisAntigasDeAcordoComOEnpointQUeFoiPedido();
        return ResponseEntity.ok(tarefas);
    }

}
