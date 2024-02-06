package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaPorNomeEPeriodoResponse;
import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaResponseList;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    private final DepartamentoService departamentoService;

    public Pessoa salvar(final Pessoa pessoa) {
        departamentoService.buscarPorIdDepartamentoParaPessoa(pessoa.getDepartamento().getId());
        return repository.save(pessoa);
    }

    public Pessoa buscarPorId(final Long pessoaId) {
        return repository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException(String.format("Pessoa de código: %d não encontrada",pessoaId)));
    }

    public void removerPessoa(final Long pessoaId) {
        repository.deleteById(pessoaId);
    }

    public Pessoa alterar(final Long pessoaId, final Pessoa pessoa) {
        Pessoa pessoaExistente = buscarPorId(pessoaId);
        pessoaExistente.setNome(pessoa.getNome());
        Departamento departamento = departamentoService.buscarPorId(pessoa.getDepartamento().getId());
        pessoaExistente.setDepartamento(departamento);
        return repository.save(pessoaExistente);
    }

    public List<PessoaResponseList> listarInformacoesPessoas() {
        return repository.listarPessoas();
    }

    public List<PessoaPorNomeEPeriodoResponse> buscarPessoasPorNomeEPeriodo(String nome, Date dataInicio, Date dataFim) {
        return repository.buscarPessoasPorNomeEPeriodo(nome, dataInicio, dataFim);
    }

}