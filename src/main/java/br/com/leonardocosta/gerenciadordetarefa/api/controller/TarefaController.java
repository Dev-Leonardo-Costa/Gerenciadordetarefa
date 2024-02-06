package br.com.leonardocosta.gerenciadordetarefa.api.controller;

import br.com.leonardocosta.gerenciadordetarefa.api.request.TarefaRequest;
import br.com.leonardocosta.gerenciadordetarefa.api.response.TarefaListarAntigasResponse;
import br.com.leonardocosta.gerenciadordetarefa.api.response.TarefaReponse;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Tarefa;
import br.com.leonardocosta.gerenciadordetarefa.exception.StandarError;
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
    ResponseEntity<TarefaReponse> registrarTarefa(@RequestBody TarefaRequest request);

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
    @Operation(summary = "Busca 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos")
    @GetMapping("/pendentes")
    ResponseEntity<List<TarefaListarAntigasResponse>> listarTarefasPendentes();

}
