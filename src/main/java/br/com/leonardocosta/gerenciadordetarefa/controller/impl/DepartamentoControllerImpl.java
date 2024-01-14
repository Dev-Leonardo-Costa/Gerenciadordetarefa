package br.com.leonardocosta.gerenciadordetarefa.controller.impl;

import br.com.leonardocosta.gerenciadordetarefa.controller.DepartamentoController;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departamentos")
public class DepartamentoControllerImpl implements DepartamentoController {

    private final PessoaService service;
    @Override
    public ResponseEntity<List<DepartamentoDTO>> listarDepartamentosComQuantidadeDePessoasETarefas() {
        return ResponseEntity.ok(service.listarDepartamentosComQuantidadeDePessoasETarefas());
    }

}
