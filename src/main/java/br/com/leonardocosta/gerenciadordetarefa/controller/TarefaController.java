package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.StandarError;
import br.com.leonardocosta.gerenciadordetarefa.request.TarefaCreateRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = "Tarefas", description = "Controlador para gerenciar tarefa")
@RequestMapping("/tarefas")
public interface TarefaController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
    })
    @Operation(summary = "Adiciona uma tarefa")
    @PostMapping
    ResponseEntity<Void> adiconarTarefa(@RequestBody TarefaCreateRecord tarefa);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa alocada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandarError.class)
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @Operation(summary = "Aloca uma tarefa para uma pessoa")
    @PutMapping("/alocar/{tarefaId}")
    ResponseEntity<Tarefa> alocar(@PathVariable final Long tarefaId, @RequestParam final Long pessoaId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa finalizada"),
            @ApiResponse(
                    responseCode = "404", description = "Tarefa não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandarError.class)
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
    })
    @Operation(summary = "Finaliza uma tarefa")
    @PutMapping("/finalizar/{tarefaId}")
    ResponseEntity<Tarefa> finalizarTarefa(
            @Parameter(description = "Tarefa", required = true, example = "1") @PathVariable Long tarefaId
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    ))
    })
    @Operation(summary = "Busca as tarefas pendentes por limite que desejar e com os prazos mais antigos")
    @GetMapping("/pendentes-meu")
    ResponseEntity<List<Tarefa>> listarTarefasPendentesMaisAntigas(@RequestParam(defaultValue = "3") int limit);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    ))
    })
    @Operation(summary = "Busca 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos")
    @GetMapping("/pendentes")
    ResponseEntity<List<Tarefa>> listarTarefasPendentesMaisAntigas();

}
