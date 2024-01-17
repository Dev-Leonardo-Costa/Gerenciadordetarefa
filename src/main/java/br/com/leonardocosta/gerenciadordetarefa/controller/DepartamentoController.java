package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Departamento" , description = "Controlador para departamentos")
@RequestMapping("/departamentos")
public interface DepartamentoController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departamentos encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Busca departamentos com quantidade de pessoas e tarefas")
    @GetMapping
    ResponseEntity<List<DepartamentoDTO>> listarDepartamentosComQuantidadeDePessoasETarefas();

}
