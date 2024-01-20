package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaCreateDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaGastosDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.StandarError;
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
    ResponseEntity<PessoaCreateDTO> registrar(
            @Parameter(description = "Pessoa", required = true, example = "Leonardo Costa")
            @RequestBody final PessoaCreateDTO pessoa
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
            @ApiResponse(responseCode = "400", description = "Requisição inválida",    content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
    })
    @Operation(summary = "Altera os dados de uma pessoa")
    @PutMapping("/{pessoaId}")
    ResponseEntity<PessoaCreateDTO> alterar(@PathVariable final Long pessoaId, @RequestBody final PessoaCreateDTO pessoaUpdateDTO);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa removida"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandarError.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Erro interno",    content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Remove uma pessoa")
    @DeleteMapping("/{pessoaId}")
    ResponseEntity<?> remover(@PathVariable final Long pessoaId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno",    content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",    content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
    })
    @Operation(summary = "Busca todas as pessoas")
    @GetMapping
    ResponseEntity<List<PessoaDTO>> listarPessoa();

    @GetMapping("/pessoas")
    List<Pessoa> listarPessoas();

//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Pessoas encontradas"),
//            @ApiResponse(responseCode = "500", description = "Erro interno",    content = @Content(
//                    mediaType = APPLICATION_JSON_VALUE,
//                    schema = @Schema(implementation = StandarError.class)
//            )),
//            @ApiResponse(responseCode = "400", description = "Requisição inválida",    content = @Content(
//                    mediaType = APPLICATION_JSON_VALUE,
//                    schema = @Schema(implementation = StandarError.class)
//            )),
//    })
//    @Operation(summary = "Busca todas as pessoas por nome e pelo periodo")
//    @GetMapping("/gastos")


}
