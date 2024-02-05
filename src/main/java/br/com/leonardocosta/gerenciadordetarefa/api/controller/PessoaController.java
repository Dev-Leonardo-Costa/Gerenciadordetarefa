package br.com.leonardocosta.gerenciadordetarefa.api.controller;

import br.com.leonardocosta.gerenciadordetarefa.api.request.PessoaRequest;
import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaPorNomeEPeriodoResponse;
import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaResponse;
import br.com.leonardocosta.gerenciadordetarefa.api.response.PessoaResponseList;
import br.com.leonardocosta.gerenciadordetarefa.exception.StandarError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = "Pessoas", description = "Controlador resposavel por gerenciar pessoa")
@RequestMapping("/pessoas")
public interface PessoaController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada"),
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
    @Operation(summary = "Registra uma nova pessoa")
    @PostMapping
    ResponseEntity<PessoaResponse> registrar(
            @Parameter(description = "Pessoa", required = true, example = "Leonardo Costa")
            @RequestBody final PessoaRequest request
    );

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa alterada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
    })
    @Operation(summary = "Altera os dados de uma pessoa")
    @PutMapping("/{pessoaId}")
    ResponseEntity<PessoaResponse> alterar(@PathVariable final Long pessoaId, @RequestBody final PessoaRequest request);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa removida"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Remove uma pessoa")
    @DeleteMapping("/{pessoaId}")
    ResponseEntity<Void> remover(@PathVariable final Long pessoaId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
    })
    @Operation(summary = "Busca todas as pessoas")
    @GetMapping
    ResponseEntity<List<PessoaResponseList>> listarPessoa();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
    })
    @Operation(summary = "Busca todas as pessoas por nome e pelo periodo")
    @GetMapping("/gastos")
    ResponseEntity<List<PessoaPorNomeEPeriodoResponse>> buscarMediaHorasPorTarefa(
            @RequestParam("nome") String nome,
            @RequestParam("dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim
    );

}
