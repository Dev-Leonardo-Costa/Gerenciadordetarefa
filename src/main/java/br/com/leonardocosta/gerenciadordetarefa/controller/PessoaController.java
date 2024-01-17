package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.dto.PessoaGastosDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.entity.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Tag(name = "Pessoa", description = "Controlador resposavel por gerenciar pessoa")
@RequestMapping("/pessoa")
public interface PessoaController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @Operation(summary = "Registra uma nova pessoa")
    @PostMapping
    ResponseEntity<Void> registrar(@RequestBody final Pessoa pessoa);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa alterada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Altera os dados de uma pessoa")
    @PutMapping("/{pessoaId}")
    ResponseEntity<Pessoa> alterar(@PathVariable final Long pessoaId, @RequestBody final Pessoa pessoa);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa removida"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Remove uma pessoa")
    @DeleteMapping("/{pessoaId}")
    ResponseEntity<?> remover(@PathVariable final Long pessoaId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Busca todas as pessoas")
    @GetMapping
    ResponseEntity<List<PessoaDTO>> listarPessoa();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Busca todas as pessoas por nome e pelo periodo")
    @GetMapping("/gastos")
    ResponseEntity<List<PessoaGastosDTO>> buscarPessoasPorNomeEPeriodo(
            @RequestParam String nome,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataInicio,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataFim
    );


}
