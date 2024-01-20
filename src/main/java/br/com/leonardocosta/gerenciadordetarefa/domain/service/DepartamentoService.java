package br.com.leonardocosta.gerenciadordetarefa.domain.service;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Departamento;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.NotFoundException;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.DepartamentoRepository;
import br.com.leonardocosta.gerenciadordetarefa.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}