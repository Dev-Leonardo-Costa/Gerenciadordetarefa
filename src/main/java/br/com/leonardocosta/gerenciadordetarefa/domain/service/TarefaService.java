package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.TarefaFinalizadaException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.TarefaRepository;
import br.com.leonardocosta.gerenciadordetarefa.request.TarefaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

    private final PessoaService pessoaService;

    public Tarefa salvar(TarefaRecord tarefa) {
        Tarefa entidade = Tarefa.fromTo(tarefa);
        return repository.save(entidade);
    }

    public Tarefa alocar(final Long tarefaId, final Long pessoaId) {
        Tarefa tarefa = buscarPorId(tarefaId);
        Pessoa pessoa = pessoaService.buscarPorId(pessoaId);
        tarefa.setPessoa(pessoa);
        return repository.save(tarefa);
    }

    public Tarefa buscarPorId(final Long tarefaId) {
        return repository.findById(tarefaId)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada, código: " + tarefaId));
    }

    public Tarefa finalizarTarefa(final Long tarefaId) {
        var tarefa = buscarPorId(tarefaId);

        if (tarefa.isFinalizada()) {
            throw new TarefaFinalizadaException("Tarefa já se encontra finalizada");
        }

        if (tarefa.getPessoa() == null) {
            throw new NotFoundException("A tarefa não está associada a uma pessoa");
        }

        tarefa.setFinalizado(true);
        return repository.save(tarefa);
    }


}
