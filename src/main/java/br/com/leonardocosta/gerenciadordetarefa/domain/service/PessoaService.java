package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public Pessoa salvar(final Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public Pessoa buscarPorId(final Long pessoaId) {
        return repository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada de código: " + pessoaId));
    }

    public void removerPessoa(final Long pessoaId) {
        repository.deleteById(pessoaId);
    }

    public Pessoa alterar(final Long pessoaId, final Pessoa pessoa) {
        Pessoa pessoaExistente = buscarPorId(pessoaId);
        pessoaExistente.setNome(pessoa.getNome());
        return repository.save(pessoaExistente);
    }

    public List<PessoaDTO> listarInformacoesPessoas() {
        List<Pessoa> pessoas = repository.findAll();
        return getPessoaDTOS(pessoas);
    }

    public List<DepartamentoDTO> listarDepartamentosComQuantidadeDePessoasETarefas() {
        List<Object[]> resultados = repository.listarDepartamentosComQuantidadeDePessoasETarefas();

        return resultados.stream()
                .map(result -> new DepartamentoDTO(
                        (String) result[0],
                        ((Number) result[1]).longValue(),
                        ((Number) result[2]).longValue()
                ))
                .collect(Collectors.toList());
    }

    private List<PessoaDTO> getPessoaDTOS(List<Pessoa> pessoas) {
        List<PessoaDTO> pessoasDTO = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            PessoaDTO pessoaDTO = new PessoaDTO();
            pessoaDTO.setNome(pessoa.getNome());
            pessoaDTO.setDepartamento(pessoa.getDepartamento());
            pessoaDTO.setTotalHorasGasta(calcularTotalHorasTarefas(pessoa.getId()));

            pessoasDTO.add(pessoaDTO);
        }

        return pessoasDTO;
    }

    private int calcularTotalHorasTarefas(Long pessoaId) {
        Pessoa pessoa = buscarPorId(pessoaId);
        return pessoa.horas();
    }

}
