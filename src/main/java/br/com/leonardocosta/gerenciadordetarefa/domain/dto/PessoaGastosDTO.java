package br.com.leonardocosta.gerenciadordetarefa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PessoaGastosDTO {

    private String nome;

    private Double mediaHorasPorTarefa;

}
