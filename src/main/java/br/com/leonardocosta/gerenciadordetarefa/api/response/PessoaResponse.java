package br.com.leonardocosta.gerenciadordetarefa.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponse {

    private Long id;

    private String nome;

    private Long departamento;

}
