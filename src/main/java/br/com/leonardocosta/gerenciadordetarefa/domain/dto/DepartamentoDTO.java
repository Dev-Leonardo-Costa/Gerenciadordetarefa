package br.com.leonardocosta.gerenciadordetarefa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartamentoDTO {

    private String departamento;

    private Long pessoas;

    private Long tarefas;

}
