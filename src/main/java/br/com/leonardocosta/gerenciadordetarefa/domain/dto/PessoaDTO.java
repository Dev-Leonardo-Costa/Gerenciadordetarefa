package br.com.leonardocosta.gerenciadordetarefa.domain.dto;

import lombok.Data;

@Data
public class PessoaDTO {

    private String nome;

    private String departamento;

    private int totalHorasGasta;

}
