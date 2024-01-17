package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.request.TarefaCreateRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Tarefas" , description = "Controlador para gerenciar tarefa")
@RequestMapping("/tarefas")
public interface TarefaController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @Operation(summary = "Adiciona uma tarefa")
    @PostMapping
    ResponseEntity<Void> adiconarTarefa(@RequestBody TarefaCreateRecord tarefa);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa alocada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @Operation(summary = "Aloca uma tarefa para uma pessoa")
    @PutMapping("/alocar/{tarefaId}")
    ResponseEntity<Tarefa> alocar(@PathVariable final Long tarefaId, @RequestParam final Long pessoaId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Busca uma tarefa pelo seu ID")
    @GetMapping("/{id}")
    ResponseEntity<Tarefa> buscar(@PathVariable Long id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa finalizada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Finaliza uma tarefa")
    @PutMapping("/finalizar/{tarefaId}")
    ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Long tarefaId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @Operation(summary = "Busca as tarefas pendentes por limite que desejar e com os prazos mais antigos")
    @GetMapping("/pendentes-meu")
    ResponseEntity<List<Tarefa>> listarTarefasPendentesMaisAntigas(@RequestParam(defaultValue = "3") int limit);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @Operation(summary = "Busca 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos")
    @GetMapping("/pendentes")
    ResponseEntity<List<Tarefa>> listarTarefasPendentesMaisAntigas();

}
