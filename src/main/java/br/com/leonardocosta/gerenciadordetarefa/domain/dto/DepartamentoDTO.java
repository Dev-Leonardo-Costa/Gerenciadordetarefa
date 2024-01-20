package br.com.leonardocosta.gerenciadordetarefa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoDTO {

    private String departamento;

    private Long qtdPessoas;

    private Long qtdTarefas;

}
