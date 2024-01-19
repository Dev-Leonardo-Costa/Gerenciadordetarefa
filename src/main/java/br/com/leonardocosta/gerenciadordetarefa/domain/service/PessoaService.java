package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.*;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        repository.save(entity);
        return PessoaCreateDTO.fromModel(entity);
    }

    public Pessoa buscarPorId(final Long pessoaId) {
        return repository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada de código: " + pessoaId));
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

    public List<PessoaGastosDTO> buscarPessoasPorNomeEPeriodo(String nome, Date dataInicio, Date dataFim) {
        List<Object[]> resultados = repository.buscarPessoasPorNomeEPeriodo(nome, dataInicio, dataFim);
        List<PessoaGastosDTO> dtos = new ArrayList<>();

        for (Object[] resultado : resultados) {
            String nomePessoa = (String) resultado[0];
            Double mediaHorasGastas = (Double) resultado[1];

            PessoaGastosDTO dto = new PessoaGastosDTO(nomePessoa, mediaHorasGastas);
            dtos.add(dto);
        }

        return dtos;
    }

    private List<PessoaDTO> getPessoaDTOS(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(pessoa -> {
                    PessoaDTO pessoaDTO = new PessoaDTO();
                    pessoaDTO.setNome(pessoa.getNome());
                    pessoaDTO.setDepartamento(pessoa.getDepartamento().getNome());
                    pessoaDTO.setTotalHorasGasta(calcularTotalHorasTarefas(pessoa.getId()));
                    return pessoaDTO;
                })
                .collect(Collectors.toList());
    }


    private int calcularTotalHorasTarefas(Long pessoaId) {
        Pessoa pessoa = buscarPorId(pessoaId);
        return pessoa.horas();
    }

}