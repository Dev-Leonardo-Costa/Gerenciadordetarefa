package br.com.leonardocosta.gerenciadordetarefa.api.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.api.controller.TarefaController;
import br.com.leonardocosta.gerenciadordetarefa.api.mapper.TarefaMapper;
import br.com.leonardocosta.gerenciadordetarefa.api.request.TarefaRequest;
import br.com.leonardocosta.gerenciadordetarefa.api.response.TarefaListarAntigasResponse;
import br.com.leonardocosta.gerenciadordetarefa.api.response.TarefaReponse;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.TarefaService;
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
    public ResponseEntity<TarefaReponse> registrarTarefa(final TarefaRequest request) {
        Tarefa tarefa = TarefaMapper.toTarefa(request);
        Tarefa tarefaSalva = service.salvar(tarefa);
        TarefaReponse reponse = TarefaMapper.toTarefaResponse(tarefaSalva);
        return ResponseEntity.status(CREATED).body(reponse);
    }

    @Override
    public ResponseEntity<TarefaReponse> alocar(final Long tarefaId, final Long pessoaId) {
        service.alocar(tarefaId, pessoaId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TarefaReponse> finalizarTarefa(final Long tarefaId) {
        service.finalizarTarefa(tarefaId);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<List<TarefaListarAntigasResponse>> listarTarefasPendentes() {
        return ResponseEntity.ok(service.listarTarefasPendentesMaisAntigas());
    }

}
