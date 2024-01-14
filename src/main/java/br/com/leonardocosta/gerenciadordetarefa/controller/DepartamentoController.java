package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface DepartamentoController {

    @GetMapping
    ResponseEntity<List<DepartamentoDTO>> listarDepartamentosComQuantidadeDePessoasETarefas();

}
