package br.com.leonardocosta.gerenciadordetarefa.controller;

import br.com.leonardocosta.gerenciadordetarefa.domain.dto.DepartamentoDTO;
import br.com.leonardocosta.gerenciadordetarefa.domain.exception.StandarError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = "Departamento" , description = "Controlador para departamentos")
@RequestMapping("/departamentos")
public interface DepartamentoController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departamentos encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno",    content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = StandarError.class)
            )),
    })
    @Operation(summary = "Busca departamentos com quantidade de pessoas e tarefas")
    @GetMapping
    ResponseEntity<List<DepartamentoDTO>> listarDepartamentosComQuantidadeDePessoasETarefas();

}
