package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.*;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    private final DepartamentoService departamentoService;

    public PessoaCreateDTO salvar(final PessoaCreateDTO pessoaDTO) {
        final Pessoa entity = PessoaCreateDTO.toModel(pessoaDTO);
        departamentoService.buscarPorIdDepartamentoParaPessoa(entity.getDepartamento().getId());
        repository.save(entity);
        return PessoaCreateDTO.fromModel(entity);
    }

    public Pessoa buscarPorId(final Long pessoaId) {
        return repository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException(String.format("Pessoa de código: %d não encontrada",pessoaId)));
    }

    public void removerPessoa(final Long pessoaId) {
        repository.deleteById(pessoaId);
    }

    public Pessoa alterar(final Long pessoaId, final PessoaCreateDTO pessoaCreateDTO) {
        final Pessoa pessoaExistente = buscarPorId(pessoaId);
        pessoaExistente.setNome(pessoaCreateDTO.getNome());
        Departamento departamento = departamentoService.buscarPorId(pessoaCreateDTO.getDepartamento());
        pessoaExistente.setDepartamento(departamento);
        return repository.save(pessoaExistente);
    }

    public List<PessoaDTO> listarInformacoesPessoas() {
        return repository.listarPessoas();
    }

    public List<PessoaPorNomeEPeriodoProjection> buscarPessoasPorNomeEPeriodo(String nome, Date dataInicio, Date dataFim) {
        return repository.buscarPessoasPorNomeEPeriodo(nome, dataInicio, dataFim);
    }

}