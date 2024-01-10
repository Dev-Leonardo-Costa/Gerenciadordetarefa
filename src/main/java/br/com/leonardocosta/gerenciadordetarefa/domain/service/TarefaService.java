package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    @Autowired
    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public Tarefa salvar(final Tarefa tarefa) {
        return repository.save(tarefa);
    }

}
