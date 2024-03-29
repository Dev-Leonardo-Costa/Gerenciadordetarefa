package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.api.response.TarefaListarAntigasResponse;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.exception.TarefaFinalizadaException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    public static final String TAREFA_NAO_ENCONTRADA = "Tarefa não encontrada de código: ";
    public static final String ESTA_TAREFA_JA_FOI_CONCLUIDA = "Esta tarefa já foi concluída anteriormente";
    public static final String ESTA_TAREFA_NAO_ESTA_ASSOCIADA = "Esta tarefa não está associada a uma pessoa";

    private final TarefaRepository repository;
    private final PessoaService pessoaService;

    public Tarefa salvar(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public Tarefa alocar(final Long tarefaId, final Long pessoaId) {
        Tarefa tarefa = buscarPorId(tarefaId);
        Pessoa pessoa = pessoaService.buscarPorId(pessoaId);
        tarefa.setPessoa(pessoa);
        return repository.save(tarefa);
    }

    public Tarefa buscarPorId(final Long tarefaId) {
        return repository.findById(tarefaId)
                .orElseThrow(() -> new NotFoundException(TAREFA_NAO_ENCONTRADA + tarefaId));
    }

    public Tarefa finalizarTarefa(final Long tarefaId) {
        var tarefa = buscarPorId(tarefaId);
        validarTarefaParaConclusao(tarefa);
        tarefa.setFinalizado(true);
        return repository.save(tarefa);
    }


    public List<TarefaListarAntigasResponse> listarTarefasPendentesMaisAntigas() {
        return repository.listarTarefasPendentesSemPessoa();
    }

    private void validarTarefaParaConclusao(Tarefa tarefa) {

        if (tarefa.isFinalizado()) {
            throw new TarefaFinalizadaException(ESTA_TAREFA_JA_FOI_CONCLUIDA);
        }

        if (tarefa.getPessoa() == null) {
            throw new NotFoundException(ESTA_TAREFA_NAO_ESTA_ASSOCIADA);
        }

    }

}
