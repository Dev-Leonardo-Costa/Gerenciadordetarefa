package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoProjection;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.DepartamentoRepository;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.ResultListTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoRepository repository;
    public Departamento buscarPorId(final Long idDepartamento) {
        return repository.findById(idDepartamento)
                .orElseThrow(() -> new NotFoundException(String.format("Departamento de código: %d não encontrado", idDepartamento)));
    }
    public Departamento buscarPorIdDepartamentoParaPessoa(final Long idDepartamento) {
        return repository.findById(idDepartamento)
                .orElseThrow(() -> new NotFoundException(String.format("Departamento não encontrado de código: %d para cadastrar pessoa", idDepartamento)));
    }

    public List<DepartamentoDTO> listarDepartamento() {
        List<DepartamentoProjection> projections = repository.listarDepartamentosComQuantidadeDePessoasETarefas();
        return DepartamentoDTO.getDepartamentoDTOS(projections);
    }

}