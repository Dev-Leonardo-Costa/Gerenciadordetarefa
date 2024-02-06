package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.api.request.DepartamentoRequest;
import br.com.leonardocosta.gerenciadordetarefa.api.response.DepartamentoResponse;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<DepartamentoResponse> listarDepartamento() {
        return DepartamentoResponse.getDepartamentoResponses(repository.listarDepartamentosComQuantidadeDePessoasETarefas());
    }

}